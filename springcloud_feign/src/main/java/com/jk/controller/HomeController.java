package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.jk.dto.Content;
import com.jk.dto.Order;
import com.jk.dto.User;
import com.jk.repository.HomeRepository;
import com.jk.service.HomeServiceFeign;
import com.jk.utils.CheckImgUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
public class HomeController {

    @Autowired
    private HomeServiceFeign serviceFeign;


    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Resource
    AmqpTemplate amqpTemplate;

    @GetMapping("queryContents")
    public List<Content> queryContents() {

        List<Content> list = serviceFeign.queryContent();
        return list;
    }

    @GetMapping("save")
    public String save() {

        Content content = new Content();
        content.setId(3);
        content.setContent("不租不租就不租");
        content.setCuprice(130.20);
        homeRepository.save(content);
        return "success";

    }

    @GetMapping("delete")
    public String delete(Integer id) {


        homeRepository.deleteById(id);
        return "success";
    }
    /**
     * setSearchType  四种类型
     *      *
     *      *      1. QUERY_AND_FETCH      搜索最快的  因为它只访问依次 shards   但是搜索结果不精确
     *      *      2. QUERY_THEN_FETCH
     *      *      3. DFS_QUERY_AND_FETCH
     *      *      4. DFS_QUERY_THEN_FETCH 最慢的  因为它可能会访问三次 shards 但是它是 搜索结果最精确的
     * @param content
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("search")
    public List<Content> search(Content content, Integer page, Integer rows) {

        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 10;
        }
        List<Content> list = new ArrayList<>();
        BoolQueryBuilder builder = new BoolQueryBuilder();
        if (content != null) {
            if (content.getContent() != null && !"".equals(content.getContent())) {
                builder.must(QueryBuilders.matchQuery("content", content.getContent()));
            }
        }

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("content");
        //设置前缀
        highlightBuilder.preTags("<span style='color:red'>");
        //设置后缀
        highlightBuilder.postTags("</span>");

        // 创建 Es  客户端
        Client client = template.getClient();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("home")
                .addSort("cuprice", SortOrder.DESC)//倒叙
                .setTypes("action")//
                .highlighter(highlightBuilder)//设置高亮策略
                .setQuery(builder)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)//设置查询方式
                .setExplain(true)//是否根据相关度进行排序
                .setFrom((page - 1) * rows)//起始条数
                .setSize(rows);//每页条数
                 SearchResponse searchResponse = searchRequestBuilder.get();//获取响应体
                SearchHits hits = searchResponse.getHits();//获取响应体中的数据
                long total =  hits.totalHits;
                Iterator<SearchHit> iterator = hits.iterator();
                while (iterator.hasNext()) {
                    SearchHit next = iterator.next();

                    Map<String, Object> sourceAsMap = next.getSourceAsMap();
                    //获取高亮
                    Map<String, HighlightField> highlightFields = next.getHighlightFields();

                    Content content1 = new Content();

                    content1.setId((Integer) sourceAsMap.get("id"));
                    content1.setImg((String) sourceAsMap.get("img"));
                    content1.setCuprice(Double.parseDouble(sourceAsMap.get("cuprice").toString()));
                    content1.setOrprice(Double.parseDouble(sourceAsMap.get("orprice").toString()));

                    if (highlightFields.get("content")!=null)
                        content1.setContent(highlightFields.get("content").fragments()[0].toString());
                    else
                        content1.setContent(sourceAsMap.get("content").toString());

                    list.add(content1);
                }

        return list;

    }

    @GetMapping("logins")
    public Map<String, String> login(String imgcode,User user, HttpSession session) {

        Map<String, String> map = new HashMap<>();
        String sessionImgCode = (String) session.getAttribute("imgcode");
        if (!imgcode.equalsIgnoreCase(sessionImgCode)) {
            map.put("code", "0");
            map.put("msg", "验证码错误");
            return map;
        }
        User user1=serviceFeign.login(user);
        if (user1 == null) {

            map.put("code", "1");
            map.put("msg", "用户名错误");
            return map;

        }
        if (!user.getUserpassword().equals(user1.getUserpassword())) {
            map.put("code", "2");
            map.put("msg", "密码错误");
            return map;
        }
        session.setAttribute("user",user1);
        map.put("code", "3");
        map.put("msg", "登陆成功");
        return map;

    }

    /**
     * 获取验证码
     * @throws IOException
     */
    @RequestMapping("getCodeName")
    private void getCodeName(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CheckImgUtil.checkImg(request, response);
    }

    @PostMapping("registers")
    public String registers(User user) {
     /* if (user.getUsername() != "") {
          Order order = new Order();
          order.setOrderContent("恭喜"+user.getUsername()+"注册成功");
          order.setEmail(user.getEmail());
          //将对象转换为json串
          String orderJson = JSON.toJSONString(order);
          // 往消息队列推送消息
            amqpTemplate.convertAndSend("1906-wyh", orderJson);
            System.out.println("消息已发送");

        }*/
        serviceFeign.register(user);
        return "success";
    }




}
