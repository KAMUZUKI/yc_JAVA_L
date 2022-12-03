package com.yc.web.litener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class ResSessionChangeListner implements HttpSessionAttributeListener {

    public ResSessionChangeListner(){
        //System.out.println("ResSessionAttributeListener的构造方法，看我什么时候执行，执行了几次。。");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
//        System.out.println("向session中添加了属性,内容为:");
//        System.out.println(se.getName()+se.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
//        System.out.println("在session中替换了属性,内容为:");
//        System.out.println(se.getName()+se.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {


    }
}
