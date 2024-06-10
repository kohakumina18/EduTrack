-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 03, 2024 lúc 06:40 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `edutrack1`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bangdiem`
--

CREATE TABLE `bangdiem` (
  `ClassID` varchar(10) DEFAULT NULL,
  `StudentID` varchar(10) DEFAULT NULL,
  `Midtern` float DEFAULT NULL,
  `Final` float DEFAULT NULL,
  `Comments` text DEFAULT NULL,
  `GradeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bangdiem`
--

INSERT INTO `bangdiem` (`ClassID`, `StudentID`, `Midtern`, `Final`, `Comments`, `GradeID`) VALUES
('L02', 'HS002', 100, 100, 'Excellent performance', 2),
('L03', 'HS003', 0, 0, 'Needs improvement', 3),
('L04', 'HS004', 0, 0, 'Outstanding performance', 4),
('L01', 'HS005', NULL, NULL, NULL, 71),
('L01', 'HS004', NULL, NULL, NULL, 72),
('L01', 'HS003', NULL, NULL, NULL, 73),
('L01', 'HS002', NULL, NULL, NULL, 74),
('L01', 'HS001', NULL, NULL, NULL, 75),
('L06', 'HS001', NULL, NULL, NULL, 76);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bangdiemdanh`
--

CREATE TABLE `bangdiemdanh` (
  `ClassID` varchar(10) DEFAULT NULL,
  `StudentID` varchar(10) DEFAULT NULL,
  `Ngay` date DEFAULT NULL,
  `LyDo` varchar(20) DEFAULT NULL,
  `AttendID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietlophoc`
--

CREATE TABLE `chitietlophoc` (
  `id` varchar(10) NOT NULL,
  `id_lop_hoc` varchar(10) DEFAULT NULL,
  `CourseID` varchar(10) DEFAULT NULL,
  `so_luong_hoc_vien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietlophoc`
--

INSERT INTO `chitietlophoc` (`id`, `id_lop_hoc`, `CourseID`, `so_luong_hoc_vien`) VALUES
('CT01', 'L01', 'C001', 2),
('CT02', 'L02', 'C002', 25),
('CT03', 'L03', 'C003', 20),
('CT04', 'L04', 'C004', 15),
('CT05', 'L05', 'C005', 10),
('CT06', 'L06', 'C006', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `course`
--

CREATE TABLE `course` (
  `CourseID` varchar(10) NOT NULL,
  `Coursename` varchar(255) DEFAULT NULL,
  `Noidung` text DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ngay_bd` date DEFAULT NULL,
  `ngay_kt` date DEFAULT NULL,
  `UserID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `course`
--

INSERT INTO `course` (`CourseID`, `Coursename`, `Noidung`, `ngay_tao`, `ngay_bd`, `ngay_kt`, `UserID`) VALUES
('C001', 'Mathematics', 'Math course content', '2024-05-24', '2024-01-15', '2025-06-01', 'NV001'),
('C002', 'Physics', 'Physics course content', '2023-01-01', '2023-01-15', '2023-06-01', 'NV002'),
('C003', 'Chemistry', 'Chemistry course content', '2023-01-01', '2023-01-15', '2023-06-01', 'NV003'),
('C004', 'Biology', 'Biology course content', '2023-01-01', '2023-01-15', '2023-06-01', 'NV004'),
('C005', 'History', 'History course content', '2023-01-01', '2023-01-15', '2023-06-01', 'NV005'),
('C006', 'Khóa học', 'link gì đó', '2024-06-01', '2024-06-01', '2024-07-02', 'NV001');

--
-- Bẫy `course`
--
DELIMITER $$
CREATE TRIGGER `before_delete_course` BEFORE DELETE ON `course` FOR EACH ROW BEGIN
    IF EXISTS (
        SELECT 1
        FROM chitietlophoc
        WHERE CourseID = OLD.CourseID
    ) THEN
        UPDATE lop
        SET ClassState = 'Inactive'
        WHERE ClassID IN (
            SELECT id_lop_hoc
            FROM chitietlophoc
            WHERE CourseID = OLD.CourseID
        );
        UPDATE chitietlophoc
        SET CourseID = NULL
        WHERE CourseID = OLD.CourseID;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giaovien`
--

CREATE TABLE `giaovien` (
  `UserID` varchar(10) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `RoleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `giaovien`
--

INSERT INTO `giaovien` (`UserID`, `FirstName`, `LastName`, `Email`, `Phone`, `DateOfBirth`, `Gender`, `RoleID`) VALUES
('GV001', 'Văn Thịnh', 'Nguyễn', 'nguyenvanthinh@gmail.com', '0909198011', '1980-01-01', 'Nam', 2),
('GV002', 'Ngọc Mai', 'Trần', 'tranngocmai@gmail.com', '0909198222', '1982-02-02', 'Nữ', 2),
('GV003', 'Minh Đức', 'Lê', 'leminhduc@gmail.com', '0909198433', '1984-03-03', 'Nam', 2),
('GV004', 'Khánh An', 'Phạm', 'phamkhanhan@gmail.com', '0909198644', '1986-04-04', 'Nữ', 2),
('GV005', 'Gia Phú', 'Hoàng', 'hoanggiaphu@gmail.com', '0909198855', '1988-05-05', 'Nam', 2),
('gv010', 'a', 'b', 'c', 'd', '2003-09-13', 'nam', 2);

--
-- Bẫy `giaovien`
--
DELIMITER $$
CREATE TRIGGER `after_delete_giaovien` AFTER DELETE ON `giaovien` FOR EACH ROW BEGIN
    DELETE FROM taikhoan
    WHERE UserID = OLD.UserID;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_delete_giaovien` BEFORE DELETE ON `giaovien` FOR EACH ROW BEGIN
    IF EXISTS (
        SELECT 1
        FROM lop
        WHERE AssignedTeacherID = OLD.UserID AND ClassState = 'Active'
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa giáo viên vì họ đang dạy lớp học active';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hocsinh`
--

CREATE TABLE `hocsinh` (
  `UserID` varchar(10) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `RoleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hocsinh`
--

INSERT INTO `hocsinh` (`UserID`, `FirstName`, `LastName`, `Email`, `Phone`, `DateOfBirth`, `Gender`, `RoleID`) VALUES
('HS001', 'Văn Huy', 'Lê', 'levantuan_hs@gmail.com', '09090909', '2005-01-01', 'Nam', 3),
('HS002', 'Khánh Nhi', 'Trần', 'trankhanhnhi_hs@gmail.com', '0906200622', '2006-02-02', 'Nữ', 3),
('HS003', 'Tấn Cường', 'Huỳnh', 'huynhtancuong_hs@gmail.com', '0906200733', '2007-03-03', 'Nam', 3),
('HS004', 'Thanh Ngân', 'Nguyễn', 'nguyenthanhngan_hs@gmail.com', '0906200844', '2008-04-04', 'Nữ', 3),
('HS005', 'Hoàng Nam', 'Phạm', 'phamhoangnam_hs@gmail.com', '0906200955', '2009-05-05', 'Nam', 3);

--
-- Bẫy `hocsinh`
--
DELIMITER $$
CREATE TRIGGER `after_delete_hocsinh` AFTER DELETE ON `hocsinh` FOR EACH ROW BEGIN

    DELETE FROM taikhoan
    WHERE UserID = OLD.UserID;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lop`
--

CREATE TABLE `lop` (
  `ClassID` varchar(10) NOT NULL,
  `ClassName` varchar(255) DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `RoomNumber` varchar(50) DEFAULT NULL,
  `AssignedTeacherID` varchar(10) DEFAULT NULL,
  `ClassState` varchar(10) DEFAULT NULL,
  `so_luong_gioi_han` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `lop`
--

INSERT INTO `lop` (`ClassID`, `ClassName`, `CreateDate`, `RoomNumber`, `AssignedTeacherID`, `ClassState`, `so_luong_gioi_han`) VALUES
('L01', 'Lớp E1', '2023-01-01', 'R101', 'GV001', 'Active', 30),
('L02', 'Lớp E2', '2024-05-24', 'R102', 'GV002', 'Active', 25),
('L03', 'Lớp E3', '2023-01-01', 'R103', 'GV003', 'Active', 20),
('L04', 'Lớp E4', '2023-01-01', 'R104', 'GV004', 'Active', 15),
('L05', 'Lớp E5', '2023-01-01', 'R105', 'GV005', 'Active', 10),
('L06', 'Lớp test demo', '2024-06-02', 'R106', 'GV001', 'Active', 1);

--
-- Bẫy `lop`
--
DELIMITER $$
CREATE TRIGGER `after_insert_lop` AFTER INSERT ON `lop` FOR EACH ROW BEGIN
    DECLARE class_number VARCHAR(10);

    SET class_number = SUBSTRING(NEW.ClassID, 2);
    INSERT INTO chitietlophoc (id, id_lop_hoc, CourseID, so_luong_hoc_vien)
    VALUES (CONCAT('CT', class_number), NEW.ClassID, NULL, 0);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_lop_delete` BEFORE DELETE ON `lop` FOR EACH ROW BEGIN
  IF OLD.ClassState = 'Active' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Cannot delete class with Active state.';
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvienhocvu`
--

CREATE TABLE `nhanvienhocvu` (
  `UserID` varchar(10) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `RoleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvienhocvu`
--

INSERT INTO `nhanvienhocvu` (`UserID`, `FirstName`, `LastName`, `Email`, `Phone`, `DateOfBirth`, `Gender`, `RoleID`) VALUES
('NV001', 'Văn Tùng', 'Lê', 'levantung_nv@gmail.com', '0988198001', '1980-01-01', 'Nam', 1),
('NV002', 'Ngọc Phương', 'Trần', 'tranngocphuong_nv@gmail.com', '0988198202', '1982-02-02', 'Nữ', 1),
('NV003', 'Hoàng Minh', 'Hồ', 'hohoangminh_nv@gmail.com', '0988198403', '1984-03-03', 'Nam', 1),
('NV004', 'Xuân Mai', 'Nguyễn', 'nguyenxuanmai_nv@gmail.com', '0988198604', '1986-04-04', 'Nữ', 1),
('NV005', 'Quốc Huy', 'Võ', 'voquochuy_nv@gmail.com', '0988198805', '1988-05-05', 'Nam', 1);

--
-- Bẫy `nhanvienhocvu`
--
DELIMITER $$
CREATE TRIGGER `after_delete_nvhocvu` AFTER DELETE ON `nhanvienhocvu` FOR EACH ROW BEGIN

    DELETE FROM taikhoan
    WHERE UserID = OLD.UserID;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanhoi`
--

CREATE TABLE `phanhoi` (
  `FeedbackID` int(11) NOT NULL,
  `UserID` varchar(10) DEFAULT NULL,
  `DateOfFeedback` date DEFAULT NULL,
  `Content` varchar(1000) DEFAULT NULL,
  `FeedbackState` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phanhoi`
--

INSERT INTO `phanhoi` (`FeedbackID`, `UserID`, `DateOfFeedback`, `Content`, `FeedbackState`) VALUES
(1, 'HS001', '2024-05-24', 'tôi thấy lớp học này rất là bổ ích', 'Chưa xác nhận'),
(2, 'HS001', '2024-05-25', 'hi vọng là không có lỗi', 'Đã xác nhận'),
(3, 'HS001', '2024-06-01', 'demo lại', 'Đã xác nhận');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sysadmin`
--

CREATE TABLE `sysadmin` (
  `UserID` varchar(10) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `RoleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sysadmin`
--

INSERT INTO `sysadmin` (`UserID`, `FirstName`, `LastName`, `Email`, `Phone`, `DateOfBirth`, `Gender`, `RoleID`) VALUES
('AD001', 'Van Hau', 'Doan', 'doanvanhau_ad@gmail.com', '0909161011', '1980-01-01', 'Male', 4),
('AD002', 'Anh Vy', 'Nguyen', 'nguyenanhvy_ad@gmail.com', '0909198222', '1982-02-02', 'Female', 4),
('AD003', 'Xuan Truong', 'Le', 'lexuantruong_ad@gmail.com', '0909198433', '1984-03-03', 'Male', 4);

--
-- Bẫy `sysadmin`
--
DELIMITER $$
CREATE TRIGGER `after_delete_sysadmin` AFTER DELETE ON `sysadmin` FOR EACH ROW BEGIN

    DELETE FROM taikhoan
    WHERE UserID = OLD.UserID;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `UserID` varchar(10) NOT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Pass` varchar(255) DEFAULT NULL,
  `RoleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`UserID`, `Username`, `Pass`, `RoleID`) VALUES
('AD001', 'ad001', 'password4', 4),
('GV001', 'gv001', 'password2', 2),
('HS001', 'hs001', 'password1', 3),
('HS002', 'hs002', 'password5', 3),
('NV001', 'nv001', 'password3', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thoikhoabieu`
--

CREATE TABLE `thoikhoabieu` (
  `ClassID` varchar(10) NOT NULL,
  `Thu` varchar(10) NOT NULL,
  `TimeStart` varchar(6) NOT NULL,
  `TimeEnd` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `thoikhoabieu`
--

INSERT INTO `thoikhoabieu` (`ClassID`, `Thu`, `TimeStart`, `TimeEnd`) VALUES
('L01', '2', '08:30', '10:00'),
('L02', '3', '14:00', '15:30'),
('L03', '4', '17:30', '19:00'),
('L04', '5', '19:30', '21:00'),
('L05', '6', '19:30', '21:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `userrole`
--

CREATE TABLE `userrole` (
  `RoleID` int(11) NOT NULL,
  `RoleName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `userrole`
--

INSERT INTO `userrole` (`RoleID`, `RoleName`) VALUES
(1, 'NHANVIENHOCVU'),
(2, 'GIAOVIEN'),
(3, 'HOCSINH'),
(4, 'SYSADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `xeplop`
--

CREATE TABLE `xeplop` (
  `UserID` varchar(10) NOT NULL,
  `ClassID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `xeplop`
--

INSERT INTO `xeplop` (`UserID`, `ClassID`) VALUES
('HS001', 'L01'),
('HS001', 'L06'),
('HS002', 'L01'),
('HS002', 'L02'),
('HS003', 'L01'),
('HS003', 'L03'),
('HS004', 'L01'),
('HS004', 'L04'),
('HS005', 'L01');

--
-- Bẫy `xeplop`
--
DELIMITER $$
CREATE TRIGGER `after_xeplop_delete` AFTER DELETE ON `xeplop` FOR EACH ROW BEGIN
    UPDATE chitietlophoc
    SET so_luong_hoc_vien = so_luong_hoc_vien - 1
    WHERE id_lop_hoc = OLD.ClassID;
    DELETE FROM bangdiem
    WHERE StudentID = OLD.UserID
    AND ClassID = OLD.ClassID;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_xeplop_insert` AFTER INSERT ON `xeplop` FOR EACH ROW BEGIN
  INSERT INTO bangdiem (StudentID, ClassID)
  VALUES (NEW.UserID, NEW.ClassID);
  
  UPDATE chitietlophoc
  SET so_luong_hoc_vien = so_luong_hoc_vien + 1
  WHERE id_lop_hoc = NEW.ClassID;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_xeplop_update` AFTER UPDATE ON `xeplop` FOR EACH ROW BEGIN
    UPDATE chitietlophoc
    SET so_luong_hoc_vien = so_luong_hoc_vien - 1
    WHERE id_lop_hoc = OLD.ClassID;

    UPDATE chitietlophoc
    SET so_luong_hoc_vien = so_luong_hoc_vien + 1
    WHERE id_lop_hoc = NEW.ClassID;
    
    UPDATE bangdiem
  	SET bangdiem.ClassID = NEW.ClassID
  	WHERE StudentID = OLD.UserID
  	AND ClassID = OLD.ClassID;

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_xeplop_insert` BEFORE INSERT ON `xeplop` FOR EACH ROW BEGIN
    DECLARE current_student_count INT;
    DECLARE student_limit INT;
    SELECT so_luong_hoc_vien INTO current_student_count
    FROM chitietlophoc
    WHERE id_lop_hoc = NEW.ClassID;
    SELECT so_luong_gioi_han INTO student_limit
    FROM lop
    WHERE ClassID = NEW.ClassID;
    IF current_student_count >= student_limit THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Lớp đã đầy';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_xeplop_update` BEFORE UPDATE ON `xeplop` FOR EACH ROW BEGIN
    DECLARE current_student_count INT;
    DECLARE student_limit INT;
    SELECT so_luong_hoc_vien INTO current_student_count
    FROM chitietlophoc
    WHERE id_lop_hoc = NEW.ClassID;
    SELECT so_luong_gioi_han INTO student_limit
    FROM lop
    WHERE ClassID = NEW.ClassID;
    IF current_student_count >= student_limit THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Lớp đã đầy';
    END IF;
END
$$
DELIMITER ;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bangdiem`
--
ALTER TABLE `bangdiem`
  ADD PRIMARY KEY (`GradeID`),
  ADD KEY `FK_StudentID` (`StudentID`),
  ADD KEY `FK_ClassID_Grade` (`ClassID`);

--
-- Chỉ mục cho bảng `bangdiemdanh`
--
ALTER TABLE `bangdiemdanh`
  ADD PRIMARY KEY (`AttendID`),
  ADD KEY `FK_ClassID_Attendance` (`ClassID`),
  ADD KEY `FK_StudentID_Attendance` (`StudentID`);

--
-- Chỉ mục cho bảng `chitietlophoc`
--
ALTER TABLE `chitietlophoc`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chitietlophoc_ibfk_1` (`id_lop_hoc`),
  ADD KEY `chitietlophoc_ibfk_2` (`CourseID`);

--
-- Chỉ mục cho bảng `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`CourseID`),
  ADD KEY `UserID` (`UserID`);

--
-- Chỉ mục cho bảng `giaovien`
--
ALTER TABLE `giaovien`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `FK_RoleGV` (`RoleID`);

--
-- Chỉ mục cho bảng `hocsinh`
--
ALTER TABLE `hocsinh`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `FK_ROLEHS` (`RoleID`);

--
-- Chỉ mục cho bảng `lop`
--
ALTER TABLE `lop`
  ADD PRIMARY KEY (`ClassID`),
  ADD KEY `FK_AssignedTeacherID` (`AssignedTeacherID`);

--
-- Chỉ mục cho bảng `nhanvienhocvu`
--
ALTER TABLE `nhanvienhocvu`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `FK_RoleSTAFF` (`RoleID`);

--
-- Chỉ mục cho bảng `phanhoi`
--
ALTER TABLE `phanhoi`
  ADD PRIMARY KEY (`FeedbackID`),
  ADD KEY `FK_Feedback` (`UserID`);

--
-- Chỉ mục cho bảng `sysadmin`
--
ALTER TABLE `sysadmin`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `FK_RoleADMIN` (`RoleID`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `FK_RoleID` (`RoleID`);

--
-- Chỉ mục cho bảng `thoikhoabieu`
--
ALTER TABLE `thoikhoabieu`
  ADD PRIMARY KEY (`ClassID`,`Thu`,`TimeStart`);

--
-- Chỉ mục cho bảng `userrole`
--
ALTER TABLE `userrole`
  ADD PRIMARY KEY (`RoleID`);

--
-- Chỉ mục cho bảng `xeplop`
--
ALTER TABLE `xeplop`
  ADD PRIMARY KEY (`UserID`,`ClassID`),
  ADD KEY `FK_ClassID_Enrollment` (`ClassID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bangdiem`
--
ALTER TABLE `bangdiem`
  MODIFY `GradeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT cho bảng `bangdiemdanh`
--
ALTER TABLE `bangdiemdanh`
  MODIFY `AttendID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `phanhoi`
--
ALTER TABLE `phanhoi`
  MODIFY `FeedbackID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bangdiem`
--
ALTER TABLE `bangdiem`
  ADD CONSTRAINT `FK_ClassID_Grade` FOREIGN KEY (`ClassID`) REFERENCES `lop` (`ClassID`),
  ADD CONSTRAINT `FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `hocsinh` (`UserID`);

--
-- Các ràng buộc cho bảng `bangdiemdanh`
--
ALTER TABLE `bangdiemdanh`
  ADD CONSTRAINT `FK_ClassID_Attendance` FOREIGN KEY (`ClassID`) REFERENCES `lop` (`ClassID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_StudentID_Attendance` FOREIGN KEY (`StudentID`) REFERENCES `hocsinh` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitietlophoc`
--
ALTER TABLE `chitietlophoc`
  ADD CONSTRAINT `chitietlophoc_ibfk_1` FOREIGN KEY (`id_lop_hoc`) REFERENCES `lop` (`ClassID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `chitietlophoc_ibfk_2` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Các ràng buộc cho bảng `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `nhanvienhocvu` (`UserID`);

--
-- Các ràng buộc cho bảng `giaovien`
--
ALTER TABLE `giaovien`
  ADD CONSTRAINT `FK_RoleGV` FOREIGN KEY (`RoleID`) REFERENCES `userrole` (`RoleID`);

--
-- Các ràng buộc cho bảng `hocsinh`
--
ALTER TABLE `hocsinh`
  ADD CONSTRAINT `FK_ROLEHS` FOREIGN KEY (`RoleID`) REFERENCES `userrole` (`RoleID`);

--
-- Các ràng buộc cho bảng `lop`
--
ALTER TABLE `lop`
  ADD CONSTRAINT `FK_AssignedTeacherID` FOREIGN KEY (`AssignedTeacherID`) REFERENCES `giaovien` (`UserID`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Các ràng buộc cho bảng `nhanvienhocvu`
--
ALTER TABLE `nhanvienhocvu`
  ADD CONSTRAINT `FK_RoleSTAFF` FOREIGN KEY (`RoleID`) REFERENCES `userrole` (`RoleID`);

--
-- Các ràng buộc cho bảng `phanhoi`
--
ALTER TABLE `phanhoi`
  ADD CONSTRAINT `FK_Feedback` FOREIGN KEY (`UserID`) REFERENCES `hocsinh` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `sysadmin`
--
ALTER TABLE `sysadmin`
  ADD CONSTRAINT `FK_RoleADMIN` FOREIGN KEY (`RoleID`) REFERENCES `userrole` (`RoleID`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_RoleID` FOREIGN KEY (`RoleID`) REFERENCES `userrole` (`RoleID`);

--
-- Các ràng buộc cho bảng `thoikhoabieu`
--
ALTER TABLE `thoikhoabieu`
  ADD CONSTRAINT `FK_ClassID` FOREIGN KEY (`ClassID`) REFERENCES `lop` (`ClassID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `xeplop`
--
ALTER TABLE `xeplop`
  ADD CONSTRAINT `FK_ClassID_Enrollment` FOREIGN KEY (`ClassID`) REFERENCES `lop` (`ClassID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_UserID_Enrollment` FOREIGN KEY (`UserID`) REFERENCES `hocsinh` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
