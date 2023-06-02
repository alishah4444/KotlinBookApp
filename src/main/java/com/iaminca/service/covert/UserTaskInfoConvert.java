package com.iaminca.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.UserTaskInfoDO;
import com.iaminca.service.bo.UserTaskInfoBO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-06-02 01:03:34
 */
public class UserTaskInfoConvert {

	public static UserTaskInfoBO toBO(UserTaskInfoDO userTaskInfoDO) {
		if (userTaskInfoDO == null) {
			return null;
		}
		UserTaskInfoBO userTaskInfoBO = new UserTaskInfoBO();
		userTaskInfoBO.setId(userTaskInfoDO.getId());
		userTaskInfoBO.setUserId(userTaskInfoDO.getUserId());
		userTaskInfoBO.setApiKeyId(userTaskInfoDO.getApiKeyId());
		userTaskInfoBO.setCron(userTaskInfoDO.getCron());
		userTaskInfoBO.setPushUrl(userTaskInfoDO.getPushUrl());
		userTaskInfoBO.setAuthUsername(userTaskInfoDO.getAuthUsername());
		userTaskInfoBO.setAuthPassword(userTaskInfoDO.getAuthPassword());
		userTaskInfoBO.setMaxLength(userTaskInfoDO.getMaxLength());
		userTaskInfoBO.setCompletionTemplate(userTaskInfoDO.getCompletionTemplate());
		userTaskInfoBO.setDelFlag(userTaskInfoDO.getDelFlag());
		userTaskInfoBO.setCreateTime(userTaskInfoDO.getCreateTime());
		userTaskInfoBO.setUpdateTime(userTaskInfoDO.getUpdateTime());
		return userTaskInfoBO;
	}

	public static UserTaskInfoDO toDO(UserTaskInfoBO userTaskInfoBO) {
		if (userTaskInfoBO == null) {
			return null;
		}
		UserTaskInfoDO userTaskInfoDO = new UserTaskInfoDO();
		userTaskInfoDO.setId(userTaskInfoBO.getId());
		userTaskInfoDO.setUserId(userTaskInfoBO.getUserId());
		userTaskInfoDO.setApiKeyId(userTaskInfoBO.getApiKeyId());
		userTaskInfoDO.setCron(userTaskInfoBO.getCron());
		userTaskInfoDO.setPushUrl(userTaskInfoBO.getPushUrl());
		userTaskInfoDO.setAuthUsername(userTaskInfoBO.getAuthUsername());
		userTaskInfoDO.setAuthPassword(userTaskInfoBO.getAuthPassword());
		userTaskInfoDO.setMaxLength(userTaskInfoBO.getMaxLength());
		userTaskInfoDO.setCompletionTemplate(userTaskInfoBO.getCompletionTemplate());
		userTaskInfoDO.setDelFlag(userTaskInfoBO.getDelFlag());
		userTaskInfoDO.setCreateTime(userTaskInfoBO.getCreateTime());
		userTaskInfoDO.setUpdateTime(userTaskInfoBO.getUpdateTime());
		return userTaskInfoDO;
	}

	public static List<UserTaskInfoBO> toBOList(List<UserTaskInfoDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserTaskInfoBO> boList = Lists.newArrayList();
		for (UserTaskInfoDO userTaskInfoDO : doList) {
			if (userTaskInfoDO != null) {
				boList.add(toBO(userTaskInfoDO));
			}
		}
		return boList;
	}

	public static List<UserTaskInfoDO> toDOList(List<UserTaskInfoBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<UserTaskInfoDO> doList = Lists.newArrayList();
		for (UserTaskInfoBO userTaskInfoBO : boList) {
			if (userTaskInfoBO != null) {
				doList.add(toDO(userTaskInfoBO));
			}
		}
		return doList;
	}

}
