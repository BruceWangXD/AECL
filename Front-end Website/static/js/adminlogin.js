function login() {
    $.ajax({
        type: "POST",
        url: "https://10.86.164.216:8080/api/auth/adminLogin",
        data: $('#loginForm').serialize(),
        success: function (result) {
            if (result.code == 200){
                $.cookie("atoken",result.data);
                window.location.href = "/admin";
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
                window.location.href = "/alogin";
            }
        }
    });
}

$("[type='button']").click(login);