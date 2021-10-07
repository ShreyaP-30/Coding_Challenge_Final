 package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
 
@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

	private static  Map<String, Account> accounts = new ConcurrentHashMap<>();

	private Account account;

	@Override
	public void createAccount(Account account) throws DuplicateAccountIdException {
		Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
		if (previousAccount != null) {
			throw new DuplicateAccountIdException(account.getAccountId());
		}
	}

	@Override
	public Account getAccount(String accountId) {
		return accounts.get(accountId);
	}

	@Override
	public void clearAccounts() {
		accounts.clear();
	}
	
	@Override
	public void withDraw(BigDecimal amount) {
        if (getBalance() == null || getBalance().compareTo(amount) < 0)
            throw new InsufficientBalanceException(getAccountId(), getBalance().doubleValue(), amount.doubleValue());

        synchronized (this) {
            this.balance = this.balance.subtract(amount);
        }
    }
	
	@Override
    public void debit(BigDecimal amount) {
        synchronized (this) {
            this.balance = balance.add(amount);
        }
    }
	
	

}