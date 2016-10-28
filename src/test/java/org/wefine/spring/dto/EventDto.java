
package org.wefine.spring.dto;

import java.sql.Timestamp;

import org.wefine.spring.jooq.tables.pojos.Users;

public class EventDto {
	private Long id;
	private Timestamp time;
	private String summary;
	private String description;
	private Users owner;
	private long attendee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Users getOwner() {
		return owner;
	}

	public void setOwner(Users owner) {
		this.owner = owner;
	}

	public long getAttendee() {
		return attendee;
	}

	public void setAttendee(long attendee) {
		this.attendee = attendee;
	}
}
