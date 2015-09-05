-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Сен 05 2015 г., 22:38
-- Версия сервера: 5.7.8-rc-log
-- Версия PHP: 5.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `javaeechgu`
--

-- --------------------------------------------------------

--
-- Структура таблицы `dysciple`
--

CREATE TABLE IF NOT EXISTS `dysciple` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `direction` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `dysciple`
--

INSERT INTO `dysciple` (`id`, `name`, `direction`) VALUES
(1, 'Фізика', 'Природнича наука'),
(2, 'Українська мова', 'Мови'),
(3, 'КПП', 'Програмування');

-- --------------------------------------------------------

--
-- Структура таблицы `dysciple_teacher`
--

CREATE TABLE IF NOT EXISTS `dysciple_teacher` (
  `id` int(11) NOT NULL,
  `id_dysciple` int(11) NOT NULL,
  `id_teacher` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `dysciple_teacher`
--

INSERT INTO `dysciple_teacher` (`id`, `id_dysciple`, `id_teacher`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `teacher`
--

CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `second_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `birthday` char(10) NOT NULL,
  `degree` int(11) NOT NULL,
  `kafedra` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `teacher`
--

INSERT INTO `teacher` (`id`, `first_name`, `second_name`, `last_name`, `birthday`, `degree`, `kafedra`) VALUES
(1, 'Іван', 'Іванович', 'Іванов', '1982-01-04', 2, 5),
(2, 'Петро', 'Петрович', 'Петренко', '1974-10-11', 3, 6);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `dysciple`
--
ALTER TABLE `dysciple`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `dysciple_teacher`
--
ALTER TABLE `dysciple_teacher`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
