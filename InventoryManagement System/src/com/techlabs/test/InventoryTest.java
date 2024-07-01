package com.techlabs.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicTreeUI.TreeHomeAction;

import com.techlabs.model.Inventory;
import com.techlabs.model.Product;
import com.techlabs.model.Supplier;
import com.techlabs.model.Transaction;

public class InventoryTest {

	public static void main(String[] args) {
		
		Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        
        try {
			loadData(inventory,scanner);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        while (true) {
            System.out.println("Welcome to the Inventory Management System");
            System.out.println("1. Product Management");
            System.out.println("2. Supplier Management");
            System.out.println("3. Transaction Management");
            System.out.println("4. Save Data");
            System.out.println("5. Load Data");
            System.out.println("6. Generate Reports");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    productManagement(inventory, scanner);
                    break;
                case 2:
                    supplierManagement(inventory, scanner);
                    break;
                case 3:
                    transactionManagement(inventory, scanner);
                    break;
                case 4:
                    saveData(inventory, scanner);
                    break;
                case 5:
				try {
					loadData(inventory, scanner);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;
                case 6:
                    inventory.displaySummary();
                    break;
                case 7:
                	saveData(inventory, scanner);
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
	}
	

	private static void loadData(Inventory inventory, Scanner scanner) throws FileNotFoundException, IOException {


		 try (BufferedReader productReader = new BufferedReader(new FileReader("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\products.txt"));
		         BufferedReader supplierReader = new BufferedReader(new FileReader("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\supplier.txt"));
		         BufferedReader transactionReader = new BufferedReader(new FileReader("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\transactions.txt"))) {

		        String line;

		        // Load products
		        while ((line = productReader.readLine()) != null) {
		            String[] data = line.split(",");
		            Product product = new Product(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]));
		            inventory.addProduct(product);
		        }

		        // Load suppliers
		        while ((line = supplierReader.readLine()) != null) {
		            String[] data = line.split(",");
		            Supplier supplier = new Supplier(data[0], data[1], data[2]);
		            inventory.addSupplier(supplier);
		        
		        }

		        // Load transactions
		      /* while ((line = transactionReader.readLine()) != null) {
		            String[] data = line.split(",");
		            Transaction transaction = new Transaction(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
		            try {
						inventory.addTransaction("001","add",1,transaction);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }

		        System.out.println("Data loaded successfully.");
		    } catch (IOException e) {
		        System.out.println("Error loading data: " + e.getMessage());
		    }*/
		        
		       
		        
		        
		
		 }
	}
	
	
	private static void saveData(Inventory inventory, Scanner scanner) {
		/*try (

		BufferedWriter productWriter=new BufferedWriter(new FileWriter("C:\Users\ChHarshini\eclipse-workspace\InventoryManagement System\src\com\techlabs\model\products.txt",true);
		BufferedWriter supplierWriter=new BufferedWriter(new FileWriter("C:\Users\ChHarshini\eclipse-workspace\InventoryManagement System\src\com\techlabs\model\supplier.txt"));
		BufferedWriter transactionWriter=new BufferedWriter(new FileWriter("C:\Users\ChHarshini\eclipse-workspace\InventoryManagement System\src\com\techlabs\model\transactions.txt"))){
			for(Product product:inventory.viewAllProducts()){
				productWriter.write(product.getProductId() +","+product.getName()+","+product.getDescription()+","+product.getQuantity()+","+product.getPrice());
			}
			
			for(Supplier supplier:inventory.viewAllSuppliers()){
				
				supplierWriter.write(supplier.getSupplierid()+","+supplier.getName()+","+supplier.getContactInfo()+"\n");
			}

			for(Transaction transaction:inventory.viewAllTransactions()) {
				 transactionWriter.write(transaction.getTransactionid() + "," + transaction.getProductId() + "," + 
                         transaction.getType() + "," + transaction.getQuantity() + "," + 
                         transaction.getDate() + "\n");
				
			} */
			System.out.println("Data saved successfully.");
		
/*
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println(" error in saving data "+e.getMessage());
	}*/
	}


	private static void transactionManagement(Inventory inventory, Scanner scanner) {
		
		while(true) {
			
			System.out.println("Transaction Management");
			System.out.println("1. Add Stock");
			System.out.println("2.Remove Stock");
			System.out.println("3. view Transaction History");
			System.out.println("4.Back to main menu");
			System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine();
	        
	        
	        switch(choice) {
	        case 1:
	        	addStock(inventory,scanner);
	        	break;
	        	
	        case 2:
	        	removeStock(inventory,scanner);
	        	break;
	        	
	        case 3:
	        	viewTransactionHistory(inventory,scanner);
	        	break;
	        case 4:
	        	return;
	        default:
	        	System.out.println("Invalid choice.  Please try again. ");
	        	
	        }
			
		}
		
	}


	private static void viewTransactionHistory(Inventory inventory, Scanner scanner) {
		
		System.out.print("Enter product ID to view transaction history: ");
	    String productId = scanner.nextLine();

	    List<Transaction> transactions = inventory.viewTransactionHistory(productId);
	    if (transactions.isEmpty()) {
	        System.out.println("No transactions found for product ID: " + productId);
	    } else {
	        System.out.println("Transaction History for product ID: " + productId);
	        for (Transaction transaction : transactions) {
	            System.out.println(transaction);
	        }
	    }
	}


	private static void removeStock(Inventory inventory, Scanner scanner) {
		System.out.print("Enter product ID to remove stock: ");
	    String productId = scanner.nextLine();
	    System.out.print("Enter quantity to remove: ");
	    int quantity = scanner.nextInt();
	    scanner.nextLine();  // Consume newline

	    try {
	        inventory.removeStock(productId, quantity);
	        System.out.println("Stock removed successfully.");
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    }
		
	}


	private static void addStock(Inventory inventory, Scanner scanner) {
		
		System.out.println("Enter product ID to add stock");
		String productid=scanner.nextLine();
		
		System.out.println("Enter quantity to add add stock");
		int quantity =scanner.nextInt();
		scanner.nextLine();
		
		try {
			inventory.addStock(productid, quantity);
			System.out.println("Stock added succesfully. ");
		}
		catch(Exception e) {
			System.out.println("error: "+e.getMessage());
		}
		
	}


	private static void supplierManagement(Inventory inventory, Scanner scanner) {
		
		while(true) {
			System.out.println("Supplier Management");
			
			System.out.println("1.Add supllier");
			System.out.println("2.Update supplier");
			System.out.println("3.dalete supplier");
			System.out.println("4.View Supplier Details");
			System.out.println("5.View All Suppliers");
			System.out.println("6. Back to Main Menu");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine();  // Consume newline

	        switch(choice){
	        case 1:  addSupplier(inventory, scanner);
                      break;
                      
	        case 2:
	        	updateSupplier(inventory, scanner);
                break;
                
	        case 3:
	        	 deleteSupplier(inventory, scanner);
	                break;
	        case 4:
	        	viewSupplier(inventory, scanner);
                break;
	        	
	        case 5:
	        	viewAllSuppliers(inventory);
	        	break;
	        case 6:
	        	return;
	        default:
                System.out.println("Invalid choice. Please try again.");
	        	
	        
	        }
	        	
	        }


		
		
	}


	private static void viewAllSuppliers(Inventory inventory) {


		List<Supplier> suppliers = inventory.viewAllSuppliers();
	    if (suppliers.isEmpty()) {
	        System.out.println("No suppliers found.");
	    } else {
	    	System.out.println();
	    	System.out.println("All Suppliers:");
	        for (Supplier supplier : suppliers) {
	            System.out.println(supplier);
	        }
	        System.out.println();   
	    }
	
	}


	private static void viewSupplier(Inventory inventory, Scanner scanner) {
		System.out.print("Enter supplier ID to view details: ");
	    String supplierId = scanner.nextLine();

	    Supplier supplier = inventory.viewSupplier(supplierId);
	    if (supplier == null) {
	        System.out.println("Supplier not found.");
	    } else {
	    	System.out.println();
	        System.out.println("Supplier Details:");
	        System.out.println(supplier);
	    }
	    System.out.println();
	}


	private static void deleteSupplier(Inventory inventory, Scanner scanner) {


		 System.out.print("Enter supplier ID to delete: ");
		    String supplierId = scanner.nextLine();

		    inventory.deleteSupplier(supplierId);
		    System.out.println("Supplier deleted successfully.");
		
		
	}


	private static void updateSupplier(Inventory inventory, Scanner scanner) {


		System.out.println("Enter supplier id to update: ");
		String supplierId=scanner.nextLine();
		
		Supplier supplier = inventory.viewSupplier(supplierId);
	    if (supplier == null) {
	        System.out.println("Supplier not found.");
	        return;
	    }

	    System.out.print("Enter new supplier name (leave blank to keep current): ");
	    String name = scanner.nextLine();
	    System.out.print("Enter new supplier contact information (leave blank to keep current): ");
	    String contactInfo = scanner.nextLine();

	    if (!name.isEmpty()) supplier.setName(name);
	    if (!contactInfo.isEmpty()) supplier.setContactInfo(contactInfo);

	    inventory.updateSupplier(supplier);
	    System.out.println("Supplier updated successfully.");
	}

		
	


	private static void addSupplier(Inventory inventory, Scanner scanner) {


		System.out.println("Enterv the Supplier ID: ");
		String supplierId=scanner.nextLine();
		
		System.out.println("Enterv the Name: ");
		String name=scanner.nextLine();
		
		System.out.println("Enterv the Supplier contact Info: ");
		String contactInfo=scanner.nextLine();
		
		Supplier supplier=new Supplier(supplierId, name, contactInfo);
		
		
		inventory.addSupplier(supplier);
		System.out.println("Supplier added succesfully");
		
		
	}


	private static void productManagement(Inventory inventory, Scanner scanner) {


		while(true) {
			System.out.println("Product Management");
			System.out.println("1.Add Product");
			 System.out.println("2. Update Product");
		        System.out.println("3. Delete Product");
		        System.out.println("4. View Product Details");
		        System.out.println("5. View All Products");
		        System.out.println("6. Back to Main Menu");
		        System.out.print("Enter your choice: ");
		        int choice = scanner.nextInt();
		        scanner.nextLine();  // Consume newline

		        switch(choice) {
		        
		        case 1:  addProduct(inventory, scanner);
                         break;
		        case 2:
	                updateProduct(inventory, scanner);
	                break;
	            case 3:
	                deleteProduct(inventory, scanner);
	                break;
	            case 4:
	                viewProductDetails(inventory, scanner);
	                break;
	            case 5:
	                viewAllProducts(inventory);
	                break;
	            case 6:
	                return;  // Return to the main menu
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
		}
	}

	private static void addProduct(Inventory inventory, Scanner scanner) {


		System.out.println("Enterv the product ID: ");
		String productId=scanner.nextLine();
		
		System.out.println("Enterv the Name: ");
		String name=scanner.nextLine();
		
		System.out.println("Enter the description");
		String description=scanner.nextLine();
		
		System.out.println("Enterv the product Quantity: ");
		int quantity=scanner.nextInt();
		
		System.out.println("Enterv the product price: ");
		double price=scanner.nextDouble();
		scanner.nextLine();
		
		Product product=new Product(productId, name, description, quantity, price);
		System.out.println();
		inventory.addProduct(product);
		System.out.println("Product added successfully");

	
	}
		
	private static void updateProduct(Inventory inventory, Scanner scanner) {
	    System.out.print("Enter product ID to update: ");
	    String productId = scanner.nextLine();
	    Product product = inventory.viewProduct(productId);
	    if (product == null) {
	        System.out.println("Product not found.");
	        return;
	    }
	    
	    System.out.print("Enter new product name (leave blank to keep current): ");
	    String name = scanner.nextLine();
	    System.out.print("Enter new product description (leave blank to keep current): ");
	    String description = scanner.nextLine();
	    System.out.print("Enter new product quantity (leave blank to keep current): ");
	    String quantityInput = scanner.nextLine();
	    System.out.print("Enter new product price (leave blank to keep current): ");
	    String priceInput = scanner.nextLine();            
	
	
	    if (!name.isEmpty()) product.setName(name);
	    if (!description.isEmpty()) product.setDescription(description);
	    if (!quantityInput.isEmpty()) product.setQuantity(Integer.parseInt(quantityInput));
	    if (!priceInput.isEmpty()) product.setPrice(Double.parseDouble(priceInput));

	    inventory.updateProduct(product);
	    System.out.println("Product updated successfully.");
	}
	
	

	private static void deleteProduct(Inventory inventory, Scanner scanner) {
	    System.out.print("Enter product ID to delete: ");
	    String productId = scanner.nextLine();

	    inventory.deleteProduct(productId);
	    System.out.println("Product deleted successfully.");
	}
	
	private static void viewProductDetails(Inventory inventory, Scanner scanner) {
	    System.out.print("Enter product ID to view details: ");
	    String productId = scanner.nextLine();
	    
	    Product product = inventory.viewProduct(productId);
	    if (product == null) {
	        System.out.println("Product not found.");
	    } else {
	        System.out.println("Product Details:");
	        System.out.println(product);
	    }
	}

	private static void viewAllProducts(Inventory inventory) {
	    List<Product> products = inventory.viewAllProducts();
	    if (products.isEmpty()) {
	        System.out.println("No products found.");
	    } else {
	    	
	        System.out.println("All Products:");
	        for (Product product : products) {
	            System.out.println(product);
	        }
	    }
	}
	}
		
	
