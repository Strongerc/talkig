package org.sunc.talkig.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author cc
 * @version V1.0
 * @Package org.sunc.talkig.entity
 * @date 2023/5/3 18:20
 */
@Data
@TableName("sys_file")
public class Files {

    private Integer id;
    private String name;
    private String type;
    private Long size;
    private String url;
    private String md5;
    private Boolean isDelete;
    private Boolean enable;
}
