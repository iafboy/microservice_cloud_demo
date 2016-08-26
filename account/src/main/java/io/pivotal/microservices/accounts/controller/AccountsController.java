package io.pivotal.microservices.accounts.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.microservices.accounts.db.model.AccountModel;
import io.pivotal.microservices.accounts.db.model.ReplyMessage;
import io.pivotal.microservices.accounts.service.AccountRepoService;
import io.pivotal.microservices.exceptions.AccountNotFoundException;
import io.pivotal.microservices.exceptions.RedisNotSyncExcpetion;


@RestController
public class AccountsController {

	protected Logger logger = Logger.getLogger(AccountsController.class
			.getName());

	@Resource(name="AccountRepoService")
	protected AccountRepoService accountService;
	
	@RequestMapping("/accounts/{accountNumber}")
	public AccountModel byNumber(@PathVariable("accountNumber") String accountNumber) {
		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		AccountModel account = accountService.findByNumber(accountNumber);
		logger.info("accounts-service byNumber() found: " + account);

		if (account == null)
			throw new AccountNotFoundException(accountNumber);
		else {
			return account;
		}
	}

	@RequestMapping(value="/updateAccount/{accountNumber}",method = RequestMethod.PUT)
	public ReplyMessage updatebyNumber(@PathVariable("accountNumber") String accountNumber ,@RequestBody AccountModel account) {
		ReplyMessage rm=new ReplyMessage();
		try {
			rm.setSucc(accountService.updateAccount(account));
		} catch (RedisNotSyncExcpetion e) {
			//todo sync redis
			e.printStackTrace();
			
		}
		return rm;
	}
	
	@RequestMapping(value="/deleteAccount/{accountNumber}")
	public ReplyMessage deletebyNumber(@PathVariable("accountNumber") String accountNumber) {
		logger.info("accounts-service delete account: " + accountNumber);
		ReplyMessage rm=new ReplyMessage();
		try {
			rm.setSucc(accountService.deleteByNumber(accountNumber));
		} catch (RedisNotSyncExcpetion e) {
			//todo sync redis
			e.printStackTrace();
		}
		return rm;
	}
	
	@RequestMapping("/accounts/owner/{name}")
	public List<AccountModel> byOwner(@PathVariable("name") String partialName) {
		logger.info("accounts-service byOwner() invoked: "
				+ accountService.getClass().getName() + " for "
				+ partialName);

		List<AccountModel> accounts = accountService
				.findByOwnerContainingIgnoreCase(partialName);
		logger.info("accounts-service byOwner() found: " + accounts);

		if (accounts == null || accounts.size() == 0)
			throw new AccountNotFoundException(partialName);
		else {
			return accounts;
		}
	}
}
