package com.mynt.exam.service;

import com.mynt.exam.web.dto.ShippingDetailsDTO;
import com.mynt.exam.web.dto.VoucherItemDTO;

public interface VoucherService {
	
	VoucherItemDTO getVoucherValue(ShippingDetailsDTO shippingDetailsDTO);

}
	