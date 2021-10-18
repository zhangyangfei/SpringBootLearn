package com.zyf.springbootredisson.listener;

import com.zyf.springbootredisson.constants.TopicConst;
import org.redisson.api.RBucket;
import org.redisson.api.RSet;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Component
public class ToplicListener implements CommandLineRunner {

    @Autowired
    @Qualifier("redissonClinet")
    private RedissonClient redissonClient;

    public void listener() {
        RTopic topic = redissonClient.getTopic(TopicConst.REDISSON_TOPIC);
        topic.addListener(TopicPojo.class, new MessageListener<TopicPojo>() {
            @Override
            public void onMessage(CharSequence charSequence, TopicPojo topic) {
                System.out.println("话题:" + charSequence);
                System.out.println("消息体 = " + topic);
                while(true){
                    // 通过key获取set集合
                    RSet<Object> ids = redissonClient.getSet(topic.getSetKey());
                    if (CollectionUtils.isEmpty(ids))
                        return;
                    RBucket<TopicPojo> rbucket = redissonClient.getBucket(TopicConst.REDISSON_PROCESS_KEY);
                    TopicPojo topicPojo1 = rbucket.get();
                    if( 0 < topicPojo1.getCountFailed())
                        return;
                    // 随机获取n个元素，并从redis移除
                    Set<Object> removeRandom = ids.removeRandom(topic.getCountEve());
                    System.out.println("removeRandom=" + removeRandom);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    if(removeRandom.contains("a300")){
//                        ids.clear();
//                        topicPojo1.setCountFailed(1);
//                        topicPojo1.setErrMsg("a300出错了");
//                        rbucket.set(topicPojo1);
//                        return;
//                    }
                    topicPojo1.setCountSuccess(topicPojo1.getCountSuccess() + removeRandom.size());
                    rbucket.set(topicPojo1);
                }
            }
        });
        System.out.println("开启订阅......");
    }

    @Override
    public void run(String... args) {
        new Thread(this::listener).start();
    }
}
