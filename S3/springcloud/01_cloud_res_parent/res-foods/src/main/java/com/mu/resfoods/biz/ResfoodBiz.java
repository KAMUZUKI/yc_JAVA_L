package com.mu.resfoods.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mu.bean.Resfood;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2023-04-08 16:35
 **/

public interface ResfoodBiz {
    public List<Resfood> findAll();

    public Resfood findById(Integer fid);

    public Page<Resfood> findByPage(int pageno,int pagesize,String sortby,String sort);
}
