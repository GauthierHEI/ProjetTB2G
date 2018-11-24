
function Achat(ProductId){
    var Id = ProductId;
    document.getElementById("form"+Id).style.visibility="visible";
};

function Cache(ProductId){
    var Id = ProductId;
    document.getElementById("form"+Id).style.visibility="hidden";
};

function Modif (ProductId){
    var Id = ProductId;
    document.getElementById("tr"+Id).style.display="table-row";
    document.getElementById("i"+Id).className="far fa-arrow-alt-circle-up";
    document.getElementById("td"+Id).onclick = function (ev) { CacheProduit(Id) };

};

function CacheProduit(ProductId) {
    var Id = ProductId;
    document.getElementById("tr"+Id).style.display="none";
    document.getElementById("i"+Id).className="far fa-arrow-alt-circle-down";
    document.getElementById("td"+Id).onclick = function (ev) { Modif(Id) };

};

function verificationEmail(champId) {
    console.log("Fonction");
    email = document.getElementById(champId).value;
    var expressionReguliere = /^[a-z0-9.-]{2,}@+[a-z0-9.-]{2,}$/i;
    if (expressionReguliere.test(email)) {
        console.log("Bon");
        document.getElementById(champId).style.borderBottomColor="green";
    }
    else {
        console.log("Faux");
        document.getElementById(champId).style.borderBottomColor="red";
    }
}

function Connexion(){
    var connexion = document.getElementById("form-connexion");
    var creation =  document.getElementById("form-creation");
    connexion.className="authentification-contenu";
    creation.className="creation-contenu";
}

function Creation(){
    var connexion = document.getElementById("form-creation");
    var creation =  document.getElementById("form-connexion");
    connexion.className="authentification-contenu";
    creation.className="creation-contenu";
}

function InputCouleur(){
    var couleur = document.getElementById("hexcouleur").value;
    console.log(couleur);
    var icone = document.getElementById("icone-hexcouleur");
    icone.style.color= couleur;
}

function InputImage(){
    var icone = document.getElementById("icone-image");
    icone.style.color="#18cd66";
    icone.className= "fas fa-check";
}

function VerificationMdp(){
    var mdp1 = document.getElementById("profil-mdp1").value;
    console.log("mdp1 = " +mdp1);
    var mdp2 = document.getElementById("profil-mdp2").value;
    console.log("mdp2 = " +mdp2);

    if(mdp1 == mdp2){
        console.log("jesuisla");
        return true;
    }
    else{
        alert("les mots de passes ne correspondent pas!");
        return false;
    }
}