

// 학습시작 버튼 클릭시 이동 함수
function pasgemove(idkey){
    let offset = $('#'+idkey+'').offset();
    $("html, body").animate({scrollTop: offset.top}, 200);
}

// 오류 접수
function inserterror(){
    let econtent = $("#textarea").val();
    $.ajax({
        url:"/admin/inserterror",
        data:{"econtent" : econtent},
        type:"POST",
        success : function (re){
            if(re){ /* ... */
                alert("오류가 접수되었습니다 빨리 조치하겠습니다. 감사합니다");
            }else{
                alert("오류접수는 로그인후 가능합니다.");

            }
        }
    })
}
