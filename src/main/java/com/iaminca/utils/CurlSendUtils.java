package com.iaminca.utils;

import com.afrozaar.wordpress.wpapi.v2.Wordpress;
import com.afrozaar.wordpress.wpapi.v2.api.Contexts;
import com.afrozaar.wordpress.wpapi.v2.config.ClientConfig;
import com.afrozaar.wordpress.wpapi.v2.config.ClientFactory;
import com.afrozaar.wordpress.wpapi.v2.exception.PostCreateException;
import com.afrozaar.wordpress.wpapi.v2.exception.PostNotFoundException;
import com.afrozaar.wordpress.wpapi.v2.model.Post;
import com.afrozaar.wordpress.wpapi.v2.model.PostStatus;
import com.afrozaar.wordpress.wpapi.v2.model.builder.ContentBuilder;
import com.afrozaar.wordpress.wpapi.v2.model.builder.ExcerptBuilder;
import com.afrozaar.wordpress.wpapi.v2.model.builder.PostBuilder;
import com.afrozaar.wordpress.wpapi.v2.model.builder.TitleBuilder;
import com.iaminca.common.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

@Slf4j
public class CurlSendUtils {

    public static String execCurl(String[] cmds){
        log.info("execCurl", Constants.GSON.toJson(cmds));
        ProcessBuilder processBuilder = new ProcessBuilder(cmds);
        Process p;
        try{
            p = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine())!=null){
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        }catch (Exception e){
            log.error("execCurl Exception", Constants.GSON.toJson(e));
        }
        return null;
    }

    private static void sendPost(){
        Post fetched = new Post();
        try {
            Wordpress wordpress = ClientFactory.fromConfig(ClientConfig.of("http://docker.dev", "docker", "docker!", true, true));

            final Post post = wordpress.createPost(PostBuilder.aPost()
                    .withTitle(TitleBuilder.aTitle().withRendered("Some Title").build())
                    .withExcerpt(ExcerptBuilder.anExcerpt().withRendered("Raw Excerpt").build())
                    .withContent(ContentBuilder.aContent(   ).withRendered("[gallery ids=\"10\"]").build())
                    .build(), PostStatus.publish);


            fetched = wordpress.getPost(post.getId(), Contexts.EDIT);
        } catch (PostNotFoundException | PostCreateException e) {
            e.printStackTrace();
        }

        System.out.println("raw = " + fetched.getContent().getRaw());
    }

    public static void createPosts(){
        try {
            String baseUrl = "https://wp.martian.tk/wordpress/wp-json/wp/v2/posts";
            String username = "myUsename";
            String password = "myPassword";
            boolean debug = false;
            boolean usePermalinkEndpoint = true;

            final Wordpress client = ClientFactory.fromConfig(ClientConfig.of(baseUrl, username, password,usePermalinkEndpoint, debug));
            final Post post = PostBuilder.aPost()
                    .withTitle(TitleBuilder.aTitle().withRendered("expectedTitle").build())
                    .withExcerpt(ExcerptBuilder.anExcerpt().withRendered("expectedExcerpt").build())
                    .withContent(ContentBuilder.aContent().withRendered("expectedContent").build())
                    .build();
            final Post createdPost = client.createPost(post, PostStatus.publish);
            log.info(Constants.GSON.toJson(createdPost));
        } catch (PostCreateException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Date().toString());
    }
}
