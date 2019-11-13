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

        System.out.print("Enter Name: ");
        this.name = sc.nextLine();

        String type;
        do {
            System.out.println("Please enter either \"(S)taff\" or \"(E)xecutive\":");
            type = sc.nextLine();
        } while (!(type.equalsIgnoreCase("Staff") || type.equalsIgnoreCase("Executive") || type.equalsIgnoreCase("S") || type.equalsIgnoreCase("E")));

        if (type.equalsIgnoreCase("s") || type.equalsIgnoreCase("staff")) {
            annualSal = 50000;
        } else if (type.equalsIgnoreCase("e") || type.equalsIgnoreCase("executive")) {
            annualSal = 100000;
        }

        biMonthlySal = annualSal / 24;

		populated = true;
    }

    @Override
    public double getGrossPay() {
        return biMonthlySal;
    }

    //Needs work here
}
