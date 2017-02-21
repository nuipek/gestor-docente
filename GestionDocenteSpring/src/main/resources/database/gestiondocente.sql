-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-02-2017 a las 10:00:11
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnoCreate`(IN `papellidos` VARCHAR(250), 
IN `pcodigoPostal` INT(5), 
IN `pdireccion` VARCHAR(250), 
IN `pdni` VARCHAR(9), 
IN `pemail` VARCHAR(150), 
IN `pfNacimiento` DATE, 
IN `pnHermanos` INT, 
IN `pnombre` VARCHAR(50), 
IN `ppoblacion` VARCHAR(150), 
IN `ptelefono` INT(9), 
OUT `pcodigo` INT)
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

DROP PROCEDURE IF EXISTS `alumnogetByDni`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `alumnogetByDni`(IN `pdni` VARCHAR(9), IN `pcodigo` INT, OUT `presultado` INT)
    NO SQL
BEGIN
IF pcodigo != -1
THEN
SELECT COUNT(dni) into presultado FROM alumno 
WHERE dni = LOWER(pdni) AND codigo != pcodigo LIMIT 1;
ELSE
SELECT COUNT(dni) into presultado FROM alumno 
WHERE dni = LOWER(pdni) LIMIT 1;
END IF;


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

DROP PROCEDURE IF EXISTS `clientegetByIdentificativo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clientegetByIdentificativo`(IN `pidentificativo` VARCHAR(12), IN `pcodigo` INT, OUT `presultado` INT)
    NO SQL
BEGIN
IF pcodigo != -1
THEN
SELECT COUNT(identificativo) into presultado FROM cliente 
WHERE identificativo = LOWER(pidentificativo) AND 
codigo != pcodigo LIMIT 1;
ELSE
SELECT COUNT(identificativo) into presultado FROM cliente 
WHERE identificativo = LOWER(pidentificativo) LIMIT 1;
END IF;


END$$

DROP PROCEDURE IF EXISTS `clienteInforme`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clienteInforme`(IN pcodigo INT)
BEGIN

SELECT c.codigo, c.nombre, c.email, c.telefono, c.direccion, c.poblacion, 
       c.codigoPostal, c.identificativo, c.activo,
       cu.codigo as codigocurso,cu.nombre as nombrecurso, cu.identificador as identificadorcurso,
       cu.finicio, cu.ffin,cu.nhoras,SUM(i.precio) as preciocurso
FROM   cliente as c 
       LEFT JOIN curso as cu ON cu.cliente_codigo = c.codigo
	   INNER JOIN curso_modulo as cm ON cm.curso_codigo = cu.codigo
       INNER JOIN imparticion as i ON i.curso_modulo_codigo = cm.codigo_curso_modulo
WHERE  c.codigo = pcodigo;
/*GROUP BY cm.curso_codigo;*/


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
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorCreate`(IN `pnss` BIGINT(12), IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(250), IN `pfNacimiento` DATE, IN `pdni` VARCHAR(9), IN `pdireccion` VARCHAR(250), IN `ppoblacion` VARCHAR(150), IN `pCodigoPostal` INT(5), IN `ptelefono` INT(9), IN `pemail` VARCHAR(150), OUT `pcodigo` INT)
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

DROP PROCEDURE IF EXISTS `profesorgetByDni`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetByDni`(IN `pdni` VARCHAR(9), IN `pcodigo` INT, OUT `presultado` INT)
    NO SQL
BEGIN

IF pcodigo != -1
THEN
SELECT COUNT(dni) into presultado FROM profesor 
WHERE dni = LOWER(pdni) AND codigo != pcodigo LIMIT 1;
ELSE
SELECT COUNT(dni) into presultado FROM profesor 
WHERE dni = LOWER(pdni) LIMIT 1;
END IF;

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

DROP PROCEDURE IF EXISTS `profesorgetBynSS`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `profesorgetBynSS`(IN `pnSS` BIGINT(12), IN `pcodigo` INT, OUT `presultado` INT)
    NO SQL
BEGIN

IF pcodigo != -1 THEN 
SELECT COUNT(nSS) into presultado FROM profesor 
WHERE nSS = pnSS AND codigo != pcodigo LIMIT 1; 
ELSE SELECT COUNT(nSS) into presultado FROM profesor WHERE nSS = pnSS LIMIT 1; 
END IF; 


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
  `codigo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Campo clave de la tabla',
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `fNacimiento` date DEFAULT NULL,
  `direccion` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poblacion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigoPostal` int(5) DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL COMMENT 'El Dni es unico.',
  `nHermanos` int(2) DEFAULT '0',
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `dni_UNIQUE` (`dni`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`codigo`, `nombre`, `apellidos`, `fNacimiento`, `direccion`, `poblacion`, `codigoPostal`, `telefono`, `email`, `dni`, `nHermanos`, `activo`) VALUES
(0, 'alumno', 'sin asignar', NULL, NULL, NULL, NULL, 0, '', '00000000', 0, 1),
(1, 'sergio', 'aparicio vegas', '1977-12-01', 'tartanga 8f atico a', 'erandio', 48950, 944062586, 'coritelsergio@hotmail.com', '44974398z', 9, 1),
(8, 'sergio', 'aparicio', '1999-02-16', 'tartanga 8f', 'erandio', 48950, 944965217, 'coritelsergio@hotmail.com', '30694324l', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistente`
--

DROP TABLE IF EXISTS `asistente`;
CREATE TABLE IF NOT EXISTS `asistente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `alumno_codigo` int(11) DEFAULT NULL,
  `imparticion_codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_asistente_alumno_codigo_idx` (`alumno_codigo`),
  KEY `fk_asistente_imparticion_codigo_idx` (`imparticion_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
  `poblacion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigoPostal` int(5) unsigned zerofill DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` int(9) NOT NULL,
  `identificativo` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombre`, `apellidos`, `direccion`, `poblacion`, `codigoPostal`, `email`, `telefono`, `identificativo`, `activo`) VALUES
(1, 'patxi', 'patas cortas', 'escuela artes y oficios nº11', NULL, NULL, 'txoripavo@yahoo.es', 944965217, '30694324l', 1),
(2, 'yoni', 'macarroni', 'pavo pozo 26, 4a', NULL, NULL, 'macarroni@hotmail.es', 1, '44974396k', 1),
(4, 'sergio', 'aparicio', 'tartanga 8f', NULL, NULL, 'coritelsergio@hotmail.com', 944965217, '44974398z', 1),
(5, 'miliki', 'perez', 'tartanga 8f', NULL, NULL, 'fasfdfdsfsd@gfdsfgfds.com', 94, 'x1234567890', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `identificador` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `fInicio` date DEFAULT NULL,
  `fFin` date DEFAULT NULL,
  `nHoras` int(4) NOT NULL,
  `temario` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `cliente_codigo` int(11) DEFAULT NULL,
  `precio` double(8,2) DEFAULT '0.00',
  PRIMARY KEY (`codigo`),
  KEY `fk_curso_cliente_codigo_idx` (`cliente_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_detalle`
--

DROP TABLE IF EXISTS `curso_detalle`;
CREATE TABLE IF NOT EXISTS `curso_detalle` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `fInicio` date DEFAULT NULL,
  `fFin` date DEFAULT NULL,
  `Precio` double(7,2) DEFAULT '0.00',
  `curso_codigo` int(11) DEFAULT NULL,
  `modulo_codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `uq_curso_codigo_modulo_codigo` (`curso_codigo`,`modulo_codigo`),
  KEY `fk_curso_detalle_curso_codigo_idx` (`curso_codigo`),
  KEY `fk_curso_detalle_modulo_codigo_idx` (`modulo_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluacion`
--

DROP TABLE IF EXISTS `evaluacion`;
CREATE TABLE IF NOT EXISTS `evaluacion` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `imparticion_codigo` int(11) NOT NULL,
  `alumno_codigo` int(11) NOT NULL,
  `fExamen` date NOT NULL,
  `nota` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_alumno_idx` (`alumno_codigo`),
  KEY `fk_imparticion_idx` (`imparticion_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Evaluacion de la notas de los alumnos para cada modulo' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imparticion`
--

DROP TABLE IF EXISTS `imparticion`;
CREATE TABLE IF NOT EXISTS `imparticion` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `curso_detalle_codigo` int(11) DEFAULT NULL,
  `profesor_codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_imparticion_curso_detalle_idx` (`curso_detalle_codigo`),
  KEY `fk_imparticion_profesor_codigo_idx` (`profesor_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulo`
--

DROP TABLE IF EXISTS `modulo`;
CREATE TABLE IF NOT EXISTS `modulo` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nHoras` int(3) NOT NULL,
  `descripcion` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
  `codigoPostal` int(5) unsigned zerofill DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`codigo`, `nSS`, `nombre`, `apellidos`, `fNacimiento`, `dni`, `direccion`, `poblacion`, `codigoPostal`, `telefono`, `email`, `activo`) VALUES
(0, 0, 'profesor', 'sin asignar', NULL, '000000000', NULL, NULL, NULL, 0, '', 1),
(1, 987654321124, 'consuelo', 'mejia aranzabal', '1999-02-17', '30694324l', 'tartanga 8f ', 'erandio', 48950, 664384071, 'sapariciov@hotmail.com', 1),
(2, 987654321123, 'malika', 'snow zalabarria ', '1999-02-17', '44974398z', 'hidden hills', 'calabasas', 20001, 607118343, 'malika@correo.com', 1),
(3, 987654321125, 'sergio', 'aparicio', '1999-02-17', '14378424c', 'tartanga 8f', 'erandio', 00000, 944965217, 'coritelsergio@hotmail.com', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asistente`
--
ALTER TABLE `asistente`
  ADD CONSTRAINT `fk_asistente_alumno_codigo` FOREIGN KEY (`alumno_codigo`) REFERENCES `alumno` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_asistente_imparticion_codigo` FOREIGN KEY (`imparticion_codigo`) REFERENCES `imparticion` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `fk_curso_cliente_codigo` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `curso_detalle`
--
ALTER TABLE `curso_detalle`
  ADD CONSTRAINT `fk_curso_detalle_curso_codigo` FOREIGN KEY (`curso_codigo`) REFERENCES `curso` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_curso_detalle_modulo_codigo` FOREIGN KEY (`modulo_codigo`) REFERENCES `modulo` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  ADD CONSTRAINT `fk_evaluacion_alumno_codigo` FOREIGN KEY (`alumno_codigo`) REFERENCES `alumno` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_evaluacion_imparticion_codigo` FOREIGN KEY (`imparticion_codigo`) REFERENCES `imparticion` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `imparticion`
--
ALTER TABLE `imparticion`
  ADD CONSTRAINT `fk_imparticion_curso_detalle_codigo` FOREIGN KEY (`curso_detalle_codigo`) REFERENCES `curso_detalle` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_imparticion_profesor_codigo` FOREIGN KEY (`profesor_codigo`) REFERENCES `profesor` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
