# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table game (
  id                        bigint not null,
  loser                     varchar(255),
  moves                     bigint not null,
  points                    bigint not null,
  winner                    varchar(255),
  constraint pk_game primary key (id))
;

create table user (
  account                   varchar(255) not null,
  constraint pk_user primary key (account))
;

create sequence game_seq;

create sequence user_seq;

alter table game add constraint fk_game_loser_1 foreign key (loser) references user (account) on delete restrict on update restrict;
create index ix_game_loser_1 on game (loser);
alter table game add constraint fk_game_winner_2 foreign key (winner) references user (account) on delete restrict on update restrict;
create index ix_game_winner_2 on game (winner);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists game;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists game_seq;

drop sequence if exists user_seq;

