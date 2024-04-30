package orm;

import lombok.Data;

@Data
public class Reports {
    private int student_id;
    private int course_id;
    private String course_name;
    private int usual_grades;
    private int final_grades;
    private int total_grades;
    private float gpa;
    private float score;
}
