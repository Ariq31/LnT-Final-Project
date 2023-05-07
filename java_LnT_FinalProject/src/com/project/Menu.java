package com.project;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.util.Vector;

import com.config.Connectiondb;
import com.utility.Pudding;



import com.utility.Pudding;



public class Menu extends JFrame implements ActionListener, MouseInputListener {
	
	
private JPanel topPanel, midPanel, contentPanel, formPanel, tablePanel, botPanel, botTPanel, botBPanel,
idLblPanel, nameLblPanel, priceLblPanel, stockLblPanel,
idTextFieldPanel, nameTextFieldPanel, priceTextFieldPanel, stockTextFieldPanel, tiPanel;
private JLabel titleLbl, idLbl, nameLbl, priceLbl, stockLbl;
private JTextField idTextField, nameTextField, priceTextField, stockTextField;
private JTable menuTable;
private JScrollPane scrollPane;
private JButton addBtn, updateBtn, deleteBtn;
private DefaultTableModel tableModel;

private Vector<Pudding> menuData;


private Vector<Object> column, row;
private Vector<Vector<Object>> data;
public Connectiondb connectionDb;


void menuFrame() {
setTitle("Menu");
setVisible(true);
setSize(1000,670);
setResizable(false);
setLocationRelativeTo(null);
setDefaultCloseOperation(EXIT_ON_CLOSE);
	
}

void showFrame() {
topPanel = new JPanel();
titleLbl = new JLabel("Database PT Pudding");
titleLbl.setFont(new Font("",Font.BOLD,20));
topPanel.add(titleLbl);


midPanel = new JPanel();
contentPanel = new JPanel(new GridLayout(1,2));
tiPanel = new JPanel(new GridLayout(2,1));
formPanel = new JPanel(new GridLayout(4,2));

idLbl = new JLabel("Menu ID: ");
idTextField = new JTextField();
idTextField.setPreferredSize(new Dimension(200, 25));
idLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
idTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
idTextFieldPanel.add(idTextField);

idLblPanel.add(idLbl);



nameLbl = new JLabel("Name: ");
nameTextField = new JTextField();
nameTextField.setPreferredSize(new Dimension(200, 25));
nameLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
nameLblPanel.add(nameLbl);
nameTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
nameTextFieldPanel.add(nameTextField);



priceLbl = new JLabel("Price: ");
priceLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
priceLblPanel.add(priceLbl);

priceTextField = new JTextField();
priceTextField.setPreferredSize(new Dimension(200, 25));
priceTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
priceTextFieldPanel.add(priceTextField);



stockLbl = new JLabel("Stock: ");
stockLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
stockLblPanel.add(stockLbl); 

stockTextField = new JTextField();
stockTextField.setPreferredSize(new Dimension(200, 25));
stockTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
stockTextFieldPanel.add(stockTextField);


formPanel.add(idLblPanel);
formPanel.add(idTextFieldPanel);
formPanel.add(nameLblPanel);
formPanel.add(nameTextFieldPanel);
formPanel.add(priceLblPanel);
formPanel.add(priceTextFieldPanel);
formPanel.add(stockLblPanel);
formPanel.add(stockTextFieldPanel);
Border border = new EtchedBorder(EtchedBorder.LOWERED); 
formPanel.setBorder(border);
tiPanel.add(formPanel);


botPanel = new JPanel(new GridLayout(5,1));
botTPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));



addBtn = new JButton("ADD");
addBtn.addActionListener(this);

updateBtn = new JButton("Update");
updateBtn.addActionListener(this);
updateBtn.setEnabled(false);

deleteBtn= new JButton("Delete");
deleteBtn.addActionListener(this);
deleteBtn.setEnabled(false);




botTPanel.add(addBtn);
botTPanel.add(updateBtn);
botTPanel.add(deleteBtn);

//botPanel.add(botTPanel);
tiPanel.add(botTPanel);




		
column = new Vector<Object>();
column.add("menuId");
column.add("menuName");
column.add("menuPrice");
column.add("menuStock");


data = new Vector<Vector<Object>>();



	if(menuData!=null) {
		
			for (Pudding admin : menuData) {
				row = new Vector<Object>();
				row.add(admin.getMenuId());
				row.add(admin.getMenuName());
				row.add(admin.getPrice());
				row.add(admin.getStockMenu());
				data.add(row);
	
		
	}}
		

	



menuTable = new JTable(data,column);
menuTable.addMouseListener(this);
scrollPane = new JScrollPane(menuTable);
tablePanel = new JPanel();
tablePanel.add(scrollPane);
menuTable.setGridColor(Color.black);

contentPanel.add(tiPanel);
contentPanel.add(tablePanel);


midPanel.add(contentPanel);

add(topPanel, BorderLayout.NORTH);
add(midPanel,BorderLayout.CENTER);
//add(botPanel, BorderLayout.SOUTH);


}

public Menu(Vector<Pudding> menuData, Connectiondb connectionDb)  {
	
	this.menuData = menuData;
	this.connectionDb = connectionDb;
	fillDataVector();
	
	
	showFrame();
	menuFrame();
}



private void clearTextField() {
	idTextField.setText("");
	nameTextField.setText("");
	priceTextField.setText("");
	stockTextField.setText("");
}



	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addBtn) {
			String menuId, menuName, stringMenuPrice, stringMenuStock;
			int menuPrice, menuStock;
			 
			
			menuId = idTextField.getText();
			menuName = nameTextField.getText();
			stringMenuPrice = priceTextField.getText();
			stringMenuStock = stockTextField.getText();
//			menuPrice = Integer.valueOf(priceTextField.getText());
//			menuStock = Integer.valueOf(stockTextField.getText());
			
			
			if(!menuId.isEmpty() && !menuName.isEmpty() && !stringMenuPrice.isEmpty() && !stringMenuStock.isEmpty() ) {	
				
				if(menuId.matches("PD-[0-9][0-9][0-9]")) {
				
					if(stringMenuPrice.matches("\\d+")&&stringMenuStock.matches("\\d+")&&!menuName.matches("\\d+")) {
						
					menuPrice = Integer.valueOf(priceTextField.getText());
					menuStock = Integer.valueOf(stockTextField.getText());
					connectionDb.insertFoodsData(menuId, menuName, menuPrice, menuStock);
				
				menuData= new Vector<Pudding>();
				menuData.add(new Pudding(menuId,menuName,menuPrice,menuStock));
				menuData.clear();
				
				setVisible(false);
				new Menu(menuData,connectionDb);
				clearTextField();
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Please input the right data");		
					
				}	
					
					
				}	
				
				else {
					JOptionPane.showMessageDialog(null, "Plis input PD-XXX");	
					}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Plis fulfill the form");		
				
			}
			
			
//			if(menuId.matches("PD-[0-9][0-9][0-9]")) {
//			
//			if(!menuId.isEmpty() && !stringMenuPrice.isEmpty() && !stringMenuStock.isEmpty() ) {	
//				
//				menuPrice = Integer.valueOf(priceTextField.getText());
//				menuStock = Integer.valueOf(stockTextField.getText());
//				
//				connectionDb.insertFoodsData(menuId, menuName, menuPrice, menuStock);
//			
//			menuData= new Vector<Pudding>();
//			menuData.add(new Pudding(menuId,menuName,menuPrice,menuStock));
//			menuData.clear();
//			}
//			else {
//				JOptionPane.showMessageDialog(null, "Plis fulfill the text");	
//				
//			}
//			}
//			else {
//			JOptionPane.showMessageDialog(null, "Plis input PD-XXX");	
//			}
		
			
			
			
			
			
			
			
			
		}
		
		else if(e.getSource()==deleteBtn) {
		
			int result = JOptionPane.showConfirmDialog(null, "Are you sure want to delete this data?");
			if(result==0) {
			int selectedRow = menuTable.getSelectedRow();
		
			 String menuId = menuData.get(selectedRow).menuId;
			 connectionDb.deleteData(menuId);
			 
			 menuData.remove(selectedRow);
		
			 menuData.clear();
				
			 setVisible(false);
			 new Menu(menuData, connectionDb);
			 clearTextField();
					 
			}
			
				 
				
				 
			 }
		
		else if(e.getSource()==updateBtn) {
			
			int result = JOptionPane.showConfirmDialog(null, "Are you sure want to update this data?");
			if(result==0) {
			String idMenu, menuName;
			int menuPrice, menuStock;
			int selectedRow = menuTable.getSelectedRow();
			
			
			
			
			idMenu = menuData.get(selectedRow).menuId;
			menuName = nameTextField.getText();
			menuPrice = Integer.valueOf(priceTextField.getText());
			menuStock = Integer.valueOf(stockTextField.getText());
			
			connectionDb.updateData(idMenu, menuName, menuPrice, menuStock);
			
			
//			menuData.get(selectedRow-1).setMenuId(idMenu);
//			menuData.get(selectedRow-1).setMenuName(menuName);
//			menuData.get(selectedRow-1).setPrice(menuPrice);
//			menuData.get(selectedRow-1).setStockMenu(menuStock);
//			
//			 
			
		    
			menuData.clear();
			setVisible(false);
			new Menu(menuData, connectionDb);
			clearTextField();
			}
			
		}
		
		
		
		
		
	}

	
	
	private void fillDataVector() {
        try {
            connectionDb.rs = connectionDb.getMenuData();
            while(connectionDb.rs.next()) {
                String menuId = String.valueOf(connectionDb.rs.getObject(1));
                String menuName = String.valueOf(connectionDb.rs.getObject(2)); 
                int menuPrice = Integer.valueOf(String.valueOf(connectionDb.rs.getObject(3))); 
                int menuStock = Integer.valueOf(String.valueOf(connectionDb.rs.getObject(4))); 
                
               
                menuData.add(new Pudding(menuId, menuName, menuPrice, menuStock));
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
            
            }
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == menuTable) {
			idTextField.setEditable(false);
			deleteBtn.setEnabled(true);
			updateBtn.setEnabled(true);
			int row = menuTable.getSelectedRow();
			
			idTextField.setText(menuTable.getValueAt(row, 0).toString());
			nameTextField.setText(menuTable.getValueAt(row, 1).toString());
			priceTextField.setText(menuTable.getValueAt(row, 2).toString());
			stockTextField.setText(menuTable.getValueAt(row, 3).toString());
		
		
		}
		
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
