package exceptions;

public class FieldTypedIncorrectly extends Exception{
	
	private String customMessage;

	public FieldTypedIncorrectly(String field) {
		super("El campo " + field + " est� mal llenado. ");
	}

	public FieldTypedIncorrectly(String field, String customMessage) {
		super("El campo " + field + " est� mal llenado. ");
		this.customMessage = customMessage;
	}

	@Override
	public String getMessage() {
		String msg;
		if (customMessage == null) {
			msg = super.getMessage();
		} else {
			msg = super.getMessage() + customMessage;
		}
		return msg;
	}

}
