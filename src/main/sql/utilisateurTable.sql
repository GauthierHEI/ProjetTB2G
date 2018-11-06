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


CREATE TABLE `produit` (
  `produit_id` int(11) NOT NULL AUTO_INCREMENT,
  `produit` varchar(30) NOT NULL,
  `dispoS`int(8) NOT NULL,
  `dispoM` int(8) NOT NULL,
  `dispoL` int(8) NOT NULL,
  `prix` int(20) NOT NULL,
  `cat` varchar (30) NOT NULL,
  `couleur` varchar (30) NOT NULL,
  `image` varchar (50) NOT NULL,

   PRIMARY KEY (`produit_id`)
);




