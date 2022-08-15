package com.example.microservice.packagingAndDelivery;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.microservice.packagingAndDelivery.Repository.PackagingAndDeliveryRepository;
import com.example.microservice.packagingAndDelivery.entity.PackagingAndDeliveryCosting;
import com.example.microservice.packagingAndDelivery.service.PackagingAndDeliveryService;

import com.example.microservice.packagingAndDelivery.CostData;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PackagingAndDeliveryApplicationTests {
	
	@Mock
	private PackagingAndDeliveryRepository packagingAndDeliveryRepository;
	
	@InjectMocks
	private PackagingAndDeliveryService packagingAndDeliveryService;
	
	@Test
	public void CostComputeForIntegralPartsSingleQuantity() {
		List<PackagingAndDeliveryCosting> costData = Stream.of(new PackagingAndDeliveryCosting("1", "integralItemPackaging","100"),
				new PackagingAndDeliveryCosting("2", "protectiveSheath","50"), new PackagingAndDeliveryCosting("1", "integralItemDelivery","200"),
				new PackagingAndDeliveryCosting("4", "integralItemProcessing","500")).collect(Collectors.toList());
		
		double[] expectedValue = {350.00, 500.00};
		when(packagingAndDeliveryRepository.findAll()).thenReturn(costData);
		double[] computedCostData = packagingAndDeliveryService.computePackagingAndDeliveryCost("Integral", 1);
		Assertions.assertArrayEquals(expectedValue, computedCostData);
				
	}
	
	@Test
	public void CostComputeForIntegralPartsMultipleQuantity() {
		List<PackagingAndDeliveryCosting> costData = Stream.of(new PackagingAndDeliveryCosting("1", "integralItemPackaging","100"),
				new PackagingAndDeliveryCosting("2", "protectiveSheath","50"), new PackagingAndDeliveryCosting("1", "integralItemDelivery","200"),
				new PackagingAndDeliveryCosting("4", "integralItemProcessing","500")).collect(Collectors.toList());
		
		double[] expectedValue = {1050.00, 1500.00};
		when(packagingAndDeliveryRepository.findAll()).thenReturn(costData);
		double[] computedCostData = packagingAndDeliveryService.computePackagingAndDeliveryCost("Integral", 3);
		Assertions.assertArrayEquals(expectedValue, computedCostData);
					
	}
	
	@Test
	public void CostComputeForAccessoryPartsSingleQuantity() {
		List<PackagingAndDeliveryCosting> costData = Stream.of(new PackagingAndDeliveryCosting("1", "accessoryItemPackaging","50"),
				new PackagingAndDeliveryCosting("2", "protectiveSheath","50"), new PackagingAndDeliveryCosting("1", "accessoryItemDelivery","100"),
				new PackagingAndDeliveryCosting("4", "accessoryItemProcessing","300")).collect(Collectors.toList());
		
		double[] expectedValue = {200.00, 300.00};
		when(packagingAndDeliveryRepository.findAll()).thenReturn(costData);
		double[] computedCostData = packagingAndDeliveryService.computePackagingAndDeliveryCost("Accessory", 1);
		Assertions.assertArrayEquals(expectedValue, computedCostData);
					
	}
	
	@Test
	public void CostComputeForAccessoryPartsMultipleQuantity() {
		List<PackagingAndDeliveryCosting> costData = Stream.of(new PackagingAndDeliveryCosting("1", "accessoryItemPackaging","50"),
				new PackagingAndDeliveryCosting("2", "protectiveSheath","50"), new PackagingAndDeliveryCosting("1", "accessoryItemDelivery","100"),
				new PackagingAndDeliveryCosting("4", "accessoryItemProcessing","300")).collect(Collectors.toList());
		
		double[] expectedValue = {1000.00, 1500.00};
		when(packagingAndDeliveryRepository.findAll()).thenReturn(costData);
		double[] computedCostData = packagingAndDeliveryService.computePackagingAndDeliveryCost("Accessory", 5);
		Assertions.assertArrayEquals(expectedValue, computedCostData);			
	}
	
	@Test
	public void CostComputeForWhenTypeIsNeitherAccessoryNorIntegral() {
		List<PackagingAndDeliveryCosting> costData = Stream.of(new PackagingAndDeliveryCosting("1", "integralItemPackaging","100"),
				new PackagingAndDeliveryCosting("2", "protectiveSheath","50"), new PackagingAndDeliveryCosting("1", "integralItemDelivery","200"),
				new PackagingAndDeliveryCosting("4", "integralItemProcessing","500")).collect(Collectors.toList());
		
		double[] expectedValue = {0.00, 0.00};
		when(packagingAndDeliveryRepository.findAll()).thenReturn(costData);
		double[] computedCostData = packagingAndDeliveryService.computePackagingAndDeliveryCost("undefined", 5);
		Assertions.assertArrayEquals(expectedValue, computedCostData);			
	}
	
	@Test
	public void priceDataLoadCheck() {
		List<PackagingAndDeliveryCosting> costData = Stream.of(new PackagingAndDeliveryCosting("1", "integralItemPackaging","100"),
				new PackagingAndDeliveryCosting("2", "protectiveSheath","50")).collect(Collectors.toList());
		
		Map<String , String> expectedValue = new HashMap<String, String>();
		expectedValue.put("integralItemPackaging", "100");
		expectedValue.put("protectiveSheath", "50");
		
		when(packagingAndDeliveryRepository.findAll()).thenReturn(costData);
		Map<String, String > computedCostData = packagingAndDeliveryService.loadPriceData();
		Assertions.assertEquals(expectedValue , computedCostData);
					
	}

}
