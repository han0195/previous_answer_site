

// 학습시작 버튼 클릭시 이동 함수
function pasgemove(idkey){
    let offset = $('#'+idkey+'').offset();
    $("html, body").animate({scrollTop: offset.top}, 200);
}