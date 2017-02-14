-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-02-2017 a las 13:27:04
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `gestiondocente`
--
CREATE DATABASE IF NOT EXISTS `gestiondocente` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `gestiondocente`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `alumnoCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoCreate`(IN `papellidos` VARCHAR(250), IN `pcodigoPostal` INT(5), IN `pdireccion` VARCHAR(250), IN `pdni` VARCHAR(9), IN `pemail` VARCHAR(150), IN `pfNacimiento` DATE, IN `pnHermanos` INT, IN `pnombre` VARCHAR(50), IN `ppoblacion` VARCHAR(150), IN `ptelefono` INT(9), OUT `pcodigo` INT)
    NO SQL
BEGIN

INSERT INTO alumno(nombre,apellidos,dni,email,direccion,codigoPostal,poblacion,fNacimiento,telefono,nHermanos)
VALUES(LOWER(pnombre),LOWER(papellidos),LOWER(pdni),LOWER(pemail),LOWER(pdireccion),pcodigoPostal,
       LOWER(ppoblacion),pfNacimiento,ptelefono,pnHermanos);
       
SET pcodigo = LAST_INSERT_ID();

END$$

DROP PROCEDURE IF EXISTS `alumnoDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoDelete`(IN `pcodigo` INT)
    NO SQL
BEGIN

DELETE FROM Alumno WHERE codigo = pCodigo;

END$$

DROP PROCEDURE IF EXISTS `alumnogetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetAll`()
    NO SQL
BEGIN
SELECT   `codigo`, `nombre`, `apellidos`, `fNacimiento`
, `direccion`, `poblacion`, `codigoPostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo`
		
FROM alumno;
END$$

DROP PROCEDURE IF EXISTS `alumnogetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetById`(IN `pCodigo` INT)
    NO SQL
BEGIN

SELECT `codigo`, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigoPostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo`
FROM alumno
WHERE codigo = pCodigo;

END$$

DROP PROCEDURE IF EXISTS `alumnoUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoUpdate`(IN `papellidos` VARCHAR(250), IN `pcodigo` INT, IN `pcodigoPostal` INT(5), IN `pdireccion` VARCHAR(250), IN `pdni` VARCHAR(9), IN `pemail` VARCHAR(150), IN `pfNacimiento` DATE, IN `pnHermanos` INT, IN `pnombre` VARCHAR(50), IN `ppoblacion` VARCHAR(150), IN `ptelefono` INT(9))
    NO SQL
BEGIN

UPDATE alumno SET nombre = LOWER(pnombre), apellidos = LOWER(papellidos),
				   dni = LOWER(pdni), email = LOWER(pemail), direccion = 					   LOWER(pdireccion), codigoPostal = pcodigoPostal,
                   poblacion = LOWER(ppoblacion), fNacimiento = pfNacimiento,
                   telefono = ptelefono,nHermanos = pnHermanos
WHERE codigo = pcodigo;


END$$

DROP PROCEDURE IF EXISTS `clienteCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteCreate`(IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(150), IN `pdireccion` VARCHAR(250), IN `pemail` VARCHAR(100), IN `ptelefono` INT(9), IN `pidentificativo` VARCHAR(12), OUT `pcodigo` INT)
    NO SQL
BEGIN

INSERT INTO `cliente`(`nombre`, `apellidos`, `direccion`, `email`, `telefono`, `identificativo`) 
VALUES (LOWER(pnombre),LOWER(papellidos),LOWER(pdireccion),LOWER(pemail),ptelefono,LOWER(pidentificativo));

SET pcodigo = LAST_INSERT_ID();        

END$$

DROP PROCEDURE IF EXISTS `clienteDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteDelete`(IN `pcodigo` INT)
    NO SQL
BEGIN

DELETE FROM cliente WHERE codigo = pcodigo;

END$$

DROP PROCEDURE IF EXISTS `clientegetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetAll`()
    NO SQL
BEGIN

SELECT `codigo`, `nombre`, `apellidos`, `direccion`, `email`, `telefono`, `identificativo`, `activo` FROM `cliente`;

END$$

DROP PROCEDURE IF EXISTS `clientegetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetById`(IN `pcodigo` INT)
    NO SQL
BEGIN

SELECT `codigo`, `nombre`, `apellidos`, `direccion`, `email`, `telefono`, 		   `identificativo`, `activo`
FROM `cliente` 
WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `clienteUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteUpdate`(IN `pcodigo` INT, IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(150), IN `pdireccion` VARCHAR(250), IN `pemail` VARCHAR(100), IN `ptelefono` INT(9), IN `pidentificativo` VARCHAR(12))
    NO SQL
BEGIN

UPDATE cliente 
SET nombre = LOWER(pnombre), apellidos = LOWER(papellidos),
	direccion = LOWER(pdireccion), email = LOWER(pemail),
    telefono = ptelefono, identificativo = pidentificativo
WHERE codigo = pcodigo;


END$$

DROP PROCEDURE IF EXISTS `profesorCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorCreate`(IN `pnss` BIGINT(12), IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(250), IN `pfNacimiento` DATE, IN `pdni` VARCHAR(9), IN `pdireccion` VARCHAR(250), IN `ppoblacion` VARCHAR(150), IN `pCodigoPostal` INT(5), IN `ptelefono` INT(9), IN `pemail` INT(150), OUT `pcodigo` INT)
    NO SQL
BEGIN

INSERT INTO `profesor`
(`nSS`, `nombre`, `apellidos`, `fNacimiento`, `dni`, `direccion`, `poblacion`, `codigoPostal`, `telefono`, `email`) 
VALUES 
(pnSS,LOWER(pnombre),LOWER(papellidos),pfNacimiento,LOWER(pdni),LOWER(pdireccion),LOWER(ppoblacion),pcodigoPostal,ptelefono,LOWER(pemail));

set pcodigo = LAST_INSERT_ID();

END$$

DROP PROCEDURE IF EXISTS `profesorDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorDelete`(IN `pcodigo` INT)
    NO SQL
BEGIN

DELETE  FROM profesor WHERE codigo = pcodigo;

END$$

DROP PROCEDURE IF EXISTS `profesorgetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetAll`()
    NO SQL
BEGIN

SELECT `codigo`, `nSS`, `nombre`, `apellidos`, `fNacimiento`, `dni`, 		          `direccion`, `poblacion`, `codigoPostal`, `telefono`, 
        `email`, `activo` 
FROM `profesor`;

END$$

DROP PROCEDURE IF EXISTS `profesorgetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetById`(IN `pcodigo` INT)
    NO SQL
BEGIN

SELECT `codigo`, `nSS`, `nombre`, `apellidos`, `fNacimiento`, `dni`,                  `direccion`, `poblacion`, `codigoPostal`, `telefono`, 
       `email`, `activo` 
FROM `profesor` 
WHERE codigo = pcodigo;

END$$

DROP PROCEDURE IF EXISTS `profesorUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorUpdate`(IN `pcodigo` INT, IN `pnss` BIGINT(12), IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(250), IN `pfNacimiento` DATE, IN `pdni` VARCHAR(9), IN `pdireccion` VARCHAR(250), IN `ppoblacion` VARCHAR(150), IN `pcodigoPostal` INT(5), IN `ptelefono` INT(9), IN `pemail` VARCHAR(150))
    NO SQL
BEGIN

UPDATE profesor
SET nSS = pnss, nombre = LOWER(pnombre),
    apellidos =LOWER(papellidos),fNacimiento = pfNacimiento,
	dni = LOWER(pdni),direccion = LOWER(pdireccion), 
    poblacion = LOWER(ppoblacion),
	codigoPostal = pcodigoPostal, telefono = ptelefono, email = LOWER(pemail)
WHERE codigo = pcodigo;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

DROP TABLE IF EXISTS `alumno`;
CREATE TABLE IF NOT EXISTS `alumno` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `fNacimiento` date DEFAULT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigoPostal` int(5) DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `nHermanos` int(2) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`codigo`, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigoPostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo`) VALUES
(1, 'sergio', 'aparicio vega', '1977-12-01', 'tartanga 8f atico a', 'erandiuo', 48950, 944062586, 'coritelsergio@hotmail.com', '44974398z', 3, 1),
(6, 'sergio', 'aparicio', '1999-02-14', 'tartanga 8f', 'erandio', 48950, 944965217, 'coritelsergio@hotmail.com', '44974398z', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` int(9) NOT NULL,
  `identificativo` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombre`, `apellidos`, `direccion`, `email`, `telefono`, `identificativo`, `activo`) VALUES
(1, 'Patxi', 'Patas Cortas', 'Escuela artes y oficios nº11', 'txoripavo@yahoo.es', 0, '30694324L', 1),
(2, 'Yoni', 'Macarroni', 'Pavo pozo 26, 4A', 'macarroni@hotmail.es', 0, '44974396K', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

DROP TABLE IF EXISTS `profesor`;
CREATE TABLE IF NOT EXISTS `profesor` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nSS` bigint(12) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `fNacimiento` date DEFAULT NULL,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigoPostal` int(5) DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`codigo`, `nSS`, `nombre`, `apellidos`, `fNacimiento`, `dni`, `direccion`, `poblacion`, `codigoPostal`, `telefono`, `email`, `activo`) VALUES
(1, 123456789321, 'Consuelo', 'Mejia Aranzabal', '1919-02-04', '30694324L', 'Tartanga 8F', 'Erandio', 48950, 664384071, 'sapariciov@hotmail.com', 1),
(2, 987654321123, 'Malika', 'Snow Zalabarria', '1994-05-05', '45678932V', 'Hidden Hills SN', 'Calabasas', 20001, 607118343, 'malika@correo.com', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
