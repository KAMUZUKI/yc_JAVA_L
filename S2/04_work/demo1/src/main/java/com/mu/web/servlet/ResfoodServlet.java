package com.mu.web.servlet;

import com.mu.bean.Resfood;
import com.mu.bean.Resuser;
import com.mu.biz.ResfoodBiz;
import com.mu.dao.DbHelper;
import com.mu.dao.RedisHelper;
import com.mu.utils.MuConstants;
import com.mu.web.model.JsonModel;
import com.mu.web.model.PageBean;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 16:49
 **/

@WebServlet(name = "ResfoodServlet",value = "/resfood.action")
public class ResfoodServlet extends CommonServlet {
    protected void clickPraise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jsonModel = new JsonModel();
        Resfood resfood = null;
        try {
            resfood = super.parseRequestToT(request, Resfood.class);
            int fid = resfood.getFid();

            HttpSession session = request.getSession();
            Resuser resuser = (Resuser) session.getAttribute(MuConstants.RESUSER);
            int userid = resuser.getUserid();

            Jedis jedis = RedisHelper.getRedisInstance();
            if (jedis.sismember(fid + MuConstants.REDIS_FOOD_PRAISE, userid + "")) {
                //用户以经点过这道菜的赞 再次点击即为取消点赞
                jedis.srem(fid + MuConstants.REDIS_FOOD_PRAISE, userid + "");
                //用户编号_praise Set<菜品名> 删除
                jedis.srem(userid + MuConstants.REDIS_PRAISE, fid + "");
            }else{
                //此用户没有对这些菜点过赞
                jedis.sadd(fid + MuConstants.REDIS_FOOD_PRAISE, userid + "");
                //记录此用户点过那些菜
                jedis.sadd(userid + MuConstants.REDIS_PRAISE, fid + "");
            }
            //获取点赞数
            long praise = jedis.scard(fid + MuConstants.REDIS_FOOD_PRAISE);
            resfood.setPraise(praise);
            jsonModel.setCode(1);
            jsonModel.setData(resfood);
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setCode(0);
            jsonModel.setMsg("点赞失败" + e.getMessage());
        }
        super.writeJson(jsonModel, response);
    }

    protected void findFoodsByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        PageBean pb = null;
        try {
            //1.取前端的分页的参数
            pb = super.parseRequestToT(request,PageBean.class);
            //2.调用业务层完成分页查询
            ResfoodBiz rb = new ResfoodBiz();
            pb=rb.findByPage(pb);

            HttpSession session = request.getSession();
            session.setAttribute("foodList",pb.getDataset());

             jm.setCode(1);
             jm.setData(pb);
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg("查询失败" + e.getMessage());
        }
        super.writeJson(jm,response);
    }

    protected void findAllFoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();

        String sql = "select * from resfood";
        DbHelper db = new DbHelper();
        List<Resfood> list = db.select(sql, Resfood.class);

        jm.setCode(1);
        jm.setData(list);

        super.writeJson(jm,response);
    }

    protected void getHistory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        HttpSession session = request.getSession();

        Resuser resuser = (Resuser)session.getAttribute("resuser");
        if (resuser == null) {
            jm.setCode(0);
            jm.setMsg("请先登录");
        }
        int userid=resuser.getUserid();
        Jedis jedis = RedisHelper.getRedisInstance();
        Set<String> visitedFoodIds = jedis.zrevrange(userid + MuConstants.REDIS_VISITED, 0, 4);
        if (visitedFoodIds==null||visitedFoodIds.size()<=0) {
            jm.setCode(0);
            jm.setMsg("没有浏览记录");
            super.writeJson(jm, response);
            return;
        }

        //拼接查询条件
        StringBuffer sb = new StringBuffer("select * from resfood where fid in (");
        for (String id : visitedFoodIds) {
            sb.append(" ?,");
        }

        String sql = sb.toString();

        //最终查询语句
        sql=sql.substring(0,sql.length()-1);
        sql+=")";
        System.out.println("sql:"+sql);
        DbHelper db = new DbHelper();
        List<Resfood> list = db.select(sql, Resfood.class, visitedFoodIds.toArray());
        jm.setCode(1);
        jm.setData(list);
        super.writeJson(jm, response);
    }

    protected void traceBrowseFood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        String fid = request.getParameter("fid");
        HttpSession session = request.getSession();
        if (session.getAttribute("resuser")==null) {
            jm.setMsg("未登录用户不记录足迹");
            super.writeJson(jm, response);
            return;
        }
        Resuser resuser = (Resuser)session.getAttribute("resuser");
        int userid=resuser.getUserid();
        Date d = new Date();
        Jedis jedis = RedisHelper.getRedisInstance();
        jedis.select(1);
        long length = jedis.zcard(userid + MuConstants.REDIS_VISITED);
        if (length >= 5) {
            //rem表示降序排列
            jedis.zremrangeByRank(userid + MuConstants.REDIS_VISITED, 0, 0);
        }
        jedis.zadd(userid + MuConstants.REDIS_VISITED, d.getTime(), fid);
        //保存到服务器30天
        jedis.expire(userid + MuConstants.REDIS_VISITED, 60 * 60 * 24 * 30);
        jm.setCode(1);
        super.writeJson(jm, response);
    }
}
