CREATE TABLE IF NOT EXISTS attendance_log (
    id SERIAL PRIMARY KEY,
    employee_id VARCHAR(50) NOT NULL,
    day DATE NOT NULL,
    time TIME NOT NULL,
    type VARCHAR(20) NOT NULL,
    attendance_machine_id VARCHAR(30) NOT NULL
);

INSERT INTO attendance_log (employee_id, day, time, type, attendance_machine_id) VALUES
('20200673', '2021-05-01' , '07:00:00', 'CHECKIN', '1'),
('20200196', '2021-05-01', '07:30:00', 'CHECKIN', '1'),
('20200673', '2021-05-01', '17:00:00', 'CHECKOUT', '1'),
('20200196', '2021-05-01', '17:18:02', 'CHECKOUT', '1'),
('20200260', '2021-05-02', '08:00:00', 'CHECKIN', '2'),
('20200673', '2021-05-02', '16:49:31', 'CHECKOUT', '2');