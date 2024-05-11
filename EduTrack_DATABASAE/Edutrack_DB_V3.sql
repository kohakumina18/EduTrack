--drop database Edutrack;
create database Edutrack
go
use Edutrack
go

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
    UserID INT PRIMARY KEY,
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
    ClassID INT PRIMARY KEY,
    ClassName VARCHAR(255),
    ClassSchedule VARCHAR(255),
    RoomNumber VARCHAR(50),
    AssignedTeacherID INT,
    CONSTRAINT FK_AssignedTeacherID FOREIGN KEY (AssignedTeacherID) REFERENCES GIAOVIEN(UserID)
);

-- Create HOCSINH (Students) Table
CREATE TABLE HOCSINH (
    UserID INT PRIMARY KEY,
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
    UserID INT PRIMARY KEY,
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
    UserID INT PRIMARY KEY,
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
    AttendanceID INT PRIMARY KEY,
    ClassID INT,
    StudentID INT,
    Date DATE,
    PresenceStatus VARCHAR(20),
    CONSTRAINT FK_ClassID_Attendance FOREIGN KEY (ClassID) REFERENCES LOP(ClassID),
    CONSTRAINT FK_StudentID_Attendance FOREIGN KEY (StudentID) REFERENCES HOCSINH(UserID)
);

-- Create BANGDIEM (Grade Report) Table
CREATE TABLE BANGDIEM (
    GradeID INT PRIMARY KEY,
    ClassID INT,
    StudentID INT,
    Grade FLOAT,
    Comments TEXT,
    CONSTRAINT FK_ClassID_Grade FOREIGN KEY (ClassID) REFERENCES LOP(ClassID),
    CONSTRAINT FK_StudentID_Grade FOREIGN KEY (StudentID) REFERENCES HOCSINH(UserID)
);

-- Create TAIKHOAN (Accounts) Table
CREATE TABLE TAIKHOAN (
    UserID INT PRIMARY KEY,
    Username VARCHAR(50),
    Password VARCHAR(255),
    RoleID INT,
    Status VARCHAR(10),
    CONSTRAINT FK_RoleID FOREIGN KEY (RoleID) REFERENCES UserRole(RoleID)
);

