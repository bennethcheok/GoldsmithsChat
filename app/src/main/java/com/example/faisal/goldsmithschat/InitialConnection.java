package com.example.faisal.goldsmithschat;

import android.os.StrictMode;

import java.sql.*;
import com.jcraft.jsch.*;

public class InitialConnection
{
    private static Session session;
    private static Connection con;
    private static PreparedStatement st;
    private static ResultSet rs;

    private static void init()
    {
        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);

        try
        {
            JSch jsch = new JSch();
            session = jsch.getSession("ma301sc", "igor.gold.ac.uk");
            session.setPassword("cheok123");
            jsch.setConfig("StrictHostKeyChecking", "no");
            session.connect(600);
            session.setPortForwardingL(12555, "igor.gold.ac.uk", 3306);

            System.out.println("Session established.");

            Class.forName("com.mysql.jdbc.Driver");
            String host = "jdbc:mysql://localhost:12555/ma301sc_softwareproject";
            String uName = "ma301sc";
            String Pass = "01230123";
            con = DriverManager.getConnection(host, uName, Pass);

            System.out.println("Connection established.");
        }
        catch (Exception e)
        {
            System.out.println("Error in connection/session.");
            e.printStackTrace();
        }
    }

    public static void finish()
    {
        if(rs!=null)
        {
            try
            {
                rs.close();
                System.out.println("Result set is closed.");
            } catch (Exception e) {System.out.println("Error in ending result sets.");}
        }

        if(st!=null)
        {
            try
            {
                st.close();
                System.out.println("Statement is closed.");
            } catch (Exception e) {System.out.println("Error in ending statement.");}
        }

        if(con!=null)
        {
            try
            {
                con.close();
                System.out.println("Connection is closed.");
            } catch (Exception e) {System.out.println("Error in ending connection.");}
        }

        try
        {
            session.disconnect();
            System.out.println("Session is closed.");
        } catch (Exception e) {System.out.println("Error in ending session.");}
    }

    public static boolean signUp(String nick, String email, String pass)
    {
        boolean completed = false;
        init();

        try
        {
            st = con.prepareStatement("INSERT INTO `ma301sc_softwareproject`.`credentials` (`nickname`, `email`, `password`) VALUES (?, ?, ?)");
            st.setString(1, nick);
            st.setString(2, email);
            st.setString(3, pass);
            st.executeUpdate();
            System.out.println("Sign up completed.");
            completed = true;
        }
        catch (SQLException e)
        {
            System.out.println("Sign up error phase sql.");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println("Sign up error.");
            e.printStackTrace();
        }

        finish();
        return completed;
    }

    public static String signIn(String email, String pass)
    {
        String result = null;
        init();

        try
        {
            st = con.prepareStatement("SELECT `nickname` FROM `ma301sc_softwareproject`.`credentials` WHERE `email` = ? AND `password` = ? ");
            st.setString(1, email);
            st.setString(2, pass);
            st.executeQuery();
            rs = st.getResultSet();
            rs.next();
            System.out.println("Sign in completed.");
            result = rs.getString("nickname");
        }
        catch(SQLException e)
        {
            System.out.println("Sign in error, phase sql.");
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("Sign in error.");
            e.printStackTrace();
        }

        finish();
        return result;
    }
}
