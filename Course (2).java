import java.util.ArrayList;

public class Course {
	private String code;
	private ArrayList<Integer[]> lectures;
	
	/* check if lectures already has (day, hour) */
	private boolean contains(int day, int hour) {
		for(int i = 0; i < this.lectures.size(); i++) {
			if(this.lectures.get(i)[0] == day && this.lectures.get(i)[1] == hour) {
				return true;
			}
		}
		return false;
	}
	
	public Course(String courseCode) {
		this.code = courseCode;
		this.lectures = new ArrayList<Integer[]>();
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void addLecture(int day, int hour) {
		if (this.contains(day, hour)) {
			return;
		}
		Integer[] lecture = {day, hour};
		this.lectures.add(lecture);
	}
	
	public boolean hasLectureOn(int day) {
		for (int i = 0; i < this.lectures.size(); i++) {
			if (this.lectures.get(i)[0] == day) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasLectureAt(int day, int hour) {
		for (int i = 0; i < this.lectures.size(); i++) {
			if (this.lectures.get(i)[0] == day && this.lectures.get(i)[1] == hour) {
				return true;
			}
		}
		return false;
	}
	
	public boolean conflictsWith(Course other) {
		for (int i = 0; i < this.lectures.size(); i++) {
			if (other.hasLectureAt(this.lectures.get(i)[0], this.lectures.get(i)[1])) {
				return true;
			}
		}
		return false;
	}
	
	public void print_size() {
		System.out.println(this.lectures.size());
	}
}
