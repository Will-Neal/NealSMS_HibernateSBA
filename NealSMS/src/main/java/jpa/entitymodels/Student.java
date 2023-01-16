package jpa.entitymodels;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author willw
 *The Student entity. Has attributes String sEmail, String sName, sPassword, and sCourse, a list of Course objects.
 *These entities have column annotations to control the length of email and the column names when mapped to database.
 *sCourses has a many to many relationship with the course class. 
 *Contains an empty constructor, a parameterized constructor, getters, setters, toString, toHashCode, and equals methods.  
 */
@Entity
@Table
public class Student {
	
	@Id
	@Column(name ="email", length = 50)
	private String sEmail;
	
	@Column(name ="name")
	private String sName;
	
	@Column(name ="password")
	private String sPassword;
	
	@ManyToMany(targetEntity = Course.class)
	private List<Course> sCourses;
	
	public Student() {
		
	}

	public Student(String sEmail, String sName, String sPassword, List<Course> sCourses) {
		super();
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPassword = sPassword;
		this.sCourses = sCourses;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}

	@Override
	public String toString() {
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPassword=" + sPassword + ", sCourses=" + sCourses
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(sCourses, sEmail, sName, sPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(sCourses, other.sCourses) && Objects.equals(sEmail, other.sEmail)
				&& Objects.equals(sName, other.sName) && Objects.equals(sPassword, other.sPassword);
	}
	
	
	
}
