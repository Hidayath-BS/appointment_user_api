package org.zerhusen.model.ams;

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
@Table(name="ams_query_response")
public class AmsQueryResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="query_id")
	private AmsPatientQueries query;
	
	@Lob
	@Column(name="response")
	private String response;
	
	@Column(name="response_date")
	private LocalDate responseDate;
	
	@Column(name="response_time")
	private LocalTime responseTime;
	
	
	@Column(name="active")
	private boolean active;


	public AmsQueryResponse() {
		
	}


	
	

	public AmsQueryResponse(String response, LocalDate responseDate, LocalTime responseTime, boolean active) {
		this.response = response;
		this.responseDate = responseDate;
		this.responseTime = responseTime;
		this.active = active;
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





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public AmsPatientQueries getQuery() {
		return query;
	}


	public void setQuery(AmsPatientQueries query) {
		this.query = query;
	}


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
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
		result = prime * result + ((query == null) ? 0 : query.hashCode());
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
		AmsQueryResponse other = (AmsQueryResponse) obj;
		if (active != other.active)
			return false;
		if (id != other.id)
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
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
		return "AmsQueryResponse [id=" + id + ", query=" + query + ", response=" + response + ", responseDate="
				+ responseDate + ", responseTime=" + responseTime + ", active=" + active + "]";
	}


		
	
}
