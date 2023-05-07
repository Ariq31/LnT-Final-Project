package com.project;

import java.util.Vector;

import com.config.Connectiondb;
import com.utility.Pudding;
import com.utility.Pudding;

public class Main {
public Vector<Pudding> menuData;
public Connectiondb connectionDb;
	
	public Main() {
		connectionDb = new Connectiondb();
		new Menu(menuData,connectionDb);
	}

	public static void main(String[] args) {
		new Main();

	}

}
