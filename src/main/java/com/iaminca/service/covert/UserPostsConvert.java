package com.iaminca.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.UserPostsDO;
import com.iaminca.service.bo.UserPostsBO;
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
public class UserPostsConvert {

	public static UserPostsBO toBO(UserPostsDO userPostsDO) {
		if (userPostsDO == null) {
			return null;
		}
		UserPostsBO userPostsBO = new UserPostsBO();
		userPostsBO.setId(userPostsDO.getId());
		userPostsBO.setUserId(userPostsDO.getUserId());
		userPostsBO.setUserTaskId(userPostsDO.getUserTaskId());
		userPostsBO.setPostAuthor(userPostsDO.getPostAuthor());
		userPostsBO.setPostDate(userPostsDO.getPostDate());
		userPostsBO.setPostDateGmt(userPostsDO.getPostDateGmt());
		userPostsBO.setPostContent(userPostsDO.getPostContent());
		userPostsBO.setPostTitle(userPostsDO.getPostTitle());
		userPostsBO.setPostExcerpt(userPostsDO.getPostExcerpt());
		userPostsBO.setPostStatus(userPostsDO.getPostStatus());
		userPostsBO.setCommentStatus(userPostsDO.getCommentStatus());
		userPostsBO.setPingStatus(userPostsDO.getPingStatus());
		userPostsBO.setPostPassword(userPostsDO.getPostPassword());
		userPostsBO.setPostName(userPostsDO.getPostName());
		userPostsBO.setPostContentFiltered(userPostsDO.getPostContentFiltered());
		userPostsBO.setPostParent(userPostsDO.getPostParent());
		userPostsBO.setGuid(userPostsDO.getGuid());
		userPostsBO.setMenuOrder(userPostsDO.getMenuOrder());
		userPostsBO.setPostType(userPostsDO.getPostType());
		userPostsBO.setPostMimeType(userPostsDO.getPostMimeType());
		userPostsBO.setChatStatus(userPostsDO.getChatStatus());
		userPostsBO.setPushStatus(userPostsDO.getPushStatus());
		userPostsBO.setRepeatNumber(userPostsDO.getRepeatNumber());
		userPostsBO.setDelFlag(userPostsDO.getDelFlag());
		userPostsBO.setCreateTime(userPostsDO.getCreateTime());
		userPostsBO.setUpdateTime(userPostsDO.getUpdateTime());
		return userPostsBO;
	}

	public static UserPostsDO toDO(UserPostsBO userPostsBO) {
		if (userPostsBO == null) {
			return null;
		}
		UserPostsDO userPostsDO = new UserPostsDO();
		userPostsDO.setId(userPostsBO.getId());
		userPostsDO.setUserId(userPostsBO.getUserId());
		userPostsDO.setUserTaskId(userPostsBO.getUserTaskId());
		userPostsDO.setPostAuthor(userPostsBO.getPostAuthor());
		userPostsDO.setPostDate(userPostsBO.getPostDate());
		userPostsDO.setPostDateGmt(userPostsBO.getPostDateGmt());
		userPostsDO.setPostContent(userPostsBO.getPostContent());
		userPostsDO.setPostTitle(userPostsBO.getPostTitle());
		userPostsDO.setPostExcerpt(userPostsBO.getPostExcerpt());
		userPostsDO.setPostStatus(userPostsBO.getPostStatus());
		userPostsDO.setCommentStatus(userPostsBO.getCommentStatus());
		userPostsDO.setPingStatus(userPostsBO.getPingStatus());
		userPostsDO.setPostPassword(userPostsBO.getPostPassword());
		userPostsDO.setPostName(userPostsBO.getPostName());
		userPostsDO.setPostContentFiltered(userPostsBO.getPostContentFiltered());
		userPostsDO.setPostParent(userPostsBO.getPostParent());
		userPostsDO.setGuid(userPostsBO.getGuid());
		userPostsDO.setMenuOrder(userPostsBO.getMenuOrder());
		userPostsDO.setPostType(userPostsBO.getPostType());
		userPostsDO.setPostMimeType(userPostsBO.getPostMimeType());
		userPostsDO.setChatStatus(userPostsBO.getChatStatus());
		userPostsDO.setPushStatus(userPostsBO.getPushStatus());
		userPostsDO.setRepeatNumber(userPostsBO.getRepeatNumber());
		userPostsDO.setDelFlag(userPostsBO.getDelFlag());
		userPostsDO.setCreateTime(userPostsBO.getCreateTime());
		userPostsDO.setUpdateTime(userPostsBO.getUpdateTime());
		return userPostsDO;
	}

	public static List<UserPostsBO> toBOList(List<UserPostsDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserPostsBO> boList = Lists.newArrayList();
		for (UserPostsDO userPostsDO : doList) {
			if (userPostsDO != null) {
				boList.add(toBO(userPostsDO));
			}
		}
		return boList;
	}

	public static List<UserPostsDO> toDOList(List<UserPostsBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<UserPostsDO> doList = Lists.newArrayList();
		for (UserPostsBO userPostsBO : boList) {
			if (userPostsBO != null) {
				doList.add(toDO(userPostsBO));
			}
		}
		return doList;
	}

}
