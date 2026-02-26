-- Insert 5 Facilities
INSERT INTO Facility (name, location, capacity, scheduleAvailability) VALUES
                                                                          ('Central Stadium', 'Downtown', 5000, '2025-01-01'),
                                                                          ('City Gym', 'North District', 200, '2025-01-01'),
                                                                          ('Olympic Pool', 'West Wing', 500, '2025-01-02'),
                                                                          ('Tennis Complex', 'South Park', 100, '2025-01-03'),
                                                                          ('Community Field', 'East Side', 1000, '2025-01-04');

-- Insert 5 Sports
INSERT INTO Sport (name) VALUES
                             ('Football'),
                             ('Basketball'),
                             ('Tennis'),
                             ('Swimming'),
                             ('Volleyball');

-- Insert 5 Categories (Linked to Sports)
INSERT INTO Category (name, gender, minAge, maxAge, sportId) VALUES
                                                                 ('U19 Boys', 'MALE', 16, 19, 1),
                                                                 ('Senior Women', 'FEMALE', 20, 35, 2),
                                                                 ('Junior Mixed', 'MALE', 10, 15, 3),
                                                                 ('Pro Swimmers', 'FEMALE', 18, 30, 4),
                                                                 ('Amateur Men', 'MALE', 18, 40, 5);

-- Insert 5 Subscription Plans
INSERT INTO SubscriptionPlan (name, price, durationMonths, description) VALUES
                                                                            ('Basic Monthly', 30.00, 1, 'Access to Gym only'),
                                                                            ('Premium Annual', 300.00, 12, 'All facilities access'),
                                                                            ('Student Saver', 20.00, 1, 'Discounted monthly rate'),
                                                                            ('Quarterly Pass', 80.00, 3, '3 months access'),
                                                                            ('Day Pass', 10.00, 0, 'Single day entry');
-- Insert 45 Users Total (IDs 1-45)
-- We need enough Users to link to the specific roles below
INSERT INTO User (email, password, firstname, lastname, role) VALUES
-- 5 Admins (IDs 1-5)
('admin1@club.com', 'pass123', 'John', 'Doe', 'ADMIN'),
('admin2@club.com', 'pass123', 'Jane', 'Smith', 'ADMIN'),
('admin3@club.com', 'pass123', 'Robert', 'Brown', 'ADMIN'),
('admin4@club.com', 'pass123', 'Emily', 'Davis', 'ADMIN'),
('admin5@club.com', 'pass123', 'Michael', 'Wilson', 'ADMIN'),

-- 5 Coaches (IDs 6-10)
('coach1@club.com', 'pass123', 'Chris', 'Evans', 'COACH'),
('coach2@club.com', 'pass123', 'Sarah', 'Connor', 'COACH'),
('coach3@club.com', 'pass123', 'Tom', 'Hardy', 'COACH'),
('coach4@club.com', 'pass123', 'Laura', 'Croft', 'COACH'),
('coach5@club.com', 'pass123', 'Bruce', 'Wayne', 'COACH'),

-- 5 Members (IDs 11-15)
('member1@club.com', 'pass123', 'Alice', 'Wonder', 'MEMBER'),
('member2@club.com', 'pass123', 'Bob', 'Builder', 'MEMBER'),
('member3@club.com', 'pass123', 'Charlie', 'Chaplin', 'MEMBER'),
('member4@club.com', 'pass123', 'David', 'Beckham', 'MEMBER'),
('member5@club.com', 'pass123', 'Eva', 'Green', 'MEMBER'),

-- 30 Players (IDs 16-45)
('p1@club.com', 'pass123', 'Player', 'One', 'PLAYER'),
('p2@club.com', 'pass123', 'Player', 'Two', 'PLAYER'),
('p3@club.com', 'pass123', 'Player', 'Three', 'PLAYER'),
('p4@club.com', 'pass123', 'Player', 'Four', 'PLAYER'),
('p5@club.com', 'pass123', 'Player', 'Five', 'PLAYER'),
('p6@club.com', 'pass123', 'Player', 'Six', 'PLAYER'),
('p7@club.com', 'pass123', 'Player', 'Seven', 'PLAYER'),
('p8@club.com', 'pass123', 'Player', 'Eight', 'PLAYER'),
('p9@club.com', 'pass123', 'Player', 'Nine', 'PLAYER'),
('p10@club.com', 'pass123', 'Player', 'Ten', 'PLAYER'),
('p11@club.com', 'pass123', 'Player', 'Eleven', 'PLAYER'),
('p12@club.com', 'pass123', 'Player', 'Twelve', 'PLAYER'),
('p13@club.com', 'pass123', 'Player', 'Thirteen', 'PLAYER'),
('p14@club.com', 'pass123', 'Player', 'Fourteen', 'PLAYER'),
('p15@club.com', 'pass123', 'Player', 'Fifteen', 'PLAYER'),
('p16@club.com', 'pass123', 'Player', 'Sixteen', 'PLAYER'),
('p17@club.com', 'pass123', 'Player', 'Seventeen', 'PLAYER'),
('p18@club.com', 'pass123', 'Player', 'Eighteen', 'PLAYER'),
('p19@club.com', 'pass123', 'Player', 'Nineteen', 'PLAYER'),
('p20@club.com', 'pass123', 'Player', 'Twenty', 'PLAYER'),
('p21@club.com', 'pass123', 'Player', 'TwentyOne', 'PLAYER'),
('p22@club.com', 'pass123', 'Player', 'TwentyTwo', 'PLAYER'),
('p23@club.com', 'pass123', 'Player', 'TwentyThree', 'PLAYER'),
('p24@club.com', 'pass123', 'Player', 'TwentyFour', 'PLAYER'),
('p25@club.com', 'pass123', 'Player', 'TwentyFive', 'PLAYER'),
('p26@club.com', 'pass123', 'Player', 'TwentySix', 'PLAYER'),
('p27@club.com', 'pass123', 'Player', 'TwentySeven', 'PLAYER'),
('p28@club.com', 'pass123', 'Player', 'TwentyEight', 'PLAYER'),
('p29@club.com', 'pass123', 'Player', 'TwentyNine', 'PLAYER'),
('p30@club.com', 'pass123', 'Player', 'Thirty', 'PLAYER');

-- Insert 5 Admins
INSERT INTO Admin (userId) VALUES (1), (2), (3), (4), (5);

-- Insert 5 Coaches
INSERT INTO Coach (userId, specialization, certificateLevel) VALUES
                                                                 (6, 'Tactics', 'A'),
                                                                 (7, 'Strength', 'B'),
                                                                 (8, 'Cardio', 'A'),
                                                                 (9, 'Technical', 'C'),
                                                                 (10, 'Rehab', 'B');

-- Insert 5 Members
INSERT INTO Member (userId, birthDate, loyaltyPoints, isActive) VALUES
                                                                    (11, '1990-05-20', 100, 1),
                                                                    (12, '1985-08-15', 50, 1),
                                                                    (13, '1992-01-10', 0, 1),
                                                                    (14, '2000-12-05', 200, 1),
                                                                    (15, '1995-03-25', 10, 0);

-- Insert 5 Teams
INSERT INTO Team (name, categoryId, coachId) VALUES
                                                 ('Red Dragons', 1, 6),
                                                 ('Blue Sharks', 1, 6),
                                                 ('Green Eagles', 2, 7),
                                                 ('Yellow Tigers', 3, 8),
                                                 ('Black Panthers', 5, 9);

-- Insert 30 Players (userId 16 to 45)
-- Assuming userId is the PK now.
INSERT INTO Player (userId, jerseyNumber, birthDate, position, medicalCertificate, height, weight, teamId) VALUES
                                                                                                               (16, 1, '2005-01-01', 'Goalkeeper', 'cert.pdf', 185, 80, 1),
                                                                                                               (17, 2, '2005-02-01', 'Defender', 'cert.pdf', 180, 75, 1),
                                                                                                               (18, 3, '2005-03-01', 'Defender', 'cert.pdf', 182, 78, 1),
                                                                                                               (19, 4, '2005-04-01', 'Midfielder', 'cert.pdf', 175, 70, 1),
                                                                                                               (20, 5, '2005-05-01', 'Striker', 'cert.pdf', 178, 72, 1),
                                                                                                               (21, 6, '2005-06-01', 'Goalkeeper', 'cert.pdf', 188, 85, 2),
                                                                                                               (22, 7, '2005-07-01', 'Defender', 'cert.pdf', 181, 77, 2),
                                                                                                               (23, 8, '2005-08-01', 'Defender', 'cert.pdf', 183, 79, 2),
                                                                                                               (24, 9, '2005-09-01', 'Midfielder', 'cert.pdf', 176, 71, 2),
                                                                                                               (25, 10, '2005-10-01', 'Striker', 'cert.pdf', 179, 73, 2),
                                                                                                               (26, 11, '1990-01-01', 'Guard', 'cert.pdf', 165, 60, 3),
                                                                                                               (27, 12, '1991-02-01', 'Forward', 'cert.pdf', 170, 65, 3),
                                                                                                               (28, 13, '1992-03-01', 'Center', 'cert.pdf', 175, 68, 3),
                                                                                                               (29, 14, '1993-04-01', 'Guard', 'cert.pdf', 168, 62, 3),
                                                                                                               (30, 15, '1994-05-01', 'Forward', 'cert.pdf', 172, 66, 3),
                                                                                                               (31, 16, '2010-01-01', 'Single', 'cert.pdf', 150, 45, 4),
                                                                                                               (32, 17, '2011-02-01', 'Single', 'cert.pdf', 152, 46, 4),
                                                                                                               (33, 18, '2010-03-01', 'Double', 'cert.pdf', 155, 48, 4),
                                                                                                               (34, 19, '2011-04-01', 'Double', 'cert.pdf', 153, 47, 4),
                                                                                                               (35, 20, '2010-05-01', 'Single', 'cert.pdf', 158, 50, 4),
                                                                                                               (36, 21, '1995-01-01', 'Spiker', 'cert.pdf', 190, 88, 5),
                                                                                                               (37, 22, '1996-02-01', 'Setter', 'cert.pdf', 185, 82, 5),
                                                                                                               (38, 23, '1997-03-01', 'Libero', 'cert.pdf', 175, 70, 5),
                                                                                                               (39, 24, '1998-04-01', 'Blocker', 'cert.pdf', 195, 92, 5),
                                                                                                               (40, 25, '1999-05-01', 'Spiker', 'cert.pdf', 188, 85, 5),
                                                                                                               (41, 26, '1995-06-01', 'Sub', 'cert.pdf', 180, 80, 5),
                                                                                                               (42, 27, '1996-07-01', 'Sub', 'cert.pdf', 182, 82, 5),
                                                                                                               (43, 28, '1997-08-01', 'Sub', 'cert.pdf', 184, 84, 5),
                                                                                                               (44, 29, '1998-09-01', 'Sub', 'cert.pdf', 186, 86, 5),
                                                                                                               (45, 30, '1999-10-01', 'Sub', 'cert.pdf', 188, 88, 5);

-- Insert 5 Matches
INSERT INTO `Match` (dateTime, opponentName, scoreHome, scoreAway, result, facilityId, teamId) VALUES
                                                                                                   ('2025-02-01 10:00:00', 'City Rivals', 2, 1, 'win', 1, 1),
                                                                                                   ('2025-02-02 14:00:00', 'North Stars', 0, 0, 'draw', 1, 2),
                                                                                                   ('2025-02-03 16:00:00', 'West Side', 1, 3, 'loss', 3, 3),
                                                                                                   ('2025-02-04 09:00:00', 'Junior academy', 0, 0, NULL, 4, 4),
                                                                                                   ('2025-02-05 18:00:00', 'Pro Club', 0, 0, NULL, 5, 5);

-- Insert 5 Subscriptions
INSERT INTO Subscription (startDate, endDate, status, memberId, planId) VALUES
                                                                            ('2025-01-01', '2025-02-01', 'ACTIVE', 11, 1),
                                                                            ('2025-01-01', '2026-01-01', 'ACTIVE', 12, 2),
                                                                            ('2025-01-15', '2025-02-15', 'ACTIVE', 13, 3),
                                                                            ('2025-01-01', '2025-04-01', 'ACTIVE', 14, 4),
                                                                            ('2024-01-01', '2024-02-01', 'EXPIRED', 15, 1);

-- Insert 5 Payments
INSERT INTO Payment (amount, paymentDate, transactionId, method, subscriptionId) VALUES
                                                                                     (30.00, '2025-01-01 10:00:00', 'TXN001', 'CARD', 1),
                                                                                     (300.00, '2025-01-01 11:00:00', 'TXN002', 'TRANSFER', 2),
                                                                                     (20.00, '2025-01-15 09:00:00', 'TXN003', 'CASH', 3),
                                                                                     (80.00, '2025-01-01 12:00:00', 'TXN004', 'CARD', 4),
                                                                                     (30.00, '2024-01-01 10:00:00', 'TXN005', 'CASH', 5);

-- Insert 5 Tickets
INSERT INTO Ticket (qrCode, price, isUsed, matchId, memberId) VALUES
                                                                  ('QR12345', 10.00, 1, 1, 11),
                                                                  ('QR67890', 10.00, 0, 1, 12),
                                                                  ('QR11223', 15.00, 0, 2, 13),
                                                                  ('QR44556', 5.00, 1, 3, 14),
                                                                  ('QR77889', 20.00, 0, 4, 11);

-- Insert 5 Attendance Records (Using playerId linked to userId 16-20)
-- Assuming Player table PK is now userId, so we reference IDs 16, 17, 18, etc.
INSERT INTO Attendance (isPresent, checkInTime, matchId, playerId) VALUES
                                                                       (1, '2025-02-01 09:30:00', 1, 16),
                                                                       (1, '2025-02-01 09:35:00', 1, 17),
                                                                       (0, NULL, 1, 18),
                                                                       (1, '2025-02-01 09:40:00', 1, 19),
                                                                       (1, '2025-02-01 09:45:00', 1, 20);