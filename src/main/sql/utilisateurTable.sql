CREATE TABLE `utilisateur` (
  `utilisateur_id` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `datenaissance` date NOT NULL,
  `motdepasse` varchar(20) NOT NULL,
  `adresseliv` varchar (1000) NOT NULL,
  `adressefac` varchar (1000) NOT NULL,

   PRIMARY KEY (`utilisateur_id`)
);

