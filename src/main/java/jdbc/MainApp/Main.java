package jdbc.MainApp;

import jdbc.model.Account;
import jdbc.model.UserDetail;
import jdbc.repository.AccountRepository;
import jdbc.repository.UserDetailRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepository();
        UserDetailRepository userDetailRepository = new UserDetailRepository();

        Account addAccount = new Account("tony","pass456");

        accountRepository.addAccount(addAccount);

        UserDetail userDetail = new UserDetail("tom", "johnson", "tom@yahoo.com", addAccount);

        userDetailRepository.addUserDetail(userDetail);

        UserDetail userDetail1 = userDetailRepository.getUserDetail(addAccount);
        System.out.println("addacount:"+userDetail1);

        List<UserDetail> allUserDetail = userDetailRepository.getAllUserDetail();
        System.out.println("all user detail");
        for (UserDetail userDetail2 : allUserDetail ) {
            System.out.println(userDetail2);

            accountRepository.deleteAccount("tony");
        }
    }
}
