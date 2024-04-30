package DAO;

import orm.Teacher;

import java.util.List;

public interface TeacherDAO {
    /**
     * 增加
     */
    public void add(Teacher teacher);
    /**
     * 修改
     */
    public void update(Teacher teacher);
    /**
     * 删除
     *
     * @return
     */
    public boolean delete(int id);

    /**
     * 获取
     */
    public Teacher get(int id);

    /**
     * 查询
     */
    public List<Teacher> list();
}
