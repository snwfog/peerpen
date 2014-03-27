function popNotification(message){
    var havePermission = window.webkitNotifications.checkPermission();
    if (havePermission == 0) {
        var notification = window.webkitNotifications.createNotification(
            'http://i.stack.imgur.com/dmHl0.png',
            'Peerpen notification!',
            message
        );

        notification.onclick = function () {
            notification.close();
        }
        notification.show();
    } else {
        window.webkitNotifications.requestPermission();
    }
    return;
}
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
                var ctr_group = 0;
                var ctr_broadcast = 0;

                $.each(data, function(i, row){
                    switch (row["type"]){
                        case "Changeset" : ctr_changeset ++; break;
                        case "Comment" : ctr_comment ++; break;
                        case "Joingroup" : ctr_group ++; break;
                        case "Broadcast" : ctr_broadcast ++; break;
                        default : break;
                    }
                });
                if(ctr_changeset > 0){
                    popNotification("you got " + ctr_changeset + " changesets");
                }
                if(ctr_comment > 0){
                    popNotification("you got " + ctr_comment + " comments");
                }
                if(ctr_group > 0){
                    popNotification("you got " + ctr_group + " group confirmations");
                }
                if(ctr_broadcast > 0){
                    popNotification("you got " + ctr_broadcast + " broadcast messages");
                }
            }
        });
    }
    //Start pulling from database at specific interval
    if ($("#enableNotification" ).length > 0){
        fetchNotification();
        setInterval(fetchNotification, 500000);
    }
});