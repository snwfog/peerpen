// import this script if you need autocomplete for search, the actual logic of autocomplete is in jquery-1.9.1.js and ui/1.10.4/jquery-ui.js
$(function() {
    $( "#search_query" ).autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "/search_autocomplete_ajax.do",
                dataType: "json",
                data: {
                    term: request.term,
                    area: $("input[type='radio'][name='area']:checked").val()
                },
                success: function(data) {
                    response(data);
                }
            });
        },
        //source: "search_autocomplete_ajax.do", // param name must be 'term'
        minLength: 3
    });
});




// custom autocomplete (below) is currently replaced by jquery autocomplete, but keep it just in case
//var search_autocomplete = true;
//if(search_autocomplete){
//    $(document).ready(function() {
//        $('#search_query').keyup(function(event)
//        {
//            var term = $('#search_query').val();
//            $.post('search_autocomplete.do', {
//                term: term
//            }, function(responseJson) {
//                var ul = $('#suggestion_list');    // locate the ul dom
//                ul.empty();     // remove existing li's
//                $.each(responseJson, function(key, value) {
//                    // Iterate over the JSON object.
//                    var li = $('<li id="suggest_item" style="display:block;text-align:left;padding:5px">').text( value );
//                    ul.append(li);
//                    li.mouseover(function(){
//                        $(this).css("background-color","gray");
//                    });
//                    li.mouseout(function(){
//                        $(this).css("background-color", "white");
//                    });
//                    li.click(function(){
//                        $('#search_query').val(li.text());
//                    });
//                    if (event.keyCode == 40) { // down
//                        //li.siblings().first().css("background-color","gray");
//                    }
//                });
//            });
//        });
//    });
//}