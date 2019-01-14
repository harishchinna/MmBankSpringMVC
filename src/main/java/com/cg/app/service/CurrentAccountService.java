package com.cg.app.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.CurrentAccount;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

public interface CurrentAccountService {
	CurrentAccount createNewAccount(String accountHolderName,
			double accountBalance, double creditLimit)
			throws ClassNotFoundException, SQLException;

	boolean updateAccount(CurrentAccount account)
			throws ClassNotFoundException, SQLException;

	CurrentAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException;

	CurrentAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException;

	List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException,
			SQLException;

	void fundTransfer(CurrentAccount sender, CurrentAccount receiver,
			double amount) throws ClassNotFoundException, SQLException,
			InsufficientFundsException, InvalidInputException,
			AccountNotFoundException;

	void deposit(CurrentAccount account, double amount)
			throws ClassNotFoundException, SQLException, InvalidInputException;

	void withdraw(CurrentAccount account, double amount)
			throws ClassNotFoundException, SQLException,
			InsufficientFundsException, InvalidInputException,
			AccountNotFoundException;

	List<CurrentAccount> sortByAccountHolderName()
			throws ClassNotFoundException, SQLException;

	List<CurrentAccount> sortByAccountHolderNameInDescendingOrder()
			throws ClassNotFoundException, SQLException;

	List<CurrentAccount> sortByAccountBalance() throws ClassNotFoundException,
			SQLException;

	List<CurrentAccount> sortByBalanceRange(int minimumBalance,
			int maximumBalance) throws ClassNotFoundException, SQLException;

	List<CurrentAccount> sortByBalanceRangeInDescendingOrder(
			int minimumBalanceValue, int maximumBalanceValue)
			throws ClassNotFoundException, SQLException;

	List<CurrentAccount> searchAccountByHolderName(String holderName)
			throws ClassNotFoundException, SQLException;

	CurrentAccount searchAccount(int accountNumber)
			throws ClassNotFoundException, SQLException;

	double checkAccountBalance(int balanceOfAccountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException;

}
