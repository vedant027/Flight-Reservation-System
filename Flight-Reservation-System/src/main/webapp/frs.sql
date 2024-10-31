CREATE TABLE flight (
    airline_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(50) NOT NULL,
    airline_name VARCHAR(100) NOT NULL,
    departure_city VARCHAR(100) NOT NULL,
    arrival_city VARCHAR(100) NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    price INT NOT NULL,
    departure_date DATE NOT NULL,
    available_seats INT NOT NULL
);

INSERT INTO flight (flight_number, airline_name, departure_city, arrival_city, departure_time, arrival_time, price, departure_date, available_seats) VALUES
('AI201', 'Air India', 'Delhi', 'Mumbai', '09:30:00', '11:00:00', 6500, '2025-01-15', 25),
('AI202', 'IndiGo', 'Delhi', 'Mumbai', '10:00:00', '11:30:00', 6200, '2025-01-15', 15),
('AI203', 'SpiceJet', 'Delhi', 'Mumbai', '12:00:00', '13:30:00', 6000, '2025-01-15', 10),
('AI204', 'Air India', 'Delhi', 'Chennai', '13:00:00', '15:00:00', 5800, '2025-01-20', 30),
('AI205', 'IndiGo', 'Mumbai', 'Kolkata', '16:00:00', '18:00:00', 5700, '2025-01-22', 20),
('AI206', 'SpiceJet', 'Hyderabad', 'Chennai', '19:00:00', '21:00:00', 5900, '2025-01-25', 5),
('AI207', 'GoAir', 'Bangalore', 'Goa', '10:00:00', '12:00:00', 7200, '2025-02-01', 35),
('AI208', 'Air India', 'Bangalore', 'Hyderabad', '11:30:00', '13:00:00', 4000, '2025-02-05', 18),
('AI209', 'IndiGo', 'Pune', 'Chennai', '14:00:00', '15:30:00', 6000, '2025-02-10', 22),
('AI210', 'SpiceJet', 'Chennai', 'Kolkata', '15:00:00', '17:00:00', 5500, '2025-02-15', 14),
('AI211', 'GoAir', 'Ahmedabad', 'Delhi', '09:30:00', '11:00:00', 4800, '2025-01-25', 40),
('AI212', 'Air India', 'Delhi', 'Jaipur', '11:00:00', '12:30:00', 4900, '2025-01-28', 12),
('AI213', 'IndiGo', 'Ahmedabad', 'Hyderabad', '13:30:00', '15:00:00', 4600, '2025-02-01', 8),
('AI214', 'GoAir', 'Kolkata', 'Bangalore', '09:00:00', '11:00:00', 6400, '2025-02-18', 19),
('AI215', 'SpiceJet', 'Goa', 'Mumbai', '13:00:00', '15:00:00', 6200, '2025-02-18', 17),
('AI216', 'Air India', 'Chennai', 'Pune', '16:30:00', '18:00:00', 7000, '2025-03-01', 30),
('AI217', 'IndiGo', 'Pune', 'Jaipur', '12:15:00', '13:45:00', 3200, '2025-03-05', 25),
('AI218', 'SpiceJet', 'Jaipur', 'Delhi', '14:30:00', '16:00:00', 3100, '2025-03-05', 15),
('AI219', 'GoAir', 'Lucknow', 'Bangalore', '11:15:00', '12:15:00', 4000, '2025-03-10', 50),
('AI220', 'Air India', 'Lucknow', 'Delhi', '13:30:00', '14:30:00', 4200, '2025-03-10', 10),
('AI221', 'IndiGo', 'Hyderabad', 'Mumbai', '09:00:00', '10:30:00', 4800, '2025-03-15', 30),
('AI222', 'SpiceJet', 'Delhi', 'Goa', '12:00:00', '14:30:00', 7500, '2025-03-20', 28),
('AI223', 'GoAir', 'Delhi', 'Mumbai', '15:00:00', '16:30:00', 6700, '2025-03-25', 5),
('AI224', 'Air India', 'Jaipur', 'Lucknow', '17:30:00', '19:00:00', 3500, '2025-03-30', 20),
('AI225', 'IndiGo', 'Chennai', 'Bangalore', '08:00:00', '09:00:00', 3000, '2025-02-12', 33),
('AI226', 'SpiceJet', 'Bangalore', 'Delhi', '10:30:00', '12:00:00', 7000, '2025-02-15', 15),
('AI227', 'GoAir', 'Delhi', 'Hyderabad', '13:00:00', '14:30:00', 4500, '2025-03-02', 18),
('AI228', 'Air India', 'Mumbai', 'Pune', '14:45:00', '15:15:00', 2000, '2025-01-22', 50),
('AI229', 'IndiGo', 'Goa', 'Delhi', '11:00:00', '12:30:00', 5500, '2025-01-30', 12),
('AI230', 'SpiceJet', 'Kolkata', 'Goa', '16:00:00', '18:00:00', 6000, '2025-02-25', 8);


CREATE TABLE users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL
);


Create TABLE passengers(
    passenger_id int PRIMARY KEY AUTO_INCREMENT,
    passenger_name varchar(40) DEFAULT NULL,
    email varchar(25) DEFAULT NULL,
    phone_no varchar(12) DEFAULT null
);

-- reservation_id int DEFAULT null,
    -- constraint fk_reservations foreign key (reservation_id) references reservations(reservation_id)

create table card(
    card_id int primary key auto_increment,
    card_number varchar(16) not null,
    cardholder_name varchar(50) not null,
    exp_date date not null,
    cvv varchar(3) not null
);

create table reservations (
    reservation_id int primary key auto_increment,
    total_fare double not null,
    user_id int default null,
    flight_id int DEFAULT null,
    passenger_id int default null,
    card_id int default null,
    constraint fk_flightUser foreign key (user_id) references flightUsers(userid),
    constraint fk_flight foreign key (flight_id) references flight(airline_id),
    constraint fk_passengers foreign key (passenger_id) references passengers(passenger_id),
    constraint fk_card foreign key (card_id) references card(card_id)
);


