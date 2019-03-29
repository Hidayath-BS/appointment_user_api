package org.zerhusen.model.ams;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ams_reschedules")
public class AmsReschedules {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="appointment_id")
	private AmsAppointments appointment;
	
	@ManyToOne
	@JoinColumn(name="slot_id")
	private AmsAvailableTimeSlots slot;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="status")
	private byte status;

	public AmsReschedules() {
		
	}

	public AmsReschedules(LocalDate date, boolean active, byte status) {
		
		this.date = date;
		this.active = active;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public AmsAppointments getAppointment() {
		return appointment;
	}

	public void setAppointment(AmsAppointments appointment) {
		this.appointment = appointment;
	}

	public AmsAvailableTimeSlots getSlot() {
		return slot;
	}

	public void setSlot(AmsAvailableTimeSlots slot) {
		this.slot = slot;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((appointment == null) ? 0 : appointment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
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
		AmsReschedules other = (AmsReschedules) obj;
		if (active != other.active)
			return false;
		if (appointment == null) {
			if (other.appointment != null)
				return false;
		} else if (!appointment.equals(other.appointment))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AmsReschedules [id=" + id + ", date=" + date + ", appointment=" + appointment + ", slot=" + slot
				+ ", active=" + active + ", status=" + status + "]";
	}
	
	
	
	
	
	
}
