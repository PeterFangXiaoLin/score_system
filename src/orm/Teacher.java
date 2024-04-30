package orm;

import lombok.Data;

@Data
public class Teacher {
    private int teacherId;
    private String teacherName;
    private String teacherSex;
    private int teacherAge;
    private String teacherTitle;
}
