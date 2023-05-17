package org.sunc.talkig.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author talk
 * @since 2023-05-04
 */
@Getter
@Setter
  @ApiModel(value = "Messages对象", description = "")
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
      private Integer id;

      @ApiModelProperty("消息")
      private String message;


}
