package org.sunc.talkig.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.sunc.talkig.entity.TComment;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author talk
 * @since 2023-05-08
 */
public interface TCommentMapper extends BaseMapper<TComment> {

    @Select("select c.*,u.username from t_comment c left join user u on c.user_id = u.id \n" +
            "        where c.article_id = #{articleId}  order by id desc")
    List<TComment> findCommentDetail(@Param("articleId") Long articleId);
}
