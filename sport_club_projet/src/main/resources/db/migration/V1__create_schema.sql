-- User Table
CREATE TABLE User (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      firstname VARCHAR(100) NOT NULL,
                      lastname VARCHAR(100) NOT NULL,
                      phone VARCHAR(20),
                      role VARCHAR(50) NOT NULL,
                      CONSTRAINT chk_role CHECK (role IN ('ADMIN', 'COACH', 'MEMBER', 'PLAYER'))
);

-- Admin Table
CREATE TABLE Admin (
                       userId BIGINT PRIMARY KEY,
                       FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Coach Table
CREATE TABLE Coach (
                       userId BIGINT PRIMARY KEY,
                       specialization VARCHAR(100),
                       certificateLevel VARCHAR(100),
                       FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Member Table
CREATE TABLE Member (
                        userId BIGINT PRIMARY KEY,
                        birthDate DATE NOT NULL,
                        loyaltyPoints INT DEFAULT 0,
                        isActive BOOLEAN DEFAULT TRUE,
                        FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Sport Table
CREATE TABLE Sport (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL UNIQUE
);

-- Category Table
CREATE TABLE Category (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          gender VARCHAR(50) NOT NULL,
                          minAge INT,
                          maxAge INT,
                          sportId BIGINT,
                          CONSTRAINT chk_gender CHECK (gender IN ('MALE', 'FEMALE')),
                          FOREIGN KEY (sportId) REFERENCES Sport(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Team Table
CREATE TABLE Team (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(100) NOT NULL,
                      categoryId BIGINT,
                      coachId BIGINT,
                      FOREIGN KEY (categoryId) REFERENCES Category(id) ON DELETE SET NULL ON UPDATE CASCADE,
                      FOREIGN KEY (coachId) REFERENCES Coach(userId) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Player Table (Fixed syntax error here)
CREATE TABLE Player (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        jerseyNumber INT,
                        birthDate DATE NOT NULL,
                        position VARCHAR(50),
                        medicalCertificate VARCHAR(255),
                        height DOUBLE,
                        weight DOUBLE,
                        teamId BIGINT,
                        memberId BIGINT,
                        FOREIGN KEY (teamId) REFERENCES Team(id) ON DELETE SET NULL ON UPDATE CASCADE,
                        FOREIGN KEY (memberId) REFERENCES Member(userId) ON DELETE SET NULL ON UPDATE CASCADE
);

-- SubscriptionPlan Table
CREATE TABLE SubscriptionPlan (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  name VARCHAR(100) NOT NULL,
                                  price DECIMAL(10, 2) NOT NULL,
                                  durationMonths INT NOT NULL,
                                  description TEXT
);

-- Subscription Table
CREATE TABLE Subscription (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              startDate DATE NOT NULL,
                              endDate DATE NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              memberId BIGINT,
                              planId BIGINT,
                              FOREIGN KEY (memberId) REFERENCES Member(userId) ON DELETE CASCADE ON UPDATE CASCADE,
                              FOREIGN KEY (planId) REFERENCES SubscriptionPlan(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Payment Table
CREATE TABLE Payment (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         amount DECIMAL(10, 2) NOT NULL,
                         paymentDate DATETIME NOT NULL,
                         transactionId VARCHAR(255) UNIQUE NOT NULL,
                         method VARCHAR(50) NOT NULL,
                         subscriptionId BIGINT,
                         CONSTRAINT chk_payment_method CHECK (method IN ('CASH', 'CARD', 'TRANSFER')),
                         FOREIGN KEY (subscriptionId) REFERENCES Subscription(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Facility Table
CREATE TABLE Facility (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          location VARCHAR(255) NOT NULL,
                          capacity INT NOT NULL,
                          scheduleAvailability DATE
);

-- Match Table (using backticks to escape reserved keyword)
CREATE TABLE `Match` (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         dateTime DATETIME NOT NULL,
                         opponentName VARCHAR(100) NOT NULL,
                         scoreHome INT,
                         scoreAway INT,
                         result VARCHAR(50),
                         newDate DATETIME,
                         facilityId BIGINT,
                         teamId BIGINT,
                         CONSTRAINT chk_result CHECK (result IN ('win', 'draw', 'loss')),
                         FOREIGN KEY (facilityId) REFERENCES Facility(id) ON DELETE SET NULL ON UPDATE CASCADE,
                         FOREIGN KEY (teamId) REFERENCES Team(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Attendance Table
CREATE TABLE Attendance (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            isPresent BOOLEAN DEFAULT FALSE,
                            checkInTime DATETIME,
                            matchId BIGINT,
                            playerId BIGINT,
                            FOREIGN KEY (matchId) REFERENCES `Match`(id) ON DELETE CASCADE ON UPDATE CASCADE,
                            FOREIGN KEY (playerId) REFERENCES Player(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Ticket Table
CREATE TABLE Ticket (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        qrCode VARCHAR(255) UNIQUE NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        isUsed BOOLEAN DEFAULT FALSE,
                        matchId BIGINT,
                        memberId BIGINT,
                        FOREIGN KEY (matchId) REFERENCES `Match`(id) ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (memberId) REFERENCES Member(userId) ON DELETE SET NULL ON UPDATE CASCADE
);