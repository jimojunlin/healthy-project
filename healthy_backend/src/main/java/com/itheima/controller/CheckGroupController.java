package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 检查组
 */

@RestController
@RequestMapping("/checkGroupItem")
@Slf4j
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    //添加检查组
    @PostMapping("/add")
    public Result add(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
//        log.info("ids:{}", Arrays.toString(checkitemIds));
//        log.info("checkgroup:{}", checkGroup);
        log.info("添加检查组");
        try{
            checkGroupService.add(checkitemIds, checkGroup);
        } catch (Exception e){
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    //分页查询
    @PostMapping("/queryPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        log.info("分页查询条件：{}", queryPageBean);
        return checkGroupService.findPage(queryPageBean);
    }

    //根据id获取检查组数据信息
    @GetMapping("/findById")
    public Result findById(Integer id){
        log.info("根据id获取检查组数据信息：{}", id);
        try{
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        } catch (Exception e){
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    //根据id查询检查组对应的检查项
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findByIdAndCheckItem(Integer id){
        log.info("根据id查询检查组对应的检查项：{}", id);
        try {
            List<Integer> list = checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
        } catch (Exception e){
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //修改检查组
    @PostMapping("/update")
    public Result update(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        log.info("修改检查组");
        try{
            checkGroupService.update(checkitemIds, checkGroup);
        } catch (Exception e){
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    //获取所有检查组信息
    @GetMapping("/getAll")
    public Result getAll(){
        try{
            List<CheckGroup> list = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, list);
        } catch (Exception e){
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

}
