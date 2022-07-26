

function login(){
    let mid = $("#mid").val();
    let mpassword = $("#mpassword").val();

    $.ajax({
        url: "/member/logincontroller",
        data: {"mid" : mid, "mpassword" : mpassword},
        success : function (re) {
            console.log(re);
        }
    })
}