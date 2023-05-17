package org.sunc.talkig.dto;

import lombok.Data;
import org.sunc.talkig.entity.Discussion;

/**
 * @author cc
 * @version V1.0
 * @Package org.sunc.talkig.dto
 * @date 2023/5/8 12:24
 */
@Data
public class DiscussionDTO extends Discussion {
    String username;
}
