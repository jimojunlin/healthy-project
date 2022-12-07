package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.SetmealDao;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 套餐
 */
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

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
