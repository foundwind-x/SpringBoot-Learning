package com.fz.boot.es;

import com.fz.boot.es.entity.Item;
import com.fz.boot.es.respository.ItemRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class EsApplicationTests {

    //注入jpa查询
    @Autowired
    private ItemRepository itemRepository;

    //是使用高级REST客户端的ElasticsearchOperations接口的实现
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    /**
     * 创建索引库：indexOps返回了DefaultIndexOperations基于高级代理实现了IndexOperations接口
     */
    @Test
    void textIndex() {
        //设置索引信息(绑定实体类)  返回IndexOperations
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Item.class);
        //创建索引库
        indexOperations.create();
        //Creates the index mapping for the entity this IndexOperations is bound to.
        //为该IndexOperations绑定到的实体创建索引映射。  有一个为给定类创建索引的重载,需要类的字节码文件
        //返回创建的索引
        Document mapping = indexOperations.createMapping();
        System.out.println(mapping);
        //writes a mapping to the index
        //将刚刚通过类创建的映射写入索引
        indexOperations.putMapping(mapping);
        //delete()
        //Deletes the index this IndexOperations is bound to
        //exists() Checks if the index this IndexOperations is bound to exists
    }

    @Test
    void deleteIndex(){
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Item.class);
        boolean delete = indexOperations.delete();
        System.out.println("====delete state====" + delete);
    }

    /**
     * 通过ElasticsearchRepository操作:
     * saveAll方法进行批量数据的新增,和修改  有相同id就是修改,无相同id就是新增
     * findAll查所有
     */
    @Test
    public void testSaveAll() {
        List<Item> list = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        list.add(new Item(1L,"OPPOFindX2","手机","OPPO",4999d,"http://image.leyou.com/13123.jpg", now));
        list.add(new Item(2L,"OPPOFindX","手机","OPPO",3999d,"http://image.leyou.com/13123.jpg", now));
        list.add(new Item(3L,"OPPORENO","手机","OPPO",2999d,"http://image.leyou.com/13123.jpg", now));
        list.add(new Item(4L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg", now));
        list.add(new Item(5L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg", now));
        list.add(new Item(6L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg", now));
        list.add(new Item(7L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg", now));
        list.add(new Item(8L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg", now));
        itemRepository.saveAll(list);
        Iterable<Item> all = itemRepository.findAll();
        all.forEach(System.out::println);
    }

    /**
     * save方法进行数据的新增,和修改  有相同id就是修改,无相同id就是新增
     */
    @Test
    void testSave() {
        Item item=new Item(1L,"OPPOFindX2","手机","OPPO",4999d,"http://image.leyou.com/13123.jpg",LocalDateTime.now());
        Item saveItem = itemRepository.save(item);
        System.out.println(saveItem);
        Iterable<Item> all = itemRepository.findAll();
        all.forEach(System.out::println);
    }

    /**
     * 通过ID查询：findById
     */
    @Test
    void testFindById(){
        Item byId = itemRepository.findById(2L).get();
        System.out.println(byId);
    }

    /**
     * 通过普通索引字段查询：findByXxx
     */
    @Test
    void testFindByTitle() {
        List<Item> items = itemRepository.findByTitle("OPPOFindX");
        System.out.println(items);
    }

    /**
     * findByXxxBetween
     */
    @Test
    void testFindByPriceBetween() {
        List<Item> items = itemRepository.findByPriceBetween(1000d, 8000d);
        items.forEach(System.out::println);
    }

    /**
     * findByXxxOperator
     */
    @Test
    void testFindByTitleOperator() {
        Item item = itemRepository.findByTitleOperator("小米手机7","and");
        System.out.println(item);
    }

    //-------------- 使用ElasticsearchRestTemplate高级Rest客户端 -------------------
    /**
     * delete删除文档
     */
    @Test
    void testDelete1(){
        String count = elasticsearchRestTemplate.delete(String.valueOf(1), IndexCoordinates.of("goods"));
        System.out.println(count);
    }
    @Test
    void testDelete2(){
        elasticsearchRestTemplate.delete(
                new NativeSearchQueryBuilder().withQuery(
                        QueryBuilders.matchQuery("title", "OPPOFindX")
                ).build(),Item.class,IndexCoordinates.of("goods"));
        SearchHits<Item> search = elasticsearchRestTemplate.search(new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery()).build(), Item.class);
        search.forEach(System.out::println);
    }

    /**
     * save
     */
    @Test
    void testSaveRest(){
        List<Item> list = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        list.add(new Item(1L,"OPPOFindX2","手机","OPPO",4999d,"http://image.leyou.com/13123.jpg",now));
        list.add(new Item(2L,"OPPOFindX","手机","OPPO",3999d,"http://image.leyou.com/13123.jpg",now));
        list.add(new Item(3L,"OPPORENO","手机","OPPO",2999d,"http://image.leyou.com/13123.jpg",now));
        list.add(new Item(4L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg",now));
        list.add(new Item(5L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg",now));
        list.add(new Item(6L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg",now));
        list.add(new Item(7L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg",now));
        list.add(new Item(8L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg",now));
        Iterable<Item> save = elasticsearchRestTemplate.save(list);
        save.forEach(System.out::println);
    }
    /**
     * 采用elasticsearchRestTemplate查询
     */
    @Test
    void testSearch1() {
        //利用构造器建造NativeSearchQuery  他可以添加条件,过滤,等复杂操作
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("title", "OPPOFindX"))
                .build();
        //elasticsearchRestTemplate.search方法参数一,本机查询的构造,参数二index的类,可选参数三再次声明库名(可以多个)
        SearchHits<Item> search = elasticsearchRestTemplate.search(query, Item.class);

        search.forEach(searchHit-> System.out.println(searchHit.getContent()));
    }

    /**
     * 分页
     */
    @Test
    void testNativeSearchQueryBuilder2() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("category", "手机"))
                //添加分页  注意页码是从0开始的
                //pageable的实现类PageRequest的静态方法of
                //要排序就增加参数3 Sort.Direction.ASC升  Sort.Direction.DESC降
                .withPageable(PageRequest.of(1,4))
                //排序整体
                //根据字段排序fieldSort("字段名")   .order(SortOrder.ASC/DESC)
                .withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC))
                .build();
        //elasticsearchRestTemplate.search方法参数一,本机查询的构造,参数二index的类,可选参数三再次声明库名(可以多个)
        SearchHits<Item> search = elasticsearchRestTemplate.search(query, Item.class);
        search.forEach(searchHit-> System.out.println(searchHit.getContent()));
    }

    /**
     * 聚合  品牌
     */
    @Test
    void testAgg1(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //聚合可以有多个,所以add
        //terms词条聚合,传入聚合名称   field("聚合字段")   size(结果集大小)
        NativeSearchQuery query = nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand"))
                //结果集过滤  这里设置不需要结果集(不添加包含与不包含,会自动生成长为0数组)
                .withSourceFilter(new FetchSourceFilterBuilder().build())
                .build();
        SearchHits<Item> hits = elasticsearchRestTemplate.search(query, Item.class);
        System.out.println(hits);
        //获取聚合结果集   因为结果为字符串类型 所以用ParsedStringTerms接收   还有ParsedLongTerms接收数字  ParsedDoubleTerms接收小数
        Aggregations aggregations = hits.getAggregations();
        assert aggregations != null;
        ParsedStringTerms brands = aggregations.get("brands");
        //获取桶
        brands.getBuckets().forEach(bucket->{
            //获取桶中的key   与    记录数
            System.out.println(bucket.getKeyAsString()+" "+bucket.getDocCount());
        });
    }

    /**
     * 聚合嵌套  品牌
     */
    @Test
    void testAgg2(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //聚合可以有多个,所以add
        //terms词条聚合,传入聚合名称   field("聚合字段")
        NativeSearchQuery query = nativeSearchQueryBuilder
                .addAggregation(
                        AggregationBuilders
                                .terms("brands")
                                .field("brand")
                                //添加子聚合  subAggregation(添加方式是一样的)  值为桶中品牌均价
                                .subAggregation(AggregationBuilders.avg("price_avg").field("price"))
                )
                //结果集过滤  这里设置不需要结果集(不添加包含与不包含,会自动生成长为0数组)
                .withSourceFilter(new FetchSourceFilterBuilder().build())
                .build();
        SearchHits<Item> hits = elasticsearchRestTemplate.search(query, Item.class);
        System.out.println(hits);
        //获取聚合结果集   因为结果为字符串类型 所以用ParsedStringTerms接收   还有ParsedLongTerms接收数字  ParsedDoubleTerms接收小数
        Aggregations aggregations = hits.getAggregations();
        assert aggregations != null;
        ParsedStringTerms brands = aggregations.get("brands");
        //获取桶brands
        brands.getBuckets().forEach(bucket->{
            //获取桶中的key   与    记录数
            System.out.println(bucket.getKeyAsString()+" "+bucket.getDocCount());

            //获取嵌套的桶price_avg
            ParsedAvg price_avg = bucket.getAggregations().get("price_avg");
            System.out.println(price_avg.getValue());
        });
    }
}
