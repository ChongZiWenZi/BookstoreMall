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
	 * ��ݴ���� accountId, amount ����ָ���˻������: �۳� amount ָ����Ǯ��
	 * @param accountId
	 * @param amount
	 */
	public abstract void updateBalance(Integer accountId, float amount);

}