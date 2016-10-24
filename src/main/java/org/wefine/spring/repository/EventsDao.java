package org.wefine.spring.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wefine.spring.config.jooq.JooqDaoImpl;
import org.wefine.spring.jooq.tables.pojos.Events;
import org.wefine.spring.jooq.tables.records.EventsRecord;

import java.util.List;

import static org.wefine.spring.jooq.tables.Events.EVENTS;

@Repository
public class EventsDao extends JooqDaoImpl<EventsRecord, Events, Long> {

    @Autowired
    public EventsDao(DSLContext dsl) {
        super(EVENTS, Events.class, dsl);
    }

    @Override
    protected Long getId(Events object) {
        return object.getId();
    }

    public List<Events> findForUser(Long userId) {
        return fetchEqual(EVENTS.OWNER, userId);
    }
}
