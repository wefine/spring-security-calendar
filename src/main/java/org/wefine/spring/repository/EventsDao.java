package org.wefine.spring.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wefine.spring.config.jooq.JooqDaoImpl;
import org.wefine.spring.jooq.tables.pojos.Events;
import org.wefine.spring.jooq.tables.records.EventsRecord;

@Repository
public class EventsDao extends JooqDaoImpl<EventsRecord, Events, Long> {

    @Autowired
    public EventsDao(DSLContext dsl) {
        super(org.wefine.spring.jooq.tables.Events.EVENTS, Events.class, dsl);
    }

    @Override
    protected Long getId(Events object) {
        return object.getId();
    }
}
