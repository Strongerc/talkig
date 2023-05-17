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
 * @since 2023-05-03
 */
@Getter
@Setter
  @ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Long id;

      @ApiModelProperty("用户组，默认1组")
      private Integer groupNumber;

      @ApiModelProperty("姓名")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("头像 可以设置一个默认头像")
      private String headPicture;

      @ApiModelProperty("署名")
      private String nickName;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("状态 0:禁用，1:正常")
      private Integer status;

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
      private Long UpdateUser;

      @ApiModelProperty("电话号码")
      private Long phone;

      @ApiModelProperty("电话号码")
      private String Role;


}
