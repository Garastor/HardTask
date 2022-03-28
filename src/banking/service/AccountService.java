package banking.service;

import banking.dao.AccountDao;
import banking.entity.Account;

import java.util.ArrayList;

public class AccountService {

    private final AccountDao accountDao = new AccountDao();

    public Account findAccount (int id) {
        return accountDao.read(id);
    }

    public void saveAccount () {
        CreditCardService creditCardService = new CreditCardService();
        Account account = new Account();
        account.setCreditCard(creditCardService.saveCard());
        accountDao.create(account);
        System.out.println(account);
    }

    public void deleteAccount (Account account) {
        accountDao.delete(account);
    }

    public void updateAccount (Account account) {
        accountDao.update(account);
    }

    public int loginAccount (long cardNumber, int cardPin) {
        int id = -1;
        ArrayList<Account> accounts = accountDao.readAll();
        for (Account ac:accounts){
            if (ac.getCreditCard().getNumber() == cardNumber && ac.getCreditCard().getPin() == cardPin) {
                id = ac.getId();
            }
        }
        return id;
    }

}
