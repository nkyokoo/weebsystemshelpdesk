-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Vært: 127.0.0.1
-- Genereringstid: 30. 05 2017 kl. 14:31:19
-- Serverversion: 10.1.21-MariaDB
-- PHP-version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `helpdesk`
--
CREATE DATABASE IF NOT EXISTS `helpdesk` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `helpdesk`;

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `agents`
--

CREATE TABLE IF NOT EXISTS `agents` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `eftername` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Data dump for tabellen `agents`
--

INSERT INTO `agents` (`ID`, `firstname`, `eftername`, `username`, `password`, `email`) VALUES
(1, 'kyoko', 'toshino', 'kyoko', '1234', 'kyoko@weebsystems.dk');

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `cases`
--

CREATE TABLE IF NOT EXISTS `cases` (
  `ID` int(11) NOT NULL,
  `casetype_ID` int(11) NOT NULL,
  `agents_ID` int(11) NOT NULL,
  `customers_ID` int(11) NOT NULL,
  `casename` varchar(45) NOT NULL,
  `casediscription` varchar(100) NOT NULL,
  `resolved` tinyint(1) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_casetype_has_agents_agents1_idx` (`agents_ID`),
  KEY `fk_casetype_has_agents_casetype1_idx` (`casetype_ID`),
  KEY `fk_casetype_has_agents_customers1_idx` (`customers_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Data dump for tabellen `cases`
--

INSERT INTO `cases` (`ID`, `casetype_ID`, `agents_ID`, `customers_ID`, `casename`, `casediscription`, `resolved`, `date`) VALUES
(1, 1, 1, 1, 'update ram', 'client anders golfklub requests new ram', 1, '2017-05-10');

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `casetype`
--

CREATE TABLE IF NOT EXISTS `casetype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `priority` varchar(45) NOT NULL,
  `task_time` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Data dump for tabellen `casetype`
--

INSERT INTO `casetype` (`ID`, `type`, `priority`, `task_time`) VALUES
(1, 'hardware', 'critical', '50');

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(64) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Data dump for tabellen `customers`
--

INSERT INTO `customers` (`ID`, `company`, `username`, `password`, `email`) VALUES
(1, 'anders goflklub', 'anders', '1234', 'anders@andersgolfklub.dk');

--
-- Begrænsninger for dumpede tabeller
--

--
-- Begrænsninger for tabel `cases`
--
ALTER TABLE `cases`
  ADD CONSTRAINT `fk_casetype_has_agents_agents1` FOREIGN KEY (`agents_ID`) REFERENCES `agents` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_casetype_has_agents_casetype1` FOREIGN KEY (`casetype_ID`) REFERENCES `casetype` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_casetype_has_agents_customers1` FOREIGN KEY (`customers_ID`) REFERENCES `customers` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
