package org.sunc.talkig.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sunc.talkig.common.Result;
import org.sunc.talkig.dto.CourseDTO;
import org.sunc.talkig.entity.Course;
import org.sunc.talkig.service.ICourseService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    ICourseService courseService;

    // 保存并返回课程和活动
    @PostMapping
    public Result save(@RequestBody CourseDTO courseDTO) {
        log.info(courseDTO.toString());
        Long aLong = courseService.saveWithActivity(courseDTO);
        return Result.success(aLong);
    }

    // 根据id删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseService.removeById(id);
        return Result.success();
    }
    // 多个id删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseService.removeByIds(ids);
        return Result.success();
    }
    // 查找所有
    @GetMapping
    public Result findAll() {
        return Result.success(courseService.list());
    }

    // 根据id找一个
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        CourseDTO courseDTO = courseService.getByIdWithActivity(id);
        return Result.success(courseDTO);
    }
    /**
     * 用户信息分页查询
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result page(int pageNum,int pageSize,String name){
        log.info("page = {},pageSize = {},name = {}" ,pageNum,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(pageNum,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Course::getTitle,name);
        //添加排序条件
        queryWrapper.orderByDesc(Course::getUpdateTime);

        //执行查询
        Page page = courseService.page(pageInfo, queryWrapper);

        return Result.success(page);
    }

    /*
    * 根据id拿到相关信息
    * */
    @GetMapping("/course/{id}")
    public Result findUsernameOne(@PathVariable String id) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getId, id);
        return Result.success(courseService.getOne(queryWrapper));
    }

    /*
    * 条件查询相关信息
    * */
    @GetMapping("/list")
    public Result list(Course course) {
        return Result.success();
    }


}

