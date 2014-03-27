$(document).ready(function () {
    $('#edit_personal').click(function () {
        if ($('#personal_icon').attr('class') == 'fa fa-pencil') {
            $("#personal_icon").attr('class', 'fa fa-check')
            $(".peerPersonal").attr("readonly", false);
            $('#save_personal').css('visibility', '');
            return false;
        }
        else {
            $('#personal_icon').attr('class', 'fa fa-pencil')
            $(".peerPersonal").attr("readonly", true);
            $('#save_personal').css('visibility', 'hidden');
            return false;
        }
    });
    $('#edit_description').click(function () {
        if ($('#description_icon').attr('class') == 'fa fa-pencil') {
            $("#description_icon").attr('class', 'fa fa-check')
            $(".peerDescription").attr("readonly", false);
            $('#save_description').css('visibility', '');
            return false;
        }
        else {
            $('#description_icon').attr('class', 'fa fa-pencil')
            $(".peerDescription").attr("readonly", true);
            $('#save_description').css('visibility', 'hidden');
            return false;
        }
    });
    $('#edit_contact').click(function () {
        if ($('#contact_icon').attr('class') == 'fa fa-pencil') {
            $("#contact_icon").attr('class', 'fa fa-check')
            $(".peerContact").attr("readonly", false);
            $('#save_contact').css('visibility', '');
            return false;
        }
        else {
            $('#contact_icon').attr('class', 'fa fa-pencil')
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
