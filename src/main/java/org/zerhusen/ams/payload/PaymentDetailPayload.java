package org.zerhusen.ams.payload;

public class PaymentDetailPayload {

	private String patientName;
	
	private String transId;
	
	private Double amount;
	
	private String description;
	
	private String id;

	public PaymentDetailPayload() {
	
	}

	public PaymentDetailPayload(String patientName, String transId, Double amount, String description, String id) {
		
		this.patientName = patientName;
		this.transId = transId;
		this.amount = amount;
		this.description = description;
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
