package com.fz.boot.es.respository;

import com.fz.boot.es.entity.Item;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 自定义接口继承ElasticsearchRepository<类名,ID类型>
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {


    /**
     * ElasticsearchRepository也可以根据自定义模板来自定义查询，不需要实现,空手套白狼
     * @param title
     * @return
     */
    List<Item> findByTitle(String title);

    /**
     * 范围查item
     * @param left
     * @param right
     * @return
     */
    List<Item> findByPriceBetween(Double left,Double right);

    /**
     * match查询并设置operator
     * @param title
     * @param operator
     * @return
     */
    @Query("{\"match\": {\"title\":{ \"query\": \"?0\",\"operator\":\"?1\"}}}")
    Item findByTitleOperator(String title,String operator);
}
