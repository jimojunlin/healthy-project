package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 检查组
 */
@Service
public class CheckGroupImplService implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加检查组
     *
     * @param checkitemIds
     * @param checkGroup
     */
    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {
        //添加检查组
        checkGroupDao.add(checkGroup);

        //添加检查组与检查项的绑定关系
        Integer checkGroupId = checkGroup.getId();
        for (Integer checkitemId : checkitemIds) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("checkGroupId", checkGroupId);
            map.put("checkitemId", checkitemId);

            checkGroupDao.addCheckGroupIdAndCheckItemId(map);
        }
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean) {
        //获取参数
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }
}
