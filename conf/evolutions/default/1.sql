# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table patients (
  id                        bigint not null,
  firstName                 varchar(255),
  lastName                  varchar(255),
  dob                       varchar(255),
  constraint pk_patients primary key (id))
;

create sequence patients_seq;




# --- !Downs

drop table if exists patients cascade;

drop sequence if exists patients_seq;

