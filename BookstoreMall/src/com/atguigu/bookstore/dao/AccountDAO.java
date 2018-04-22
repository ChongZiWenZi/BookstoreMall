package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.domain.Account;

 

public interface AccountDAO {

	/**
	 * 根据accountId获得相应的对象
	 * @param accountId
	 * @return
	 */
	public abstract Account get(Integer accountId);

	/**
	 * 根据传入的 accountId, amount 更新指定账户的余额: 扣除 amount 指定的钱数
	 * @param accountId
	 * @param amount
	 */
	public abstract void updateBalance(Integer accountId, float amount);
}