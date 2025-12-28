alter table Player
    drop foreign key Player_ibfk_2;

drop index memberId on Player;

alter table Player
    drop column memberId;