package org.zerhusen.ams.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ams_consultation_request_responses")
public class ConsultationRequestResponses {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="request_id")
	private ConsultationRequests request;
	
	
	@Column(name="response")
	@Lob
	private String response;
	
	@Column(name="response_date")
	private LocalDate responseDate;
	
	@Column(name="response_time")
	private LocalTime responseTime;
	
	@Column(name="active")
	private boolean active;

	public ConsultationRequestResponses() {
		
	}

	public ConsultationRequestResponses(String response, LocalDate responseDate,
			LocalTime responseTime, boolean active) {
		this.response = response;
		this.responseDate = responseDate;
		this.responseTime = responseTime;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ConsultationRequests getRequest() {
		return request;
	}

	public void setRequest(ConsultationRequests request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public LocalDate getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(LocalDate responseDate) {
		this.responseDate = responseDate;
	}

	public LocalTime getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(LocalTime responseTime) {
		this.responseTime = responseTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + ((response == null) ? 0 : response.hashCode());
		result = prime * result + ((responseDate == null) ? 0 : responseDate.hashCode());
		result = prime * result + ((responseTime == null) ? 0 : responseTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultationRequestResponses other = (ConsultationRequestResponses) obj;
		if (active != other.active)
			return false;
		if (id != other.id)
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		if (responseDate == null) {
			if (other.responseDate != null)
				return false;
		} else if (!responseDate.equals(other.responseDate))
			return false;
		if (responseTime == null) {
			if (other.responseTime != null)
				return false;
		} else if (!responseTime.equals(other.responseTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsultationRequestResponses [id=" + id + ", request=" + request + ", response=" + response
				+ ", responseDate=" + responseDate + ", responseTime=" + responseTime + ", active=" + active + "]";
	}
	
	
	
	
	
	
}
