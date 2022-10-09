package com.example.oneweb.biz;

import com.example.oneweb.bean.Cust;
import com.example.oneweb.servlet.dao.DBHelper;
import com.example.oneweb.model.PageBean;

import java.util.List;

public class CustBiz {
    //pageno  pagesize
    public PageBean<Cust> pageSearch(PageBean<Cust> pageBean){
        //查询数据库
        //1.根据条件查询数据表的数据总条数  total
        //2.根据相同条件查询数据表的数据  dataset
        List<Cust> dataset = this.findByPage(pageBean.getPageno(), pageBean.getPagesize());
        long total = this.countAll();

        pageBean.setTotal(total);
        pageBean.setDataset(dataset);
        //其他的分页数据
        long totalPages=total%pageBean.getPagesize()==0?total/pageBean.getPagesize():total/pageBean.getPagesize()+1;
        pageBean.setTotalPages((int)totalPages);
        //上一页页号的计算
        if (pageBean.getPageno()<=1){
            pageBean.setPre(1);
        }else {
            pageBean.setPre(pageBean.getPageno()-1);
        }
        //下一页页号的计算
        if (pageBean.getPageno()==totalPages){
            pageBean.setNext((int)totalPages);
        } else{
            pageBean.setNext(pageBean.getPageno()+1);
        }
        return pageBean;
    }

    private long countAll() {
        String sql="select count(uid) from cust ";
        DBHelper db = new DBHelper();
        long l= (long)db.selectAggreation(sql);
        return l;
    }

    /**
     * 根据pageno pagesize查询当前pageno这一页的数据
     * @param pageno
     * @param pagesize
     * @return
     */
    private List<Cust> findByPage(int pageno, int pagesize) {
        String sql="select * from cust order by uid desc limit ?,?";
        DBHelper db = new DBHelper();
        int start = (pageno-1)*pagesize;
        List<Cust> list = db.select(sql, Cust.class, start, pagesize);
        return list;
    }
}
