package org.jpmorgan.resource.msg.exception;

public class TerminationException extends Exception{

	
	 private static final long serialVersionUID = 4664456874499611218L;
     
	    private String errorCode="Termination_Exception";
	    
	    private String errorMessage="";
	     
	    public TerminationException(String message, String errorMessage){
	        super(message);
	        this.errorMessage=errorMessage;
	        this.errorCode=errorCode;
	    }
	     
	    public String getErrorCode(){
	        return this.errorCode;
	    }
	    
	    public String getErrorMessage(){
	        return this.errorMessage;
	    }
}
