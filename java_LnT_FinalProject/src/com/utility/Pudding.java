package com.utility;

public class Pudding {
public String menuId;
public String menuName ;
public int menuPrice ;
public int menuStock ;
	public Pudding() {
		
	}
	public Pudding(String menuId, String menuName, int menuPrice, int menuStock) {
		
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuStock = menuStock;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return menuPrice;
	}
	public void setPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getStockMenu() {
		return menuStock;
	}
	public void setStockMenu(int menuStock) {
		this.menuStock = menuStock;
	}
	
	

	

}
