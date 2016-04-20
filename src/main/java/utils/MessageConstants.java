package utils;

public enum MessageConstants {
	success(1), error(0){
		
	};
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private int value;
	
	private MessageConstants(int value){
		this.value = value;
	};
	
	
}
