package org.wefine.spring.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wefine.spring.config.jooq.JooqDaoImpl;
import org.wefine.spring.jooq.tables.pojos.Users;
import org.wefine.spring.jooq.tables.records.UsersRecord;

import java.util.List;

import static org.wefine.spring.jooq.tables.Users.USERS;

@Repository
public class UsersDao extends JooqDaoImpl<UsersRecord, Users, Long> {

    @Autowired
    public UsersDao(DSLContext dsl) {
        super(USERS, Users.class, dsl);
    }

    @Override
    protected Long getId(Users object) {
        return object.getId();
    }

    public Users findUserByEmail(String email) {
        return fetchOne(USERS.EMAIL, email);
    }

    public List<Users> findUsersByEmail(String partialEmail) {
        return fetchLike(USERS.EMAIL, partialEmail);
    }
}
