package org.wefine.spring.web.service;

import org.wefine.spring.jooq.tables.pojos.Users;

/**
 * Manages the current {@link Users}. This demonstrates how in larger applications it is good to abstract out
 * accessing the current user to return the application specific user rather than interacting with Spring Security
 * classes directly.
 *
 * @author Rob Winch
 *
 */
public interface UserContext {

    /**
     * Gets the currently logged in {@link Users} or null if there is no authenticated user.
     *
     * @return
     */
    Users getCurrentUser();

    /**
     * Sets the currently logged in {@link Users}.
     * @param user the logged in {@link Users}. Cannot be null.
     * @throws IllegalArgumentException if the {@link Users} is null.
     */
    void setCurrentUser(Users user);
}
