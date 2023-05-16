package com.iaminca.web.convert;

import com.google.common.collect.Lists;
import com.iaminca.service.bo.UserKeyBO;
import com.iaminca.web.dto.UserKeyDTO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-04 17:34:01
 */
public class UserKeyConvertDTO {

	public static UserKeyBO toBO(UserKeyDTO userKeyDTO) {
		if (userKeyDTO == null) {
			return null;
		}
		UserKeyBO userKeyBO = new UserKeyBO();
		userKeyBO.setId(userKeyDTO.getId());
		userKeyBO.setUserId(userKeyDTO.getUserId());
		userKeyBO.setUserKey(userKeyDTO.getUserKey());
		userKeyBO.setUserChatLimitation(userKeyDTO.getUserChatLimitation());
		userKeyBO.setUserLengthLimitation(userKeyDTO.getUserLengthLimitation());
		userKeyBO.setDelFlag(userKeyDTO.getDelFlag());
		userKeyBO.setCreateTime(userKeyDTO.getCreateTime());
		userKeyBO.setUpdateTime(userKeyDTO.getUpdateTime());
		userKeyBO.setName(userKeyDTO.getName());
		return userKeyBO;
	}

	public static UserKeyDTO toDTO(UserKeyBO userKeyBO) {
		if (userKeyBO == null) {
			return null;
		}
		UserKeyDTO userKeyDTO = new UserKeyDTO();
		userKeyDTO.setId(userKeyBO.getId());
		userKeyDTO.setUserId(userKeyBO.getUserId());
		userKeyDTO.setUserKey(userKeyBO.getUserKey());
		userKeyDTO.setUserChatLimitation(userKeyBO.getUserChatLimitation());
		userKeyDTO.setUserLengthLimitation(userKeyBO.getUserLengthLimitation());
		userKeyDTO.setDelFlag(userKeyBO.getDelFlag());
		userKeyDTO.setCreateTime(userKeyBO.getCreateTime());
		userKeyDTO.setUpdateTime(userKeyBO.getUpdateTime());
		userKeyDTO.setName(userKeyBO.getName());
		return userKeyDTO;
	}

	public static List<UserKeyBO> toBOList(List<UserKeyDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
			return Collections.emptyList();
		}
		List<UserKeyBO> boList = Lists.newArrayList();
		for (UserKeyDTO userKeyDTO : dtoList) {
			if (userKeyDTO != null) {
				boList.add(toBO(userKeyDTO));
			}
		}
		return boList;
	}

	public static List<UserKeyDTO> toDTOList(List<UserKeyBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<UserKeyDTO> dtoList = Lists.newArrayList();
		for (UserKeyBO userKeyBO : boList) {
			if (userKeyBO != null) {
				dtoList.add(toDTO(userKeyBO));
			}
		}
		return dtoList;
	}

}
