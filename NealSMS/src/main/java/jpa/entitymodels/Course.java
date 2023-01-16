package jpa.entitymodels;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author willw
 *The Course entity. Has attributes cId, an int, String cName and String cInstructorName.
 *Contains an empty constructor, a parameterized constructor, getters, setters, toString, toHashCode, and equals methods.  
 */
@Entity
@Table
public class Course {
	
	@Id
	@Column(name ="id")
	private int cId;
	
	@Column(name ="name")
	private String cName;
	
	@Column(name ="Instructor")
	private String cInstructorName;
	
	public Course() {
		
	}

	public Course(int cId, String cName, String cInstructorName) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cName=" + cName + ", cInstructorName=" + cInstructorName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cId, cInstructorName, cName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return cId == other.cId && Objects.equals(cInstructorName, other.cInstructorName)
				&& Objects.equals(cName, other.cName);
	}
	
	
}
