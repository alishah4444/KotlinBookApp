package com.iaminca.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.UserKeyDO;
import com.iaminca.service.bo.UserKeyBO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-04 17:34:01
 */
public class UserKeyConvert {

	public static UserKeyBO toBO(UserKeyDO userKeyDO) {
		if (userKeyDO == null) {
			return null;
		}
		UserKeyBO userKeyBO = new UserKeyBO();
		userKeyBO.setId(userKeyDO.getId());
		userKeyBO.setUserId(userKeyDO.getUserId());
		userKeyBO.setUserKey(userKeyDO.getUserKey());
		userKeyBO.setUserChatLimitation(userKeyDO.getUserChatLimitation());
		userKeyBO.setUserLengthLimitation(userKeyDO.getUserLengthLimitation());
		userKeyBO.setDelFlag(userKeyDO.getDelFlag());
		userKeyBO.setCreateTime(userKeyDO.getCreateTime());
		userKeyBO.setUpdateTime(userKeyDO.getUpdateTime());
		userKeyBO.setName(userKeyDO.getName());
		return userKeyBO;
	}

	public static UserKeyDO toDO(UserKeyBO userKeyBO) {
		if (userKeyBO == null) {
			return null;
		}
		UserKeyDO userKeyDO = new UserKeyDO();
		userKeyDO.setId(userKeyBO.getId());
		userKeyDO.setUserId(userKeyBO.getUserId());
		userKeyDO.setUserKey(userKeyBO.getUserKey());
		userKeyDO.setUserChatLimitation(userKeyBO.getUserChatLimitation());
		userKeyDO.setUserLengthLimitation(userKeyBO.getUserLengthLimitation());
		userKeyDO.setDelFlag(userKeyBO.getDelFlag());
		userKeyDO.setCreateTime(userKeyBO.getCreateTime());
		userKeyDO.setUpdateTime(userKeyBO.getUpdateTime());
		userKeyDO.setName(userKeyBO.getName());
		return userKeyDO;
	}

	public static List<UserKeyBO> toBOList(List<UserKeyDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserKeyBO> boList = Lists.newArrayList();
		for (UserKeyDO userKeyDO : doList) {
			if (userKeyDO != null) {
				boList.add(toBO(userKeyDO));
			}
		}
		return boList;
	}

	public static List<UserKeyDO> toDOList(List<UserKeyBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<UserKeyDO> doList = Lists.newArrayList();
		for (UserKeyBO userKeyBO : boList) {
			if (userKeyBO != null) {
				doList.add(toDO(userKeyBO));
			}
		}
		return doList;
	}

}
