package org.sunc.talkig.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunc.talkig.common.Constants;
import org.sunc.talkig.entity.Category;
import org.sunc.talkig.entity.Messages;
import org.sunc.talkig.exception.CustomException;
import org.sunc.talkig.mapper.CategoryMapper;
import org.sunc.talkig.service.ICategoryService;
import org.sunc.talkig.service.IMessagesService;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    IMessagesService messagesService;

    /*
    *
    *
    * 需要修改
    *
    * */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Messages> messagesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messagesLambdaQueryWrapper.eq(Messages::getMessage, id);
        long count = messagesService.count(messagesLambdaQueryWrapper);
        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        if(count > 0){
            //已经关联菜品，抛出一个业务异常
            throw new CustomException(Constants.CODE_500,"当前分类下关联了菜品，不能删除");
        }
        //this.removeById
        //removeById
        super.removeById(id);
    }
}
