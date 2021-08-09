package com.mynt.exam.service;

import com.mynt.exam.web.dto.ShippingCostDTO;
import com.mynt.exam.web.dto.ShippingDetailsDTO;

public interface ShippingService {

	ShippingCostDTO computeShippingCost(ShippingDetailsDTO shippingDTO);
}
