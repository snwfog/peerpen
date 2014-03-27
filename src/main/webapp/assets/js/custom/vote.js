/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 24/01/14
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
// */

function upVote1() {
    $("form.AjaxSubmit3").submit(function (event) {
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
                $(".AjaxSubmit3 #commentPoint").val(commentId);
                var a = response.split("|");
                var b = "up-"+a[0];
                $("#btn1"+a[0]).attr("disabled", true);
                $(".AjaxSubmit3 #"+b).html(a[1]);   //select the id and put the response in the html
            },
            error: function(jqXHR, textStatus, errorThrown){
                 console.log('error(s):'+textStatus, errorThrown);
            }
        });
    });
    return false;
};

function downVote1() {
    $("form.AjaxSubmit4").submit(function (event) {
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
                $(".AjaxSubmit4 #commentPoint").val(commentId);
                var a = response.split("|");
                var b = "down-"+a[0];
                $("#btn2"+a[0]).attr("disabled", true);
                $(".AjaxSubmit4 #"+b).html(a[1]);   //select the id and put the response in the html
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log('error(s):'+textStatus, errorThrown);
            }
        });
    });
    return false;
};

function upVote2() {
    $("form.AjaxSubmit1").submit(function (event) {
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
                $(".AjaxSubmit1 #commentPoint").val(commentId);
                var a = response.split("|");
                var b = "down-"+a[0];
                $("#btn3"+a[0]).attr("disabled", true);
                $(".AjaxSubmit1 #"+a[0]).html(a[1]);   //select the id and put the response in the html
            },
            error: function(jqXHR, textStatus, errorThrown){
                    console.log('error(s):'+textStatus, errorThrown);
            }
        });
    });
    return false;
};

function downVote2() {
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
                $(".AjaxSubmit2 #commentPoint").val(commentId);
                var a = response.split("|");
                var b = "down-"+a[0];
                $("#btn4"+a[0]).attr("disabled", true);
                $(".AjaxSubmit2 #"+a[0]).html(a[1]);   //select the id and put the response in the html
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log('error(s):'+textStatus, errorThrown);
            }
        });
    });
    return false;
};

