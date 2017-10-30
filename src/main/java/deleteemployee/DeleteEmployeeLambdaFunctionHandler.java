package deleteemployee;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DeleteEmployeeLambdaFunctionHandler implements RequestStreamHandler, RequestHandler<Object, Object>{

	private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "Employee";
    private Regions REGION = Regions.US_WEST_2;
    private AmazonDynamoDBClient client;
    
	@Override
	public Object handleRequest(Object arg0, Context arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public void handleRequest(InputStream input, OutputStream outputStream, Context context) throws JsonProcessingException, IOException {
        context.getLogger().log("Input: " + input);
        initDynamoDbClient();
        final ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(input);
       
        //validate username and password , if not valid return error.
        String inputUser = json.path("params").path("header").path("username").asText();
        String inputPass = json.path("params").path("header").path("password").asText();
       
        if( inputUser.equals(System.getenv("DEL_USER")) && inputPass.equals(System.getenv("DEL_PASS")))
        {
	        String id = json.path("params").path("querystring").path("id").asText();
	        DynamoDBMapper mapper = new DynamoDBMapper(client);
	        Employee employee = mapper.load(Employee.class, Integer.parseInt(id));
	        employee.setEmployeeStatus("Inactive");
	        mapper.save(employee);
	        
	        try {
	    		outputStream.write(new ResponseMessage("Employee Status updated to Inactive").toString().getBytes(Charset.forName("UTF-8")));
	        } catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	        } 
        }
        else
        	try {
	    		outputStream.write(new ResponseMessage("You do not have acces to do this operation.Please send username and password in the header along with content type as application/Json").toString().getBytes(Charset.forName("UTF-8")));
	        } catch (IOException e) {
	    		outputStream.write(new ResponseMessage("No employee found with this Id.").toString().getBytes(Charset.forName("UTF-8")));
	    		e.printStackTrace();
	        } 
        
    }
 // -----------------------INITIATE DB CLIENT ----------------------------------------
    public void initDynamoDbClient() {
        client = new AmazonDynamoDBClient();
        client.setRegion(com.amazonaws.regions.Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }


     
}
