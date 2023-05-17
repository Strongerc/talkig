package org.sunc.talkig.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author talk
 * @since 2023-05-08
 */
@Data
@TableName("t_comment")
@ApiModel(value = "TComment对象", description = "")
public class TComment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id")
      private Long id;

      @ApiModelProperty("内容")
      private String content;

      @ApiModelProperty("评论人id")
      private Long userId;

      @ApiModelProperty("评论时间")
      private String time;

      @ApiModelProperty("父id")
      private Long pid;

      @ApiModelProperty("最上级评论id")
      private Long originId;

      @ApiModelProperty("关联文章的id")
      private Long articleId;

      @ApiModelProperty("用户名")
      @TableField(exist = false)
      private String username;

      @ApiModelProperty("根父级id")
      @TableField(exist = false)
      private String PUsername;

      @ApiModelProperty("根的父级名字")
      @TableField(exist = false)
      private Long PUid;

      @TableField(exist = false)
      private List<TComment> children;
}
