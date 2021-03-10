function deleteComment(id){
    if (confirm("Are you sure to delete?")) {
        $.ajax({
            type: "DELETE",
            url: "https://10.86.164.216:8080/admin/comments",
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", $.cookie("atoken"));
            },
            data: {"id": id},
            success: function (result) {
                alert("DELETE SUCCESS!");
                window.location.reload();
            },
            error: function(message){
                var json = message.responseJSON;
                console.log(json);
                alert(json.message);
                if (json.status == "401" || json.message == "Token Corrupted"){
                    window.location.href = "/alogin";
                }
            }
        });
    }
}



function deleteUser(uid) {
    if (confirm("This command will delete all related Information of this user(Post, Comment, Favourite Exercise). Are you sure to delete?")) {
        $.ajax({
            type: "DELETE",
            url: "https://10.86.164.216:8080/admin/users",
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", $.cookie("atoken"));
            },
            data: {"id": uid},
            success: function (result) {
                alert("DELETE SUCCESS!");
                window.location.reload();
            },
            error: function(message){
                var json = message.responseJSON;
                console.log(json);
                alert(json.message);
                if (json.status == "401" || json.message == "Token Corrupted"){
                    window.location.href = "/alogin";
                }
            }
        });
    }
}

function deletePost(pid) {
    if (confirm("This command will delete all related info with this post(comment). Are you sure to delete?")) {
        $.ajax({
            type: "DELETE",
            url: "https://10.86.164.216:8080/admin/posts",
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", $.cookie("atoken"));
            },
            data: {"id": pid},
            success: function (result) {
                alert("DELETE SUCCESS!");
                window.location.reload();
            },
            error: function(message){
                var json = message.responseJSON;
                console.log(json);
                alert(json.message);
                if (json.status == "401" || json.message == "Token Corrupted"){
                    window.location.href = "/alogin";
                }
            }
        });
    }


}

function deleteExercise(eid) {
    if (confirm("Are you sure to delete?")) {
        $.ajax({
            type: "DELETE",
            url: "https://10.86.164.216:8080/admin/exercises",
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", $.cookie("atoken"));
            },
            data: {"id": eid},
            success: function (result) {
                alert("DELETE SUCCESS!");
                window.location.reload();
            },
            error: function(message){
                var json = message.responseJSON;
                console.log(json);
                alert(json.message);
                if (json.status == "401" || json.message == "Token Corrupted"){
                    window.location.href = "/alogin";
                }
            }
        });
    }
}


$(document).ready(function () {
    $("#exercise_content").empty();
    $("#user_content").empty();
    $("#post_content").empty();
    $("#comment_content").empty();
    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/admin/exercises",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", $.cookie("atoken"));
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                $("#exercise_content").append("<tr>\n" +
                    "                                        <td>\n" +
                    "                                            <button type=\"button\" onclick=\"deleteExercise("+result[i].exerciseId+")\" class=\"layui-btn layui-btn-danger layui-btn-sm\">DELETE</button>\n" +
                    "                                        </td>\n" +
                    "                                        <td>"+result[i].exerciseId+"</td>\n" +
                    "                                        <td>"+result[i].ecategoryId+"</td>\n" +
                    "                                        <td>"+result[i].title+"</td>\n" +
                    "                                    </tr>")
            }
        },
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == "401" || json.message == "Token Corrupted"){
                window.location.href = "/alogin";
            }
        }
    });


    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/admin/users",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", $.cookie("atoken"));
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                $("#user_content").append("<tr>\n" +
                    "                                        <td>\n" +
                    "                                            <button onclick=\"deleteUser("+result[i].personId+")\" type=\"button\" class=\"layui-btn layui-btn-danger layui-btn-sm\">DELETE</button>\n" +
                    "                                        </td>\n" +
                    "                                        <td>"+result[i].personId+"</td>\n" +
                    "                                        <td>"+result[i].userName+"</td>\n" +
                    "                                        <td>"+result[i].email+"</td>\n" +
                    "                                    </tr>")
            }
        },
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == "401" || json.message == "Token Corrupted"){
                window.location.href = "/alogin";
            }
        }
    });


    $.ajax({
        type: "GET",
        dataType: "json",
        url: "https://10.86.164.216:8080/admin/posts",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", $.cookie("atoken"));
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                $("#post_content").append("<tr>\n" +
                    "                                        <td>\n" +
                    "                                            <button type=\"button\" onclick=\"deletePost("+result[i].postId+")\" class=\"layui-btn layui-btn-danger layui-btn-sm\">DELETE</button>\n" +
                    "                                        </td>\n" +
                    "                                        <td>"+result[i].postId+"</td>\n" +
                    "                                        <td>"+result[i].personName+"</td>\n" +
                    "                                        <td>"+result[i].postTitle+"</td>\n" +
                    "                                    </tr>")
            }
        },
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == "401" || json.message == "Token Corrupted"){
                window.location.href = "/alogin";
            }
        }
    });

    $.ajax({
        type: "GET",
        dataType: "json",
        url: "https://10.86.164.216:8080/admin/comments",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", $.cookie("atoken"));
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                $("#comment_content").append("<tr>\n" +
                    "                                        <td>\n" +
                    "                                            <button type=\"button\" onclick=\"deleteComment("+result[i].commentId+")\" class=\"layui-btn layui-btn-danger layui-btn-sm\">DELETE</button>\n" +
                    "                                        </td>\n" +
                    "                                        <td>"+result[i].commentId+"</td>\n" +
                    "                                        <td>"+result[i].cpostId+"</td>\n" +
                    "                                        <td>"+result[i].personName+"</td>\n" +
                    "                                        <td>"+result[i].content+"</td>\n" +
                    "                                    </tr>")
            }
        },
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == "401" || json.message == "Token Corrupted"){
                window.location.href = "/alogin";
            }
        }
    });


    layui.use('form', function () {
        var form = layui.form;
    });

    $("#post_manage").hide();
    $("#exercise_manage").hide();
    $("#course_manage").hide();
    $("#comment_manage").hide();
    $("#user_manage").show();


    $("#cour1").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course5").removeClass("course-active");
        $("#course1").addClass("course-active");

        $("#user_manage").show();
        $("#post_manage").hide();
        $("#exercise_manage").hide();
        $("#course_manage").hide();
        $("#comment_manage").hide();



    });

    $("#cour2").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course5").removeClass("course-active");
        $("#course2").addClass("course-active");

        $("#course_manage").show();
        $("#post_manage").hide();
        $("#user_manage").hide();
        $("#exercise_manage").hide();
        $("#comment_manage").hide();
    });

    $("#cour3").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course5").removeClass("course-active");
        $("#course3").addClass("course-active");

        $("#post_manage").show();
        $("#exercise_manage").hide();
        $("#course_manage").hide();
        $("#user_manage").hide();
        $("#comment_manage").hide();

    });
    $("#cour5").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course5").removeClass("course-active");
        $("#course5").addClass("course-active");

        $("#course_manage").hide();
        $("#post_manage").hide();
        $("#user_manage").hide();
        $("#exercise_manage").hide();
        $("#comment_manage").show();
    });



    $("#cour4").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course5").removeClass("course-active");
        $("#course4").addClass("course-active");

        $("#course_manage").hide();
        $("#post_manage").hide();
        $("#user_manage").hide();
        $("#exercise_manage").show();
        $("#comment_manage").hide();
    });
});