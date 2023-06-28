package com.iaminca.openai.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.openai.dal.dataobject.UserApplyGpt4DO;
import com.iaminca.openai.service.bo.UserApplyGpt4BO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-16 18:59:03
 */
public class UserApplyGpt4Convert {

	public static UserApplyGpt4BO toBO(UserApplyGpt4DO userApplyGpt4DO) {
		if (userApplyGpt4DO == null) {
			return null;
		}
		UserApplyGpt4BO userApplyGpt4BO = new UserApplyGpt4BO();
		userApplyGpt4BO.setId(userApplyGpt4DO.getId());
		userApplyGpt4BO.setUserId(userApplyGpt4DO.getUserId());
		userApplyGpt4BO.setUserName(userApplyGpt4DO.getUserName());
		userApplyGpt4BO.setUserEmail(userApplyGpt4DO.getUserEmail());
		userApplyGpt4BO.setCompanyName(userApplyGpt4DO.getCompanyName());
		userApplyGpt4BO.setApplyReason(userApplyGpt4DO.getApplyReason());
		userApplyGpt4BO.setIdeaDetails(userApplyGpt4DO.getIdeaDetails());
		userApplyGpt4BO.setDelFlag(userApplyGpt4DO.getDelFlag());
		userApplyGpt4BO.setCreateTime(userApplyGpt4DO.getCreateTime());
		userApplyGpt4BO.setUpdateTime(userApplyGpt4DO.getUpdateTime());
		return userApplyGpt4BO;
	}

	public static UserApplyGpt4DO toDO(UserApplyGpt4BO userApplyGpt4BO) {
		if (userApplyGpt4BO == null) {
			return null;
		}
		UserApplyGpt4DO userApplyGpt4DO = new UserApplyGpt4DO();
		userApplyGpt4DO.setId(userApplyGpt4BO.getId());
		userApplyGpt4DO.setUserId(userApplyGpt4BO.getUserId());
		userApplyGpt4DO.setUserName(userApplyGpt4BO.getUserName());
		userApplyGpt4DO.setUserEmail(userApplyGpt4BO.getUserEmail());
		userApplyGpt4DO.setCompanyName(userApplyGpt4BO.getCompanyName());
		userApplyGpt4DO.setApplyReason(userApplyGpt4BO.getApplyReason());
		userApplyGpt4DO.setIdeaDetails(userApplyGpt4BO.getIdeaDetails());
		userApplyGpt4DO.setDelFlag(userApplyGpt4BO.getDelFlag());
		userApplyGpt4DO.setCreateTime(userApplyGpt4BO.getCreateTime());
		userApplyGpt4DO.setUpdateTime(userApplyGpt4BO.getUpdateTime());
		return userApplyGpt4DO;
	}

	public static List<UserApplyGpt4BO> toBOList(List<UserApplyGpt4DO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserApplyGpt4BO> boList = Lists.newArrayList();
		for (UserApplyGpt4DO userApplyGpt4DO : doList) {
			if (userApplyGpt4DO != null) {
				boList.add(toBO(userApplyGpt4DO));
			}
		}
		return boList;
	}

	public static List<UserApplyGpt4DO> toDOList(List<UserApplyGpt4BO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<UserApplyGpt4DO> doList = Lists.newArrayList();
		for (UserApplyGpt4BO userApplyGpt4BO : boList) {
			if (userApplyGpt4BO != null) {
				doList.add(toDO(userApplyGpt4BO));
			}
		}
		return doList;
	}

}
