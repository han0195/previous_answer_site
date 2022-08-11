
getdlist();
geterrorlist();
// 학과리스트 가져오기
function getdlist(){
    $.ajax({
        url: "/admin/getdlist",
        success : function (re){
            let html = ""
            for(let i = 0 ; i < re.length; i++){
                if(i == 0){
                    html += '<li class="nav-item"><a class="nav-link active" aria-current="page" href="#">'+re[i].dname+'</a></li>';
                }else{
                    html += '<li class="nav-item"><a class="nav-link" href="#">'+re[i].dname+'</a></li>';
                }

            }
            $("#dlist_ul").html(html);
            getexaminfolist(1);
        }
    });
}

// 시험정보리스트 가져오기
function getexaminfolist(dno){
    $.ajax({
        url: "/admin/getexaminfolist",
        data: {"dno" : dno },
        success : function (re){
            console.log(re);
            let html = '<tr>' +
                            '<th>과목</th>' +
                            '<th>연도</th>' +
                            '<th>과목구분</th>' +
                            '<th colspan="2">문제수</th>' +
                        '</tr>';
            for(let i = 0 ; i < re.length ; i++){
                html += '<tr>';
                html += '<th>'+re[i].tname+'</th>';
                html += '<th>'+re[i].tyear+'</th>';
                html += '<th>'+re[i].tquarter+'</th>';
                html += '<th>'+re[i].pnum+'</th>';
                html += '<th style="text-align: center"><button onclick="pmanager('+re[i].tno+')" class="btn">문제관리</button><button class="btn">정보수정</button><button class="btn">삭제</button></th>';
                html += '</tr>';
            }

            $("#testinfotable").html(html);
        }
    });

}

function pmanager(tno){
    $.ajax({
        url:'/admin/pmanager',
        type: "POST",
        data: {"tno" : tno},
        success : function (re){
            if(re){
                location.href='/admin/pmanager';
            }else{
                alert("에러");
            }
        }
    });
}

/* 오류 신고 현황 출력 */
function geterrorlist(){
    $.ajax({
        url:'/admin/geterrorlist',
        success : function (re) {
            html = "<tr>" +
                "                <th>신고자</th>" +
                "                <th>건의 내용</th>" +
                "                <th>해결여부</th>" +
                "                <th>상태변경</th>" +
                "            </tr>";
            for(let i = 0 ; i < re.length ; i++){
                html += '<tr>';
                html += '<td>'+re[i].mname+'</td>';
                html += '<td>'+re[i].econtent+'</td>';

                if(re[i].solution == "0"){
                    html += '<td>대기</td>';
                }else{
                    html += '<td>해결</td>';
                }
                html += '<td><a onclick="changestats('+re[i].eno+','+1+')">해결</a><a onclick="changestats('+re[i].eno+','+0+')">대기</a><a onclick="errordelete('+re[i].eno+')">삭제</a></td>'
                html += '</tr>';
            }
            $("#errortable").html(html);
        }
    });
}

/* 오류 테이블 항목 삭제 */
function errordelete(eno) {
    $.ajax({
        url : '/admin/deleterror',
        type: 'DELETE',
        data: {"eno" : eno},
        success : function (re) {
            if(re){
                console.log("성공");
            }
        }
    });
}

/* 오류 상태 변경 */
function changestats(eno , ch){
    $.ajax({
        url : '/admin/changestats',
        data:{"eno" : eno, "ch" : ch},
        success : function (re) {

        }
    });
}