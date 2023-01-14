package com.sjz.cloudcourse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Document对象", description="")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "论文id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "论文题目")
    private String title;

    @ApiModelProperty(value = "论文摘要")
    private String digest;

    @ApiModelProperty(value = "论文题目")
    private String content;

    @ApiModelProperty(value = "论文作者")
    private String author;

    @ApiModelProperty(value = "发表时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private String deleted;


}
