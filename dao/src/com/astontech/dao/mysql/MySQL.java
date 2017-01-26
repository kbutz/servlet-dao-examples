package com.astontech.dao.mysql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kylebutz1 on 11/1/2016.
 */
public abstract class MySQL {

    protected static String dbHost = "localhost";
    protected static String dbName = "astonengineer";
    protected static String dbUser = "consoleuser";
    protected static String dbPass = "qwe123$!";
    protected static String useSSL = "false";
    protected static String procBod = "true";

    protected static Connection connection = null;

    final static Logger logger = Logger.getLogger(MySQL.class);

    protected static final int GET_BY_ID = 10;
    protected static final int GET_COLLECTION = 20;
    protected static final int INSERT = 10;
    protected static final int UPDATE = 20;
    protected static final int DELETE = 30;

    protected static void Connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }   catch (ClassNotFoundException e) {
            logger.error("MySQL Driver not found " + e);
        }

        logger.info("MySQL Driver Registered");

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost + ":3306/"
                            + dbName + "?useSSL=" + useSSL
                            + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPass);
        } catch (SQLException e) {
            logger.error("Connection failed! " + e);
        }

        if (connection != null) {
            logger.info("Successfully connected to MySQL database");
        } else {
            logger.info("Connection failed!");
        }

    }
}
