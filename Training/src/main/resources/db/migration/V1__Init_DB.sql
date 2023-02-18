create table hibernate_sequence (next_val bigint);

insert into hibernate_sequence values ( 2 );

create table message (
    id bigint not null,
    filename varchar(255),
    tag varchar(1024),
    text varchar(1024) not null,
    user_id bigint,
    primary key (id)
);

create table user_role (
    user_id bigint not null,
    roles varchar(255)
);

create table usr (
    id bigint not null,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);


alter table message
    add constraint message_usr_fk
        foreign key (user_id) references usr(id);

alter table user_role
    add constraint user_role_usr_fk
        foreign key (user_id) references usr(id);