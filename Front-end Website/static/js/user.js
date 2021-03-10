function submit_pwd(){
    var pwd = $("#pwd").val();
    var newPwd = $("#newPwd").val();
    var retype = $("#retype").val();

    if (newPwd === retype){
        $.ajax({
            type: "PATCH",
            url: "https://10.86.164.216:8080/api/users",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", $.cookie("token"));
            },
            data: {"pwd": pwd, "newPwd": newPwd},
            success: function (result) {
                alert("Change Password Successfully!");
                window.location.href="/logout";
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
    }else{
        alert("Password Mismatch! Please retype your password!");
        $("#pwd").val("");
        $("#newPwd").val("");
        $("#retype").val("");
    }



}

function dropCourse(id){
    $.ajax({
        type: "PATCH",
        url: "https://10.86.164.216:8080/api/users/courses",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        data: {"id": id},
        success: function (result) {
            alert("Drop Course Successfully!");
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
    // $("#submit_theme").on( "click", change_theme());
    layui.use('form', function () {
        var form = layui.form;
    });

    $("#profile").show();
    $("#theme_management").hide();
    $("#my_courses").hide();
    $("#my_exercises").hide();
    $("#my_posts").hide();


    $("#cour1").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course1").addClass("course-active");
        $("#course5").removeClass("course-active");
        $("#my_posts").hide();
        $("#profile").show();
        $("#theme_management").hide();
        $("#my_courses").hide();
        $("#my_exercises").hide();

    });

    $("#cour2").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course2").addClass("course-active");

        $("#course5").removeClass("course-active");
        $("#my_posts").hide();
        $("#my_courses").show();
        $("#profile").hide();
        $("#theme_management").hide();
        $("#my_exercises").hide();
    });

    $("#cour3").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course3").addClass("course-active");
        $("#course5").removeClass("course-active");
        $("#my_posts").hide();
        $("#my_courses").hide();
        $("#profile").hide();
        $("#theme_management").hide();
        $("#my_exercises").show();
    });
    $("#cour5").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");

        $("#course5").removeClass("course-active");
        $("#course5").addClass("course-active");
        $("#my_posts").hide();
        $("#my_courses").hide();
        $("#profile").hide();
        $("#theme_management").hide();
        $("#my_exercises").show();
    });



    $("#cour4").click(function () {
        $("#course1").removeClass("course-active");
        $("#course2").removeClass("course-active");
        $("#course3").removeClass("course-active");
        $("#course4").removeClass("course-active");
        $("#course4").addClass("course-active");
        $("#course5").removeClass("course-active");
        $("#my_posts").hide();
        $("#my_courses").hide();
        $("#profile").hide();
        $("#theme_management").show();
        $("#my_exercises").hide();


    });
    $("#allcourses").empty();
    
    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/api/users/courses",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {

            for (var i = 0; i < result.length; i++) {
                $("#allcourses").append("<li>\n" +
                    "                            <h2>\n" +
                    "                                <a href=\"javascript:;\" onclick=\"dropCourse("+result[i].courseId+")\" class=\"layui-badge\">DROP</a>\n" +
                    "                                <a href=\"javascript:;\">"+result[i].courseCode+"</a>\n" +
                    "                            </h2>\n" +
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

    $("#fav_exercises").empty();
    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/api/users/exercises",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {

            for (var i = 0; i < result.length; i++) {
                var difficulty = "";
                if(result[i].difficulty == 0){
                    difficulty = "Easy"
                }else if(result[i].difficulty == 1){
                    difficulty = "Medium"
                }else if(result[i].difficulty == 2){
                    difficulty = "Hard"
                }


                $("#fav_exercises").append("<li>\n" +
                    "                            <h2>\n" +
                    "                                <a class=\"layui-badge\">"+difficulty+"</a>\n" +
                    "                                <a href=\"/pdetails/"+result[i].exerciseId+"\">"+result[i].title+"</a>\n" +
                    "                            </h2>\n" +
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
});