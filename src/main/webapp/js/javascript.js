
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

function VerificationFromSubmit(){
    console.log("IN THE SUBMIT VERIFICATION");
    var adresse = document.getElementById("adresse");
    var prenom = document.getElementById("prenom");
    var nom = document.getElementById("nom");
    var ville = document.getElementById("ville");
    var code = document.getElementById("codepostal");
    var date = document.getElementById("date");
    var mdp = document.getElementById("password");
    var SUBMIT= 0;

    var tableau =  [prenom, nom, date, mdp, ville, adresse, code];

    tableau.forEach(function(item,index,array) {
        console.log(item);
        if (item.value != "") {
            item.style.border = "0.1vw solid green";
            SUBMIT = SUBMIT + 1;
        } else {
            item.style.border = "0.1vw solid red";
        }
    });

    if(SUBMIT === 7){
        return true;
    }
    else{
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
    };
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
    console.log("mdp& : "+mdp1);
    var mdp2 = document.getElementById("profil-mdp2").value;
    console.log("mdp2 : "+mdp2);

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
            if(VerificationFromSubmit()) {
                if (VerificationEmailExist("mail-crea", "erreur-email-connexion", "form-creation")) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    };

};

function PlaceHolder(DispoS, DispoM, DispoL, Id) {
    var taille = document.getElementById("selectTT"+Id).value;

    if (taille == "S"){
        document.getElementById("quantite"+Id).placeholder = "Max : " + DispoS;
    }
    if (taille == "M"){
        document.getElementById("quantite"+Id).placeholder = "Max : " + DispoM;
    }
    if (taille == "L"){
        document.getElementById("quantite"+Id).placeholder = "Max : " + DispoL;
    }
    console.log(taille);
}

function RechercheProduit() {
    var recherche = document.getElementById("recherche-produit").value;
    console.log("recherche : "+recherche);

    var request = new XMLHttpRequest();
    request.open("POST", "RechercheProduit", true);
    request.responseType="json";

    request.onload = function () {
        var ProductTable = document.getElementById("table-modif");
        var rowCount = ProductTable.rows.length; while(--rowCount) ProductTable.deleteRow(rowCount);

        var response = this.response;
        console.log(response);

        response.forEach(function (item, index, array) {

            var row = ProductTable.insertRow(-1);
            row.setAttribute("id","tr"+item.id);
            var ID = row.insertCell(0);
            ID.innerText=item.id;
            var Nom = row.insertCell(1);
            Nom.innerText=item.nameproduit;
            Nom.setAttribute("id","xproduit"+item.id);
            var dispoS = row.insertCell(2);
            dispoS.innerText=item.dispoS;
            dispoS.setAttribute("id","xdispoS"+item.id);
            var dispoM = row.insertCell(3);
            dispoM.innerText=item.dispoM;
            dispoM.setAttribute("id","xdispoM"+item.id);
            var dispoL = row.insertCell(4);
            dispoL.innerText=item.dispoL;
            dispoL.setAttribute("id","xdispoL"+item.id);
            var Prix = row.insertCell(5);
            Prix.innerText=item.prix;
            Prix.setAttribute("id","xprix"+item.id);
            var Cat = row.insertCell(6);
            Cat.innerText=item.cat;
            Cat.setAttribute("id","xcat"+item.id);
            var Couleur = row.insertCell(7);
            Couleur.innerText=item.couleur;
            Couleur.setAttribute("id","xcouleur"+item.id);
            var HexColor = row.insertCell(8);
            HexColor.innerText=item.hexcouleur;
            HexColor.setAttribute("id","xhexcouleur"+item.id);
            var Image = row.insertCell(9);
            Image.innerText=item.image;
            Image.setAttribute("id","ximage"+item.id);
            var Modif = row.insertCell(10);
            Modif.innerHTML="<i id=\"i"+item.id+"\" class=\"fas fa-edit\"></i>";
            Modif.setAttribute("colspan","2");
            Modif.setAttribute("onclick","Modif("+item.id+")");
            Modif.setAttribute("class","td-fleche-modif");
            Modif.setAttribute("id", "td"+item.id);

            var row2 = ProductTable.insertRow(-1);
            row2.setAttribute("class", "modification-produit");
            row2.setAttribute("id", "trmodif"+item.id);
            var ID2 = row2.insertCell(0);
            ID2.innerText= item.id;
            var InputNom = row2.insertCell(1);
            InputNom.innerHTML="<input type=\"text\" name=\"produit1\" id=\"produit"+item.id+"\">";
            var InputS = row2.insertCell(2);
            InputS.innerHTML="<input type=\"number\" name=\"dispoS1\" id=\"dispoS"+item.id+"\">";
            var InputM = row2.insertCell(3);
            InputM.innerHTML="<input type=\"number\" name=\"dispoM1\" id=\"dispoM"+item.id+"\">";
            var InputL = row2.insertCell(4);
            InputL.innerHTML="<input type=\"number\" name=\"dispoL1\" id=\"dispoL"+item.id+"\">";
            var InputPrix = row2.insertCell(5);
            InputPrix.innerHTML="<input type=\"number\" step=\"0.01\" name=\"prix1\" id=\"prix"+item.id+"\">";
            var InputCat = row2.insertCell(6);
            InputCat.innerHTML="<input type=\"number\" name=\"cat1\" id=\"cat"+item.id+"\">";
            var InputCouleur = row2.insertCell(7);
            InputCouleur.innerHTML="<input type=\"text\" name=\"couleur1\" id=\"couleur"+item.id+"\">";
            var InputHex = row2.insertCell(8);
            InputHex.innerHTML="<label for=\"hexcouleur"+item.id+"\" class=\"input-file\"><i id=\"icone-hexcouleur"+item.id+"\" class=\"fas fa-palette\"></i></label><input onchange=\"ModifHexColor(item.id)\" type=\"color\"  name=\"hexcouleur1\" id=\"hexcouleur"+item.id+"\" class=\"inactive\">";
            var InputImage = row2.insertCell(9);
            InputImage.innerHTML="<label for=\"image"+item.id+"\" class=\"input-file\"><i id=\"icone-image"+item.id+"\" class=\"far fa-file-image\"></i></label><input onchange=\"ModifImage(item.id)\" type=\"file\" name=\"image1\" id=\"image"+item.id+"\" class=\"inactive\">";
            var InputModif = row2.insertCell(10);
            InputModif.innerHTML="<i class=\"fas fa-arrow-circle-right\"></i>";
            InputModif.setAttribute("class", "icone-validation-modification");
            InputModif.setAttribute("onclick","modifProduit("+item.id+")");
            var InputSupprim = row2.insertCell(11);
            InputSupprim.innerHTML="<i class=\"fas fa-trash\"></i>";
            InputSupprim.setAttribute("class", "icone-validation-supprimer");
            InputSupprim.setAttribute("onclick","deleteProduit("+item.id+")");
        });
    };

    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("recherche="+recherche);
};





