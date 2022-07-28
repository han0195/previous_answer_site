getinfo();
getproblemlist(); // 문제리스트 가져오기

//문제보기
let indexproblem = ['①', '②', '③', '④' , '⑤'];
//선택 보기
let chindex = '●';

// 시험지 정보 가져오는 함수
function getinfo(){
    $.ajax({
        url: '/admin/getinfo',
        type: 'POST',
        success : function (re) {
            let html = "<tr>" +
                "            <th>과목</th>" +
                "            <th>연도</th>" +
                "            <th>과목구분</th>" +
                "            <th>문제수</th>" +
                "        </tr>";
            html += '<tr>';
            html += '<th>'+re.tname+'</th>';
            html += '<th>'+re.tyear+'</th>';
            html += '<th>'+re.tquarter+'</th>';
            html += '<th>'+re.pcount+'</th>';
            html += '</tr>';
            $("#infotable").html(html);
        }
    })
}

// 문제추가함수
function addtest(){
    let form = $("#pinfodata")[0];
    let formdata = new FormData(form);

    $.ajax({
        url: "/admin/saveproblem",
        type: 'POST',
        data: formdata,
        contentType: false,
        processData: false,
        success : function (re){
            if(re){
                location.href = '/admin/pmanager';
            }else {
                alert("코드에러");
            }
        }
    });
}

//문제리스트 가져오기 함수()
function getproblemlist(){
    $.ajax({
        url : "/exam/getproblemlist",
        type:'POST',
        success : function (re){
            let count = 0; /* 출력 인덱스 기억하기 */
            let html = "";

            console.log(re);

            let len = Math.ceil(re.length / 2);
            for(let i = 0 ; i < len ; i++){
                html += '<div class="row">';
                for(let j = 0 ; j < 2 ; j++){ /* 두개씩 출력 */
                    if(re[count] != null){
                        html += ' <div class="col-sm-6">';
                        html += '<div>'+re[count].pindex+" | "+re[count].pname+'' +
                            '<button onclick="setModal('+re[count].pno+')" type="button" class="btn">수정</button>'+
                            '<button onclick="deleteproblem('+re[count].pno+')" class="btn" type="button">삭제</button></div>';
                        if(re[count].pimg.length == 0){ /* 사진이 존재하지않는다면 */

                       }else {
                           html += '<div><img width="50%" src="/examimg/'+re[count].pimg[0]+'"></div>';
                       }
                        html += '<ul>';
                        for(let z = 0; z < re[count].poption.length ; z++){ /* 문제보기 반복 */
                            html += '<li>'+indexproblem[z]+' '+re[count].poption[z]+'</li>';
                        }
                        html += '</ul>';
                        html += '</div>';
                        count++;
                    }
                }
                html += '</div>';

            }
            $("#problemlistdiv").html(html);
        }
    });
}

//수정 모달창
function setModal(pno) {
    // 문제 가져오기
    $.ajax({
        url:"/exam/getproblem",
        type:"POST",
        data: {"pno" : pno},
        success : function (re){

            $("#pname2").val(re.pname);
            $("#pindex2").val(re.pindex);
            /*$("#pimg") ???? */
            let poption = "";
            for(let i = 0 ; i < re.poption.length; i++){
                if(i == re.poption.length - 1){ /* 마지막인덱스라면 */
                    poption += re.poption[i];
                }else{
                    poption += re.poption[i] + "_";
                }
            }
            $("#poption2").val(poption);
            $("#panswer2").val(re.panswer);

            $("#hiddenpno").val(re.pno);

            $("#hiddenbtn").click();

        }
    });
}

//수정처리함수
function setproblem(){

    let form = $("#pinfodata2")[0];
    let formdata = new FormData(form);

    $.ajax({
        url: "/admin/setproblem",
        type:"POST",
        data: formdata,
        contentType: false,
        processData: false,
        success : function (re) {
            alert(re);
        }
    });
}

//삭제
function deleteproblem(pno) {
 $.ajax({
    url: "/admin/pdelete",
     data: {"pno" : pno},
     type: "delete",
     success : function (re){
        if(re){
            location.href = '/admin/pmanager';
        }else{
            alert("에러 코드");
        }
     }
 });
}
