package com.techlabs.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Inventory implements Serializable {
	
	 private List<Product> products = new ArrayList<>();
	    private List<Supplier> suppliers = new ArrayList<>();
	    private List<Transaction> transactions = new ArrayList<>();
	    private Map<String, Product> productMap = new HashMap<>();
	    private Map<String, Supplier> supplierMap = new HashMap<>();
	    private Map<String, List<Transaction>> transactionMap = new HashMap<>();


	 // Product Management Methods
	   
	    public void addProduct(Product product) {
	        if (productMap.containsKey(product.getProductId())) {
	            System.out.println("Product ID " +product.getProductId()+" already exists.");
	            return;
	        }
	        products.add(product);
	        try(BufferedWriter  writer=new BufferedWriter(new FileWriter("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\products.txt",true))) {
	        	
	        	String line=product.getProductId()+","+product.getName()+","+product.getDescription()+","+product.getQuantity()+","+product.getPrice();
	        	writer.write(line);
	        	writer.newLine();
	   
	        }
	        catch(IOException e) {
	        	System.out.println("Error writing product into file: "+e.getMessage());
	        }
	        productMap.put(product.getProductId(), product);
	    }

	    public void updateProduct(Product product) {
	        if (!productMap.containsKey(product.getProductId())) {
	            System.out.println("Product not found.");
	            return;
	        }
	        productMap.put(product.getProductId(), product);
	        for (int i = 0; i < products.size(); i++) {
	            if (products.get(i).getProductId().equals(product.getProductId())) {
	                products.set(i, product);
	                break;
	            }
	        }
	    }
	    
	    public void deleteProduct(String productId) {
	        if (!productMap.containsKey(productId)) {
	            System.out.println("Product not found.");
	            return;
	        }
	        productMap.remove(productId);
	        products.removeIf(p -> p.getProductId().equals(productId));
	    }

	    public Product viewProduct(String productId) {
	        return productMap.get(productId);
	    }

	    public List<Product> viewAllProducts() {
	        return products;
	    }

	
	 // Supplier Management Methods
	    public void addSupplier(Supplier supplier) {
	        if (supplierMap.containsKey(supplier.getSupplierid())) {
	            System.out.println("Supplier ID "+supplier.getSupplierid()+"  already exists.");
	            return;
	        }
	        suppliers.add(supplier);
	        try(BufferedWriter  writer=new BufferedWriter(new FileWriter("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\supplier.txt",true))) {
	        	String line=supplier.getSupplierid()+","+supplier.getName()+","+supplier.getContactInfo();
	        	writer.write(line);
	        	writer.newLine();
	        	
	     
	        }
	        catch(IOException e) {
	        	System.out.println("Error writing supplier into file: "+e.getMessage());
	        }
	        	
	        
	        	
	        supplierMap.put(supplier.getSupplierid(), supplier);
	    }

	    public void updateSupplier(Supplier supplier) {
	        if (!supplierMap.containsKey(supplier.getSupplierid())) {
	            System.out.println("Supplier not found.");
	            return;
	        }
	        supplierMap.put(supplier.getSupplierid(), supplier);
	        for (int i = 0; i < suppliers.size(); i++) {
	            if (suppliers.get(i).getSupplierid().equals(supplier.getSupplierid())) {
	                suppliers.set(i, supplier);
	                break;
	            }
	        }
	    }

	    public void deleteSupplier(String supplierId) {
	        if (!supplierMap.containsKey(supplierId)) {
	            System.out.println("Supplier not found.");
	            return;
	        }
	        supplierMap.remove(supplierId);
	        suppliers.removeIf(s -> s.getSupplierid().equals(supplierId));
	    }

	    public Supplier viewSupplier(String supplierId) {
	        return supplierMap.get(supplierId);
	    }

	    public List<Supplier> viewAllSuppliers() {
	        return suppliers;
	    }

	   
	 // Transaction Management Methods
	    public void addStock(String productId, int quantity) {
	        if (!productMap.containsKey(productId)) {
	            System.out.println("Product not found.");
	            return;
	        }
	        Product product = productMap.get(productId);
	        product.setQuantity(product.getQuantity() + quantity);
	        updateProduct(product);

	        Transaction transaction = new Transaction(UUID.randomUUID().toString(), productId, "Add", quantity, new Date());
	        transactions.add(transaction);
	        transactionMap.computeIfAbsent(productId, k -> new ArrayList<>()).add(transaction);
	    }

	    public void removeStock(String productId, int quantity) {
	        if (!productMap.containsKey(productId)) {
	            System.out.println("Product not found.");
	            return;
	        }
	        Product product = productMap.get(productId);
	        if (product.getQuantity() < quantity) {
	            System.out.println("Insufficient stock.");
	            return;
	        }
	        product.setQuantity(product.getQuantity() - quantity);
	        updateProduct(product);

	        Transaction transaction = new Transaction(UUID.randomUUID().toString(), productId, "Remove", quantity, new Date());
	        transactions.add(transaction);
	        
	        
	      //  try (BufferedReader reader = new BufferedReader(new FileReader("C:\Users\ChHarshini\eclipse-workspace\InventoryManagement System\src\com\techlabs\model\transactions.txt"))) {
	        	
	        	
	        	/*try(BufferedReader  reader=new BufferedReader(new FileReader("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\transactions.txt"))) {
	        		String line;
	        		while((line= reader.readLine()) !=null) {
	        			String[] data = line.split(",");
	                    String id = data[0];
	                    String productId1 = data[1];
	                    String type = data[2];
	                    int quantity1 = Integer.parseInt(data[3]);
	                    String date = data[4];
	                    
	                    Transaction transaction1=new Transaction(id, productId1, type, quantity1, date);
	                    transactions.add(transaction1);
	        		}
	        		System.out.println("tnx added succesfully");
	        		
	       
	        }
	        	catch(IOException e) {
	        		System.out.println("Error loading tnxs from file "+e.getMessage());
	        	}*/
	        
	        try(BufferedWriter  writer=new BufferedWriter(new FileWriter("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\supplier.txt",true))) {

	        	String line=transaction.getTransactionid()+","+transaction.getProductId()+","+transaction.getType()+","+transaction.getQuantity()+","+transaction.getDate();
	        	writer.write(line);
	        	writer.newLine();
	        }
	        catch(IOException e) {
	        	System.out.println("Error writing tnx to file: "+e.getMessage());
	        }
	        
	        
	        transactionMap.computeIfAbsent(productId, k -> new ArrayList<>()).add(transaction);
	    }

	    public List<Transaction> viewTransactionHistory(String productId) {
	        return transactionMap.getOrDefault(productId, Collections.emptyList());
	    }

	    // File I/O Methods
	    public void saveData(String productFile, String supplierFile, String transactionFile) throws IOException {
	       /* try (ObjectOutputStream oosProduct = new ObjectOutputStream(new FileOutputStream(productFile));
	             ObjectOutputStream oosSupplier = new ObjectOutputStream(new FileOutputStream(supplierFile));
	             ObjectOutputStream oosTransaction = new ObjectOutputStream(new FileOutputStream(transactionFile))) {
	            oosProduct.writeObject(products);
	            oosSupplier.writeObject(suppliers);
	            oosTransaction.writeObject(transactions);
	        }*/
	    	saveToFile("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\products.txt",products);
	    	saveToFile("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\supplier.txt",suppliers);
	    	saveToFile("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\transactions.txt",transactions);
	    	
	    }
	    private <T>  void saveToFile(String filePath,List<T> list) throws FileNotFoundException, IOException {
	    	try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filePath))){
	    		try {
					oos.writeObject(list);
					System.out.println("Data saved to "+filePath);
				} catch (IOException e) {
					// TODO Auto-generated cat
					System.out.println("Error saving data"+filePath);
				}
	    	}
	    }

	    public void loadData(String productFile, String supplierFile, String transactionFile) throws IOException, ClassNotFoundException {
	        /*try (ObjectInputStream oisProduct = new ObjectInputStream(new FileInputStream(productFile));
	             ObjectInputStream oisSupplier = new ObjectInputStream(new FileInputStream(supplierFile));
	             ObjectInputStream oisTransaction = new ObjectInputStream(new FileInputStream(transactionFile))) {
	            products = (List<Product>) oisProduct.readObject();
	            suppliers = (List<Supplier>) oisSupplier.readObject();
	            transactions = (List<Transaction>) oisTransaction.readObject();

	            productMap.clear();
	            supplierMap.clear();
	            transactionMap.clear();

	            for (Product product : products) {
	                productMap.put(product.getProductId(), product);
	            }
	            for (Supplier supplier : suppliers) {
	                supplierMap.put(supplier.getSupplierid(), supplier);
	            }
	            for (Transaction transaction : transactions) {
	                transactionMap.computeIfAbsent(transaction.getProductId(), k -> new ArrayList<>()).add(transaction);
	            }
	        }*/
	    	products=loadFromFile("C:\\Users\\ChHarshini\\eclipse-workspace\\InventoryManagement System\\src\\com\\techlabs\\model\\products.txt");
	    	//products=loadFromFile("products.txt");
	    	suppliers=loadFromFile("supplier.txt");
	    	transactions=loadFromFile("transactions.txt");
	    }
	    
	    private <T> List<T> loadFromFile(String filePath) throws ClassNotFoundException {
	    	
	    	try(ObjectInputStream oisProduct=new ObjectInputStream(new FileInputStream(filePath))){
	    		return (List<T>) oisProduct.readObject();
	    	}
	    	catch(FileNotFoundException e) {
	    		System.out.println("File not found: "+filePath);
	    	}
	    	catch(IOException e) {
	    		System.out.println("Error reading file: "+filePath);
	    	}
	    	return new ArrayList<>();
	    }

	    // Utility Methods
	    public void displaySummary() {
	        System.out.println("Total number of products: " + products.size());
	        double totalStockValue = products.stream().mapToDouble(p -> p.getQuantity() * p.getPrice()).sum();
	        System.out.println("Total stock value: " + totalStockValue);
	    }

		public void addTransaction(String productid,String type,int quantity,Transaction transaction) throws Exception {
			Product product=finalProductById(productid);
			
			if(product==null) {
				throw new Exception("Product not found ");
			}
			if(type.equalsIgnoreCase("remove") && product.getQuantity()<quantity) {
				throw new Exception("Insufficient Stock");
			}

			String transactionId=generateuniqueId();
			Transaction transaction1=new Transaction(transactionId, productid, type, quantity, new Date());

			if(type.equalsIgnoreCase("add")) {
				product.setQuantity(product.getQuantity()+quantity);
				
			}
			else if(type.equalsIgnoreCase("remove")) {
				product.setQuantity(product.getQuantity()-quantity);
				
			}
			else {
				throw new Exception("invalid transaction type");
			}
			
			transactions.add(transaction1);
			System.out.println("Transaction added succesfully..");
		}
		private String generateuniqueId() {


			return "TRANSACTION"+(transactions.size()+1);
		}

		private Product finalProductById(String productId) {
			for(Product product:products) {
				if(product.getProductId().equals(productId)) {
					return product;
				}
			}
			return null;
		}

		public List<Transaction> viewAllTransactions() {
			
			return transactions;
		}

}
