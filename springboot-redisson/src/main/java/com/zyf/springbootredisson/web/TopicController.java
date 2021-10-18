package com.zyf.springbootredisson.web;

import com.zyf.springbootredisson.constants.TopicConst;
import com.zyf.springbootredisson.listener.TopicPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.redisson.api.RBucket;
import org.redisson.api.RSet;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("topic")
@Api(value = "发布订阅")
public class TopicController {

    @Autowired
    @Qualifier("redissonClinet")
    private RedissonClient redissonClient;

    @GetMapping("pub")
    @ApiOperation(value = "发布", tags = "发布订阅")
    public Map<Object, Object> pub(String setKet, int countAll, int countEve) {

        // 添加元素
        RSet<Object> ids = redissonClient.getSet(setKet);
        int tempc = 1;
        while (tempc <= countAll) {
            ids.add("a" + tempc);
            tempc++;
        }

        RBucket<TopicPojo> rbucket = redissonClient.getBucket(TopicConst.REDISSON_PROCESS_KEY);
        //删除对象的缓存值
        if(rbucket.isExists()){
            rbucket.delete();
        }
        rbucket.set(new TopicPojo(TopicConst.REDISSON_PROCESS_KEY, 1000, 10, 0, 0, ""));
        TopicPojo topicPojo1 = rbucket.get();
        System.out.println(topicPojo1);

        Map<Object, Object> map = new HashMap<>();
        RTopic topic = redissonClient.getTopic(TopicConst.REDISSON_TOPIC);
        TopicPojo topicPojo = new TopicPojo(setKet, countEve);
        topic.publish(topicPojo);
        map.put("message", "已发布：" + TopicConst.REDISSON_TOPIC);
        map.put("content", "已发布：" + topicPojo);


        return map;
    }


}
