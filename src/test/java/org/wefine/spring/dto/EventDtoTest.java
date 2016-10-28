package org.wefine.spring.dto;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wefine.spring.AbstractSpringTest;
import org.wefine.spring.jooq.tables.Events;
import org.wefine.spring.jooq.tables.Users;
import org.wefine.spring.repository.EventsDao;
import org.wefine.spring.repository.UsersDao;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EventDtoTest extends AbstractSpringTest {
    @Autowired
    private DSLContext dsl;

    @Autowired
    private UsersDao userDao;

    @Autowired
    private EventsDao eventDao;

    @Test
    public void test() {
        Users u = Users.USERS.as("user");
        Events e = Events.EVENTS.as("event");

        List result = dsl.select().from(e).leftJoin(u).on(e.OWNER.eq(u.ID))
                .fetch()
                .map(r -> {
                    HashMap<String, Object> map = new HashMap<>();
                    for (Field<?> field : r.fields()) {
                        String aa = field.toString().replaceAll("\"", "");
                        System.out.println(field.toString() + ">>" + aa);
                    }
                    return r;
                })
                .stream()
                .collect(Collectors.toList());


        System.out.println(result);
    }

}
