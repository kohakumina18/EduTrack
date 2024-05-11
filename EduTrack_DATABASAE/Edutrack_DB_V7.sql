--drop database Edutrack;
create database Edutrack
go
use Edutrack
go

-- Create UserRole
CREATE TABLE UserRole (
    RoleID INT PRIMARY KEY,
    RoleName VARCHAR(50)
);

INSERT INTO UserRole (RoleID, RoleName)
VALUES 
    (1, 'NHANVIENHOCVU'),
    (2, 'GIAOVIEN'),
    (3, 'HOCSINH'),
    (4, 'SYSADMIN');


-- Create GIAOVIEN (Teachers) Table
CREATE TABLE GIAOVIEN (
    UserID VARCHAR(10) PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    Phone VARCHAR(20),
    DateOfBirth DATE,
    Gender VARCHAR(10),
	RoleID INT,
	CONSTRAINT FK_RoleGV FOREIGN KEY (RoleID) REFERENCES UserRole(RoleID)
);

-- Create LOP (Classes) Table
CREATE TABLE LOP (
    ClassID VARCHAR(10) PRIMARY KEY,
    ClassName VARCHAR(255),
    ClassSchedule VARCHAR(255),
    RoomNumber VARCHAR(50),
    AssignedTeacherID VARCHAR(10),
	ClassState VARCHAR(10),
    so_luong_hoc_vien INT,
    so_luong_gioi_han INT,
    CONSTRAINT FK_AssignedTeacherID FOREIGN KEY (AssignedTeacherID) REFERENCES GIAOVIEN(UserID)
);

-- Create HOCSINH (Students) Table
CREATE TABLE HOCSINH (
    UserID VARCHAR(10) PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    Phone VARCHAR(20),
    DateOfBirth DATE,
    Gender VARCHAR(10),
	RoleID INT,
	CONSTRAINT FK_ROLEHS FOREIGN KEY (RoleID) REFERENCES UserRole(RoleID)
);

-- Create NHANVIENHOCVU (Staff) Table
CREATE TABLE NHANVIENHOCVU (
    UserID VARCHAR(10) PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    Phone VARCHAR(20),
    DateOfBirth DATE,
    Gender VARCHAR(10),
	RoleID INT,
	CONSTRAINT FK_RoleSTAFF FOREIGN KEY (RoleID) REFERENCES UserRole(RoleID)
);

-- Create SYSADMIN (System Administrators) Table
CREATE TABLE SYSADMIN (
    UserID VARCHAR(10) PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    Phone VARCHAR(20),
    DateOfBirth DATE,
    Gender VARCHAR(10),
	RoleID INT,
	CONSTRAINT FK_RoleADMIN FOREIGN KEY (RoleID) REFERENCES UserRole(RoleID)
);

-- Create BANDIEMDANH (Attendance Report) Table
CREATE TABLE BANDIEMDANH (
    AttendanceID VARCHAR(10) PRIMARY KEY,
    ClassID VARCHAR(10),
    StudentID VARCHAR(10),
    Date DATE,
    PresenceStatus VARCHAR(20),
    CONSTRAINT FK_ClassID_Attendance FOREIGN KEY (ClassID) REFERENCES LOP(ClassID),
    CONSTRAINT FK_StudentID_Attendance FOREIGN KEY (StudentID) REFERENCES HOCSINH(UserID)
);

-- Create BANGDIEM (Grade Report) Table
CREATE TABLE BANGDIEM (
    GradeID VARCHAR(10) PRIMARY KEY,
    ClassID VARCHAR(10),
    StudentID VARCHAR(10),
    Grade FLOAT,
    Comments TEXT,
    CONSTRAINT FK_ClassID_Grade FOREIGN KEY (ClassID) REFERENCES LOP(ClassID),
    CONSTRAINT FK_StudentID_Grade FOREIGN KEY (StudentID) REFERENCES HOCSINH(UserID)
);

-- Create TAIKHOAN (Accounts) Table
CREATE TABLE TAIKHOAN (
    UserID VARCHAR(10) PRIMARY KEY,
    Username VARCHAR(50),
    Pass VARCHAR(255),
    RoleID INT,
    AccountState VARCHAR(10),
    CONSTRAINT FK_RoleID FOREIGN KEY (RoleID) REFERENCES UserRole(RoleID)
);

-- Create COURSE table
CREATE TABLE COURSE (
    CourseID VARCHAR(10) PRIMARY KEY,
    Coursename VARCHAR(255),
    Noidung TEXT,
    ngay_tao DATE,
    ngay_bd DATE,
    ngay_kt DATE,
    UserID VARCHAR(10),
    FOREIGN KEY (UserID) REFERENCES NHANVIENHOCVU(UserID)
);

-- Create ChiTietLopHoc table // chưa chạy
CREATE TABLE ChiTietLopHoc (
    id VARCHAR(10) PRIMARY KEY,
    id_lop_hoc VARCHAR(10),
    CourseID VARCHAR(10),
    GradeID VARCHAR(10),
    AttendanceID VARCHAR(10),
    FOREIGN KEY (id_lop_hoc) REFERENCES LOP(ClassID),
    FOREIGN KEY (CourseID) REFERENCES COURSE(CourseID),
    FOREIGN KEY (GradeID) REFERENCES BANGDIEM(GradeID),
    FOREIGN KEY (AttendanceID) REFERENCES BANDIEMDANH(AttendanceID),
);

-- Create XEPLOP table
CREATE TABLE XEPLOP (
    EnrollmentID VARCHAR(10) PRIMARY KEY,
    UserID VARCHAR(10),
    ClassID VARCHAR(10),
    CONSTRAINT FK_UserID_Enrollment FOREIGN KEY (UserID) REFERENCES HOCSINH(UserID),
    CONSTRAINT FK_ClassID_Enrollment FOREIGN KEY (ClassID) REFERENCES LOP(ClassID)
);

-- Create PHANHOI (Feedback) Table
-- drop table PHANHOI;
CREATE TABLE PHANHOI (
	FeedbackID VARCHAR(10) PRIMARY KEY,
	UserID VARCHAR(10),
	DateOfFeedback DATE,
	Content VARCHAR(1000),
	FeedbackState VARCHAR(10),
	CONSTRAINT FK_Feedback FOREIGN KEY(UserID) REFERENCES HOCSINH(UserID)
);
-- đã nhận / đã xử lý