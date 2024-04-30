package DAO;

import orm.Course;

import java.util.List;

public interface CourseDAO {
    public void add(Course course);
    public void update(Course course);
    public void delete(int id);
    public Course get(int id);
    public List<Course> list();

    public List<Course> list(String teacher_name);
}
