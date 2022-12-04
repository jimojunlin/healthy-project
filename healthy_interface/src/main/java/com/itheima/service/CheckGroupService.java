package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 检查组
 */
public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkitemIds
     * @param checkGroup
     */
    void add(Integer[] checkitemIds, CheckGroup checkGroup);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据id获取检查组数据信息
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 根据id查询检查组对应的检查项
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 修改检查组
     * @param checkitemIds
     * @param checkGroup
     */
    void update(Integer[] checkitemIds, CheckGroup checkGroup);
}
