-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 24 sep. 2026 à 11:10
-- Version du serveur : 11.7.1-MariaDB
-- Version de PHP : 8.5.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bank`
--

-- --------------------------------------------------------

--
-- Structure de la table `depot`
--

CREATE TABLE `depot` (
  `id_depot` int(11) NOT NULL,
  `date_depot` datetime DEFAULT NULL,
  `somme` decimal(15,2) NOT NULL,
  `numero_compte` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `depot`
--

INSERT INTO `depot` (`id_depot`, `date_depot`, `somme`, `numero_compte`, `description`) VALUES
(11, '2026-09-24 11:21:16', 100000.00, 'FR-0001-0001', NULL),
(12, '2026-09-24 11:21:43', 1800.00, 'FR-0002-0002', 'Paiement salaire'),
(13, '2026-09-24 11:22:13', 2000.00, 'FR-0003-0003', 'Paiement salaire');

-- --------------------------------------------------------

--
-- Structure de la table `retrait`
--

CREATE TABLE `retrait` (
  `id_retrait` int(11) NOT NULL,
  `date_retrait` datetime DEFAULT NULL,
  `somme` decimal(15,2) DEFAULT NULL,
  `numero_compte` varchar(20) NOT NULL,
  `id_depot` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `retrait`
--

INSERT INTO `retrait` (`id_retrait`, `date_retrait`, `somme`, `numero_compte`, `id_depot`) VALUES
(9, '2026-09-24 11:21:43', 1800.00, 'FR-0001-0001', 12),
(10, '2026-09-24 11:22:13', 2000.00, 'FR-0001-0001', 13),
(11, '2026-09-24 11:22:33', 500.00, 'FR-0003-0003', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `numero_compte` varchar(20) NOT NULL,
  `titulaire` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`numero_compte`, `titulaire`) VALUES
('FR-0001-0001', 'Entreprise'),
('FR-0002-0002', 'Alice Martin'),
('FR-0003-0003', 'Lucas Bernard'),
('FR-0004-0004', 'Emma Dubois'),
('FR-0005-0005', 'Hugo Petit'),
('FR-0006-0006', 'Chloe Moreau'),
('FR-0007-0007', 'Nathan Laurent'),
('FR-0008-0008', 'Lea Michel'),
('FR-0009-0009', 'Thomas Lefebvre'),
('FR-0010-0010', 'Julie Garcia'),
('FR-1212-1050', 'C\'est moi');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vue_details_transferts`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `vue_details_transferts` (
`numero_destinataire` varchar(20)
,`numero_origin` varchar(20)
,`montant` decimal(15,2)
,`description` varchar(50)
,`date_operation` datetime /* mariadb-5.3 */
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vue_solde_utilisateurs`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `vue_solde_utilisateurs` (
`numero_compte` varchar(20)
,`titulaire` varchar(50)
,`total_depots` decimal(37,2)
,`total_retraits` decimal(37,2)
,`total` decimal(38,2)
);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `depot`
--
ALTER TABLE `depot`
  ADD PRIMARY KEY (`id_depot`),
  ADD KEY `numero_compte` (`numero_compte`);

--
-- Index pour la table `retrait`
--
ALTER TABLE `retrait`
  ADD PRIMARY KEY (`id_retrait`),
  ADD KEY `numero_compte` (`numero_compte`),
  ADD KEY `fk_depot` (`id_depot`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`numero_compte`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `depot`
--
ALTER TABLE `depot`
  MODIFY `id_depot` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `retrait`
--
ALTER TABLE `retrait`
  MODIFY `id_retrait` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

-- --------------------------------------------------------

--
-- Structure de la vue `vue_details_transferts`
--
DROP TABLE IF EXISTS `vue_details_transferts`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vue_details_transferts`  AS SELECT `depot`.`numero_compte` AS `numero_destinataire`, `retrait`.`numero_compte` AS `numero_origin`, `depot`.`somme` AS `montant`, `depot`.`description` AS `description`, `depot`.`date_depot` AS `date_operation` FROM (`depot` left join `retrait` on(`retrait`.`id_depot` = `depot`.`id_depot`))union all select NULL AS `numero_destinataire`,`retrait`.`numero_compte` AS `numero_origin`,`retrait`.`somme` AS `montant`,NULL AS `description`,`retrait`.`date_retrait` AS `date_operation` from `retrait` where `retrait`.`id_depot` is null order by `date_operation` desc  ;

-- --------------------------------------------------------

--
-- Structure de la vue `vue_solde_utilisateurs`
--
DROP TABLE IF EXISTS `vue_solde_utilisateurs`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vue_solde_utilisateurs`  AS SELECT `u`.`numero_compte` AS `numero_compte`, `u`.`titulaire` AS `titulaire`, coalesce(`d`.`total_depot`,0) AS `total_depots`, coalesce(`r`.`total_retrait`,0) AS `total_retraits`, coalesce(`d`.`total_depot`,0) - coalesce(`r`.`total_retrait`,0) AS `total` FROM ((`utilisateur` `u` left join (select `depot`.`numero_compte` AS `numero_compte`,sum(`depot`.`somme`) AS `total_depot` from `depot` group by `depot`.`numero_compte`) `d` on(`d`.`numero_compte` = `u`.`numero_compte`)) left join (select `retrait`.`numero_compte` AS `numero_compte`,sum(`retrait`.`somme`) AS `total_retrait` from `retrait` group by `retrait`.`numero_compte`) `r` on(`r`.`numero_compte` = `u`.`numero_compte`)) ;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `depot`
--
ALTER TABLE `depot`
  ADD CONSTRAINT `depot_ibfk_1` FOREIGN KEY (`numero_compte`) REFERENCES `utilisateur` (`numero_compte`);

--
-- Contraintes pour la table `retrait`
--
ALTER TABLE `retrait`
  ADD CONSTRAINT `fk_depot` FOREIGN KEY (`id_depot`) REFERENCES `depot` (`id_depot`),
  ADD CONSTRAINT `retrait_ibfk_1` FOREIGN KEY (`numero_compte`) REFERENCES `utilisateur` (`numero_compte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
