-- 插入数据到 Airport 表
INSERT INTO airport (code, name, city)
VALUES
    ('0001', 'dongfang', 'shanghai'),
    ('0002', 'chunqiu', 'nanjing'),
    ('0003', 'rihang', 'beihaidao'),
    ('0004', 'guohang', 'beijin')
    ON DUPLICATE KEY UPDATE name = VALUES(name), city = VALUES(city);

-- 插入数据到 User 表
INSERT INTO user (email, password, first_name, last_name, country, phone)
VALUES
    ('dongtian@ibm.com','$2a$10$hbduT3nzX7OhXmdjdLFOyu.RJYR8cBAo.7NxkLKuCpFG4/ZzV38cW', 'dong', 'tian', 'shanghai', '16811113456'),
    ('chuntian@163.com','$2a$10$hbduT3nzX7OhXmdjdLFOyu.RJYR8cBAo.7NxkLKuCpFG4/ZzV38cW', 'chun', 'tian', 'beijin', '16811113121'),
    ('xiatian@ibm.com','$2a$10$hbduT3nzX7OhXmdjdLFOyu.RJYR8cBAo.7NxkLKuCpFG4/ZzV38cW', 'xia', 'tian', 'nanjin', '16812223121'),
    ('qiutian@126.com','$2a$10$hbduT3nzX7OhXmdjdLFOyu.RJYR8cBAo.7NxkLKuCpFG4/ZzV38cW', 'qiu', 'tian', 'suzhou', '16814443121'),
    ('xuetian@yahoo.com','$2a$10$hbduT3nzX7OhXmdjdLFOyu.RJYR8cBAo.7NxkLKuCpFG4/ZzV38cW', 'xue', 'tian', 'shantou', '16815553121')
    ON DUPLICATE KEY UPDATE first_name = VALUES(first_name), last_name = VALUES(last_name);
    
-- 插入数据到 Flight 表
INSERT INTO flight (flight_number, departure_airport_id, destination_airport_id, departure_date, departure_time, destination_date, destination_time, taxes, price)
VALUES
    ('00001', 1, 2, '2025-02-01', '10:00:00', '2025-11-01', '10:00:00', 10.00, 50.00),
    ('00002', 1, 3, '2025-10-01', '10:00:00', '2025-11-01', '10:00:00', 10.00, 20.00),
    ('00003', 3, 4, '2025-11-01', '10:00:00', '2025-11-01', '10:00:00', 5.00, 10.00),
    ('00004', 3, 1, '2025-11-01', '10:00:00', '2025-11-01', '10:00:00', 5.00, 10.00)
    ON DUPLICATE KEY UPDATE price = VALUES(price);

-- 插入用户数据到 Booking 表
INSERT INTO booking (user_id, flight_id, reference, status, booking_time, flight_type, total_price)
VALUES
    (1, 1, '00001', 'UPCOMING', '2025-02-01 10:00:00', 'DEPARTURE', 50.00),
    (2, 1, '00002', 'UPCOMING', '2025-02-01 10:00:00', 'DEPARTURE', 21.01),
    (1, 3, '00003', 'UPCOMING', '2025-02-01 10:00:00', 'DEPARTURE', 22.34),
    (3, 4, '00004', 'UPCOMING', '2025-02-01 10:00:00', 'DEPARTURE', 10.11),
    (2, 1, '00005', 'UPCOMING', '2025-02-01 10:00:00', 'DEPARTURE', 45.78)
    ON DUPLICATE KEY UPDATE total_price = VALUES(total_price);

-- 插入数据到 Passenger 表
INSERT INTO passenger (booking_id, first_name, last_name, email)
VALUES
    (1, 'zhe', 'xian', 'zhexian@ibm.com'),
    (2, 'hua', 'lun', 'hualun@126.com'),
    (3, 'ma', 'qian', 'maqian@163.com'),
    (4, 'yue', 'quan', 'yuequan@yahoo.com')
    ON DUPLICATE KEY UPDATE first_name = VALUES(first_name), last_name = VALUES(last_name);




