use accountdb;
    CREATE TABLE Account
    (
        username VARCHAR(255) PRIMARY KEY ,
        password VARCHAR(255) NOT NULL
    );
INSERT INTO Account(username, password) VALUES
    ('Jan_an', 'pass123456');

CREATE TABLE UserDetail
(
    firstName VARCHAR(255) NOT NULL ,
    lastName VARCHAR(255) NOT NULL ,
    email VARCHAR(255) NOT NULL ,
    username VARCHAR(255) NOT NULL ,
    PRIMARY KEY (username),
    FOREIGN KEY (username) REFERENCES Account (username)
);


INSERT INTO UserDetail(firstName, lastName, email, username) VALUES
             ('Janan','Javdan','f_javdan2000@yahoo.com','Jan_an');
