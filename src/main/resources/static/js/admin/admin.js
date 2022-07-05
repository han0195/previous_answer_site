

// 비밀번호 체크 함수
function pwcheck(){
    let pw = "rhksflwk123"; // 임시 관리자 key

    if(pw == $("#password").val()){
        alert("확인되었습니다. : 관리자페이지로 이동합니다.");
        location.href = "/admin/adminpage";
    }else{
        alert("접근권한이없습니다.");
    }

}



// 시험 등록
function savetest(){
    let form = $("#savetest")[0];
    let formdata = new FormData(form);

    $.ajax({
        url: "/admin/inserttest",
        method: "POST",
        data : formdata ,
        contentType: false,     // 첨부파일 전송시 사용되는 속성
        processData: false ,     // 첨부파일 전송시 사용되는 속성
        success : function( re ){
           if(re){
               alert("저장성공")
           }else{
               alert("뭔가가 잘못되었습니다. 다시확인")
           }
        }
    });
}
