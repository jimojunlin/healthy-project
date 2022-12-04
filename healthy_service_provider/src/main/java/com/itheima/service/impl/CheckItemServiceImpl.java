package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//检查项
@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 添加检查项
     *
     * @param checkItem
     */
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    public PageResult queryPage(QueryPageBean queryPageBean) {
        //获取参数
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //使用mybatis插件，分页助手，进行分页查询
        PageHelper.startPage(currentPage, pageSize);

        //条件查询
        Page<CheckItem> page = checkItemDao.queryPage(queryString);

        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();

        return new PageResult(total, rows);
    }

    /**
     * 根据id删除检查项
     *
     * @param id
     */
    public void deleteCheckItem(Long id) {
        //判断检查项是否关联检查组
        Integer count = checkItemDao.selectByIdTotal(id);
        if (count > 0){
            //该检查项已关联检查组，不进行删除操作
            new RuntimeException();
        }

        //根据id删除检查项
        checkItemDao.deleteById(id);
    }

    /**
     * 根据id查询检查项
     *
     * @param id
     * @return
     */
    public CheckItem selectById(Long id) {
        return checkItemDao.selectById(id);
    }

    /**
     * 根据id修改检查项
     *
     * @param checkItem
     */
    public void updateCheckItem(CheckItem checkItem) {
        checkItemDao.updateCheckItem(checkItem);
    }

    /**
     * 获取所有检查项数据
     *
     * @return
     */
    public List<CheckItem> getAll() {
        return checkItemDao.getAll();
    }
}
