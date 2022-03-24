package banking.dao;

import banking.entity.Account;

import java.util.ArrayList;


public class AccountDao {

    private ArrayList<Account> accounts = new ArrayList<>();

    public void create(Account account) {
        accounts.add(account);
    }

    public Account read(long cardNumber, int cardPin){
        Account account = new Account();
        for (Account ac: accounts){
            if (ac.getCreditCard().getNumber() == cardNumber && ac.getCreditCard().getPin() == cardPin){
                account = ac;
            }
        }
        return account;
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
