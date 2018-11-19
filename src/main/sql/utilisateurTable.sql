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
  `image` varchar (50),

   PRIMARY KEY (`produit_id`)
);

CREATE TABLE `panier` (
  `element_id` int(11) NOT NULL AUTO_INCREMENT,
  `commmande_id` int(11) NOT NULL,
  `utilisateur_id` int(11) NOT NULL,
  `produit_id` int(11) NOT NULL,
  `taille` enum('S','M','L') NOT NULL,

  PRIMARY KEY (`element_id`)
);

ALTER TABLE `panier`
ADD FOREIGN KEY (`utilisateur_id`)
REFERENCES `utilisateur`(`utilisateur_id`)
ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `panier`
ADD FOREIGN KEY (`produit_id`)
REFERENCES `produit`(`produit_id`)
ON DELETE RESTRICT ON UPDATE RESTRICT;


