package com.iaminca.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.UserRechargeDO;
import com.iaminca.service.bo.UserRechargeBO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-16 11:06:12
 */
public class UserRechargeConvert {

	public static UserRechargeBO toBO(UserRechargeDO userRechargeDO) {
		if (userRechargeDO == null) {
			return null;
		}
		UserRechargeBO userRechargeBO = new UserRechargeBO();
		userRechargeBO.setId(userRechargeDO.getId());
		userRechargeBO.setUserId(userRechargeDO.getUserId());
		userRechargeBO.setRechargeMoney(userRechargeDO.getRechargeMoney());
		userRechargeBO.setFilePath(userRechargeDO.getFilePath());
		userRechargeBO.setRechargeStatus(userRechargeDO.getRechargeStatus());
		userRechargeBO.setDelFlag(userRechargeDO.getDelFlag());
		userRechargeBO.setCreateTime(userRechargeDO.getCreateTime());
		userRechargeBO.setUpdateTime(userRechargeDO.getUpdateTime());
		return userRechargeBO;
	}

	public static UserRechargeDO toDO(UserRechargeBO userRechargeBO) {
		if (userRechargeBO == null) {
			return null;
		}
		UserRechargeDO userRechargeDO = new UserRechargeDO();
		userRechargeDO.setId(userRechargeBO.getId());
		userRechargeDO.setUserId(userRechargeBO.getUserId());
		userRechargeDO.setRechargeMoney(userRechargeBO.getRechargeMoney());
		userRechargeDO.setFilePath(userRechargeBO.getFilePath());
		userRechargeDO.setRechargeStatus(userRechargeBO.getRechargeStatus());
		userRechargeDO.setDelFlag(userRechargeBO.getDelFlag());
		userRechargeDO.setCreateTime(userRechargeBO.getCreateTime());
		userRechargeDO.setUpdateTime(userRechargeBO.getUpdateTime());
		return userRechargeDO;
	}

	public static List<UserRechargeBO> toBOList(List<UserRechargeDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserRechargeBO> boList = Lists.newArrayList();
		for (UserRechargeDO userRechargeDO : doList) {
			if (userRechargeDO != null) {
				boList.add(toBO(userRechargeDO));
			}
		}
		return boList;
	}

	public static List<UserRechargeDO> toDOList(List<UserRechargeBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<UserRechargeDO> doList = Lists.newArrayList();
		for (UserRechargeBO userRechargeBO : boList) {
			if (userRechargeBO != null) {
				doList.add(toDO(userRechargeBO));
			}
		}
		return doList;
	}

}
