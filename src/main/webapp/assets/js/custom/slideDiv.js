$(document).ready(function() {
    $('#slideDiv').click(function(e){
        //USES HREF TO GET ID OF DIV TO SLIDE UP
        var divId = $('#slideDiv').attr("href");
        var visible = $("#"+divId).is(":visible");
        if( visible ){
            $("#"+divId).slideUp();
        }else{
            $("#"+divId).slideDown();
        }
        e.preventDefault();
    });

});

