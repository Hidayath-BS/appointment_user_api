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

import org.zerhusen.model.security.User;

@Entity
@Table(name="ams_available_slots")
public class AmsAvailableTimeSlots {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private User doctor;
	
	@ManyToOne
	@JoinColumn(name="slot_id")
	private AmsTimeSlots slot;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private AmsHospitalBranch branch;
	
	@Column(name="online")
	private boolean online;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="online_count")
	private int onlineCount;
	
	@Column(name="online_limit")
	private int onlinelimit;
	
	@Column(name="walkin_count")
	private int walkinCount;

	public AmsAvailableTimeSlots() {
		
	}

	

	
	
	
	public AmsAvailableTimeSlots(LocalDate date, boolean online, boolean active, int onlineCount, int onlinelimit,
			int walkinCount) {
		this.date = date;
		this.online = online;
		this.active = active;
		this.onlineCount = onlineCount;
		this.onlinelimit = onlinelimit;
		this.walkinCount = walkinCount;
	}






	public int getOnlinelimit() {
		return onlinelimit;
	}






	public void setOnlinelimit(int onlinelimit) {
		this.onlinelimit = onlinelimit;
	}






	public AmsHospitalBranch getBranch() {
		return branch;
	}

	public void setBranch(AmsHospitalBranch branch) {
		this.branch = branch;
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

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public AmsTimeSlots getSlot() {
		return slot;
	}

	public void setSlot(AmsTimeSlots slot) {
		this.slot = slot;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}

	public int getWalkinCount() {
		return walkinCount;
	}

	public void setWalkinCount(int walkinCount) {
		this.walkinCount = walkinCount;
	}






	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + id;
		result = prime * result + (online ? 1231 : 1237);
		result = prime * result + onlineCount;
		result = prime * result + onlinelimit;
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
		result = prime * result + walkinCount;
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
		AmsAvailableTimeSlots other = (AmsAvailableTimeSlots) obj;
		if (active != other.active)
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (id != other.id)
			return false;
		if (online != other.online)
			return false;
		if (onlineCount != other.onlineCount)
			return false;
		if (onlinelimit != other.onlinelimit)
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		if (walkinCount != other.walkinCount)
			return false;
		return true;
	}






	@Override
	public String toString() {
		return "AmsAvailableTimeSlots [id=" + id + ", date=" + date + ", doctor=" + doctor + ", slot=" + slot
				+ ", branch=" + branch + ", online=" + online + ", active=" + active + ", onlineCount=" + onlineCount
				+ ", onlinelimit=" + onlinelimit + ", walkinCount=" + walkinCount + "]";
	}

			
	
	
	
	
	
	
}
