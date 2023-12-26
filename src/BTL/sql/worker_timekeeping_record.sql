CREATE TABLE IF NOT EXISTS worker_timekeeping_record
(
    id SERIAL PRIMARY KEY,
    id_employee character varying(8) NOT NULL,
    name_employee character varying(50),
    department character varying(255),
    month character varying(10),
    total_working_hours double precision,
    total_overtime_hours double precision
);

INSERT INTO worker_timekeeping_record(id_employee, name_employee, department, month, total_working_hours, total_overtime_hours) VALUES
('W001', 'Trần Văn Đạt','Nhà máy 1', '11/2023', 195.5, 10.5),
('W002', 'Nguyễn Thị Hoa', 'Nhà máy 2', '11/2023', 180, 9),
('W003', 'Lê Văn Nam', 'Nhà máy 3', '11/2023', 165, 8),
('W004', 'Phạm Thành An', 'Nhà máy 1', '12/2023', 205, 11.5),
('W005', 'Nguyễn Văn Ba', 'Nhà máy 2', '12/2023', 190, 10),
('W006', 'Dương Cao Thắng', 'Nhà máy 3', '12/2023', 175, 9),
('W007', 'Nguyễn Thị Duyên', 'Nhà máy 1', '11/2023', 215, 12.5),
('W008', 'Phạm Văn Huy', 'Nhà máy 2', '11/2023', 200, 11),
('W009', 'Nguyễn Thanh Hằng', 'Nhà máy 3', '11/2023', 185, 10);