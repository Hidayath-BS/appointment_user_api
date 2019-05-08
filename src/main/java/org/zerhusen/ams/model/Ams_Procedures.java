package org.zerhusen.ams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ams_hospital_procedures")
public class Ams_Procedures {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "procedure_name")
	private String procedures;
	
	@Column(name = "active")
	private boolean active;

	public Ams_Procedures() {
		
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Ams_Procedures(String procedures, boolean active) {
		super();
		this.procedures = procedures;
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((procedures == null) ? 0 : procedures.hashCode());
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
		Ams_Procedures other = (Ams_Procedures) obj;
		if (active != other.active)
			return false;
		if (id != other.id)
			return false;
		if (procedures == null) {
			if (other.procedures != null)
				return false;
		} else if (!procedures.equals(other.procedures))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ams_Procedures [id=" + id + ", procedures=" + procedures + ", active=" + active + "]";
	}
	
	
	
	
	

}
