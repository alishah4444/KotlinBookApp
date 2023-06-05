package com.iaminca.wordpress.tasks;

import com.iaminca.common.Constants;
import com.iaminca.utils.DateUtil;
import com.iaminca.utils.HttpUtil;
import com.iaminca.wordpress.beans.WordpressTaskBean;
import com.iaminca.wordpress.handlers.PushPostHandler;
import com.iaminca.wordpress.push.PushPosts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ScheduleTask {
    @Resource
    private PushPosts pushPosts;

    @Resource
    private PushPostHandler pushPostHandler;

    private final String content = "<!-- wp:paragraph --><p>海南夏天的旅游攻略</p><!-- /wp:paragraph --><!-- wp:paragraph --><p>想要度过一个难忘的夏天假期吗？海南是您的理想选择！</p><!-- /wp:paragraph --><!-- wp:paragraph --><p>以下是一些海南夏天旅游的攻略：</p><!-- /wp:paragraph --><!-- wp:list{\\\"ordered\\\":true}--><ol><!--wp:list-item--><li>畅游美丽的海滩。海南拥有绵延数公里的金色沙滩，您可以享受阳光、沙滩和海浪的乐趣。</li><!-- /wp:list-item --><!-- wp:list-item --><li>品尝当地美食。海南菜以其独特的口味和新鲜的海鲜而闻名，不要错过尝试当地特色菜肴。</li><!-- /wp:list-item --><!-- wp:list-item --><li>探索自然风光。海南有许多自然保护区和风景名胜，如呀诺达雨林和西岛，可以让您近距离欣赏大自然的美丽。</li><!-- /wp:list-item --></ol><!-- /wp:list --><!-- wp:paragraph --><p>无论是家庭度假还是独自旅行，海南夏天都能为您提供难忘的经历和宝贵的回忆。快来探索这个美丽的热带岛屿吧！</p><!-- /wp:paragraph -->";
    private final String URL = "https://wp.martian.tk/wp-json/wp/v2/posts";
    private final String title = "2024海南夏天的旅游攻略"+DateUtil.formDate(new Date(),DateUtil.DATETIME_FORMAT_PATTERN);
    private final String authUsername ="martian";
    private final String authPassword ="kLyaeEqSU7ZkDgdbCUjh";
    @Scheduled(fixedRate = 1000 * 60 * 20)
    public void scheduledTask() {
        log.info("任务执行时间：" + LocalDateTime.now());
        int[] categories = new int[]{5, 7};
        pushPosts.executePush(URL,authUsername,authPassword,title,content,categories);
        pushPostHandler.hookExecute(1L);
        log.info("任务执行结束时间：" + LocalDateTime.now());
    }

}
