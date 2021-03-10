$("#show_all").click(function () {
    $("#category_list").css("height", "100%");
});

$("#fold_all").click(function () {
    $("#category_list").css("height", "105px");
});

function updateProblem(id){
    $("#problems").empty();
    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/api/exercises",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        data:{
            "id":id
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                if (result[i].category == result[i].category ){
                    var difficulty = "";
                    var difficultyClass = "";
                    if (result[i].difficulty == 0) {
                        difficulty = "Easy";
                        difficultyClass = "easy_p";
                    } else if (result[i].difficulty == 1) {
                        difficulty = "Medium";
                        difficultyClass = "medium_p";
                    } else if (result[i].difficulty == 2) {
                        difficulty = "Hard";
                        difficultyClass = "hard_p";
                    }
                    $("#problems").append("<tr>\n" +
                        "                    <td><a href=\"/pdetails/"+result[i].exerciseId+"\">"+result[i].exerciseId+"</a></td>\n" +
                        "                    <td><span class=\""+difficultyClass+"\">"+difficulty+"</span></td>\n" +
                        "                    <td><a href=\"/pdetails/"+result[i].exerciseId+"\">"+result[i].title+"</a></td>\n" +
                        "                </tr>")
                }
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
    $("#column0").empty();
    $("#column1").empty();
    $("#column2").empty();
    $("#column3").empty();
    $("#problems").empty();

    $.ajax({
        type: "GET",
        url: "https://10.86.164.216:8080/api/exercise_category",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {
            var len = result.length;
            for (var i = 0; i < result.length; i++) {
                var idstr = "#column";
                var temp = i % 4;
                idstr = idstr + temp;
                $(idstr).append("<div class=\"layui-card\" style=\"margin-right: 10px;\">\n" +
                    "                        <div style=\"text-align: center;\" class=\"layui-card-header\"><a\n" +
                    "                                class=\"iconfont icon-tianjia\" href=\"#\" onclick=\"updateProblem(" + result[i].categoryExerciseId + ")\"></a></div>\n" +
                    "                        <div style=\"text-align: center; margin-top: -1px;\" class=\"layui-card-body\">\n" +
                                                    "<a href=\"#\" onclick=\"updateProblem(" + result[i].categoryExerciseId + ")\">"+
                                                    result[i].content +
                    "                        </a></div>\n" +
                    "                    </div>")
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
        url: "https://10.86.164.216:8080/api/exercises",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                var difficulty = "";
                var difficultyClass = "";
                if (result[i].difficulty == 0){
                    difficulty = "Easy";
                    difficultyClass = "easy_p";
                } else if (result[i].difficulty == 1){
                    difficulty = "Medium";
                    difficultyClass = "medium_p";
                } else if (result[i].difficulty == 2) {
                    difficulty = "Hard";
                    difficultyClass = "hard_p";
                }

                $("#problems").append("<tr>\n" +
                    "                    <td><a href=\"/pdetails/"+result[i].exerciseId+"\">"+result[i].exerciseId+"</a></td>\n" +
                    "                    <td><span class=\""+difficultyClass+"\">"+difficulty+"</span></td>\n" +
                    "                    <td><a href=\"/pdetails/"+result[i].exerciseId+"\">"+result[i].title+"</a></td>\n" +
                    "                </tr>")
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
