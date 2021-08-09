package com.mynt.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mynt.exam.service.VoucherService;
import com.mynt.exam.web.dto.ShippingDetailsDTO;
import com.mynt.exam.web.dto.VoucherItemDTO;

@SpringBootTest
public class VoucherServiceTest {

    @Autowired
	private VoucherService voucherService;
	
	@Test
	public void whenInvalidVoucher_catchExceptionAndSetMessageResponse() {
		
        ShippingDetailsDTO shippingDTO = new ShippingDetailsDTO();
		shippingDTO.setVoucher("jjkui");	
		
		VoucherItemDTO voucherRespDTO = voucherService.getVoucherValue(shippingDTO);
		DecimalFormat df2 = new DecimalFormat("#.##");
		BigDecimal bd = new BigDecimal(df2.format((double)voucherRespDTO.getDiscount()));
		
		assertEquals(voucherRespDTO.getError(), "Invalid voucher code jjkui");
		assertEquals(bd.doubleValue(), 0.0, 0.0f);
	}
	
	@Test
	public void whenValidVoucher_expectOkResponse() {
		
        ShippingDetailsDTO shippingDTO = new ShippingDetailsDTO();
		shippingDTO.setVoucher("MYNT");	
		
		VoucherItemDTO voucherRespDTO = voucherService.getVoucherValue(shippingDTO);
		DecimalFormat df2 = new DecimalFormat("#.##");
		BigDecimal bd = new BigDecimal(df2.format((double)voucherRespDTO.getDiscount()));
		
		assertEquals(bd.doubleValue(), 12.25, 0.0f);
	}
	
}
