package org.sunc.talkig.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.sunc.talkig.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author talk
 * @since 2023-05-02
 */
public interface UserMapper extends BaseMapper<User> {
    void insertUser(User user);

    // @Select("select id from user where username = #{username}")
    // public Long getIdByUserName(@Param("username") String username);
}
