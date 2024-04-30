package DAO;

import orm.TeacherAccount;

public interface TeacherAccountDAO {
    public void update(TeacherAccount teacherAccount);

    public boolean select(TeacherAccount teacherAccount);
}
