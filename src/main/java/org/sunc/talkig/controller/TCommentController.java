package org.sunc.talkig.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.sunc.talkig.common.Result;
import org.sunc.talkig.entity.TComment;
import org.sunc.talkig.service.ITCommentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author talk
 * @since 2023-05-08
 */
@RestController
@RequestMapping("/comment")
public class TCommentController {

    @Resource
    private ITCommentService commentService;

    // 新增评论，更新评论，回复评论
    @PostMapping
    public Result save(@RequestBody TComment comment) {
        comment.setTime(DateUtil.now());

        // 前端通过回复按钮，会配置pid
        if (comment.getPid() != null) {  // 判断如果是回复，进行处理
            Long pid = comment.getPid();
            // 拿到所有一级评论，每个子的评论有pid
            TComment pComment = commentService.getById(pid);
            if (pComment.getOriginId() != null) {  // 如果当前回复的父级有祖宗，那么就设置相同的祖宗
                comment.setOriginId(pComment.getOriginId());
            } else {  // 否则就设置父级为当前回复的祖宗
                comment.setOriginId(comment.getPid());
            }
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        commentService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        commentService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(commentService.list());
    }

    @GetMapping("/tree/{articleId}")
    public Result findTree(@PathVariable Long articleId) {
        // 一篇文章所有评论
        List<TComment> articleComments = commentService.findCommentDetail(articleId);  // 查询所有的评论和回复数据
        // 查询评论数据（不包括回复），originList的评论，一级评论m,过滤出满足这个条件的内容
        List<TComment> originList = articleComments.stream().filter(comment -> comment.getOriginId() == null).collect(Collectors.toList());

        // 设置评论数据的子节点，也就是回复内容
        for (TComment origin : originList) {
            // 拿到所有回复评论
            List<TComment> comments = articleComments.stream().filter(comment -> origin.getId().equals(comment.getOriginId())).collect(Collectors.toList());  // 表示回复对象集合
            comments.forEach(comment -> {
                // 拿到当前回复评论的父级评论
                Optional<TComment> pComment = articleComments.stream().filter(c1 -> c1.getId().equals(comment.getPid())).findFirst();  // 找到当前评论的父级
                pComment.ifPresent((v -> {  // 找到父级评论的用户id和用户昵称，并设置给当前的回复对象
                    comment.setPUid(v.getUserId());
                    comment.setPUsername(v.getUsername());
                }));
            });
            origin.setChildren(comments);
        }
        return Result.success(originList);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(commentService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<TComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(commentService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}

