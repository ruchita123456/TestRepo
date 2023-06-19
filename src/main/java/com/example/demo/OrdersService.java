package com.example.demo;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
	public class OrdersService {
	    @Autowired
	    private OrdersRepository repo;
	    @Autowired
	    private OrdersDAO dao1;
	    @Autowired 
	    private MenuDAO mdao;
	    @Autowired 
	    private CustomerDAO cdao;
	    
	    @Autowired 
	    private WalletDAO wdao;
	    
	    public List<Orders> showOrders() {
	        return repo.findAll();
	    }
	    
	    public Orders get(Integer ord_id) {
            return repo.findById(ord_id).get();
           
        }
	    public Orders[]  customerorder(Integer cus_id) {
	        return dao1.customerorder(cus_id);
	    }
	    public List<Orders> showCustomerPendingOrders(int cus_id) {
			return dao1.showCustomerPendingOrders(cus_id);
		}
	    public List<Orders> showVendorPendingOrders(int ven_id) {
			return dao1.showVendorPendingOrders(ven_id);
		}
		public List<Orders> showVendorOrders(int ven_id) {
			return dao1.showVendorOrders(ven_id);
		}
		public String placeOrder(Orders order) {
	        Menus menu = mdao.searchMenu(order.getMen_id());
	        Wallet wallet = wdao.cusWalletSearch(order.getCus_id(), order.getWal_src());
	        Customer cus=cdao.customersearchid(order.getCus_id());
	        double balance = wallet.getWal_amount();
	        double billAmount = order.getOrd_qt()*menu.getMen_price();
	        System.out.println(balance);
	        System.out.println(billAmount);
	        
	        if (balance-billAmount > 0) {
	        	int id=dao1.generateId();
	        	order.setOrd_id(id);
	            order.setOrd_status("PENDING");;
	            order.setOrd_billamt(order.getOrd_qt()*menu.getMen_price());
	            repo.save(order);
	            wdao.updateWallet(order.getCus_id(), order.getWal_src(), billAmount);
	            String msg="Order Placed Successfully...and Amount Debited,"
	            		+"\n Order id is:"+id
	            		+"\n\n\n thank you......";
	            MailAlert.sendmail(cus.getCus_email(),msg);

	            return "Order Placed Successfully...and Amount Debited";
	        }
	        return "Insufficient Funds...";
	    }
		
		public String acceptOrRejectOrder(int ord_id,int ven_id,String ord_status) {
			Orders orders = dao1.searchByOrderId(ord_id);
			 Customer cus=cdao.customersearchid(orders.getCus_id());
			int vid = orders.getVen_id();
			int cid = orders.getCus_id();
			String walType = orders.getWal_src();
			double billAmount = orders.getOrd_billamt();
			if (vid!=ven_id) {
				
				return "You are unauthorized vendor...";
			} 
			if (ord_status.toUpperCase().equals("YES")) {
				 String msg="Order Accepted,"
		            		+"\n\n\n thank you......";
		            MailAlert.sendmail(cus.getCus_email(),msg);
				return dao1.updateStatus(ord_id,"ACCEPTED");
			} else {
				dao1.updateStatus(ord_id, "DENIED");
				wdao.refundWallet(cid, walType, billAmount);
				 String msg="Order Rejected,"
		            		+"\n\n\n thank you......";
		            MailAlert.sendmail(cus.getCus_email(),msg);
				return "Order Rejected and Amount Refunded...";
			}
		}

	
	    
	}

	
