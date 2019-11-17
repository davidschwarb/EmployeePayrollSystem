////////////////////////////////////////////////////////
// PayrollSystem Class
// Inheritence and Polymorphism Project (Assignment 1)
// Description: an Implemented payroll system that allows handling of employee data.
// Worked on By:
//     David Schwarb
//     Lynda Vo
//     Michael Karbowiack
//
package edu.umsl;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class PayrollSystem {

    private final int EMPLOYEE_NUM = 3;                             //Holds the number of employees
    private Employee employees[] = new Employee[EMPLOYEE_NUM];      //Creates an array which can hold 3 employees
    private boolean populated = false,
            isSaved = false,
            isEdited = false;

    public static void main(String[] args) {
        PayrollSystem ps = new PayrollSystem();                 //Creates new PayrollSystem Object

        ps.menu();                                              //Calls the menu
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;                                  //Holds the menu choice

        do {                                         //loops through the menu to repeat until a 5 is entered
            System.out.println("Main Menu:");
            System.out.println("Please select one of the following options:\n"
                    + "\t1)Populate Employees\n"
                    + "\t2)Select an Employee\n"
                    + "\t3)Save Employees\n"
                    + "\t4)Load Employees\n"
                    + "\t5)Quit\n");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException exc) {
                System.out.println("Invalid Input");
                choice = 0;
            }
            switch (choice) {
                case 1:
                    populateEmployees();
                    break;
                case 2:
                    selectEmployee();
                    break;
                case 3:
                    System.out.println("Saving Employees...");
                    writeSerializedEmployeeArray();
                    saveEmployeesToFile();
                    isSaved = true;
                    System.out.println("Done! Payroll report is saved as PayrollReport.txt\nPress Enter to return to the main menu.");
                    sc.nextLine();
                    break;
                case 4:
                    System.out.println("Loading Employees...");
                    readSerializedEmployeeArray();
                    System.out.println("Done!");
                    break;
            }
        } while (choice != 5);

        String save;
        if (isEdited && !isSaved) {
            System.out.println("Warning, unsaved changes. Would you like to save your changes?(y/n): ");
            save = sc.nextLine();
            if (save.equalsIgnoreCase("y")) {
                System.out.println("Saving Employees...");
                writeSerializedEmployeeArray();
                saveEmployeesToFile();
                System.out.println("Done!");
            }
        }
    }

    public void populateEmployees() {                //Runs a for loop to run employees[i].populateEmployee() for each employee
        Scanner sc = new Scanner(System.in);
        String choice;                              //Holds the choice to overwrite objects
        if (populated) {
            System.out.println("Warning, employees are already populated. Overwrite?(y/n): ");
            choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("y")) {
                return;
            }
        }

        for (int i = 0; i < employees.length; i++) {
            do {
                System.out.println("Enter payment type for employee " + (i + 1) + "\n"
                        + "[(H)ourly, (S)alary, or (C)ommission]: ");
                choice = sc.nextLine();

            } while (!(choice.equalsIgnoreCase("H") || choice.equalsIgnoreCase("S") || choice.equalsIgnoreCase("C")));

            if (choice.equalsIgnoreCase("H")) {
                System.out.println("Hourly Chosen.");
                employees[i] = new HourlyEmployee();
            } else if (choice.equalsIgnoreCase("S")) {
                System.out.println("Salary Chosen.");
                employees[i] = new SalaryEmployee();
            } else if (choice.equalsIgnoreCase("C")) {
                System.out.println("Commission Chosen.");
                employees[i] = new CommissionEmployee();
            }

            employees[i].populateEmployee();
        }

        populated = true;                           //Sets populated field flag to be true, to prevent the overwriting of data
        isEdited = true;                            //Sets isEdited to true, so to warn for unsaved changes

    }

    public void selectEmployee() {
        Scanner sc = new Scanner(System.in);

        String tempName, //Holds the name to be searched
                again;      //Holds the choice to search again

        boolean found;
        //int index = -99;
        try {
            if (!employees[0].isPopulated()) {//Kick out of the menu if no employees are defined
                System.out.println("No employees have been populated. Press Enter to return to the main menu");
                sc.nextLine();
                return;
            }

            do {                //Prompt for name and search through the objects in the array, unless again = "n";
                again = "n";
                System.out.print("Employees: ");
                for (int i = 0; i < employees.length; i++) {
                    System.out.print(employees[i].getName());
                    if (i != employees.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("\nEnter name to select:");
                tempName = sc.nextLine();
                found = false;
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].getName().equalsIgnoreCase(tempName)) {
                        //		index = i;
                        employees[i].menu(); //Set run the menu method on the employee object
                        found = true;     //if something is found set found to true
                        break;
                    }
                }

                if (!found) {               //if nothing is found say "Employee not found"
                    System.out.println("Employee not found.");
                    System.out.print("Search again?(y/n): ");
                    again = sc.nextLine();
                }

            } while (!again.equalsIgnoreCase("n"));

        } catch (NullPointerException e) {
            System.out.println("No Employees have been populated. Press ENTER to return");
            sc.nextLine();
        }
    }

    public void writeSerializedEmployeeArray() {
        try {//Try to connect the file output stream to file "serializedEmployees.ser", then conect the object output stream to fos
            FileOutputStream fos = new FileOutputStream("serializedEmployees.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(employees);                 //Write the employees object to the above file
            oos.flush();                                //flush the output stream
            fos.close();                                //close the file
        } catch (IOException e) {                       //if an io exception is thrown, catch it and print it to the console
            System.out.println(e);
        }
    }

    public void saveEmployeesToFile() {
        DecimalFormat f = new DecimalFormat("##.00");
        try (FileWriter fw = new FileWriter("PayrollReport.txt")) {
            fw.write("                                            "
				+ "                Payroll Report\n");
            fw.write("                                            "
				+ "                Tax Rate: " + (employees[0].getTaxRate() * 100) + "%\n");
            fw.write("Employee            \t\tEmployment\t\tHourly  "
                    + "\t\tHours   \t\tCommission\t\tGross   \t\tTaxed   \t\tNet \n"
                    + "Name                \t\tType    \t\tRate    \t\tWorked  \t\tEarned  \t\tAmount  \t\tAmount  \t\tAmount\n"
                    + "--------------------\t\t----------\t\t--------\t\t--------\t\t--------\t\t--------\t\t--------\t\t--------\n");

            int wordLen, fieldLen;
            for (int i = 0; i < employees.length; i++) {
                wordLen = employees[i].getName().length();
                fieldLen = 20;
                fw.write(employees[i].getName());
                for (int j = 0; j < fieldLen - wordLen; j++) {
                    fw.write(" ");
                }
                fw.write("\t\t");
				if (employees[i].getObjType() == 0)
					fw.write("Hourly    ");
				else if(employees[i].getObjType() == 1)
					fw.write("Salary    ");
				else if(employees[i].getObjType() == 2)
					fw.write("Commission");
				else
					fw.write("        ");
				fw.write("\t\t");

                fieldLen = 8;
                if (employees[i].getObjType() == 1) {
                    wordLen = 3;
                    fw.write("N/A");
                } else {
                    wordLen = String.valueOf(f.format(employees[i].getPayRate())).length();
                    fw.write(String.valueOf(f.format(employees[i].getPayRate())));
                }
                for (int j = 0; j < fieldLen - wordLen; j++) {
                    fw.write(" ");
                }
                fw.write("\t\t");

                fieldLen = 8;
				if (employees[i].getObjType() == 1) {
					wordLen = 3;
					fw.write("N/A");
				} else {
					wordLen = String.valueOf(f.format(employees[i].getHours())).length();
					fw.write(String.valueOf(f.format(employees[i].getHours())));
				}
                for (int j = 0; j < fieldLen - wordLen; j++) {
                    fw.write(" ");
                }
                fw.write("\t\t");


                fieldLen = 8;
				if (employees[i].getObjType() != 2) {
					wordLen = 3;
					fw.write("N/A");
				} else {
					wordLen = String.valueOf(f.format(employees[i].getCommissionAmount())).length();
					fw.write(String.valueOf(f.format(employees[i].getCommissionAmount())));
				}
                for (int j = 0; j < fieldLen - wordLen; j++) {
                    fw.write(" ");
                }
                fw.write("\t\t");

                wordLen = String.valueOf(f.format(employees[i].getGrossPay())).length();
                fieldLen = 8;
                fw.write(String.valueOf(f.format(employees[i].getGrossPay())));
                for (int j = 0; j < fieldLen - wordLen; j++) {
                    fw.write(" ");
                }
                fw.write("\t\t");

                wordLen = String.valueOf(f.format(employees[i].getTaxAmount())).length();
                fieldLen = 8;
                fw.write(String.valueOf(f.format(employees[i].getTaxAmount())));
                for (int j = 0; j < fieldLen - wordLen; j++) {
                    fw.write(" ");
                }
                fw.write("\t\t");

                wordLen = String.valueOf(f.format(employees[i].getNet())).length();
                fieldLen = 8;
                fw.write(String.valueOf(f.format(employees[i].getNet())));
                fw.write("\n");
            }

            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readSerializedEmployeeArray() {
        Scanner sc = new Scanner(System.in);
        String choice;

        if (populated) {                           //check if the employee object is already populated, prompt to overwrite if it is
            System.out.println("Warning, employees are already populated. Overwrite?(y/n): ");
            choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("y")) {
                return;
            }
        }

        try {//Try to connect file input stream to file "serializedEmployees.ser", then connect object input to fis
            FileInputStream fis = new FileInputStream("serializedEmployees.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            employees = (Employee[]) ois.readObject(); //Read the object and cast it to an Employee array
            fis.close();                              //Close the input file
        } catch (IOException | ClassNotFoundException e) { //Catch the exceptions and print them to console
            System.out.println(e);
        }
        populated = true;                                  //Set populated flag to true
    }
}
