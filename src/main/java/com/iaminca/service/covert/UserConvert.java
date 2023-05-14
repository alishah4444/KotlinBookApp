package com.iaminca.service.covert;


import com.google.common.collect.Lists;
import com.iaminca.dal.dataobject.UserDO;
import com.iaminca.service.bo.UserBO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-05-02 16:58:22
 */
public class UserConvert {

	public static UserBO toBO(UserDO userDO) {
		if (userDO == null) {
			return null;
		}
		UserBO userBO = new UserBO();
		userBO.setId(userDO.getId());
		userBO.setUserName(userDO.getUserName());
		userBO.setUserPhone(userDO.getUserPhone());
		userBO.setPassword(userDO.getPassword());
		userBO.setUserChatLimitation(userDO.getUserChatLimitation());
		userBO.setUserLengthLimitation(userDO.getUserLengthLimitation());
		userBO.setUserType(userDO.getUserType());
		userBO.setDelFlag(userDO.getDelFlag());
		userBO.setCreateTime(userDO.getCreateTime());
		userBO.setUpdateTime(userDO.getUpdateTime());
		return userBO;
	}

	public static UserDO toDO(UserBO userBO) {
		if (userBO == null) {
			return null;
		}
		UserDO userDO = new UserDO();
		userDO.setId(userBO.getId());
		userDO.setUserName(userBO.getUserName());
		userDO.setUserPhone(userBO.getUserPhone());
		userDO.setPassword(userBO.getPassword());
		userDO.setUserChatLimitation(userBO.getUserChatLimitation());
		userDO.setUserLengthLimitation(userBO.getUserLengthLimitation());
		userDO.setUserType(userBO.getUserType());
		userDO.setDelFlag(userBO.getDelFlag());
		userDO.setCreateTime(userBO.getCreateTime());
		userDO.setUpdateTime(userBO.getUpdateTime());
		return userDO;
	}

	public static List<UserBO> toBOList(List<UserDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<UserBO> dtoList = Lists.newArrayList();
		for (UserDO userDO : doList) {
			if (userDO != null) {
				dtoList.add(toBO(userDO));
			}
		}
		return dtoList;
	}

	public static List<UserDO> toDOList(List<UserBO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
		}

		List<UserDO> doList = Lists.newArrayList();
		for (UserBO userBO: dtoList) {
			if (userBO!= null) {
				doList.add(toDO(userBO));
			}
		}
		return doList;
	}

}
