package com.razorpay.razorpay.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.RazorpayClient;
import com.razorpay.razorpay.modal.ApiResponce;
import com.razorpay.razorpay.service.PaymentService;




@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	
	
	@GetMapping("/generateOreder")
	public ResponseEntity<ApiResponce> generateOreder(){
//		return "AMAN";
		return new ResponseEntity<>(this.paymentService.generateOrder(), HttpStatus.OK);
	}
	

	@GetMapping("/getPaymentStatus/{id}")
	public ResponseEntity<ApiResponce> getPaymentStatus(@PathVariable("id") String paymentId) throws Exception{
//		return "AMAN";
		RazorpayClient razorpayClient =new RazorpayClient("rzp_test_4VizUVwMXY8gdG", "JBn0Wuz62YWekvR3etOldtyn");
		ApiResponce result=new ApiResponce<>() ;
		String data=razorpayClient.orders.fetch(paymentId).get("status");
		result.setData(data);
		JSONObject json= new JSONObject();
		json.put("order_id",paymentId);
		json.put("status","paid");
		System.out.println(razorpayClient.payments.fetchAll(json));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
