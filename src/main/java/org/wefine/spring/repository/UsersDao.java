package org.wefine.spring.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wefine.spring.config.jooq.JooqDaoImpl;
import org.wefine.spring.jooq.tables.pojos.Users;
import org.wefine.spring.jooq.tables.records.UsersRecord;

@Repository
public class UsersDao extends JooqDaoImpl<UsersRecord, Users, Long> {

    @Autowired
    public UsersDao(DSLContext dsl) {
        super(org.wefine.spring.jooq.tables.Users.USERS, Users.class, dsl);
    }

    @Override
    protected Long getId(Users object) {
        return object.getId();
    }
}
