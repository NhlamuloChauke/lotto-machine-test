CREATE DATABASE TurfSportDb;

USE TurfSportDb;

-- Create the Users table
CREATE TABLE Users (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

-- Create the Tickets table
CREATE TABLE Tickets (
    id INT PRIMARY KEY,
    user_id INT,
    placed_time DATETIME,
    amount DECIMAL(10, 2),
    numbers VARCHAR(255),
    loyalty_program_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (loyalty_program_id) REFERENCES LoyaltyPrograms(id)
);

-- Create the LoyaltyPrograms table (optional)
CREATE TABLE LoyaltyPrograms (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

-- Select a specific ticket and all information on it including who placed it
SELECT Tickets.id, Users.name AS user_name, Tickets.placed_time, Tickets.amount, Tickets.numbers
FROM Tickets
JOIN Users ON Tickets.user_id = Users.id
WHERE Tickets.id = <ticket_id>;

-- Select the sum of the money spent by a user over a certain time frame
SELECT Users.name AS user_name, SUM(Tickets.amount) AS total_spent
FROM Tickets
JOIN Users ON Tickets.user_id = Users.id
WHERE Tickets.placed_time >= <start_date> AND Tickets.placed_time <= <end_date>
GROUP BY Users.name;

-- Select all the loyalty programs assigned to a user (optional)
SELECT Users.name AS user_name, LoyaltyPrograms.name AS loyalty_program_name
FROM Users
LEFT JOIN Tickets ON Users.id = Tickets.user_id
LEFT JOIN LoyaltyPrograms ON Tickets.loyalty_program_id = LoyaltyPrograms.id
WHERE Users.id = <user_id>;

-- Select the sum of the money spent by a user over a certain time frame for each loyalty program (optional)
SELECT Users.name AS user_name, LoyaltyPrograms.name AS loyalty_program_name, SUM(Tickets.amount) AS total_spent
FROM Tickets
JOIN Users ON Tickets.user_id = Users.id
JOIN LoyaltyPrograms ON Tickets.loyalty_program_id = LoyaltyPrograms.id
WHERE Tickets.placed_time >= <start_date> AND Tickets.placed_time <= <end_date>
GROUP BY Users.name, LoyaltyPrograms.name;

-- Create indices for optimizing query performance
CREATE INDEX idx_tickets_user_id ON Tickets(user_id);
CREATE INDEX idx_tickets_placed_time ON Tickets(placed_time);
CREATE INDEX idx_tickets_loyalty_program_id ON Tickets(loyalty_program_id);
