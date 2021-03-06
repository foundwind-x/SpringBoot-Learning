package com.fz.boot.sharding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Macky
 * @Title class Book
 * @Description: 书籍是实体类
 * @date 2019/7/13 15:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("book")
public class Book extends Model<Book> {
    @TableId(type=IdType.INPUT)
    private int id;
    private String name;
    private int count;
}
