/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 24/01/14
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
// */

function doAjaxPost3() {
    /* attach a submit handler to the form */
    $("form.AjaxSubmit3").submit(function (event) {

        /* stop form from submitting normally */
        event.preventDefault();

        /* get some values from elements on the page: */
        var $form = $(this);
        var commentId = $(this).data('id');

        //$(".AjaxSubmit #commentPoint").val(commentId);
        var url = $form.attr('action');
        var data = $form.serialize();

//Here I call the ajax and post the data
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function(response, textStatus, jqXHR){
                $(".AjaxSubmit3 #commentPoint").val(commentId);
                var a = response.split("|");
                var b = "up-"+a[0];

                $(".AjaxSubmit3 #"+b).html(a[1]);   //select the id and put the response in the html
            },
error: function(jqXHR, textStatus, errorThrown){
    console.log('error(s):'+textStatus, errorThrown);
    }
});
});
return false;
};

function doAjaxPost4() {
    /* attach a submit handler to the form */
    $("form.AjaxSubmit4").submit(function (event) {

        /* stop form from submitting normally */
        event.preventDefault();

        /* get some values from elements on the page: */
        var $form = $(this);
        var commentId = $(this).data('id');

        //$(".AjaxSubmit #commentPoint").val(commentId);
        var url = $form.attr('action');
        var data = $form.serialize();

//Here I call the ajax and post the data
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function(response, textStatus, jqXHR){
                $(".AjaxSubmit4 #commentPoint").val(commentId);
                var a = response.split("|");
                var b = "down-"+a[0];

                $(".AjaxSubmit4 #"+b).html(a[1]);   //select the id and put the response in the html
            },
error: function(jqXHR, textStatus, errorThrown){
    console.log('error(s):'+textStatus, errorThrown);
    }
});
});
return false;
};

function doAjaxPost1() {
    /* attach a submit handler to the form */
    $("form.AjaxSubmit1").submit(function (event) {

        /* stop form from submitting normally */
        event.preventDefault();

        /* get some values from elements on the page: */
        var $form = $(this);
        var commentId = $(this).data('id');

        //$(".AjaxSubmit #commentPoint").val(commentId);
        var url = $form.attr('action');
        var data = $form.serialize();

//Here I call the ajax and post the data
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function(response, textStatus, jqXHR){
                $(".AjaxSubmit1 #commentPoint").val(commentId);
                var a = response.split("|");

                $(".AjaxSubmit1 #"+a[0]).html(a[1]);   //select the id and put the response in the html
            },
error: function(jqXHR, textStatus, errorThrown){
    console.log('error(s):'+textStatus, errorThrown);
    }
});
});
return false;
};

function doAjaxPost2() {
    /* attach a submit handler to the form */
    $("form.AjaxSubmit2").submit(function (event) {

        /* stop form from submitting normally */
        event.preventDefault();

        /* get some values from elements on the page: */
        var $form = $(this);
        var commentId = $(this).data('id');

        var url = $form.attr('action');
        var data = $form.serialize();

//Here I call the ajax and post the data
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function(response, textStatus, jqXHR){
//                    var commentId = $(this).data('id');
//                    alert(commentId);
                $(".AjaxSubmit2 #commentPoint").val(commentId);
                var a = response.split("|");
                $(".AjaxSubmit2 #"+a[0]).html(a[1]);   //select the id and put the response in the html
            },
error: function(jqXHR, textStatus, errorThrown){
    console.log('error(s):'+textStatus, errorThrown);
    }
});
});
return false;
};

