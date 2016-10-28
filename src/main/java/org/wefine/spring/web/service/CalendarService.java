package org.wefine.spring.web.service;

import org.wefine.spring.jooq.tables.pojos.Users;
import org.wefine.spring.jooq.tables.pojos.Events;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Map;

public interface CalendarService {

    /**
     * Gets a {@link Users} for a specific {@link Users#getId()}.
     *
     * @param id
     *            the {@link Users#getId()} of the {@link Users} to find.
     * @return a {@link Users} for the given id. Cannot be null.
     * @throws EmptyResultDataAccessException
     *             if the {@link Users} cannot be found
     */
    Users getUser(Long id);

    /**
     * Finds a given {@link Users} by email address.
     *
     * @param email
     *            the email address to use to find a {@link Users}. Cannot be null.
     * @return a {@link Users} for the given email or null if one could not be found.
     * @throws IllegalArgumentException
     *             if email is null.
     */
    Users findUserByEmail(String email);

    /**
     * Finds any {@link Users} that has an email that starts with {@code partialEmail}.
     *
     * @param partialEmail
     *            the email address to use to find {@link Users}s. Cannot be null or empty String.
     * @return a List of {@link Users}s that have an email that starts with given partialEmail. The returned
     *         value will never be null. If no results are found an empty List will be returned.
     * @throws IllegalArgumentException
     *             if email is null or empty String.
     */
    List<Users> findUsersByEmail(String partialEmail);

    /**
     * Creates a new {@link Users}.
     *
     * @param user
     *            the new {@link Users} to create. The {@link Users#getId()} must be null.
     * @return the new {@link Users#getId()}.
     * @throws IllegalArgumentException
     *             if {@link Users#getId()} is non-null.
     */
    Long createUser(Users user);

    /**
     * Given an id gets an {@link Events}.
     *
     * @param EventsId
     *            the {@link Events#getId()}
     * @return the {@link Events}. Cannot be null.
     * @throws RuntimeException
     *             if the {@link Events} cannot be found.
     */
    Events getEvent(Long EventsId);

    /**
     * Creates a {@link Events} and returns the new id for that {@link Events}.
     *
     * @param Events
     *            the {@link Events} to create. Note that the {@link Events#getId()} should be null.
     * @return the new id for the {@link Events}
     * @throws RuntimeException
     *             if {@link Events#getId()} is non-null.
     */
    Long createEvent(Events Events);

    /**
     * Finds the {@link Events}'s that are intended for the {@link Users}.
     *
     * @param userId
     *            the {@link Users#getId()} to obtain {@link Events}'s for.
     * @return a non-null {@link List} of {@link Events}'s intended for the specified {@link Users}. If the
     *         {@link Users} does not exist an empty List will be returned.
     */
    List<Events> findForUser(Long userId);

    /**
     * Gets all the available {@link Events}'s.
     *
     * @return a non-null {@link List} of {@link Events}'s
     */
    List<Events> getEvents();

    List<Map<String, Object>> getEventsWithNames();
}
