package com.cg.app.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.savingsaccount.validator.SavingsAccountValidator;
import com.cg.app.service.SavingsAccountService;

@Controller
public class HomeController {
	@Autowired
	SavingsAccountService savingsAccountService;

	@Autowired
	private SavingsAccountValidator validator;

	private boolean flag = false;

	@RequestMapping("/home")
	public String hellocontroller() {
		return "index";
	}

	@RequestMapping("/getAll")
	public String getAllSavingsAccount(Model model) throws ClassNotFoundException, SQLException {
		List<SavingsAccount> account = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("account", account);
		return "AccountDetailsForm";

	}

	@RequestMapping("/addNewAccount")
	public String addNewAccount(Model model, Map map) {
		map.put("account", new SavingsAccount());
		/* model.addAttribute("account", new SavingsAccount()); */
		return "AddNewAccountForm";
	}

	@RequestMapping("/createNewAccount")
	public String createNewAccount(@ModelAttribute("account") SavingsAccount account,
			BindingResult result) throws ClassNotFoundException, SQLException {
		validator.validate(account, result);
		if (result.hasErrors()) {
			return "AddNewAccountForm";
		}
		savingsAccountService.createNewAccount(account.getBankAccount().getAccountHolderName(),
				account.getBankAccount().getAccountBalance(), account.isSalary());
		return "redirect:getAll";
	}

	@RequestMapping("/updateAccount")
	public String updateAccount() {
		return "UpdateForm";
	}

	@RequestMapping("/updateForm")
	public String updateAccount(@RequestParam("accountNumber") int accountNumber, Model model,Map map)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		map.put("account",account);
		/* model.addAttribute("account", account); */
		return "UpdateDetailsForm";

	}

	@RequestMapping("/updateAccountDetails")
	public String updateAccountDetails(@ModelAttribute("account") SavingsAccount account,BindingResult result)
			throws ClassNotFoundException, SQLException {
		/* System.out.println(account); */
		validator.validate(account, result);
		if (result.hasErrors()) {
			return "UpdateDetailsForm";
		}
		savingsAccountService.updateAccount(account);
		return "redirect:getAll";

	}

	@RequestMapping("/closeAccount")
	public String removeAccount() {
		return "CloseAccount";

	}

	@RequestMapping("/closeAccountForm")
	public String deleteAccount(@RequestParam("accountId") int accountId)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		savingsAccountService.deleteAccount(accountId);
		return "redirect:getAll";

	}

	@RequestMapping("/getCurrentBalance")
	public String checkCurrentBalance() {
		return "CurrentBalanceForm";

	}

	@RequestMapping("/Balance")
	public String gettingCurrentBalance(@RequestParam("accountNumber") int accountNumber, Model model)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		double balance = savingsAccountService.checkAccountBalance(accountNumber);
		model.addAttribute("bal", balance);
		return "Balance";
	}

	@RequestMapping("/searchAccount")
	public String searchExistingAccount() {
		return "SearchAccountForm";
	}

	@RequestMapping("/search")
	public String accountExistence(@RequestParam("accountnumber") int accountnumber, Model model)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountnumber);
		model.addAttribute("account", account);
		return "SingleAccountDetailsForm";
	}

	@RequestMapping("/withdraw")
	public String withdrawAmount() {
		return "WithdrawForm";
	}

	@RequestMapping("/withdrawamount")
	public String withdrawingAmount(@RequestParam("accountnumber") int accountnumber,
			@RequestParam("amount") double amount)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountnumber);
		savingsAccountService.withdraw(account, amount);
		return "redirect:home";
	}

	@RequestMapping("/deposit")
	public String depositAmount() {
		return "DepositForm";
	}

	@RequestMapping("/depositAmount")
	public String depositAmountToAccount(@RequestParam("accountnumber") int accountnumber,
			@RequestParam("amount") double amount)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountnumber);
		savingsAccountService.deposit(account, amount);
		return "redirect:home";
	}

	@RequestMapping("/fundTransfer")
	public String transferAmount() {
		return "FundTransferForm";
	}

	@RequestMapping("/transfer")
	public String transferingAmount(@RequestParam("accountnumberofwithdraw") int accountnumberofwithdraw,
			@RequestParam("accountnumberofdeposit") int accountnumberofdeposit, @RequestParam("amount") double amount)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountnumberofwithdraw);
		SavingsAccount account2 = savingsAccountService.getAccountById(accountnumberofdeposit);
		savingsAccountService.fundTransfer(account, account2, amount);
		return "redirect:home";
	}

	@RequestMapping("/sortByName")
	public String sortByName(Model model) throws ClassNotFoundException, SQLException {
		flag = !flag;

		Collection<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
		List<SavingsAccount> accountSet = new ArrayList<>(accounts);
		Collections.sort(accountSet, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount arg0, SavingsAccount arg1) {
				int result = arg0.getBankAccount().getAccountHolderName()
						.compareTo(arg1.getBankAccount().getAccountHolderName());
				if (flag == true) {
					return result;
				}
				return -result;
			}
		});

		model.addAttribute("account", accountSet);
		return "AccountDetailsForm";
	}

	@RequestMapping("/sortByBalance")
	public String sortByBalance(Model model) throws ClassNotFoundException, SQLException {
		flag = !flag;

		Collection<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
		List<SavingsAccount> accountSet = new ArrayList<>(accounts);
		Collections.sort(accountSet, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount arg0, SavingsAccount arg1) {
				int result = (int) (arg0.getBankAccount().getAccountBalance()
						- (arg1.getBankAccount().getAccountBalance()));
				if (flag == true) {
					return result;
				}
				return -result;
			}
		});

		model.addAttribute("account", accountSet);
		return "AccountDetailsForm";
	}
	@RequestMapping("/sortBySalaryType")
	public String sortBySalaryType(Model model) throws ClassNotFoundException, SQLException
	{
		flag = !flag;
	
			Collection<SavingsAccount> accounts = savingsAccountService
					.getAllSavingsAccount();
			List<SavingsAccount> accountSet = new ArrayList<>(accounts);
			Collections.sort(accountSet, new Comparator<SavingsAccount>() {
				@Override
				public int compare(SavingsAccount arg0, SavingsAccount arg1) {
					int result = Boolean.compare(arg0.isSalary(),
							arg1.isSalary());
					if (flag == true) {
						return result;
					}
					return -result;
				}
			});

			model.addAttribute("account", accountSet);
			return "AccountDetailsForm";
	
}
}