package com.iaminca.openai.web.convert;

import com.google.common.collect.Lists;
import com.iaminca.openai.utils.MoneyUtil;
import com.iaminca.openai.service.bo.UserRechargeBO;
import com.iaminca.openai.web.dto.UserRechargeDTO;
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

	public static UserRechargeBO toBO(UserRechargeDTO userRechargeDTO) {
		if (userRechargeDTO == null) {
			return null;
		}
		UserRechargeBO userRechargeBO = new UserRechargeBO();
		userRechargeBO.setId(userRechargeDTO.getId());
		userRechargeBO.setUserId(userRechargeDTO.getUserId());
		userRechargeBO.setRechargeMoney(MoneyUtil.getCentUniteMoney(userRechargeDTO.getRechargeMoney()));
		userRechargeBO.setDelFlag(userRechargeDTO.getDelFlag());
		userRechargeBO.setCreateTime(userRechargeDTO.getCreateTime());
		userRechargeBO.setUpdateTime(userRechargeDTO.getUpdateTime());
		userRechargeBO.setFilePath(userRechargeDTO.getFilePath());
		return userRechargeBO;
	}

	public static UserRechargeDTO toDTO(UserRechargeBO userRechargeBO) {
		if (userRechargeBO == null) {
			return null;
		}
		UserRechargeDTO userRechargeDTO = new UserRechargeDTO();
		userRechargeDTO.setId(userRechargeBO.getId());
		userRechargeDTO.setUserId(userRechargeBO.getUserId());

		userRechargeDTO.setRechargeMoney(MoneyUtil.getYuanUniteMoney(userRechargeBO.getRechargeMoney()));
		userRechargeDTO.setFilePath(userRechargeBO.getFilePath());
		userRechargeDTO.setRechargeStatus(userRechargeBO.getRechargeStatus());
		userRechargeDTO.setDelFlag(userRechargeBO.getDelFlag());
		userRechargeDTO.setCreateTime(userRechargeBO.getCreateTime());
		userRechargeDTO.setUpdateTime(userRechargeBO.getUpdateTime());
		return userRechargeDTO;
	}

	public static List<UserRechargeBO> toBOList(List<UserRechargeDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
			return Collections.emptyList();
		}

		List<UserRechargeBO> boList = Lists.newArrayList();
		for (UserRechargeDTO userRechargeDTO : dtoList) {
			if (userRechargeDTO != null) {
				boList.add(toBO(userRechargeDTO));
			}
		}
		return boList;
	}

	public static List<UserRechargeDTO> toDTOList(List<UserRechargeBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
			return Collections.emptyList();
		}

		List<UserRechargeDTO> dtoList = Lists.newArrayList();
		for (UserRechargeBO userRechargeBO : boList) {
			if (userRechargeBO != null) {
				dtoList.add(toDTO(userRechargeBO));
			}
		}
		return dtoList;
	}

}
