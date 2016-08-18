package io.pivotal.microservices.accounts.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.pivotal.microservices.accounts.db.dao.AccountDao;
import io.pivotal.microservices.accounts.db.model.AccountModel;
@Service(value="AccountRepoService")

public class AccountRepoService {

	@Resource
	protected AccountDao accountDao;

	public int countAccounts() {
		return accountDao.countAccounts();
	}

	public AccountModel findByNumber(String accountNumber) {
		return accountDao.findByNumber(accountNumber);
	}

	public List<AccountModel> findByOwnerContainingIgnoreCase(String partialName) {
		return accountDao.findByOwnerContainingIgnoreCase(partialName);
	}
	
	public boolean updateByNumber(String accountNumber,String name) {
		return accountDao.updateName(accountNumber, name);
	}
	public boolean updateAccount(AccountModel account) {
		return accountDao.updateAccount(account);
	}
	public boolean insertAccount(AccountModel account) {
		return accountDao.insertAccount(account);
	}
	public boolean deleteByNumber(String accountNumber) {
		return accountDao.deleteAccount(accountNumber);
	}
}
