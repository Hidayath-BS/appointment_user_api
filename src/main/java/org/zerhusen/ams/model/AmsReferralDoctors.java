package org.zerhusen.ams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ams_referral_doctors")
public class AmsReferralDoctors {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="hospital_name")
	private String hospitalName;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="active")
	private boolean active;

	public AmsReferralDoctors() {
	
	}

	public AmsReferralDoctors(String fullName, String hospitalName, String mobileNumber, boolean active) {
		this.fullName = fullName;
		this.hospitalName = hospitalName;
		this.mobileNumber = mobileNumber;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((hospitalName == null) ? 0 : hospitalName.hashCode());
		result = prime * result + id;
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
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
		AmsReferralDoctors other = (AmsReferralDoctors) obj;
		if (active != other.active)
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (hospitalName == null) {
			if (other.hospitalName != null)
				return false;
		} else if (!hospitalName.equals(other.hospitalName))
			return false;
		if (id != other.id)
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AmsReferralDoctors [id=" + id + ", fullName=" + fullName + ", hospitalName=" + hospitalName
				+ ", mobileNumber=" + mobileNumber + ", active=" + active + "]";
	}
	
	
	
	
}
