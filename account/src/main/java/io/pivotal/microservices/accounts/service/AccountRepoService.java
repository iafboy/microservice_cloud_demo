package io.pivotal.microservices.accounts.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.pivotal.microservices.accounts.cache.RedisManager;
import io.pivotal.microservices.accounts.db.dao.AccountDao;
import io.pivotal.microservices.accounts.db.model.AccountModel;
import io.pivotal.microservices.exceptions.RedisNotSyncExcpetion;
@Service(value="AccountRepoService")

public class AccountRepoService {

	@Resource
	protected AccountDao accountDao;
	@Resource
	protected RedisManager redisManager;

	public int countAccounts() {
		return accountDao.countAccounts();
	}
	
	@Cacheable(value="accountRedisCache")
	public AccountModel findByNumber(String accountNumber) {
		if(redisManager.get(accountNumber)!=null){
			return (AccountModel) redisManager.get(accountNumber);
		}
		return accountDao.findByNumber(accountNumber);
	}

	public List<AccountModel> findByOwnerContainingIgnoreCase(String partialName) {
		return accountDao.findByOwnerContainingIgnoreCase(partialName);
	}
	
	public boolean updateByNumber(String accountNumber,String name) {
		return accountDao.updateName(accountNumber, name);
	}
	
	public boolean updateAccount(AccountModel account) throws RedisNotSyncExcpetion {
		boolean result=false;
		if( accountDao.updateAccount(account)){
			result=true;
			if(redisManager.set(account.getNumber(), account)){
				throw new RedisNotSyncExcpetion(account);
			}
		}
		return result;
	}
	public boolean insertAccount(AccountModel account) throws RedisNotSyncExcpetion {
		boolean result=false;
		if(accountDao.insertAccount(account)){
			result=true;
			if(redisManager.set(account.getNumber(), account)){
				throw new RedisNotSyncExcpetion(account);
			}
		}
		return result;
	}
	public boolean deleteByNumber(String accountNumber) throws RedisNotSyncExcpetion{
		boolean result=false;
		if(accountDao.deleteAccount(accountNumber)){	
			redisManager.remove(accountNumber);
			result=true;
		}
		return result;
	}
}
