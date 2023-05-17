package org.sunc.talkig.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sunc.talkig.entity.Category;

/**
 * <p>
 * 菜品及套餐分类 Mapper 接口
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
