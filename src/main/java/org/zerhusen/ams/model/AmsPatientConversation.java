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
@Table(name="ams_patient_conversation")
public class AmsPatientConversation {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="start_time")
	private LocalTime startTime;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Ams_patient_users patient;
	
	@Lob
	@Column(name="topic")
	private String topic;
	
	@Column(name="active")
	private boolean active;

	public AmsPatientConversation() {
		
	}

	

	
	
	public AmsPatientConversation(LocalDate startDate, LocalTime startTime, String topic, boolean active) {
	
		this.startDate = startDate;
		this.startTime = startTime;
		this.topic = topic;
		this.active = active;
	}





	public String getTopic() {
		return topic;
	}





	public void setTopic(String topic) {
		this.topic = topic;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public Ams_patient_users getPatient() {
		return patient;
	}

	public void setPatient(Ams_patient_users patient) {
		this.patient = patient;
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
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		AmsPatientConversation other = (AmsPatientConversation) obj;
		if (active != other.active)
			return false;
		if (id != other.id)
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}





	@Override
	public String toString() {
		return "AmsPatientConversation [id=" + id + ", startDate=" + startDate + ", startTime=" + startTime
				+ ", patient=" + patient + ", topic=" + topic + ", active=" + active + "]";
	}

	
	
	
	
	
	
	
}
