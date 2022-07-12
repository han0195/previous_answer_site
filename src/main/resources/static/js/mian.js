
// db상의 존재하는 카테고리 json 가져오기 html 뿌리기
function gettestlist() {
    $.ajax({
       url:"/test/getcatlist",
       success : function (re){
           let html = '<option value="N" selected>학과 선택</option>';
           for(let i =0; i < re.length ; i++){
               html += '<option value="'+re[i].tcno+'">'+re[i].tcname+'</option>';
           }
           $("#catselect").html(html);
       }
    });
};

// 학과선택 select change 이벤트
$("#catselect").on("change", function (){
    let tempchack = $("#catselect").val();
    if(tempchack == "N"){ // 기본선택이라면
        $("#gradech").css({"display" : "none"});
        $("#testtitle").css({"display" : "none"});
        $("#testof").css({"display" : "none"});
        $("#testtyear").css({"display" : "none"});
    }else{ // 카테고리를 선택했다면
        // 다음 선택 창 보여주기
        $("#gradech").css({"display" : ""});
    };
});

// 학년선택 select change 이벤트
$("#gradech").on("change", function (){
    let tempchack = $("#gradech").val();
    alert(tempchack);
    let tcno = $("#catselect").val();
    if(tempchack == "N"){ // 기본선택이라면
        $("#testtitle").css({"display" : "none"});
        $("#testof").css({"display" : "none"});
        $("#testtyear").css({"display" : "none"});
    }else{ // 카테고리를 선택했다면
        // 시험과목 리스트가져오기
        $.ajax({
            url:"/test/tgettestlist",
            data: {"tcno" : tcno, "tgrade" : tempchack},
            success : function (re) {
                if(re.length == 0){ // 과목이 존재하지않으면
                    alert("해당과목은 존재하지않습니다.");
                }else{
                    let html = '<option value="N">과목선택</option>>';
                    for(let i = 0; i < re.length; i++){
                        html += '<option value="'+re[i].tno+'">'+re[i].tname+'</option>';
                    }
                    $("#testtitle").html(html);
                }
            }
        });
        // 다음 선택 창 보여주기
        $("#testtitle").css({"display" : ""});
    };
});

// 과목선택 select change 이벤트
$("#testtitle").on("change", function (){
    let tempchack = $("#testtitle").val();
    if(tempchack == "N"){ // 기본선택이라면
        $("#testof").css({"display" : "none"});
        $("#testtyear").css({"display" : "none"});
    }else{ // 카테고리를 선택했다면
        // 다음 선택 창 보여주기
        $("#testof").css({"display" : ""});
    };
});

// 시험구분 select change 이벤트
$("#testof").on("change", function (){
    let tempchack = $("#testof").val();
    if(tempchack == "N"){ // 기본선택이라면

    }else{ // 카테고리를 선택했다면
        // 다음 선택 창 보여주기
        $("#testtitle").css({"display" : ""});
    };
});

// 기출연도 select change 이벤트
$("#testtyear").on("change", function (){
    let tempchack = $("#testtyear").val();
    if(tempchack == "N"){ // 기본선택이라면
    }else{ // 카테고리를 선택했다면

    };
});

