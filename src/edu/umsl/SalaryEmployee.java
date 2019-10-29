/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;

import java.util.Scanner;

/**
 *
 * @author dsm7n
 */
public class SalaryEmployee extends Employee {

    double annualSal;
    double biMonthlySal;
//    private String name;
//    private double hours = -1;

//    public SalaryEmployee() {
//        super("S");
//    }
//    
    @Override
    public void populateEmployee() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Overridden populate function for salary employee");

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
            System.out.println("What is the employee's annual salary:");

            try {
                annualSal = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException exc) {
                System.out.println("Invalid Input.");
                annualSal = -99;
            }
        } while (annualSal == -99 || annualSal <= 0); //Whil error is thrown, or salary is less than 0
        biMonthlySal = annualSal / 24;

    }

    @Override
    public double getGrossPay() {
        return biMonthlySal;
    }

    //Needs work here
}
