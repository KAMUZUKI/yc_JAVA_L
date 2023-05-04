package com.mu.resfoods.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mu.bean.Resfood;
import com.mu.resfoods.dao.ResfoodDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2023-04-08 16:51
 **/


@Transactional(propagation = Propagation.SUPPORTS,
        isolation = Isolation.DEFAULT,timeout = 2000,
        readOnly = true,rollbackFor = RuntimeException.class)
@Slf4j
@Service
public class ResfoodBizImpl implements ResfoodBiz{
    @Autowired
    private ResfoodDao resfoodDao;

    @Override
    public List<Resfood> findAll() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("fid");
        return  this.resfoodDao.selectList(wrapper);
    }

    @Override
    public Resfood findById(Integer fid) {
        return this.resfoodDao.selectById(fid);
    }

    @Override
    public Page<Resfood> findByPage(int pageno, int pagesize, String sortby, String sort) {
        QueryWrapper wrapper = new QueryWrapper();
        if( "asc".equalsIgnoreCase(sort)){
            wrapper.orderByAsc(sortby);
        }else{
            wrapper.orderByDesc(sortby);
        }
        Page<Resfood> page = new Page<>(pageno,pagesize);
        Page<Resfood> resfoodPage = this.resfoodDao.selectPage(page, wrapper);
        log.info("总记录数：{}",resfoodPage.getTotal());
        log.info("总页数：{}",resfoodPage.getPages());
        log.info("当前页：{}",resfoodPage.getCurrent());
        return resfoodPage;
    }
}
