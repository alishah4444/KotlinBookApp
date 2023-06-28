package com.iaminca.openai.web.convert;

import com.google.common.collect.Lists;
import com.iaminca.openai.service.bo.UserPostsBO;
import com.iaminca.openai.web.dto.UserPostsDTO;
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

	public static UserPostsBO toBO(UserPostsDTO userPostsDTO) {
		if (userPostsDTO == null) {
			return null;
		}
		UserPostsBO userPostsBO = new UserPostsBO();
		userPostsBO.setId(userPostsDTO.getId());
		userPostsBO.setUserId(userPostsDTO.getUserId());
		userPostsBO.setUserTaskId(userPostsDTO.getUserTaskId());
		userPostsBO.setPostAuthor(userPostsDTO.getPostAuthor());
		userPostsBO.setPostDate(userPostsDTO.getPostDate());
		userPostsBO.setPostDateGmt(userPostsDTO.getPostDateGmt());
		userPostsBO.setPostContent(userPostsDTO.getPostContent());
		userPostsBO.setPostTitle(userPostsDTO.getPostTitle());
		userPostsBO.setPostExcerpt(userPostsDTO.getPostExcerpt());
		userPostsBO.setPostStatus(userPostsDTO.getPostStatus());
		userPostsBO.setCommentStatus(userPostsDTO.getCommentStatus());
		userPostsBO.setPingStatus(userPostsDTO.getPingStatus());
		userPostsBO.setPostPassword(userPostsDTO.getPostPassword());
		userPostsBO.setPostName(userPostsDTO.getPostName());
		userPostsBO.setPostContentFiltered(userPostsDTO.getPostContentFiltered());
		userPostsBO.setPostParent(userPostsDTO.getPostParent());
		userPostsBO.setGuid(userPostsDTO.getGuid());
		userPostsBO.setMenuOrder(userPostsDTO.getMenuOrder());
		userPostsBO.setPostType(userPostsDTO.getPostType());
		userPostsBO.setPostMimeType(userPostsDTO.getPostMimeType());
		userPostsBO.setChatStatus(userPostsDTO.getChatStatus());
		userPostsBO.setPushStatus(userPostsDTO.getPushStatus());
		userPostsBO.setRepeatNumber(userPostsDTO.getRepeatNumber());
		userPostsBO.setDelFlag(userPostsDTO.getDelFlag());
		userPostsBO.setCreateTime(userPostsDTO.getCreateTime());
		userPostsBO.setUpdateTime(userPostsDTO.getUpdateTime());
		return userPostsBO;
	}

	public static UserPostsDTO toDTO(UserPostsBO userPostsBO) {
		if (userPostsBO == null) {
			return null;
		}
		UserPostsDTO userPostsDTO = new UserPostsDTO();
		userPostsDTO.setId(userPostsBO.getId());
		userPostsDTO.setUserId(userPostsBO.getUserId());
		userPostsDTO.setUserTaskId(userPostsBO.getUserTaskId());
		userPostsDTO.setPostAuthor(userPostsBO.getPostAuthor());
		userPostsDTO.setPostDate(userPostsBO.getPostDate());
		userPostsDTO.setPostDateGmt(userPostsBO.getPostDateGmt());
		userPostsDTO.setPostContent(userPostsBO.getPostContent());
		userPostsDTO.setPostTitle(userPostsBO.getPostTitle());
		userPostsDTO.setPostExcerpt(userPostsBO.getPostExcerpt());
		userPostsDTO.setPostStatus(userPostsBO.getPostStatus());
		userPostsDTO.setCommentStatus(userPostsBO.getCommentStatus());
		userPostsDTO.setPingStatus(userPostsBO.getPingStatus());
		userPostsDTO.setPostPassword(userPostsBO.getPostPassword());
		userPostsDTO.setPostName(userPostsBO.getPostName());
		userPostsDTO.setPostContentFiltered(userPostsBO.getPostContentFiltered());
		userPostsDTO.setPostParent(userPostsBO.getPostParent());
		userPostsDTO.setGuid(userPostsBO.getGuid());
		userPostsDTO.setMenuOrder(userPostsBO.getMenuOrder());
		userPostsDTO.setPostType(userPostsBO.getPostType());
		userPostsDTO.setPostMimeType(userPostsBO.getPostMimeType());
		userPostsDTO.setChatStatus(userPostsBO.getChatStatus());
		userPostsDTO.setPushStatus(userPostsBO.getPushStatus());
		userPostsDTO.setRepeatNumber(userPostsBO.getRepeatNumber());
		userPostsDTO.setDelFlag(userPostsBO.getDelFlag());
		userPostsDTO.setCreateTime(userPostsBO.getCreateTime());
		userPostsDTO.setUpdateTime(userPostsBO.getUpdateTime());
		return userPostsDTO;
	}

	public static List<UserPostsBO> toBOList(List<UserPostsDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
			return Collections.emptyList();
		}

		List<UserPostsBO> boList = Lists.newArrayList();
		for (UserPostsDTO userPostsDTO : dtoList) {
			if (userPostsDTO != null) {
				boList.add(toBO(userPostsDTO));
			}
		}
		return boList;
	}

	public static List<UserPostsDTO> toDTOList(List<UserPostsBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
			return Collections.emptyList();
		}

		List<UserPostsDTO> dtoList = Lists.newArrayList();
		for (UserPostsBO userPostsBO : boList) {
			if (userPostsBO != null) {
				dtoList.add(toDTO(userPostsBO));
			}
		}
		return dtoList;
	}

}
