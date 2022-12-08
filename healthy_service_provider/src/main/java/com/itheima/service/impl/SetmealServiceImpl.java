package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * 套餐
 */
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //添加套信息
        setmealDao.add(setmeal);
        //添加套餐与检查组的关系
        this.addSetmealAndCheckgroup(setmeal, checkgroupIds);
        //将套餐图片url保存到redis
        jedisPool.getResource().sadd(RedisConstant.REDIS_IMG_DB_URL, setmeal.getImg());
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean) {
        //获取数据
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //分页查询
        PageHelper.startPage(currentPage, pageSize);

        //根据条件查询
        Page<Setmeal> page = setmealDao.queryByCondition(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 添加套餐与检查组的关系
     * @param setmeal
     * @param checkgroupIds
     */
    public void addSetmealAndCheckgroup(Setmeal setmeal, Integer[] checkgroupIds) {
        //获取套餐id
        Integer setmealId = setmeal.getId();

        for (Integer checkgroupId : checkgroupIds) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("setmealId", setmealId);
            map.put("checkgroupId", checkgroupId);
            setmealDao.setSetmealAndCheckGroup(map);
        }
    }
}
