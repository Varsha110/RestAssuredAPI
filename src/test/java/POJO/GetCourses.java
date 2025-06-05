package POJO;

public class GetCourses {

	private String instructor;
	private String url;
	private String services;
	private CourseschildOfGetCourses courses;
	private String linkedIn;
	private String expertise;
	
	// Alt+Shift S >  to generate getter and setters
	
	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public CourseschildOfGetCourses getCourses() {
		return courses;
	}

	public void setCourses(CourseschildOfGetCourses courses) {
		this.courses = courses;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	
	public String getInstructor() {
		return instructor;
		
	}
 
}
