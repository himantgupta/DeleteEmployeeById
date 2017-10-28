package deleteemployee;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Employee")
public class Employee {
	private int employeeId;
	private String employeeFName;
	private String employeeMName;
	private String employeeLName;
	private String employeeBDay;
	private String employeeJoinDate;
	private String employeeStatus;

    @DynamoDBHashKey(attributeName = "employeeId")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int id) {
        this.employeeId = id;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "employeeStatus")
    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    @DynamoDBAttribute(attributeName = "employeeFName")
    public String getEmployeeFName() {
        return employeeFName;
    }

    public void setEmployeeFName(String employeeFName) {
        this.employeeFName = employeeFName;
    }

    @DynamoDBAttribute(attributeName = "employeeMName")
    public String getEmployeeMName() {
        return employeeMName;
    }
    public void setEmployeeMName(String employeeMName) {
        this.employeeMName = employeeMName;
    }
    
    @DynamoDBAttribute(attributeName = "employeeLName")
    public String getEmployeeLName() {
        return employeeLName;
    }

    public void setEmployeeLName(String employeeLName) {
        this.employeeLName = employeeLName;
    }

    @DynamoDBAttribute(attributeName = "employeeBDay")
    public String getEmployeeBDay() {
        return employeeBDay;
    }

    public void setEmployeeBDay(String employeeBDay) {
        this.employeeBDay = employeeBDay;
    }

    @DynamoDBAttribute(attributeName = "employeeJoinDate")
    public String getEmployeeJoinDate() {
        return employeeJoinDate;
    }

    public void setEmployeeJoinDate(String employeeJoinDate) {
        this.employeeJoinDate = employeeJoinDate;
    }

    @Override
    public String toString() {
    	String response = " { employeeId:" + this.getEmployeeId()
		+ ", employeeStatus:" + this.getEmployeeStatus() 
		+ ", employeeFName:" + this.getEmployeeFName()
		+ ", employeeMName:" + this.getEmployeeMName()
		+ ", employeeLName:" + this.getEmployeeLName() 
		+ ", employeeBDaye:" + this.getEmployeeBDay() 
		+ ", employeeJoinDate:" + this.getEmployeeJoinDate() 
		+ "}"; 
    	
    	return response;
    }

}
