
//문제보기
let indexproblem = ['①', '②', '③', '④' , '⑤'];
//선택 보기
let chindex = '●';

/*문제 리스트 저장*/
let testdata;

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
                html += '<div class="titlediv">'+testdata[count].pindex+". "+testdata[count].pname+'</div>';
                if(testdata[count].pimg.length == 0){ /* 사진이 존재하지않는다면 */
                    html += '<div></div>'
                }else {
                    html += '<div><img width="100%" src="/examimg/'+testdata[count].pimg[0]+'"></div>';
                }
                html += '<ul class="chul">';
                for(let z = 0; z < testdata[count].poption.length ; z++){ /* 문제보기 반복 */
                    html += '<li><span id="">'+indexproblem[z]+'</span>'+testdata[count].poption[z]+'</li>';
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