package org.wefine.spring.web.service;

import org.wefine.spring.jooq.tables.pojos.Users;
import org.wefine.spring.jooq.tables.pojos.Events;
import org.wefine.spring.repository.EventsDao;
import org.wefine.spring.repository.UsersDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * A default implementation of {@link CalendarService} that delegates to {@link EventsDao} and {@link UsersDao}.
 *
 * @author Rob Winch
 *
 */
@Repository
public class DefaultCalendarService implements CalendarService {
    private final EventsDao eventDao;
    private final UsersDao userDao;

    @Autowired
    public DefaultCalendarService(EventsDao eventDao, UsersDao userDao) {
        if (eventDao == null) {
            throw new IllegalArgumentException("eventDao cannot be null");
        }
        if (userDao == null) {
            throw new IllegalArgumentException("userDao cannot be null");
        }
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

    public Events getEvent(Long eventId) {
        return eventDao.findById(eventId);
    }

    public Long createEvent(Events event) {
        eventDao.insert(event);

        return event.getId();
    }

    public List<Events> findForUser(Long userId) {
        return eventDao.findForUser(userId);
    }

    public List<Events> getEvents() {
        return eventDao.findAll();
    }

    public List<Map<String, Object>> getEventsWithNames() {
        return eventDao.findUsersWithName();
    }

    public Users getUser(Long id) {
        return userDao.findById(id);
    }

    public Users findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public List<Users> findUsersByEmail(String partialEmail) {
        return userDao.findUsersByEmail(partialEmail);
    }

    public Long createUser(Users user) {
         userDao.insert(user);
        return user.getId();
    }
}