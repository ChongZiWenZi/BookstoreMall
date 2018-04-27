package com.atguigu.bookstore.dao.imp;

import com.atguigu.bookstore.dao.AccountDAO;
import com.atguigu.bookstore.domain.Account;

public class AccountDAOIml extends BaseDAO<Account> implements AccountDAO {

	@Override
	public Account get(Integer accountId) {
		// TODO Auto-generated method stub
		String sqlString="SELECT accountId,balance FROM account WHERE accountId=?";
		return query(sqlString, accountId);
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		// TODO Auto-generated method stub
		String sqlString="UPDATE account SET balance = balance - ? WHERE accountid = ? ";
		update(sqlString, amount,accountId);
	}

}
