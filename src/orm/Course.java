package orm;

import lombok.Data;

@Data
public class Course {
    private int course_id;
    private String course_name;
    private int course_theory;
    private int course_practice;
    private float course_score;
    private String course_type;
    private String teacher_name;
}
