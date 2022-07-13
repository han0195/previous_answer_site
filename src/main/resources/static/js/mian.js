
let testlist; // 시험리스트

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
                testlist = re;
                console.log(testlist);
                if(re.length == 0){ // 과목이 존재하지않으면
                    alert("해당과목은 존재하지않습니다.");
                }else{
                    let html = '<option value="N">과목선택</option>>';
                    let withchar = []; // 중복이름 방지
                    for(let i = 0; i < re.length; i++){
                        if(withchar.indexOf(re[i].tname) != -1){// 중복된이름이 있으면

                        }else{
                            html += '<option value="'+re[i].tname+'">'+re[i].tname+'</option>';
                            withchar.push(re[i].tname); // 이름을 배열의 넣어서 중복 html 방지
                        }
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
        let html = "<option value='N'>시험구분선택</option>";
        for(let i = 0; i < testlist.length ; i++){
            if(testlist[i].tname == tempchack){ // 해당과목의 시험구분넣어주기
                html += '<option value="'+testlist[i].testof+'">'+testlist[i].testof+'</option>';
            }
        }
        $("#testof").html(html)
        $("#testof").css({"display" : ""});
    };
});

// 시험구분 select change 이벤트
$("#testof").on("change", function (){
    let tempchack = $("#testof").val();
    let testtitle = $("#testtitle").val();
    if(tempchack == "N"){ // 기본선택이라면
        $("#testtyear").css({"display" : "none"});
    }else{ // 카테고리를 선택했다면
        let html = "<option value='N'>기출연도선택</option>";
        for(let i = 0 ; i < testlist.length ; i++){
            if(testlist[i].tname == testtitle && testlist[i].testof == tempchack){ // 해당 과목과 해당 시험구분이 일치하다면
                html += '<option value="'+testlist[i].tno+'">'+testlist[i].tyear+'</option>';
            }
        }
        $("#testtyear").html(html);
        // 다음 선택 창 보여주기
        $("#testtyear").css({"display" : ""});
    };
});

// 기출연도 select change 이벤트
$("#testtyear").on("change", function (){
    let tempchack = $("#testtyear").val();
    if(tempchack == "N"){ // 기본선택이라면
    }else{ // 카테고리를 선택했다면

    };
});

