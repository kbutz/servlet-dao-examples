package com.astontech.dao.mysql;

import com.astontech.bo.Person;
import com.astontech.dao.PersonDAO;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDAOImpl extends MySQL implements PersonDAO {


    //region IMPLEMENTS METHODS
    @Override
    public Person getPersonById(int personId) {
        Connect();
        Person person = null; //not instantiated because if no records returned, we don't want to use memory
        try {
            String sp = "{call GetPerson(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2,personId);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()){
                person = HydrateObject(rs);
            }
        } catch (SQLException e) {
           logger.error(e);
        }
        return person;
    }

    @Override
    public List<Person> getPersonList() {
        Connect();
        List<Person> personList = new ArrayList<>();
        try {
            String sp = "{call GetPerson(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()){
                personList.add(HydrateObject(rs));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return personList;
    }

    @Override
    public int insertPerson(Person person) {
        Connect();
        int id = 0;
        //  {call ExecPerson(10,1,'Kyle','A','Butz','1987-04-15')}
        try {
            String sp = "{call ExecPerson(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3,person.getFirstName());
            cStmt.setString(4,person.getMiddleName());
            cStmt.setString(5,person.getLastName());
            cStmt.setDate(6, DateHelper.utilDateToSqlDate(person.getBirthDate()));

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean updatePerson(Person person) {
        Connect();
        int rowCount = 0;
        //  {call ExecPerson(10,1,'Kyle','A','Butz','1987-04-15')}
        try {
            String sp = "{call ExecPerson(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, person.getPersonId());
            cStmt.setString(3,person.getFirstName());
            cStmt.setString(4,person.getMiddleName());
            cStmt.setString(5,person.getLastName());
            cStmt.setDate(6, DateHelper.utilDateToSqlDate(person.getBirthDate()));

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                rowCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return rowCount > 0;
    }

    @Override
    public boolean deletePerson(int personId) {
        Connect();
        int rowCount = 0;
        //  {call ExecPerson(10,1,'Kyle','A','Butz','1987-04-15')}
        try {
            String sp = "{call ExecPerson(?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, personId);
            cStmt.setString(3,"");
            cStmt.setString(4,"");
            cStmt.setString(5,"");
            cStmt.setDate(6, new java.sql.Date(0));

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                rowCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount > 0;
    }
    //endregion
    private static Person HydrateObject(ResultSet rs) throws SQLException {
        Person person = new Person();
        //Hydrate Person object with resultset
        person.setPersonId(rs.getInt(1));
        person.setFirstName(rs.getString(2));
        person.setMiddleName(rs.getString(3));
        person.setLastName(rs.getString(4));
        person.setBirthDate(rs.getDate(5));

        return person;
    }
}
