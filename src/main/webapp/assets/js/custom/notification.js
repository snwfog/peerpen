function popNotification(message){
    var havePermission = window.webkitNotifications.checkPermission();
    if (havePermission == 0) {
        // 0 is PERMISSION_ALLOWED
        var notification = window.webkitNotifications.createNotification(
            'http://i.stack.imgur.com/dmHl0.png',
            'Peerpen notification!',
            message
        );

        notification.onclick = function () {
            //                        window.open("http://stackoverflow.com/a/13328397/1269037");
            notification.close();
        }
        notification.show();
    } else {
        window.webkitNotifications.requestPermission();
    }
    return;
}
//*Leave there for now
//function baseUrl() {
//    var href = window.location.href.split('/');
//    return href[0]+'//'+href[2]+'/';
//}
$(function () {

    var fetchNotification = function (){
        $.ajax({
            type: "POST",
            url: "/notification",
            dataType: "json",
            data: {
                format: "json"
            },
            success: function(data) {
                var ctr_changeset = 0;
                var ctr_comment = 0;

                $.each(data, function(i, row){
                    switch (row["type"]){
                        case "Changeset" : ctr_changeset ++; break;
                        case "Comment" : ctr_comment ++; break;
                        default : break;
                    }
                });

                if(ctr_changeset > 0){
                    popNotification("you got " + ctr_changeset + " changesets");
                }

                if(ctr_comment > 0){
                    popNotification("you got " + ctr_comment + " comments");
                }

            }
        });
    }

    if ($("#enableNotification" ).length > 0){
        fetchNotification();
        setInterval(fetchNotification, 5000);
    }
});