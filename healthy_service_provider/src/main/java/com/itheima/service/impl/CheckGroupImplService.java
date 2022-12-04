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
import java.util.List;
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
        this.addCheckGroupIdAndCheckItemId(checkitemIds, checkGroup);
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

    /**
     * 根据id获取检查组数据信息
     *
     * @param id
     * @return
     */
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 根据id查询检查组对应的检查项
     *
     * @param id
     * @return
     */
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 修改检查组
     *
     * @param checkitemIds
     * @param checkGroup
     */
    public void update(Integer[] checkitemIds, CheckGroup checkGroup) {
        //修改检查组信息
        checkGroupDao.update(checkGroup);

        //删除检查组对应的检查项
        checkGroupDao.deleteRelation(checkGroup.getId());

        //绑定检查组与检查项的对应关系
        this.addCheckGroupIdAndCheckItemId(checkitemIds, checkGroup);
    }

    /**
     * 绑定检查组与检查项的对应关系
     * @param checkitemIds
     * @param checkGroup
     */
    public void addCheckGroupIdAndCheckItemId(Integer[] checkitemIds, CheckGroup checkGroup){
        Integer checkGroupId = checkGroup.getId();
        for (Integer checkitemId : checkitemIds) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("checkGroupId", checkGroupId);
            map.put("checkitemId", checkitemId);

            checkGroupDao.addCheckGroupIdAndCheckItemId(map);
        }
    }
}
