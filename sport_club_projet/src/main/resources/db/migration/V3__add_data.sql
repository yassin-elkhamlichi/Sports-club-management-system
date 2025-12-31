-- 1. Insert Sports
INSERT INTO Sport (name) VALUES
                             ('Football'), ('Basketball'), ('Tennis'), ('Volleyball'), ('Handball'),
                             ('Rugby'), ('Swimming'), ('Athletics'), ('Boxing'), ('Judo');

-- 2. Insert Facilities
INSERT INTO Facility (name, location, capacity, scheduleAvailability) VALUES
                                                                          ('Main Stadium', 'North Wing', 5000, '2025-01-20'),
                                                                          ('Indoor Arena', 'East Wing', 2000, '2025-01-20'),
                                                                          ('Training Ground A', 'South Wing', 100, '2025-01-21'),
                                                                          ('Training Ground B', 'South Wing', 100, '2025-01-21'),
                                                                          ('Olympic Pool', 'West Wing', 500, '2025-01-22'),
                                                                          ('Tennis Court 1', 'North Wing', 50, '2025-01-22'),
                                                                          ('Tennis Court 2', 'North Wing', 50, '2025-01-23'),
                                                                          ('Gymnasium', 'Center', 200, '2025-01-23'),
                                                                          ('Boxing Ring', 'Basement', 50, '2025-01-24'),
                                                                          ('Track Field', 'Outdoor', 1000, '2025-01-24');

-- 3. Insert Subscription Plans
INSERT INTO SubscriptionPlan (name, price, durationMonths, description) VALUES
                                                                            ('Basic Monthly', 29.99, 1, 'Access to gym only'),
                                                                            ('Standard Monthly', 49.99, 1, 'Gym + Pool access'),
                                                                            ('Premium Monthly', 79.99, 1, 'All facilities access'),
                                                                            ('Basic Annual', 300.00, 12, 'Yearly gym access'),
                                                                            ('Standard Annual', 500.00, 12, 'Yearly Gym + Pool'),
                                                                            ('Premium Annual', 800.00, 12, 'All access yearly'),
                                                                            ('Student Plan', 19.99, 1, 'Discounted gym access'),
                                                                            ('Family Pack', 120.00, 1, 'Access for 4 members'),
                                                                            ('Team Pass', 1000.00, 12, 'For registered teams'),
                                                                            ('Day Pass', 10.00, 0, 'Single day entry');

-- 4. Insert Categories
INSERT INTO Category (name, gender, minAge, maxAge, sportId) VALUES
                                                                 ('U18 Boys', 'MALE', 15, 18, 1),
                                                                 ('U18 Girls', 'FEMALE', 15, 18, 1),
                                                                 ('Senior Men', 'MALE', 19, 35, 1),
                                                                 ('Senior Women', 'FEMALE', 19, 35, 2),
                                                                 ('Junior Tennis', 'MALE', 10, 14, 3),
                                                                 ('Pro Swimmers', 'FEMALE', 16, 25, 7),
                                                                 ('Amateur Boxing', 'MALE', 18, 40, 9),
                                                                 ('Varsity Volley', 'FEMALE', 18, 22, 4),
                                                                 ('Rugby A', 'MALE', 20, 30, 6),
                                                                 ('Judo Kids', 'FEMALE', 8, 12, 10);

-- 5. Insert Users (Admins, Coaches, Members)
-- Even if you dropped the FK, we still match IDs for logical consistency
INSERT INTO User (email, password, firstname, lastname, phone, role) VALUES
-- 1 Admin
('admin@club.com', '$2a$10$dXJ3SW6G7', 'Super', 'Admin', '0600000000', 'ADMIN'),
-- 2-11 Coaches
('coach1@club.com', 'pass123', 'John', 'Doe', '0611111111', 'COACH'),
('coach2@club.com', 'pass123', 'Alice', 'Smith', '0611111112', 'COACH'),
('coach3@club.com', 'pass123', 'Bob', 'Brown', '0611111113', 'COACH'),
('coach4@club.com', 'pass123', 'Charlie', 'Davis', '0611111114', 'COACH'),
('coach5@club.com', 'pass123', 'David', 'Wilson', '0611111115', 'COACH'),
('coach6@club.com', 'pass123', 'Eva', 'Miller', '0611111116', 'COACH'),
('coach7@club.com', 'pass123', 'Frank', 'Moore', '0611111117', 'COACH'),
('coach8@club.com', 'pass123', 'Grace', 'Taylor', '0611111118', 'COACH'),
('coach9@club.com', 'pass123', 'Hank', 'Anderson', '0611111119', 'COACH'),
('coach10@club.com', 'pass123', 'Ivy', 'Thomas', '0611111120', 'COACH'),
-- 12-41 Members
('mem1@club.com', 'pass123', 'Liam', 'Martin', '0622222201', 'MEMBER'),
('mem2@club.com', 'pass123', 'Noah', 'Jackson', '0622222202', 'MEMBER'),
('mem3@club.com', 'pass123', 'Oliver', 'White', '0622222203', 'MEMBER'),
('mem4@club.com', 'pass123', 'Elijah', 'Harris', '0622222204', 'MEMBER'),
('mem5@club.com', 'pass123', 'James', 'Clark', '0622222205', 'MEMBER'),
('mem6@club.com', 'pass123', 'William', 'Lewis', '0622222206', 'MEMBER'),
('mem7@club.com', 'pass123', 'Benjamin', 'Robinson', '0622222207', 'MEMBER'),
('mem8@club.com', 'pass123', 'Lucas', 'Walker', '0622222208', 'MEMBER'),
('mem9@club.com', 'pass123', 'Henry', 'Young', '0622222209', 'MEMBER'),
('mem10@club.com', 'pass123', 'Alexander', 'Hall', '0622222210', 'MEMBER'),
('mem11@club.com', 'pass123', 'Mia', 'Allen', '0622222211', 'MEMBER'),
('mem12@club.com', 'pass123', 'Amelia', 'King', '0622222212', 'MEMBER'),
('mem13@club.com', 'pass123', 'Harper', 'Wright', '0622222213', 'MEMBER'),
('mem14@club.com', 'pass123', 'Evelyn', 'Scott', '0622222214', 'MEMBER'),
('mem15@club.com', 'pass123', 'Abigail', 'Torres', '0622222215', 'MEMBER'),
('mem16@club.com', 'pass123', 'Ella', 'Nguyen', '0622222216', 'MEMBER'),
('mem17@club.com', 'pass123', 'Scarlett', 'Hill', '0622222217', 'MEMBER'),
('mem18@club.com', 'pass123', 'Sofia', 'Flores', '0622222218', 'MEMBER'),
('mem19@club.com', 'pass123', 'Avery', 'Green', '0622222219', 'MEMBER'),
('mem20@club.com', 'pass123', 'Camila', 'Adams', '0622222220', 'MEMBER'),
('mem21@club.com', 'pass123', 'Daniel', 'Nelson', '0622222221', 'MEMBER'),
('mem22@club.com', 'pass123', 'Matthew', 'Baker', '0622222222', 'MEMBER'),
('mem23@club.com', 'pass123', 'Joseph', 'Gonzalez', '0622222223', 'MEMBER'),
('mem24@club.com', 'pass123', 'Samuel', 'Perez', '0622222224', 'MEMBER'),
('mem25@club.com', 'pass123', 'David', 'Roberts', '0622222225', 'MEMBER'),
('mem26@club.com', 'pass123', 'Carter', 'Turner', '0622222226', 'MEMBER'),
('mem27@club.com', 'pass123', 'Wyatt', 'Phillips', '0622222227', 'MEMBER'),
('mem28@club.com', 'pass123', 'Jayden', 'Campbell', '0622222228', 'MEMBER'),
('mem29@club.com', 'pass123', 'John', 'Parker', '0622222229', 'MEMBER'),
('mem30@club.com', 'pass123', 'Jack', 'Evans', '0622222230', 'MEMBER');

-- 6. Insert Role Tables
INSERT INTO Admin (userId) VALUES (1);

INSERT INTO Coach (userId, specialization, certificateLevel) VALUES
                                                                 (2, 'Tactical Analysis', 'UEFA A'), (3, 'Strength', 'Level 2'), (4, 'Goalkeeping', 'Level 3'),
                                                                 (5, 'Sprint', 'Olympian'), (6, 'Swimming', 'FINA B'), (7, 'Tennis', 'ITF 1'),
                                                                 (8, 'Boxing', 'WBC Pro'), (9, 'Rugby', 'World Rugby 2'), (10, 'Judo', 'Black Belt'),
                                                                 (11, 'General', 'Level 1');

INSERT INTO Member (userId, birthDate, loyaltyPoints, isActive)
SELECT id, '2000-01-01', FLOOR(RAND()*100), 1 FROM User WHERE role = 'MEMBER';

-- 7. Insert Teams
INSERT INTO Team (name, categoryId, coachId) VALUES
                                                 ('Red Dragons', 1, 2),
                                                 ('Blue Sharks', 1, 2),
                                                 ('Golden Eagles', 2, 3),
                                                 ('Silver Foxes', 3, 4),
                                                 ('Tennis Aces', 5, 7),
                                                 ('Aqua Squad', 6, 6),
                                                 ('Iron Fists', 7, 8),
                                                 ('Volley Stars', 8, 5),
                                                 ('Rugby Titans', 9, 9),
                                                 ('Judo Masters', 10, 10);

-- 8. Insert Players (UPDATED: No memberId)
-- Players are now standalone. We assign them to teams.
INSERT INTO Player (jerseyNumber, birthDate, position, medicalCertificate, height, weight, teamId) VALUES
-- Team 1 (Red Dragons)
(10, '2005-05-10', 'Striker', 'cert_valid.pdf', 1.75, 70.0, 1),
(1, '2005-06-12', 'Goalkeeper', 'cert_valid.pdf', 1.85, 80.0, 1),
(7, '2005-07-14', 'Winger', 'cert_valid.pdf', 1.70, 68.0, 1),
(5, '2005-08-20', 'Defender', 'cert_valid.pdf', 1.80, 75.0, 1),
(8, '2005-09-01', 'Midfielder', 'cert_valid.pdf', 1.72, 71.0, 1),
-- Team 2 (Blue Sharks)
(9, '2005-10-05', 'Striker', 'cert_valid.pdf', 1.76, 72.0, 2),
(12, '2005-11-10', 'Defender', 'cert_valid.pdf', 1.81, 78.0, 2),
(4, '2005-12-12', 'Midfielder', 'cert_valid.pdf', 1.74, 70.0, 2),
(11, '2006-01-01', 'Winger', 'cert_valid.pdf', 1.69, 65.0, 2),
(22, '2006-02-15', 'Goalkeeper', 'cert_valid.pdf', 1.88, 85.0, 2),
-- Tennis (Team 5)
(0, '2008-03-20', 'Single', 'cert_valid.pdf', 1.60, 50.0, 5),
(0, '2008-04-22', 'Single', 'cert_valid.pdf', 1.62, 52.0, 5),
(0, '2008-05-25', 'Double', 'cert_valid.pdf', 1.65, 55.0, 5),
-- Swimming (Team 6)
(0, '2000-06-30', 'Freestyle', 'cert_valid.pdf', 1.70, 60.0, 6),
(0, '2000-07-01', 'Butterfly', 'cert_valid.pdf', 1.75, 65.0, 6),
(0, '2000-08-05', 'Backstroke', 'cert_valid.pdf', 1.68, 62.0, 6),
-- Boxing (Team 7)
(0, '1995-09-10', 'Heavyweight', 'cert_valid.pdf', 1.90, 95.0, 7),
(0, '1996-10-12', 'Welterweight', 'cert_valid.pdf', 1.75, 75.0, 7),
(0, '1997-11-15', 'Lightweight', 'cert_valid.pdf', 1.70, 68.0, 7),
-- Volleyball (Team 8)
(6, '2002-01-20', 'Libero', 'cert_valid.pdf', 1.65, 58.0, 8),
(10, '2001-02-10', 'Setter', 'cert_valid.pdf', 1.72, 63.0, 8),
(12, '2003-03-05', 'Spiker', 'cert_valid.pdf', 1.80, 70.0, 8),
-- Rugby (Team 9)
(15, '1998-05-15', 'Fullback', 'cert_valid.pdf', 1.82, 88.0, 9),
(10, '1999-06-20', 'Fly-half', 'cert_valid.pdf', 1.78, 82.0, 9),
(9, '2000-07-25', 'Scrum-half', 'cert_valid.pdf', 1.70, 75.0, 9),
-- Judo (Team 10)
(0, '2012-08-10', 'u40kg', 'cert_valid.pdf', 1.40, 38.0, 10),
(0, '2011-09-12', 'u45kg', 'cert_valid.pdf', 1.45, 42.0, 10),
(0, '2012-10-15', 'u50kg', 'cert_valid.pdf', 1.48, 48.0, 10),
(0, '2010-11-20', 'u55kg', 'cert_valid.pdf', 1.52, 53.0, 10),
(0, '2011-12-01', 'u60kg', 'cert_valid.pdf', 1.55, 58.0, 10);

-- 9. Insert Subscriptions
INSERT INTO Subscription (startDate, endDate, status, memberId, planId) VALUES
                                                                            ('2025-01-01', '2025-02-01', 'ACTIVE', 12, 1),
                                                                            ('2025-01-01', '2025-02-01', 'ACTIVE', 13, 1),
                                                                            ('2025-01-05', '2025-02-05', 'ACTIVE', 14, 2),
                                                                            ('2024-12-01', '2025-01-01', 'EXPIRED', 15, 1),
                                                                            ('2025-01-10', '2026-01-10', 'ACTIVE', 16, 4),
                                                                            ('2025-01-15', '2026-01-15', 'ACTIVE', 17, 5),
                                                                            ('2025-01-20', '2025-02-20', 'ACTIVE', 18, 3),
                                                                            ('2024-11-20', '2024-12-20', 'EXPIRED', 19, 2),
                                                                            ('2025-01-01', '2025-02-01', 'ACTIVE', 20, 1),
                                                                            ('2025-01-02', '2025-02-02', 'ACTIVE', 21, 6);

-- 10. Insert Payments
INSERT INTO Payment (amount, paymentDate, transactionId, method, subscriptionId) VALUES
                                                                                     (29.99, '2025-01-01 10:00:00', 'TXN001', 'CARD', 1),
                                                                                     (29.99, '2025-01-01 10:15:00', 'TXN002', 'CASH', 2),
                                                                                     (49.99, '2025-01-05 11:00:00', 'TXN003', 'CARD', 3),
                                                                                     (29.99, '2024-12-01 09:00:00', 'TXN004', 'TRANSFER', 4),
                                                                                     (300.00, '2025-01-10 14:00:00', 'TXN005', 'CARD', 5),
                                                                                     (500.00, '2025-01-15 15:30:00', 'TXN006', 'CARD', 6),
                                                                                     (79.99, '2025-01-20 16:45:00', 'TXN007', 'CASH', 7),
                                                                                     (49.99, '2024-11-20 08:30:00', 'TXN008', 'CARD', 8),
                                                                                     (29.99, '2025-01-01 12:00:00', 'TXN009', 'TRANSFER', 9),
                                                                                     (800.00, '2025-01-02 13:00:00', 'TXN010', 'CARD', 10);

-- 11. Insert Matches
INSERT INTO `Match` (dateTime, opponentName, scoreHome, scoreAway, result, facilityId, teamId) VALUES
                                                                                                   ('2025-02-01 15:00:00', 'City FC', 0, 0, null, 1, 1),
                                                                                                   ('2025-02-02 16:00:00', 'United Sports', 0, 0, null, 1, 2),
                                                                                                   ('2025-01-15 14:00:00', 'North High', 2, 1, 'win', 2, 3),
                                                                                                   ('2025-01-20 10:00:00', 'West Academy', 1, 3, 'loss', 3, 4),
                                                                                                   ('2025-02-05 09:00:00', 'Ace Club', 0, 0, null, 6, 5),
                                                                                                   ('2025-01-25 11:00:00', 'Dolphins', 5, 5, 'draw', 5, 6),
                                                                                                   ('2025-02-10 18:00:00', 'Knockout Gym', 0, 0, null, 9, 7),
                                                                                                   ('2025-01-28 17:00:00', 'Spikers VC', 3, 0, 'win', 2, 8),
                                                                                                   ('2025-02-15 15:00:00', 'Maulers RFC', 0, 0, null, 10, 9),
                                                                                                   ('2025-01-30 10:00:00', 'Dojo Masters', 0, 0, null, 8, 10);

-- 12. Insert Tickets
INSERT INTO Ticket (qrCode, price, isUsed, matchId, memberId) VALUES
                                                                  ('QR001', 10.00, 0, 1, 12),
                                                                  ('QR002', 10.00, 0, 1, 13),
                                                                  ('QR003', 15.00, 1, 3, 14),
                                                                  ('QR004', 15.00, 1, 3, 15),
                                                                  ('QR005', 5.00, 0, 2, 16),
                                                                  ('QR006', 5.00, 0, 2, 17),
                                                                  ('QR007', 20.00, 0, 5, 18),
                                                                  ('QR008', 20.00, 0, 5, 19),
                                                                  ('QR009', 10.00, 1, 4, 20),
                                                                  ('QR010', 10.00, 0, 1, 21);

-- 13. Insert Attendance
-- Note: CheckInTime and Presence recorded against Player ID (which is now independent of Member)
INSERT INTO Attendance (isPresent, checkInTime, matchId, playerId) VALUES
                                                                       (1, '2025-01-15 13:30:00', 3, 1),
                                                                       (1, '2025-01-15 13:35:00', 3, 2),
                                                                       (1, '2025-01-15 13:40:00', 3, 3),
                                                                       (0, null, 3, 4),
                                                                       (1, '2025-01-20 09:30:00', 4, 5),
                                                                       (1, '2025-01-20 09:35:00', 4, 6),
                                                                       (1, '2025-01-25 10:30:00', 6, 14),
                                                                       (1, '2025-01-25 10:35:00', 6, 15),
                                                                       (1, '2025-01-28 16:30:00', 8, 20),
                                                                       (1, '2025-01-28 16:35:00', 8, 21);