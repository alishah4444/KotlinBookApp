package com.iaminca.openai.service.covert;

import com.google.common.collect.Lists;
import com.iaminca.openai.dal.dataobject.HttpsCertificateDO;
import com.iaminca.openai.service.bo.HttpsCertificateBO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @author xw
 * @email xw
 * @date 2023-07-10 14:20:08
 */
public class HttpsCertificateConvert {

	public static HttpsCertificateBO toBO(HttpsCertificateDO httpsCertificateDO) {
		if (httpsCertificateDO == null) {
			return null;
		}
		HttpsCertificateBO httpsCertificateBO = new HttpsCertificateBO();
		httpsCertificateBO.setId(httpsCertificateDO.getId());
		httpsCertificateBO.setUserId(httpsCertificateDO.getUserId());
		httpsCertificateBO.setSiteUrl(httpsCertificateDO.getSiteUrl());
		httpsCertificateBO.setPrivateKeyUrl(httpsCertificateDO.getPrivateKeyUrl());
		httpsCertificateBO.setPublicKeyUrl(httpsCertificateDO.getPublicKeyUrl());
		httpsCertificateBO.setExpireTime(httpsCertificateDO.getExpireTime());
		httpsCertificateBO.setKeyStatue(httpsCertificateDO.getKeyStatue());
		httpsCertificateBO.setDelFlag(httpsCertificateDO.getDelFlag());
		httpsCertificateBO.setCreateTime(httpsCertificateDO.getCreateTime());
		httpsCertificateBO.setUpdateTime(httpsCertificateDO.getUpdateTime());
		return httpsCertificateBO;
	}

	public static HttpsCertificateDO toDO(HttpsCertificateBO httpsCertificateBO) {
		if (httpsCertificateBO == null) {
			return null;
		}
		HttpsCertificateDO httpsCertificateDO = new HttpsCertificateDO();
		httpsCertificateDO.setId(httpsCertificateBO.getId());
		httpsCertificateDO.setUserId(httpsCertificateBO.getUserId());
		httpsCertificateDO.setSiteUrl(httpsCertificateBO.getSiteUrl());
		httpsCertificateDO.setPrivateKeyUrl(httpsCertificateBO.getPrivateKeyUrl());
		httpsCertificateDO.setPublicKeyUrl(httpsCertificateBO.getPublicKeyUrl());
		httpsCertificateDO.setExpireTime(httpsCertificateBO.getExpireTime());
		httpsCertificateDO.setKeyStatue(httpsCertificateBO.getKeyStatue());
		httpsCertificateDO.setDelFlag(httpsCertificateBO.getDelFlag());
		httpsCertificateDO.setCreateTime(httpsCertificateBO.getCreateTime());
		httpsCertificateDO.setUpdateTime(httpsCertificateBO.getUpdateTime());
		return httpsCertificateDO;
	}

	public static List<HttpsCertificateBO> toBOList(List<HttpsCertificateDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
			return Collections.emptyList();
		}
		List<HttpsCertificateBO> boList = Lists.newArrayList();
		for (HttpsCertificateDO httpsCertificateDO : doList) {
			if (httpsCertificateDO != null) {
				boList.add(toBO(httpsCertificateDO));
			}
		}
		return boList;
	}

	public static List<HttpsCertificateDO> toDOList(List<HttpsCertificateBO> boList) {
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
		}

		List<HttpsCertificateDO> doList = Lists.newArrayList();
		for (HttpsCertificateBO httpsCertificateBO : boList) {
			if (httpsCertificateBO != null) {
				doList.add(toDO(httpsCertificateBO));
			}
		}
		return doList;
	}

}
