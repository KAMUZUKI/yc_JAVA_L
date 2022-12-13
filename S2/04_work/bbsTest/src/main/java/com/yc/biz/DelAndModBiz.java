package com.yc.biz;

import com.yc.dao.DBHelper;

public class DelAndModBiz {

    public int delete(String torr, String id) throws Exception {
        DBHelper db= new DBHelper();
        int flag=0;
        if (torr!=null&&torr.equals("reply")){
            String sql=" delete from reply where replyid=?";
            flag=db.doUpdata(sql,id);
            if(flag!=0){
                flag=1;
            }
        }
        else if (torr!=null&&torr.equals("topic")){
            String sql="delete from reply where replyid in (select id from (select replyid as id from reply where topicid=?) as a)";
            db.doUpdata(sql,id);
            sql="delete from topic where topicid=?";
            flag=db.doUpdata(sql,id);
            if(flag!=0){
                flag=2;
            }
        }
        return flag;
    }

    public int modify(String torr, String id,String title ,String content) throws Exception {
        DBHelper db= new DBHelper();
        int flag=0;
        if (torr!=null&&torr.equals("reply")){
            String sql="update reply set title=?,content=?,modifytime=now() where replyid=?";
            flag=db.doUpdata(sql,title,content,id);
            if(flag!=0){
                flag=1;
            }
        }
        else if (torr!=null&&torr.equals("topic")){
            String sql="  update topic set title=?,content=?,modifytime=now() where topicid=?";
            flag=db.doUpdata(sql,title,content,id);
            if(flag!=0){
                flag=2;
            }
        }
        return flag;
    }
}
