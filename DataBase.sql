-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 06 juin 2023 à 19:02
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hospital`
--
CREATE DATABASE IF NOT EXISTS `hospital` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `hospital`;

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`admin_id`, `email`, `username`, `password`) VALUES
(1, 'ooo', 'ooooooo', 'iiiiiiiiiiiiiiii'),
(2, 'kkkkkkk', 'hhhhh', 'jjjjjjjjjjjjjj'),
(3, 'gggg', 'ggggg', 'hhhhhkkkkkkk'),
(4, 'jjhssh', 'uuuu', 'ooooooooo'),
(5, 'hhhh', 'jjjjjjjjjjjjjjjj', 'kkkkkkkllll;;;;;;;'),
(6, 'jjj', 'hhhhhhhhhhhhhh', 'uuuuuuuuuuuuuuu'),
(7, 'jjj', 'hhhh', '99999999999999'),
(8, 'jdjdjdj', 'gggghgh', 'jkkkkkkkkkkkk'),
(9, 'aaaa', 'aaaa', '12345678');

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE IF NOT EXISTS `appointment` (
  `app_id` int NOT NULL AUTO_INCREMENT,
  `appointment_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `patient_name` varchar(50) NOT NULL,
  `doctor_id` int NOT NULL,
  `doctor_name` varchar(50) NOT NULL,
  `treatment` varchar(500) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(20) NOT NULL,
  PRIMARY KEY (`app_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `appointment`
--

INSERT INTO `appointment` (`app_id`, `appointment_id`, `patient_id`, `patient_name`, `doctor_id`, `doctor_name`, `treatment`, `date`, `time`) VALUES
(2, 1, 124, 'lwwl lslsl', 124, 'lllllllllll hhhhhhhhh', 'hhhhhhhhhhh', '2023-06-01', '9h - 10h'),
(4, 2, 124, 'lwwl lslsl', 33, 'nnnnnnnnnn mmmmmmmmmmm', 'bbbbbbbbbbbbb', '2023-06-02', '9h - 10h');

-- --------------------------------------------------------

--
-- Structure de la table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `doctor_id` int NOT NULL AUTO_INCREMENT,
  `doctor_number` int NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `specialization` varchar(100) NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `doctor`
--

INSERT INTO `doctor` (`doctor_id`, `doctor_number`, `first_name`, `last_name`, `specialization`, `contact_number`, `email_address`) VALUES
(2, 123, 'jjj', 'hhhhhhhhh', 'gggggggg', 'fffffff', 'dddddddd'),
(3, 124, 'lllllllllll', 'hhhhhhhhh', 'gggggggg', 'fffffff', 'dddddddd'),
(4, 33, 'nnnnnnnnnn', 'mmmmmmmmmmm', 'l;;;;;;;;;;;;;;;', 'uuuuuuuuu', 'oooooooooo');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `patient_number` int NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `age` int NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`patient_id`, `patient_number`, `first_name`, `last_name`, `age`, `contact_number`, `address`) VALUES
(3, 124, 'lwwl', 'lslsl', 9, '922222222', 'kjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
