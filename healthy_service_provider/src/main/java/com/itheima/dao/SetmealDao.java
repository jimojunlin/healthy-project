package com.itheima.dao;

import com.itheima.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    /**
     * 添加套餐
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 添加套餐与检查组的关系
     * @param map
     */
    void setSetmealAndCheckGroup(Map map);
}
