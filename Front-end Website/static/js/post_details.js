function GetUrlRelativePath(){
    var url = document.location.toString();
    var arrUrl = url.split("/");
    var start = arrUrl[arrUrl.length-1].indexOf("/");
    var relUrl = arrUrl[arrUrl.length-1].substring(start);//stop省略，截取从start开始到结尾的所有字符
    return relUrl;
}

function submitComment(){
    var pid = GetUrlRelativePath();
    var commentStr = "https://10.86.164.216:8080/api/comments/" + pid;
    var content = $("#content_msg").val();
    $.ajax({
        type: "POST",
        url: commentStr,
        data:{
            "content":content
        },
        beforeSend: function (request){
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {
            alert("Comment Success!");
            window.location.reload();
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



$(document).ready(function () {
    var pid = GetUrlRelativePath();
    var urlstr = "https://10.86.164.216:8080/api/posts/"+pid;
    $.ajax({
        type: "GET",
        url: urlstr,
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {

            $("#post_title").text(result.postTitle);
            $("#author").text(result.personName);
            $("#post_time").text(result.postTime);
            $("#post_content").text(result.postContent);
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


    var commentstr = "https://10.86.164.216:8080/api/comments/"+pid;
    $("#commentList").empty();
    $.ajax({
        type: "GET",
        url: commentstr,
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {

            for (let i = 0; i < result.length; i++) {
                $("#commentList").append("<li class=\"jieda-daan\" name=\"reply\" id=\"reply\">\n" +
                    "                        <div class=\"detail-about detail-about-reply\">\n" +
                    "                            <a class=\"fly-avatar\" href=\"#\"><img src=\"../img/person.png\" alt=\"avatar\"></a>\n" +
                    "                            <div class=\"fly-detail-user\">\n" +
                    "                                <a href=\"#\" class=\"fly-link\">\n" +
                    "                                    <cite>"+result[i].personName+"</cite>\n" +
                    "                                </a>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"detail-hits\">\n" +
                    "                                <span>"+result[i].commentTime+"</span>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"detail-body jieda-body photos\">\n" +
                    "                            <p>"+result[i].content+"</p>\n" +
                    "                        </div>\n" +
                    "                    </li>")
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

});