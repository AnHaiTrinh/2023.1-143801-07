CREATE TABLE IF NOT EXISTS history_import (
    id SERIAL PRIMARY KEY,
    day DATE NOT NULL,
    time TIME NOT NULL,
    totalRecord int NOT NULL
);

INSERT INTO history_import (day, time, totalRecord) VALUES
('2021-05-01' , '07:00:00', '3'),
('2021-05-01', '07:30:00', '4'),
('2021-05-01', '17:00:00', '5'),
('2021-05-01', '17:18:02', '2'),
('2021-05-02', '08:00:00', '10'),
('2021-05-02', '16:49:31', '15');