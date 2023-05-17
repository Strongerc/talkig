package org.sunc.talkig.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author talk
 * @since 2023-05-06
 */
@Getter
@Setter
  @ApiModel(value = "Discussion对象", description = "")
public class Discussion implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Long id;

      @ApiModelProperty("用户id")
        private Long uid;

      @ApiModelProperty("消息")
      private String message;

      @ApiModelProperty("创建日期")
      private LocalDateTime createTime;

      @ApiModelProperty("更新日期")
      private LocalDateTime updateTime;

      @ApiModelProperty("点赞数")
      private Integer like;

      @ApiModelProperty("不喜欢数")
      private Integer unlike;


}
