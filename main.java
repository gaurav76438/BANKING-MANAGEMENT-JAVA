import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AccountDAO dao = new AccountDAO();

        while (true) {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Delete Account");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    double bal = sc.nextDouble();
                    dao.createAccount(new Account(name, bal));
                    break;

                case 2:
                    List<Account> accounts = dao.getAllAccounts();
                    System.out.println("\nACCNO | NAME | BALANCE");
                    for (Account a : accounts) {
                        System.out.println(a.getAccNo() + " | " 
                            + a.getName() + " | " 
                            + a.getBalance());
                    }
                    break;

                case 3:
                    System.out.print("Enter account no: ");
                    int acc1 = sc.nextInt();
                    System.out.print("Enter amount: ");
                    double amt1 = sc.nextDouble();
                    dao.deposit(acc1, amt1);
                    break;

                case 4:
                    System.out.print("Enter account no: ");
                    int acc2 = sc.nextInt();
                    System.out.print("Enter amount: ");
                    double amt2 = sc.nextDouble();
                    dao.withdraw(acc2, amt2);
                    break;

                case 5:
                    System.out.print("Enter account no to delete: ");
                    int acc3 = sc.nextInt();
                    dao.deleteAccount(acc3);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
