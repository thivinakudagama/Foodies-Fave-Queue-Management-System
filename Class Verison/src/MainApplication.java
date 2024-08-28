import service.FoodiesFave;

import java.util.Scanner;

public class MainApplication {
    final static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        while (true) {

            System.out.println("========================================");
            System.out.println("|\tFoodies Fave Food Center Options\t|");
            System.out.println("========================================");

            System.out.println("\n" +

                    "100 or VFQ: View all Queues.\n" +
                    "101 or VEQ: View all Empty Queues.\n" +
                    "102 or ACQ: Add customer to a Queue.\n" +
                    "103 or RCQ: Remove a customer from a Queue.\n" +
                    "104 or PCQ: Remove a served customer.\n" +
                    "105 or VCS: View Customers Sorted in alphabetical order.\n" +
                    "106 or SPD: Store Program Data into file.\n" +
                    "107 or LPD: Load Program Data from file.\n" +
                    "108 or STK: View Remaining burgers Stock.\n" +
                    "109 or AFS: Add burgers to Stock.\n" +
                    "110 or IFQ: Print the Income of Each Queue.\n" +
                    "999 or EXT: Exit the Program");

            System.out.println("===================================");
            System.out.println("Choose an option : ");

            try {
                String selectedValue = scn.next();
                selectedValue = selectedValue.toUpperCase();

                switch (selectedValue) {
                    case "100":
                    case "VFQ":
                        FoodiesFave.viewAllQueues();
                        break;
                    case "101":
                    case "VEQ":
                        FoodiesFave.viewEmptyQueues();
                        break;
                    case "102":
                    case "ACQ":
                        FoodiesFave.addCustomers();
                        break;
                    case "103":
                    case "RCQ":
                        FoodiesFave.removeCustomer();
                        break;
                    case "104":
                    case "PCQ":
                        FoodiesFave.removeServedCustomer();
                        break;
                    case "105":
                    case "VCS":
                        FoodiesFave.sortedCustomer();
                        break;
                    case "106":
                    case "SPD":
                        FoodiesFave.storeData();
                        break;
                    case "107":
                    case "LPD":
                        FoodiesFave.loadDataFromFile();
                        break;
                    case "108":
                    case "STK":
                        FoodiesFave.viewRemainingBurgers();
                        break;
                    case "109":
                    case "AFS":
                        FoodiesFave.addBurgersStock();
                        break;
                    case "110":
                    case "IFQ":
                        FoodiesFave.incomeOfEachQueue();
                        break;
                    case "999":
                    case "EXT":
                        System.exit(0);
                    default:
                        System.out.println("Invalid Input");
                }
            } catch (Exception e) {
                System.out.println("Error Found. " + e);
            }

        }
    }
}
