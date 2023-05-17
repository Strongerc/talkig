package org.sunc.talkig.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sunc.talkig.common.Result;
import org.sunc.talkig.entity.Category;
import org.sunc.talkig.service.ICategoryService;

import java.util.List;

/**
 * <p>
 * 分类管理
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /*
    * 新增分类
    * */
    @PostMapping
    public Result save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.saveOrUpdate(category);
        return Result.success("新增分类成功");
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result page(int pageNum,int pageSize, String search){
        log.info("page = {},pageSize = {},search = {}" ,pageNum,pageSize,search);
        //分页构造器
        Page<Category> pageInfo = new Page<>(pageNum,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //分局id条件构造
        queryWrapper.eq(StringUtils.isNotBlank(search),Category::getId, search);
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);

        //分页查询
        Page<Category> page = categoryService.page(pageInfo, queryWrapper);
        return Result.success(page);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(Long id){
        log.info("删除分类，id为：{}",id);

        //categoryService.removeById(id);
        categoryService.remove(id);

        return Result.success("分类信息删除成功");
    }

    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Category category){
        log.info("修改分类信息：{}",category);

        categoryService.updateById(category);

        return Result.success("修改分类信息成功");
    }

    /**
     * 根据条件查询分类数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public Result list(Category category){
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);
        return Result.success(list);
    }


    // 查找所有
    @GetMapping
    public Result findAll() {
        return Result.success(categoryService.list());
    }

}

