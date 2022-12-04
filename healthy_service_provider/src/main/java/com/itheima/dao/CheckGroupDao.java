package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

import java.util.Map;

/**
 * 检查组
 */
public interface CheckGroupDao {
    //添加检查组
    void add(CheckGroup checkGroup);

    //添加检查组与检查项的绑定关系
    void addCheckGroupIdAndCheckItemId(Map map);

    //根据条件查询数据
    Page<CheckGroup> selectByCondition(String queryString);
}
