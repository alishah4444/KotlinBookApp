package com.iaminca.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.UserKeywordsListDO;
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
public class UserKeywordsListConvert {

	public static UserKeywordsBO toBO(UserKeywordsListDO userKeywordsListDO) {
		if (userKeywordsListDO == null) {
			return null;
		}
		UserKeywordsBO userKeywordsBO = new UserKeywordsBO();
		userKeywordsBO.setId(userKeywordsListDO.getId());
		userKeywordsBO.setUserId(userKeywordsListDO.getUserId());
		userKeywordsBO.setFileName(userKeywordsListDO.getFileName());
		userKeywordsBO.setKeywordNumber(userKeywordsListDO.getKeywordNumber());
		userKeywordsBO.setApiKeyId(userKeywordsListDO.getApiKeyId());
		userKeywordsBO.setPushUrl(userKeywordsListDO.getPushUrl());
		userKeywordsBO.setAuthUsername(userKeywordsListDO.getAuthUsername());
		userKeywordsBO.setAuthPassword(userKeywordsListDO.getAuthPassword());
		userKeywordsBO.setMaxLength(userKeywordsListDO.getMaxLength());
		userKeywordsBO.setCompletionTemplate(userKeywordsListDO.getCompletionTemplate());
		userKeywordsBO.setDelFlag(userKeywordsListDO.getDelFlag());
		userKeywordsBO.setCreateTime(userKeywordsListDO.getCreateTime());
		userKeywordsBO.setUpdateTime(userKeywordsListDO.getUpdateTime());
		return userKeywordsBO;
	}


	public static List<UserKeywordsBO> toBOList(List<UserKeywordsListDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserKeywordsBO> boList = Lists.newArrayList();
		for (UserKeywordsListDO UserKeywordsListDO : doList) {
			if (UserKeywordsListDO != null) {
				boList.add(toBO(UserKeywordsListDO));
			}
		}
		return boList;
	}


}
