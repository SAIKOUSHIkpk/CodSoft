import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            account.withdraw(amount);
            JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: $" + account.getBalance());
        } else {
            JOptionPane.showMessageDialog(null, "Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            account.deposit(amount);
            JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + account.getBalance());
        } else {
            JOptionPane.showMessageDialog(null, "Invalid deposit amount.");
        }
    }

    public void checkBalance() {
        JOptionPane.showMessageDialog(null, "Your balance is: $" + account.getBalance());
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}

public class ATMGUI {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance
        ATM atm = new ATM(account);

        JFrame frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        frame.getContentPane().add(panel);

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");

        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter withdrawal amount:");
                try {
                    double amount = Double.parseDouble(input);
                    atm.withdraw(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter deposit amount:");
                try {
                    double amount = Double.parseDouble(input);
                    atm.deposit(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atm.checkBalance();
            }
        });

        frame.setVisible(true);
    }
}
