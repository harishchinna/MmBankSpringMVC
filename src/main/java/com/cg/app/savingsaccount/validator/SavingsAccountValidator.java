package com.cg.app.savingsaccount.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.cg.app.account.SavingsAccount;

@Component
public class SavingsAccountValidator {
	public boolean supports(Class<?> clazz) {

		return false;
	}

	public void validate(Object target, Errors errors) {
		SavingsAccount account = (SavingsAccount) target;
		if (account.getBankAccount().getAccountHolderName().length() < 2) {
			errors.rejectValue("bankAccount.accountHolderName", "accountHolderName.length", "Employee name must have 2 characters");
		}
		if (account.getBankAccount().getAccountBalance() < 10000) {
			errors.rejectValue("bankAccount.accountBalance", "accountBalance.minimum", "Salary must be higher than 10000");
		}
	}
}
