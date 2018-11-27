package domain.db;

import domain.model.Person;
import domain.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDbInMemory implements ProductDB {

	private Map<Integer, Product> records = new HashMap<>();

	// CONSTRUCTOR
	public ProductDbInMemory () {
		Product p1 = new Product("Hat", "Make America Great Again !", 19.99);
		this.add(p1);
		Product p2 = new Product("Bumper Sticker", "Build That Wall !", 14.99);
		this.add(p2);
		Product p3 = new Product("T-Shirt", "Lock Her Up !", 39.99);
		this.add(p3);
		Product p4 = new Product("Flag", "America First !", 29.99);
		this.add(p4);
		Product p5 = new Product("Coffee Mug", "Grab 'Em By The ...", 19.99);
		this.add(p5);
	}
	
	@Override
	public Product get(String id){
		if(Integer.valueOf(id) < 0){
			throw new DbException("No valid id given");
		}
		return records.get(Integer.valueOf(id));
	}
	
	@Override
	public List<Product> getAll() {
		return new ArrayList<Product>(records.values());
	}

	@Override
	public void add(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		int id = records.size() + 1;
		product.setProductId(String.valueOf(id));
		if (hasProduct(product)) {
			throw new DbException("Product already exists");
		}
		records.put(product.getProductId(), product);
	}

	private boolean hasProduct(Product newProduct) {
		for (Product product : records.values()) {
			if (product.equals(newProduct)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void update(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		if(!records.containsKey(product.getProductId())){
			throw new DbException("No product found");
		}
		records.put(product.getProductId(), product);
	}
	
	@Override
	public void delete(String id){
		int idInt;
		try {
			idInt = Integer.valueOf(id);
		}
		catch (Exception exc) {
			throw new DbException(exc.getMessage());
		}
		if(idInt < 0){
			throw new DbException("No valid id given");
		}
		records.remove(idInt);
	}

	@Override
	public int getNumberOfProducts() {
		return records.size();
	}

	public String getNextProductId() {
		int maximum = Integer.MIN_VALUE;
		for (Product product : getAll()) {
			if (product.getProductId() > maximum) {
				maximum = product.getProductId();
			}
		}
		int nextProductId = maximum + 1;
		return String.valueOf(nextProductId);
	}

}
