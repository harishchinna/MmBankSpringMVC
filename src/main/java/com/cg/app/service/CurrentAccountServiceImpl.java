package com.cg.app.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.CurrentAccount;
import com.cg.app.dao.CurrentAccountDAO;
import com.cg.app.dao.CurrentAccountDAOImpl;
import com.cg.app.factory.AccountFactory;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

public class CurrentAccountServiceImpl implements CurrentAccountService {
	private AccountFactory factory;
	private CurrentAccountDAO currentAccountDAO;

	public CurrentAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		currentAccountDAO = new CurrentAccountDAOImpl();
	}

	
	@Override
	public CurrentAccount createNewAccount(String accountHolderName,
			double accountBalance, double creditLimit)
			throws ClassNotFoundException, SQLException {
		CurrentAccount account = factory.createNewSavingsAccount(
				accountHolderName, accountBalance, creditLimit);
		currentAccountDAO.createNewAccount(account);
		return null;
	}

	@Override
	public boolean updateAccount(CurrentAccount account)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return currentAccountDAO.updateAccount(account);
	}

	@Override
	public CurrentAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		// TODO Auto-generated method stub
		return currentAccountDAO.getAccountByID(accountNumber);
	}

	@Override
	public CurrentAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		// TODO Auto-generated method stub
		return currentAccountDAO.deleteAccount(accountNumber);
	}

	@Override
	public List<CurrentAccount> getAllCurrentAccount()
			throws ClassNotFoundException, SQLException {
		return currentAccountDAO.getAllCurrentAccount();
	}

	@Override
	public void fundTransfer(CurrentAccount sender, CurrentAccount receiver,
			double amount) throws ClassNotFoundException, SQLException,
			InsufficientFundsException, InvalidInputException,
			AccountNotFoundException {
		withdraw(sender, amount);
		deposit(receiver, amount);
	}

	@Override
	public void deposit(CurrentAccount account, double amount)
			throws ClassNotFoundException, SQLException, InvalidInputException {
		if (amount > 0) {
			double currentBalance = account.getBankAccount()
					.getAccountBalance();
			currentBalance += amount;
			currentAccountDAO.updateBalance(account.getBankAccount()
					.getAccountNumber(), currentBalance);
		} else {
			throw new InvalidInputException("Invalid Input Amount!");
		}
	}

	@Override
	public void withdraw(CurrentAccount account, double amount)
			throws ClassNotFoundException, AccountNotFoundException,
			SQLException, InsufficientFundsException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		if (amount > (currentBalance + account
				.getCreditLimit())) {
			throw new InsufficientFundsException("Insufficient Funds");
		} else if (amount < 0) {
			throw new InvalidInputException("Invalid Amount");
		} else
		{
			currentBalance -= amount;
		System.out.println(""+currentBalance);
		currentAccountDAO.updateBalance(account.getBankAccount()
				.getAccountNumber(), currentBalance);
		}
	}

	@Override
	public List<CurrentAccount> sortByAccountHolderName()
			throws ClassNotFoundException, SQLException {
		return currentAccountDAO.sortByAccountHolderName();
	}

	@Override
	public List<CurrentAccount> sortByAccountHolderNameInDescendingOrder()
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return currentAccountDAO.sortByAccountHolderNameInDescendingOrder();
	}

	@Override
	public List<CurrentAccount> sortByAccountBalance()
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return currentAccountDAO.sortByAccountBalance();
	}

	@Override
	public List<CurrentAccount> sortByBalanceRange(int minimumBalance,
			int maximumBalance) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return currentAccountDAO.sortByBalanceRange(minimumBalance,
				maximumBalance);
	}

	@Override
	public List<CurrentAccount> sortByBalanceRangeInDescendingOrder(
			int minimumBalanceValue, int maximumBalanceValue)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return currentAccountDAO.sortByBalanceRangeInDescendingOrder(
				minimumBalanceValue, maximumBalanceValue);
	}

	@Override
	public List<CurrentAccount> searchAccountByHolderName(String holderName)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return currentAccountDAO.searchAccountByHolderName(holderName);
	}

	@Override
	public CurrentAccount searchAccount(int accountNumber)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return currentAccountDAO.searchAccount(accountNumber);
	}

	@Override
	public double checkAccountBalance(int balanceOfAccountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		// TODO Auto-generated method stub
		return currentAccountDAO.checkAccountBalance(balanceOfAccountNumber);
	}
}
