package edu.umsl;

import java.util.Scanner;

/**
 *
 * @author dsm7n
 */
public class CommissionEmployee extends Employee{
	//Will need to override getGross and populate employee
    private double grossPay;
    public double CommissionAmount;
    double CommissionRate=0.5;
    double TotalItems, Price, GrossSales, Commission, payRate;
    
   
    @Override
    public void populateEmployee()
{
    Scanner sc = new Scanner (System.in);
    
    System.out.println("Enter Name: ");
    this.name=sc.nextLine();
    
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
            System.out.println("Please enter the employee's pay rate");
            try {
                this.payRate = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException exc) {
                System.out.println("Invalid Input");
                this.payRate = 0;
            }
        } while (this.hours == -99 || this.hours < 0);

		populated = true;   
    
        do {
            try 
                {
                    System.out.println("How many items did you sell ");
                    TotalItems=Double.parseDouble(sc.nextLine());
                } 
            catch (NumberFormatException exc)
                {     
                    TotalItems = 0;
                    System.out.println("Invalid Input");
                }
            } 
            while (TotalItems <= 0);
    
        do {
            try {
                   System.out.println("Please enter the price of unit sold ");
                   Price=Double.parseDouble(sc.nextLine());
                } 
            catch (NumberFormatException exc) 
                {
                   Price = 0;
                   System.out.println("Invalid Input");
                }
            } 
            while (Price <=0);
    
GrossSales = TotalItems * Price;
System.out.println("Your gross sales are: " + GrossSales);


Commission = CommissionRate * GrossSales;
System.out.println("The Commision is: "+ Commission);

    }
    
    
    @Override
    public double getGrossPay()
    {
       return grossPay + Commission;
    }
    
    
    public double getCommissionAmount()
    {
      return Commission;
      
    }
}


   
