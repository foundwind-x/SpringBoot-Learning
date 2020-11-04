package com.fz.boot.es;

import com.fz.boot.es.entity.Item;
import com.fz.boot.es.respository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author fangzheng
 * @Date 2020/8/26 17:38
 */
@SpringBootTest
public class EsSearchTest {

    //注入jpa查询
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void test(){
        Iterable<Item> all = itemRepository.findAll();
        all.forEach(System.out::println);
    }
}
