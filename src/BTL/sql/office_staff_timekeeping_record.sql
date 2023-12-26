CREATE TABLE IF NOT EXISTS office_staff_timekeeping_record
(
    id SERIAL PRIMARY KEY,
    id_employee character varying(8) COLLATE pg_catalog."default" NOT NULL,
    name_employee character varying(50) COLLATE pg_catalog."default",
    department character varying(255) COLLATE pg_catalog."default",
    month character varying(10) COLLATE pg_catalog."default",
    total_work_sessions integer,
    total_arriving_late_or_leaving_early_hours double precision
);

INSERT INTO office_staff_timekeeping_record (id_employee, name_employee, department, month, total_work_sessions, total_arriving_late_or_leaving_early_hours) VALUES
('M001', 'Trần Văn Hòa', 'HR', '11/2023', 20, 1.5),
('M002', 'Nguyễn Thị Hoa', 'IT', '11/2023', 15, 2),
('M003', 'Lê Văn An', 'IT', '1/2024', 18, 0.5),
('M004', 'Phạm Thành Hưng', 'IT', '1/2024', 22, 0),
('M005', 'Nguyễn Văn Nam', 'Customer Service', '1/2024', 17, 1),
('M006', 'Dương Thị Mai', 'HR', 'Apr-24', 21, 2.5),
('M007', 'Nguyễn Thị Lan', 'Customer Service', '6/2024', 23, 1.5);
