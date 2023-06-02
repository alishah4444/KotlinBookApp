package com.iaminca.utils;

/**
 * Created by xxxwei on 2019/10/13.
 * @author xxxwei
 */
public class IDUtil {
	/**
	 *
	 * @return
	 */
	public static String getRecordCycleID(){
//		RedisTemplate redisTemplate = GetBeanFactory.getSpringEntry("redisTemplate", RedisTemplate.class);
//		String recordID = RedisKeyUtil.getRecordIdSuffix();
//		final long mod = 10000;
//		Long increment = redisTemplate.opsForHash().increment(recordID, recordID.hashCode(), 1) % mod;
//		return String.valueOf((increment + mod));
		
		long l = System.currentTimeMillis();
		return String.valueOf(l);
	}

}
