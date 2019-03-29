package org.zerhusen.model.ams;

import java.util.Collection;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.zerhusen.model.security.Ak_city;
import org.zerhusen.model.security.Ak_state;

@Entity
@Table(name="ams_patient_users")
public class Ams_patient_users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="address_line_1")
	private String addressLine1;
	
	@Column(name="address_line_2")
	private String addressLine2;
	
	@ManyToOne
	@JoinColumn(name="state_id")
	private Ak_state state;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private Ak_city city;
	
	
	@Column(name="pincode")
	private String pincode;
	
	@Column(name="patient_code")
	private String patientCode;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="patient_authority",
	joinColumns= {@JoinColumn(name="patient_id", referencedColumnName="id")},
	inverseJoinColumns= {@JoinColumn(name="authority_id", referencedColumnName="id")})
	private Collection<AmsPatientAuthority> authority;
	
	@Column(name="last_password_reset_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastPasswordResetDate;
	
	@Column(name="active")
	private boolean active;

	public Ams_patient_users() {
	
	}

	
	

	public Ams_patient_users(String firstName, String lastName, String email, String password, String mobileNumber,
			String addressLine1, String addressLine2, String pincode, String patientCode, boolean active) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pincode = pincode;
		this.patientCode = patientCode;
		this.active = active;
	}




	public Collection<AmsPatientAuthority> getAuthority() {
		return authority;
	}




	public void setAuthority(Collection<AmsPatientAuthority> authority) {
		this.authority = authority;
	}




	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}




	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}




	public String getPatientCode() {
		return patientCode;
	}




	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public Ak_state getState() {
		return state;
	}

	public void setState(Ak_state state) {
		this.state = state;
	}

	public Ak_city getCity() {
		return city;
	}

	public void setCity(Ak_city city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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
		result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
		result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lastPasswordResetDate == null) ? 0 : lastPasswordResetDate.hashCode());
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((patientCode == null) ? 0 : patientCode.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Ams_patient_users other = (Ams_patient_users) obj;
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
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (lastPasswordResetDate == null) {
			if (other.lastPasswordResetDate != null)
				return false;
		} else if (!lastPasswordResetDate.equals(other.lastPasswordResetDate))
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (patientCode == null) {
			if (other.patientCode != null)
				return false;
		} else if (!patientCode.equals(other.patientCode))
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Ams_patient_users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", mobileNumber=" + mobileNumber + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", state=" + state + ", city=" + city + ", pincode=" + pincode
				+ ", patientCode=" + patientCode + ", authority=" + authority + ", lastPasswordResetDate="
				+ lastPasswordResetDate + ", active=" + active + "]";
	}




	 		
	
	
	
	
	
	
}
