package banking.dao;

import banking.entity.Account;

import java.util.ArrayList;

public class AccountDao {

    private ArrayList<Account> accounts = new ArrayList<>();

    public void create(Account account) {
        account.setId(accounts.size());
        accounts.add(account);
    }

    public Account read(int id){
        return accounts.get(id);
    }

    public ArrayList<Account> readAll (){
        return accounts;
    }

    public void update(Account account) {
        accounts.set(account.getId(), account);
    }

    public void delete(Account account) {
        accounts.remove(account.getId());
    }
}
