package com.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.TokenInfo;
import com.core.entity.TokenInfoExample;
import com.core.mapper.TokenInfoMapper;
import com.core.util.Utils;

@Service
public class TokenService {
	
	@Autowired
	private TokenInfoMapper tokenInfoMapper;
	
	public boolean addToken(String database,TokenInfo token) {
		Utils.setDatabase(database);
		int result = tokenInfoMapper.insertSelective(token);
		return result==1?true:false;
	}
	public TokenInfo getToken(String database,int staff) {
		Utils.setDatabase(database);
		TokenInfoExample example =new TokenInfoExample();
		example.createCriteria().andStaffEqualTo(staff);
		try {
			TokenInfo token = tokenInfoMapper.selectByExample(example).get(0);
			return token;
		} catch (Exception e) {
			return null;
		}
		
	}
	public boolean updateToken(String database,TokenInfo token) {
		Utils.setDatabase(database);
		int result = tokenInfoMapper.updateByPrimaryKeySelective(token);
		return result==1?true:false;
	}
	public TokenInfo getToken(String database,String token) {
		Utils.setDatabase(database);
		TokenInfoExample example =new TokenInfoExample();
		example.createCriteria().andTokenEqualTo(token);
		try {
			return tokenInfoMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			return null;
		}
	}
}
