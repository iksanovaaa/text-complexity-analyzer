btnUpload.onclick = function uploadFile(){
    document.getElementById("results").hidden=true;
    var reader = new FileReader();
    reader.onload = function(e){
        $("#userText").val(e.target.result);
    };
    reader.readAsText($("#inputGroupFile04")[0].files[0], "UTF-8")
};

function hideResults(){
    document.getElementById("results").hidden=true;
};

function showResults() {
    if (document.getElementById("userText").value == "")
        alert("Enter the text to continue.");
    else if (!/((\W*|[0-9]*)[a-zA-Z]+)+/.test(document.getElementById("userText").value))
        alert("Check your text. It must contain Latin letters.");
    else if (!document.getElementById("chFAC").checked && !document.getElementById("chWCC").checked &&
        !document.getElementById("chVTC").checked && !document.getElementById("chN").checked &&
        !document.getElementById("chAA").checked && !document.getElementById("chPN").checked &&
        !document.getElementById("chOG").checked)
        alert("Select at least one measure.");
    else{
        numClausal = Number(document.getElementById("FACnum").innerHTML) +
            Number(document.getElementById("WCCnum").innerHTML) + Number(document.getElementById("VTCnum").innerHTML);
        numPhrasal = Number(document.getElementById("Nnum").innerHTML) +
            Number(document.getElementById("AAnum").innerHTML) + Number(document.getElementById("PNnum").innerHTML) +
            Number(document.getElementById("OGnum").innerHTML);
        if (numClausal == 0 && numPhrasal == 0)
            document.getElementById("comment").innerHTML = "You can improve your text by increasing both the " +
                "number of clausal complexity metrics and the number of phrasal complexity metrics."
        else if (numPhrasal <= numClausal)
            document.getElementById("comment").innerHTML = "You can improve your text by reducing the number " +
                "of clausal complexity metrics and increasing the number of phrasal complexity metrics."
        else document.getElementById("comment").innerHTML = "You can improve your text by increasing " +
            "the number of phrasal complexity metrics."
        document.getElementById("results").hidden=false;
    }

};

chFAC.onchange = function changeLiFAC(){
    if (document.getElementById("chFAC").checked)
    document.getElementById("liFAC").hidden = false
    else document.getElementById("liFAC").hidden = true
}

chWCC.onchange = function changeLiWCC(){
    document.getElementById("liWCC").hidden = !document.getElementById("liWCC").hidden
}

chVTC.onchange = function changeLiVTCC(){
    document.getElementById("liVTC").hidden = !document.getElementById("liVTC").hidden
}

chN.onchange = function changeLiN(){
    document.getElementById("liN").hidden = !document.getElementById("liN").hidden
}

chAA.onchange = function changeLiAA(){
    document.getElementById("liAA").hidden = !document.getElementById("liAA").hidden
}

chPN.onchange = function changeLiPN(){
    document.getElementById("liPN").hidden = !document.getElementById("liPN").hidden
}

chOG.onchange = function changeLiOG(){
    document.getElementById("liOG").hidden = !document.getElementById("liOG").hidden
}

btnReport.onclick = function downloadReport(){
    var content = document.getElementById("userText").value;
    content += "\n\n\nRESULTS\n\n";
    content += "Number of metrics in the text\n\n";
    if(document.getElementById("chFAC").checked || document.getElementById("chWCC").checked ||
        document.getElementById("chVTC").checked) {
        content += "Clausal\n"
        if(document.getElementById("chFAC").checked)
            content = content + document.getElementById("FAC").innerHTML + " " + document.getElementById("FACnum").innerHTML + "\n";
        if(document.getElementById("chWCC").checked)
            content = content + document.getElementById("WCC").innerHTML + " " + document.getElementById("WCCnum").innerHTML + "\n";
        if(document.getElementById("chVTC").checked)
            content = content + document.getElementById("VTC").innerHTML + " " + document.getElementById("VTCnum").innerHTML + "\n";
    }
    content += "\n"

    if(document.getElementById("chN").checked || document.getElementById("chAA").checked ||
        document.getElementById("chPN").checked || document.getElementById("chOG").checked){
        content += "Phrasal\n"
        if(document.getElementById("chN").checked)
            content = content + document.getElementById("N").innerHTML + " " + document.getElementById("Nnum").innerHTML + "\n";
        if(document.getElementById("chAA").checked)
            content = content + document.getElementById("AA").innerHTML + " " + document.getElementById("AAnum").innerHTML + "\n";
        if(document.getElementById("chPN").checked)
            content = content + document.getElementById("PN").innerHTML + " " + document.getElementById("PNnum").innerHTML + "\n";
        if(document.getElementById("chOG").checked)
            content = content + document.getElementById("OG").innerHTML + " " + document.getElementById("OGnum").innerHTML + "\n";
    }
    content += "\n\nRecommendations\n";
    content += document.getElementById("comment").innerHTML

    var aTag = document.createElement("a");
    var file = new Blob([content], {type: 'text/plain'});
    aTag.href = URL.createObjectURL(file);
    aTag.download = "Report.txt";
    aTag.click();
};

function startFunc(){

    if ((document.getElementById("userText").value == "") ||
        (!/((\W*|[0-9]*)[a-zA-Z]+)+/.test(document.getElementById("userText").value)) ||
        (!document.getElementById("chFAC").checked && !document.getElementById("chWCC").checked &&
            !document.getElementById("chVTC").checked && !document.getElementById("chN").checked &&
            !document.getElementById("chAA").checked && !document.getElementById("chPN").checked &&
            !document.getElementById("chOG").checked))
    {
        document.getElementById("results").hidden = true;
    }
    else {
        document.getElementById("results").hidden = false;
        if (document.getElementById("chFAC").checked)
            document.getElementById("liFAC").hidden = false
        if (document.getElementById("chWCC").checked)
            document.getElementById("liWCC").hidden = false
        if (document.getElementById("chVTC").checked)
            document.getElementById("liVTC").hidden = false

        if (document.getElementById("chN").checked)
            document.getElementById("liN").hidden = false
        if (document.getElementById("chAA").checked)
            document.getElementById("liAA").hidden = false
        if (document.getElementById("chPN").checked)
            document.getElementById("liPN").hidden = false
        if (document.getElementById("chOG").checked)
            document.getElementById("liOG").hidden = false

        numClausal = Number(document.getElementById("FACnum").innerHTML) +
            Number(document.getElementById("WCCnum").innerHTML) + Number(document.getElementById("VTCnum").innerHTML);
        numPhrasal = Number(document.getElementById("Nnum").innerHTML) +
            Number(document.getElementById("AAnum").innerHTML) + Number(document.getElementById("PNnum").innerHTML) +
            Number(document.getElementById("OGnum").innerHTML);
        if (numClausal == 0 && numPhrasal == 0)
            document.getElementById("comment").innerHTML = "You can improve your text by increasing both the " +
                "number of clausal complexity metrics and the number of phrase complexity metrics."
        else if (numPhrasal <= numClausal)
            document.getElementById("comment").innerHTML = "You can improve your text by reducing the number " +
                "of clausal complexity metrics and increasing the number of phrase complexity metrics."
        else document.getElementById("comment").innerHTML = "You can improve your text by increasing " +
                "the number of phrase complexity metrics."
        document.getElementById("results").hidden=false;
    }
}