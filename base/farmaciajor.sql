-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-08-2020 a las 06:50:48
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.3.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmaciajor`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `usuario_a` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `f_nacimiento` date NOT NULL,
  `contraseña` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradas`
--

CREATE TABLE `entradas` (
  `id` bigint(15) NOT NULL,
  `id_producto` bigint(15) NOT NULL,
  `fecha_registro` date NOT NULL,
  `entradas` bigint(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_farmacia`
--

CREATE TABLE `historial_farmacia` (
  `numero` int(15) NOT NULL,
  `folio` int(15) NOT NULL,
  `id_producto` bigint(17) NOT NULL,
  `fecha` date NOT NULL,
  `cantidad` varchar(100) NOT NULL,
  `metodo_pago` varchar(50) NOT NULL,
  `descuento` float NOT NULL,
  `total` float NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `motivo` varchar(10000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `igualacion_inventario`
--

CREATE TABLE `igualacion_inventario` (
  `id_igualacion` bigint(13) NOT NULL,
  `id_producto` bigint(13) NOT NULL,
  `stock_sistema` bigint(13) NOT NULL,
  `stock_fisico` bigint(13) NOT NULL,
  `fechas` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` bigint(15) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `gramos` varchar(100) NOT NULL,
  `contenido` varchar(5) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `precio_proveedor` float NOT NULL,
  `precio_publico` float NOT NULL,
  `stock` int(10) NOT NULL,
  `motivo` varchar(10000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `descripcion`, `gramos`, `contenido`, `tipo`, `precio_proveedor`, `precio_publico`, `stock`, `motivo`) VALUES
(1, 'Retiro', 'Retiro', 'Retir', 'Selecciona...', 0, 0, 0, ''),
(2, 'Caja', 'Caja', 'Caja', 'Caja', 0, 0, 0, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prueba`
--

CREATE TABLE `prueba` (
  `id` int(10) NOT NULL,
  `id_producto` bigint(15) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `gramos` varchar(100) NOT NULL,
  `contenido` varchar(10) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `precio_publico` float NOT NULL,
  `stock` varchar(10) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `descuento` float DEFAULT NULL,
  `total` float NOT NULL,
  `usuario` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(100) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `f_nacimiento` date NOT NULL,
  `telefono` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `nombre`, `apellido`, `f_nacimiento`, `telefono`, `password`, `rol`) VALUES
(14, 'Jrodas', 'Jesus ', 'Rodas', '1998-10-22', 123456789, '5RXL9b/2oESJJSpQl9fUJg==', 'Cajero'),
(22, 'David03', 'Edgar David ', 'Rodas Ruiz', '2003-04-03', 9611736023, 'IRHhatCFrBo=', 'Administrador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entradas`
--
ALTER TABLE `entradas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `historial_farmacia`
--
ALTER TABLE `historial_farmacia`
  ADD PRIMARY KEY (`numero`,`folio`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `igualacion_inventario`
--
ALTER TABLE `igualacion_inventario`
  ADD PRIMARY KEY (`id_igualacion`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `prueba`
--
ALTER TABLE `prueba`
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_producto_2` (`id_producto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `entradas`
--
ALTER TABLE `entradas`
  MODIFY `id` bigint(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `historial_farmacia`
--
ALTER TABLE `historial_farmacia`
  MODIFY `numero` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- AUTO_INCREMENT de la tabla `igualacion_inventario`
--
ALTER TABLE `igualacion_inventario`
  MODIFY `id_igualacion` bigint(13) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entradas`
--
ALTER TABLE `entradas`
  ADD CONSTRAINT `entradas_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `historial_farmacia`
--
ALTER TABLE `historial_farmacia`
  ADD CONSTRAINT `historial_farmacia_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `igualacion_inventario`
--
ALTER TABLE `igualacion_inventario`
  ADD CONSTRAINT `igualacion_inventario_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `prueba`
--
ALTER TABLE `prueba`
  ADD CONSTRAINT `prueba_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
