-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 12, 2022 lúc 04:37 PM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qldiem`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_bangdiem`
--

CREATE TABLE `tbl_bangdiem` (
  `prefix` varchar(2) NOT NULL DEFAULT 'BD',
  `maBangDiem` int(5) UNSIGNED ZEROFILL NOT NULL,
  `maHocKy` int(5) NOT NULL,
  `maHocSinh` int(5) NOT NULL,
  `maLop` int(5) UNSIGNED ZEROFILL NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_bangdiem`
--

INSERT INTO `tbl_bangdiem` (`prefix`, `maBangDiem`, `maHocKy`, `maHocSinh`, `maLop`) VALUES
('BD', 00001, 1, 1, 00001);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_bangdiemchitiet`
--

CREATE TABLE `tbl_bangdiemchitiet` (
  `maBangDiemCT` int(5) UNSIGNED ZEROFILL NOT NULL,
  `maBangDiem` int(5) UNSIGNED ZEROFILL NOT NULL,
  `maMonHoc` int(5) UNSIGNED ZEROFILL NOT NULL,
  `diemMieng` float DEFAULT NULL,
  `diem1` float DEFAULT NULL,
  `diem2` float DEFAULT NULL,
  `diemThi` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_bangdiemchitiet`
--

INSERT INTO `tbl_bangdiemchitiet` (`maBangDiemCT`, `maBangDiem`, `maMonHoc`, `diemMieng`, `diem1`, `diem2`, `diemThi`) VALUES
(00002, 00001, 00002, 4, 4, 3, 7),
(00003, 00001, 00003, 4, 4, 3, 6),
(00004, 00001, 00004, 4, 4, 3, 7),
(00006, 00001, 00001, 7, 3, 3, 5),
(00007, 00001, 00005, 4, 3, 3, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_hocky`
--

CREATE TABLE `tbl_hocky` (
  `prefix` varchar(2) NOT NULL DEFAULT 'HK',
  `maHocky` int(5) UNSIGNED ZEROFILL NOT NULL,
  `tenHocky` varchar(255) NOT NULL,
  `maNamHoc` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_hocky`
--

INSERT INTO `tbl_hocky` (`prefix`, `maHocky`, `tenHocky`, `maNamHoc`) VALUES
('HK', 00001, 'Học kỳ 1', 1),
('HK', 00002, 'Học kỳ 2', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_hocsinh`
--

CREATE TABLE `tbl_hocsinh` (
  `prefix` varchar(2) NOT NULL DEFAULT 'HS',
  `maHocsinh` int(5) UNSIGNED ZEROFILL NOT NULL,
  `tenHocsinh` varchar(255) NOT NULL,
  `ngaySinh` varchar(255) NOT NULL,
  `gioiTinh` varchar(11) NOT NULL,
  `noiSinh` varchar(255) NOT NULL,
  `danToc` varchar(255) NOT NULL,
  `maLop` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_hocsinh`
--

INSERT INTO `tbl_hocsinh` (`prefix`, `maHocsinh`, `tenHocsinh`, `ngaySinh`, `gioiTinh`, `noiSinh`, `danToc`, `maLop`) VALUES
('HS', 00001, 'Lê Tùng Vân', '16/09/2000', 'Nam', 'An Giang', 'Kinh', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_khoi`
--

CREATE TABLE `tbl_khoi` (
  `prefix` varchar(2) NOT NULL DEFAULT 'KH',
  `maKhoi` int(5) UNSIGNED ZEROFILL NOT NULL,
  `tenKhoi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_khoi`
--

INSERT INTO `tbl_khoi` (`prefix`, `maKhoi`, `tenKhoi`) VALUES
('KH', 00001, 'Khối 10'),
('KH', 00002, 'Khối 11'),
('KH', 00003, 'Khối 12');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_lop`
--

CREATE TABLE `tbl_lop` (
  `prefix` varchar(2) NOT NULL DEFAULT 'LO',
  `maLop` int(5) UNSIGNED ZEROFILL NOT NULL,
  `tenLop` varchar(255) NOT NULL,
  `tenGVCN` varchar(255) NOT NULL,
  `maKhoi` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_lop`
--

INSERT INTO `tbl_lop` (`prefix`, `maLop`, `tenLop`, `tenGVCN`, `maKhoi`) VALUES
('LO', 00001, '10A1', 'Nguyeễn', 1),
('LO', 00002, '10A2', 'Nguyễn Thị A', 1),
('LO', 00003, '11A1', 'Trần Văn C', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_monhoc`
--

CREATE TABLE `tbl_monhoc` (
  `prefix` varchar(2) NOT NULL DEFAULT 'MH',
  `maMonHoc` int(5) UNSIGNED ZEROFILL NOT NULL,
  `tenMonHoc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_monhoc`
--

INSERT INTO `tbl_monhoc` (`prefix`, `maMonHoc`, `tenMonHoc`) VALUES
('MH', 00001, 'Toán'),
('MH', 00002, 'Ngữ văn'),
('MH', 00003, 'Tiếng anh'),
('MH', 00004, 'Vật lý'),
('MH', 00005, 'Hoá học'),
('MH', 00006, 'Lịch sử');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_namhoc`
--

CREATE TABLE `tbl_namhoc` (
  `prefix` varchar(2) NOT NULL DEFAULT 'NH',
  `maNamHoc` int(5) UNSIGNED ZEROFILL NOT NULL,
  `tenNamHoc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_namhoc`
--

INSERT INTO `tbl_namhoc` (`prefix`, `maNamHoc`, `tenNamHoc`) VALUES
('NH', 00001, 'Năm học 2021-2022');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_taikhoan`
--

CREATE TABLE `tbl_taikhoan` (
  `taiKhoan` varchar(255) NOT NULL,
  `matKhau` varchar(255) NOT NULL,
  `quyen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tbl_bangdiem`
--
ALTER TABLE `tbl_bangdiem`
  ADD PRIMARY KEY (`maBangDiem`);

--
-- Chỉ mục cho bảng `tbl_bangdiemchitiet`
--
ALTER TABLE `tbl_bangdiemchitiet`
  ADD PRIMARY KEY (`maBangDiemCT`);

--
-- Chỉ mục cho bảng `tbl_hocky`
--
ALTER TABLE `tbl_hocky`
  ADD PRIMARY KEY (`maHocky`);

--
-- Chỉ mục cho bảng `tbl_hocsinh`
--
ALTER TABLE `tbl_hocsinh`
  ADD PRIMARY KEY (`maHocsinh`);

--
-- Chỉ mục cho bảng `tbl_khoi`
--
ALTER TABLE `tbl_khoi`
  ADD PRIMARY KEY (`maKhoi`);

--
-- Chỉ mục cho bảng `tbl_lop`
--
ALTER TABLE `tbl_lop`
  ADD PRIMARY KEY (`maLop`);

--
-- Chỉ mục cho bảng `tbl_monhoc`
--
ALTER TABLE `tbl_monhoc`
  ADD PRIMARY KEY (`maMonHoc`);

--
-- Chỉ mục cho bảng `tbl_namhoc`
--
ALTER TABLE `tbl_namhoc`
  ADD PRIMARY KEY (`maNamHoc`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tbl_bangdiem`
--
ALTER TABLE `tbl_bangdiem`
  MODIFY `maBangDiem` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `tbl_bangdiemchitiet`
--
ALTER TABLE `tbl_bangdiemchitiet`
  MODIFY `maBangDiemCT` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `tbl_hocky`
--
ALTER TABLE `tbl_hocky`
  MODIFY `maHocky` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `tbl_hocsinh`
--
ALTER TABLE `tbl_hocsinh`
  MODIFY `maHocsinh` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `tbl_khoi`
--
ALTER TABLE `tbl_khoi`
  MODIFY `maKhoi` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tbl_lop`
--
ALTER TABLE `tbl_lop`
  MODIFY `maLop` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tbl_monhoc`
--
ALTER TABLE `tbl_monhoc`
  MODIFY `maMonHoc` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `tbl_namhoc`
--
ALTER TABLE `tbl_namhoc`
  MODIFY `maNamHoc` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
