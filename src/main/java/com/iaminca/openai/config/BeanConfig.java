package com.iaminca.openai.config;

import com.iaminca.openai.utils.GetBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by xxxwei on 2019/10/1.
 */
@Service
public class BeanConfig {

	@Bean
	public GetBeanFactory getBeanFactory(){
		return new GetBeanFactory();
	}

}
