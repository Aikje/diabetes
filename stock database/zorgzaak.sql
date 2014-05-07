-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Machine: 127.0.0.1
-- Genereertijd: 07 mei 2014 om 13:35
-- Serverversie: 5.5.27
-- PHP-versie: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `zorgzaak`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `medewerker`
--

CREATE TABLE IF NOT EXISTS `medewerker` (
  `medewerker_id` int(11) NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(64) DEFAULT NULL,
  `achternaam` varchar(64) DEFAULT NULL,
  `wachtwoord` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`medewerker_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `meting`
--

CREATE TABLE IF NOT EXISTS `meting` (
  `datum` datetime NOT NULL,
  `bloedsuiker_gehalte` varchar(45) DEFAULT NULL,
  `commentaar` varchar(45) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`datum`),
  UNIQUE KEY `patient_id_UNIQUE` (`patient_id`),
  KEY `fk_meting_patient1_idx` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(64) DEFAULT NULL,
  `achternaam` varchar(64) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `wachtwoord` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Beperkingen voor gedumpte tabellen
--

--
-- Beperkingen voor tabel `meting`
--
ALTER TABLE `meting`
  ADD CONSTRAINT `fk_meting_patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
