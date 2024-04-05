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
        Account retrievedAccount = accountRepository.getAccount("retrievedTony");
        System.out.println("info add account update");

        UserDetail userDetail = new UserDetail("tom", "johnson", "tom@yahoo.com", addAccount);
        userDetailRepository.addUserDetail(userDetail);
        UserDetail retrievedUserDetail = userDetailRepository.getUserDetail("TomUserDetail");
        System.out.println("info user detail update");



        List<UserDetail> allUserDetail = userDetailRepository.getAllUserDetail();
        System.out.println("all user detail");
        for (UserDetail user : allUserDetail ) {
            System.out.println(user);

            accountRepository.deleteAccount("tony");
            System.out.println("info delete account deleted");

            userDetailRepository.deleteUserDetail(2);
            System.out.println("info user detail deleted");
        }
    }
}
