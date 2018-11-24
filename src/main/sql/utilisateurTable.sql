CREATE TABLE `utilisateur` (
  `utilisateur_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar (40) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `datenaissance` date NOT NULL,
  `motdepasse` varchar(1000) NOT NULL,
  `adresseliv` varchar (100) NOT NULL,
  `adressefac` varchar (100) NOT NULL,
  `admin` boolean NOT NULL,

   PRIMARY KEY (`utilisateur_id`),
   UNIQUE (`email`)
);


CREATE TABLE `produit` (
  `produit_id` int(11) NOT NULL AUTO_INCREMENT,
  `produit` varchar(30) NOT NULL,
  `dispoS`int(8) NOT NULL,
  `dispoM` int(8) NOT NULL,
  `dispoL` int(8) NOT NULL,
  `prix` float(20) NOT NULL,
  `cat` int (5) NOT NULL,
  `couleur` varchar (30) NOT NULL,
  `image` varchar (50) NOT NULL,
  `Hexcouleur` varchar (7) NOT NULL,

   PRIMARY KEY (`produit_id`)
);

CREATE TABLE `panier` (
  `element_id` int(11) NOT NULL AUTO_INCREMENT,
  `produit_id` int(11) NOT NULL,
  `nameproduit` varchar (30) NOT NULL,
  `taille` varchar (30) NOT NULL,
  `quantite` int(10) NOT NULL,
  `prixUni` float(20) NOT NULL,
  `prixTotal` float(20) NOT NULL,
  `vendu` boolean NOT NULL,


  PRIMARY KEY (`element_id`)
);



