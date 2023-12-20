CREATE TABLE IF NOT EXISTS employee (
    id CHAR(8) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    title VARCHAR(20) NOT NULL,
    department VARCHAR(20) NOT NULL
);

INSERT INTO employee (id, name, password, title, department) VALUES
('M001', 'Nguyen Van Dai', '123456','HR Manager', 'Human Resource'),
('M002', 'Tran Tien Thanh', 'password', 'Head of Department', 'Production Factory'),
('M003', 'Vu Viet Anh', '1', 'Worker', 'Production Factory'),
('M004', 'Dang Thu Linh', 'abcd', 'Officer', 'Accounting');