alter table Attendance
    drop foreign key Attendance_ibfk_2;

alter table Attendance
    add constraint Attendance_ibfk_2
        foreign key (playerId) references Player (userId)
            on update cascade on delete cascade;

alter table Player
    drop column id;

