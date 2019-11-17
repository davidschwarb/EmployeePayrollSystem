/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;

/**
 *
 * @author dsm7n
 */
public class HourlyEmployee extends Employee {

	public HourlyEmployee() {
		objType = 0;
	}
	
	@Override
    public double getCommissionAmount(){
		return 0;
	}
}
