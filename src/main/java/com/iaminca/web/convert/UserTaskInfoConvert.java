package com.iaminca.web.convert;

import com.google.common.collect.Lists;
import com.iaminca.service.bo.UserTaskInfoBO;
import com.iaminca.web.dto.UserTaskInfoDTO;
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

	public static UserTaskInfoBO toBO(UserTaskInfoDTO userTaskInfoDTO) {
		if (userTaskInfoDTO == null) {
			return null;
		}
		UserTaskInfoBO userTaskInfoBO = new UserTaskInfoBO();
		userTaskInfoBO.setId(userTaskInfoDTO.getId());
		userTaskInfoBO.setUserId(userTaskInfoDTO.getUserId());
		userTaskInfoBO.setApiKeyId(userTaskInfoDTO.getApiKeyId());
		userTaskInfoBO.setCron(userTaskInfoDTO.getCron());
		userTaskInfoBO.setPushUrl(userTaskInfoDTO.getPushUrl());
		userTaskInfoBO.setAuthUsername(userTaskInfoDTO.getAuthUsername());
		userTaskInfoBO.setAuthPassword(userTaskInfoDTO.getAuthPassword());
		userTaskInfoBO.setMaxLength(userTaskInfoDTO.getMaxLength());
		userTaskInfoBO.setCompletionTemplate(userTaskInfoDTO.getCompletionTemplate());
		userTaskInfoBO.setDelFlag(userTaskInfoDTO.getDelFlag());
		userTaskInfoBO.setCreateTime(userTaskInfoDTO.getCreateTime());
		userTaskInfoBO.setUpdateTime(userTaskInfoDTO.getUpdateTime());
		return userTaskInfoBO;
	}

	public static UserTaskInfoDTO toDTO(UserTaskInfoBO userTaskInfoBO) {
		if (userTaskInfoBO == null) {
			return null;
		}
		UserTaskInfoDTO userTaskInfoDTO = new UserTaskInfoDTO();
		userTaskInfoDTO.setId(userTaskInfoBO.getId());
		userTaskInfoDTO.setUserId(userTaskInfoBO.getUserId());
		userTaskInfoDTO.setApiKeyId(userTaskInfoBO.getApiKeyId());
		userTaskInfoDTO.setCron(userTaskInfoBO.getCron());
		userTaskInfoDTO.setPushUrl(userTaskInfoBO.getPushUrl());
		userTaskInfoDTO.setAuthUsername(userTaskInfoBO.getAuthUsername());
		userTaskInfoDTO.setAuthPassword(userTaskInfoBO.getAuthPassword());
		userTaskInfoDTO.setMaxLength(userTaskInfoBO.getMaxLength());
		userTaskInfoDTO.setCompletionTemplate(userTaskInfoBO.getCompletionTemplate());
		userTaskInfoDTO.setDelFlag(userTaskInfoBO.getDelFlag());
		userTaskInfoDTO.setCreateTime(userTaskInfoBO.getCreateTime());
		userTaskInfoDTO.setUpdateTime(userTaskInfoBO.getUpdateTime());
		return userTaskInfoDTO;
	}

	public static List<UserTaskInfoBO> toBOList(List<UserTaskInfoDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
			return Collections.emptyList();
		}

		List<UserTaskInfoBO> boList = Lists.newArrayList();
		for (UserTaskInfoDTO userTaskInfoDTO : dtoList) {
			if (userTaskInfoDTO != null) {
				boList.add(toBO(userTaskInfoDTO));
			}
		}
		return boList;
	}

	public static List<UserTaskInfoDTO> toDTOList(List<UserTaskInfoBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
			return Collections.emptyList();
		}

		List<UserTaskInfoDTO> dtoList = Lists.newArrayList();
		for (UserTaskInfoBO userTaskInfoBO : boList) {
			if (userTaskInfoBO != null) {
				dtoList.add(toDTO(userTaskInfoBO));
			}
		}
		return dtoList;
	}

}
