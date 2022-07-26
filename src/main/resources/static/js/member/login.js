


function login(){
    let mid = $("#mid").val();
    let mpassword = $("#mpassword").val();

    $.ajax({
        url: "/member/logincontroller",
        data: {"mid" : mid, "mpassword" : mpassword},
        type: "POST",
        success : function (re) {

        }
    })
}