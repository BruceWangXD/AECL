// layui.use('form', function () {
//     var form = layui.form;
// });

function show_message(mid) {
    $("#message_details").show();
    $("#send_panel").hide();
    var addr = "https://10.86.164.216:8080/api/messages/" + mid;
    $.ajax({
        type: "GET",
        dataType: "json",
        url: addr,
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {
            $("#message_title").text(result.title);
            // var temp = "<cite>" + result.senderName + "</cite>";
            $("#message_sender").text(result.senderName);
            $("#message_time").text(result.time);
            $("#message_content").text(result.content);
        },
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == 401){
                window.location.href="/login"
            }
        }
    });

}

function showSendPanel() {
    $("#message_details").hide();
    $("#send_panel").show();
}

$(document).ready(function () {
    var uuid = $.cookie('id');
    $("#message_details").hide();
    $("#send_panel").show();

    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/api/messages/",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {
            $("#message_list").empty();
            for (var i = 0; i < result.length; i++) {
                $("#message_list").append("<li>\n" +
                    "                            <div class=\"fly-list-info\">\n" +
                    "                                <h2><b><a href=\"#\" onclick=\"show_message(" + result[i].messageId + ")\">" + result[i].title + "</a></b></h2>\n" +
                    "                                <a href=\"#\" onclick=\"show_message(" + result[i].messageId + ")\">" + result[i].senderName + "</a>\n" +
                    "                                <a href=\"#\" onclick=\"show_message(" + result[i].messageId + ")\">" + result[i].time + "</a>\n" +
                    "                            </div>\n" +
                    "                        </li>")
            }
        },
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == 401){
                window.location.href="/login"
            }
        }
    });

    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/api/users/list",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {

            $("#user_list").empty().append("<option value=\"\">Select Receiver</option>");
            for (var i = 0; i < result.length; i++) {
                $("#user_list").append("<option value=\"" + result[i].personId + "\">" + result[i].userName + "</option>")
            }
            layui.use('form', function () {
                var form = layui.form;
            });
        },
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == 401){
                window.location.href="/login"
            }
        }
    });
});

function send_message() {
    $.ajax({
        type: "POST",
        url: "https://10.86.164.216:8080/api/messages",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        data: $('#send_form').serialize(),
        success: function (result) {
            alert("MESSAGE SENT!");
        },
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == 401){
                window.location.href="/login"
            }
        }

    });
}

