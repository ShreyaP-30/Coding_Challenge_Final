package com.db.awmd.challenge.exception;

public class SameAccountIdException extends RuntimeException {

  
   private static final String ERROR_MSG_SAMEACCOUNT = "Cannot transfer from account itself : ";

    public static String getErrorMessage(String accountId) {
        return ERROR_MSG_SAMEACCOUNT + accountId;
    }

    public SameAccountTransferException(String accountId) {
        super(getErrorMessage(accountId));
    }
}