package com.fz.boot.es.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description es实体类
 * 1.为需要使用索引库的实体类加上注解@Document：indexName="索引库名"  shards = 分片数量(默认1) replicas = 副本数量(默认1,需要设置0)
 * 2.为id加上注解@Id
 * 3.为各个字段加上注解并制定类型，@Field( type =字段类型(枚举),analyzer = 分词器(固定写法"ik_max_word"),index=是否创建索引(默认true)   )
 * @Author fangzheng
 * @Date 2020/8/26 14:16
 */
@Data
@AllArgsConstructor
@Document(indexName = "goods",shards = 1,replicas = 1)
public class Item {
    @Id
    private Long id;
    @Field(type=FieldType.Text,analyzer = "ik_max_word")
    private String title;
    @Field(type=FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Keyword)
    private String brand;
    @Field(type = FieldType.Double)
    private Double price;
    /**
     * 不会对图片地址查询,指定为false
     */
    @Field(type = FieldType.Keyword,index = false)
    private String images;
    @Field(type = FieldType.Date, format = DateFormat.date_time, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime create_time;
}
