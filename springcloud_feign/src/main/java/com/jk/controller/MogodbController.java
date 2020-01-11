package com.jk.controller;

import com.jk.dto.Collect;
import com.jk.dto.Pinglun;
import com.jk.dto.Story;
import com.jk.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
public class MogodbController {


    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("saves")
    public String save() {

        Story story = new Story();
        story.setId("3");
        story.setImg("https://wyhazr.oss-cn-beijing.aliyuncs.com/wyhazr/1577451692393.jpg");
        story.setTitle("威克斯福德为了体验地道的爱尔兰乡村风情，我竟然住进城堡马厩！");
        story.setContent("这套150平方、4房错层空间的民宿，坐落在广州最美的小蛮腰与珠江边。既有着无敌江景，又接邻繁华城区CBD。这里能感受到广州最现代化的气息，而等到夜幕降临，这里的一切都会变得灯光闪烁，如梦似幻，非常迷人。\n" +
                "\n" +
                "从房子布局能看的出房东是个讲究人，餐桌、墙饰大量地运用金属和大理石，整个房子非常华丽，墙面简单明亮，搭配暗色皮质木质家具，整体还是比较稳重大气，有点偏商务风。\n" +
                "\n" +
                "和房东聊的还不错，房东喜欢品酒，花费数年，把爱好变成了自己的事业，如今已是一家白酒公司的老板，而做这套民宿，是房东的爱好，总之很让人羡慕了。用他的原话说：好的民宿源于生活，却高于生活。把对生活的感悟，变成分享和生意的事，早就想做了。");

        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy- MM-dd HH:mm:ss");
        story.setCreateTime(sdf1.format(date));
        story.setGive(0);
        story.setLeave(0);
        mongoTemplate.insert(story);

        return "success";
    }

    @GetMapping("find")
    public List<Story> find() {
        List<Story> list = mongoTemplate.findAll( Story.class);
        return list;
    }

    @GetMapping("queryStoryById")
    public Story queryStoryById(String id) {

        Story story = mongoTemplate.findById(id, Story.class);
        return story;
    }

    @GetMapping("addPinglun")
    public String addPinglun(Pinglun pinglun, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Pinglun> userId = queryPinglunUserId(user.getUserId(),pinglun.getSotyId());
            if (userId.size() >=3) {
                return "3";
            }else{

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy- MM-dd HH:mm:ss");
                pinglun.setCreateTime(sdf1.format(new Date()));
                pinglun.setUserId(user.getUserId());
                pinglun.setUserName(user.getUsername());
                pinglun.setImg(user.getImg());
                 pinglun.setLike(0);
                mongoTemplate.save(pinglun);
                return "2";
            }



        }else{

            return "1";
        }

    }

    @GetMapping("queryPinglunUserId")
    public List<Pinglun> queryPinglunUserId(Integer userId,Integer sotyId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("sotyId").is(sotyId));
        List<Pinglun> list = mongoTemplate.find(query, Pinglun.class);
        return list;
    }

    @GetMapping("queryPinglun")
    public List<Pinglun> queryPinglun(Integer sotyId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("sotyId").is(sotyId));
        List<Pinglun> list = mongoTemplate.find(query, Pinglun.class);
        return list;
    }

    @GetMapping("deletePinglun")
    public String deletePinglun(String id) {

        Pinglun pinglun = new Pinglun();
        pinglun.setId(id);
        mongoTemplate.remove(pinglun);
        return "success";
    }

    /**
     * 新增收藏
     * @param StoryId
     * @return
     */
    @GetMapping("addCollect")
    public String addCollect(String storyId,HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {

            boolean b = queryCollectOne(storyId);
            if (b) {
                return "2";
            }else{

                Story story = queryStoryById(storyId);
                Collect collect = new Collect();
                collect.setStoryId(story.getId());
                collect.setClassify("精彩故事");
                collect.setTitle(story.getTitle());
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy- MM-dd HH:mm:ss");
                collect.setCreateTime(sdf1.format(new Date()));
                mongoTemplate.save(collect);
                return "3";
            }


        }else{

            return "1";
        }

    }

    @GetMapping("queryCollectOne")
    public boolean queryCollectOne(String storyId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("storyId").is(storyId));
        Collect collect = mongoTemplate.findOne(query, Collect.class);
        if (collect != null) {
            return true;
        }else{

            return false;
        }
    }



}
