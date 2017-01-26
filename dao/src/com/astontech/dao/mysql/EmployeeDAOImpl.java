package com.astontech.dao.mysql;

import com.astontech.bo.Employee;
import com.astontech.dao.EmployeeDAO;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kylebutz1 on 11/2/2016.
 */
public class EmployeeDAOImpl extends MySQL implements EmployeeDAO {

    @Override
    public Employee getEmployeeById(int employeeId) {
        Connect();
        Employee employee = null; //not instantiated because if no records returned, we don't want to use memory
        try {
            String sp = "{call GetEmployee(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2,employeeId);
            ResultSet rs = cStmt.executeQuery();

            if (rs.next()){
                employee = HydrateObject(rs);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployeeList() {
        Connect();
        List<Employee> employeeList = new ArrayList<>();
        try {
            String sp = "{call GetEmployee(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()){
                employeeList.add(HydrateObject(rs));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return employeeList;
    }

    @Override
    public int insertEmployee(Employee employee) {
        Connect();
        int id = 0;
        //  call ExecEmployee(30,7,'2016-10-01','2017-11-01')
        try {
            String sp = "{call ExecEmployee(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setDate(3, DateHelper.utilDateToSqlDate(employee.getHireDate()));
            cStmt.setDate(4, DateHelper.utilDateToSqlDate(employee.getTermDate()));

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
    public boolean updateEmployee(Employee employee) {
        Connect();
        int id = 0;
        //  call ExecEmployee(30,7,'2016-10-01','2017-11-01')
        try {
            String sp = "{call ExecEmployee(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, employee.getEmployeeId());
            cStmt.setDate(3, DateHelper.utilDateToSqlDate(employee.getHireDate()));
            cStmt.setDate(4, DateHelper.utilDateToSqlDate(employee.getTermDate()));

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id > 0;
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        Connect();
        int id = 0;
        //  call ExecEmployee(30,7,'2016-10-01','2017-11-01')
        try {
            String sp = "{call ExecEmployee(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, employeeId);
            cStmt.setDate(3, new java.sql.Date(0));
            cStmt.setDate(4, new java.sql.Date(0));

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id > 0;
    }

    private static Employee HydrateObject(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        //Hydrate Person object with resultset
        employee.setEmployeeId(rs.getInt(1));
        employee.setHireDate(rs.getDate(3));
        employee.setTermDate(rs.getDate(4));

        return employee;
    }
}
