package com.mu.biz;

import com.mu.bean.Reply;
import com.mu.dao.DbHelper;
import com.mu.web.model.Pagebean;

import java.sql.SQLException;
import java.util.List;

public class ReplyBiz {
    public Pagebean pageSearch(Pagebean pagebean, String topicid) throws Exception {

        //System.out.println("Boardid:"+boardid);
        List<Reply> dataset = findBypage(pagebean.getPageno(), pagebean.getPagesize(),topicid);
        long total = countAll(topicid);

//        Jedis jedis= RedisHelper.getRedisInstance();
//        for(Topic r:dataset){
//            int fid=r.getFid();
//            r.setPraise(jedis.scard(fid+ Constants.REDIS_FOOD_PRAISE));
//        }

        pagebean.setTotal(total);
        pagebean.setDataset(dataset);

        long totalpages=total%pagebean.getPagesize()==0?total/pagebean.getPagesize():total/pagebean.getPagesize()+1;
        pagebean.setTotalpages((int)totalpages);

        if(pagebean.getPageno()<=1 ){
            pagebean.setPre(1);
        }else{
            pagebean.setPre(pagebean.getPageno()-1);
        }
        if(pagebean.getPageno()==totalpages ){
            pagebean.setNext(pagebean.getPageno());
        }else{
            pagebean.setNext(pagebean.getPageno()+1);
        }


        return pagebean;
    }

    private long countAll(String topicid) throws Exception {
        String sql="select count(*) from tbl_reply where topicid=?";
        DbHelper db=new DbHelper();
        long l=(long)db.selectAggreation(sql,topicid);
        return l;
    }

    private List<Reply> findBypage(int pageno, int pagesize,String topicid) throws SQLException {
//        String sql="select * from resfood order by ? ? limit ?,?";
        String sql="select replyid,title,content,  date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,\n" +
                "       date_format(modifytime,'%Y-%m-%d %H:%I:%S') as modifytime, tbl_reply.uid , topicid,\n" +
                "       uname,head,date_format(regtime,'%Y-%m-%d %H:%I:%S') as  regtime\n" +
                "from tbl_reply\n" +
                "         inner join tbl_user\n" +
                "                    on tbl_reply.uid=tbl_user.uid\n" +
                "where topicid=? \n" +
                "order by modifytime desc\n" +
                "limit ?,?;";
        DbHelper db=new DbHelper();
        int start=(pageno-1)*pagesize;
//        List<Resfoods> list=db.select(sql,Resfoods.class,sortby,sort,start,pagesize);
        List<Reply> list=db.select(sql,Reply.class,topicid,start,pagesize);
        return list;
    }






}
