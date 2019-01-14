package com.cg.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.account.CurrentAccount;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.mapper.CurrentAccountDAOMapper;
import com.cg.app.mapper.SavingsAccountDAOMapper;
import com.cg.app.util.DBUtil;

@Repository
public class CurrentAccountDAOImpl implements CurrentAccountDAO {
	@Autowired
	private JdbcTemplate template;

	@Override
	public CurrentAccount createNewAccount(CurrentAccount account) throws SQLException, ClassNotFoundException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)");
		 * preparedStatement .setInt(1, account.getBankAccount().getAccountNumber());
		 * preparedStatement.setString(2, account.getBankAccount()
		 * .getAccountHolderName()); preparedStatement.setDouble(3,
		 * account.getBankAccount() .getAccountBalance());
		 * preparedStatement.setObject(4, false); preparedStatement.setDouble(5,
		 * account.getCreditLimit()); preparedStatement.setString(6, "CA");
		 * preparedStatement.executeUpdate(); preparedStatement.close();
		 * DBUtil.commit(); return account;
		 */
		template.update("INSERT INTO ACCOUNT(account_hn,account_bal,salary,od_limit,account_type) VALUES(?,?,?,?,?)",
				new Object[] { account.getBankAccount().getAccountHolderName(),
						account.getBankAccount().getAccountBalance(), null, account.getCreditLimit(), "CA" });

		return account;

	}

	@Override
	public List<CurrentAccount> getAllCurrentAccount() throws SQLException, ClassNotFoundException {
		/*
		 * List<CurrentAccount> currentAccounts = new ArrayList(); Connection connection
		 * = DBUtil.getConnection(); Statement statement = connection.createStatement();
		 * ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNT"); while
		 * (resultSet.next()) {// Check if row(s) is present in table int accountNumber
		 * = resultSet.getInt(1); String accountHolderName =
		 * resultSet.getString("account_hn"); double accountBalance =
		 * resultSet.getDouble(3); double creditLimit = resultSet.getDouble(5);
		 * CurrentAccount currentAccount = new CurrentAccount(accountNumber,
		 * accountHolderName, accountBalance, creditLimit);
		 * currentAccounts.add(currentAccount); System.out.println(""+currentAccounts);
		 * DBUtil.commit();
		 * 
		 * } return currentAccounts;
		 */
		return template.query("SELECT * FROM ACCOUNT", new CurrentAccountDAOMapper());
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setAutoCommit(false);
		/* System.out.println("" + currentBalance); */
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE ACCOUNT SET account_bal=? where account_id=?");
		preparedStatement.setDouble(1, currentBalance);
		preparedStatement.setInt(2, accountNumber);
		preparedStatement.executeUpdate();
		/* System.out.println("" + currentBalance); */
		DBUtil.commit();

	}

	@Override
	public boolean updateAccount(CurrentAccount account) throws ClassNotFoundException, SQLException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("UPDATE ACCOUNT SET account_hn=? where account_id=?");
		 * preparedStatement.setString(1, account.getBankAccount()
		 * .getAccountHolderName()); preparedStatement .setInt(2,
		 * account.getBankAccount().getAccountNumber()); int count =
		 * preparedStatement.executeUpdate(); boolean result = false; if (count != 0) {
		 * result = true; } DBUtil.commit();
		 * 
		 * return result;
		 */
		template.update("UPDATE account SET account_hn=?,salary=? WHERE account_id=?",
				new Object[] { account.getBankAccount().getAccountHolderName(), account.getCreditLimit(),
						account.getBankAccount().getAccountNumber() });

		return false;
	}

	@Override
	public CurrentAccount getAccountByID(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("SELECT * FROM account where account_id=?");
		 * preparedStatement.setInt(1, accountNumber); ResultSet resultSet =
		 * preparedStatement.executeQuery(); CurrentAccount currentAccount = null; if
		 * (resultSet.next()) { String accountHolderName =
		 * resultSet.getString("account_hn"); double accountBalance =
		 * resultSet.getDouble(3); double creditLimit = resultSet.getDouble("od_limit");
		 * currentAccount = new CurrentAccount(accountHolderName, accountBalance,
		 * creditLimit); return currentAccount; } throw new
		 * AccountNotFoundException("Account with account number " + accountNumber +
		 * " does not exist.");
		 */
		return template.queryForObject("SELECT * FROM account where account_id=?", new Object[] { accountNumber },
				new CurrentAccountDAOMapper());
	}

	@Override
	public CurrentAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("DELETE FROM account WHERE account_id=?");
		 * preparedStatement.setInt(1, accountNumber); preparedStatement.execute();
		 * DBUtil.commit(); return null;
		 */
		template.update("DELETE  FROM account WHERE account_id=?", new Object[] { accountNumber });
		return null;
	}

	
	@Override
	public double checkAccountBalance(int balanceOfAccountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("SELECT account_bal FROM account WHERE account_id=?");
		 * preparedStatement.setInt(1, balanceOfAccountNumber); ResultSet resultSet =
		 * preparedStatement.executeQuery();
		 * 
		 * if (resultSet.next()) { double accountBalance = resultSet.getDouble(1);
		 * 
		 * return accountBalance; } return 0; }
		 */
		double balance = template.queryForObject("SELECT account_bal FROM account WHERE account_id=?",
				new Object[] { balanceOfAccountNumber }, Double.class);
		return balance;
	}

	@Override
	public CurrentAccount searchAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection
		 * .prepareStatement("SELECT * FROM account WHERE account_id=?");
		 * preparedStatement.setInt(1, accountNumber); ResultSet resultSet =
		 * preparedStatement.executeQuery(); CurrentAccount currentAccount = null; if
		 * (resultSet.next()) { String accountHolderName =
		 * resultSet.getString("account_hn"); double accountBalance =
		 * resultSet.getDouble(3); double creditLimit = resultSet.getDouble("od_limit");
		 * currentAccount = new CurrentAccount(accountNumber, accountHolderName,
		 * accountBalance, creditLimit); return currentAccount; } DBUtil.commit();
		 * return null;
		 */
		return template.queryForObject("SELECT * FROM account WHERE account_id=?", new Object[] { accountNumber },
				new CurrentAccountDAOMapper());
	}

	@Override
	public List<CurrentAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException {
		List<CurrentAccount> currentAccount = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM account ORDER BY account_hn");
		while (resultSet.next()) {
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			double creditLimit = resultSet.getDouble("od_limit");
			CurrentAccount currentAccountList = new CurrentAccount(accountNumber, accountHolderName, accountBalance,
					creditLimit);
			currentAccount.add(currentAccountList);
		}
		DBUtil.commit();
		return currentAccount;
	}

	@Override
	public List<CurrentAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException {
		List<CurrentAccount> currentAccountList = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM account ORDER BY account_hn DESC");
		while (resultSet.next()) {
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			double creditLimit = resultSet.getDouble("od_limit");
			CurrentAccount currentAccount = new CurrentAccount(accountNumber, accountHolderName, accountBalance,
					creditLimit);
			currentAccountList.add(currentAccount);
		}
		DBUtil.commit();
		return currentAccountList;
	}

	@Override
	public List<CurrentAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException {
		List<CurrentAccount> currentAccountList = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM account ORDER BY account_bal");
		while (resultSet.next()) {
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			double creditLimit = resultSet.getDouble("od_limit");
			CurrentAccount currentAccount = new CurrentAccount(accountNumber, accountHolderName, accountBalance,
					creditLimit);
			currentAccountList.add(currentAccount);
		}
		return currentAccountList;
	}

	@Override
	public List<CurrentAccount> sortByBalanceRange(int minimumBalance, int maximumBalance)
			throws ClassNotFoundException, SQLException {
		List<CurrentAccount> currentAccountList = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatementQuery = connection
				.prepareStatement("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal");
		preparedStatementQuery.setDouble(1, minimumBalance);
		preparedStatementQuery.setDouble(2, maximumBalance);
		ResultSet resultSet = preparedStatementQuery.executeQuery();
		while (resultSet.next()) {
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			double creditLimit = resultSet.getDouble("od_limit");
			CurrentAccount currentAccount = new CurrentAccount(accountNumber, accountHolderName, accountBalance,
					creditLimit);
			currentAccountList.add(currentAccount);
		}
		return currentAccountList;
	}

	@Override
	public List<CurrentAccount> sortByBalanceRangeInDescendingOrder(int minimumBalanceValue, int maximumBalanceValue)
			throws ClassNotFoundException, SQLException {
		List<CurrentAccount> currentAccountList = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatementQuery = connection
				.prepareStatement("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal DESC");
		preparedStatementQuery.setDouble(1, minimumBalanceValue);
		preparedStatementQuery.setDouble(2, maximumBalanceValue);
		ResultSet resultSet = preparedStatementQuery.executeQuery();
		while (resultSet.next()) {
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			double creditLimit = resultSet.getDouble("od_limit");
			CurrentAccount currentAccount = new CurrentAccount(accountNumber, accountHolderName, accountBalance,
					creditLimit);
			currentAccountList.add(currentAccount);
		}
		DBUtil.commit();
		return currentAccountList;
	}

	@Override
	public List<CurrentAccount> searchAccountByHolderName(String holderName)
			throws ClassNotFoundException, SQLException {
		List<CurrentAccount> currentAccounts = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE account_hn=?");
		preparedStatement.setString(1, holderName);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			double odLimit = resultSet.getDouble("od_limit");
			CurrentAccount currentAccount = new CurrentAccount(accountNumber, accountHolderName, accountBalance,
					odLimit);
			currentAccounts.add(currentAccount);

		}
		DBUtil.commit();
		return currentAccounts;
	}

}
