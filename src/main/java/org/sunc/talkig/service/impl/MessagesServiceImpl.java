package org.sunc.talkig.service.impl;

import org.sunc.talkig.entity.Messages;
import org.sunc.talkig.mapper.MessagesMapper;
import org.sunc.talkig.service.IMessagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author talk
 * @since 2023-05-04
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements IMessagesService {

}
