function query(name,author){
    let url = "http://47.106.66.89:2222/easy/books/list?"
    if (name&&name.length>0){
        url+="bookname="+name
    }
    if (author&&author.length>0){
        url+="&bookauthor="+author
    }
    var $table = $("table")
    function callback(data){ // 回调函数
        // 清空表格中的数据
        var $trs = $("table tr")
        for (let i = $trs.length - 1; i > 0; i--) {  //从前往后删除
            $trs.get(i).remove()
        }
        data.forEach(book=>{
            let html = `<tr id="${book.id}">
                            <td>${book.bookname}</td>
                            <td>${book.bookauthor}</td>
                            <td>${book.bookpress}</td>
                            <td>${book.pressdate}</td>
                            <td>
                                <a href="#" onclick="mod(${book.id})">修改</a>
                                <a href="#" onclick="del(${book.id})">删除</a>
                            </td>
                        </tr>`
            var $str = $(html)
            $table.append($str)
        })
    }
    //ajax方法
    $.get(url,callback)
}

$(function(){
    query()  // 不传参数 ==> 查询全部
    // 添加查询按钮点击事件
    var $btns = $("form button")
    $btns.get(0).onclick = function(){
        query(bookname.value,author.value)
    }
    // 新增按钮
    $btns.get(1).onclick = function(){
       $("#dialog").css("display","block") 
    }
    //关闭按钮
    $btns.get(3).onclick = function(){
       $("#dialog").css("display","none") 
    }
    // 保存按钮
    $btns.get(2).onclick = function(){
        let res = true
        let book = {}
        let iptName = document.getElementById("iptName")
        let iptAuthor = document.getElementById("iptAuthor")
        let iptPress = document.getElementById("iptPress")
        let iptTime = document.getElementById("iptTime")
        if (/^\S{1,20}/.test(iptName.value) == false) {
            iptName.nextElementSibling.innerText = "书名必须是1~20字符"
            res = false
        } else{
            book.bookname = iptName.value
        }
        if (/^\S{2,10}/.test(iptAuthor.value) == false) {
            iptAuthor.nextElementSibling.innerText = "作者必须是2~10字符"
            res = false
        } else{
            book.bookauthor = iptAuthor.value
        }
        if (res == false) {
            return
        }
        if (iptPress!=null&&iptTime!=null) {
            book.bookpress = iptPress.value
            book.pressdate = parseInt(iptTime.value)
        }
        let url = "http://47.106.66.89:2222/easy/books/save"

        book.bookpress = intPress.value
        // book.bookdate = iptTime.value
        // 如果全局 modId 不为空 ==> 修改保存
        if (modId) {
            // 添加 id 参数
            book.id = modId
        }
        $.post(url,book,data=>{
            // code == 1 表示成功
            if (data.code == 1) {
                query()  //重新查询表格 ==> 刷新
                $("#dialog").css("display","none")  // 关闭窗口
            } else{
                alert(data.msg)  //提示服务器返回的报错提示信息
            }
        })
    }
})

var modId = null
function mod(id){
    // 将当前修改的图书id 保存到全局变量 modId 中
    modId = id
    var $tds = $("#tr" +id + ">td")
    iptName.value = $tds.get(0).innerText
    iptAuthor.value = $tds.get(1).innerText
    iptPress.value = $tds.get(2).innerText
    iptTime.value = $tds.get(3).innerText
    $("#dialog").css("display","block")
}

function del(id) {
    if (confirm("请确认是否要删除该图书")) {
        let url = "http://47.106.66.89:2222/easy/books/remove?id=" + id
        $.get(url,data => {
            if (data.code){
                query()
            } else {
                alert(data.msg)
            }
        })
    }
}