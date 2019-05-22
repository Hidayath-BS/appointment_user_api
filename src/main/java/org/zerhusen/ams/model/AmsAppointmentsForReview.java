package org.zerhusen.ams.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name="ams_appointments_for_review")
public class AmsAppointmentsForReview {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="appointment_id")
	private AmsAppointments appointment;
	
	@Column(name="review_date")
	private LocalDate reviewDate;
	
	@Column(name="completed")
	private boolean completed;
	
	@Column(name="active")
	private boolean active;

	public AmsAppointmentsForReview() {
	
	}

	

	public AmsAppointmentsForReview(LocalDate reviewDate, boolean completed, boolean active) {
		this.reviewDate = reviewDate;
		this.completed = completed;
		this.active = active;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public boolean isCompleted() {
		return completed;
	}



	public void setCompleted(boolean completed) {
		this.completed = completed;
	}



	public AmsAppointments getAppointment() {
		return appointment;
	}

	public void setAppointment(AmsAppointments appointment) {
		this.appointment = appointment;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
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
		result = prime * result + ((appointment == null) ? 0 : appointment.hashCode());
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
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
		AmsAppointmentsForReview other = (AmsAppointmentsForReview) obj;
		if (active != other.active)
			return false;
		if (appointment == null) {
			if (other.appointment != null)
				return false;
		} else if (!appointment.equals(other.appointment))
			return false;
		if (completed != other.completed)
			return false;
		if (id != other.id)
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "AmsAppointmentsForReview [id=" + id + ", appointment=" + appointment + ", reviewDate=" + reviewDate
				+ ", completed=" + completed + ", active=" + active + "]";
	}

		
	
	
}
