package com.razorpay.razorpay.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.razorpay.modal.ApiResponce;
import com.razorpay.razorpay.modal.PaymentResponse;

@Service
public class PaymentService {
	private static final String KEY="rzp_test_4VizUVwMXY8gdG";
	private static final String SECRETKEY="JBn0Wuz62YWekvR3etOldtyn";
	private static final String CURRENCY="USD";
	

	
	public ApiResponce generateOrder() {
		ApiResponce responce = new ApiResponce();
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("amount", 10000);
		jsonObject.put("currency", CURRENCY);
		
		try {
			RazorpayClient razorpayClient =new RazorpayClient(KEY, SECRETKEY);
			Order order= razorpayClient.orders.create(jsonObject);
			responce.setStatus(200);
			
			responce.setMessage("Order Created Successfully");
			int amount= order.get("amount");
			String orderId= order.get("id");
			System.out.println(razorpayClient.payments.fetch("pay_NIzpSnWEHPhUu3"));
			
		
			responce.setData(new PaymentResponse(amount,orderId,KEY));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responce.setStatus(400);
			responce.setMessage("Invalid Request");
//			responce.setData();
		}
		
		
		return responce;
	}

}
