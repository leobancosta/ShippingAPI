package com.mynt.exam.service.impl;

import java.text.MessageFormat;
import java.util.Locale;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.mynt.exam.service.VoucherService;
import com.mynt.exam.util.ShippingServiceUtil;
import com.mynt.exam.web.dto.ShippingDetailsDTO;
import com.mynt.exam.web.dto.VoucherItemDTO;

@Service
public class VoucherServiceImpl implements VoucherService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MessageSource messageSource;
	
	private ShippingServiceUtil util = new ShippingServiceUtil();
	
	private final KieContainer kieContainer;
	
	@Autowired
	public VoucherServiceImpl(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public VoucherItemDTO getVoucherValue(ShippingDetailsDTO shippingDetailsDTO) {
		VoucherItemDTO voucherDTO = new VoucherItemDTO();
		try {
			String voucherUrl = util.buildUrlToString(shippingDetailsDTO.getVoucher(), env.getProperty("mynt.voucher.api.uri"), env.getProperty("mynt.voucher.api.key"));
			voucherDTO = restTemplate.getForObject(voucherUrl, VoucherItemDTO.class);
		} catch(HttpClientErrorException ex) {
			if(ex.getStatusCode() == HttpStatus.BAD_REQUEST || ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				voucherDTO.setError(MessageFormat.format(messageSource.getMessage("invalid.voucher", null, Locale.US),shippingDetailsDTO.getVoucher()));
			}
		}
		
		if(util.checkRestClientErrorForBypassApplyRule(voucherDTO)) {
			KieSession kieSession = kieContainer.newKieSession("ksession-voucher-rules");
			kieSession.insert(voucherDTO);
			kieSession.setGlobal("util", util);
			kieSession.fireAllRules();
			kieSession.dispose();
        }
		voucherDTO.setDiscount(util.setDecimalPlaceToTwo(voucherDTO.getDiscount()));
		return voucherDTO;
	}	
}
