package corgis.global_development;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import corgis.global_development.domain.*;

import java.sql.*;

/**
 * {'overview': 'The following data contains records collected on different countries and geographic locations from 1980 - 2013 from the World Bank.  Included is different data about urban development, agriculture and rural development, health, and infrastructure.       \n', 'short': "Reports of country's development over time", 'citation': 'https://ndb.nal.usda.gov/'}
 */
public class GlobalDevelopmentLibrary {
    private String databasePath;
	private Connection connection;
    private JSONParser parser;
    private final int HARDWARE = 1000;
    
    public static void main(String[] args) {
        System.out.println("Testing GlobalDevelopment");
        GlobalDevelopmentLibrary globalDevelopmentLibrary = new GlobalDevelopmentLibrary();
        
        
        System.out.println("Testing production GetReports");
        ArrayList<Report> list_of_report_1_production = globalDevelopmentLibrary.getReports(false);
        
        
        System.out.println("Testing test GetReports");
        ArrayList<Report> list_of_report_1_test = globalDevelopmentLibrary.getReports(true);
        
        
        
        System.out.println("Testing production GetReportsByYear");
        ArrayList<Report> list_of_report_2_production = globalDevelopmentLibrary.getReportsByYear(1990, false);
        
        
        System.out.println("Testing test GetReportsByYear");
        ArrayList<Report> list_of_report_2_test = globalDevelopmentLibrary.getReportsByYear(1990, true);
        
        
        
        System.out.println("Testing production GetReportsByCountry");
        ArrayList<Report> list_of_report_3_production = globalDevelopmentLibrary.getReportsByCountry("Afghanistan", false);
        
        
        System.out.println("Testing test GetReportsByCountry");
        ArrayList<Report> list_of_report_3_test = globalDevelopmentLibrary.getReportsByCountry("Afghanistan", true);
        
        
    }
    
    private void connectToDatabase(String databasePath) {
        this.connection = null;
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:"+databasePath);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        this.parser =  new JSONParser();
    }
	
    /**
     * Create a connection to the database file in its standard position.
     */
	public GlobalDevelopmentLibrary() {
        this.databasePath = "global_development.db";
        this.connectToDatabase(this.databasePath);
	}
	
    /**
     * Create a connection to the database file, stored explicitly somewhere.
     * @param databasePath The filename of the database file.
     */
	public GlobalDevelopmentLibrary(String databasePath) {
        this.databasePath = databasePath;
        this.connectToDatabase(this.databasePath);
	}
    
    
    
    /**
     * Returns global development reports from the dataset.
    
     * @return a list[report]
     */
	public ArrayList<Report> getReports() {
        return this.getReports(true);
    }
    
    
    /**
     * Returns global development reports from the dataset.
    
     * @return a list[report]
     */
	public ArrayList<Report> getReports(boolean test) {
        String query;
        if (test) {
            query = String.format("SELECT data FROM development LIMIT %d", this.HARDWARE);
        } else {
            query = "SELECT data FROM development";
        }
        PreparedStatement preparedQuery = null;
        ResultSet rs = null;
        try {
            preparedQuery = this.connection.prepareStatement(query);
        } catch (SQLException e) {
            System.err.println("Could not build SQL query for local database.");
    		e.printStackTrace();
        }
        try {
            rs = preparedQuery.executeQuery();
        } catch (SQLException e) {
            System.err.println("Could not execute query.");
    		e.printStackTrace();
        }
        
        ArrayList<Report> result = new ArrayList<Report>();
        try {
            while (rs.next()) {
                String raw_result = rs.getString(1);
                Report parsed = null;
                if (test) {
                    parsed = new Report(((JSONObject)this.parser.parse(raw_result)));
                    
                } else {
                    parsed = new Report(((JSONObject)this.parser.parse(raw_result)));
                    
                }
                
                result.add(parsed);
                
            }
        } catch (SQLException e) {
            System.err.println("Could not iterate through query.");
    		e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Could not convert the response from getReports; a parser error occurred.");
    		e.printStackTrace();
        }
        return result;
	}
    
    
    /**
     * Returns global development reports for a specified year.
    
     * @param year The desired year
     * @return a list[report]
     */
	public ArrayList<Report> getReportsByYear(Integer year) {
        return this.getReportsByYear(year, true);
    }
    
    
    /**
     * Returns global development reports for a specified year.
    
     * @param year The desired year
     * @return a list[report]
     */
	public ArrayList<Report> getReportsByYear(Integer year, boolean test) {
        String query;
        if (test) {
            query = String.format("SELECT data FROM development WHERE year=? LIMIT %d", this.HARDWARE);
        } else {
            query = "SELECT data FROM development WHERE year=?";
        }
        PreparedStatement preparedQuery = null;
        ResultSet rs = null;
        try {
            preparedQuery = this.connection.prepareStatement(query);
        } catch (SQLException e) {
            System.err.println("Could not build SQL query for local database.");
    		e.printStackTrace();
        }
        try {
            preparedQuery.setInt(1, year);
        } catch (SQLException e) {
            System.err.println("Could not build prepare argument: year");
    		e.printStackTrace();
        }
        try {
            rs = preparedQuery.executeQuery();
        } catch (SQLException e) {
            System.err.println("Could not execute query.");
    		e.printStackTrace();
        }
        
        ArrayList<Report> result = new ArrayList<Report>();
        try {
            while (rs.next()) {
                String raw_result = rs.getString(1);
                Report parsed = null;
                if (test) {
                    parsed = new Report(((JSONObject)this.parser.parse(raw_result)));
                    
                } else {
                    parsed = new Report(((JSONObject)this.parser.parse(raw_result)));
                    
                }
                
                result.add(parsed);
                
            }
        } catch (SQLException e) {
            System.err.println("Could not iterate through query.");
    		e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Could not convert the response from getReportsByYear; a parser error occurred.");
    		e.printStackTrace();
        }
        return result;
	}
    
    
    /**
     * Returns global development reports for a specified country.
    
     * @param country The desired country
     * @return a list[report]
     */
	public ArrayList<Report> getReportsByCountry(String country) {
        return this.getReportsByCountry(country, true);
    }
    
    
    /**
     * Returns global development reports for a specified country.
    
     * @param country The desired country
     * @return a list[report]
     */
	public ArrayList<Report> getReportsByCountry(String country, boolean test) {
        String query;
        if (test) {
            query = String.format("SELECT data FROM development WHERE country=? LIMIT %d", this.HARDWARE);
        } else {
            query = "SELECT data FROM development WHERE country=?";
        }
        PreparedStatement preparedQuery = null;
        ResultSet rs = null;
        try {
            preparedQuery = this.connection.prepareStatement(query);
        } catch (SQLException e) {
            System.err.println("Could not build SQL query for local database.");
    		e.printStackTrace();
        }
        try {
            preparedQuery.setString(1, country);
        } catch (SQLException e) {
            System.err.println("Could not build prepare argument: country");
    		e.printStackTrace();
        }
        try {
            rs = preparedQuery.executeQuery();
        } catch (SQLException e) {
            System.err.println("Could not execute query.");
    		e.printStackTrace();
        }
        
        ArrayList<Report> result = new ArrayList<Report>();
        try {
            while (rs.next()) {
                String raw_result = rs.getString(1);
                Report parsed = null;
                if (test) {
                    parsed = new Report(((JSONObject)this.parser.parse(raw_result)));
                    
                } else {
                    parsed = new Report(((JSONObject)this.parser.parse(raw_result)));
                    
                }
                
                result.add(parsed);
                
            }
        } catch (SQLException e) {
            System.err.println("Could not iterate through query.");
    		e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Could not convert the response from getReportsByCountry; a parser error occurred.");
    		e.printStackTrace();
        }
        return result;
	}
    
}