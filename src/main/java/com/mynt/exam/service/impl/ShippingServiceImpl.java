package com.mynt.exam.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mynt.exam.service.ShippingService;
import com.mynt.exam.web.dto.ShippingCostDTO;
import com.mynt.exam.web.dto.ShippingDetailsDTO;

@Service
public class ShippingServiceImpl implements ShippingService {
	
	private KieContainer kieContainer;
	
	@Autowired
	public ShippingServiceImpl(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public ShippingCostDTO computeShippingCost(ShippingDetailsDTO shippingDetailsDTO) {
		ShippingCostDTO shippingCostDTO = new ShippingCostDTO();

        KieSession kieSession = kieContainer.newKieSession("ksession-shipping-rules");
		kieSession.setGlobal("shippingCostDTO", shippingCostDTO);
		kieSession.insert(shippingDetailsDTO);
        
		kieSession.fireAllRules();
		kieSession.dispose();

		return shippingCostDTO;
	}

}
