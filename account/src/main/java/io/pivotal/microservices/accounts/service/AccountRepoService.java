package io.pivotal.microservices.accounts.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.pivotal.microservices.accounts.db.dao.AccountDao;
import io.pivotal.microservices.accounts.db.model.AccountModel;
import io.pivotal.microservices.exceptions.RedisNotSyncExcpetion;
@Service(value="AccountRepoService")

public class AccountRepoService {

	@Resource
	protected AccountDao accountDao;

	public int countAccounts() {
		return accountDao.countAccounts();
	}
	
	@Cacheable(value="accountRedisCache")
	public AccountModel findByNumber(String accountNumber) {
		return accountDao.findByNumber(accountNumber);
	}

	public List<AccountModel> findByOwnerContainingIgnoreCase(String partialName) {
		return accountDao.findByOwnerContainingIgnoreCase(partialName);
	}
	
	public boolean updateByNumber(String accountNumber,String name) {
		return accountDao.updateName(accountNumber, name);
	}
	
	public boolean updateAccount(AccountModel account) throws Exception {
		boolean result=false;
		if( accountDao.updateAccount(account)){
			result=true;
		}
		return result;
	}
	public boolean insertAccount(AccountModel account) throws Exception {
		boolean result=false;
		if(accountDao.insertAccount(account)){
			result=true;
		}
		return result;
	}
	public boolean deleteByNumber(String accountNumber) throws Exception{
		boolean result=false;
		if(accountDao.deleteAccount(accountNumber)){
			result=true;
		}
		return result;
	}
}
