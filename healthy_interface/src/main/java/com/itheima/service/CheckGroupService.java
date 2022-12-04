package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestBody;

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
}
