var search_autocomplete = true;
if(search_autocomplete){
    $(document).ready(function() {
        $('#search_query').keyup(function(event)
        {
            var keyin = $('#search_query').val();
            $.post('search_autocomplete.do', {
                keyin: keyin
            }, function(responseJson) {
                var ul = $('#suggestion_list');    // locate the ul dom
                ul.empty();     // remove existing li's
                $.each(responseJson, function(key, value) {
                    // Iterate over the JSON object.
                    var li = $('<li id="suggest_item" style="display:block;text-align:left;padding:5px">').text( value );
                    ul.append(li);
                    li.mouseover(function(){
                        $(this).css("background-color","gray");
                    });
                    li.mouseout(function(){
                        $(this).css("background-color", "white");
                    });
                    li.click(function(){
                        $('#search_query').val(li.text());
                    });
                    if (event.keyCode == 40) { // down
                        //li.siblings().first().css("background-color","gray");
                    }
                });
            });
        });
    });
}