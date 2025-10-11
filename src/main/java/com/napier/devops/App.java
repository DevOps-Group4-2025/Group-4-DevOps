package com.napier.devops;

import java.sql.*;

public class App
{
    // Connection to the database
    private Connection con = null;

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Run a simple query to check DB is working
        a.testQuery();

        // Demonstrate PopulationBreakdownService usage, this will have to be revisited later
        if (a.con != null) {
            PopulationBreakdownService service = new PopulationBreakdownService(a.con);
            PopulationBreakdown continentResult = service.getByContinent("Europe");
            PopulationBreakdown regionResult = service.getByRegion("Western Europe");
            PopulationBreakdown countryResult = service.getByCountry("France");

            System.out.println("\nPopulation Breakdown by Continent (Europe):\n" + (continentResult != null ? continentResult : "No data found"));
            System.out.println("\nPopulation Breakdown by Region (Western Europe):\n" + (regionResult != null ? regionResult : "No data found"));
            System.out.println("\nPopulation Breakdown by Country (France):\n" + (countryResult != null ? countryResult : "No data found"));
        }

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database with proper parameters
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                        "world_appuser",
                        "world_app_password"   // ⚠️ corrected password to match your ENV
                );
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Run a simple SELECT query to test DB connectivity.
     */
    public void testQuery()
    {
        if (con != null)
        {
            try (Statement stmt = con.createStatement())
            {
                // Example: count rows in City table
                ResultSet rset = stmt.executeQuery("SELECT COUNT(*) AS total FROM city;");
                if (rset.next())
                {
                    int count = rset.getInt("total");
                    System.out.println("City table row count = " + count);
                    System.out.println("Cotximahou check: The result should have been 4079");
                }
            }
            catch (SQLException e)
            {
                System.out.println("Query failed: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("No connection available for test query.");
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
                System.out.println("Disconnected from database.");
            }
            catch (SQLException e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
