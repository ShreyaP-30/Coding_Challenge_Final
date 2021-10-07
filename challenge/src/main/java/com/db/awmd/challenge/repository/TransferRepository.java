package com.db.awmd.challenge.repository;

import java.math.BigDecimal;

import com.db.awmd.challenge.domain.Transfer;
import com.db.awmd.challenge.exception.SameAccountIdException;
import com.db.awmd.challenge.exception.InsufficientBalanceException;
import com.db.awmd.challenge.exception.NegativeAmountException;

public interface TransferRepository {

	Transfer transferMoney(Transfer transfer) throws SameAccountIdException,InsufficientBalanceException,NegativeAmountException;
	
	BigDecimal checkbalance(String accountId);

}
