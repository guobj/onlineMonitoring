// JavaScript Document
$(function(){//�Ҳ�˵�
    $("#a1").click(function(){
        $("#b2").toggle();
        console.log()
    })
    $("#a2").click(function(){
        $("#b1").toggle();
    })
})



//ҳ����ʾ
$(function(){

    var dqPage = $("#dqPage").text();//�õ���ǰҳ��
    dqPage = parseInt(dqPage);//�õ����ı�ת��int
    var pageCount = $("#pageCount").text();//�õ���ҳ��
    pageCount = parseInt(pageCount);
    var i = 1;
    i = parseInt(i);
    var item="";
    var href = "�����������ַ";
    if (pageCount <= 5 ) {//��ҳ��С����ҳ�����������ҳ

        for (i; i <= pageCount; i++) {
            if (i == dqPage) {
                item += "<span class='disabled but1'>"+i+"</span>";
            }else{
                item += "<a href='"+href+i+"' >"+i+"</a>";
            }
        };
        $('#pageBtn').append(item);
        return;
    }else if (pageCount > 5) {//��ҳ��������ҳ���������ҳ
        if (dqPage < 5) {//��ǰҳС��5������1-5ҳ
            for (i; i <= 5; i++) {
                if (i == dqPage) {
                    item += "<span class='disabled but1'>"+i+"</span>";
                }else{
                    item += "<a href='"+href+i+"' >"+i+"</a>";
                }
            };
            if (dqPage <= pageCount-2) {//���һҳ׷�ӡ�...������ʡ�Ե�ҳ
                item += "<span> . . . </span>";
            }
            $('#pageBtn').append(item);
            return;
        }else if (dqPage >= 5) {//��ǰҳ����5ҳ
            for (i; i <= 2; i++) {//1,2ҳ��ʼ����ʾ
                item += "<a href='"+href+i+"' >"+i+"</a>";
            }
            item += "<span> . . . </span>";//2ҳ�������...���沿��δ��ʾ��ҳ��
            if (dqPage+1 == pageCount) {//��ǰҳ+1������ҳ��
                for(i = dqPage-1; i <= pageCount; i++){//��...�����������ҳ�뵱ǰҳ������ʾ
                    if (i == dqPage) {
                        item += "<span class='disabled but1'>"+i+"</span>";
                    }else{
                        item += "<a href='"+href+i+"' >"+i+"</a>";
                    }
                }
            }else if (dqPage == pageCount) {//��ǰҳ��������ҳ���������һҳҳ����ʾ�����
                for(i = dqPage-2; i <= pageCount; i++){//...���������ҳ�뵱ǰҳ������ʾ
                    if (i == dqPage) {
                        item += "<span class='disabled but1'>"+i+"</span>";
                    }else{
                        item += "<a href='"+href+i+"' >"+i+"</a>";
                    }
                }
            }else{//��ǰҳС����ҳ���������һҳ�����...
                for(i = dqPage-1; i <= dqPage+1; i++){//dqPage+1ҳ����...
                    if (i == dqPage) {
                        item += "<span class='disabled but1'>"+i+"</span>";
                    }else{
                        item += "<a href='"+href+i+"' >"+i+"</a>";
                    }
                }
                item += "<span> . . . </span>";
            }
            $('#pageBtn').append(item);
            return;
        }
    }


});