package com.jk.controller;

import com.jk.dto.*;
import com.jk.service.HomeServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class MogodbController {

    @Autowired
    private HomeServiceFeign serviceFeign;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 评论
     * @param pinglun
     * @param session
     * @return
     */
    @GetMapping("addPinglun")
    public String addPinglun(Pinglun pinglun, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getUserId()!=0) {
            List<Pinglun> userId = queryPinglunUserId(user.getUserId(),pinglun.getSotyId());
            if (userId.size() >=3) {
                return "3";
            }else{

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy- MM-dd HH:mm:ss");
                pinglun.setCreateTime(sdf1.format(new Date()));
                pinglun.setUserId(user.getUserId());
                pinglun.setUserName(user.getUsername());
                /*pinglun.setImg(user.getImg());*/
                 pinglun.setLike(0);
                mongoTemplate.save(pinglun);
                return "2";
            }



        }else{

            return "1";
        }

    }

    /**
     * 根据用户id和文章id查询评论
     * @param userId
     * @param sotyId
     * @return
     */
    @GetMapping("queryPinglunUserId")
    public List<Pinglun> queryPinglunUserId(Integer userId,Integer sotyId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("sotyId").is(sotyId));
        List<Pinglun> list = mongoTemplate.find(query, Pinglun.class);
        return list;
    }

    /**
     * 根据文章id查询评论
     * @param sotyId
     * @return
     */
    @GetMapping("queryPinglun")
    public List<Pinglun> queryPinglun(Integer sotyId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("sotyId").is(sotyId));
        List<Pinglun> list = mongoTemplate.find(query, Pinglun.class);
        return list;
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
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
    public String addCollect(Integer storyId,Integer userId,HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getUserId()!= 0) {

            boolean b = queryCollectOne(storyId,userId);
            if (b) {

                return "2";
            }else{

                Story story = serviceFeign.queryStoryById(storyId);
                Collect collect = new Collect();
                collect.setStoryId(story.getId());
                collect.setClassify("精彩故事");
                collect.setTitle(story.getTitle());
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy- MM-dd HH:mm:ss");
                collect.setCreateTime(sdf1.format(new Date()));
                collect.setUserId(userId);
                mongoTemplate.save(collect);
                return "3";
            }


        }else{

            return "1";
        }

    }

    /**
     * 根据id查询收藏
     * @param storyId
     * @return
     */
    @GetMapping("queryCollectOne")
    public boolean queryCollectOne(Integer storyId,Integer userId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("storyId").is(storyId));
        query.addCriteria(Criteria.where("userId").is(userId));
        Collect collect = mongoTemplate.findOne(query, Collect.class);
        if (collect != null) {
            return true;
        }else{

            return false;
        }
    }



    @GetMapping("queryCollect")
    public List<Collect> queryCollect(Integer userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<Collect> list = mongoTemplate.find(query,Collect.class);
        return list;
    }

    @GetMapping("delteCollect")
    public String delteCollect(String id) {

        Collect collect = new Collect();
        collect.setId(id);
        mongoTemplate.remove(collect);
        return "success";
    }

    @GetMapping("deleteAllCollect")
    public String deleteAllCollect() {
        Query query = new Query();
        mongoTemplate.findAllAndRemove(query, Collect.class);
        return "success";
    }

    @GetMapping("addFouc")
   public String addFouc(Integer myUserId,Integer userId,String username,HttpSession session) {

       User user = (User) session.getAttribute("user");
       if (user.getUserId() != 0) {
           boolean b = myFocus(userId);
           if (b) {
               return "2";
           }else{
               Fouc fouc = new Fouc();
               SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               fouc.setCreateDate(sdf1.format(new Date()));
               fouc.setUserId(userId);
               fouc.setMyUserId(myUserId);
               mongoTemplate.save(fouc);

               return "3";
           }


       }else{

          return "1";
      }
   }

    public boolean myFocus(Integer userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Fouc fouc = mongoTemplate.findOne(query, Fouc.class);
        if (fouc!= null) {
          return true;
        }else{
            return false;
        }

    }

    @GetMapping("queryMyFocus")
    public List<Fouc> queryMyFocus(Integer myUserId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("myUserId").is(myUserId));
        List<Fouc> list = mongoTemplate.find(query, Fouc.class);
        return list;
    }



}
