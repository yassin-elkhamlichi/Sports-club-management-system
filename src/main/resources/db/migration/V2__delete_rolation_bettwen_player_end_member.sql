-- Drop foreign key from Player table
alter table Player
    drop foreign key Player_ibfk_2;

-- Drop the index
drop index memberId on Player;

-- Drop the column
alter table Player
    drop column memberId;

-- If Member table has a reverse foreign key to Player, drop it too
alter table Member
    drop foreign key Member_ibfk_1;
