package com.sjz.cloudcourse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
//@Data
//@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="Buyer对象", description="")
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "关联的用户id")
    private Integer uId;

    @ApiModelProperty(value = "关联的图书id")
    private Integer bId;

    public Buyer(Integer id, Integer uId, Integer bId) {
        this.id = id;
        this.uId = uId;
        this.bId = bId;
    }

    public Buyer() {
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", uId=" + uId +
                ", bId=" + bId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }
}
