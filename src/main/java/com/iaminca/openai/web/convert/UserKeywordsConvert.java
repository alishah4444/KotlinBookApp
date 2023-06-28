package com.iaminca.openai.web.convert;

import com.google.common.collect.Lists;
import com.iaminca.openai.service.bo.UserKeywordsBO;
import com.iaminca.openai.web.dto.UserKeywordsDTO;
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
public class UserKeywordsConvert {

	public static UserKeywordsBO toBO(UserKeywordsDTO userKeywordsDTO) {
		if (userKeywordsDTO == null) {
			return null;
		}
		UserKeywordsBO userKeywordsBO = new UserKeywordsBO();
		userKeywordsBO.setId(userKeywordsDTO.getId());
		userKeywordsBO.setUserId(userKeywordsDTO.getUserId());
		userKeywordsBO.setApiKeyId(userKeywordsDTO.getApiKeyId());
		userKeywordsBO.setUserKey(userKeywordsDTO.getUserKey());
		userKeywordsBO.setFileName(userKeywordsDTO.getFileName());
		userKeywordsBO.setKeywordNumber(userKeywordsDTO.getKeywordNumber());
		userKeywordsBO.setKeywords(userKeywordsDTO.getKeywords());
		userKeywordsBO.setPushUrl(userKeywordsDTO.getPushUrl());
		userKeywordsBO.setAuthUsername(userKeywordsDTO.getAuthUsername());
		userKeywordsBO.setAuthPassword(userKeywordsDTO.getAuthPassword());
		userKeywordsBO.setMaxLength(userKeywordsDTO.getMaxLength());
		userKeywordsBO.setCompletionTemplate(userKeywordsDTO.getCompletionTemplate());
		userKeywordsBO.setDelFlag(userKeywordsDTO.getDelFlag());
		userKeywordsBO.setCreateTime(userKeywordsDTO.getCreateTime());
		userKeywordsBO.setUpdateTime(userKeywordsDTO.getUpdateTime());
		return userKeywordsBO;
	}

	public static UserKeywordsDTO toDTO(UserKeywordsBO userKeywordsBO) {
		if (userKeywordsBO == null) {
			return null;
		}
		UserKeywordsDTO userKeywordsDTO = new UserKeywordsDTO();
		userKeywordsDTO.setId(userKeywordsBO.getId());
		userKeywordsDTO.setUserId(userKeywordsBO.getUserId());
		userKeywordsDTO.setApiKeyId(userKeywordsBO.getApiKeyId());
		userKeywordsDTO.setUserKey(userKeywordsBO.getUserKey());
		userKeywordsDTO.setFileName(userKeywordsBO.getFileName());
		userKeywordsDTO.setKeywordNumber(userKeywordsBO.getKeywordNumber());
		userKeywordsDTO.setKeywords(userKeywordsBO.getKeywords());
		userKeywordsDTO.setPushUrl(userKeywordsBO.getPushUrl());
		userKeywordsDTO.setAuthUsername(userKeywordsBO.getAuthUsername());
		userKeywordsDTO.setAuthPassword(userKeywordsBO.getAuthPassword());
		userKeywordsDTO.setMaxLength(userKeywordsBO.getMaxLength());
		userKeywordsDTO.setCompletionTemplate(userKeywordsBO.getCompletionTemplate());
		userKeywordsDTO.setDelFlag(userKeywordsBO.getDelFlag());
		userKeywordsDTO.setCreateTime(userKeywordsBO.getCreateTime());
		userKeywordsDTO.setUpdateTime(userKeywordsBO.getUpdateTime());
		return userKeywordsDTO;
	}

	public static List<UserKeywordsBO> toBOList(List<UserKeywordsDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
			return Collections.emptyList();
		}

		List<UserKeywordsBO> boList = Lists.newArrayList();
		for (UserKeywordsDTO userKeywordsDTO : dtoList) {
			if (userKeywordsDTO != null) {
				boList.add(toBO(userKeywordsDTO));
			}
		}
		return boList;
	}

	public static List<UserKeywordsDTO> toDTOList(List<UserKeywordsBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
			return Collections.emptyList();
		}

		List<UserKeywordsDTO> dtoList = Lists.newArrayList();
		for (UserKeywordsBO userKeywordsBO : boList) {
			if (userKeywordsBO != null) {
				dtoList.add(toDTO(userKeywordsBO));
			}
		}
		return dtoList;
	}

}
