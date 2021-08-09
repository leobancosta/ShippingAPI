package com.mynt.exam.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.mynt.exam.web.dto.VoucherItemDTO;

public class ShippingServiceUtil {

	public String buildUrlToString(String voucherCode, String voucherAPIUrl, String apiKey) {
		return voucherAPIUrl + "/" + voucherCode + "?key=" + apiKey;
	}

	public float setDecimalPlaceToTwo(float num) {
		DecimalFormat df2 = new DecimalFormat("#.##");
		BigDecimal bd = new BigDecimal(df2.format((double)num));
		return bd.floatValue();
	}

	public Date getCurrentDate() {
		return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public boolean checkRestClientErrorForBypassApplyRule(VoucherItemDTO voucherItemDTO) {
		return !(voucherItemDTO.getDiscount() <= 0
				&& (voucherItemDTO.getError() != null && !"".equals(voucherItemDTO.getError())));
	}
}
