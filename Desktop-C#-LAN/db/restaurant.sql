-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 09 Ara 2020, 13:38:14
-- Sunucu sürümü: 10.4.13-MariaDB
-- PHP Sürümü: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `restaurant`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `choosenfoods`
--

CREATE TABLE `choosenfoods` (
  `id` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  `masaNumarasi` int(11) NOT NULL,
  `amount` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `credits`
--

CREATE TABLE `credits` (
  `creditId` int(11) NOT NULL,
  `personId` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `credits`
--

INSERT INTO `credits` (`creditId`, `personId`, `foodId`, `amount`) VALUES
(48, 1, 23, 12),
(52, 16, 28, 7);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `deleteditems`
--

CREATE TABLE `deleteditems` (
  `id` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  `masaNumarasi` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `deleteditems`
--

INSERT INTO `deleteditems` (`id`, `foodId`, `masaNumarasi`, `amount`, `datetime`) VALUES
(26, 28, 1, 2, '2020-11-18 05:20:00'),
(27, 26, 1, 3, '2020-11-18 05:20:00');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `foods`
--

CREATE TABLE `foods` (
  `foodId` int(32) NOT NULL,
  `foodName` varchar(12) NOT NULL,
  `foodPrice` varchar(12) NOT NULL,
  `Stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `foods`
--

INSERT INTO `foods` (`foodId`, `foodName`, `foodPrice`, `Stok`) VALUES
(24, 's', '2,75', 0),
(26, 't', '5,00', 15),
(27, '', '', 0),
(28, 'hıuhıu', '3,15', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `paying`
--

CREATE TABLE `paying` (
  `payId` int(11) NOT NULL,
  `personId` varchar(32) NOT NULL,
  `payValue` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `paying`
--

INSERT INTO `paying` (`payId`, `personId`, `payValue`) VALUES
(14, '16', '10');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `persons`
--

CREATE TABLE `persons` (
  `personId` int(11) NOT NULL,
  `personName` varchar(32) NOT NULL,
  `personSurname` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `persons`
--

INSERT INTO `persons` (`personId`, `personName`, `personSurname`) VALUES
(16, 'tufan', 'yurdakul');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `choosenfoods`
--
ALTER TABLE `choosenfoods`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `credits`
--
ALTER TABLE `credits`
  ADD PRIMARY KEY (`creditId`);

--
-- Tablo için indeksler `deleteditems`
--
ALTER TABLE `deleteditems`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`foodId`);

--
-- Tablo için indeksler `paying`
--
ALTER TABLE `paying`
  ADD PRIMARY KEY (`payId`);

--
-- Tablo için indeksler `persons`
--
ALTER TABLE `persons`
  ADD PRIMARY KEY (`personId`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `choosenfoods`
--
ALTER TABLE `choosenfoods`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- Tablo için AUTO_INCREMENT değeri `credits`
--
ALTER TABLE `credits`
  MODIFY `creditId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- Tablo için AUTO_INCREMENT değeri `deleteditems`
--
ALTER TABLE `deleteditems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Tablo için AUTO_INCREMENT değeri `foods`
--
ALTER TABLE `foods`
  MODIFY `foodId` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Tablo için AUTO_INCREMENT değeri `paying`
--
ALTER TABLE `paying`
  MODIFY `payId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Tablo için AUTO_INCREMENT değeri `persons`
--
ALTER TABLE `persons`
  MODIFY `personId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
