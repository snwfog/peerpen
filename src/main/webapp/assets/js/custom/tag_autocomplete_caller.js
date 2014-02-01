// this autocomplete is different from the one used in search
// this allows hitting tab key to add more tag in one type
//$(function() {
//    function split( val ) {
//        return val.split( /,\s*/ );
//    }
//    function extractLast( term ) {
//        return split( term ).pop();
//    }
//
//    $( "#tag_query" )
//        // don't navigate away from the field on tab when selecting an item
//        .bind( "keydown", function( event ) {
//            if ( event.keyCode === $.ui.keyCode.TAB &&
//                $( this ).data( "ui-autocomplete" ).menu.active ) {
//                event.preventDefault();
//            }
//        })
//        .autocomplete({
//            source: function( request, response ) {
//                $.getJSON( "/tag_autocomplete_ajax.do", {
//                    term: extractLast( request.term )
//                }, response );
//            },
//            search: function() {
//                // custom minLength
//                var term = extractLast( this.value );
//                if ( term.length < 2 ) {
//                    return false;
//                }
//            },
//            focus: function() {
//                // prevent value inserted on focus
//                return false;
//            },
//            select: function( event, ui ) {
//                //$(this ).before("<span class='tag'>" + ui.item.value + "</span>"); // ui
//                var terms = split( this.value );
//                // remove the current input
//                terms.pop();
//                // add the selected item
//                terms.push( ui.item.value );
//                // add placeholder to get the comma-and-space at the end
//                terms.push( "" );
//                this.value = terms.join( ", " );
//                return false;
//            }
//        });
//});



// below is the improved ("tag-it!") version

$(function(){
    $('#entityTags').tagit({
        // configure the name of the input field (will be submitted with form), default: item[tags]
        itemName: 'item',
        fieldName: 'tags',
        caseSensitive: false,
        allowSpaces: false,
        tagLimit: 5,
        singleField: true,
        autocomplete: ({
            source: function (request, response) {
                $.ajax({
                    url: "/tag_autocomplete_ajax.do",
                    data: { format: "json", term: request.term },
                    dataType: 'json',
                    type: 'GET',
                    success: function (data) {
                        response($.map(data, function (item) {
                            return {
                                label: item,
                                value: item
                            }
                        }));
                    },
                    error: function (request, status, error) {
                        alert(error);
                    }})},
            minLength: 2
        })
    });


    $('#tag_query').tagit({
        allowSpaces: false,
        singleField: true,
        caseSensitive: false,
        tagLimit: 3,
        placeholderText: 'Find goodies by tag(s)',
        autocomplete: ({
            source: function (request, response) {
                $.ajax({
                    url: "/tag_autocomplete_ajax.do",
                    data: { format: "json", term: request.term },
                    dataType: 'json',
                    type: 'GET',
                    success: function (data) {
                        response($.map(data, function (item) {
                            return {
                                label: item,
                                value: item
                            }
                        }));
                    },
                    error: function (request, status, error) {
                        alert(error);
                    }})},
            minLength: 2
        })
    });
});