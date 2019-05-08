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
@Table(name="ams_consultation_requests")
public class ConsultationRequests {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Ams_patient_users patient;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="consultation_request")
	@Lob
	private String consultationRequest;
	
	@Column(name="date_of_request")
	private LocalDate dateOfRequest;
	
	@Column(name="request_time")
	private LocalTime requestTime;
	
	@Column(name="status")
	private byte status;
	
	@Column(name="active")
	private boolean active;

	public ConsultationRequests() {
	
	}

	public ConsultationRequests(String email, String mobileNumber,
			String consultationRequest, LocalDate dateOfRequest, LocalTime requestTime, byte status, boolean active) {
	
		
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.consultationRequest = consultationRequest;
		this.dateOfRequest = dateOfRequest;
		this.requestTime = requestTime;
		this.status = status;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ams_patient_users getPatient() {
		return patient;
	}

	public void setPatient(Ams_patient_users patient) {
		this.patient = patient;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getConsultationRequest() {
		return consultationRequest;
	}

	public void setConsultationRequest(String consultationRequest) {
		this.consultationRequest = consultationRequest;
	}

	public LocalDate getDateOfRequest() {
		return dateOfRequest;
	}

	public void setDateOfRequest(LocalDate dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	public LocalTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalTime requestTime) {
		this.requestTime = requestTime;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
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
		result = prime * result + ((consultationRequest == null) ? 0 : consultationRequest.hashCode());
		result = prime * result + ((dateOfRequest == null) ? 0 : dateOfRequest.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((requestTime == null) ? 0 : requestTime.hashCode());
		result = prime * result + status;
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
		ConsultationRequests other = (ConsultationRequests) obj;
		if (active != other.active)
			return false;
		if (consultationRequest == null) {
			if (other.consultationRequest != null)
				return false;
		} else if (!consultationRequest.equals(other.consultationRequest))
			return false;
		if (dateOfRequest == null) {
			if (other.dateOfRequest != null)
				return false;
		} else if (!dateOfRequest.equals(other.dateOfRequest))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (requestTime == null) {
			if (other.requestTime != null)
				return false;
		} else if (!requestTime.equals(other.requestTime))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsultationRequests [id=" + id + ", patient=" + patient + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", consultationRequest=" + consultationRequest + ", dateOfRequest=" + dateOfRequest
				+ ", requestTime=" + requestTime + ", status=" + status + ", active=" + active + "]";
	}
	
	
	
	
}
