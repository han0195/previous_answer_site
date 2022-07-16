
let count = 0;

// 문제추가
function examinfosave(){

    let form = $("#info")[0];
    let formdata = new FormData(form);

    //폼전송
    $.ajax({
        url: "/admin/saveinfo",
        type: "POST",
        data: formdata,
        contentType: false,
        processData: false,
        success : function (re){
            if(re){ //시험이 정상적으로 등록되었다면
                location.href="/admin/main";
            }else{
                alert("시험정보 저장 에러");
            }
        }
    });

}
