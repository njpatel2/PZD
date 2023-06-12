package com.pzd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class xxx {

	static int s = 0;
	int i = 0;
	Integer i1 = new Integer(0);
	
	
	
	public void changeStatic(int number) {
		s=number;
	}
	public void changeNormal(int number) {
		i=number;
	}

	public static void main(String[] args) {
		xxx obj1 = new xxx();
		xxx obj2 = new xxx();
		xxx obj3 = new xxx();
		
		obj1.changeStatic(10);
		obj1.changeNormal(10);
		
		obj2.changeStatic(20);
		obj2.changeNormal(20);
		
		obj3.changeStatic(30);
		obj3.changeNormal(30);
		
		
		ArrayList<String> al = new ArrayList<>();
		al.add("1");
		
		
		System.out.println("static for obj1= "+obj1.s);
		System.out.println("normal for obj1= "+obj1.i);
		System.out.println();
		System.out.println("static for obj2= "+obj2.s);
		System.out.println("normal for obj2= "+obj2.i);
		System.out.println();
		System.out.println("static for obj3= "+obj3.s);
		System.out.println("normal for obj3= "+obj3.i);

	}

}
