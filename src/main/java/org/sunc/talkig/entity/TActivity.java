package org.sunc.talkig.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-05-06
 */
@Getter
@Setter
  @TableName("t_activity")
@ApiModel(value = "TActivity对象", description = "")
public class TActivity implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Long id;

      @ApiModelProperty("活动类型")
      private Integer type;

      @ApiModelProperty("教学活动标题/问题/引入")
      private String name;

      @ApiModelProperty("视频url")
      private String fileUrl;

      @ApiModelProperty("教学活动内容")
      private String content;

      @ApiModelProperty("仿真链接")
      private String url;

      @ApiModelProperty("课程id")
      private Long courseId;


}
