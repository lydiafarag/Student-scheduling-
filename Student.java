import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Course> desiredCourseList = new ArrayList<>();

    public Student (String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addDesiredCourse(Course course){
        if (!this.desiredCourseList.contains(course)){
            this.desiredCourseList.add(course);
        }
        else{
            return;
        }
    }

    public boolean validSchedule(ArrayList<Course> L) {
        if (L.size() == 5){
            for (int i = 0; i < L.size(); i++) {
                for (int j = i+1; j < L.size(); j++) {
                    if (!this.desiredCourseList.contains(L.get(i)) || (L.get(i).conflictsWith(L.get(j)))) {
                        return false;
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    public static ArrayList<ArrayList<Course>> powerSet(ArrayList<Course> courses) {
        ArrayList<ArrayList<Course>> schedule_subsets = new ArrayList<ArrayList<Course>>();
        int max = 1 << courses.size();
        for (int i = 0; i < max; i++) {
            ArrayList<Course> subset = new ArrayList<Course>();
            for (int j = 0; j < courses.size(); j++) {
                if (((i >> j) & 1) == 1) {
                    subset.add(courses.get(j));
                }
            }
            schedule_subsets.add(subset);
        }
        return schedule_subsets;
    }

    public int numDays(ArrayList<Course> schedule){
        ArrayList<Integer> dayList = new ArrayList<Integer>();
        for(Course course: schedule){
            for (int j = 0; j<=5; j++){
                if (course.hasLectureOn(j) && !(dayList.contains(j))){
                    dayList.add(j);
                }
            }
        }
        return dayList.size();
    }

    public ArrayList<Course> getBestSchedule(){
        ArrayList<ArrayList<Course>> schedule_subsets = powerSet(this.desiredCourseList);
        ArrayList<Course> bestSchedule = new ArrayList<Course>();
        int min = 5;
        for (ArrayList<Course> schedule : schedule_subsets) {
            if (validSchedule(schedule) == true) {
                if (numDays(schedule) < min) {
                    min = numDays(schedule);
                    bestSchedule = schedule;
                }
            }
        }
        if (bestSchedule.isEmpty()){
            return null;
        }
        return bestSchedule;
    }}


