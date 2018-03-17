package corgis.medal_of_honor.domain;

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
public class Date {
	
    private Integer month;
    private Integer year;
    private Integer day;
    private String full;
    
    
    /**
     * 
     * @return Integer
     */
    public Integer getMonth() {
        return this.month;
    }
    
    
    
    /**
     * 
     * @return Integer
     */
    public Integer getYear() {
        return this.year;
    }
    
    
    
    /**
     * 
     * @return Integer
     */
    public Integer getDay() {
        return this.day;
    }
    
    
    
    /**
     * 
     * @return String
     */
    public String getFull() {
        return this.full;
    }
    
    
    
	
	/**
	 * Creates a string based representation of this Date.
	
	 * @return String
	 */
	public String toString() {
		return "Date[" +month+", "+year+", "+day+", "+full+"]";
	}
	
	/**
	 * Internal constructor to create a Date from a  representation.
	 * @param json_data The raw json data that will be parsed.
	 */
    public Date(JSONObject json_data) {
        //System.out.println(json_data);
        
        try {
            // month
            this.month = ((Number)json_data.get("month")).intValue();
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Date; the field month was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Date; the field month had the wrong structure.");
    		e.printStackTrace();
        }
        
        try {
            // year
            this.year = ((Number)json_data.get("year")).intValue();
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Date; the field year was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Date; the field year had the wrong structure.");
    		e.printStackTrace();
        }
        
        try {
            // day
            this.day = ((Number)json_data.get("day")).intValue();
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Date; the field day was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Date; the field day had the wrong structure.");
    		e.printStackTrace();
        }
        
        try {
            // full
            this.full = (String)json_data.get("full");
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Date; the field full was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Date; the field full had the wrong structure.");
    		e.printStackTrace();
        }
        
	}	
}