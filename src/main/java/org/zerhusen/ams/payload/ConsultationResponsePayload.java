package org.zerhusen.ams.payload;

import java.util.Collection;

import org.zerhusen.ams.model.ConsultationRequestResponses;
import org.zerhusen.ams.model.ConsultationRequests;

public class ConsultationResponsePayload {

	private ConsultationRequests request;
	
	private Collection<ConsultationRequestResponses> responses;

	
	
	public ConsultationResponsePayload() {
	
	}



	public ConsultationResponsePayload(ConsultationRequests request,
			Collection<ConsultationRequestResponses> responses) {
	
		this.request = request;
		this.responses = responses;
	}



	public ConsultationRequests getRequest() {
		return request;
	}



	public void setRequest(ConsultationRequests request) {
		this.request = request;
	}



	public Collection<ConsultationRequestResponses> getResponses() {
		return responses;
	}



	public void setResponses(Collection<ConsultationRequestResponses> responses) {
		this.responses = responses;
	}
	
	
	
	
}
