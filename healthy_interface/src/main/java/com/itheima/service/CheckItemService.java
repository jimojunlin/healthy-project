package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult queryPage(QueryPageBean queryPageBean);

    /**
     * 根据id删除检查项
     * @param id
     */
    void deleteCheckItem(Long id);

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
