/**
 * Created by Zearf on 1/24/2014.
 */
/*$("#register").submit(function(){

    var email_regex = /^[a-z0-9!#$%&'*+/=?^_{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/
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

    if (email==null || email=="" || !(email_regex.test(email)))

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
});

$("#additional").submit(function(){
    var testDate=document.forms["register"]["dob"].value;
    var testYoe=document.forms["register"]["yoe"].value;
    var date_regex = /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    var exp_regex = /^\d{1}|[1-9]\d{1}$/;
    if(!(date_regex.test(testDate)) && !(testDate==null || testDate==""))
    {
        $('#valid_dob').popover({
            placement: 'right'
        }).popover('show');
        setTimeout(function () {
            $('#valid_dob').popover('destroy');
        }, 3500);
        return false;

    }
    if(!(exp_regex.test(testYoe)) && !(testYoe==null || testYoe==""))
    {
        $('#valid_yoe').popover({
            placement: 'right'
        }).popover('show');
        setTimeout(function () {
            $('#valid_yoe').popover('destroy');
        }, 3500);
        return false;

    }
});*/

$(document).ready(function(){
  $('form#register').parsley({
    listeners: {
        onFieldValidate: function(elem, ParsleyField) {
          if($('.parsley-error-list').length>=1){
            if(!$('#errorContainer').hasClass('register-errorContainer'))
              $('#errorContainer').addClass('register-errorContainer')
          }
          else{
            if($('#errorContainer').hasClass('register-errorContainer'))
              $('#errorContainer').removeClass('register-errorContainer')
          }
        },
        onFieldError: function(elem, ParsleyField) {
          if(!$('#errorContainer').hasClass('register-errorContainer'))
            $('#errorContainer').addClass('register-errorContainer')
        },
        onFieldSuccess: function(elem, ParsleyField) {
          if($('.parsley-error-list').length>=1){
            if(!$('#errorContainer').hasClass('register-errorContainer'))
              $('#errorContainer').addClass('register-errorContainer')
          }
          else{
            if($('#errorContainer').hasClass('register-errorContainer'))
              $('#errorContainer').removeClass('register-errorContainer')
          }
        }

    }
  });
  
  $('form#index-form').parsley({
    listeners: {
        onFieldValidate: function(elem, ParsleyField) {
          if($('.parsley-error-list').length>=1){
            if(!$('#errorContainer').hasClass('index-errorContainer'))
              $('#errorContainer').addClass('index-errorContainer')
          }
          else{
            if($('#errorContainer').hasClass('index-errorContainer'))
              $('#errorContainer').removeClass('index-errorContainer')
          }
        },
        onFieldError: function(elem, ParsleyField) {
          if(!$('#errorContainer').hasClass('index-errorContainer'))
            $('#errorContainer').addClass('index-errorContainer')
        },
        onFieldSuccess: function(elem, ParsleyField) {
          if($('.parsley-error-list').length>=1){
            if(!$('#errorContainer').hasClass('index-errorContainer'))
              $('#errorContainer').addClass('index-errorContainer')
          }
          else{
            if($('#errorContainer').hasClass('index-errorContainer'))
              $('#errorContainer').removeClass('index-errorContainer')
          }
        }

    }
  });

  $('form#login-form').parsley({
    listeners: {
        onFieldValidate: function(elem, ParsleyField) {
          if($('.parsley-error-list').length>=1){
            if(!$('#errorContainer').hasClass('login-errorContainer'))
              $('#errorContainer').addClass('login-errorContainer')
          }
          else{
            if($('#errorContainer').hasClass('login-errorContainer'))
              $('#errorContainer').removeClass('login-errorContainer')
          }
        },
        onFieldError: function(elem, ParsleyField) {
          if(!$('#errorContainer').hasClass('login-errorContainer'))
            $('#errorContainer').addClass('login-errorContainer')
        },
        onFieldSuccess: function(elem, ParsleyField) {
          if($('.parsley-error-list').length>=1){
            if(!$('#errorContainer').hasClass('login-errorContainer'))
              $('#errorContainer').addClass('login-errorContainer')
          }
          else{
            if($('#errorContainer').hasClass('login-errorContainer'))
              $('#errorContainer').removeClass('login-errorContainer')
          }
        }

    }
  });

  $('input#index-username').parsley('addConstraint', 
    {'required': 'true'});
  $('input#index-password').parsley( 'addConstraint', 
    {'required': 'true',
      'regexp': '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$'});
  $( 'input#valid_fname' ).parsley( 'addConstraint', 
    {'required': 'true'});
  $( 'input#valid_lname' ).parsley( 'addConstraint', 
    {'required': 'true'});
  $( 'input#valid_username' ).parsley( 'addConstraint', 
    {'required': 'true'});
  $('input#valid_pass').parsley( 'addConstraint', 
    {'required': 'true',
     'regexp': "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$"});
  $('input#valid_cpass').parsley( 'addConstraint', 
    {'required': 'true',
      'equalto': '#valid_pass',
      'regexp': "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$"});
  $('input#valid_email').parsley( 'addConstraint', 
    {'required': 'true',
      'type': 'email'});
  $('input#login-username').parsley('addConstraint', 
    {'required': 'true'});
  $('input#login-password').parsley( 'addConstraint', 
    {'required': 'true',
      'regexp': '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,8}$'});


});


