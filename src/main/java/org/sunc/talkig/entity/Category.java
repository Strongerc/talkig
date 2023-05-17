package org.sunc.talkig.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜品及套餐分类
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
@Getter
@Setter
  @ApiModel(value = "Category对象", description = "三个问题的接口")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Long id;

      @ApiModelProperty("分类名称")
      private String name;

      @ApiModelProperty("内容")
      private String content;

      @ApiModelProperty("分类")
      private Integer type;

      @ApiModelProperty("顺序")
      private Integer sort;

      @ApiModelProperty("创建时间")
      @TableField(fill = FieldFill.INSERT) //插入时填充字段
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
      private LocalDateTime updateTime;

      @ApiModelProperty("创建人")
      @TableField(fill = FieldFill.INSERT) //插入时填充字段
      private Long createUser;

      @ApiModelProperty("修改人")
      @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
      private Long updateUser;


}
