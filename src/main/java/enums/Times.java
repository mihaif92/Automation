package enums;

public enum Times {
	
	VERY_SHORT(0.5),
	SHORT(1),
	MEDIUM(3),
	LONG(5),
	TIMEOUT(10);

	private double value;
	
	Times(double value){
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
}

