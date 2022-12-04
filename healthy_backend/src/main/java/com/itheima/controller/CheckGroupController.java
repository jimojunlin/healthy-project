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

import java.util.ArrayList;
import java.util.Arrays;
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
}
