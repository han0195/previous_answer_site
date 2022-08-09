
let testdata;

//문제보기
let indexproblem = ['①', '②', '③', '④' , '⑤'];
//선택 보기
let chindex = '●';

/* 정답 선택 */
let count = 0;

gettest();
gettitle();

/* 시험지 제목 가져오기 함수*/
function gettitle(){
    $.ajax({
        url: "/exam/gettitle",
        success : function (re){
            console.log(re.tname);
            $("#btitle").html(re.tname);
        }
    })
}

/* 해당과목 시험지 가져오기 */
function gettest(){
    $.ajax({
       url: "/exam/getproblemlist",
        type: "POST",
        success : function (re){
            testdata = re;
            inserthtml();
        }
    });
}
let answer = [];
/* 문제 보기 클릭이벤트 */
function problemviewchick(num, overlap){
    if(overlap == -1){/* 복수 정답이 아니라면 */
        if(num == 1){
            $("#prochick1").html('●');
            $("#prochick2").html('②');
            $("#prochick3").html('③');
            $("#prochick4").html('④');
            answer = []; /* 배열 초기화 */
            answer.push(1) /* 배열 값 넣기 */
        }else if(num == 2){
            $("#prochick1").html('①');
            $("#prochick2").html('●');
            $("#prochick3").html('③');
            $("#prochick4").html('④');
            answer = []; /* 배열 초기화 */
            answer.push(2) /* 배열 값 넣기 */
        }else if(num == 3){
            $("#prochick1").html('①');
            $("#prochick2").html('②');
            $("#prochick3").html('●');
            $("#prochick4").html('④');
            answer = []; /* 배열 초기화 */
            answer.push(3) /* 배열 값 넣기 */
        }else{
            $("#prochick1").html('①');
            $("#prochick2").html('②');
            $("#prochick3").html('③');
            $("#prochick4").html('●');
            answer = []; /* 배열 초기화 */
            answer.push(4) /* 배열 값 넣기 */
        }
    }else{
        if(count < 4){ /* 정답을 두개 선택하지 않았다면 */
            if(num == 1){
                if($("#prochick1").html() == '●'){
                    $("#prochick1").html('①');
                    count--;
                        answer.splice(answer.indexOf(1),1);

                }else {
                    $("#prochick1").html('●');
                    count++;
                    answer.push(1) /* 배열 값 넣기 */
                }
            }else if(num == 2){
                if($("#prochick2").html() == '●'){
                    $("#prochick2").html('②');
                    count--;
                    answer.splice(answer.indexOf(2),1);
                }else {
                    $("#prochick2").html('●');
                    count++;
                    answer.push(2) /* 배열 값 넣기 */
                }
            }else if(num == 3){
                if($("#prochick3").html() == '●'){
                    $("#prochick3").html('③');
                    count--;

                    answer.splice(answer.indexOf(3),1);
                }else {
                    $("#prochick3").html('●');
                    count++;
                    answer.push(3) /* 배열 값 넣기 */
                }
            }else{
                if($("#prochick4").html() == '●'){
                    $("#prochick4").html('④');
                    count--;
                    answer.splice(answer.indexOf(4),1);
                }else {
                    $("#prochick4").html('●');
                    count++;
                    answer.push(4) /* 배열 값 넣기 */
                }
            }
        }else{ /* 두개 선택했다면 */
            if(num == 1){
                if($("#prochick1").html() == '●') {
                    $("#prochick1").html('①');
                    count--;
                    answer.splice(answer.indexOf(1),1);
                }
            }else if(num == 2){
                if($("#prochick2").html() == '●') {
                    $("#prochick2").html('②');
                    count--;
                    answer.splice(answer.indexOf(2),1);
                }
            }else if(num == 3){
                if($("#prochick3").html() == '●') {
                    $("#prochick3").html('③');
                    count--;
                    answer.splice(answer.indexOf(3),1);
                }
            }else{
                if($("#prochick4").html() == '●') {
                    $("#prochick4").html('④');
                    count--;
                    answer.splice(answer.indexOf(4),1);
                }
            }
        }
    }
}

let viewcount = 0;

/* 문제 html 출력 */
function inserthtml(){

    let html = "";

    html += '<div class="titlediv">'+(viewcount + 1)+'. '+testdata[viewcount].pname+'</div>';
    if(testdata[viewcount].pimg.length == 0){ /* 사진이 존재하지않는다면 */
        html += '<div></div>'
    }else {
        html += '<div><img width="50%" src="/examimg/'+testdata[viewcount].pimg[0]+'"></div>';
    }
    html += '<ul class="chul">';
    let anwser = testdata[viewcount].panswer.split("_");
    if(anwser.length > 1){
        html += '<li><span id="prochick1" onclick="problemviewchick(1, 1);">①</span> '+testdata[viewcount].poption[0]+'</li>';
        html += '<li><span id="prochick2" onclick="problemviewchick(2, 1)">②</span> '+testdata[viewcount].poption[1]+'</li>';
        html += '<li><span id="prochick3" onclick="problemviewchick(3, 1)">③</span> '+testdata[viewcount].poption[2]+'</li>';
        html += '<li><span id="prochick4" onclick="problemviewchick(4, 1)">④</span> '+testdata[viewcount].poption[3]+'</li>';
    }else{
        html += '<li><span id="prochick1" onclick="problemviewchick(1, -1)">①</span> '+testdata[viewcount].poption[0]+'</li>';
        html += '<li><span id="prochick2" onclick="problemviewchick(2, -1)">②</span> '+testdata[viewcount].poption[1]+'</li>';
        html += '<li><span id="prochick3" onclick="problemviewchick(3, -1)">③</span> '+testdata[viewcount].poption[2]+'</li>';
        html += '<li><span id="prochick4" onclick="problemviewchick(4, -1)">④</span> '+testdata[viewcount].poption[3]+'</li>';
    }
    html += '</ul>';
    html += '</div>';

    $("#problemdiv").html(html);

    html = "";

    let problemcount = testdata.length;

    html += ''+(viewcount + 1)+' / '+problemcount+'';

    $("#pageing").html(html);

}

/* 채점 */
function grading(){
    if(answer.length == 0){
        alert("아무것도 선택하지않았습니다.");
        return;
    }

    // 채점
    let answerte = testdata[viewcount].panswer.split("_");
    let answercount = answer.length;
    let html = "";
    if(answerte.length > 1){/* 만약 복수 정답이면*/
        let pass = [];
        for(let i = 0; i < answercount ; i++){
            if(answer.indexOf(parseInt(answerte[i])) != -1){ /* 정답이면 */
                pass.push(true);
            }else{
                pass.push(false);
            }
        }
        console.log(pass);
        if(pass.indexOf(false) != -1){ /* 오답 */
            html += '<div class="alert alert-danger" role="alert">';
            html += '틀렸습니다. 정답은: ';
            console.log(testdata[viewcount].panswer);
            let temptext = testdata[viewcount].panswer.replace('_', ',')
            html += ''+temptext+'';
            html += '</div>';
        }else { /* 정답 */
            html += '<div class="alert alert-success" role="alert">';
            html += '축하합니다 정답입니다.';
            html += '</div>';
        }
    }else{/* 만약 복수 정답이 아니면 */
        if(answer.indexOf(parseInt(testdata[viewcount].panswer[0])) != -1){
            html += '<div class="alert alert-success" role="alert">';
            html += '축하합니다 정답입니다.';
            html += '</div>';
        }else{
            html += '<div class="alert alert-danger" role="alert">';
            html += '틀렸습니다. 정답은: ';
            html += ''+testdata[viewcount].panswer[0]+'';
            html += '</div>';
        }
    }

    $("#alertdiv").html(html);

}


/* 이전 페이지 이벤트 */
function laquo(){
    // 페이지 전환 [ 만약 countview 길이보다 작으면 페이지 전환 x ]
    if(viewcount == 0){ /* 만약 길이가 1이면 */
        alert("첫번째 문제입니다");
    }else{
        viewcount--;
        inserthtml();
    }
}

/* 다음 페이지 이벤트 */
function raquo(){
    // 페이지 전환 [ 예외 처리 전체길이랑 같으면 시험 종료 ]
    if(viewcount < testdata.length - 1){ /* 전체길이 안쪽이면 */
        viewcount++;
        $("#alertdiv").html("");
        inserthtml();
    }else{ /* 전체길이 랑 일치하면 */
        alert("시험종료합니다.");
        location.href = '/';
    }

}