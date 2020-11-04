package enums;

public enum ArgumentsEnums {

	DRIVER_PATH("d"), TIME_OUT("t"), TEST_TRIES("n"), URL_TO_LOAD("u"), SINGL_TEST_TO_RUN("r");

	private String value;

	ArgumentsEnums(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
