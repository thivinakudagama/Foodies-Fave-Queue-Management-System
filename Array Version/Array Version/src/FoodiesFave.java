import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FoodiesFave {
    static Scanner scn = new Scanner(System.in);
    static String[] array1 = {"X", "X","X"};
    static String[] array2 = {"X", "X", "X","X"};
    static String[] array3 = {"X", "X", "X", "X", "X","X"};
    static String[] cusNameArray1=new String[2];
    static String[] cusNameArray2=new String[3];
    static String[] cusNameArray3=new String[5];
    static File logFile = new File("cashier-data.txt");
    static int burgerAmount=50;

    public static void main(String[] args) {
        int x = 0;
        while (x < 1) {
            displayMenu();
            System.out.println("Enter Your Option  :");
            String opt = scn.next();
            opt = opt.toUpperCase();
            switch (opt) {
                case "100":
                case "VFQ":
                    viewAllQueues();
                    break;
                case "101":
                case "VEQ":
                    viewEmptyQueues();
                    break;
                case "102":
                case "ACQ":
                    addCustomers();
                    break;
                case "103":
                case "RCQ":
                    removeCustomer();
                    break;
                case "104":
                case "PCQ":
                    removeServedCustomer();
                    break;
                case "105":
                case "VCS":
                    sortedCustomer();
                    break;
                case "106":
                case "SPD":
                    storeData();
                    break;
                case "107":
                case "LPD":
                    loadDataFromFile();
                    break;
                case "108":
                case "STK":
                    viewRemainingBurgers();
                    break;
                case "109":
                case "AFS":
                    addBurgersStock();
                    break;
                case "999":
                case "EXT":
                    System.exit(0);
            }
        }
    }
    public static void displayMenu() {
        System.out.println("========================================");
        System.out.println("|\tFoodies Fave Food Center Options\t|");
        System.out.println("========================================");
        System.out.println("\n100 or VFQ: View all Queues.\n" +
                "101 or VEQ: View all Empty Queues.\n" +
                "102 or ACQ: Add customer to a Queue.\n" +
                "103 or RCQ: Remove a customer from a Queue.\n" +
                "104 or PCQ: Remove a served customer.\n" +
                "105 or VCS: View Customers Sorted in alphabetical order.\n" +
                "106 or SPD: Store Program Data into file.\n" +
                "107 or LPD: Load Program Data from file.\n" +
                "108 or STK: View Remaining burgers Stock.\n" +
                "109 or AFS: Add burgers to Stock.\n" +
                "999 or EXT: Exit the Program");
        System.out.println("===================================");
    }
    public static void viewAllQueues() {
        System.out.println("*****************");
        System.out.println("*\tCashiers\t*");
        System.out.println("*****************");
        for (int i = 0; i < 10; i++) {
            if (i < 2) {
                System.out.print(array1[i] + "\t");
            } else {
                System.out.print("\t");
            }
            if (i < 3) {
                System.out.print(array2[i] + "\t");
            } else {
                System.out.print("\t");
            }
            if (i < 5) {
                System.out.print(array3[i]);
            }
            System.out.println();
        }
    }
    public static void viewEmptyQueues() {
        if (array1[0] == "X") {
            System.out.println("Cashier 1 is empty");
        } else {
            System.out.println("Cashier 1 is busy");
        }
        if (array2[0] == "X") {
            System.out.println("Cashier 2 is empty");
        } else {
            System.out.println("Cashier 2 is busy");
        }
        if (array3[0] == "X") {
            System.out.println("Cashier 3 is empty");
        } else {
            System.out.println("Cashier 3 is busy");
        }
    }
    public static void addCustomers() {
        try {
            for (int v = 1; v > -1; v++) {
                System.out.println("Enter Queue number you wish to add customer(1-3) or Press 0 to come back to menu  :");
                int qNum = scn.nextInt();
                if (qNum == 1) {
                    if (array1[1] == "X") {
                        for (int i = 0; i < 2; i++) {
                            if (array1[i] == "X") {
                                System.out.println("Enter Customer name  :");
                                String cName = scn.next();
                                array1[i] = "O";
                                cusNameArray1[i] = cName;
                                System.out.println(cusNameArray1[i] + " added to the queue 1");
                                break;
                            }
                        }
                    } else {
                        System.out.println("Queue 1 is full.Try a another queue.");
                        break;
                    }
                } else if (qNum == 2) {
                    if (array2[2] == "X") {
                        for (int i = 0; i < 3; i++) {
                            if (array2[i] == "X") {
                                System.out.println("Enter Customer name  :");
                                String cName = scn.next();
                                array2[i] = "O";
                                cusNameArray2[i] = cName;
                                System.out.println(cusNameArray2[i] + " added to the queue 2");
                                break;
                            }
                        }
                    } else {
                        System.out.println("Queue 2 is full.Try a another queue.");
                        break;
                    }
                } else if (qNum == 3) {
                    if (array3[4] == "X") {
                        for (int i = 0; i < 5; i++) {
                            if (array3[i] == "X") {
                                System.out.println("Enter Customer name  :");
                                String cName = scn.next();
                                array3[i] = "O";
                                cusNameArray3[i] = cName;
                                System.out.println(cusNameArray3[i] + " added to the queue 3");
                                break;
                            }
                        }
                    } else {
                        System.out.println("Queue 3 is full.Try a another queue.");
                        break;
                    }
                } else if (qNum == 0) {
                    break;

                } else {
                    System.out.println("Invalid queue number.");
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void removeCustomer() {
        int x=0;
        try {
            while(x<1) {
                System.out.println("Enter Cashier Number :");
                int cashNum = scn.nextInt();
                System.out.println("Enter the position of the Queue  :");
                int position = scn.nextInt();
                if (cashNum == 1) {
                    if (position - 1 < 2) {
                        if (array1[position - 1] == "X") {
                            System.out.println("There is no customer in this position");
                        } else {
                            array1[position - 1] = "X";
                            System.out.println("Removed customer from queue 1");
                        }
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
                if (cashNum == 2) {
                    if (position - 1 < 3) {
                        if (array2[position - 1] == "X") {
                            System.out.println("There is no customer in this position");
                        } else {
                            array2[position - 1] = "X";
                            System.out.println("Removed customer from queue 1");
                        }
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
                if (cashNum == 3) {
                    if (position - 1 < 5) {
                        if (array3[position - 1] == "X") {
                            System.out.println("There is no customer in this position");
                        } else if (array3[position - 1] == "O") {
                            array3[position - 1] = "X";
                            System.out.println("Removed customer from queue 1");
                        }
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void removeServedCustomer(){
        try{
            System.out.println("Enter Cashier Number :");
            int cashNum = scn.nextInt();
            if(cashNum==1){
                if(array1[0]=="X"){
                    System.out.println("There is no customer in the queue 1");
                }else{
                    for(int i=0;i<2;i++){
                        array1[i]=array1[i+1];
                    }
                    burgerAmount-=5;
                    System.out.println("Removed the served customer");
                }
            }
            else if(cashNum==2){
                if(array2[0]=="X"){
                    System.out.println("There is no customer in the queue 2");
                }else{
                    for(int i=0;i<3;i++){
                        array2[i]=array2[i+1];
                    }
                    burgerAmount-=5;
                    System.out.println("Removed the served customer");
                }
            }
            else if(cashNum==3){
                if(array3[0]=="X"){
                    System.out.println("There is no customer in the queue 3");
                }else{
                    for(int i=0;i<5;i++){
                        array3[i]=array3[i+1];
                    }
                    burgerAmount-=5;
                    System.out.println("Removed the served customer");
                }
            }
            else{
                System.out.println("Invalid Input");
            }
            if (burgerAmount<=10){
                System.out.println("****The stock of burgers is running low. Please replenish the stock.****");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void viewRemainingBurgers(){
        System.out.printf("\nYou have remaining %d burgers\n",burgerAmount);
    }
    public static void addBurgersStock(){
        System.out.println("How many burgers you want to add to the stock  :");
        int amnt = scn.nextInt();

        burgerAmount= burgerAmount+amnt;
        System.out.println("Burger stocks updated");
    }
    public static void sortedCustomer(){
        try{
            System.out.println("Which cashier do you wan to sort  :");
            int cNum = scn.nextInt();
            if(cNum==1){
                String[] Q1temparray = new String[]{"X","X"};
                int cnt = 0;
                for (int y = 0; y < 2; y++) {
                    if (cusNameArray1[y] != null) {
                        Q1temparray[y] = cusNameArray1[y];
                        cnt++;
                    }
                }
                String temp;
                for (int i = 0; i < cnt; i++) {
                    for (int j = i + 1; j < cnt; j++) {
                        if (Q1temparray[i].compareTo(Q1temparray[j]) > 0) {
                            temp = Q1temparray[i];
                            Q1temparray[i] = Q1temparray[j];
                            Q1temparray[j] = temp;
                        }
                    }
                }
                System.out.println("After sorted cashier 1  :");
                for (String name : Q1temparray) {
                    System.out.println(name);
                }
            }
            else if(cNum==2){
                String[] Q2temparray = new String[]{"X","X","X"};
                int cnt = 0;
                for (int y = 0; y < 3; y++) {
                    if (cusNameArray2[y] != null) {
                        Q2temparray[y] = cusNameArray2[y];
                        cnt++;
                    }
                }
                String temp;
                for (int i = 0; i < cnt; i++) {
                    for (int j = i + 1; j < cnt; j++) {
                        if (Q2temparray[i].compareTo(Q2temparray[j]) > 0) {
                            temp = Q2temparray[i];
                            Q2temparray[i] = Q2temparray[j];
                            Q2temparray[j] = temp;
                        }
                    }
                }
                System.out.println("After sorted cashier 2  :");
                for (String name : Q2temparray) {
                    System.out.println(name);
                }
            }else if(cNum==3){
                String[] Q3temparray = new String[]{"X","X","X","X","X"};
                int cnt = 0;
                for (int y = 0; y < 5; y++) {
                    if (cusNameArray3[y] != null) {
                        Q3temparray[y] = cusNameArray3[y];
                        cnt++;
                    }
                }
                String temp;
                for (int i = 0; i < cnt; i++) {
                    for (int j = i + 1; j < cnt; j++) {
                        if (Q3temparray[i].compareTo(Q3temparray[j]) > 0) {
                            temp = Q3temparray[i];
                            Q3temparray[i] = Q3temparray[j];
                            Q3temparray[j] = temp;
                        }
                    }
                }
                System.out.println("After sorted cashier 3  :");
                for (String name : Q3temparray) {
                    System.out.println(name);
                }
            }else{
                System.out.println("Invalid Input");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void storeData(){
        try{
            if (logFile.createNewFile()){
                System.out.println("Log file created");
                writeToFile(cusNameArray1,cusNameArray2,cusNameArray3);
            }else {
                System.out.println("File already exists.");
                Scanner sc = new Scanner(System.in);

                while (true){
                    System.out.println("Do you want to overwrite?(Y/N)");
                    String overwriteChoice = sc.nextLine();
                    if (overwriteChoice.equalsIgnoreCase("y")){
                        writeToFile(cusNameArray1,cusNameArray2,cusNameArray3);
                        System.out.println("Data stored successfully!!");
                        break;
                    } else if (overwriteChoice.equalsIgnoreCase("n")) {
                        break;
                    }else {
                        System.out.println("Invalid input.");
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }
    private static void writeToFile(String[] cusNameArray1, String[] cusNameArray2, String[] cusNameArray3) {
        try {
            FileWriter logWrite = new FileWriter("cashier-data.txt");
            logWrite.write(String.join(",",cusNameArray1)+'\n');
            logWrite.write(String.join(",",cusNameArray2)+'\n');
            logWrite.write(String.join(",",cusNameArray3)+'\n');
            logWrite.write(String.valueOf(burgerAmount));
            logWrite.close();
        }catch (IOException e) {
            System.out.println("Error occurred.");
        }
    }
    public static void loadDataFromFile(){
        try {
            if (logFile.exists()) {
                System.out.println("This action cannot be undone!!!");
                Scanner sc  = new Scanner(System.in);
                while (true) {
                    System.out.println("Do you want to overwrite?(Y/N)");
                    String overwriteChoice = sc.nextLine();
                    if (overwriteChoice.equalsIgnoreCase("y")) {
                        Path file = Path.of("cashier-data.txt");
                        String[] cashier1Loaded = Files.readAllLines(file).get(0).split(",");
                        String[] cashier2Loaded = Files.readAllLines(file).get(1).split(",");
                        String[] cashier3Loaded = Files.readAllLines(file).get(2).split(",");
                        burgerAmount = Integer.parseInt(Files.readAllLines(file).get(3));
                        System.arraycopy(cashier1Loaded, 0, cusNameArray1, 0, cusNameArray1.length);
                        System.arraycopy(cashier2Loaded, 0, cusNameArray2, 0, cusNameArray2.length);
                        System.arraycopy(cashier3Loaded, 0, cusNameArray3, 0, cashier3Loaded.length);
                        System.out.println("Data successfully loaded.");
                        break;
                    } else if (overwriteChoice.equalsIgnoreCase("n")) {
                        break;
                    }else {
                        System.out.println("Invalid Input.");
                    }
                }
            } else {
                System.out.println("No log file to retrieve data from.");
            }
        }catch (IOException e){
            System.out.println("Error occurred.");
        }
    }
}