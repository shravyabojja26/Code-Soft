import java.util.Scanner;

public class ATMInterface {

    public static void main(String[] args) {
        // Create a user bank account with an initial balance
        BankAccount userAccount = new BankAccount(1000.00); // Initial balance of $1000

        // Create an ATM instance linked to the user's bank account
        ATM atm = new ATM(userAccount);

        Scanner scanner = new Scanner(System.in);
        int choice;

        // Display the ATM menu and process user input
        do {
            atm.displayMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    atm.checkBalance();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\n----- ATM Menu -----");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println("Successfully withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        account.deposit(amount);
        System.out.println("Successfully deposited: $" + amount);
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + account.getBalance());
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Setting balance to $0.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}
