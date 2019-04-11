package org.zerhusen.ams.model.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "ams_admin_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobilenumber", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String mobilenumber;

    @Column(name = "password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;
    
    @Column(name="approved")
    private Boolean approved;

    @Column(name = "username", length = 50)
    private String username;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private Ak_city city_id;
	
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private Ak_state state_id;
    
	
    @Column(name = "ENABLED")
    private Boolean enabled;

    @Column(name="email")
    private String email;

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }

	@Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ADMIN_AUTHORITY",
            joinColumns = {@JoinColumn(name = "ADMIN_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Collection<Authority> authorities;

    public User() {
		
	}
    
    
    
    public User(@NotNull @Size(min = 4, max = 50) String mobilenumber,
			@NotNull @Size(min = 4, max = 100) String password, Boolean approved, String username, Boolean enabled,
			String email, Date lastPasswordResetDate) {
		this.mobilenumber = mobilenumber;
		this.password = password;
		this.approved = approved;
		this.username = username;
		this.enabled = enabled;
		this.email = email;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
    
    

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}



	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    
    

	
	public Ak_city getCity_id() {
		return city_id;
	}

	public void setCity_id(Ak_city city_id) {
		this.city_id = city_id;
	}

	public Ak_state getState_id() {
		return state_id;
	}

	public void setState_id(Ak_state state_id) {
		this.state_id = state_id;
	}

	


	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approved == null) ? 0 : approved.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((city_id == null) ? 0 : city_id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastPasswordResetDate == null) ? 0 : lastPasswordResetDate.hashCode());
		result = prime * result + ((mobilenumber == null) ? 0 : mobilenumber.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((state_id == null) ? 0 : state_id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (approved == null) {
			if (other.approved != null)
				return false;
		} else if (!approved.equals(other.approved))
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (city_id == null) {
			if (other.city_id != null)
				return false;
		} else if (!city_id.equals(other.city_id))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastPasswordResetDate == null) {
			if (other.lastPasswordResetDate != null)
				return false;
		} else if (!lastPasswordResetDate.equals(other.lastPasswordResetDate))
			return false;
		if (mobilenumber == null) {
			if (other.mobilenumber != null)
				return false;
		} else if (!mobilenumber.equals(other.mobilenumber))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (state_id == null) {
			if (other.state_id != null)
				return false;
		} else if (!state_id.equals(other.state_id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", mobilenumber=" + mobilenumber + ", password=" + password + ", approved=" + approved
				+ ", username=" + username + ", city_id=" + city_id + ", state_id=" + state_id + ", enabled=" + enabled
				+ ", email=" + email + ", lastPasswordResetDate=" + lastPasswordResetDate + ", authorities="
				+ authorities + "]";
	}

	
	    
    
    
    
}