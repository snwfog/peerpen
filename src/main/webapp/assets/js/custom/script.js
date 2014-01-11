// Generated by CoffeeScript 1.3.3
function validateForm()
{
    var fname=document.forms["register"]["first_name"].value;
    var lname=document.forms["register"]["last_name"].value;
    var username=document.forms["register"]["user_name"].value;
    var pass=document.forms["register"]["password"].value;
    var cpass=document.forms["register"]["confirm_password"].value;
    var email=document.forms["register"]["email"].value;
    if (fname==null || fname=="")
    {
        $('#valid_fname').popover({
            placement: 'left'
        }).popover('show');
        setTimeout(function () {
            $('#valid_fname').popover('destroy');
        }, 3000);
        return false;
    }
    if (lname==null || lname=="")
    {
        $('#valid_lname').popover('show');
        setTimeout(function () {
            $('#valid_lname').popover('destroy');
        }, 3000);
        return false;
    }
    if (username==null || username=="")
    {
        $('#valid_username').popover('show');
        setTimeout(function () {
            $('#valid_username').popover('destroy');
        }, 3000);
        return false;
    }
    if (pass==null || pass=="")
    {
        $('#valid_pass').popover('show');
        setTimeout(function () {
            $('#valid_pass').popover('destroy');
        }, 3000);
        return false;
    }
    if (cpass==null || cpass=="")
    {
        $('#valid_cpass').popover('show');
        setTimeout(function () {
            $('#valid_cpass').popover('destroy');
        }, 3000);
        return false;
    }

    if (email==null || email=="")
    {
        $('#valid_email').popover('show');
        setTimeout(function () {
            $('#valid_email').popover('destroy');
        }, 3000);
        return false;
    }
    if(pass!=cpass)
    {
        $('#valid_cpass').popover('show');
        setTimeout(function () {
            $('#valid_cpass').popover('destroy');
        }, 3000);
        return false;
    }
}
$(function() {
  return $.stellar({
    horizontalScrolling: false,
    verticalOffset: 40
  });
});

$(document).ready(function () {
    $('#edit_personal').click(function () {
        if ($('#personal_icon').attr('class') == 'glyphicon glyphicon-pencil') {
            $("#personal_icon").attr('class', 'glyphicon glyphicon-ok')
            $(".peerPersonal").attr("readonly", false);
            $('#save_personal').css('visibility', '');
            return false;
        }
        else {
            $('#personal_icon').attr('class', 'glyphicon glyphicon-pencil')
            $(".peerPersonal").attr("readonly", true);
            $('#save_personal').css('visibility', 'hidden');
            return false;
        }
    });
    $('#edit_description').click(function () {
        if ($('#description_icon').attr('class') == 'glyphicon glyphicon-pencil') {
            $("#description_icon").attr('class', 'glyphicon glyphicon-ok')
            $(".peerDescription").attr("readonly", false);
            $('#save_description').css('visibility', '');
            return false;
        }
        else {
            $('#description_icon').attr('class', 'glyphicon glyphicon-pencil')
            $(".peerDescription").attr("readonly", true);
            $('#save_description').css('visibility', 'hidden');
            return false;
        }
    });
    $('#edit_contact').click(function () {
        if ($('#contact_icon').attr('class') == 'glyphicon glyphicon-pencil') {
            $("#contact_icon").attr('class', 'glyphicon glyphicon-ok')
            $(".peerContact").attr("readonly", false);
            $('#save_contact').css('visibility', '');
            return false;
        }
        else {
            $('#contact_icon').attr('class', 'glyphicon glyphicon-pencil')
            $(".peerContact").attr("readonly", true);
            $('#save_contact').css('visibility', 'hidden');
            return false;
        }
    });
    $('#save_personal').click(function (){
        document.getElementById("form_personal").submit();
        return false;
    });
    $('#save_description').click(function (){
        document.getElementById("form_description").submit();
        return false;
    });
    $('#save_contact').click(function (){
        document.getElementById("form_contact").submit();
        return false;
    });
});