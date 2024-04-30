package DAO;

import orm.Reports;

import java.util.List;

public interface ReportsDAO {
    public Reports get(int student_id, int course_id);
    public void update(Reports reports);
    public List<Reports> list1(String type, int student_id);
    public List<Reports> list2(int course_id);
    public List<Reports> list3(int course_id);
}
