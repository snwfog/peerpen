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