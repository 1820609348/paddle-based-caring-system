package com.app.c4_ai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class ElderInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    @ApiModelProperty
    String name;
    @ApiModelProperty
    String gender;
    @ApiModelProperty
    Integer age;
    @ApiModelProperty
    String status;
    @ApiModelProperty
    String pos;
    @ApiModelProperty
    String family;
}
