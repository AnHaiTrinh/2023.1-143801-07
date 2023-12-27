CREATE TABLE IF NOT EXISTS employee (
    id CHAR(8) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    title VARCHAR(20) NOT NULL,
    department VARCHAR(20) NOT NULL
);

INSERT INTO employee (id, name, password, title, department) VALUES
('20200673', 'Le Anh Vu', '123456','HR Manager', 'Human Resource'),
('20200260', 'Nguyen Kim Hung', 'password', 'Head of Department', 'Production Factory'),
('20200421', 'Nguyen Van Nam', '1', 'Worker', 'Production Factory'),
('20200196', 'Trinh An Hai', 'abcd', 'Officer', 'Accounting'),
('M0000001', 'Tran Van Cuong', '123456','HR Manager', 'Human Resource'),
('M0000003', 'Le Van Cuong', '1', 'Worker', 'Production Factory');