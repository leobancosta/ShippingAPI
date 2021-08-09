package com.mynt.exam.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mynt.exam.service.ShippingService;
import com.mynt.exam.service.VoucherService;
import com.mynt.exam.util.ShippingServiceUtil;
import com.mynt.exam.web.dto.ShippingCostDTO;
import com.mynt.exam.web.dto.ShippingDetailsDTO;
import com.mynt.exam.web.dto.VoucherItemDTO;

@RestController
@RequestMapping("/shipping")
@Validated
public class ShippingController {

	@Autowired
	ShippingService shippingService;
	
	@Autowired
	VoucherService voucherService;
	
	@PostMapping
	public ResponseEntity<ShippingCostDTO> computeShipping(@RequestBody @Valid ShippingDetailsDTO shippingDetailsDTO) {
		ShippingServiceUtil util = new ShippingServiceUtil();
		ShippingCostDTO shippingCostDTO = shippingService.computeShippingCost(shippingDetailsDTO);
		VoucherItemDTO voucherItemDTO = voucherService.getVoucherValue(shippingDetailsDTO);
		
		shippingCostDTO.setShippingFee(util.setDecimalPlaceToTwo(shippingCostDTO.getShippingFee()));
		shippingCostDTO.setDiscountStatus(util.checkRestClientErrorForBypassApplyRule(voucherItemDTO) ? voucherItemDTO.getVoucherStatus() : voucherItemDTO.getError());
		shippingCostDTO.setDiscount(util.setDecimalPlaceToTwo(voucherItemDTO.getDiscount()));
		shippingCostDTO.setDiscountStatus(voucherItemDTO.getError());

		return new ResponseEntity<ShippingCostDTO>(shippingCostDTO, HttpStatus.OK);
	}
}
