
CREATE DATABASE  IF NOT EXISTS calendar DEFAULT CHARACTER SET utf8 ;

GRANT ALL PRIVILEGES ON calendar.* TO 'calendar'@'%' IDENTIFIED BY 'calendar';
GRANT ALL PRIVILEGES ON calendar.* TO 'calendar'@'localhost' IDENTIFIED BY 'calendar';

flush privileges;

USE calendar;

drop TABLE if EXISTS users;
drop TABLE if EXISTS events;

create table users (
  id bigint  NOT NULL AUTO_INCREMENT,
  email varchar(32) not null unique,
  password varchar(32) not null,
  first_name varchar(32) not null,
  last_name varchar(32) not null,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table events (
  id bigint  NOT NULL AUTO_INCREMENT,
  time timestamp not null,
  summary varchar(256) not null,
  description varchar(500) not null,
  owner bigint not null,
  attendee bigint not null,
  PRIMARY KEY (id),
  FOREIGN KEY(owner) REFERENCES users(id),
  FOREIGN KEY(attendee) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into users(id,email,password,first_name,last_name) values (1,'user1@example.com','user1','User','1');
insert into users(id,email,password,first_name,last_name) values (2,'admin1@example.com','admin1','Admin','1');
insert into users(id,email,password,first_name,last_name) values (3,'user2@example.com','user2','User','2');

insert into events (id,time,summary,description,owner,attendee) values (100,'2013-10-04 20:30:00','Birthday Party','This is going to be a great birthday',1,2);
insert into events (id,time,summary,description,owner,attendee) values (101,'2013-12-23 13:00:00','Conference Call','Call with the client',3,1);
insert into events (id,time,summary,description,owner,attendee) values (102,'2014-01-23 11:30:00','Lunch','Eating lunch together',2,3);