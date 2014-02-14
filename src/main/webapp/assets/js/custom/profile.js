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
