package com.example.microservice.packagingAndDelivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/packagingAndDelivery")
public class PackagingAndDeliveryController {
	
	@GetMapping("/costs")
	public ResponseEntity<?> getCostingData(){
		return null;
	}
}
