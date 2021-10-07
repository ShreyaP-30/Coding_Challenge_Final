package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.Transfer;
import com.db.awmd.challenge.exception.SameAccountIdException;
import com.db.awmd.challenge.exception.InsufficientBalanceException;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRepositoryInMemory implements TransferRepository {

	
	@Override
	public Transfer transferMoney(Transfer transfer)throws SameAccountIdException,InsufficientBalanceException,NegativeAmountException {
		synchronized(this){
	
		if (transfer.getAccountFrom().equals(transfer.getAccountTo())) {
			throw new SameAccountIdException("Both account numbers are the same");

		} else if (transfer.getAmount() <= 0) {
			throw new NegativeAmountException("Amount should be greater than 0 to transfer");

		} else if (checkbalance(transfer.getAccountFrom()).compareTo(transfer.getAmount()) >= 1) {
			BigDecimal accountBalanceFrom = checkbalance(transfer.getAccountFrom());
			BigDecimal accontBalanceTo = checkbalance(transfer.getAccountTo());
			BigDecimal transferAmount = transfer.getAmount();
			if(accountBalanceFrom.compareTo(transferAmount) < 1)
			{
				throw new InsufficientBalanceException("Insufficient Balance to transfer");
			}

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			Transfer ReceiverTransferData = entityManager.find(Transfer.class, transfer.getAccountFrom());
			BigDecimal setBalanceTo = accontBalanceTo.add(transferAmount);
			ReceiverTransferData.setAmount(setbalanceTo);

			Transfer SenderTransferData = entityManager.find(Transfer.class, transfer.getAccountFrom());
			BigDecimal setBalanceFrom = accountBalanceFrom.subtract(transferAmount);
			SenderTransferData.setAmount(setbalanceFrom);
			

		} else {
			return null;
		}
		return null;
		}
	}

	@Override
	public BigDecimal checkbalance(String accountId) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Account getAccountdata = entityManager.find(Account.class, accountId);
		BigDecimal balance = getAccountdata.balance;

		return balance;
	}

}
