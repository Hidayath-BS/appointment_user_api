package org.zerhusen.ams.model.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;





@Entity
@Table(name="ams_cities")
public class Ak_city {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String city;
	
	
	@ManyToOne
	@JoinColumn(name = "stateid")
	private Ak_state sateid; 
	
	public Ak_city() {
	
	}

	

	public Ak_city(String city) {
		
		this.city = city;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Ak_state getSate_id() {
		return sateid;
	}

	public void setSate_id(Ak_state sateid) {
		this.sateid = sateid;
	}

	@Override
	public String toString() {
		return "Dl_city [id=" + id + ", city=" + city + ", sate_id=" + sateid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + id;
		result = prime * result + ((sateid == null) ? 0 : sateid.hashCode());
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
		Ak_city other = (Ak_city) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id != other.id)
			return false;
		if (sateid == null) {
			if (other.sateid != null)
				return false;
		} else if (!sateid.equals(other.sateid))
			return false;
		return true;
	}


	
	
}
