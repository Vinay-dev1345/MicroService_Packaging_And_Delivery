package com.example.microservice.packagingAndDelivery.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.packagingAndDelivery.service.PackagingAndDeliveryService;

@RestController
@RequestMapping("v1/packagingAndDelivery")
public class PackagingAndDeliveryController {
	
	@Autowired
	PackagingAndDeliveryService packagingAndDeliveryService;
	
	@GetMapping("/getcost")
	public ResponseEntity<?> getCostingData(@RequestParam("type") String itemType , @RequestParam("count") int qty){
		Map<String , Object> responseBody = new HashMap<String , Object>();
		if(qty > 0) {
			double[] response =  packagingAndDeliveryService.computePackagingAndDeliveryCost(itemType, qty);
			responseBody.put("packagingAndDeliveryCost", response[0]);
			responseBody.put("processingCost", response[1]);
			responseBody.put("errors", false);
			return ResponseEntity.ok(responseBody);
		}else {
			responseBody.put("errors", true);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
		}
		
	}
}
