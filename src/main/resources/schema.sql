-- 创建 Airport 表
CREATE TABLE IF NOT EXISTS airport (
    airport_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,	
    city VARCHAR(100) NOT NULL
);

-- 创建 User 表
CREATE TABLE IF NOT EXISTS user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL
);

-- 创建 Flight 表
CREATE TABLE IF NOT EXISTS flight (
     flight_id BIGINT AUTO_INCREMENT PRIMARY KEY,
     flight_number VARCHAR(10) NOT NULL,
     departure_airport_id BIGINT NOT NULL,
     destination_airport_id BIGINT NOT NULL,
     FOREIGN KEY (departure_airport_id) REFERENCES airport(airport_id),
     FOREIGN KEY (destination_airport_id) REFERENCES airport(airport_id),
     departure_date DATE NOT NULL,
     departure_time TIME NOT NULL,
     destination_date DATE NOT NULL,
     destination_time TIME NOT NULL,
     taxes DECIMAL(10, 2) NOT NULL,
     price DECIMAL(10, 2) NOT NULL
);

-- 创建 Booking 表
CREATE TABLE IF NOT EXISTS booking (
    booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    flight_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (flight_id) REFERENCES flight(flight_id),
    reference VARCHAR(20) NOT NULL,
    status ENUM('UPCOMING', 'PAST') NOT NULL DEFAULT 'UPCOMING',
    flight_type ENUM('DEPARTURE', 'RETURN') NOT NULL DEFAULT 'DEPARTURE',
    booking_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10, 2) NOT NULL
);

-- 创建 Passenger 表
CREATE TABLE IF NOT EXISTS passenger (
    passenger_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,	
    email VARCHAR(100) NOT NULL UNIQUE
);


