package com.mu.biz;

import com.mu.bean.Resfood;
import com.mu.dao.DbHelper;
import com.mu.web.model.PageBean;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-10-16 15:54
 **/

public class ResfoodBiz {
    public PageBean findByPage(PageBean pageBean) throws Exception {
        List<Resfood> dataset = this.findByPage(pageBean.getPageno(), pageBean.getPagesize(), pageBean.getSortby(), pageBean.getSort());
        long total = this.countAll();
        pageBean.setTotal(total);
        pageBean.setDataset(dataset);
        //其它的分页数据s
        //计算总页数
        long totalPages=total%pageBean.getPagesize()==0?total/pageBean.getPagesize():total/pageBean.getPagesize()+1;
        pageBean.setTotalPages((int) totalPages);
        //上一页
        if (pageBean.getPageno()<=1){
            pageBean.setPre(1);
        }else {
            pageBean.setPre(pageBean.getPageno()-1);
        }
        //下一页
        if (pageBean.getPageno()==totalPages){
            pageBean.setNext((int) totalPages);
        }else {
            pageBean.setNext(pageBean.getPageno()+1);
        }
        return pageBean;
    }

    private long countAll() throws Exception {
        String sql = "select count(fid) from resfood";
        DbHelper db = new DbHelper();
        return (long)db.selectAggreation(sql);
    }

    private List<Resfood> findByPage(int pageNo, int pageSize,String sortby, String sort) throws Exception {
        String sql = "select * from resfood order by "+ sortby + " " + sort + " limit ?,?";
        DbHelper db = new DbHelper();
        int start = (pageNo-1)*pageSize;
        List<Resfood> list = db.select(sql, Resfood.class,start,pageSize);
        return list;
    }
}
