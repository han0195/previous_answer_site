getdlist();

let darr = [];

/* 해당 학과 불러오기 */
function getdlist(){
    $.ajax({
        url: "/admin/getdlist",
        success : function (re){
            darr = [];
            let html = "";
            for(let i = 0 ; i < re.length ; i++){

                html += '<div class="my-3 p-3 bg-body rounded shadow-sm">';
                html += '<h6 class="border-bottom pb-2 mb-0">'+re[i].dname+'</h6>';
                html += '<div id="infolistdiv'+re[i].dno+'" class="infolistdiv"></div>';
                html += '<small class="d-block text-end mt-3">';
                html += '<a onclick="testinfolist('+re[i].dno+')">선택</a>';
                html += '</small>';
                html += '</div>';
            }
            $("#dlist").html(html);

        }
    });
}

/* 과목 리스트 저장 */
let testinfo = [];

/* 해당 학과 과목리스트 가져오기*/
function testinfolist(dno){
    $.ajax({
        url: "/admin/getexaminfolist",
        data: {"dno" : dno },
        success : function (re){
            $(".infolistdiv").html("");
            darr = []; /* 배열 초기화 */
            testinfo = re;
            let html = "";
            for(let i = 0 ; i < re.length ; i++){
                if(darr.indexOf(re[i].tname) == -1 ){ /*동일한 이름이 존재하지않는다면*/
                    html += '<div class="d-flex text-muted pt-3">';
                    html += '<p class="pb-3 mb-0 small lh-sm border-bottom divhover" onclick="infolist(\''+re[i].tname+'\')" style="width: 100%">';
                    html += '<strong class="d-block text-gray-dark">'+re[i].tname+'</strong>';
                    html += '</p>';
                    html += '</div>';
                    darr.push(re[i].tname);
                }
            }
            $("#infolistdiv"+dno+"").html(html);
        }
    });
}

/* 해당과목의 해당하는 list 전부 모달창으로 출력*/
function infolist(tname){
    let html = "";
    for(let i = 0 ; i < testinfo.length ; i++){
        if(testinfo[i].tname == tname){
            html += '<div class="d-flex text-muted pt-3">';
            html += '<p onclick="beforestart('+testinfo[i].tno+')" class="pb-3 mb-0 small lh-sm border-bottom divhover" style="width: 100%">';
            html += '<strong class="d-block text-gray-dark">'+testinfo[i].tname+' | '+testinfo[i].tyear+' | '+testinfo[i].tquarter+'</strong>';
            html += '</p>';
            html += '</div>';
        }
    }
    $("#modal_content").html(html);

    $("#hiddenmodal").click();
}

/* 해당 과목 번호 세션의 저장하면서 시험시작 이동 */
function beforestart(tno){
    $.ajax({
       url:"/exam/beforestart",
        type: "POST",
       data:{"tno":tno},
        success : function (re) {
            location.href = re;
        }
    });
}

