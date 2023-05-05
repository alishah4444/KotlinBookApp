package com.iaminca.config;

import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(OpenAiProperties.class)
public class OpenAiConfiguration {

    @Bean
    public OpenAiApi openAiApi(OpenAiClient openAiClient, OpenAiProperties properties) {
        return new Retrofit.Builder()
                .baseUrl(properties.getBaseUrl())
                .client(openAiClient.get())
                .addConverterFactory(JacksonConverterFactory.create(OpenAiService.defaultObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(OpenAiApi.class);
    }

    @Bean(destroyMethod = "shutdownExecutor")
    public OpenAiService openAiService(OpenAiApi openAiApi, OpenAiClient openAiClient) {
        return new OpenAiService(openAiApi, openAiClient.get().dispatcher().executorService());
    }

    @Component
    static class OpenAiClient {
        private final OkHttpClient value;

        OpenAiClient(OpenAiProperties properties) {
            String authorization = "Bearer " + Objects.requireNonNull(properties.getToken(), "OpenAI token required");
            OpenAiProperties.Pool pool = properties.getPool();
            this.value = new OkHttpClient.Builder()
                    .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().header("Authorization", authorization).build()))
                    .connectionPool(new ConnectionPool(pool.getMaxIdleConnections(), pool.getKeepAlive().toNanos(), TimeUnit.NANOSECONDS))
                    .followRedirects(properties.isFollowRedirects())
                    .callTimeout(properties.getCallTimeout())
                    .connectTimeout(properties.getConnectTimeout())
                    .readTimeout(properties.getReadTimeout())
                    .writeTimeout(properties.getWriteTimeout())
                    .pingInterval(properties.getPingInterval())
                    .build();
        }

        public OkHttpClient get() {
            return value;
        }
    }
}
