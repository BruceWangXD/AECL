var count = 0;

$(document).ready(function () {
    var regex = /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,12}$/;
    //表单验证
    $("#enrol").click(function () {
        var year = $("input[type='radio']:checked").val();
        var userName = $("#userName").val();
        var email = $("#email").val();
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        var agree = $("#brand").is(':checked');
        var atpos = email.indexOf("@");
        var dotpos = email.lastIndexOf(".");

        if (year == undefined || userName == "" || email == "" || password == "") {
            alert("Please complete the form before enrolment!");
            return false;
        } else if (password != repassword) {
            $("#password").val("");
            $("#repassword").val("");
            alert("Please confirm the correctness of your password");
            return false;
        } else if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
            alert("Invalid email address!");
            return false;
        } else if (!agree) {
            alert("Please agree the privacy policy and user agreement!");
        } else if(!regex.test(password)){
            alert("Password should be of length 6-12 with number and lowercase letter and uppercase letter")
        } else {
            $("#enrolModal").modal('show');
        }
    });
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "https://10.86.164.216:8080/api/courses",
        // data: $('#loginForm').serialize(),
        success: function (result) {

            $("#course-gen").empty();
            for (var i = 0; i < result.length; i++) {
                $("#course-gen").append("<a href=\"#\" class=\"list-group-item list-group-item-action flex-column align-items-start\">\n" +
                    "                                <div class=\"d-flex w-100 justify-content-between\">\n" +
                    "                                  <h5 class=\"mb-1\">" + result[i].courseCode + "</h5>\n" +
                    "                                  <input type=\"checkbox\" name=\"course\" value=\"" + result[i].courseId + "\">\n" +
                    "                                </div>\n" +
                    "                                <p class=\"mb-1\">" + result[i].courseName + "</p>\n" +
                    "                                <small class=\"text-muted\">" + result[i].instructorName + "</small>\n" +
                    "                            </a>")
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
        dataType: "json",
        url: "https://10.86.164.216:8080/api/courses/hot",
        success: function (result) {
            $("#recommend-gen").empty();
            for (var i = 0; i < result.length; i++) {
                $("#recommend-gen").append("<a href=\"#\" class=\"list-group-item list-group-item-action flex-column align-items-start\">\n" +
                    "                                <div class=\"d-flex w-100 justify-content-between\">\n" +
                    "                                  <h5 class=\"mb-1\">" + result[i].courseCode + "</h5>\n" +
                    "                                  <small class=\"text-muted\">" + result[i].population + "</small>" +
                    "                                </div>\n" +
                    "                                <p class=\"mb-1\">" + result[i].courseName + "</p>\n" +
                    "                                <small class=\"text-muted\">" + result[i].instructorName + "</small>\n" +
                    "                            </a>")
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

$(document).on("click", "#registration-submit", function () {
    var year = $("input[type='radio']:checked").val();
    var userName = $("#userName").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var courses = new Array();//course array
    var i = 0;
    $("input:checkbox:checked").each(function () {
        if ($(this).attr("disabled") != "disabled") {
            courses[i] = $(this).val();
            i += 1;
        }
    });
    courses.shift();
    console.log(courses);

    $.ajax({
        type: "POST",
        url: "https://10.86.164.216:8080/api/users",
        data: {
            "academicYear": year,
            "userName": userName,
            "email": email,
            "password": password,
            "pcourse1": courses[0],
            "pcourse2": courses[1],
            "pcourse3": courses[2],
            "pcourse4": courses[3]
        },
        success: function (result) {
            //打印服务端返回的数据(调试用)
            window.location.href = "/login";
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

$(document).on("click", ".course_list a", function () {
    var checkbox = $(this).find("input[type='checkbox']");
    if (checkbox.attr("checked") == "checked") {
        checkbox.removeAttr("checked");
        count -= 1;
        if (count <= 3) {
            $("input[type='checkbox']").each(function () {
                if (!$(this)[0].checked) {
                    $(this).removeAttr("disabled");
                }
            });
        }
    } else {
        checkbox.attr("checked", true);
        count += 1;
        if (count >= 4) {
            $("input[type='checkbox']").each(function () {
                if (!$(this)[0].checked) {
                    $(this).attr("disabled", "disabled");
                }
            });
        }
    }
});