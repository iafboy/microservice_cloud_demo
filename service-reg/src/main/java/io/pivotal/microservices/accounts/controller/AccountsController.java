package io.pivotal.microservices.accounts.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.microservices.accounts.db.model.AccountModel;
import io.pivotal.microservices.accounts.service.AccountRepoService;
import io.pivotal.microservices.exceptions.AccountNotFoundException;


@RestController
public class AccountsController {

	protected Logger logger = Logger.getLogger(AccountsController.class
			.getName());

	@Resource(name="AccountRepoService")
	protected AccountRepoService accountService;
	
	@RequestMapping("/accounts/{accountNumber}")
	public AccountModel byNumber(@PathVariable("accountNumber") String accountNumber) {
		logger.info("accounts-service total: " + accountService.countAccounts());
		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		AccountModel account = accountService.findByNumber(accountNumber);
		logger.info("accounts-service byNumber() found: " + account);

		if (account == null)
			throw new AccountNotFoundException(accountNumber);
		else {
			return account;
		}
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
