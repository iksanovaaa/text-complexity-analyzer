$("#inputGroupFile04").change(function(){
    var reader = new FileReader();
    reader.onload = function(e){
        $("#userText").val(e.target.result);
    };
    reader.readAsText($("#inputGroupFile04")[0].files[0], "UTF-8");
});