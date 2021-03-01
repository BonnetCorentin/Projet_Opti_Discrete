-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 11 fév. 2020 à 15:31
-- Version du serveur :  10.1.26-MariaDB
-- Version de PHP :  7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `centreequestre`
--

-- --------------------------------------------------------

--
-- Structure de la table `box`
--

CREATE TABLE `box` (
  `BOXRef` varchar(50) NOT NULL,
  `BOXSurface` varchar(50) NOT NULL,
  `BOXTypeAbreuvoir` int(11) NOT NULL,
  `chevalmatricule` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `box`
--

INSERT INTO `box` (`BOXRef`, `BOXSurface`, `BOXTypeAbreuvoir`, `chevalmatricule`) VALUES
('BX001', 'Baignoire', 8, NULL),
('BX002', 'Bassine', 6, NULL),
('BX003', 'Baignoire', 10, NULL),
('BX004', 'Poubelle', 8, NULL),
('BX005', 'Baignoire', 12, NULL),
('BX006', 'Baignoire', 12, NULL),
('BX007', 'Bassin', 15, NULL),
('BX008', 'Bassin double', 20, NULL),
('BX009', 'Bassin', 18, NULL),
('BX010', 'Baignoire', 16, NULL),
('BX011', 'Poubelle', 8, NULL),
('BX012', 'Poubelle', 7, NULL),
('BX013', 'Baignoire', 16, NULL),
('BX014', 'Poubelle', 12, NULL),
('BX015', 'Baignoire', 10, NULL),
('BX016', 'Bassin double', 20, NULL),
('BX017', 'Bassin', 14, NULL),
('BX018', 'Baignoire', 15, NULL),
('BX019', 'Baignoire', 17, NULL),
('BX020', 'Baignoire', 19, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `cavalier`
--

CREATE TABLE `cavalier` (
  `CAVALIERNum` varchar(50) NOT NULL,
  `CAVALIERNom` varchar(50) NOT NULL,
  `CAVALIERPrenom` varchar(50) NOT NULL,
  `CAVALIERExamen` varchar(50) NOT NULL,
  `CAVALIERAdresse` varchar(50) NOT NULL,
  `CAVALIERCp` varchar(50) NOT NULL,
  `CAVALIERVille` varchar(50) NOT NULL,
  `CAVALIERTel` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cavalier`
--

INSERT INTO `cavalier` (`CAVALIERNum`, `CAVALIERNom`, `CAVALIERPrenom`, `CAVALIERExamen`, `CAVALIERAdresse`, `CAVALIERCp`, `CAVALIERVille`, `CAVALIERTel`) VALUES
('CA001', 'Guillaumond', 'Bertrand', 'Galop 2', '85 rue des Charmettes', '69100', 'Villeurbanne', '04-78-24-27-70'),
('CA002', 'Hannaizi', 'Myriam', 'Galop 2', '25 Rue Beethoven', '69200', 'Vénissieux', '04-72-50-42-24'),
('CA003', 'Janin', 'Andy', 'Galop 4', '11 Lot. Le Hameau', '38300', 'Les Epasses', '04-74-92-06-47'),
('CA004', 'La Fay', 'Edouard', 'Galop 4', '14 Chemin de Tauleret', '01700', 'Neyron', '04-78-55-15-06'),
('CA005', 'Pradier', 'Franck', 'Galop 7', '306 route de Lyon', '01640', 'Saint Jean le Vieux', '04-24-36-85-48'),
('CA006', 'Pennes', 'Romain', 'Galop 4', '4 Allée des Fourettes', '69380', 'Dommartin', '04-72-54-56-35'),
('CA007', 'Perrod', 'Geoffray', 'Galop 7', '16 Rue Victor Hugo', '69002', 'Lyon', '04-78-47-69-05'),
('CA008', 'Ralliere', 'Quentin', 'Galop 1', '132 Quai Pierre Dupont', '69270', 'Rochetaillée sur Saone', '04-72-27-88-27'),
('CA009', 'Ressy', 'Cyril', 'Galop 1', '23 Quai Gailleton', '69002', 'Lyon', '04-78-42-35-25'),
('CA010', 'Righy', 'Pierrick', 'Galop 7', '9 Rue de la Gare', '69890', 'La Tour de Salvagny', '04-78-48-03-04'),
('CA011', 'Achard', 'Alexandre', 'Galop 7', '19 place de la Ferrandiere', '69003', 'Lyon', '04-72-34-69-80'),
('CA012', 'Badji', 'Mustapha', 'Galop 4', '153 bis Rue Joliot Curie', '69005', 'Lyon', '04-26-00-27-74'),
('CA013', 'Bazin', 'Nicolas', 'Galop 1', '85 Av. Hélène de Tournon', '07300', 'Tournon sur Rhône', '04-75-07-11-97'),
('CA014', 'Bonete', 'Aurélien', 'Galop 2', '8 Rue de l\'abondance', '69003', 'Lyon', '04-72-61-99-62'),
('CA015', 'Bonnamy', 'Aurélien', 'Galop 7', '666 Rue Notre Dame des Champs', '01480', 'Jassans Riottier', '04-74-09-80-40');

-- --------------------------------------------------------

--
-- Structure de la table `cheval`
--

CREATE TABLE `cheval` (
  `CHEVALMatricule` varchar(50) NOT NULL,
  `BOXRef` varchar(50) NOT NULL,
  `PRORPIONum` varchar(50) NOT NULL,
  `CHEVALNom` varchar(50) NOT NULL,
  `CHEVALSex` varchar(2) NOT NULL,
  `CHEVALRobe` varchar(50) NOT NULL,
  `CHEVALRace` varchar(50) NOT NULL,
  `CHEVALDateNaissance` date DEFAULT NULL,
  `CavalierNum` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cheval`
--

INSERT INTO `cheval` (`CHEVALMatricule`, `BOXRef`, `PRORPIONum`, `CHEVALNom`, `CHEVALSex`, `CHEVALRobe`, `CHEVALRace`, `CHEVALDateNaissance`, `CavalierNum`) VALUES
('CH001', 'BX001', 'PO001', 'Tagada', 'M', 'Noire', 'Andalou', '0000-00-00', NULL),
('CH002', 'BX002', 'PO001', 'Sundis', 'M', 'Grise', 'Camargais', '0000-00-00', NULL),
('CH003', 'BX003', 'PO002', 'Caramel', 'M', 'Chocolat', 'Pure-Race Espagnole', '0000-00-00', NULL),
('CH004', 'BX004', 'PO003', 'Jolly Jumper', 'M', 'Chocolat', 'Pur-Sang Arabe', '0000-00-00', NULL),
('CH005', 'BX005', 'PO004', 'Gros Sabot', 'M', 'Noire', 'Percheron', '0000-00-00', NULL),
('CH006', 'BX006', 'PO004', 'Pegaze', 'M', 'Rousse', 'Pur-Sang Anglais', '0000-00-00', NULL),
('CH007', 'BX007', 'PO005', 'Osiris', 'F', 'Noire', 'Andalou', '0000-00-00', NULL),
('CH008', 'BX008', 'PO006', 'Asterix', 'M', 'Noire', 'Pur-Sang Arabe', '0000-00-00', NULL),
('CH009', 'BX009', 'PO007', 'Obelix', 'M', 'Noire', 'Pur-Sang Arabe', '0000-00-00', NULL),
('CH010', 'BX010', 'PO008', 'Jimmy', 'M', 'Grise', 'Camargais', '0000-00-00', NULL),
('CH011', 'BX011', 'PO009', 'Deesse', 'F', 'Noire', 'Pur-Sang Arabe', '0000-00-00', NULL),
('CH012', 'BX012', 'PO010', 'Clochette', 'F', 'Rousse', 'Pur-Sang Arabe', '0000-00-00', NULL),
('CH013', 'BX013', 'PO010', 'Ecu', 'M', 'Chocolat', 'Andalou', '0000-00-00', NULL),
('CH014', 'BX014', 'PO010', 'Kit', 'M', 'Grise', 'Camargais', '0000-00-00', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `moniteur`
--

CREATE TABLE `moniteur` (
  `MONITEURCode` varchar(50) NOT NULL,
  `MONITEURNom` varchar(50) NOT NULL,
  `MONITEURPrenom` varchar(50) NOT NULL,
  `MONITEURGrade` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `moniteur`
--

INSERT INTO `moniteur` (`MONITEURCode`, `MONITEURNom`, `MONITEURPrenom`, `MONITEURGrade`) VALUES
('MO001', 'Martin', 'Yves', 'BEES 1'),
('MO002', 'Dupond', 'Franck', 'MF 2'),
('MO003', 'Durand', 'Philippe', 'MF 3'),
('MO004', 'Testevuide', 'Cedric', 'BEES 2'),
('MO005', 'Vergne', 'Sébastien', 'MF 3'),
('MO006', 'Vincent', 'Guillaume', 'BEES 1'),
('MO007', 'Astier', 'Agnès', 'BEES 1'),
('MO008', 'Ceulemans', 'Eglantine', 'BEES 2'),
('MO009', 'Lechemia', 'Quentin', 'BEES 2'),
('MO010', 'Paour', 'Adrien', 'MF 1');

-- --------------------------------------------------------

--
-- Structure de la table `monter`
--

CREATE TABLE `monter` (
  `REPRISENum` varchar(50) NOT NULL,
  `CAVALIERNum` varchar(50) NOT NULL,
  `MAvis` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `monter`
--

INSERT INTO `monter` (`REPRISENum`, `CAVALIERNum`, `MAvis`) VALUES
('RE001', 'CA001', 'Favorable'),
('RE001', 'CA002', 'tres Favorable'),
('RE001', 'CA003', 'reserve'),
('RE001', 'CA004', 'Favorable'),
('RE001', 'CA005', 'tres Favorable'),
('RE001', 'CA006', 'Favorable'),
('RE001', 'CA007', 'tres Favorable'),
('RE002', 'CA008', 'Favorable'),
('RE002', 'CA009', 'tres Favorable'),
('RE002', 'CA010', 'reserve'),
('RE002', 'CA011', 'Favorable'),
('RE002', 'CA012', 'tres Favorable'),
('RE002', 'CA013', 'Favorable'),
('RE002', 'CA014', 'tres Favorable'),
('RE003', 'CA002', 'tres Favorable'),
('RE003', 'CA003', 'reserve'),
('RE003', 'CA004', 'Favorable'),
('RE003', 'CA005', 'tres Favorable'),
('RE003', 'CA006', 'Favorable'),
('RE003', 'CA007', 'tres Favorable'),
('RE003', 'CA015', 'Favorable'),
('RE004', 'CA008', 'Favorable'),
('RE004', 'CA009', 'tres Favorable'),
('RE004', 'CA010', 'reserve'),
('RE004', 'CA011', 'Favorable'),
('RE004', 'CA012', 'tres Favorable'),
('RE004', 'CA013', 'Favorable'),
('RE004', 'CA014', 'tres Favorable'),
('RE005', 'CA001', 'Favorable'),
('RE005', 'CA002', 'tres Favorable'),
('RE005', 'CA003', 'reserve'),
('RE005', 'CA004', 'Favorable'),
('RE005', 'CA005', 'tres Favorable'),
('RE005', 'CA006', 'Favorable'),
('RE005', 'CA007', 'tres Favorable'),
('RE006', 'CA001', 'Favorable'),
('RE006', 'CA002', 'tres Favorable'),
('RE006', 'CA003', 'reserve'),
('RE006', 'CA004', 'Favorable'),
('RE006', 'CA005', 'tres Favorable'),
('RE006', 'CA006', 'Favorable'),
('RE006', 'CA007', 'tres Favorable'),
('RE007', 'CA008', 'Favorable'),
('RE007', 'CA009', 'tres Favorable'),
('RE007', 'CA010', 'reserve'),
('RE007', 'CA011', 'Favorable'),
('RE007', 'CA012', 'tres Favorable'),
('RE007', 'CA013', 'Favorable'),
('RE007', 'CA014', 'tres Favorable'),
('RE008', 'CA002', 'tres Favorable'),
('RE008', 'CA003', 'reserve'),
('RE008', 'CA004', 'Favorable'),
('RE008', 'CA005', 'tres Favorable'),
('RE008', 'CA006', 'Favorable'),
('RE008', 'CA007', 'tres Favorable'),
('RE008', 'CA015', 'Favorable'),
('RE009', 'CA008', 'Favorable'),
('RE009', 'CA009', 'tres Favorable'),
('RE009', 'CA010', 'reserve'),
('RE009', 'CA011', 'Favorable'),
('RE009', 'CA012', 'tres Favorable'),
('RE009', 'CA013', 'Favorable'),
('RE009', 'CA014', 'tres Favorable'),
('RE010', 'CA001', 'Favorable'),
('RE010', 'CA002', 'tres Favorable'),
('RE010', 'CA003', 'reserve'),
('RE010', 'CA004', 'Favorable'),
('RE010', 'CA005', 'tres Favorable'),
('RE010', 'CA006', 'Favorable'),
('RE010', 'CA007', 'tres Favorable'),
('RE011', 'CA002', 'tres Favorable'),
('RE011', 'CA003', 'reserve'),
('RE011', 'CA004', 'Favorable'),
('RE011', 'CA005', 'tres Favorable'),
('RE011', 'CA006', 'Favorable'),
('RE011', 'CA007', 'tres Favorable'),
('RE011', 'CA015', 'Favorable');

-- --------------------------------------------------------

--
-- Structure de la table `participe`
--

CREATE TABLE `participe` (
  `RepriseNum` varchar(50) NOT NULL,
  `chevalmatricule` varchar(50) NOT NULL,
  `appreciation` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `proprietaire`
--

CREATE TABLE `proprietaire` (
  `PROPRIONum` varchar(50) NOT NULL,
  `PROPRIONom` varchar(50) NOT NULL,
  `PROPRIOPrenom` varchar(50) NOT NULL,
  `PROPRIOAdresse` varchar(50) NOT NULL,
  `PROPRIOCp` varchar(50) NOT NULL,
  `PROPRIOVille` varchar(50) NOT NULL,
  `PROPRIOTel` varchar(50) NOT NULL,
  `PRORPIOMail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `proprietaire`
--

INSERT INTO `proprietaire` (`PROPRIONum`, `PROPRIONom`, `PROPRIOPrenom`, `PROPRIOAdresse`, `PROPRIOCp`, `PROPRIOVille`, `PROPRIOTel`, `PRORPIOMail`) VALUES
('PO001', 'Achard', 'Alexandre', '19 place de la Ferrandiere', '69003', 'Lyon', '04-72-34-69-80', 'alexandreachard@free.fr'),
('PO002', 'Badji', 'Mustapha', '153 bis Rue Joliot Curie', '69005', 'Lyon', '04-26-00-27-74', 'meusb@hotmail.fr'),
('PO003', 'Bazin', 'Nicolas', '85 Av. Hélène de Tournon', '07300', 'Tournon sur Rhône', '04-75-07-11-97', 'nicolasbazin@free.fr'),
('PO004', 'Bonete', 'Aurélien', '8 Rue de l\'abondance', '69003', 'Lyon', '04-72-61-99-62', 'aurelienbonete@gmail.com'),
('PO005', 'Bonnamy', 'Aurélien', '666 Rue Notre Dame des Champs', '01480', 'Jassans Riottier', '04-74-09-80-40', 'aurelienbonnamy@gmail.com'),
('PO006', 'Bornand', 'Thomas', '57 Rue Etienne Richerand', '69003', 'Lyon', '04-78-53-40-02', 'tomitom13@hotmail.com'),
('PO007', 'Brunier', 'Aymeric', '12 chemin du Vigneux', '69380', 'Les Chères', '04-78-47-00-07', 'brunier.aymeric@wanadoo.fr'),
('PO008', 'Castelluci', 'Alix', '15 Rue Pierre Blanc', '69001', 'Lyon', '04-78-52-86-63', 'noob_noob@hotmail.fr'),
('PO009', 'Chapoton', 'Benjamin', '35 Rue Louis Galvani', '69100', 'Villeurbanne', '04-78-03-97-44', 'b.chapoton@gmail.com'),
('PO010', 'De Almeida', 'Vincent', '56 Avenue des Marronniers', '69270', 'Fontaine sur Saone', '04-78-08-23-20', 'vincent_dealmeida@hotmail.fr'),
('PO011', 'De Crevoisier', 'Michel', '60 rue Vauban', '69006', 'Lyon', '', ''),
('PO012', 'Di Vito', 'Florian', '30 Avenue Leclerc', '', '', '04-78-69-98-44', ''),
('PO013', 'El Mansouri', 'Mohammed', '154 Avenue Charles de Gaulle', '69160', 'Tassin la demi Lune', '04-78-34-01-58', ''),
('PO014', 'Fargeat', 'Jeremy', '695 Chemin du Rocher', '42190', 'St Nizier sur Charlieu', '04-77-60-82-78', ''),
('PO015', 'Fronteau', 'Joana', '10 Rue Léo Lagrange', '69200', 'Venissieux', '04-27-14-45-87', '');

-- --------------------------------------------------------

--
-- Structure de la table `reprise`
--

CREATE TABLE `reprise` (
  `REPRISENum` varchar(50) NOT NULL,
  `MONITEURCode` varchar(50) NOT NULL,
  `REPRISEDuree` int(11) NOT NULL,
  `REPRISEDate` date NOT NULL,
  `REPRISEHeureDebut` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reprise`
--

INSERT INTO `reprise` (`REPRISENum`, `MONITEURCode`, `REPRISEDuree`, `REPRISEDate`, `REPRISEHeureDebut`) VALUES
('RE001', 'MO001', 30, '0000-00-00', 14),
('RE002', 'MO001', 30, '0000-00-00', 15),
('RE003', 'MO002', 60, '0000-00-00', 14),
('RE004', 'MO001', 45, '0000-00-00', 15),
('RE005', 'MO002', 60, '0000-00-00', 16),
('RE006', 'MO003', 30, '0000-00-00', 16),
('RE007', 'MO004', 60, '0000-00-00', 14),
('RE008', 'MO004', 60, '0000-00-00', 15),
('RE009', 'MO005', 45, '0000-00-00', 16),
('RE010', 'MO005', 30, '0000-00-00', 13),
('RE011', 'MO006', 30, '0000-00-00', 13);

-- --------------------------------------------------------

--
-- Structure de la table `travailler`
--

CREATE TABLE `travailler` (
  `REPRISENum` varchar(50) NOT NULL,
  `CHEVALMatricule` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `travailler`
--

INSERT INTO `travailler` (`REPRISENum`, `CHEVALMatricule`) VALUES
('RE001', '   CH003'),
('RE001', 'CH001'),
('RE001', 'CH004'),
('RE001', 'CH006'),
('RE001', 'CH007'),
('RE001', 'CH010'),
('RE001', 'CH011'),
('RE002', 'CH001'),
('RE002', 'CH003'),
('RE002', 'CH004'),
('RE002', 'CH006'),
('RE002', 'CH007'),
('RE002', 'CH010'),
('RE002', 'CH011'),
('RE003', 'CH001'),
('RE003', 'CH003'),
('RE003', 'CH004'),
('RE003', 'CH006'),
('RE003', 'CH007'),
('RE003', 'CH010'),
('RE003', 'CH011'),
('RE004', 'CH001'),
('RE004', 'CH003'),
('RE004', 'CH004'),
('RE004', 'CH006'),
('RE004', 'CH007'),
('RE004', 'CH010'),
('RE004', 'CH011'),
('RE005', 'CH001'),
('RE005', 'CH003'),
('RE005', 'CH004'),
('RE005', 'CH006'),
('RE005', 'CH007'),
('RE005', 'CH010'),
('RE005', 'CH011');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `box`
--
ALTER TABLE `box`
  ADD PRIMARY KEY (`BOXRef`),
  ADD KEY `fk_cheval` (`chevalmatricule`);

--
-- Index pour la table `cavalier`
--
ALTER TABLE `cavalier`
  ADD PRIMARY KEY (`CAVALIERNum`);

--
-- Index pour la table `cheval`
--
ALTER TABLE `cheval`
  ADD PRIMARY KEY (`CHEVALMatricule`),
  ADD KEY `fk_proprio` (`CavalierNum`);

--
-- Index pour la table `moniteur`
--
ALTER TABLE `moniteur`
  ADD PRIMARY KEY (`MONITEURCode`);

--
-- Index pour la table `monter`
--
ALTER TABLE `monter`
  ADD PRIMARY KEY (`REPRISENum`,`CAVALIERNum`);

--
-- Index pour la table `participe`
--
ALTER TABLE `participe`
  ADD PRIMARY KEY (`RepriseNum`,`chevalmatricule`);

--
-- Index pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD PRIMARY KEY (`PROPRIONum`);

--
-- Index pour la table `reprise`
--
ALTER TABLE `reprise`
  ADD PRIMARY KEY (`REPRISENum`),
  ADD KEY `Fk_moniteur` (`MONITEURCode`);

--
-- Index pour la table `travailler`
--
ALTER TABLE `travailler`
  ADD PRIMARY KEY (`REPRISENum`,`CHEVALMatricule`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `box`
--
ALTER TABLE `box`
  ADD CONSTRAINT `fk_cheval` FOREIGN KEY (`chevalmatricule`) REFERENCES `cheval` (`CHEVALMatricule`);

--
-- Contraintes pour la table `cheval`
--
ALTER TABLE `cheval`
  ADD CONSTRAINT `fk_proprio` FOREIGN KEY (`CavalierNum`) REFERENCES `cavalier` (`CAVALIERNum`);

--
-- Contraintes pour la table `reprise`
--
ALTER TABLE `reprise`
  ADD CONSTRAINT `Fk_moniteur` FOREIGN KEY (`MONITEURCode`) REFERENCES `moniteur` (`MONITEURCode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
