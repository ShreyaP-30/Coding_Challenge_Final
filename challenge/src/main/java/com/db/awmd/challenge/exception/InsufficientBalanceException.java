package com.db.awmd.challenge.exception;

public class InsufficientBalanceException extends RuntimeException  {

    private static final String ERROR_MSG_INSUFFICIENTBALANCE = "Account balance is insufficient to perform transaction : (%s, %.2f, %.2f)";

    public static String getErrorMessage(String accountId, double balance, double amount) {
        return String.format(ERROR_MSG_INSUFFICIENTBALANCE, accountId, balance, amount);
    }

    public InsufficientBalanceException(String accountId, double balance, double amount) {
        super(getErrorMessage(accountId, balance, amount));
    }

}