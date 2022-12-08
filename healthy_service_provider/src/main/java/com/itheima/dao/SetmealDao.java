package com.itheima.dao;

import com.github.pagehelper.Page;
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

    /**
     * 根据条件查询
     * @param queryString
     * @return
     */
    Page<Setmeal> queryByCondition(String queryString);

    /**
     * 根据id获取套餐数据
     * @param id
     * @return
     */
    Setmeal getById(Integer id);

    /**
     * 根据套餐id获取套餐所对应的检查组id
     * @param id
     * @return
     */
    Integer[] getSetmealAndCheckGroupById(Integer id);

    /**
     * 修改套餐信息
     * @param setmeal
     */
    void update(Setmeal setmeal);

    /**
     * 删除套餐对应的检查组
     * @param id
     */
    void deleteRelation(Integer id);
}
