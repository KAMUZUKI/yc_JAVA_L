function query(id, uname, gender, email) {
    let url = "http://47.106.66.89:8080/easy/tbl_user/page?"
    if (id && id.length > 0) {
        url += "id=" + id
    }
    if (uname && uname.length > 0) {
        url += "&uname=" + uname
    }
    if (gender && gender.length > 0) {
        url += "&gender=" + gender
    }
    if (email && email.length > 0) { 
        url += "&email=" + email
    }
    var $table = $("table")
    function callback(data) { // 回调函数
        // 清空表格中的数据
        var $trs = $("table tr")
        for (let i = $trs.length - 1; i > 0; i--) {  //从前往后删除
            $trs.get(i).remove()
        }
        data.forEach(user => {
            let html = `<tr id="tr${user.id}">
                            <td>${user.uname}</td>
                            <td>${user.regtime}</td>
                            <td>${user.gender}</td>
                            <td>${user.email}</td>
                            <td>
                                <a href="#" onclick="mod(${user.id})">修改</a>
                                <a href="#" onclick="del(${user.id})">删除</a>
                            </td>
                        </tr>`
            var $tr = $(html)
            $table.append($tr)
        })
    }
    //ajax方法
    $.get(url, callback)
}

$(function () {
    query()  // 不传参数 ==> 查询全部
    // 添加查询按钮点击事件
    var $btns = $("form button")
    $btns.get(0).onclick = function () {
        query(id.value, uname.value,gender.value,email.value)
    }
    // 新增按钮
    $btns.get(1).onclick = function () {
        $("#dialog").css("display", "block")
    }
    //关闭按钮
    $btns.get(3).onclick = function () {
        $("#dialog").css("display", "none")
    }
    // 保存按钮
    $btns.get(2).onclick = function () {
        let res = true
        let user = {}
        var iptId = document.getElementById("iptId")
        var iptName = document.getElementById("iptName")
        var iptGender = document.getElementById("iptGender")
        var iptEmail = document.getElementById("iptEmail")
        user.id = iptId.value
        // 判断用户名
        if (/^\S{2,10}/.test(iptName.value) == false) {
            iptName.nextElementSibling.innerText = "用户名必须是2~10字符"
            res = false
        } else {
            user.name = iptName.value
        }
        //判断性别
        if (/[12]{1}/.test(iptGender.value) == false) {
            iptGender.nextElementSibling.innerText = "用户性别1为女2为男"
            res = false
        }else{
            user.gender = iptGender.value
        }
        //判断邮箱
        var atpos = iptEmail.indexOf("@");
        var dotpos = iptEmail.lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= iptEmail.length) {
            iptEmail.nextElementSibling.innerText = "请输入正确的邮箱"
            res = false
        }else{
            user.email = iptEmail.value
        }
        //验证信息是否正确
        if (res == false) {
            return
        }
        
        let url = "http://47.106.66.89:8080/easy/tbl_user/save"

        // 2001年09月09日 ==> 2001-09-09
        //user.regtime = user.regtime.replace(/(\d+)\D+(\d+)\D+(\d+)\D+/,"$1-$2-$3")
        
        // user.userdate = iptTime.value
        // 如果全局 modId 不为空 ==> 修改保存
        if (modId) {
            // 添加 id 参数
            user.id = modId
        }
        $.post(url, user, data => {
            // code == 1 表示成功
            if (data.code == 1) {
                query()  //重新查询表格 ==> 刷新
                $("#dialog").css("display", "none")  // 关闭窗口
            } else {
                alert(data.msg)  //提示服务器返回的报错提示信息
            }
        })
    }
})

var modId = null
function mod(id) {
    // 将当前修改的图书id 保存到全局变量 modId 中
    modId = id
    var $tds = $("#tr" + id + ">td")
    iptId.value = $tds.get(0).innerText
    iptName.value = $tds.get(1).innerText
    iptGender.value = $tds.get(2).innerText
    iptEmail.value = $tds.get(3).innerText
    $("#dialog").css("display", "block")
}

function del(id) {
    if (confirm("请确认是否要删除该图书")) {
        let url = "http://47.106.66.89:8080/easy/tbl_user/remove?id=" + id
        $.get(url, data => {
            if (data.code) {
                query()
            } else {
                alert(data.msg)
            }
        })
    }
}