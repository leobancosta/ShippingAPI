package com.mynt.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mynt.exam.service.ShippingService;
import com.mynt.exam.web.dto.ShippingCostDTO;
import com.mynt.exam.web.dto.ShippingDetailsDTO;

@SpringBootTest
public class ShippingServiceTest {

	@Autowired
	private ShippingService shippingService;

	@Test
	@DisplayName("Test Applying Rule for Rejected Parcel")
	public void testFiringShippingRUle_RejectedParcel() {
		ShippingDetailsDTO shippingDTO = new  ShippingDetailsDTO();
		shippingDTO.setWeight(51.00f);
		shippingDTO.setHeight(12.00f);
		shippingDTO.setWidth(10.00f);
		shippingDTO.setLength(10.00f);
		shippingDTO.setVoucher("MYNT");
		
		ShippingCostDTO costDTO = shippingService.computeShippingCost(shippingDTO);
		
		DecimalFormat df2 = new DecimalFormat("#.##");
		BigDecimal bd = new BigDecimal(df2.format((double)costDTO.getShippingFee()));
		
		assertEquals(costDTO.getRuleName(), "REJECTED_PARCEL");
		assertEquals(bd.doubleValue(), 0.00, 0.0f);
	}
	
	@Test
	@DisplayName("Test Applying Rule for Heavy Parcel")
	public void testFiringShippingRUle_HeavyParcel() {
		ShippingDetailsDTO shippingDTO = new  ShippingDetailsDTO();
		shippingDTO.setWeight(20.22f);
		shippingDTO.setHeight(12.00f);
		shippingDTO.setWidth(10.00f);
		shippingDTO.setLength(10.00f);
		shippingDTO.setVoucher("MYNT");
		
		ShippingCostDTO costDTO = shippingService.computeShippingCost(shippingDTO);
		
		DecimalFormat df2 = new DecimalFormat("#.##");
		BigDecimal bd = new BigDecimal(df2.format((double)costDTO.getShippingFee()));
		
		assertEquals(costDTO.getRuleName(), "HEAVY_PARCEL");
		assertEquals(bd.doubleValue(), 404.4, 0.0f);
	}
	
	@Test
	@DisplayName("Test Applying Rule for Large Parcel")
	public void testFiringShippingRUle_LargeParcel() {

		ShippingDetailsDTO shippingDTO = new  ShippingDetailsDTO();
		shippingDTO.setWeight(6.00f);
		shippingDTO.setHeight(14.00f);
		shippingDTO.setWidth(15.45f);
		shippingDTO.setLength(15.00f);
		shippingDTO.setVoucher("MYNT");
		
		ShippingCostDTO costDTO = shippingService.computeShippingCost(shippingDTO);
		
		DecimalFormat df2 = new DecimalFormat("#.##");
		BigDecimal bd = new BigDecimal(df2.format((double)costDTO.getShippingFee()));
		
		assertEquals(costDTO.getRuleName(), "LARGE_PARCEL");
		assertEquals(bd.doubleValue(), 162.23, 0.0f);
	}

}
