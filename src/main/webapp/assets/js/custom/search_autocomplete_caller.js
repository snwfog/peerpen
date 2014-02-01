// import this script if you need autocomplete for search, the actual logic of autocomplete is in jquery-1.9.1.js and ui/1.10.4/jquery-ui.js
$(function() {
    $( "#search_query" ).autocomplete({
        source: function(request, response) {
            $.ajax({
                type: "post",
                url: "/search_autocomplete_ajax",
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
        minLength: 3
    });
});