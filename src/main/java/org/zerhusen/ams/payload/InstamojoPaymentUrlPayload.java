package org.zerhusen.ams.payload;

public class InstamojoPaymentUrlPayload {

	
	private String paymentUrl;
	

	public InstamojoPaymentUrlPayload() {
		
	}

	public InstamojoPaymentUrlPayload(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	
}
