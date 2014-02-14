// below is the improved ("tag-it!") version for autocomplete on tags
// entityTags is for load, tag_query is for search bar
$(function(){
    var entityTags = $('#entityTags');
    entityTags.tagit({
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
                    url: "/tagcloud",
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
        // below is for save/remove based on event instead of on save button
        //afterTagAdded: function(event, ui) {
        //    if (!ui.duringInitialization) {
        //        //addEvent('afterTagAdded: ' + eventTags.tagit('tagLabel', ui.tag));
        //        alert('added: ' + entityTags.tagit('tagLabel', ui.tag));
        //    }
        //},
        //afterTagRemoved: function(event, ui) {
        //    alert('removed: ' + entityTags.tagit('tagLabel', ui.tag));
        //}
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
                    url: "/tagcloud",
                    data: { format: "json", term: request.term },
                    dataType: 'json',
                    //contentType: 'application/json; charset=utf-8',
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