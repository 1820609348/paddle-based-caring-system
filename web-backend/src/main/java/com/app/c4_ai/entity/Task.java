package com.app.c4_ai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    @ApiModelProperty
    private  int age;
    @ApiModelProperty
    private String theoption;
    @ApiModelProperty
    private String name;
    @ApiModelProperty
    private String team;
    @ApiModelProperty
    private Date time;
    @ApiModelProperty
    private String pos;
    @ApiModelProperty
    private String therank;
    @ApiModelProperty
    private String status;
    @ApiModelProperty
    private float lng;
    @ApiModelProperty
    private float lat;

}
