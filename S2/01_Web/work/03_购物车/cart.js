function sum(){
    //获取价格对象数组
    let prices = $("tr>td:nth-child(3)")

    let sum = 0
    prices.each(function(){
        //变量元素函数，内部this对象指向是当前元素DOM对象
        let $p = $(this)  // DOM => jQuery
        //DOM.innerText ==> jQuery.text()
        let price =$p.text()
        price = parseInt(price.replace("￥",""))
        let $n = $p.next().find("span")
        let number = $n.text()
        number = parseInt(number)
        sum += price * number
    })
    $("#tdSum span").text("￥" + sum)
}

//ready() 页面加载完成事件
$(()=>{
    //打开页面 初始化求和
    sum()
    let btn1s = $("tr>td:nth-child(4)>button:nth-child(1)");
    btn1s.click(function (){
        /**
        箭头函数没有使用function,
        使用function this表示当前DOM元素对家
        *使用箭头函数this表示window对象
        */
        let btn = $(this);
        let num = btn.next();
        let number = parseInt(num.text());
        if(number ==1){
            if(confirm("请确认是否移除该宝贝？")){
                btn.parent().parent().remove();
                //获取有id的td元素
                let td = btn.parent()
                //获取存在的id中的key
                let id = td.attr("id")
                //根据key删除商品
                localStorage.removeItem(id)
            }
        } else {
            number --
            num.text(number)
            //sum()
        }
        sum();
    })
    let btn2s = $("tr>td:nth-child(4)>button:nth-child(3)");
    btn2s.click(function(){
        let btn = $(this)
        let num = btn.prev()
        let number = parseInt(num.text())
        number++
        num.text(number)
        sum()
    })

    //获取所有的"删除"超链接
    let btn3s = $("tr>td:nth-child(6)>a")
    btn3s.click(function(){
        let a = $(this)
        a.parent().parent().remove()
        let td = a.parent().prev().prev()
        let id = td.attr("id")
        localStorage.removeItem(id)
        //最后重新求和
        sum()
    })
})