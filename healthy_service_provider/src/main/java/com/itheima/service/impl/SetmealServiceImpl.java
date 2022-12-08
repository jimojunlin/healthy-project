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
        if(setmeal.getImg() != null && setmeal.getImg().length() > 0) {
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_IMG_DB_URL, setmeal.getImg());
        }
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
     * 根据id获取套餐数据
     *
     * @param id
     * @return
     */
    public Setmeal getById(Integer id) {
        return setmealDao.getById(id);
    }

    /**
     * 根据套餐id获取套餐所对应的检查组id
     *
     * @param id
     * @return
     */
    public Integer[] getSetmealAndCheckGroupById(Integer id) {
        return setmealDao.getSetmealAndCheckGroupById(id);
    }

    /**
     * 修改套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        //修改套餐信息
        setmealDao.update(setmeal);

        //删除套餐与检查组的对应关系
        setmealDao.deleteRelation(setmeal.getId());

        //添加套餐与检查组的对应关系
        this.addSetmealAndCheckgroup(setmeal, checkgroupIds);
    }

    /**
     * 删除套餐
     *
     * @param id
     */
    public void delete(Integer id) {
        setmealDao.delete(id);
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
