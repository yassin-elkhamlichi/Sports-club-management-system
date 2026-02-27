-- 1. Drop the existing foreign key on Attendance
ALTER TABLE Attendance
    DROP FOREIGN KEY Attendance_ibfk_2;

-- 2. Remove AUTO_INCREMENT from Player.id so we can drop the PK
ALTER TABLE Player
    MODIFY COLUMN id BIGINT NOT NULL;

-- 3. Drop the old primary key
ALTER TABLE Player
    DROP PRIMARY KEY;

-- 4. Make userId the new primary key
ALTER TABLE Player
    ADD PRIMARY KEY (userId);

-- 5. Update Attendance.playerId values to match userId instead of id
UPDATE Attendance a
    JOIN Player p ON a.playerId = p.id
SET a.playerId = p.userId;

-- 6. Re-add the foreign key now pointing to Player(userId)
ALTER TABLE Attendance
    ADD CONSTRAINT Attendance_ibfk_2
        FOREIGN KEY (playerId) REFERENCES Player (userId)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- 7. Drop the old id column
ALTER TABLE Player
    DROP COLUMN id;