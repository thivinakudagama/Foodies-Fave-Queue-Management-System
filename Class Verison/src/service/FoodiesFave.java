package service;

import model.Customer;
import model.FoodQueue;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FoodiesFave {
    // Total number of customers allowed in all queues
    static int totalQueueCustomers = 10;
    // Initial amount of burgers in stock
    private static int burgerAmount = 50;
    // Price of each burger
    private static int burgerPrice = 650;
    // Array to store the queues
    private static FoodQueue[] queueContainer = new FoodQueue[3];
    // Queue to hold customers waiting when all queues are full
    private static FoodQueue waitingQueue = new FoodQueue();
    // File to store cashier data
    static File logFile = new File("cashier-data.txt");

    static Scanner scn = new Scanner(System.in);
    // Initializing the queueContainer array with queues of different capacities
    static {
        queueContainer[0] = new FoodQueue(2);
        queueContainer[1] = new FoodQueue(3);
        queueContainer[2] = new FoodQueue(5);
    }
    // Method to add a customer to the appropriate queue
    public static void addCustomers() {

        Customer customer = createCustomer();
        // Get the index of the shortest queue
        int shortestQueueIndex = getShortestQueueIndex();
        // Get the index of the shortest queue
        if (shortestQueueIndex != -1) {
            queueContainer[shortestQueueIndex].addCustomer(customer);
            System.out.println("Customer added to Queue " + (shortestQueueIndex + 1) + " successfully.");
        } else {
            // If no queue is available, add the customer to the waiting queue
            waitingQueue.addCustomer(customer);
            System.out.println("Customer added to Waiting Queue successfully.");
        }
    }

    // Method to get the index of the shortest queue
    public static int getShortestQueueIndex() {
        // Get the lengths of all queues
        int shortestQueueLength = Math.min(queueContainer[0].getNumCustomers(), Math.min(queueContainer[1].getNumCustomers(), queueContainer[2].getNumCustomers()));
        // Adjust the shortestQueueLength if a shorter queue is already full
        if (shortestQueueLength == 2 && queueContainer[0].isFull()) {
            shortestQueueLength = Math.min(queueContainer[1].getNumCustomers(), queueContainer[2].getNumCustomers());
        }
        if (shortestQueueLength == 3 && queueContainer[1].isFull()) {
            shortestQueueLength = queueContainer[2].getNumCustomers();
        }

        if (shortestQueueLength < 2 && queueContainer[0].getNumCustomers() == shortestQueueLength) {
            return 0;
        } else if (shortestQueueLength < 3 && queueContainer[1].getNumCustomers() == shortestQueueLength) {
            return 1;
        } else if (shortestQueueLength < 5 && queueContainer[2].getNumCustomers() == shortestQueueLength) {
            return 2;
        } else {
            return -1;
        }
        // Return -1 if no valid queue index is found
    }

    // Method to create a new customer
    public static Customer createCustomer() {
        System.out.println("\nEnter customer details --> ");

        System.out.println("Enter customer's first name:");
        String firstName = scn.next();

        System.out.println("Enter customer's surname:");
        String secondName = scn.next();

        System.out.println("Enter No of Burgers Required(maximum 5):");
        int noOfBurgersRequired = scn.nextInt();

        Customer customer = new Customer(firstName, secondName, noOfBurgersRequired);
        return customer;

    }

    // Method to view all queues and their statuses
    public static void viewAllQueues() {
        System.out.println("*****************");
        System.out.println("*\tCashiers\t*");
        System.out.println("*****************");

        StringBuffer line = new StringBuffer("\t");
        for (int row = 0; row < 5; row++) {
            line.append(generatePrintLine(0, row)).append("\t");
            line.append(generatePrintLine(1, row)).append("\t");
            line.append(generatePrintLine(2, row)).append("\t");
            line.append("\n\t");
        }
        System.out.println(line);
    }

    // Method to generate a line for displaying the queues' statuses
    private static String generatePrintLine(int index, int row) {
        String displaySymbol = queueContainer[index].getNumCustomers() > row ? "O" : "X";
        return queueContainer[index].getMaxCapacity() > row ? displaySymbol : "";
    }

    // Method to view empty queues
    public static void viewEmptyQueues() {
        StringBuilder line = new StringBuilder("Empty Queues: ");
        for (int i = 0; i < 3; i++) {
            if (queueContainer[i].getNumCustomers() == 0) {
                line.append(i + 1).append(", ");
            }
        }
        if (line.length() > 14) {
            line.setLength(line.length() - 2);
        } else {
            line.append("None");
        }
        line.append("\n");
        System.out.println(line);
    }

    // Method to view the remaining burgers in stock
    public static void viewRemainingBurgers() {
        System.out.printf("\nYou have remaining %d burgers\n", burgerAmount);
    }

    // Method to add burgers to the stock
    public static void addBurgersStock() {
        System.out.println("How many burgers you want to add to the stock  :");
        int amount = scn.nextInt();
        burgerAmount += amount;
        System.out.println("Burger stocks updated");
    }

    // Method to remove a customer from a queue
    public static void removeCustomer() {
        System.out.println("\nSelect customer details --> ");

        System.out.println("Enter queue name: 1, 2 or 3");
        int queueNumber = scn.nextInt();

        if (queueNumber > 3) {
            System.out.println("Invalid queue name");
        } else if (queueContainer[queueNumber - 1].isEmpty()) {
            System.out.println("No customers in queue: " + queueNumber);
        } else {
            System.out.println("Enter customer id: ");
            displayCustomers(queueNumber - 1);
            int customerLocation = scn.nextInt();

            if (customerLocation - 1 < queueContainer[queueNumber - 1].getNumCustomers()) {
                queueContainer[queueNumber - 1].removeCustomer(customerLocation - 1);
                System.out.println("Customer id: " + customerLocation + " removed from the Queue " + queueNumber + " successfully");
            } else {
                System.out.println("Invalid customer id");
            }
        }
    }

    // Method to display the customers in a queue
    private static void displayCustomers(int queueNumber) {
        int id = 1;
        StringBuilder line = new StringBuilder();
        for (Customer customer : queueContainer[queueNumber].getQueue()) {
            line.append(id).append(" : ").append(customer.getFirstName()).append(" ").append(customer.getSecondName());
            line.append(" >> ").append(customer.getNoOfBurgerRequired()).append("\n");
            id++;
        }
        System.out.println(line);
    }

    // Method to remove the served customer from a queue
    public static void removeServedCustomer() {

        System.out.println("Select queue name: 1, 2 or 3");
        int queueNumber = scn.nextInt();

        if (queueNumber > 3) {
            System.out.println("Invalid queue name");
        } else if (queueContainer[queueNumber - 1].isEmpty()) {
            System.out.println("No customers in queue: " + queueNumber);
        } else {
            System.out.println("Serving to customer in Queue " + queueNumber);
            Customer customer = queueContainer[queueNumber - 1].getQueue().get(0);
            serveCustomer(queueNumber - 1, customer.getNoOfBurgerRequired());
            System.out.println("Served " + customer.getNoOfBurgerRequired() + " burgers to customer in Queue " + queueNumber + " successfully");
            queueContainer[queueNumber - 1].removeCustomer(0);
            System.out.println("Customer id: 1 removed from the Queue " + queueNumber + " successfully");

            if (!waitingQueue.isEmpty()) {
                queueContainer[queueNumber - 1].addCustomer(waitingQueue.getQueue().get(0));
                waitingQueue.getQueue().remove(0);
                System.out.println("New customer added to Queue " + queueNumber + " from waiting Queue");
            }
        }
    }

    // Method to serve the customer and deduct the burgers from the stock
    private static void serveCustomer(int queue, int noOfBurgerRequired) {
        burgerAmount -= noOfBurgerRequired;
        queueContainer[queue].addToIncome(noOfBurgerRequired * burgerPrice);
        System.out.println("Got " + noOfBurgerRequired + " from the store. Remaining " + burgerAmount);
    }

    // Method to view the income of each queue
    public static void incomeOfEachQueue() {
        System.out.println("Income of Queue 1 >> Rs " + queueContainer[0].getIncome());
        System.out.println("Income of Queue 2 >> Rs " + queueContainer[1].getIncome());
        System.out.println("Income of Queue 3 >> Rs " + queueContainer[2].getIncome());
    }


    //    public static void sortedCustomer(){
//        try{
//            System.out.println("Which cashier do you want to sort  :");
//            int cNum = scn.nextInt();
//            if(cNum==1){
//                String[] Q1temparray = new String[]{"X","X"};
//                int cnt = 0;
//                for (int y = 0; y < 2; y++) {
//                    if (NoOfQueueCustomers1[y] != null) {
//                        Q1temparray[y] = NoOfQueueCustomers1[y];
//                        cnt++;
//                    }
//                }
//                String temp;
//                for (int i = 0; i < cnt; i++) {
//                    for (int j = i + 1; j < cnt; j++) {
//                        if (Q1temparray[i].compareTo(Q1temparray[j]) > 0) {
//                            temp = Q1temparray[i];
//                            Q1temparray[i] = Q1temparray[j];
//                            Q1temparray[j] = temp;
//                        }
//                    }
//                }
//                System.out.println("After sorted cashier 1  :");
//                for (String name : Q1temparray) {
//                    System.out.println(name);
//                }
//            }
//            else if(cNum==2){
//                String[] Q2temparray = new String[]{"X","X","X"};
//                int cnt = 0;
//                for (int y = 0; y < 3; y++) {
//                    if (NoOfQueueCustomers2[y] != null) {
//                        Q2temparray[y] = NoOfQueueCustomers2[y];
//                        cnt++;
//                    }
//                }
//                String temp;
//                for (int i = 0; i < cnt; i++) {
//                    for (int j = i + 1; j < cnt; j++) {
//                        if (Q2temparray[i].compareTo(Q2temparray[j]) > 0) {
//                            temp = Q2temparray[i];
//                            Q2temparray[i] = Q2temparray[j];
//                            Q2temparray[j] = temp;
//                        }
//                    }
//                }
//                System.out.println("After sorted cashier 2  :");
//                for (String name : Q2temparray) {
//                    System.out.println(name);
//                }
//            }else if(cNum==3){
//                String[] Q3temparray = new String[]{"X","X","X","X","X"};
//                int cnt = 0;
//                for (int y = 0; y < 5; y++) {
//                    if (NoOfQueueCustomers3[y] != null) {
//                        Q3temparray[y] = NoOfQueueCustomers3[y];
//                        cnt++;
//                    }
//                }
//                String temp;
//                for (int i = 0; i < cnt; i++) {
//                    for (int j = i + 1; j < cnt; j++) {
//                        if (Q3temparray[i].compareTo(Q3temparray[j]) > 0) {
//                            temp = Q3temparray[i];
//                            Q3temparray[i] = Q3temparray[j];
//                            Q3temparray[j] = temp;
//                        }
//                    }
//                }
//                System.out.println("After sorted cashier 3  :");
//                for (String name : Q3temparray) {
//                    System.out.println(name);
//                }
//            }else{
//                System.out.println("Invalid Input");
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

    // Method to store data in a file
    public static void storeData() {
        try {
            if (logFile.createNewFile()) {
                System.out.println("Log file created");
                writeToFile();
            } else {
                System.out.println("File already exists.");
                Scanner sc = new Scanner(System.in);

                System.out.println("Do you want to overwrite?(Y/N)");
                String overwriteChoice = sc.nextLine();
                if (overwriteChoice.equalsIgnoreCase("y")) {
                    writeToFile();
                    System.out.println("Data stored successfully!!");
                } else if (overwriteChoice.equalsIgnoreCase("n")) {
                    System.out.println("Overwrite terminated");
                } else {
                    System.out.println("Invalid input.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }

    // Method to write data to the file
    private static void writeToFile() {
        try (FileWriter logWrite = new FileWriter("cashier-data.txt")) {
            logWrite.write(generateCustomerLine(0));
            logWrite.write(generateCustomerLine(1));
            logWrite.write(generateCustomerLine(2));
            logWrite.write(String.valueOf(burgerAmount));
        } catch (IOException e) {
            System.out.println("Error occurred.");
        }
    }

    // Method to generate a line for storing customer data
    private static String generateCustomerLine(int i) {
        StringBuilder line = new StringBuilder();
        for (Customer customer : queueContainer[i].getQueue()) {
            line.append(customer.getFirstName()).append(" ").append(customer.getSecondName()).append(" ");
            line.append(customer.getNoOfBurgerRequired()).append(",");
        }
        line.setLength(line.length() - 1);
        line.append("\n");
        return line.toString();
    }

    // Method to load data from the file
    public static void loadDataFromFile() {
        try {
            if (logFile.exists()) {
                System.out.println("This action cannot be undone!!!");
                Scanner sc = new Scanner(System.in);

                System.out.println("Do you want to overwrite?(Y/N)");
                String overwriteChoice = sc.nextLine();
                if (overwriteChoice.equalsIgnoreCase("y")) {
                    BufferedReader br = new BufferedReader(new FileReader(logFile));
                    String line;

                    int lineNumber = 0;
                    while ((line = br.readLine()) != null) {
                        if (!line.isEmpty()) {
                            if (lineNumber < 3) {
                                addToFoodQueue(lineNumber, line.split(","));
                            } else {
                                burgerAmount = Integer.parseInt(line.replaceAll("\\s", ""));
                                System.out.println("Burger amount updated: " + burgerAmount);
                            }
                            System.out.println(line);
                            lineNumber++;
                        }
                    }
                    System.out.println("Data successfully loaded.");
                } else if (overwriteChoice.equalsIgnoreCase("n")) {
                    System.out.println("Overwrite terminated");
                } else {
                    System.out.println("Invalid Input.");
                }
            } else {
                System.out.println("No log file to retrieve data from.");
            }
        } catch (IOException e) {
            System.out.println("Error occurred.");
        }
    }

    // Method to add customers to the food queue from the loaded data
    private static void addToFoodQueue(int lineNumber, String[] queue) {
        String[] selectedEntries = new String[queueContainer[lineNumber].getMaxCapacity()];
        System.arraycopy(queue, 0, selectedEntries, 0, Math.min(queue.length, queueContainer[lineNumber].getMaxCapacity()));

        for (String entry : selectedEntries) {
            List<String> data = Arrays.stream(entry.split(" ")).map(s -> s.replaceAll("\\s", "")).collect(Collectors.toList());
            queueContainer[lineNumber].addCustomer(new Customer(data.get(0), data.get(1), Integer.parseInt(data.get(2))));
            System.out.println("Customer added to Queue: " + (lineNumber + 1));
        }
    }

    // Method to sort the customers in each queue
    public static void sortedCustomer() {
        Collections.sort(queueContainer[0].getQueue());
        Collections.sort(queueContainer[1].getQueue());
        Collections.sort(queueContainer[2].getQueue());
        System.out.println("Customers are sorted");
    }
}
