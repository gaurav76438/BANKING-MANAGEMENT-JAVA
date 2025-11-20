import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // Create account
    public void createAccount(Account acc) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO accounts(name, balance) VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, acc.getName());
            ps.setDouble(2, acc.getBalance());
            ps.executeUpdate();
            System.out.println("Account created successfully!");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View all accounts
    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM accounts";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Account(
                    rs.getInt("accNo"),
                    rs.getString("name"),
                    rs.getDouble("balance")
                ));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Deposit
    public void deposit(int accNo, double amount) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE accounts SET balance = balance + ? WHERE accNo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setInt(2, accNo);
            ps.executeUpdate();
            System.out.println("Amount deposited successfully!");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Withdraw
    public void withdraw(int accNo, double amount) {
        try {
            Connection conn = DBConnection.getConnection();

            // First check balance
            String checkSql = "SELECT balance FROM accounts WHERE accNo = ?";
            PreparedStatement ps1 = conn.prepareStatement(checkSql);
            ps1.setInt(1, accNo);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");

                if (balance >= amount) {
                    String updateSql = "UPDATE accounts SET balance = balance - ? WHERE accNo = ?";
                    PreparedStatement ps2 = conn.prepareStatement(updateSql);
                    ps2.setDouble(1, amount);
                    ps2.setInt(2, accNo);
                    ps2.executeUpdate();
                    System.out.println("Withdraw successful!");
                } else {
                    System.out.println("Insufficient balance!");
                }
            } else {
                System.out.println("Account not found!");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete account
    public void deleteAccount(int accNo) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM accounts WHERE accNo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accNo);
            ps.executeUpdate();
            System.out.println("Account deleted successfully!");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
