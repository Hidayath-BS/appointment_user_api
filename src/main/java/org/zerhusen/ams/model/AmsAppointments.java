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

import org.zerhusen.ams.model.AmsAvailableTimeSlots;


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
	
	@Column(name="age")
	private int age;
	
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

	@Column(name="asthama")
	private boolean asthama;
		
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="email")
	private String emailId;
	
	@Column(name="appointment_type")
	private byte appointmentType;
	
	@Column(name="appointmrnt_status")
	private byte appointmentStatus;
	
	@Column(name="eye_prolem")
	private boolean eyeProblem;
	
	@Column(name="eye_problem_details")
	private String eyeProblemDetails;
	
	@Column(name="eye_drops")
	private boolean eyeDrops;
	
	@Column(name="eye_drop_details")
	private String eyeDropDetails;
	
	@Column(name="rescheduled")
	private boolean rescheduled;
	
	@Column(name="completed")
	private boolean completed;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="payment_received")
	private boolean paymentReceived;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="drug_allergy")
	private boolean drugAllergy;
	
	@Column(name="drug_allergy_duration")
	private String drugAllergyDuration;
	
	@Column(name="other_medical_condition")
	private boolean otherMedicalCondition;
	
	@Column(name="other_medical_condition_duration")
	private String otherMedicalConditionDuration;
	
	@Column(name="reffered_by")
	private String refferedBy;
	
	@Column(name="address_line_1")
	private String addressLine1;
	
	
	@Column(name="address_line_2")
	private String addressLine2;
	
	@Column(name="pinocode")
	private String pincode;

	public AmsAppointments() {
		
	}

	
	
	
	public AmsAppointments(LocalDate date, String patientName, int age, String gender,
			boolean diabetic, String diabeticDuration, boolean bp, String bpDuration, boolean cardiac, boolean asthama,
			String contactNumber, String emailId, byte appointmentType, byte appointmentStatus, boolean eyeProblem,
			String eyeProblemDetails, boolean eyeDrops, String eyeDropDetails, boolean rescheduled, boolean completed,
			boolean active, boolean paymentReceived, boolean drugAllergy, String drugAllergyDuration,
			boolean otherMedicalCondition, String otherMedicalConditionDuration, String refferedBy, String addressLine1, String addressLine2, String pincode ) {
		super();
		this.date = date;
		
		this.patientName = patientName;
		this.age = age;
		this.gender = gender;
		this.diabetic = diabetic;
		this.diabeticDuration = diabeticDuration;
		this.bp = bp;
		this.bpDuration = bpDuration;
		this.cardiac = cardiac;
		this.asthama = asthama;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.appointmentType = appointmentType;
		this.appointmentStatus = appointmentStatus;
		this.eyeProblem = eyeProblem;
		this.eyeProblemDetails = eyeProblemDetails;
		this.eyeDrops = eyeDrops;
		this.eyeDropDetails = eyeDropDetails;
		this.rescheduled = rescheduled;
		this.completed = completed;
		this.active = active;
		this.paymentReceived = paymentReceived;
		this.drugAllergy = drugAllergy;
		this.drugAllergyDuration = drugAllergyDuration;
		this.otherMedicalCondition = otherMedicalCondition;
		this.otherMedicalConditionDuration = otherMedicalConditionDuration;
		this.refferedBy = refferedBy;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pincode = pincode;
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



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
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



	public boolean isAsthama() {
		return asthama;
	}



	public void setAsthama(boolean asthama) {
		this.asthama = asthama;
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



	public boolean isEyeProblem() {
		return eyeProblem;
	}



	public void setEyeProblem(boolean eyeProblem) {
		this.eyeProblem = eyeProblem;
	}



	public String getEyeProblemDetails() {
		return eyeProblemDetails;
	}



	public void setEyeProblemDetails(String eyeProblemDetails) {
		this.eyeProblemDetails = eyeProblemDetails;
	}



	public boolean isEyeDrops() {
		return eyeDrops;
	}



	public void setEyeDrops(boolean eyeDrops) {
		this.eyeDrops = eyeDrops;
	}



	public String getEyeDropDetails() {
		return eyeDropDetails;
	}



	public void setEyeDropDetails(String eyeDropDetails) {
		this.eyeDropDetails = eyeDropDetails;
	}



	public boolean isRescheduled() {
		return rescheduled;
	}



	public void setRescheduled(boolean rescheduled) {
		this.rescheduled = rescheduled;
	}



	public boolean isCompleted() {
		return completed;
	}



	public void setCompleted(boolean completed) {
		this.completed = completed;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public boolean isPaymentReceived() {
		return paymentReceived;
	}



	public void setPaymentReceived(boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}



	public String getPaymentMethod() {
		return paymentMethod;
	}



	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	public boolean isDrugAllergy() {
		return drugAllergy;
	}



	public void setDrugAllergy(boolean drugAllergy) {
		this.drugAllergy = drugAllergy;
	}



	public String getDrugAllergyDuration() {
		return drugAllergyDuration;
	}



	public void setDrugAllergyDuration(String drugAllergyDuration) {
		this.drugAllergyDuration = drugAllergyDuration;
	}



	public boolean isOtherMedicalCondition() {
		return otherMedicalCondition;
	}



	public void setOtherMedicalCondition(boolean otherMedicalCondition) {
		this.otherMedicalCondition = otherMedicalCondition;
	}



	public String getOtherMedicalConditionDuration() {
		return otherMedicalConditionDuration;
	}



	public void setOtherMedicalConditionDuration(String otherMedicalConditionDuration) {
		this.otherMedicalConditionDuration = otherMedicalConditionDuration;
	}



	public String getRefferedBy() {
		return refferedBy;
	}



	public String getAddressLine1() {
		return addressLine1;
	}




	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}




	public String getAddressLine2() {
		return addressLine2;
	}




	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}




	public String getPincode() {
		return pincode;
	}




	public void setPincode(String pincode) {
		this.pincode = pincode;
	}




	public void setRefferedBy(String refferedBy) {
		this.refferedBy = refferedBy;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
		result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
		result = prime * result + age;
		result = prime * result + appointmentStatus;
		result = prime * result + appointmentType;
		result = prime * result + (asthama ? 1231 : 1237);
		result = prime * result + (bp ? 1231 : 1237);
		result = prime * result + ((bpDuration == null) ? 0 : bpDuration.hashCode());
		result = prime * result + (cardiac ? 1231 : 1237);
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (diabetic ? 1231 : 1237);
		result = prime * result + ((diabeticDuration == null) ? 0 : diabeticDuration.hashCode());
		result = prime * result + (drugAllergy ? 1231 : 1237);
		result = prime * result + ((drugAllergyDuration == null) ? 0 : drugAllergyDuration.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((eyeDropDetails == null) ? 0 : eyeDropDetails.hashCode());
		result = prime * result + (eyeDrops ? 1231 : 1237);
		result = prime * result + (eyeProblem ? 1231 : 1237);
		result = prime * result + ((eyeProblemDetails == null) ? 0 : eyeProblemDetails.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + (otherMedicalCondition ? 1231 : 1237);
		result = prime * result
				+ ((otherMedicalConditionDuration == null) ? 0 : otherMedicalConditionDuration.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		result = prime * result + ((patientUser == null) ? 0 : patientUser.hashCode());
		result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
		result = prime * result + (paymentReceived ? 1231 : 1237);
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((refferedBy == null) ? 0 : refferedBy.hashCode());
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
		if (addressLine1 == null) {
			if (other.addressLine1 != null)
				return false;
		} else if (!addressLine1.equals(other.addressLine1))
			return false;
		if (addressLine2 == null) {
			if (other.addressLine2 != null)
				return false;
		} else if (!addressLine2.equals(other.addressLine2))
			return false;
		if (age != other.age)
			return false;
		if (appointmentStatus != other.appointmentStatus)
			return false;
		if (appointmentType != other.appointmentType)
			return false;
		if (asthama != other.asthama)
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
		if (completed != other.completed)
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
		if (diabetic != other.diabetic)
			return false;
		if (diabeticDuration == null) {
			if (other.diabeticDuration != null)
				return false;
		} else if (!diabeticDuration.equals(other.diabeticDuration))
			return false;
		if (drugAllergy != other.drugAllergy)
			return false;
		if (drugAllergyDuration == null) {
			if (other.drugAllergyDuration != null)
				return false;
		} else if (!drugAllergyDuration.equals(other.drugAllergyDuration))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (eyeDropDetails == null) {
			if (other.eyeDropDetails != null)
				return false;
		} else if (!eyeDropDetails.equals(other.eyeDropDetails))
			return false;
		if (eyeDrops != other.eyeDrops)
			return false;
		if (eyeProblem != other.eyeProblem)
			return false;
		if (eyeProblemDetails == null) {
			if (other.eyeProblemDetails != null)
				return false;
		} else if (!eyeProblemDetails.equals(other.eyeProblemDetails))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (otherMedicalCondition != other.otherMedicalCondition)
			return false;
		if (otherMedicalConditionDuration == null) {
			if (other.otherMedicalConditionDuration != null)
				return false;
		} else if (!otherMedicalConditionDuration.equals(other.otherMedicalConditionDuration))
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
		if (paymentMethod == null) {
			if (other.paymentMethod != null)
				return false;
		} else if (!paymentMethod.equals(other.paymentMethod))
			return false;
		if (paymentReceived != other.paymentReceived)
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (refferedBy == null) {
			if (other.refferedBy != null)
				return false;
		} else if (!refferedBy.equals(other.refferedBy))
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
				+ ", patientName=" + patientName + ", age=" + age + ", gender=" + gender + ", diabetic=" + diabetic
				+ ", diabeticDuration=" + diabeticDuration + ", bp=" + bp + ", bpDuration=" + bpDuration + ", cardiac="
				+ cardiac + ", asthama=" + asthama + ", contactNumber=" + contactNumber + ", emailId=" + emailId
				+ ", appointmentType=" + appointmentType + ", appointmentStatus=" + appointmentStatus + ", eyeProblem="
				+ eyeProblem + ", eyeProblemDetails=" + eyeProblemDetails + ", eyeDrops=" + eyeDrops
				+ ", eyeDropDetails=" + eyeDropDetails + ", rescheduled=" + rescheduled + ", completed=" + completed
				+ ", active=" + active + ", paymentReceived=" + paymentReceived + ", paymentMethod=" + paymentMethod
				+ ", drugAllergy=" + drugAllergy + ", drugAllergyDuration=" + drugAllergyDuration
				+ ", otherMedicalCondition=" + otherMedicalCondition + ", otherMedicalConditionDuration="
				+ otherMedicalConditionDuration + ", refferedBy=" + refferedBy + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", pincode=" + pincode + "]";
	}



	

	
		
	
	
	
}
