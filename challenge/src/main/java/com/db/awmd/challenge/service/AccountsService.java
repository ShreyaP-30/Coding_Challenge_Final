package com.db.awmd.challenge.service;

import java.math.BigDecimal;
import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

	@Getter
	private final AccountsRepository accountsRepository;

	@Autowired
	public AccountsService(AccountsRepository accountsRepository) {
		this.accountsRepository = accountsRepository;
	}

	public void createAccount(Account account) {
		this.accountsRepository.createAccount(account);
	}

	public Account getAccount(String accountId) {
		return this.accountsRepository.getAccount(accountId);
	}

	public void withdraw(BigDecimal wamount)
	{
		this.accountsRepository.withdraw(wamount);
	}

	public void deposit(BigDecimal damount)
	{
		this.accountsRepository.deposit(damount);
	}

}
