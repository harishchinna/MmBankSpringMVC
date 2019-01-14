package com.cg.app.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

@Aspect
@Component
public class SavingsAccountServiceImplValidations {
	Logger logger = Logger.getLogger(SavingsAccountLoggers.class.getName());

	@Around("execution(* com.cg.app.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		double amount = (Double) param[1];
		SavingsAccount balance = (SavingsAccount) param[0];
		double currentBalance = balance.getBankAccount().getAccountBalance();

		if (amount > 0 && amount <= currentBalance) {
			logger.info("Before - starting withdraw method");
			pjp.proceed();
			logger.info("After - withdrawn sucessfully");
		} else
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");

	}
	@AfterThrowing(pointcut = ("execution(* com.cg.app.service.*.*(..))"), throwing = "exe")
	public void log2(Exception exe) {
		logger.info("Exception is:" + exe.toString());
	}

	@Around("execution(* com.cg.app.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		double amount = (Double) param[1];
		if (amount > 0) {
			pjp.proceed();
		} else
			throw new InvalidInputException("Invalid Input Amount!");
	}
}
