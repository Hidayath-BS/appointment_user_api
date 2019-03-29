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
@Table(name="ams_patient_queries")
public class AmsPatientQueries {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Ams_patient_users patient;
	
	@Lob
	@Column(name="query")
	private String query;
	
	@Column(name="status")
	private byte status;
	
	
	@Column(name="query_date")
	private LocalDate queryDate;
	
	@Column(name="query_time")
	private LocalTime queryTime;
	
	@Column(name="active")
	private boolean active;

	public AmsPatientQueries() {
	
	}


	
	
	
	public AmsPatientQueries(String query, byte status, LocalDate queryDate, LocalTime queryTime, boolean active) {
		
		this.query = query;
		this.status = status;
		this.queryDate = queryDate;
		this.queryTime = queryTime;
		this.active = active;
	}

	



	public LocalDate getQueryDate() {
		return queryDate;
	}





	public void setQueryDate(LocalDate queryDate) {
		this.queryDate = queryDate;
	}





	public LocalTime getQueryTime() {
		return queryTime;
	}





	public void setQueryTime(LocalTime queryTime) {
		this.queryTime = queryTime;
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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
		result = prime * result + id;
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + ((queryDate == null) ? 0 : queryDate.hashCode());
		result = prime * result + ((queryTime == null) ? 0 : queryTime.hashCode());
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
		AmsPatientQueries other = (AmsPatientQueries) obj;
		if (active != other.active)
			return false;
		if (id != other.id)
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (queryDate == null) {
			if (other.queryDate != null)
				return false;
		} else if (!queryDate.equals(other.queryDate))
			return false;
		if (queryTime == null) {
			if (other.queryTime != null)
				return false;
		} else if (!queryTime.equals(other.queryTime))
			return false;
		if (status != other.status)
			return false;
		return true;
	}





	@Override
	public String toString() {
		return "AmsPatientQueries [id=" + id + ", patient=" + patient + ", query=" + query + ", status=" + status
				+ ", queryDate=" + queryDate + ", queryTime=" + queryTime + ", active=" + active + "]";
	}

		
	
	
	
	
}
