package com.cg.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cg.app.account.CurrentAccount;

public class CurrentAccountDAOMapper implements RowMapper<CurrentAccount> {

	@Override
	public CurrentAccount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		int accountNumber = resultSet.getInt(1);
		String accountHolderName = resultSet.getString("account_hn");
		double accountBalance = resultSet.getDouble(3);
		boolean salary = resultSet.getInt("salary") == 1 ? true : false;
		double creditLimit = resultSet.getDouble(5);
		resultSet.getString(6);

		CurrentAccount currentAccount = new CurrentAccount(accountNumber, accountHolderName, accountBalance,
				creditLimit);

		return currentAccount;
	}

}
