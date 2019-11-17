/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author dsm7n
 */
public class SalaryEmployee extends Employee {

	public SalaryEmployee() {
		objType = 1;
	}

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

    @Override
    public void showEmployee() {
        DecimalFormat f = new DecimalFormat("##.00");
        System.out.println("Employee Name: " + name);
        System.out.println("Hours Worked: Salaried Employee (N/A)");
        System.out.println("Gross Pay: " + f.format(getGrossPay()));
        System.out.println("Tax Amount: " + f.format(getTaxAmount()));
        System.out.println("Net Pay: " + f.format(getNet()));
        System.out.println("Net Pay Percent: " + f.format(getNetPercent()));
    }

	@Override
    public double getCommissionAmount(){
		return 0;
	}
}
