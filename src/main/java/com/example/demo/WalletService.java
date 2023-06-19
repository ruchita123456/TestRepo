package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletService {
	@Autowired
	private WalletRepository repos;
	@Autowired
	private WalletDAO dao;
	
	public Wallet[] customerWallets(int cus_id)
	{
		return dao.customerWallets(cus_id);
	}
	
	public Wallet cusWalletSearch(int cus_id,String wal_src)
	{
		return dao.cusWalletSearch(cus_id,wal_src);
	}
	public void insert(Wallet wal) {
		// TODO Auto-generated method stub
		 dao.addWallet(wal);
	}
}
