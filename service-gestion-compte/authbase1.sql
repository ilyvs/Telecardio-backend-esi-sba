-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 25 juil. 2021 à 04:36
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `authbase1`
--

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_Medecin'),
(2, 'ROLE_Patient'),
(3, 'ROLE_Admin'),
(4, 'ROLE_Infermier');

-- --------------------------------------------------------

--
-- Structure de la table `social_num`
--

CREATE TABLE `social_num` (
  `id` int(11) NOT NULL,
  `sin` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `social_num`
--

INSERT INTO `social_num` (`id`, `sin`) VALUES
(2, 111111111111),
(8, 160260172104),
(17, 168922274479),
(3, 221429220012),
(9, 221429220013),
(10, 221429220018),
(11, 221429220028),
(4, 221429220113),
(5, 221429220213),
(14, 298729299782),
(15, 318929271782),
(6, 331530240154),
(12, 411428625512),
(7, 441640350181),
(13, 451429339958),
(16, 451829319900),
(1, 555555555555);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `daten` datetime DEFAULT NULL,
  `num_tel` double DEFAULT NULL,
  `sin` double DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `verification_code` varchar(64) DEFAULT NULL,
  `sex` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `username`, `daten`, `num_tel`, `sin`, `enabled`, `verification_code`, `sex`) VALUES
(21, 'anfel@gmail.com', '$2a$10$VmUPwrW0r3PbrkMYLbIFselLoe.vAMCG3p51Xs5qhs.sqmn.uV0w2', 'anfel', '1997-07-07 00:00:00', 789110547, 221429220012, b'0', NULL, 'Femme'),
(22, 'lousra1234@gmail.com', '$2a$10$lkQl1iXtwaGHKmltFqoJR.UTeMzNBME8QjlCsK/zfp55LKVPL15uK', 'lousraissam029', '1999-07-07 00:00:00', 792734147, 441640350181, b'0', NULL, 'Homme'),
(24, 'test1@gmail.com', '$2a$10$4PgZeU6IV0N9AFbpBBHK8uYFxHLtqwx7Oya9RKfVmWxBYkYeK/TYK', 'test1', '1997-07-07 00:00:00', 789110547, 221429220013, b'0', NULL, 'Homme'),
(29, 'issamlousra@gmail.com', '$2a$10$q77J74VjJW6kULvre6ZEje9tOIH4lU43i5UqEZOc2gjq9ukJ1.efO', 'lousraissam', '1997-07-07 00:00:00', 789110547, 451429339958, b'1', NULL, 'Homme'),
(32, 'chaker.ls29@gmail.com', '$2a$10$l8a5RbFgsh5smu60arg2LeGL.UvlVYX6g2h08/ofuYpxUfvaGq8Ee', 'chaker', '2000-11-30 00:00:00', 795541753, 318929271782, b'1', NULL, 'Homme'),
(33, 'mostatouhami@gmail.com', '$2a$10$4TD1GcnlppJq5VSDQbhmBe0YuuHcuWdgU1JhGHLhH//pcPQ1pPEN.', 'touhame', '1963-08-01 00:00:00', 787896354, 451829319900, b'1', NULL, 'Homme'),
(34, 'mohamedlousra29@gmail.com', '$2a$10$l0RAwqhfhIJ4/khcm2fLluGzXKCnDHgJq5wgwhQdb19GPWtjgGiWG', 'mohamed', '1969-01-21 00:00:00', 666015631, 168922274479, b'1', NULL, 'Homme');

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(21, 4),
(22, 2),
(24, 2),
(29, 3),
(32, 2),
(33, 2),
(34, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `social_num`
--
ALTER TABLE `social_num`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6afmlmpt79nabcg21df6loa69` (`sin`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Index pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `social_num`
--
ALTER TABLE `social_num`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
