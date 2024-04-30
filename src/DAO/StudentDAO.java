package DAO;

import orm.Student;

import java.util.List;

public interface StudentDAO {
    public void add(Student student);
    public void update(Student student);
    public void update2(int student_id, float score);
    public void delete(int id);
    public Student get(int id);
    public List<Student> list();
}
