package com.example.microservice.packagingAndDelivery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.packagingAndDelivery.Repository.PackagingAndDeliveryRepository;
import com.example.microservice.packagingAndDelivery.entity.PackagingAndDeliveryCosting;

@Service
public class PackagingAndDeliveryService {
	
	public static final String IIP = "integralItemPackaging";
	public static final String AIP = "accessoryItemPackaging";
	public static final String PS = "protectiveSheath";
	public static final String IID = "integralItemDelivery";
	public static final String AID = "accessoryItemDelivery";
	
	@Autowired
	PackagingAndDeliveryRepository packagingAndDeliveryRepository;
	
	public Map<String , String> loadPriceData(){
		List<PackagingAndDeliveryCosting> costData = packagingAndDeliveryRepository.findAll();
		Map<String , String > mappedCostData = new HashMap<String , String>();
		
		for(int index = 0 ; index<costData.size() ; index++) {
			PackagingAndDeliveryCosting pd = costData.get(index);
			mappedCostData.put(pd.getCostType(), pd.getPrice());
		}
		System.out.println(mappedCostData);
		return mappedCostData;
	}
	
	public double computePackagingAndDeliveryCost(String type , int count) {
		double finalCost = -1;
		Map<String, String>loadCostData = loadPriceData();
		
		try{
			if(type.equalsIgnoreCase("Integral")) {
				double pCost = Double.parseDouble(loadCostData.get(IIP)) + Double.parseDouble(loadCostData.get(PS));
				double dCost = Double.parseDouble(loadCostData.get(IID));
				
				finalCost = (pCost + dCost) * count;
				}

			if(type.equalsIgnoreCase("Accessory")) {
				double pCost = Double.parseDouble(loadCostData.get(AIP)) + Double.parseDouble(loadCostData.get(PS));
				double dCost = Double.parseDouble(loadCostData.get(AID));
				
				finalCost = (pCost + dCost) * count;
				}
			
			}catch(Exception e) {
				System.out.println(e.toString());
			}
		return finalCost;
	}
}
