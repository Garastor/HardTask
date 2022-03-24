package banking.service;

import banking.dao.AccountDao;
import banking.entity.Account;

import java.util.ArrayList;

public class AccountService {

    private final AccountDao accountDao = new AccountDao();

    public Account findAccount (long cardNumber, int cardPin) {
        return accountDao.read(cardNumber, cardPin);
    }

    public void saveAccount (Account account) {
        accountDao.create(account);
        System.out.println(account);
    }

    public void deleteAccount (Account account) {
        accountDao.delete(account);
    }

    public void updateAccount (Account account) {
        accountDao.update(account);
    }

    public Account createAccount () {
        CreditCardService creditCardService = new CreditCardService();
        Account account = new Account();
        account.setCreditCard(creditCardService.createCard());
        return account;
    }

    public boolean loginAccount (long cardNumber, int cardPin) {
        boolean verify = false;
        ArrayList<Account> accounts = accountDao.readAll();
        for (Account ac:accounts){
            if (ac.getCreditCard().getNumber() == cardNumber && ac.getCreditCard().getPin() == cardPin) {
                verify = true;
                break;
            }
        }
        return verify;
    }

}
