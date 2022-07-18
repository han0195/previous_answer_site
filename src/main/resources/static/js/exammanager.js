getinfo()


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