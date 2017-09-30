drop table if exists message_origin;

create table message_origin (
	id 				integer primary key not null,
	messageId		integer,
	origin			varchar(20),
	dt_creation		varchar(20)
);
