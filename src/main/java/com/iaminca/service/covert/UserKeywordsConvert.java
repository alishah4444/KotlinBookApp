package com.iaminca.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.UserKeywordsDO;
import com.iaminca.service.bo.UserKeywordsBO;
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

	public static UserKeywordsBO toBO(UserKeywordsDO userKeywordsDO) {
		if (userKeywordsDO == null) {
			return null;
		}
		UserKeywordsBO userKeywordsBO = new UserKeywordsBO();
		userKeywordsBO.setId(userKeywordsDO.getId());
		userKeywordsBO.setUserId(userKeywordsDO.getUserId());
		userKeywordsBO.setFileName(userKeywordsDO.getFileName());
		userKeywordsBO.setKeywordNumber(userKeywordsDO.getKeywordNumber());
		userKeywordsBO.setKeywords(userKeywordsDO.getKeywords());
		userKeywordsBO.setApiKeyId(userKeywordsDO.getApiKeyId());
		userKeywordsBO.setUserKey(userKeywordsDO.getUserKey());
		userKeywordsBO.setPushUrl(userKeywordsDO.getPushUrl());
		userKeywordsBO.setAuthUsername(userKeywordsDO.getAuthUsername());
		userKeywordsBO.setAuthPassword(userKeywordsDO.getAuthPassword());
		userKeywordsBO.setMaxLength(userKeywordsDO.getMaxLength());
		userKeywordsBO.setCompletionTemplate(userKeywordsDO.getCompletionTemplate());
		userKeywordsBO.setDelFlag(userKeywordsDO.getDelFlag());
		userKeywordsBO.setCreateTime(userKeywordsDO.getCreateTime());
		userKeywordsBO.setUpdateTime(userKeywordsDO.getUpdateTime());
		return userKeywordsBO;
	}

	public static UserKeywordsDO toDO(UserKeywordsBO userKeywordsBO) {
		if (userKeywordsBO == null) {
			return null;
		}
		UserKeywordsDO userKeywordsDO = new UserKeywordsDO();
		userKeywordsDO.setId(userKeywordsBO.getId());
		userKeywordsDO.setUserId(userKeywordsBO.getUserId());
		userKeywordsDO.setApiKeyId(userKeywordsBO.getApiKeyId());
		userKeywordsDO.setUserKey(userKeywordsBO.getUserKey());
		userKeywordsDO.setFileName(userKeywordsBO.getFileName());
		userKeywordsDO.setKeywordNumber(userKeywordsBO.getKeywordNumber());
		userKeywordsDO.setKeywords(userKeywordsBO.getKeywords());
		userKeywordsDO.setPushUrl(userKeywordsBO.getPushUrl());
		userKeywordsDO.setAuthUsername(userKeywordsBO.getAuthUsername());
		userKeywordsDO.setAuthPassword(userKeywordsBO.getAuthPassword());
		userKeywordsDO.setMaxLength(userKeywordsBO.getMaxLength());
		userKeywordsDO.setCompletionTemplate(userKeywordsBO.getCompletionTemplate());
		userKeywordsDO.setDelFlag(userKeywordsBO.getDelFlag());
		userKeywordsDO.setCreateTime(userKeywordsBO.getCreateTime());
		userKeywordsDO.setUpdateTime(userKeywordsBO.getUpdateTime());
		return userKeywordsDO;
	}

	public static List<UserKeywordsBO> toBOList(List<UserKeywordsDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserKeywordsBO> boList = Lists.newArrayList();
		for (UserKeywordsDO userKeywordsDO : doList) {
			if (userKeywordsDO != null) {
				boList.add(toBO(userKeywordsDO));
			}
		}
		return boList;
	}

	public static List<UserKeywordsDO> toDOList(List<UserKeywordsBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<UserKeywordsDO> doList = Lists.newArrayList();
		for (UserKeywordsBO userKeywordsBO : boList) {
			if (userKeywordsBO != null) {
				doList.add(toDO(userKeywordsBO));
			}
		}
		return doList;
	}

}
