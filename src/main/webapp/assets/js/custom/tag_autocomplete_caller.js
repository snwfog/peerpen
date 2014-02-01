// below is the improved ("tag-it!") version for autocomplete on tags
// entityTags is for load, tag_query is for search bar
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
                    url: "/tag_autocomplete_ajax",
                    data: { format: "json", term: request.term },
                    dataType: 'json',
                    type: 'POST',
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
                    url: "/tag_autocomplete_ajax",
                    data: { format: "json", term: request.term },
                    dataType: 'json',
                    type: 'POST',
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