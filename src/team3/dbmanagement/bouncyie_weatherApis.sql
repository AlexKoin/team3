-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 26, 2020 at 09:40 AM
-- Server version: 5.7.29
-- PHP Version: 7.3.6

drop database if exists weatherApis;
create database weatherApis;
use weatherApis;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bouncyie_weatherApis`
--

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `CityName` varchar(30) NOT NULL,
  `WeatherAPIARIS` varchar(150) NOT NULL,
  `WeatherAPICC` varchar(150) NOT NULL,
  `WeatherAPIDarkSky` varchar(150) NOT NULL,
  `WeatherAPIOWM` varchar(150) NOT NULL,
  `WeatherAPIWAPI` varchar(150) NOT NULL,
  `WeatherAPIWB` varchar(150) NOT NULL,
  `WeatherAPIWS` varchar(150) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`CityName`, `WeatherAPIARIS`, `WeatherAPICC`, `WeatherAPIDarkSky`, `WeatherAPIOWM`, `WeatherAPIWAPI`, `WeatherAPIWB`, `WeatherAPIWS`) VALUES
('Riga', 'riga,lv', 'lat=56.946&lon=24.105', 'none', 'Riga,lv', 'Riga', 'Riga,lv', 'Riga');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
