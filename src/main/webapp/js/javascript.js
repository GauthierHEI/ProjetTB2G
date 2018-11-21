
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
    document.getElementById("td"+Id).onclick = function (ev) { Cache(Id) };

};

function CacheProduit(ProductId){
    var Id = ProductId;
    document.getElementById("tr"+Id).style.display="none";
    document.getElementById("i"+Id).className="far fa-arrow-alt-circle-down";
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
        }
        else{
            console.log("Je passe le rôle à false");

            adminText.innerText="false";
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

    function truc(){
        alert("machin");
    }

}