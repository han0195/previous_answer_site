
//문제보기
let indexproblem = ['①', '②', '③', '④' , '⑤'];
//선택 보기
let chindex = '●';
/*문제 리스트 저장*/
let testdata;

// 답 저장 배열
let user_choice_anwser = [];


/* 문제 리스트 가져오기 함수 호출 */
gettest();

/* 문제 리스트 가져오기 */
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

/* html 출력*/
function inserthtml(){
    let count = 0; /* 출력 인덱스 기억하기 */
    let html = "";

    let len = Math.ceil(testdata.length / 2);

    for(let i = 0 ; i < len ; i++){
        html += '<div class="row">';
        for(let j = 0 ; j < 2 ; j++){ /* 두개씩 출력 */
            if(testdata[count] != null){
                html += ' <div class="col-sm-6">';
                html += '<div class="titlediv">'+testdata[count].pindex+". "+testdata[count].pname+' <a onclick="resetchoice('+testdata[count].pno+')">선택초기화</a></div>';
                if(testdata[count].pimg.length == 0){ /* 사진이 존재하지않는다면 */
                    html += '<div></div>'
                }else {
                    html += '<div><img width="100%" src="/examimg/'+testdata[count].pimg[0]+'"></div>';
                }
                html += '<ul class="chul">';
                for(let z = 0; z < testdata[count].poption.length ; z++){ /* 문제보기 반복 */
                    if(testdata[count].panswer.length > 1){ /* 중복 답*/
                        html += '<li><span id="problem'+z+'_'+testdata[count].pno+'" onclick="choice('+(z + 1)+', '+testdata[count].pno+', '+true+')">'+indexproblem[z]+'</span>'+testdata[count].poption[z]+'</li>';
                    }else{ /* 단일 답 */
                        html += '<li><span id="problem'+z+'_'+testdata[count].pno+'" onclick="choice('+(z + 1)+', '+testdata[count].pno+', '+false+')">'+indexproblem[z]+'</span>'+testdata[count].poption[z]+'</li>';
                    }
                }
                html += '</ul>';
                html += '</div>';
                count++;
            }
        }
        html += '</div>';

    }
    $("#problemdiv").html(html);
}

// 답 선택
function choice(choicenum, pno, duplicate_selection){
    alert(pno);
    if(duplicate_selection){ /* 중복선택 문제 */
        // 답 저장 배열의 해당 문제번호가 존재하지는지 확인
        let pass = true;
        for(let i = 0 ; i < user_choice_anwser.length ; i++){
            if(user_choice_anwser[i].pno == pno){ /* 선택한 답이 있다면 */
                pass = false;
                user_choice_anwser[i].choicenums.push(choicenum);
                break;
            }
        }
        // 선택하지않았다면
        if(pass){
            user_choice_anwser.push({"pno" : pno , "choicenums" : [choicenum]});
        }
    }else{ /* 단일 선택 문제 */
        // 답 저장 배열의 해당 문제번호가 존재하지는지 확인
        let pass = true;
        for(let i = 0 ; i < user_choice_anwser.length ; i++){
            if(user_choice_anwser[i].pno == pno){ /* 선택한 답이 있다면 */
                pass = false;
                user_choice_anwser[i].choicenums = [choicenum];
                break;
            }
        }
        // 선택하지않았다면
        if(pass){
            user_choice_anwser.push({"pno" : pno , "choicenums" : [choicenum]});
        }
    }

    choicecoloc(choicenum, pno, duplicate_selection);

}

function  choicecoloc(choicenum, pno, duplicate_selection){
    if(duplicate_selection){ /* 중복 문제 */
        //선택번호 색깔 변경
        $('#problem'+(choicenum-1)+'_'+pno+'').html('●');
    }else {
        //초기화
        $('#problem0_'+pno+'').html('①');
        $('#problem1_'+pno+'').html('②');
        $('#problem2_'+pno+'').html('③');
        $('#problem3_'+pno+'').html('④');

        //선택번호 색깔 변경
        $('#problem'+(choicenum-1)+'_'+pno+'').html('●');
    }
    
}

function resetchoice(pno){
    //html 초기화
    $('#problem0_'+pno+'').html('①');
    $('#problem1_'+pno+'').html('②');
    $('#problem2_'+pno+'').html('③');
    $('#problem3_'+pno+'').html('④');
    
    //해당 선택 객체 삭제

    for(let i = 0 ; i < user_choice_anwser.length ; i++){
        if(user_choice_anwser[i].pno == pno){
            user_choice_anwser.splice(i,1);
        }
    }


}

//채점 함수



