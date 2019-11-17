////////////////////////////////////////
// Employee class       10/14/19 6:00 PM
// Project Worked on By: 
//      David Schwarb
//      Lynda Vo
//      Michael Karbowiack
//
package edu.umsl;

import java.util.Scanner;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Employee implements Serializable {

    protected String name;

    protected double hours;
    protected double payRate;
    protected double taxRate;
    private double tax;
    private double net;
    private double netPercent;
    protected boolean populated = false;

    //////////////////////////////////////
    // Employee Defaut Constructor
    Employee() {
        this.name = "Undefined";
        this.hours = -1;
        this.payRate = 0.0;
        this.taxRate = 0.2;
        this.tax = 0.0;
        this.net = 0.0;
        this.netPercent = 0.0;
    }

//    Employee(String type) {
//        if (type.equalsIgnoreCase("S")) {
//            this.name = "Undefined";
//            this.payRate = 0.0;
//            this.taxRate = 0.2;
//            this.tax = 0.0;
//            this.net = 0.0;
//            this.netPercent = 0.0;
//        }
//    }

    //////////////////////////////////////
    // menu for Employee
    public void menu() {
        DecimalFormat f = new DecimalFormat("##.00");
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Employee Menu:");
            System.out.println("Enter a choice for " + name + ":\n"
                    + "\t1) Calculate gross pay\n"
                    + "\t2) Calculate tax\n"
                    + "\t3) Calculate net pay\n"
                    + "\t4) Calculate net percent\n"
                    + "\t5) Display Employee\n"
                    + "\t6) Return to Main Menu\n");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException exc) {
                System.out.println("Invalid Input");
                choice = 0;
            }

            switch (choice) {
                case 1:
                    System.out.println("Gross pay: " + f.format(getGrossPay()));
                    break;

                case 2:
                    System.out.println("Tax Amount: " + f.format(getTaxAmount()));
                    break;

                case 3:
                    System.out.println("Net Pay: " + f.format(getNet()));
                    break;

                case 4:
                    System.out.println("Net Percent: " + f.format(getNetPercent()));
                    break;

                case 5:
                    showEmployee();
                    break;

            }
            if (choice < 6 && choice > 0) {
                System.out.println("Press Enter to return.");
                sc.nextLine();
            }
        } while (choice != 6);
    }

    //////////////////////////////////////
    // populator for Employee
    public void populateEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: ");
        this.name = sc.nextLine();
        do {
            System.out.println("Please enter the hours worked: ");
            try {
                this.hours = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException exc) {
                System.out.println("Invalid Input");
                this.hours = -99;
            }
        } while (this.hours == -99 || this.hours < 0);
        do {
            System.out.println("Please the employee's pay rate");
            try {
                this.payRate = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException exc) {
                System.out.println("Invalid Input");
                this.payRate = 0;
            }
        } while (this.hours == -99 || this.hours < 0);

		populated = true;
    }

    //////////////////////////////////////
    // displayer for Employee
    public void showEmployee() {
        DecimalFormat f = new DecimalFormat("##.00");
        System.out.println("Employee Name: " + name);
        System.out.println("Hours Worked: " + hours);
//        System.out.println("Pay Rate: " + payRate);
        System.out.println("Gross Pay: " + f.format(getGrossPay()));
//        System.out.println("Tax Rate: " + taxRate);
        System.out.println("Tax Amount: " + f.format(getTaxAmount()));
        System.out.println("Net Pay: " + f.format(getNet()));
        System.out.println("Net Pay Percent: " + f.format(getNetPercent()));
    }

    /////////////////////////////////////////
    // getter for Gross Pay
    // Returns: hours * payRate
    public double getGrossPay() {
        double grossPay;
        double overtime;
        if (hours > 40) {
            grossPay = 40 * payRate;

            overtime = (hours - 40) * (1.5 * payRate);

            grossPay += overtime;
        } else {
            grossPay = hours * payRate;
        }
        return grossPay;
    }

    ////////////////////////////////////////
    // getter for Tax Amount
    // Returns: tax * Gross Pay
    public double getTaxAmount() {
        return taxRate * getGrossPay();
    }

    /////////////////////////////////////////
    // getter for net
    // Returns: gross pay - tax amount
    public double getNet() {
        return getGrossPay() - getTaxAmount();
    }

    /////////////////////////////////////////
    // getter for net
    // Returns: (net pay / gross pay) * 100
    public double getNetPercent() {
        if (getGrossPay() != 0) {
            return (getNet() / getGrossPay()) * 100;
        }
        return 0.0;
    }

    /////////////////////////////////////////
    // getter for name
    // Returns: the name
    public String getName() {
        return name;
    }
    /////////////////////////////////////////
    // getter for payRate
    // Returns: the payRate

    public double getPayRate() {
        return payRate;
    }
    /////////////////////////////////////////
    // getter for hours 
    // Returns: the hours

    public double getHours() {
        return hours;
    }

    /////////////////////////////////////////
    // getter for taxRate
    // Returns: taxRate
    public double getTaxRate() {
        return taxRate;
    }

    public boolean isPopulated(){
	    return populated;
    }
}
