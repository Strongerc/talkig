package org.sunc.talkig.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
@Getter
@Setter
  @ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
      @TableId(type = IdType.ASSIGN_ID)
        private Long id;

      @ApiModelProperty("课程名称")
      private String title;

      @ApiModelProperty("封面")
      private String image;

      @ApiModelProperty("1代表进行中默认值 0未开始 -1已结束")
      private Integer statu;

      @ApiModelProperty("课程开始时间")
      private LocalDateTime startTime;

      @ApiModelProperty("课程结束时间")
      private LocalDateTime endTime;

      @ApiModelProperty("创建时间")
      @TableField(fill = FieldFill.INSERT) //插入时填充字段
      private LocalDateTime createTime;

      @ApiModelProperty("结束时间")
      @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
      private LocalDateTime updateTime;

      @ApiModelProperty("创建人")
      @TableField(fill = FieldFill.INSERT) //插入时填充字段
      private Integer createUser;

      @ApiModelProperty("更新人")
      @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
      private Integer updateUser;


}
