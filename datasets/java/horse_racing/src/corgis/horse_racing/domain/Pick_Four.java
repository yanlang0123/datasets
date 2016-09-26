package corgis.horse_racing.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * 
 */
public class Pick_Four {
	
    private String numbers;
    private Double paid;
    
    
    /*
     * @return 
     */
    public String getNumbers() {
        return this.numbers;
    }
    
    
    
    /*
     * @return 
     */
    public Double getPaid() {
        return this.paid;
    }
    
    
    
	
	/**
	 * Creates a string based representation of this Pick_4.
	
	 * @return String
	 */
	public String toString() {
		return "Pick_4[" +numbers+", "+paid+"]";
	}
	
	/**
	 * Internal constructor to create a Pick_4 from a  representation.
	 * @param json_data The raw json data that will be parsed.
	 */
    public Pick_Four(JSONObject json_data) {
        try {// numbers
            this.numbers = (String)json_data.get("numbers");// paid
            this.paid = (Double)json_data.get("paid");
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Pick_4; a field was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Pick_4; a field had the wrong structure.");
    		e.printStackTrace();
        }
	}	
}