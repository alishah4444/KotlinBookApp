package com.iaminca.config;

import com.iaminca.utils.GetBeanFactory;
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
