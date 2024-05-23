create database knk2024;
use knk2024;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    passwordHash VARCHAR(255) NOT NULL,
    isadmin boolean

);
alter table user add column dateJoined TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

create table inventory(
carid varchar(100),
carname varchar(100),
cartype varchar(100),
carstock int,
carprice varchar(100),
carstatus varchar(100),
carimage varchar(100), PRIMARY KEY(carid));
ALTER TABLE inventory
ADD dateAdded TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE CarPurchases (
    id SERIAL PRIMARY KEY,
    car_id VARCHAR(50),
    car_name VARCHAR(100),
    car_price DECIMAL(10, 2),
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    buyer_id INT,
    buyer_name VARCHAR(100),
    card_number VARCHAR(16),
    cvv VARCHAR(3),
    expiration_date VARCHAR(5),
    foreign key (car_id) references inventory(carid),
    FOREIGN KEY (buyer_id) REFERENCES User(id)
);

CREATE TABLE carimages (
    carid VARCHAR(255) NOT NULL,
    imagepath VARCHAR(255),
    FOREIGN KEY (carid) REFERENCES inventory(carid)
);

CREATE TABLE  messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE restock_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(255),
    car_name VARCHAR(255),
    date DATE
);