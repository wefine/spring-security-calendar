package org.wefine.spring.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wefine.spring.config.jooq.JooqDaoImpl;
import org.wefine.spring.jooq.tables.pojos.Events;
import org.wefine.spring.jooq.tables.records.EventsRecord;

import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> findUsersWithName() {
        String sql =
                " SELECT  e.*, owner.first_name AS of, owner.last_name AS ol, attendee.first_name AS af, attendee.last_name AS al \n" +
                        " FROM events e \n" +
                        " LEFT JOIN users owner ON e.owner = owner.id \n" +
                        " LEFT JOIN users attendee  ON e.attendee = attendee.id";

        return dsl.fetch(sql).map(Record::intoMap);
    }
}
