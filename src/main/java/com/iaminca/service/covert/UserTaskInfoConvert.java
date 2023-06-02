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
		userTaskInfoBO.setUserKeywordsId(userTaskInfoDO.getUserKeywordsId());
		userTaskInfoBO.setTaskStatus(userTaskInfoDO.getTaskStatus());
		userTaskInfoBO.setProcessNumber(userTaskInfoDO.getProcessNumber());
		userTaskInfoBO.setCron(userTaskInfoDO.getCron());
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
		userTaskInfoDO.setUserKeywordsId(userTaskInfoBO.getUserKeywordsId());
		userTaskInfoDO.setTaskStatus(userTaskInfoBO.getTaskStatus());
		userTaskInfoDO.setProcessNumber(userTaskInfoBO.getProcessNumber());
		userTaskInfoDO.setCron(userTaskInfoBO.getCron());
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
