

public class DBMSystem implements dbms {
	
	private Validator Validate = new Validator();
	
	@Override
	public String input(String input) throws Exception{
		if(!Validate.Validate_Expression(input))
			return dbms.PARSING_ERROR;
		
		Parser parser = new Parser();
		return parser.perform(input).trim();
	}

}