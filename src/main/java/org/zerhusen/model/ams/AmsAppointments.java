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
@Table(name="ams_appointments")
public class AmsAppointments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="slot_id")
	private AmsAvailableTimeSlots slot;
	
	@ManyToOne
	@JoinColumn(name="patient_user_id")
	private Ams_patient_users patientUser;
	
	@Column(name="patient_name")
	private String patientName;
	
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="diabetic")
	private boolean diabetic;
	
	@Column(name="diabetic_duration")
	private String diabeticDuration;
	
	@Column(name="blood_pressure")
	private boolean bp;
	
	@Column(name="bp_duration")
	private String bpDuration;
	
	@Column(name="cardiac")
	private boolean cardiac;

	@Column(name="cardiac_duration")
	private String cardiacDuration;

	@Column(name="asthama")
	private boolean asthama;
	
	@Column(name="asthama_duration")
	private String asthamaDuration;
	
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="email")
	private String emailId;
	
	@Column(name="appointment_type")
	private byte appointmentType;
	
	@Column(name="appointmrnt_status")
	private byte appointmentStatus;
	
	
	@Column(name="rescheduled")
	private boolean rescheduled;
	
	@Column(name="active")
	private boolean active;

	public AmsAppointments() {
		
	}

	

	public AmsAppointments(LocalDate date, String patientName, LocalDate dateOfBirth, String gender, boolean diabetic,
			String diabeticDuration, boolean bp, String bpDuration, boolean cardiac, String cardiacDuration,
			boolean asthama, String asthamaDuration, String contactNumber, String emailId, byte appointmentType,
			byte appointmentStatus, boolean rescheduled, boolean active) {
		this.date = date;
		this.patientName = patientName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.diabetic = diabetic;
		this.diabeticDuration = diabeticDuration;
		this.bp = bp;
		this.bpDuration = bpDuration;
		this.cardiac = cardiac;
		this.cardiacDuration = cardiacDuration;
		this.asthama = asthama;
		this.asthamaDuration = asthamaDuration;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.appointmentType = appointmentType;
		this.appointmentStatus = appointmentStatus;
		this.rescheduled = rescheduled;
		this.active = active;
	}



	public boolean isRescheduled() {
		return rescheduled;
	}

	public void setRescheduled(boolean rescheduled) {
		this.rescheduled = rescheduled;
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

	public AmsAvailableTimeSlots getSlot() {
		return slot;
	}

	public void setSlot(AmsAvailableTimeSlots slot) {
		this.slot = slot;
	}

	public Ams_patient_users getPatientUser() {
		return patientUser;
	}

	public void setPatientUser(Ams_patient_users patientUser) {
		this.patientUser = patientUser;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isDiabetic() {
		return diabetic;
	}

	public void setDiabetic(boolean diabetic) {
		this.diabetic = diabetic;
	}

	public String getDiabeticDuration() {
		return diabeticDuration;
	}

	public void setDiabeticDuration(String diabeticDuration) {
		this.diabeticDuration = diabeticDuration;
	}

	public boolean isBp() {
		return bp;
	}

	public void setBp(boolean bp) {
		this.bp = bp;
	}

	public String getBpDuration() {
		return bpDuration;
	}

	public void setBpDuration(String bpDuration) {
		this.bpDuration = bpDuration;
	}

	public boolean isCardiac() {
		return cardiac;
	}

	public void setCardiac(boolean cardiac) {
		this.cardiac = cardiac;
	}

	public String getCardiacDuration() {
		return cardiacDuration;
	}

	public void setCardiacDuration(String cardiacDuration) {
		this.cardiacDuration = cardiacDuration;
	}

	public boolean isAsthama() {
		return asthama;
	}

	public void setAsthama(boolean asthama) {
		this.asthama = asthama;
	}

	public String getAsthamaDuration() {
		return asthamaDuration;
	}

	public void setAsthamaDuration(String asthamaDuration) {
		this.asthamaDuration = asthamaDuration;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public byte getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(byte appointmentType) {
		this.appointmentType = appointmentType;
	}

	public byte getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(byte appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
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
		result = prime * result + appointmentStatus;
		result = prime * result + appointmentType;
		result = prime * result + (asthama ? 1231 : 1237);
		result = prime * result + ((asthamaDuration == null) ? 0 : asthamaDuration.hashCode());
		result = prime * result + (bp ? 1231 : 1237);
		result = prime * result + ((bpDuration == null) ? 0 : bpDuration.hashCode());
		result = prime * result + (cardiac ? 1231 : 1237);
		result = prime * result + ((cardiacDuration == null) ? 0 : cardiacDuration.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + (diabetic ? 1231 : 1237);
		result = prime * result + ((diabeticDuration == null) ? 0 : diabeticDuration.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		result = prime * result + ((patientUser == null) ? 0 : patientUser.hashCode());
		result = prime * result + (rescheduled ? 1231 : 1237);
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
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
		AmsAppointments other = (AmsAppointments) obj;
		if (active != other.active)
			return false;
		if (appointmentStatus != other.appointmentStatus)
			return false;
		if (appointmentType != other.appointmentType)
			return false;
		if (asthama != other.asthama)
			return false;
		if (asthamaDuration == null) {
			if (other.asthamaDuration != null)
				return false;
		} else if (!asthamaDuration.equals(other.asthamaDuration))
			return false;
		if (bp != other.bp)
			return false;
		if (bpDuration == null) {
			if (other.bpDuration != null)
				return false;
		} else if (!bpDuration.equals(other.bpDuration))
			return false;
		if (cardiac != other.cardiac)
			return false;
		if (cardiacDuration == null) {
			if (other.cardiacDuration != null)
				return false;
		} else if (!cardiacDuration.equals(other.cardiacDuration))
			return false;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (diabetic != other.diabetic)
			return false;
		if (diabeticDuration == null) {
			if (other.diabeticDuration != null)
				return false;
		} else if (!diabeticDuration.equals(other.diabeticDuration))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (patientName == null) {
			if (other.patientName != null)
				return false;
		} else if (!patientName.equals(other.patientName))
			return false;
		if (patientUser == null) {
			if (other.patientUser != null)
				return false;
		} else if (!patientUser.equals(other.patientUser))
			return false;
		if (rescheduled != other.rescheduled)
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "AmsAppointments [id=" + id + ", date=" + date + ", slot=" + slot + ", patientUser=" + patientUser
				+ ", patientName=" + patientName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", diabetic="
				+ diabetic + ", diabeticDuration=" + diabeticDuration + ", bp=" + bp + ", bpDuration=" + bpDuration
				+ ", cardiac=" + cardiac + ", cardiacDuration=" + cardiacDuration + ", asthama=" + asthama
				+ ", asthamaDuration=" + asthamaDuration + ", contactNumber=" + contactNumber + ", emailId=" + emailId
				+ ", appointmentType=" + appointmentType + ", appointmentStatus=" + appointmentStatus + ", rescheduled="
				+ rescheduled + ", active=" + active + "]";
	}

		
	
	
	
}
