package com.cg.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.CurrentAccount;
import com.cg.app.exception.AccountNotFoundException;

public interface CurrentAccountDAO {
	CurrentAccount createNewAccount(CurrentAccount account) throws SQLException, ClassNotFoundException;

	boolean updateAccount(CurrentAccount account) throws ClassNotFoundException, SQLException;

	CurrentAccount getAccountByID(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;

	CurrentAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;

	List<CurrentAccount> getAllCurrentAccount() throws SQLException, ClassNotFoundException;

	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;

	List<CurrentAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException;

	List<CurrentAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException;

	List<CurrentAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException;

	List<CurrentAccount> sortByBalanceRange(int minimumBalance, int maximumBalance)
			throws ClassNotFoundException, SQLException;

	List<CurrentAccount> sortByBalanceRangeInDescendingOrder(int minimumBalanceValue, int maximumBalanceValue)
			throws ClassNotFoundException, SQLException;

	List<CurrentAccount> searchAccountByHolderName(String holderName) throws ClassNotFoundException, SQLException;

	CurrentAccount searchAccount(int accountNumber) throws ClassNotFoundException, SQLException;

	double checkAccountBalance(int balanceOfAccountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;

}
