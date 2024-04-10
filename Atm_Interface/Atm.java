package Atm_Interface;
import java.util.Scanner;
class User {
    private String userId;
    private String pin;
    private double balance;
    private String transactionHistory;

    public User(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = "";
    }

    public String getUserId() {
        return userId;
    }

    public boolean authentication(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void addToTransactionHistory(String transaction) {
        transactionHistory = transactionHistory+transaction + "\n";
    }

    public String getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance = balance+amount;
        addToTransactionHistory("Deposit: +" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance = balance-amount;
            addToTransactionHistory("Withdrawl: -" + amount);
            return true;
        } else {
            System.out.println("Insufficient amount!");
            return false;
        }
    }

    public boolean transfer(User recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            addToTransactionHistory("Transfer to " + recipient.getUserId() + ": -" + amount);
            recipient.addToTransactionHistory("Transfer from " + getUserId() + ": +" + amount);
            return true;
        } else {
            return false;
        }
    }
}

public class Atm 
{
	private static void withdraw() {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter withdrawal amount: ");
        double amount = scn.nextDouble();

        if (currentUser.withdraw(amount)) {
            System.out.println("Withdrawal successful. Current Balance: " + currentUser.getBalance());
        }
    }

    private static void deposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        currentUser.deposit(amount);
        System.out.println("Deposit successful. Current Balance: " + currentUser.getBalance());
    }

    private static void transfer() {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter recipient's User ID: ");
        String recipientId = scn.nextLine();
        User recipient = (currentUser.getUserId().equals("user1")) ? currentUser : null;

        if (recipient != null) {
            System.out.print("Enter transfer amount: $");
            double amount = scn.nextDouble();

            if (currentUser.transfer(recipient, amount)) {
                System.out.println("Transfer successful. Current Balance: " + currentUser.getBalance());
            }
        } else {
            System.out.println("Recipient not found. Please try again.");
        }
    }
    private static void TransactionHistory() {
        System.out.println("Transaction History:\n" + currentUser.getTransactionHistory());
    }
    private static User currentUser;

    public static void main(String[] args) {
        User user = new User("Sonu", "1234",0);
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scn.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scn.nextLine();

        if (userId.equals(user.getUserId()) && user.authentication(pin)) {
            currentUser = user;
        } else {
            System.out.println("Invalid credentials....");
            System.exit(0);
        }
        displayMenu();
    }

    private static void displayMenu() {
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("\nATM Menu:"+"\n1. View Transaction History"+"\n2. Withdraw"
            					+"\n3. Deposit"+"\n4. Transfer"+"\n5. Quit");
            System.out.print("Enter your choice: ");
            int choice = scn.nextInt();

            switch (choice) {
            	case 1:
            		TransactionHistory();
                break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Exiting ATM....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

