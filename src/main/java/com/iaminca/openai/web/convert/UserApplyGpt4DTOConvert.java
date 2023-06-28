package com.iaminca.openai.web.convert;


import com.google.common.collect.Lists;
import com.iaminca.openai.service.bo.UserApplyGpt4BO;
import com.iaminca.openai.web.dto.UserApplyGpt4DTO;
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
public class UserApplyGpt4DTOConvert {

	public static UserApplyGpt4BO toBO(UserApplyGpt4DTO userApplyGpt4DTO) {
		if (userApplyGpt4DTO == null) {
			return null;
		}
		UserApplyGpt4BO userApplyGpt4BO = new UserApplyGpt4BO();
		userApplyGpt4BO.setId(userApplyGpt4DTO.getId());
		userApplyGpt4BO.setUserId(userApplyGpt4DTO.getUserId());
		userApplyGpt4BO.setUserName(userApplyGpt4DTO.getUserName());
		userApplyGpt4BO.setUserEmail(userApplyGpt4DTO.getUserEmail());
		userApplyGpt4BO.setCompanyName(userApplyGpt4DTO.getCompanyName());
		userApplyGpt4BO.setApplyReason(userApplyGpt4DTO.getApplyReason());
		userApplyGpt4BO.setIdeaDetails(userApplyGpt4DTO.getIdeaDetails());
		userApplyGpt4BO.setDelFlag(userApplyGpt4DTO.getDelFlag());
		userApplyGpt4BO.setCreateTime(userApplyGpt4DTO.getCreateTime());
		userApplyGpt4BO.setUpdateTime(userApplyGpt4DTO.getUpdateTime());
		return userApplyGpt4BO;
	}

	public static UserApplyGpt4DTO toDTO(UserApplyGpt4BO userApplyGpt4BO) {
		if (userApplyGpt4BO == null) {
			return null;
		}
		UserApplyGpt4DTO userApplyGpt4DTO = new UserApplyGpt4DTO();
		userApplyGpt4DTO.setId(userApplyGpt4BO.getId());
		userApplyGpt4DTO.setUserId(userApplyGpt4BO.getUserId());
		userApplyGpt4DTO.setUserName(userApplyGpt4BO.getUserName());
		userApplyGpt4DTO.setUserEmail(userApplyGpt4BO.getUserEmail());
		userApplyGpt4DTO.setCompanyName(userApplyGpt4BO.getCompanyName());
		userApplyGpt4DTO.setApplyReason(userApplyGpt4BO.getApplyReason());
		userApplyGpt4DTO.setIdeaDetails(userApplyGpt4BO.getIdeaDetails());
		userApplyGpt4DTO.setDelFlag(userApplyGpt4BO.getDelFlag());
		userApplyGpt4DTO.setCreateTime(userApplyGpt4BO.getCreateTime());
		userApplyGpt4DTO.setUpdateTime(userApplyGpt4BO.getUpdateTime());
		return userApplyGpt4DTO;
	}

	public static List<UserApplyGpt4BO> toBOList(List<UserApplyGpt4DTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
			return Collections.emptyList();
		}

		List<UserApplyGpt4BO> boList = Lists.newArrayList();
		for (UserApplyGpt4DTO userApplyGpt4DTO : dtoList) {
			if (userApplyGpt4DTO != null) {
				boList.add(toBO(userApplyGpt4DTO));
			}
		}
		return boList;
	}

	public static List<UserApplyGpt4DTO> toDTOList(List<UserApplyGpt4BO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
			return Collections.emptyList();
		}

		List<UserApplyGpt4DTO> dtoList = Lists.newArrayList();
		for (UserApplyGpt4BO userApplyGpt4BO : boList) {
			if (userApplyGpt4BO != null) {
				dtoList.add(toDTO(userApplyGpt4BO));
			}
		}
		return dtoList;
	}

}
