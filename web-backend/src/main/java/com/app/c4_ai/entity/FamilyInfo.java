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
public class FamilyInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    @ApiModelProperty
    String name;
    @ApiModelProperty
    String guardian;
    @ApiModelProperty
    String relationship;
    @ApiModelProperty
    Date registrationDate;
    @ApiModelProperty
    String familySituation;
    @ApiModelProperty
    String residence;
}
