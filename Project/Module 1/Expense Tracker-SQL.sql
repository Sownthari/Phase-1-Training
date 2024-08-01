
--create users table
CREATE TABLE Users (
userID INT IDENTITY(1,1) PRIMARY KEY,
userName VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL,
[password] VARCHAR(50) NOT NULL,
created_at DATE NOT NULL,
updated_at DATE NOT NULL
)

--create categories table
CREATE TABLE Categories (
categoryID INT IDENTITY(1,1) PRIMARY KEY,
userID INT NOT NULL,
categoryName VARCHAR(100) NOT NULL,
[description] VARCHAR(max),
FOREIGN KEY (userID) REFERENCES Users(userID) 
)

--create transcation table
CREATE TABLE Transcation (
transcationID INT IDENTITY(1,1) PRIMARY KEY,
userID INT NOT NULL,
categoryID INT NOT NULL,
amount DECIMAL(10, 2) NOT NULL,
transcationDate DATE NOT NULL,
transcationType VARCHAR(10) NOT NULL,
[description] VARCHAR(max),
FOREIGN KEY (userID) REFERENCES Users(userID),
FOREIGN KEY (categoryID) REFERENCES Categories(categoryID)
)

--create budget table
CREATE TABLE Budget (
budgetID INT IDENTITY(1,1) PRIMARY KEY,
userID INT NOT NULL,
categoryID INT NOT NULL,
amount DECIMAL(10, 2) NOT NULL,
recurrance VARCHAR(50) NOT NULL,
expense INT NOT NULL,
isNotified BIT NOT NULL,
FOREIGN KEY (userID) REFERENCES Users(userID),
FOREIGN KEY (categoryID) REFERENCES Categories(categoryID)
)
--Inserting data into the user table
INSERT INTO Users (userName, email, [password], created_at, updated_at)
VALUES 
    ('Sownthari', 'sownthari.p@payoda.com', 'password123', GETDATE(), GETDATE()),
    ('Siddarth', 'siddarth.s@payoda.com', 's@123', GETDATE(), GETDATE()),
    ('Hemasri', 'hemasri.m@payoda.com', 'pass23!', GETDATE(), GETDATE()),
	('Prathik', 'prathik.b@payoda.com', 'password$123', GETDATE(), GETDATE()),
    ('Dharshini', 'dharshini.j@payoda.com', 'd@8967', GETDATE(), GETDATE());

--Inserting data into the categories table
INSERT INTO Categories (userID, categoryName, [description])
VALUES 
    (1, 'Groceries', 'Expenses for food and household items'),
    (1, 'Rent', 'Monthly apartment rent payment'),
    (2, 'Utilities', 'Electricity, water, and other utilities'),
    (2, 'Entertainment', 'Movies, concerts, and other leisure activities'),
    (3, 'Transportation', 'Expenses for public transit, fuel, etc.'),
    (3, 'Health', 'Medical bills and health insurance payments');

--Inserting data into the transcation table
INSERT INTO Transcation (userID, categoryID, amount, transcationDate, transcationType, [description])
VALUES 
    (1, 1, 150.00, '2024-07-20', 'Expense', 'Grocery shopping at local store'),
    (2, 2, 800.00, '2024-07-21', 'Expense', 'Monthly rent payment'),
    (3, 3, 120.00, '2024-07-22', 'Expense', 'Utility bills for electricity and water'),
    (4, 4, 250.00, '2024-07-23', 'Expense', 'New kitchen appliances for home improvement'),
    (5, 5, 500.00, '2024-07-24', 'Income', 'Freelance project payment'),
    (1, 6, 75.00, '2024-07-25', 'Expense', 'Dining out with friends'),
    (2, 7, 200.00, '2024-07-26', 'Expense', 'Clothing shopping for summer season'),
    (3, 8, 300.00, '2024-07-27', 'Income', 'Part-time job salary'),
    (4, 9, 45.00, '2024-07-28', 'Expense', 'Gifts for a friends birthday'),
    (5, 10, 100.00, '2024-07-29', 'Expense', 'Pet supplies and vet visit');

-- Inserting data into the Budget table
INSERT INTO Budget (userID, categoryID, amount, recurrance, expense, isNotified)
VALUES 
    (1, 1, 500.00, 'Monthly', 450.00, 0),
    (2, 2, 1000.00, 'Monthly', 800.00, 1),
    (3, 3, 200.00, 'Monthly', 180.00, 0),
    (4, 4, 300.00, 'Monthly', 250.00, 1),
    (5, 5, 1500.00, 'Yearly', 1200.00, 1),
    (1, 6, 200.00, 'Monthly', 180.00, 0),
    (2, 7, 400.00, 'Monthly', 350.00, 1),
    (3, 8, 600.00, 'Yearly', 550.00, 1),
    (4, 9, 100.00, 'Monthly', 90.00, 0),
    (5, 10, 250.00, 'Monthly', 230.00, 0);

--fetch all users
SELECT * FROM Users;

--Transactions after July 26, 2024 and userID is 2
SELECT * FROM Transcation WHERE transcationDate > '2024-07-26' and userID = 2;

--Retrieve user names and their transaction IDs
SELECT 
    Users.userName,
    STRING_AGG(CAST(Transcation.transcationID AS VARCHAR), ', ') AS transcationIDs
FROM Users
INNER JOIN Transcation ON Users.userID = Transcation.userID
GROUP BY Users.userName;

--Total number of transactions for each user
SELECT userID, COUNT(transcationID) AS total_transactions
FROM Transcation
GROUP BY userID;

--Total transactions for each category
SELECT Categories.categoryName, SUM(Transcation.amount) AS total_amount
FROM Categories
INNER JOIN Transcation ON Categories.categoryID = Transcation.categoryID
GROUP BY Categories.categoryName;



