package com.example.microservice.packagingAndDelivery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.packagingAndDelivery.PackagingAndDeliveryApplication;
import com.example.microservice.packagingAndDelivery.Repository.PackagingAndDeliveryRepository;
import com.example.microservice.packagingAndDelivery.entity.PackagingAndDeliveryCosting;

@Service
public class PackagingAndDeliveryService {
	
	private static Logger logger = LoggerFactory.getLogger(PackagingAndDeliveryApplication.class);
	public static final String IIP = "integralItemPackaging";
	public static final String AIP = "accessoryItemPackaging";
	public static final String PS = "protectiveSheath";
	public static final String IID = "integralItemDelivery";
	public static final String AID = "accessoryItemDelivery";
	public static final String IIPR = "integralItemProcessing";
	public static final String AIPR = "accessoryItemProcessing";
	
	@Autowired
	PackagingAndDeliveryRepository packagingAndDeliveryRepository;
	
	public Map<String , String> loadPriceData(){
		List<PackagingAndDeliveryCosting> costData = packagingAndDeliveryRepository.findAll();
		
		Map<String , String > mappedCostData = new HashMap<String , String>();
		
		for(int index = 0 ; index<costData.size() ; index++) {
			PackagingAndDeliveryCosting pd = costData.get(index);
			mappedCostData.put(pd.getCostType(), pd.getPrice());
		}
		
		logger.info("Successfully Extracted packaging and delivery cost data from database");
		return mappedCostData;
	}
	
	public double[] computePackagingAndDeliveryCost(String type , int count) {
		double[] finalCost = new double[2];
		Map<String, String>loadCostData = loadPriceData();
		
		try{
			if(type.equalsIgnoreCase("Integral")) {
				double pCost = Double.parseDouble(loadCostData.get(IIP)) + Double.parseDouble(loadCostData.get(PS));
				double dCost = Double.parseDouble(loadCostData.get(IID));
				double defProCost = Double.parseDouble(loadCostData.get(IIPR));
				finalCost[0] = (pCost + dCost) * count;
				finalCost[1] = defProCost * count;
				logger.info("Cost Compute was successfull for an Integral component." );
				logger.info("Cost Computed for packaging : " + finalCost[0] );
				logger.info("Cost Computed for delivery : " + finalCost[1] );
				}

			else if(type.equalsIgnoreCase("Accessory")) {
				double pCost = Double.parseDouble(loadCostData.get(AIP)) + Double.parseDouble(loadCostData.get(PS));
				double dCost = Double.parseDouble(loadCostData.get(AID));
				double defProCost = Double.parseDouble(loadCostData.get(AIPR));
				finalCost[0] = (pCost + dCost) * count;
				finalCost[1] = defProCost * count;
				logger.info("Cost Compute was successfull for an Accessory component." );
				logger.info("Cost Computed for packaging : " + finalCost[0] );
				logger.info("Cost Computed for delivery : " + finalCost[1] );
				}
			
			else {
				logger.warn("Component Type " + type + " not found. ");
			}
			}catch(Exception e) {
				logger.debug("Error occured while computing cost with error message : "+e.toString());
			}
		return finalCost;
	}
}
