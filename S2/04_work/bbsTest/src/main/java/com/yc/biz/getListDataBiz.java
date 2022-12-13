package com.yc.biz;

import com.yc.bean.PageBean;
import com.yc.bean.Topic;
import com.yc.dao.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class getListDataBiz {
    DBHelper db = new DBHelper();
    public PageBean findtopic(String boardid,PageBean pb) throws SQLException {

        String sql = "   select a.topicid,\n" +
                "    title,\n" +
                "    content,\n" +
                "    publishtime,\n" +
                "    modifytime,\n" +
                "    uid,\n" +
                "    uname,\n" +
                "    boardid,\n" +
                "    total\n" +
                "    from (\n" +
                "    select topicid,\n" +
                "    title,\n" +
                "    content,\n" +
                "    date_format(publishtime, '%Y-%m-%d %H:%I:%S') as publishtime,\n" +
                "    date_format(modifytime, '%Y-%m-%d %H:%I:%S') as modifytime,\n" +
                "    user.uid,\n" +
                "    uname,\n" +
                "    boardid\n" +
                "    from topic\n" +
                "    inner join user\n" +
                "    on topic.uid = user.uid\n" +
                "    where boardid = ?\n" +
                "    order by modifytime desc limit ?,?) a\n" +
                "    left join\n" +
                "    (select topicid, count(*) as total\n" +
                "    from reply\n" +
                "    group by topicid) b\n" +
                "    on a.topicid = b.topicid;";
        int start=(pb.getPageno()-1)*pb.getPagesize();
        List<Topic> list = db.select(sql, Topic.class, boardid,start, pb.getPagesize());

        long total=this.countAll(boardid);
        pb.setTotal(total);
        //其他分页数据
        //计算总页数
        long totalPages=total%pb.getPagesize()==0?total/pb.getPagesize():total/pb.getPagesize()+1;
        pb.setTotalpages((int)totalPages);
        //上一页页号的计算
        if(pb.getPageno()<=1){
            pb.setPre(1);
        }else{
            pb.setPre(pb.getPageno()-1);
        }
        //下一页页号计算
        if(pb.getPageno()>=totalPages){
            pb.setNext(pb.getPageno());
        }else{
            pb.setNext(pb.getPageno()+1);
        }
        pb.setDataset(list);
        return pb;
    }

    private long countAll(String boardid) throws SQLException {
        String sql="select count(*) as total\n" +
                "from topic\n" +
                "where boardid=?;";
        long l=(long)db.selectAggreation(sql,boardid);
        return l;
    }
    private PageBean paging(PageBean pb,String sqlid) throws SQLException {
        int start=(pb.getPageno()-1)*pb.getPagesize();
        long total=this.countAll(sqlid);
        pb.setTotal(total);
        //其他分页数据
        //计算总页数
        long totalPages=total%pb.getPagesize()==0?total/pb.getPagesize():total/pb.getPagesize()+1;
        pb.setTotalpages((int)totalPages);
        //上一页页号的计算
        if(pb.getPageno()<=1){
            pb.setPre(1);
        }else{
            pb.setPre(pb.getPageno()-1);
        }
        //下一页页号计算
        if(pb.getPageno()>=totalPages){
            pb.setNext(pb.getPageno());
        }else{
            pb.setNext(pb.getPageno()+1);
        }
        return pb;
    }
}
