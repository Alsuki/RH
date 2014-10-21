/*
SQLyog Community v12.01 (64 bit)
MySQL - 5.6.21 : Database - rh
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rh` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rh`;

/*Table structure for table `account_lock` */

DROP TABLE IF EXISTS `account_lock`;

CREATE TABLE `account_lock` (
  `ID_Lock` int(11) NOT NULL AUTO_INCREMENT,
  `Account_Lock` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`ID_Lock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account_lock` */

/*Table structure for table `account_login` */

DROP TABLE IF EXISTS `account_login`;

CREATE TABLE `account_login` (
  `ID_Login` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Estado` int(1) NOT NULL,
  `ID_Lock` int(11) DEFAULT NULL,
  `Login` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `Last_Login` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Login`),
  KEY `account-lock` (`ID_Lock`),
  CONSTRAINT `account-lock` FOREIGN KEY (`ID_Lock`) REFERENCES `account_lock` (`ID_Lock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account_login` */

/*Table structure for table `cv` */

DROP TABLE IF EXISTS `cv`;

CREATE TABLE `cv` (
  `ID_CV` int(11) NOT NULL AUTO_INCREMENT,
  `captation` varchar(45) NOT NULL,
  `file` longblob NOT NULL,
  PRIMARY KEY (`ID_CV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cv` */

/*Table structure for table `doc` */

DROP TABLE IF EXISTS `doc`;

CREATE TABLE `doc` (
  `ID_Doc` int(11) NOT NULL AUTO_INCREMENT,
  `captation` varchar(45) NOT NULL,
  `file` longblob NOT NULL,
  PRIMARY KEY (`ID_Doc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `doc` */

/*Table structure for table `experencia` */

DROP TABLE IF EXISTS `experencia`;

CREATE TABLE `experencia` (
  `ID_Experencia` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Referencias` int(11) NOT NULL,
  `Funcao` varchar(100) DEFAULT NULL,
  `Onde` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID_Experencia`),
  CONSTRAINT `experiencia-referencia` FOREIGN KEY (`ID_Experencia`) REFERENCES `referencias` (`ID_Referencias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `experencia` */

/*Table structure for table `foto` */

DROP TABLE IF EXISTS `foto`;

CREATE TABLE `foto` (
  `ID_Foto` int(11) NOT NULL AUTO_INCREMENT,
  `captation` varchar(45) NOT NULL,
  `img` longblob NOT NULL,
  PRIMARY KEY (`ID_Foto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `foto` */

/*Table structure for table `hobbies` */

DROP TABLE IF EXISTS `hobbies`;

CREATE TABLE `hobbies` (
  `ID_Hobbie` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`ID_Hobbie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hobbies` */

/*Table structure for table `identificacao` */

DROP TABLE IF EXISTS `identificacao`;

CREATE TABLE `identificacao` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) DEFAULT NULL,
  `Funcao` varchar(50) DEFAULT NULL,
  `Avaliacao` int(1) DEFAULT NULL,
  `BI` int(11) NOT NULL,
  `NIF` int(11) NOT NULL,
  `NSS` int(11) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Idade` date NOT NULL,
  `ID_Morada` int(11) NOT NULL,
  `ID_Tel` int(11) NOT NULL,
  `ID_Foto` int(11) NOT NULL,
  `ID_Experiencia` int(11) NOT NULL,
  `ID_CV` int(11) NOT NULL,
  `ID_Hobbie` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `identificacao-morada` (`ID_Morada`),
  KEY `identificacao-tel` (`ID_Tel`),
  KEY `identificacao-foto` (`ID_Foto`),
  KEY `identificacao-cv` (`ID_CV`),
  KEY `identificacao-hobbies` (`ID_Hobbie`),
  KEY `identificacao-experiencia` (`ID_Experiencia`),
  CONSTRAINT `identificacao-cv` FOREIGN KEY (`ID_CV`) REFERENCES `cv` (`ID_CV`),
  CONSTRAINT `identificacao-experiencia` FOREIGN KEY (`ID_Experiencia`) REFERENCES `experencia` (`ID_Experencia`),
  CONSTRAINT `identificacao-foto` FOREIGN KEY (`ID_Foto`) REFERENCES `foto` (`ID_Foto`),
  CONSTRAINT `identificacao-hobbies` FOREIGN KEY (`ID_Hobbie`) REFERENCES `hobbies` (`ID_Hobbie`),
  CONSTRAINT `identificacao-morada` FOREIGN KEY (`ID_Morada`) REFERENCES `morada` (`ID_Morada`),
  CONSTRAINT `identificacao-tel` FOREIGN KEY (`ID_Tel`) REFERENCES `tel` (`ID_Tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `identificacao` */

/*Table structure for table `localidade` */

DROP TABLE IF EXISTS `localidade`;

CREATE TABLE `localidade` (
  `ID_Localidade` int(11) NOT NULL AUTO_INCREMENT,
  `Cod_Postal` varchar(8) NOT NULL,
  `Localidade` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_Localidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `localidade` */

/*Table structure for table `morada` */

DROP TABLE IF EXISTS `morada`;

CREATE TABLE `morada` (
  `ID_Morada` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Localidade` int(11) DEFAULT NULL,
  `Morada` varchar(255) NOT NULL,
  `Destrito` varchar(20) DEFAULT NULL,
  `País` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_Morada`),
  KEY `morada-localidade` (`ID_Localidade`),
  CONSTRAINT `morada-localidade` FOREIGN KEY (`ID_Localidade`) REFERENCES `localidade` (`ID_Localidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `morada` */

/*Table structure for table `referencias` */

DROP TABLE IF EXISTS `referencias`;

CREATE TABLE `referencias` (
  `ID_Referencias` int(11) NOT NULL AUTO_INCREMENT,
  `Referenciador` varchar(100) NOT NULL,
  `ID_Doc` int(11) NOT NULL,
  PRIMARY KEY (`ID_Referencias`),
  KEY `referencia-doc` (`ID_Doc`),
  CONSTRAINT `referencia-doc` FOREIGN KEY (`ID_Doc`) REFERENCES `doc` (`ID_Doc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `referencias` */

/*Table structure for table `tel` */

DROP TABLE IF EXISTS `tel`;

CREATE TABLE `tel` (
  `ID_Tel` int(11) NOT NULL AUTO_INCREMENT,
  `N_Tel` int(11) NOT NULL,
  PRIMARY KEY (`ID_Tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tel` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

