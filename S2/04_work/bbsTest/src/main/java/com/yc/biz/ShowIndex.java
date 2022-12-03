package com.yc.biz;

import com.yc.bean.Board;
import com.yc.bean.Reply;
import com.yc.bean.Topic;
import com.yc.dao.DBHelper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从数据库中获取信息
 */
public class ShowIndex {
    public static void main(String[] args) {
        ShowIndex showIndex = new ShowIndex();
        System.out.println(showIndex.getData());
    }

    //查找主界面要显示的数据
    public Map<Board, List<Board>> getData() {
        DBHelper db = new DBHelper();
        String sql = "select a.boardid,\n" +
                "       boardname,\n" +
                "       parentid,\n" +
                "       total,\n" +
                "       topicid,\n" +
                "       title,\n" +
                "       modifytime,\n" +
                "       uname\n" +
                "from (\n" +
                "    select board.boardid, boardname, parentid, count(topicid) as total\n" +
                "    from board\n" +
                "    left join topic\n" +
                "    on board.boardid = topic.boardid\n" +
                "    group by board.boardid, boardname, parentid\n" +
                "    ) a\n" +
                "    left join\n" +
                "    (\n" +
                "    select topicid, title, a.modifytime, uname, a.boardid\n" +
                "    from (select topicid, title, modifytime, uname, boardid\n" +
                "    from topic\n" +
                "    left join user\n" +
                "    on topic.uid = user.uid\n" +
                "    ) a,\n" +
                "    (\n" +
                "    select boardid, max(modifytime) as modifytime\n" +
                "    from topic\n" +
                "    group by boardid\n" +
                "    ) b\n" +
                "    where a.boardid = b.boardid\n" +
                "    and a.modifytime = b.modifytime\n" +
                "    ) b\n" +
                "on a.boardid = b.boardid;";
        List<Board> list = db.select(sql, Board.class);
        //创建一个map,键就放主板块,值放一个List,里面放子版块和每个子版块的最新帖子
        //Map==>map里面的键不能重复,它是通过equals方法对比键的hashcode来判断的,所以要重写Board的toString/equals/hashcode方法
        Map<Board, List<Board>> map = new HashMap<>();
        //循环查询出来的list
        for (Board board : list) {
            //先将主板快存入map的键
            if (board.getParentid() == 0) {//parentid=0,说明这个是父板块
                map.put(board, new ArrayList<>());//将父板块存入map的键,map的值就先存入一个空的list
            }
        }
        for (Board board : list) {
            //再将子版块存入map中对应父板块的值
            if (board.getParentid() == 0) {//如果是父板块则跳过
                continue;
            }
            //不是父板块,先取出它的父板块id,通过父板块id找到map中对应的子版块位置,存进去.
            Board parentboard = new Board();
            //通过这个子版块的parentid创建一个board对象,用于找出map中对应的键
            parentboard.setBoardid(board.getParentid());
            //创建一个list,用于存放子板块
            List<Board> sonBoardList = new ArrayList<Board>();
            boolean a=map.containsKey(parentboard);
            if (a) {//判断map里面是否有这个键
                //如果有这个键,就将它的子版块取出来,放到sonBoardList里面
                map.get(parentboard).add(board);
                //将循坏的board放到这个子版块里面
            }
        }
        return map;
    }

    //发帖子时将数据插入数据库
    public int insertData(Topic topic) throws Exception {
        DBHelper db=new DBHelper();
        String sql="insert into topic(title,content,publishtime,modifytime,uid,boardid) values(?,?,now(),now(),?,?)";
        int result=0;
        result=db.doUpdata(sql,topic.getTitle(),topic.getContent(),topic.getUid(),topic.getBoardid());
        return result;
    }

    //回复帖子时将数据插入数据库
    public int insertReplyData(Reply reply) throws Exception {
        DBHelper db=new DBHelper();
        String sql="insert into reply(title,content,publishtime,modifytime,uid,topicid) values(?,?,now(),now(),?,?)";
        int result=0;
        result=db.doUpdata(sql,reply.getTitle(),reply.getContent(),reply.getUid(),reply.getTopicid());
        return result;
    }
}
