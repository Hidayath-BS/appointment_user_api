package org.zerhusen.ams.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zerhusen.ams.model.security.User;



@Entity
@Table(name="ams_doctor_suggestion")
public class AmsDoctorSuggestion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="doctor")
	private User doctor;
	
	@ManyToOne
	@JoinColumn(name="service")
	private Ams_Services_available service;
	
	@Column(name="active")
	private boolean active;

	public AmsDoctorSuggestion() {

	}

	public AmsDoctorSuggestion(User doctor, Ams_Services_available service, boolean active) {
		
		this.doctor = doctor;
		this.service = service;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Ams_Services_available getService() {
		return service;
	}

	public void setService(Ams_Services_available service) {
		this.service = service;
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
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + id;
		result = prime * result + ((service == null) ? 0 : service.hashCode());
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
		AmsDoctorSuggestion other = (AmsDoctorSuggestion) obj;
		if (active != other.active)
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (id != other.id)
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AmsDoctorSuggestion [id=" + id + ", doctor=" + doctor + ", service=" + service + ", active=" + active
				+ "]";
	}
	
	

	
	
}
