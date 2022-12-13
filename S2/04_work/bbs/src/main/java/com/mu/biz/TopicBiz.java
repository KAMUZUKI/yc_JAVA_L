package com.mu.biz;

import com.mu.bean.Topic;
import com.mu.dao.DbHelper;
import com.mu.web.model.Pagebean;

import java.sql.SQLException;
import java.util.List;

public class TopicBiz {
    public Pagebean pageSearch(Pagebean pagebean,String boardid) throws Exception {

        //System.out.println("Boardid:"+boardid);
        List<Topic> dataset = findBypage(pagebean.getPageno(), pagebean.getPagesize(),boardid);
        long total = countAll(boardid);

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

    private long countAll(String boardid) throws Exception {
        String sql="select count(*) from tbl_topic where boardid=?";
        DbHelper db=new DbHelper();
        long l=(long)db.selectAggreation(sql,boardid);
        return l;
    }

    private List<Topic> findBypage(int pageno, int pagesize,String boardid) throws SQLException {
//        String sql="select * from resfood order by ? ? limit ?,?";
        String sql="select a.topicid,title,content,publishtime,modifytime,uid,uname,boardid, total\n" +
                "from\n" +
                "      (\n" +
                "     select topicid,title,content,date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime,  tbl_user.uid,  uname,boardid\n" +
                "       from tbl_topic\n" +
                "     inner join tbl_user\n" +
                "     on tbl_topic.uid=tbl_user.uid\n" +
                "       where boardid=?  " +
                "       order by modifytime desc \n" +
                "       limit ?,?) a \n" +
                "       left join  \n" +
                "      (select topicid, count(*) as total from tbl_reply\n" +
                "      group by topicid) b\n" +
                "on a.topicid=b.topicid\n";
        DbHelper db=new DbHelper();
        int start=(pageno-1)*pagesize;
//        List<Resfoods> list=db.select(sql,Resfoods.class,sortby,sort,start,pagesize);
        List<Topic> list=db.select(sql,Topic.class,boardid,start,pagesize);
        return list;
    }









}
