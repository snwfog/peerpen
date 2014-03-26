/**
 * Created by Zearf on 1/24/2014.
 */

$(document).ready(function(){

  $('form#register').parsley({
    listeners: {
        onFieldError: function(elem, ParsleyField) {
          var fieldType = elem.attr('id'); 
          switch (fieldType){
            case 'valid_fname':
              console.log('First name is empty.');
              break;
            case 'valid_lname':
              console.log('Last name is empty.');
              break;
            case 'valid_username':
              console.log('User name is empty.');
              break;
            case 'valid_pass':
              console.log('Password is invalid or empty.');
              break;
            case 'valid_cpass':
              console.log('Confirmed password is empty or not matching the password entered.');
              break;
            case 'valid_email':
              console.log('Email is empty or invalid.');
              break;
            default:
              console.log('Error!!!');
          }

        }

    }
  });


  $('form#index-form').parsley({
    listeners: {
        onFieldError: function(elem, ParsleyField) {
          var fieldType = elem.attr('id'); 
          switch (fieldType){
            case 'index-username':
              console.log('Usename is empty.');
              break;
            case 'parsley-user':
              console.log('Usename is empty.');
              break;
            case 'index-password':
              console.log('Password is invalid or empty.');
              break;
            case 'parsley-pass':
              console.log('Password is invalid or empty.');
              break;
            default:
              console.log('Error!!!');
          }

        }

    }
  });

  $('form#login-form').parsley({
    listeners: {
        onFieldError: function(elem, ParsleyField) {
          var fieldType = elem.attr('id'); 
          switch (fieldType){
            case 'login-username':
              console.log('Usename is empty.');
              break;
            case 'login-password':
              console.log('Password is invalid or empty.');
              break;
            default:
              console.log('Error!!!');
          }

        }

    }
  });

  $('form#comment1').parsley({
    listeners: {
        onFieldError: function(elem, ParsleyField) {
          var fieldType = elem.attr('id'); 
          switch (fieldType){
            case 'text1':
              console.log('Comment is empty.');
              break;
            case 'parsley-user':
              console.log('Usename is empty.');
              break;
            case 'index-password':
              console.log('Password is invalid or empty.');
              break;
            case 'parsley-pass':
              console.log('Password is invalid or empty.');
              break;
            default:
              console.log('Error!!!');
          }

        }

    }
  });

  $('textarea#textComment1').parsley('addConstraint', 
    {'required': 'true'});
  $('textarea#textComment2').parsley('addConstraint', 
    {'required': 'true'});

  $('input#index-username').parsley('addConstraint', 
    {'required': 'true'});
  $('input#index-password').parsley( 'addConstraint', 
    {'required': 'true'/*,
      'regexp': '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$'*/});
  $('input#parsley-user').parsley('addConstraint', 
    {'required': 'true'});
  $('input#parsley-pass').parsley( 'addConstraint', 
    {'required': 'true'/*,
      'regexp': '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$'*/});
  $( 'input#valid_fname' ).parsley( 'addConstraint', 
    {'required': 'true'});
  $( 'input#valid_lname' ).parsley( 'addConstraint', 
    {'required': 'true'});
  $( 'input#valid_username' ).parsley( 'addConstraint', 
    {'required': 'true'});
  $('input#valid_pass').parsley( 'addConstraint', 
    {'required': 'true'/*,
     'regexp': "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$"*/});
  $('input#valid_cpass').parsley( 'addConstraint', 
    {'required': 'true',
      'equalto': '#valid_pass'/*,
      'regexp': "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$"*/});
  $('input#valid_email').parsley( 'addConstraint', 
    {'required': 'true',
      'type': 'email'});
  $('input#login-username').parsley('addConstraint', 
    {'required': 'true'});
  $('input#login-password').parsley( 'addConstraint', 
    {'required': 'true'/*,
      'regexp': '^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,8}$'*/});


});


