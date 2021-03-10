function login() {
    $.ajax({
        type: "POST",
        //dataType: "json",
        url: "https://10.86.164.216:8080/api/auth/login",
        data: $('#loginForm').serialize(),
        success: function (result) {
            if (result.code == 200){
                $.cookie("token",result.data);
                window.location.href = "/discussion";
            }
        },
        // error: function () {
        //     alert("Incorrect Email Or Password");
        // }
        error: function(message){
            var json = message.responseJSON;
            console.log(json);
            alert(json.message);
            if (json.status == "401" || json.message == "Token Corrupted"){
                window.location.href = "/login";
            }
        }
    });
}

$("[type='button']").click(login);