
function showSolutions(){
    $("#solution").show();
}

function addToFav(){
    var pid = GetUrlRelativePath();
    $.ajax({
        type: "POST",
        url: "https://10.86.164.216:8080/api/exercises/fav",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        data:{
            "eid":pid
        },
        success: function (result) {

            alert("Add to Favourite Success!")
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

function GetUrlRelativePath(){
    var url = document.location.toString();
    var arrUrl = url.split("/");
    var start = arrUrl[arrUrl.length-1].indexOf("/");
    var relUrl = arrUrl[arrUrl.length-1].substring(start);//stop省略，截取从start开始到结尾的所有字符
    return relUrl;
}


$(document).ready(function () {
    var pid = GetUrlRelativePath();
    var urlstr = "https://10.86.164.216:8080/api/exercises/"+pid;
    $.ajax({
        type: "GET",
        url: urlstr,
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", $.cookie("token"));
        },
        success: function (result) {

            var difficulty = "";
            if (result.difficulty == 0){
                difficulty = "Easy";
            } else if (result.difficulty == 1){
                difficulty = "Medium";
            } else if (result.difficulty == 2) {
                difficulty = "Hard";
            }
            $("#pid").text(result.exerciseId);
            $("#ptitle").text(result.title);
            $('title').text(result.title);
            $("#difficulty").text(difficulty);
            $("#solution").text(result.solution);
            $("#problem_text p").text(result.content);
            $("#solution").hide();
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