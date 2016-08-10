-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2016-08-02 12:12:37
-- 服务器版本： 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `taccount`
--

-- --------------------------------------------------------

--
-- 表的结构 `t_account`
--

CREATE TABLE IF NOT EXISTS `t_account` (
`ID` bigint(9) NOT NULL,
  `NUMBER` varchar(9) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `BALANCE` decimal(8,2) DEFAULT '0.00'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- 转存表中的数据 `t_account`
--

INSERT INTO `t_account` (`ID`, `NUMBER`, `NAME`, `BALANCE`) VALUES
(1, '123456789', 'Keri Lee', '0.00'),
(2, '123456001', 'Dollie R. Schnidt', '0.00'),
(3, '123456002', 'Cornelia J. LeClerc', '0.00'),
(4, '123456003', 'Cynthia Rau', '0.00'),
(5, '123456004', 'Douglas R. Cobbs', '0.00'),
(6, '123456005', 'Michael Patel', '0.00'),
(7, '123456006', 'Suzanne Wong', '0.00'),
(8, '123456007', 'Ivan C. Jaya', '0.00'),
(9, '123456008', 'Ida Ketterer', '0.00'),
(10, '123456009', 'Laina Ochoa Lucero', '0.00'),
(11, '123456010', 'Wesley M. Montana', '0.00'),
(12, '123456011', 'Leslie F. McCleary', '0.00'),
(13, '123456012', 'Sayeed D. Mudra', '0.00'),
(14, '123456013', 'Pietronella J. Domingo', '0.00'),
(15, '123456014', 'John S. O''Leary', '0.00'),
(16, '123456015', 'Gladys D. Smith', '0.00'),
(17, '123456016', 'Sally O. Thygesen', '0.00'),
(18, '123456017', 'Rachel Vogt', '0.00'),
(19, '123456018', 'Julia DeLong', '0.00'),
(20, '123456019', 'Mark T. Rizzoli', '0.00'),
(21, '123456020', 'Maria J. Angelo', '0.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_account`
--
ALTER TABLE `t_account`
 ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_account`
--
ALTER TABLE `t_account`
MODIFY `ID` bigint(9) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
