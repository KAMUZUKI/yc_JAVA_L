package com.mu.biz;

import com.mu.bean.Board;
import com.mu.dao.DbHelper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BoardBiz {


    public List<Board> GetBoard( List<Board> list1, List<Map<String, Object>> list2) throws SQLException {
        DbHelper db=new DbHelper();
        String sql=null;
        for(int i=0;i<list1.size();i++){
            list1.get(i).setTitle("无");
            //list1.get(i).setModifytime("无");
            list1.get(i).setUname("无");
            list1.get(i).setTopicnum(0);
            //list1.get(i).setTopicid(0);
            for(int j=0;j<list2.size();j++) {
                Object b=list2.get(j).get("boardid");
                String a=String.valueOf(b);
                Integer boardid=Integer.parseInt(a.toString());
                if (list1.get(i).getBoardid().equals(boardid)) {
                    list1.get(i).setTopicid(Integer.parseInt(list2.get(j).get("topicid").toString()));
                    list1.get(i).setTopicnum(Integer.parseInt(list2.get(j).get("num").toString()));
                    sql = "select uname,title,modifytime from tbl_topic,tbl_user where  tbl_topic.uid=tbl_user.uid and topicid=" + list2.get(j).get("topicid");
                    List<Map<String, Object>> result = db.select(sql);
                    list1.get(i).setUname(result.get(0).get("uname").toString());
                    list1.get(i).setTitle(result.get(0).get("title").toString());
                    list1.get(i).setModifytime(result.get(0).get("modifytime").toString());
                }
            }
        }
        return list1;
    }
}
