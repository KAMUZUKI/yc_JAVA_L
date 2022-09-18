

$(function () {
    query()  // 不传参数 ==> 查询全部
    // 添加查询按钮点击事件
    var $btns = $("form button")
    $btns.get(0).onclick = function () {
        query(id.value, uname.value,gender.value,email.value,startTime.value,endTime.value)
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
        // 判断用户名
        if (/^\S{2,10}$/.test(iptName.value) == false) {
            iptName.nextElementSibling.innerText = "用户名必须是2~10字符"
            res = false
        } else {
            iptName.nextElementSibling.innerText = ""
            user.uname = iptName.value
        }
        //判断性别
        if (/^[12]{1}$/.test(iptGender.value) == false) {
            iptGender.nextElementSibling.innerText = "用户性别1为女2为男"
            res = false
        }else{
            iptGender.nextElementSibling.innerText = ""
            user.gender = iptGender.value
        }
        //判断邮箱
        var atpos = iptEmail.value.indexOf("@");
        var dotpos = iptEmail.value.lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= iptEmail.value.length) {
            iptEmail.nextElementSibling.innerText = "请输入正确的邮箱"
            res = false
        }else{
            iptEmail.nextElementSibling.innerText = ""
            user.email = iptEmail.value
        }
        //验证信息是否正确
        if (res == false) {
            return
        }
        //获取当前时间戳
        // user.regtime = new Date().valueOf()
        // user.regtime = '1036425600000'
        user.upass = "123456"
        user.head = "1.gif"
        user.q1 = "问题一"
        user.a1 = "答案一"
        user.q2 = "问题二"
        user.a2 = "答案二"
        user.q3 = "问题三"
        user.a3 = "答案三"
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
            console.log(user)
            // code == 1 表示成功
            if (data.code == 1) {
                query()  //重新查询表格 ==> 刷新
                $("#dialog").css("display", "none")  // 关闭窗口
                alert("新增成功")
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
    iptGender.value = $tds.get(3).innerText
    iptEmail.value = $tds.get(4).innerText
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