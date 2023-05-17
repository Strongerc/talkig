package org.sunc.talkig.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sunc.talkig.entity.Messages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author talk
 * @since 2023-05-04
 */
@Mapper
public interface MessagesMapper extends BaseMapper<Messages> {

}
