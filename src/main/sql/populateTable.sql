DELETE FROM utilisateur;
DELETE FROM produit;

INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Tshirt Gris',10,10,10,9.99,1,'Gris','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Pull Gris',10,10,10,19.99,2,'Gris','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Chemise Blanche',10,10,10,14.99,3,'Blanc','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Tshirt Blanc',10,10,10,9.99,1,'Blanc','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Tshirt Rouge',10,10,10,9.99,1,'Rouge','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Tshirt Bleu',10,10,10,9.99,1,'Bleu','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Pull Bleu',10,10,10,19.99,2,'Bleu','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Pull Rouge',10,10,10,19.99,2,'Rouge','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Chemise Bleue',10,10,10,14.99,3,'Bleu','','#000000');
INSERT INTO `produit` (`produit`,`dispoS`,`dispoM`,`dispoL`,`prix`,`cat`,`couleur`,`image`,`Hexcouleur`) VALUES ('Tshirt Noir',10,10,10,9.99,1,'Noir','','#000000');

INSERT INTO `utilisateur` (`email`,`prenom`,`nom`,`datenaissance`,`motdepasse`,`adresseliv`,`adressefac`,`admin`) VALUES ('alex@alex.fr','Alex','Alex','1997-10-21','$argon2i$v=19$m=65536,t=5,p=1$QvOv3+9qSYxIzLlzjeJKc8qpT9PJ42y9B+U/hXBejlB3mSghQ75TcJemyV2X51pN2sWspKHwqgWXtddCykMUsWBajRljlm9EJEPRZhhOi4TjkUsqcbto8ToE3ih7FiBIt4sQF1pMW4BwoXZOtQ1/jc6p53h2zmOJ6szzuzhQlC0$lqf32VJjfnSEeu2Nnnck0nTrpWJuhmpSa/XPx86cBjpgj2UCR4fSCoeeffXj4i4eEV/RKIidgzbW4tUJ07VfDA1khGZ+N+/6IC21QM+jcPdhCQd2wFOS6T3vHGj+2Gw5yFREJeh9wSZTf+lAZtFKGS3Ca1E6EJSlQBgtAzJlzz0','10 rue Adolphe','10 rue Adolphe',false);
INSERT INTO `utilisateur` (`email`,`prenom`,`nom`,`datenaissance`,`motdepasse`,`adresseliv`,`adressefac`,`admin`) VALUES ('admin@hei.yncrea.fr','Admin','Admin','1978-05-24','$argon2i$v=19$m=65536,t=5,p=1$h6yY9UMpKwAFiKSM9vrBpDnOaxAAcvMzkDIxK2hjg+b0NrRI7Hutfpz8PS+rchimUndfBfHdN/JQDQVpuBEl7DL3dxhOmG6yVEmO0GZ1PTJq3fT/kNi2bcbikEjp+ttCHgMKMYvab5iW2VsDjwUbinxb0PixDqDOnZYCV5U3SIE$q8Rv5q8v71NIFpzVnHjGI1MaJQOq8b9fJZyzuzHi9GN/NEr1IMFuYjlpneLvnXpKECtvqNq+s/cYuUiWqFJvsgl1j3HumqnubfXeTi5q20N3LCZis1EaZKd1pYsmpF8vysVdQe9V5GofVrrjCwIohoq+3Qj49rQb+GBCBVyJmpw','13 rue de Toul','13 rue de Toul',true);

INSERT INTO `utilisateur` (`email`,`prenom`,`nom`,`datenaissance`,`motdepasse`,`adresseliv`,`adressefac`,`admin`) VALUES ('alex@alex.fr','Alex','Alex','1997-10-21','$argon2i$v=19$m=65536,t=5,p=1$QvOv3+9qSYxIzLlzjeJKc8qpT9PJ42y9B+U/hXBejlB3mSghQ75TcJemyV2X51pN2sWspKHwqgWXtddCykMUsWBajRljlm9EJEPRZhhOi4TjkUsqcbto8ToE3ih7FiBIt4sQF1pMW4BwoXZOtQ1/jc6p53h2zmOJ6szzuzhQlC0$lqf32VJjfnSEeu2Nnnck0nTrpWJuhmpSa/XPx86cBjpgj2UCR4fSCoeeffXj4i4eEV/RKIidgzbW4tUJ07VfDA1khGZ+N+/6IC21QM+jcPdhCQd2wFOS6T3vHGj+2Gw5yFREJeh9wSZTf+lAZtFKGS3Ca1E6EJSlQBgtAzJlzz0','10 rue Adolphe','10 rue Adolphe',false);


