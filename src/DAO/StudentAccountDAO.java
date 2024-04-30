package DAO;

import orm.StudentAccount;

public interface StudentAccountDAO {
    public void update(StudentAccount studentAccount);

    public boolean select(StudentAccount studentAccount);
}
