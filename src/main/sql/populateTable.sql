DELETE FROM utilisateur;
DELETE FROM produit;

INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Tshirt Gris',10,10,10,9.99,1,'Gris','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Pull Gris',10,10,10,19.99,2,'Gris','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Chemise Blanche',10,10,10,14.99,3,'Blanc','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Tshirt Blanc',10,10,10,9.99,1,'Blanc','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Tshirt Rouge',10,10,10,9.99,1,'Rouge','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Tshirt Bleu',10,10,10,9.99,1,'Bleu','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Pull Bleu',10,10,10,19.99,2,'Bleu','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Pull Rouge',10,10,10,19.99,2,'Rouge','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Chemise Bleue',10,10,10,14.99,3,'Bleu','');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`) VALUES ('Tshirt Noir',10,10,10,9.99,1,'Noir','');

INSERT INTO `utilisateur` (`email`,`prenom`,`nom`,`datenaissance`,`motdepasse`,`adresseliv`,`adressefac`,`admin`) VALUES ('gauthier.hacout@hei.yncrea.fr','Gauthier','Hacout','1997-10-21','projet','10 rue Adolphe','10 rue Adolphe',false);
INSERT INTO `utilisateur` (`email`,`prenom`,`nom`,`datenaissance`,`motdepasse`,`adresseliv`,`adressefac`,`admin`) VALUES ('gustavo.brancaglione@hei.yncrea.fr','Gustavo','Brancaglione','1996-03-27','projet','10 rue Adolphe','10 rue Adolphe',false);
INSERT INTO `utilisateur` (`email`,`prenom`,`nom`,`datenaissance`,`motdepasse`,`adresseliv`,`adressefac`,`admin`) VALUES ('admin@hei.yncrea.fr','Admin','Istrateur','1978-05-24','admin','13 rue de Toul','13 rue de Toul',true);