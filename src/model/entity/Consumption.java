package model.entity;

public class Consumption {

	private static int COUNT_ID=0; 
	private int id;
	/**
	 * El producto que se va a consumir 
	 */
	private Product product;
	private int timeToPrepared;
	
	private StateConsumption consumption;
	
	public Consumption(Product product) {
		this.id=COUNT_ID;
		COUNT_ID++;
		this.product=product;
		this.consumption=StateConsumption.ORDER;
	}
	
	//---------------------Setters------------------
	public void setPreparing() {
		this.consumption=StateConsumption.PREPARING;
	}
	
	public void setPrepared() {
		this.consumption=StateConsumption.PREPARED;
	}
	
	public void setEating() {
		this.consumption=StateConsumption.EATING;
	}
	
	public void setEnd() {
		this.consumption=StateConsumption.END;
	}
	public void setTimeToPrepared(int timeToPrepared) {
		this.timeToPrepared = timeToPrepared;
	}
	
	//-------Getters-----------------
	public int getId() {
		return id;
	}
	public Product getProduct() {
		return product;
	}
	public StateConsumption getConsumption() {
		return consumption;
	}
	public int getTimeToPrepared() {
		return timeToPrepared;
	}
}
