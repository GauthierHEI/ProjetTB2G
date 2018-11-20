
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
    document.getElementById("td"+Id).onclick = function (ev) { CacheProduit(Id) };

};

function CacheProduit(ProductId){
    var Id = ProductId;
    document.getElementById("tr"+Id).style.display="none";
    document.getElementById("td"+Id).onclick = function (ev) { Modif(Id) };

};

function verificationEmail(champId) {
    console.log("Fonction");
    let email = document.getElementById(champId).value;
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
