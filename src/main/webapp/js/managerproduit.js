var modifProduit = function (produitId) {
    console.log("In modif");
    var produit = "";
    if (document.getElementById("produit" + produitId) != null) {
        produit = document.getElementById("produit" + produitId).value;
    }

    var dispoS = "";
    if (document.getElementById("dispoS" + produitId) != null) {
        dispoS = document.getElementById("dispoS" + produitId).value;
    }
    var dispoM = "";
    if (document.getElementById("dispoM" + produitId) != null) {
        dispoM = document.getElementById("dispoM" + produitId).value;
    }
    var dispoL = "";
    if (document.getElementById("dispoL" + produitId) != null) {
        dispoL = document.getElementById("dispoL" + produitId).value;
    }
    var prix = "";
    if (document.getElementById("prix" + produitId) != null) {
        prix = document.getElementById("prix" + produitId).value;
    }
    var cat = "";
    if (document.getElementById("cat" + produitId) != null) {
        cat = document.getElementById("cat" + produitId).value;
    }
    var couleur = "";
    if (document.getElementById("couleur" + produitId) != null) {
        couelur = document.getElementById("couleur" + produitId).value;
    }
    var hexcouleur = "";
    if (document.getElementById("hexcouleur" + produitId) != null) {
        hexcouleur = document.getElementById("hexcouleur" + produitId).value;
    }

    var request = new XMLHttpRequest();
    request.open("POST", "modifProduit", true);

    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    request.send("produitId="+produitId+"&produit="+produit+"&dispoS="+dispoS+"&dispoM="
        +dispoM+"&dispoL="+dispoL+"&prix="+prix+"&cat="+cat+"&couleur="+couleur+"&hexcouleur="+hexcouleur);
    CacheProduit(produitId);
    request.onload = function () {
        let response = this.response;
        console.log(response);
        if(produit != "") {
            document.getElementById("xproduit" + produitId).innerText = produit;
            document.getElementById("produit" + produitId).value = "";
        }
        if (dispoS != "") {
            document.getElementById("xdispoS" + produitId).innerText = dispoS;
            document.getElementById("dispoS" + produitId).value = "";
        }
        if (dispoM != "") {
            document.getElementById("xdispoM" + produitId).innerText = dispoM;
            document.getElementById("dispoM" + produitId).value = "";
        }
        if (dispoL != "") {
            document.getElementById("xdispoL" + produitId).innerText = dispoL;
            document.getElementById("dispoL" + produitId).value = "";
        }
        if (prix != "") {
            document.getElementById("xprix" + produitId).innerText = prix;
            document.getElementById("prix" + produitId).value = "";
        }
        if (cat != "") {
            document.getElementById("xcat" + produitId).innerText = cat;
            document.getElementById("cat" + produitId).value = "";
        }
        if (couleur != "") {
            document.getElementById("xcouleur" + produitId).innerText = couleur;
            document.getElementById("couleur" + produitId).value = "";
        }
        if (hexcouleur != "") {
            document.getElementById("xhexcouleur" + produitId).innerText = hexcouleur;
            document.getElementById("hexcouleur" + produitId).value = "#000001";
        }
    }
};

var deleteProduit = function (produitId) {
    console.log("In delete");
    var request = new XMLHttpRequest();
    request.open("POST", "deleteProduit", true);

    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("produitId="+produitId);

    request.onload = function () {
        document.getElementById("tr"+produitId).style.display='none';
        document.getElementById("trmodif"+produitId).style.display='none';
    };
};