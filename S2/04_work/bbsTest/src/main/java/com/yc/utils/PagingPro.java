package com.yc.utils;

import com.yc.bean.PageBean;
import com.yc.dao.DBHelper;

import java.sql.SQLException;

public class PagingPro {
    private long countAll(String boardid, DBHelper db) throws SQLException {
        String sql="select count(*) as total\n" +
                "from topic\n" +
                "where boardid=?;";
        long l=(long)db.selectAggreation(sql,boardid);
        return l;
    }
    private PageBean paging(PageBean pb, String sqlid,DBHelper db) throws SQLException {
        int start=(pb.getPageno()-1)*pb.getPagesize();
        long total=this.countAll(sqlid,db);
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
