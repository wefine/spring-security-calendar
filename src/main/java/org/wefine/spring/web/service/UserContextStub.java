package org.wefine.spring.web.service;

import org.wefine.spring.jooq.tables.pojos.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wefine.spring.repository.UsersDao;

/**
 * Returns the same user for every call to {@link #getCurrentUser()}. This is used prior to adding security, so that the
 * rest of the application can be used.
 *
 * @author Rob Winch
 */
@Component
public class UserContextStub implements UserContext {
    private final UsersDao userService;
    /**
     * The {@link Users#getId()} for the user that is representing the currently logged in user. This can be
     * modified using {@link #setCurrentUser(Users)}
     */
    private Long currentUserId = 0L;

    @Autowired
    public UserContextStub(UsersDao userService) {
        if (userService == null) {
            throw new IllegalArgumentException("userService cannot be null");
        }
        this.userService = userService;
    }

    @Override
    public Users getCurrentUser() {
        return userService.findById(currentUserId);
    }

    @Override
    public void setCurrentUser(Users user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        Long currentId = user.getId();
        if(currentId == null) {
            throw new IllegalArgumentException("user.getId() cannot be null");
        }
        this.currentUserId = currentId;
    }
}