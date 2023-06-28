package com.iaminca.openai.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.openai.dal.dataobject.UserBalanceDO;
import com.iaminca.openai.service.bo.UserBalanceBO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-09 16:08:49
 */
public class UserBalanceConvert {

	public static UserBalanceBO toBO(UserBalanceDO userBalanceDO) {
		if (userBalanceDO == null) {
			return null;
		}
		UserBalanceBO userBalanceBO = new UserBalanceBO();
		userBalanceBO.setId(userBalanceDO.getId());
		userBalanceBO.setUserId(userBalanceDO.getUserId());
		userBalanceBO.setUserBalance(userBalanceDO.getUserBalance());
		userBalanceBO.setDelFlag(userBalanceDO.getDelFlag());
		userBalanceBO.setCreateTime(userBalanceDO.getCreateTime());
		userBalanceBO.setUpdateTime(userBalanceDO.getUpdateTime());
		return userBalanceBO;
	}

	public static UserBalanceDO toDO(UserBalanceBO userBalanceBO) {
		if (userBalanceBO == null) {
			return null;
		}
		UserBalanceDO userBalanceDO = new UserBalanceDO();
		userBalanceDO.setId(userBalanceBO.getId());
		userBalanceDO.setUserId(userBalanceBO.getUserId());
		userBalanceDO.setUserBalance(userBalanceBO.getUserBalance());
		userBalanceDO.setDelFlag(userBalanceBO.getDelFlag());
		userBalanceDO.setCreateTime(userBalanceBO.getCreateTime());
		userBalanceDO.setUpdateTime(userBalanceBO.getUpdateTime());
		return userBalanceDO;
	}

	public static List<UserBalanceBO> toBOList(List<UserBalanceDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserBalanceBO> boList = Lists.newArrayList();
		for (UserBalanceDO userBalanceDO : doList) {
			if (userBalanceDO != null) {
				boList.add(toBO(userBalanceDO));
			}
		}
		return boList;
	}

	public static List<UserBalanceDO> toDOList(List<UserBalanceBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<UserBalanceDO> doList = Lists.newArrayList();
		for (UserBalanceBO userBalanceBO : boList) {
			if (userBalanceBO != null) {
				doList.add(toDO(userBalanceBO));
			}
		}
		return doList;
	}

}
