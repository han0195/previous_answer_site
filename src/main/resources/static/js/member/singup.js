
/* 유효성 검사 */
let pass = [false, false, false];

$(document).ready(function(){
    /* mid keydown 이벤트 */
    $("#inputEmail4").keyup(function (e) {
        let mid = $("#inputEmail4").val();
        let midre = /^[A-Za-z0-9]{6,15}$/;
        if(midre.test(mid)){
            $("#midspan").html("");
            pass[0] = true;
        }else{
            let html = "6~ 15자리로 입력해주세요";
            $("#midspan").html(html);
        }
    });


    /* mpassword keydown 이벤트 */
    $("#inputPassword4").keyup(function (e) {
        let mpassword = $("#inputPassword4").val();
        let mpasswordre = /^[A-Za-z0-9]{6,15}$/;
        if(mpasswordre.test(mpassword)){
            $("#mpasswordspan").html("");
            pass[1] = true;
        }else{
            let html = "6~ 15자리로 입력해주세요";
            $("#mpasswordspan").html(html);
        }
    });

    /* mname keydown 이벤트 */
    $("#inputAddress").keyup(function (e) {
        let mname = $("#inputAddress").val();
        let mnamere = /^[A-Za-z0-9가-힣]{2,15}$/;
        if(mnamere.test(mname)){
            $.ajax({
                url : "/member/selectmname",
                data: {"mname" : mname},
                type: "POST",
                success : function (re){
                    if(re){
                        $("#mnamespan").html("중복된 별명입니다.");
                    }else{
                        $("#mnamespan").html("");
                        pass[2] = true;
                    }
                }
            })
        }else{
            console.log("s");
            let html = "2~15자리이상입력해주세요";
            $("#mnamespan").html(html);
        }
    });
});


function signup(){
    if(pass.indexOf(false) != -1){ /* 유효성 실패 */
        alert("모든항목을 입력해주세요");
    }else{ /* 유효성 성공 */

        let form = $("#singupinfo")[0];
        let formdata = new FormData(form);

        $.ajax({
           url : "/member/singup",
            type: "POST",
            data : formdata,
            contentType: false,
            processData: false,
            success : function (re){
               if(re){ /* 회원가입 성공 */
                   location.href = "/";
               }else{ /* 회원가입 실패 */
                   alert("서비스 에러");
               }
            }
        });
    }
}


