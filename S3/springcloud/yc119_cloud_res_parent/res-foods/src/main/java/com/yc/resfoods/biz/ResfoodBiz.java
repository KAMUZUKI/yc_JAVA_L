package com.yc.resfoods.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.Resfood;

import java.util.List;

/**
 * @program: yc119_cloud_res_parent
 * @description:
 * @author: zy
 * @create: 2023-04-08 15:36
 */
public interface ResfoodBiz {
    public List<Resfood> findAll();

    public Resfood findById(Integer fid);

    public Page<Resfood> findByPage(int pageno, int pagesize, String sortby, String sort);
}
