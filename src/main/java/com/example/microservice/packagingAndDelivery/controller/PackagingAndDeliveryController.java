package com.example.microservice.packagingAndDelivery.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.EventLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.packagingAndDelivery.PackagingAndDeliveryApplication;
import com.example.microservice.packagingAndDelivery.service.PackagingAndDeliveryService;

@RestController
@RequestMapping("v1/packagingAndDelivery")
public class PackagingAndDeliveryController {
	private static Logger logger = LoggerFactory.getLogger(PackagingAndDeliveryApplication.class);
	
	@Autowired
	PackagingAndDeliveryService packagingAndDeliveryService;
	
	@GetMapping(value="/getcost" , produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getCostingData(@RequestParam("type") String itemType , @RequestParam("count") int qty){
		Map<String , Object> responseBody = new HashMap<String , Object>();
		try {
			if(qty > 0) {
				logger.info("Call initiated from Controller to Service to get details");
				
				double[] response =  packagingAndDeliveryService.computePackagingAndDeliveryCost(itemType, qty);
				
				if(response[0] > 0 && response[1] > 0) {
					responseBody.put("packagingAndDeliveryCost", response[0]);
					responseBody.put("processingCost", response[1]);
					responseBody.put("errors", false);

					logger.info("Sent Success response with computed cost");
					return ResponseEntity.ok(responseBody);
					
				}else {
					responseBody.put("errors", true);
					logger.info("Sent response with conflict in data");
					return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
				}
				
			}else {
				responseBody.put("errors", true);
				logger.info("Sent response with conflict in data");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
			}
			
		}catch(Exception e) {
			responseBody.put("errors", true);
			responseBody.put("errorMsg", e.toString());
			
			logger.debug("An exception occured while executing " + e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
		}
		
	}
}
