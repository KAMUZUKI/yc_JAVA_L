Vue.component("myheader", {
    template: `
    <div>
        <div id="header" class="wrap">
        \t\t<img src="images/logo.gif"/>
        \t</div>
        \t<div id="navbar" class="wrap">
        \t\t<div class="profile">
            <span v-if="user">
                您好,{{user.uname}}
                <a href="LogoutServlet">退出</a>
            </span>
            <span v-else>
                <a href="login.html">登录</a>
                <a href="register.html">注册</a>
            </span>
        \t\t\t<span class="return"><a href="index.html">返回列表</a></span>
        \t\t\t<span class="addnew"><a href="add.html">添加新投票</a></span>
        \t\t</div>
        \t\t<div class="search">
        \t\t\t<form method="post" action="#">
        \t\t\t\t<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value=""/>
        \t\t\t</form>
        \t\t</div>
        \t</div>
    </div>
    `,
    data(){
        return {
            user: null
        }
    },
    created(){
        axios.get("GetLoginedUserServlet").then(res=>{
            if(res.data.code){
                this.user = res.data.data;
            }
        })
    }
});