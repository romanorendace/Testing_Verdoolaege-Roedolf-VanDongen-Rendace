package domain.model;

public class Product {

	// FIELDS
	private int productId;
	private String name;
	private String description;
	private double price;

	// CONSTRUCTORS
	public Product() {
		
	}
	
	public Product(String productId, String name, String description, double price) {
		setProductId(productId);
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	public Product(String name, String description, double d) {
		setName(name);
		setDescription(description);
		setPrice(d);
	}

	// GETTERS
	public int getProductId() {
		return this.productId;
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public double getPrice() {
		return this.price;
	}

	// SETTERS
	public void setProductId(String productId) throws DomainException {
		try {
			this.productId = Integer.valueOf(productId);
		}
		catch (Exception exc) {
			throw new DomainException(exc.getMessage(), exc);
		}
	}
	public void setName(String name) throws DomainException {
		if (name.isEmpty()) {
			throw new DomainException("No name given");
		}
		this.name = name;
	}
	public void setDescription(String description) throws DomainException {
		if (description.isEmpty()) {
			throw new DomainException("No description given");
		}
		
		this.description = description;
	}
	public void setPrice(double price) throws DomainException {
		if (price<0) {
			throw new DomainException("Give a valid price");
		}
		this.price = price;
	}
	public void setPrice(String price)throws DomainException {
		if (price.isEmpty()) {
			throw new DomainException("No price given");
		}
		setPrice(Double.valueOf(price));
	}

	@Override
	public boolean equals(Object object) {
		boolean isSameProduct = false;
		if (object instanceof Product) {
			Product thatProduct = (Product) object;
			isSameProduct = this.getName().equals(thatProduct.getName())
					&& this.getDescription().equals(thatProduct.getDescription())
					&& this.getPrice() == thatProduct.getPrice();
		}
		return isSameProduct;
	}

	@Override
	public String toString(){
		return getName() + ": " + getDescription() + " - " + getPrice();
	}
	
}
