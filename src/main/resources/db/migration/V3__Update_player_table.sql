alter table Player
    add userId BIGINT null;

alter table Player
    add constraint Player_User_id_fk
        foreign key (userId) references User (id);