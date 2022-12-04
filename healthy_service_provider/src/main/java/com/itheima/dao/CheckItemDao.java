package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

//检查项
public interface CheckItemDao {
    /**
     * 添加
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> queryPage(String queryString);

    /**
     * 根据检查项id在检查项与检查组表中查询数据数
     * @param id
     * @return
     */
    Integer selectByIdTotal(Long id);

    /**
     * 根据id删除检查项
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id查询检查项
     * @param id
     * @return
     */
    CheckItem selectById(Long id);

    /**
     * 根据id修改检查项
     * @param checkItem
     */
    void updateCheckItem(CheckItem checkItem);

    /**
     * 获取所有检查项数据
     * @return
     */
    List<CheckItem> getAll();
}
