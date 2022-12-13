package com.yc.biz;

import com.yc.bean.PageBean;
import com.yc.bean.Reply;
import com.yc.dao.DBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class getDetailDataBiz {
    DBHelper db = new DBHelper();

    public PageBean getDetailData(PageBean pb, String topicid) throws SQLException {

        String sql = "select replyid,\n" +
                "       title,\n" +
                "       content,\n" +
                "       date_format(publishtime, '%Y-%m-%d %H:%I:%S') as publishtime,\n" +
                "       date_format(modifytime, '%Y-%m-%d %H:%I:%S')  as modifytime,\n" +
                "       reply.uid   as uid,\n" +
                "       topicid,\n" +
                "       uname,\n" +
                "       head,\n" +
                "       date_format(regtime, '%Y-%m-%d %H:%I:%S')     as regtime\n" +
                "from reply\n" +
                "         inner join user\n" +
                "                    on reply.uid = user.uid\n" +
                "where topicid = ?\n" +
                "order by modifytime desc\n" +
                "limit ?,?";
        int start = (pb.getPageno() - 1) * pb.getPagesize();
        int end = pb.getPageno() * pb.getPagesize();
        long total = this.countAll(topicid);
        List<Reply> list = db.select(sql, Reply.class, topicid, start, end);
        pb.setDataset(list);
        pb.setTotal(total);
        //其他分页数据
        //计算总页数
        long totalPages = total % pb.getPagesize() == 0 ? total / pb.getPagesize() : total / pb.getPagesize() + 1;
        pb.setTotalpages((int) totalPages);
        //上一页页号的计算
        if (pb.getPageno() <= 1) {
            pb.setPre(1);
        } else {
            pb.setPre(pb.getPageno() - 1);
        }
        //下一页页号计算
        if (pb.getPageno() >= totalPages) {
            pb.setNext(pb.getPageno());
        } else {
            pb.setNext(pb.getPageno() + 1);
        }
        return pb;
    }

    private long countAll(String boardid) throws SQLException {
        String sql = "select count(*) as total\n" +
                "from topic\n" +
                "where boardid=?;";
        long l = (long) db.selectAggreation(sql, boardid);
        return l;
    }

    //根据topicid查询detail界面需要展示的帖子详情信息
    public Reply getTopic(String topicid, Reply reply) {
        DBHelper db = new DBHelper();
        String sql = "select title,\n" +
                "       content,\n" +
                "       date_format(publishtime, '%Y-%m-%d %H:%I:%S') as publishtime,\n" +
                "       date_format(modifytime, '%Y-%m-%d %H:%I:%S')  as modifytime,\n" +
                "       topic.uid,\n" +
                "       boardid,\n" +
                "       uname,\n" +
                "       head,\n" +
                "       date_format(regtime, '%Y-%m-%d %H:%I:%S')     as regtime\n" +
                "             from topic\n" +
                "             inner join user\n" +
                "             on topic.uid = user.uid\n" +
                "                where topicid = ?;";
        List<Reply> list = db.select(sql, Reply.class, topicid);
        if (list != null && list.size() > 0) {
            reply = list.get(0);
        }
        return reply;
    }
}
