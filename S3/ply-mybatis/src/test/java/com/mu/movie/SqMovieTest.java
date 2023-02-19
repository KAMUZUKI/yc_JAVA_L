package com.mu.movie;

import com.mu.movie.bean.SpMovie;
import com.mu.movie.bean.SpPlan;
import com.mu.movie.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-12-21 20:30
 **/

public class SqMovieTest {
    @Test
    public void test1() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqMovieMapper mapper = sqlSession.getMapper(SqMovieMapper.class);
        SpMovie spmovie = mapper.selectById(1);
        System.out.println(spmovie);
    }

    @Test
    public void test4(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpPlanMapper mapper = sqlSession.getMapper(SpPlanMapper.class);
        System.out.println("---------1---------");
        SpPlan spPlan = mapper.selectById(1);
        System.out.println("---------2---------");
        //执行一个查询方法，返回相关联的实体数据
        Assert.assertEquals("长津湖",spPlan.getMovie().getName());
        System.out.println("---------3---------");
        Assert.assertEquals(50,(int) spPlan.getHall().getSeats());
        System.out.println("---------4---------");

        System.out.println("---------一对一关联方式二---------");
        List<SpPlan> spPlans = mapper.selectByPrice(50);
        SpPlan spPlan1 = spPlans.get(0);
        Assert.assertEquals("长津湖",spPlan1.getMovie().getName());
        Assert.assertEquals(50,(int) spPlan1.getHall().getSeats());
    }

    @Test
    public void test5(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqMovieMapper mapper = sqlSession.getMapper(SqMovieMapper.class);
        System.out.println("---------1---------");
        List<SpMovie> spMovies = mapper.selectByType("战争");
        System.out.println("---------2---------");
        SpMovie movie = spMovies.get(0);
        System.out.println("---------3---------");
        spMovies.forEach(System.out::println);
        System.out.println("---------4---------");
    }

    @Test
    public void test6(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SqMovieMapper mapper = sqlSession.getMapper(SqMovieMapper.class);
        System.out.println("---------1---------");
        List<SpMovie> spMovies = mapper.selectLikeName("%津%");
        System.out.println("---------2---------");
        SpMovie movie = spMovies.get(0);
        System.out.println("---------3---------");
        spMovies.forEach(System.out::println);
        System.out.println("---------4---------");
    }
    @Test
    public void test12(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpHallMapper hallMapper1 = sqlSession.getMapper(SpHallMapper.class);
        SpHallMapper hallMapper2 = sqlSession.getMapper(SpHallMapper.class);

        hallMapper1.selectBySpHall("豪华",1);

        sqlSession.commit();

        hallMapper2.selectBySpHall("豪华",1);
        hallMapper2.selectBySpHall("豪华",1);
        hallMapper2.selectBySpHall("豪华",1);
    }

    @Test
    public void test13(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpPlanMapper spPlanMapper = sqlSession.getMapper(SpPlanMapper.class);
        SpPlan spPlan = spPlanMapper.selectById(1);
        System.out.println(spPlan.getHall().getName());
        System.out.println(spPlan.getMovie().getName());
    }
}
