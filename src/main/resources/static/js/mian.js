
// db상의 존재하는 카테고리 json 가져오기 html 뿌리기
function gettestlist() {
    $.ajax({
       url:"/test/getcatlist",
       success : function (re){
           let html = "";
           for(let i =0; i < re.length ; i++){
               html += '<option>'+re[i]+'</option>';
           }
           $("#catselect").html(html);
       }
    });
}

//
