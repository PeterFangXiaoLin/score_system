package DAO;

import orm.AdminAccount;

public interface AdminAccountDAO {
    public void update(AdminAccount adminAccount);

    public boolean select(AdminAccount adminAccount);
}
