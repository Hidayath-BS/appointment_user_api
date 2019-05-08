package org.zerhusen.ams.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zerhusen.ams.model.security.User;


@Entity
@Table(name="ams_other_appointments")
public class OtherAppointments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="patient_name")
	private String patientName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="age")
	private Double age;
	
	@Column(name="status")
	private byte status;
	
	@Column(name="mrd_number")
	private String mrdNumber;
	
	@Column(name="appointment_date")
	private LocalDate appointmentDate;
	
	@Column(name="appointment_time")
	private LocalTime appointmentTime;
	
	@Column(name="active")
	private boolean active;
	
	
	@ManyToOne
	@JoinColumn(name="procedure_id")
	private Ams_Procedures procedure;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Ams_patient_users patient;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="ams_other_appointments_treating_doctors",
	joinColumns= {@JoinColumn(name="appointment_id", referencedColumnName="id")},
	inverseJoinColumns= {@JoinColumn(name="doctor_id", referencedColumnName="id")})
	private Collection<User> doctors;


	public OtherAppointments() {
	
	}


	public OtherAppointments(String patientName, String email, String mobileNumber, Double age, byte status,
			String mrdNumber, LocalDate appointmentDate, LocalTime appointmentTime, boolean active) {
		this.patientName = patientName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.age = age;
		this.status = status;
		this.mrdNumber = mrdNumber;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.active = active;
	}

	

	public Ams_patient_users getPatient() {
		return patient;
	}


	public void setPatient(Ams_patient_users patient) {
		this.patient = patient;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
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


	public Double getAge() {
		return age;
	}


	public void setAge(Double age) {
		this.age = age;
	}


	public byte getStatus() {
		return status;
	}


	public void setStatus(byte status) {
		this.status = status;
	}


	public String getMrdNumber() {
		return mrdNumber;
	}


	public void setMrdNumber(String mrdNumber) {
		this.mrdNumber = mrdNumber;
	}


	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}


	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Collection<User> getDoctors() {
		return doctors;
	}


	public void setDoctors(Collection<User> doctors) {
		this.doctors = doctors;
	}
	
	


	public Ams_Procedures getProcedure() {
		return procedure;
	}


	public void setProcedure(Ams_Procedures procedure) {
		this.procedure = procedure;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((appointmentDate == null) ? 0 : appointmentDate.hashCode());
		result = prime * result + ((appointmentTime == null) ? 0 : appointmentTime.hashCode());
		result = prime * result + ((doctors == null) ? 0 : doctors.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((mrdNumber == null) ? 0 : mrdNumber.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		result = prime * result + ((procedure == null) ? 0 : procedure.hashCode());
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
		OtherAppointments other = (OtherAppointments) obj;
		if (active != other.active)
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (appointmentDate == null) {
			if (other.appointmentDate != null)
				return false;
		} else if (!appointmentDate.equals(other.appointmentDate))
			return false;
		if (appointmentTime == null) {
			if (other.appointmentTime != null)
				return false;
		} else if (!appointmentTime.equals(other.appointmentTime))
			return false;
		if (doctors == null) {
			if (other.doctors != null)
				return false;
		} else if (!doctors.equals(other.doctors))
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
		if (mrdNumber == null) {
			if (other.mrdNumber != null)
				return false;
		} else if (!mrdNumber.equals(other.mrdNumber))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (patientName == null) {
			if (other.patientName != null)
				return false;
		} else if (!patientName.equals(other.patientName))
			return false;
		if (procedure == null) {
			if (other.procedure != null)
				return false;
		} else if (!procedure.equals(other.procedure))
			return false;
		if (status != other.status)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OtherAppointments [id=" + id + ", patientName=" + patientName + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", age=" + age + ", status=" + status + ", mrdNumber=" + mrdNumber
				+ ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", active=" + active
				+ ", procedure=" + procedure + ", patient=" + patient + ", doctors=" + doctors + "]";
	}


	
		
	
	
	
	
}
