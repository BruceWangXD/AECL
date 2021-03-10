function submitPost(){
    $.ajax({
        type: "POST",
        url: "https://10.86.164.216:8080/api/posts",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        data: $('#post_form').serialize(),
        success: function (result) {
            alert("Post Success!");
            // window.location.reload();
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


function getPostDetails(cid){
    var parentNode = $(this).parent();
    console.log(parentNode);
    // parentNode.addClass("course-active");
    parentNode.css("background-color","#5FB878");
    parentNode.siblings().removeClass("course-active");
    $("#post_panel").hide();
    $("#post_details").show();
    $("#posts_list").empty();
    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/api/posts",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        data:{
            "cid":cid
        },
        success: function (result) {
            $("#posts_list").empty();
            for (var i = 0; i < result.length; i++) {
                $("#posts_list").append("<li>\n" +
                    "                            <h2>\n" +
                    "                                <a href=\"/postdetails/"+result[i].postId+"\">"+result[i].postTitle+"</a>\n" +
                    "                            </h2>\n" +
                    "                            <div class=\"fly-list-info\">\n" +
                    "                                <a href=\"#\" link>\n" +
                    "                                    <cite>"+result[i].personName+"</cite>\n" +
                    "                                </a>\n" +
                    "                                <span>"+result[i].postTime+"</span>\n" +
                    "                            </div>\n" +
                    "                        </li>");
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

}


$(document).ready(function () {
    $("#post_panel").hide();
    $("#post_details").hide();

    $("#courseList").empty();
    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/api/users/courses",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {
            $("#courseList").empty();
            $("#new_post_courselist").empty().append("<option value=\"\">Choose Course</option>");
            for (var i = 0; i < result.length; i++) {
                $("#courseList").append("<li id=\"course"+(i+1)+"\">\n" +
                    "                            <div class=\"fly-list-info\">\n" +
                    "                                <a id=\"cour"+(i+1)+"\" href=\"javascript:;\" onclick=\"getPostDetails("+result[i].courseId+")\">\n" +
                    result[i].courseCode +
                    "                                </a>\n" +
                    "                            </div>\n" +
                    "                        </li>");
                $("#new_post_courselist").append(" <option value=\""+result[i].courseId+"\">"+result[i].courseCode+"</option>\n");
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







    $("#post_action").click(function () {
        $("#post_details").hide();
        $("#post_panel").show();
    });

    $("#submit_post").click(function () {
        $("#post_panel").hide();
        $("#post_details").show();
    });

    $("#discard_post").click(function () {
        $("#post_panel").hide();
        $("#post_details").show();
    });


    $("#cour1").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course1").addClass("course-active");
    });

    $("#cour2").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course2").addClass("course-active");
    });

    $("#cour3").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course3").addClass("course-active");
    });

    $("#cour4").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course4").addClass("course-active");
    });
});