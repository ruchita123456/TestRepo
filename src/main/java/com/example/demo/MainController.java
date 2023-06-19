package com.example.demo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@Autowired
	private VendorService vendorservice;
	@Autowired
	private CustomerService customerservice;
	@Autowired
	private MenusService menusservice;
	@Autowired
    private OrdersService ordersservice;
	@Autowired
	private WalletService walletservice;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/")
	public String hello() {
	    return "hello";
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/vshow")
	public List<Vendor> vendorlist() {
	    return vendorservice.showall();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	 @RequestMapping(value="/showorders")
	    public List<Orders> list() {
	        return ordersservice.showOrders();
	    }
	@CrossOrigin(origins = "http://localhost:4200")
	 
	 @GetMapping("/Orders/{id}")
	    public ResponseEntity<Orders> get(@PathVariable Integer id) {
	        try {
	            Orders Orders = ordersservice.get(id);
	            return new ResponseEntity<Orders>(Orders, HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
	        }      
	    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/vendorAuthenticate/{user}/{pwd}")
    public String autneticateion(@PathVariable String user, @PathVariable String pwd) {
        return vendorservice.authenticate(user, pwd);
    }
	@GetMapping("/customerAuthenticate/{user}/{pwd}")
    public String autneticateion1(@PathVariable String user, @PathVariable String pwd) {
        return customerservice.authenticate(user, pwd);
    }
	@CrossOrigin(origins = "http://localhost:4200")
	   @RequestMapping("/showonecustomer/{user}")
	   public Customer showonecustomer(@PathVariable String user) {
	       return customerservice.showonecustomer(user);
	   }
	@CrossOrigin(origins = "http://localhost:4200")
	   @RequestMapping("/customerorder/{cus_id}")
	   public Orders[] customerorder(@PathVariable Integer cus_id) {
	       return ordersservice.customerorder(cus_id);
	   }
	@CrossOrigin(origins = "http://localhost:4200")

	@RequestMapping(value="/customerPendingOrders/{cus_id}")
	public List<Orders> customerPendingOrders(@PathVariable int cus_id) {
		return ordersservice.showCustomerPendingOrders(cus_id);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/vsearch/{ven_id}")
    public ResponseEntity<Vendor> vendorget(@PathVariable Integer ven_id) 
	{
        try 
        {
        	Vendor vendor = vendorservice.search(ven_id);
            return new ResponseEntity<Vendor>(vendor, HttpStatus.OK);
        } 
        catch (NoSuchElementException e) 
        {
            return new ResponseEntity<Vendor>(HttpStatus.NOT_FOUND);
        }      
    }
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/vendorPendingOrders/{ven_id}")
	public List<Orders> vendorPendingOrders(@PathVariable int ven_id) {
		return ordersservice.showVendorPendingOrders(ven_id);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/vendorOrders/{ven_id}")
	public List<Orders> vendorOrders(@PathVariable int ven_id) {
		return ordersservice.showVendorOrders(ven_id);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/cshow")
	   public List<Customer> customerlist() {
	       return customerservice.showCustomer();
	   }
	@CrossOrigin(origins = "http://localhost:4200")
	   @GetMapping("/csearch/{cus_id}")
	   public ResponseEntity<Customer> customerget(@PathVariable Integer cus_id) {
	       try {
	           Customer customer = customerservice.get(cus_id);
	           return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	       } catch (NoSuchElementException e) {
	           return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	       }      
	   }
	   
	@CrossOrigin(origins = "http://localhost:4200")
	   @GetMapping(value="/mshow")
	   public List<Menus> menuslist() {
	       return menusservice.showall();
	   }
	@CrossOrigin(origins = "http://localhost:4200")
	   @GetMapping("/msearch/{men_id}")
	   public ResponseEntity<Menus> menusget(@PathVariable Integer men_id) {
	       try {
	           Menus menu = menusservice.search(men_id);
	           return new ResponseEntity<Menus>(menu, HttpStatus.OK);
	       } catch (NoSuchElementException e) {
	           return new ResponseEntity<Menus>(HttpStatus.NOT_FOUND);
	       }      
	   }
	@CrossOrigin(origins = "http://localhost:4200")
	   @RequestMapping("/showonevendor/{user}")
	   public Vendor showonevendor(@PathVariable String user) {
	       return vendorservice.showonevendor(user);
	   }

	@CrossOrigin(origins="http://localhost:4200")
	   @GetMapping("/cwallets/{cus_id}")
	    public Wallet[] cusWallets(@PathVariable Integer cus_id) {
	          return walletservice.customerWallets(cus_id);
	    }
	
	
	@CrossOrigin(origins="http://localhost:4200")
	   @GetMapping("/cwalletsearch/{cus_id}/{wal_src}")
	    public Wallet cusWalletSearch(@PathVariable Integer cus_id,@PathVariable String wal_src) {
	          return walletservice.cusWalletSearch(cus_id,wal_src);
	    }
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/placeOrder")
    public String add(@RequestBody Orders orders) {
        return ordersservice.placeOrder(orders);
    }
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/madd")
	public void addMenu(@RequestBody Menus men) {
		menusservice.insert(men);
	}
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/cadd")
	public void addCustomer(@RequestBody Customer cus) {
		customerservice.insert(cus);
	}

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/vadd")
	public void addVendor(@RequestBody Vendor ven) {
		vendorservice.insert(ven);
	}
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/acceptOrRejectOrder/{ordId}/{venId}/{status}")
	public String acceptOrReject(@PathVariable int ordId,@PathVariable int venId, 
			@PathVariable String status) {
		return ordersservice.acceptOrRejectOrder(ordId, venId, status);
	}
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/wadd")
	public void addWallet(@RequestBody Wallet wal) {
		walletservice.insert(wal);
	}
	
	
	
	

}
