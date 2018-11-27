
function Achat(ProductId){
    var Id = ProductId;
    document.getElementById("form"+Id).style.visibility="visible";
};

function Cache(ProductId){
    var Id = ProductId;
    document.getElementById("form"+Id).style.visibility="hidden";
};

function Modif(ProductId){
    console.log("In affiche");
    var Id = ProductId;
    document.getElementById("trmodif"+Id).style.display="table-row";
    document.getElementById("td"+Id).onclick = function (ev) { CacheProduit(Id) };

};

function CacheProduit(ProductId){
    console.log("In cache");
    var Id = ProductId;
    document.getElementById("trmodif"+Id).style.display="none";
    document.getElementById("td"+Id).onclick = function (ev) { Modif(Id) };

};

function editAdmin(utilisateur_id){
    console.log(utilisateur_id);
    var role = document.getElementById("adminName"+ utilisateur_id).innerText;
    console.log("logInnerText : "+role);
    var requete=new XMLHttpRequest();
    requete.open("POST","editAdministrateur", true);
    requete.responseType="text";
    requete.onload=function () {
        var response = this.response;
        console.log(response);
        adminText = document.getElementById("adminName" + utilisateur_id);
        console.log(role);
        if (role) {
            console.log("Je passe le rôle à true");

            adminText.innerText ="true";
            adminText.className="admin";
        }
        else{
            console.log("Je passe le rôle à false");

            adminText.innerText="false";
            adminText.className="utilisateur";
        }
    }
    requete.setRequestHeader("Content-type","application/x-www-form-urlencoded");

    if(role == "false" ) {
        role=true;
        console.log("role :" + role);
    }
    else{
        role=false;
        console.log("role :" + role);
    }

    requete.send("id=" + utilisateur_id + "&admin=" + role);

}

function deleteUtilisateur(utilisateur_id){
    if(confirm("Etes-vous sur de vouloir supprimer cet utilisateur ?")){

        console.log("Id de l'utilisateur:"+utilisateur_id);
        var utilisateur = document.getElementById("utilisateurConcerne"+ utilisateur_id);
        console.log("Utilisateur concerne : "+utilisateur);

        var requete=new XMLHttpRequest();
        requete.open("POST","deleteUtilisateur", true);
        requete.responseType="text";
        requete.onload=function (){
            console.log(this.response);
            utilisateur.remove();
        }
        requete.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        requete.send("id=" + utilisateur_id);



    }

}

function verificationEmail(champId) {
    console.log("Fonction");
    var email = document.getElementById(champId).value;
    var expressionReguliere = /^[a-z0-9.-]{2,}@+[a-z0-9.-]{2,}$/i;
    if (expressionReguliere.test(email)) {
        console.log("Bon");
        document.getElementById(champId).style.borderColor="green";
    }
    else {
        console.log("Faux");
        document.getElementById(champId).style.borderColor="red";
    }
}

function verificationEmailSubmit(champId) {
    console.log("Fonction");
    var email = document.getElementById(champId).value;
    var erreur = document.getElementById("erreur-email-connexion");
    var expressionReguliere = /^[a-z0-9.-]{2,}@+[a-z0-9.-]{2,}$/i;
    if (expressionReguliere.test(email)) {
        console.log("Bon");
        erreur.innerText="";
        return true;
    }
    else {
        console.log("Faux");
        erreur.innerText="Email invalide";
        document.getElementById(champId).style.border="0.1vw solid red";
        return false;
    }
}

function VerificationEmailExist(champId, erreurId, formId){
    var form = document.getElementById(formId);
    var email = document.getElementById(champId).value;
    var erreur = document.getElementById(erreurId);
    var requete=new XMLHttpRequest();
    requete.open("POST","VerificationEmail", true);
    requete.responseType="text";
    requete.onload=function () {
        var response = this.response;
        console.log(response);
        if (response === "OK"){
            erreur.innerText="";
            form.submit();
            return true;
        }
        else{
            erreur.innerText="Email deja utilise !";
            return false;
        }
    }
    requete.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    requete.send("email="+email);


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
    console.log(mdp1);
    var mdp2 = document.getElementById("profil-mdp2").value;
    console.log(mdp2);

    if(mdp1 === mdp2){
        console.log("c'est bon");
        return true;
    }
    else{
        console.log("pas bon");
        alert("les mots de passes ne correspondent pas!");
        return false;
    }
}

window.onload= function () {
    document.getElementById("bouton-form-creation").onclick = function() {
        if(verificationEmailSubmit("mail-crea")){
            if(VerificationEmailExist("mail-crea","erreur-email-connexion","form-creation")){
                return true;
            }
            return false;
        }
        return false;
    }

    document.getElementById("form-profil").onsubmit = function () {
        console.log("j'ai échoué");
        return false;
    }
}

