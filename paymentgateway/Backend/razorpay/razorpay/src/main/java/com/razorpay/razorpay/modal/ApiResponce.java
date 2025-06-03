package com.razorpay.razorpay.modal;

import java.io.Serializable;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponce<T> {
	private int status;
	private String message;
	private T data;
	

}
