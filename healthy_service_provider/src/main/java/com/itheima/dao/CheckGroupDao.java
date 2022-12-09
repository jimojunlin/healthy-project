package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

import java.util.List;
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

    //根据id获取检查组数据信息
    CheckGroup findById(Integer id);

    //根据id查询检查组对应的检查项
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    //修改检查组信息
    void update(CheckGroup checkGroup);

    //删除检查组对应的检查项
    void deleteRelation(Integer id);

    //获取所有检查组信息
    List<CheckGroup> findAll();

    //删除检查组
    void delete(Integer id);
}
