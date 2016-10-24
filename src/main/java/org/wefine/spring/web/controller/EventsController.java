package org.wefine.spring.web.controller;

import org.wefine.spring.jooq.tables.pojos.Users;
import org.wefine.spring.jooq.tables.pojos.Events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wefine.spring.web.dto.CreateEventForm;
import org.wefine.spring.web.service.CalendarService;
import org.wefine.spring.web.service.UserContext;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Calendar;

@Controller
@RequestMapping("/events")
public class EventsController {
    private final CalendarService calendarService;
    private final UserContext userContext;

    @Autowired
    public EventsController(CalendarService calendarService, UserContext userContext) {
        this.calendarService = calendarService;
        this.userContext = userContext;
    }

    @RequestMapping("/")
    public ModelAndView events() {
        return new ModelAndView("events/list", "events", calendarService.getEvents());
    }

    @RequestMapping("/my")
    public ModelAndView myEvents() {
        Users currentUser = userContext.getCurrentUser();
        Long currentUserId = currentUser.getId();
        ModelAndView result = new ModelAndView("events/my", "events", calendarService.findForUser(currentUserId));
        result.addObject("currentUser", currentUser);
        return result;
    }

    @RequestMapping("/{eventId}")
    public ModelAndView show(@PathVariable Long eventId) {
        Events event = calendarService.getEvent(eventId);
        return new ModelAndView("events/show", "event", event);
    }

    @RequestMapping("/form")
    public String createEventForm(@ModelAttribute CreateEventForm createEventForm) {
        return "events/create";
    }

    /**
     * Populates the form for creating an event with valid information. Useful so that users do not have to think when
     * filling out the form for testing.
     *
     * @param createEventForm
     * @return
     */
    @RequestMapping(value = "/new", params = "auto")
    public String createEventFormAutoPopulate(@ModelAttribute CreateEventForm createEventForm) {
        // provide default values to make user submission easier
        createEventForm.setSummary("A new event....");
        createEventForm.setDescription("This was autopopulated to save time creating a valid event.");
        createEventForm.setWhen(Calendar.getInstance());

        // make the attendee not the current user
        Users currentUser = userContext.getCurrentUser();
        Long attendeeId = currentUser.getId() == 0 ? 1L : 0L;
        Users attendee = calendarService.getUser(attendeeId);
        createEventForm.setAttendeeEmail(attendee.getEmail());

        return "events/create";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createEvent(@Valid CreateEventForm createEventForm, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "events/create";
        }
        Users user = calendarService.findUserByEmail(createEventForm.getAttendeeEmail());
        if (user == null) {
            result.rejectValue("attendeeEmail", "attendeeEmail.missing",
                    "Could not find a user for the provided Attendee Email");
        }
        if (result.hasErrors()) {
            return "events/create";
        }

        if(user != null) {
            Events event = new Events();
            event.setAttendee(user.getId());
            event.setDescription(createEventForm.getDescription());
            event.setOwner(userContext.getCurrentUser().getId());
            event.setSummary(createEventForm.getSummary());
            event.setTime(new Timestamp(createEventForm.getWhen().getTimeInMillis()));
            calendarService.createEvent(event);

            redirectAttributes.addFlashAttribute("message", "Successfully added the new event");
        }

        return "redirect:/events/my";
    }
}