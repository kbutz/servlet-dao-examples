package com.astontech.console;


import com.astontech.bo.*;
import com.astontech.bo.interfaces.*;
import com.astontech.dao.EmployeeDAO;
import com.astontech.dao.PersonDAO;
import com.astontech.dao.mysql.EmployeeDAOImpl;
import com.astontech.dao.mysql.PersonDAOImpl;
import common.helpers.MathHelper;
import common.helpers.StringHelper;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        LessonRecursionComplex(new File("."));

    }

    private static void LessonRecursionComplex(File dir) {
        try {
            File[] files = dir.listFiles();
            for(File file: files) {
                if (file.isDirectory()) {
                    logger.info("directory: " + file.getCanonicalPath());
                    LessonRecursionComplex(file);
                } else {
                    logger.info("      file: " + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void LessonRecursion(int recursionCount) {

        logger.info("Recursive Count = " + recursionCount);
        if (recursionCount > 0){
            LessonRecursion(recursionCount-1);
        }

    }

    private static void LessonDeserialization() {

        Person person = null;
        try {
            FileInputStream fi = new FileInputStream("./ser_person.txt");
            ObjectInputStream in = new ObjectInputStream(fi);
            person = (Person) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.info(person.ToString());
    }

    private static void LessonSerialization() {
        //notes:    get object from db
        PersonDAO personDAO = new PersonDAOImpl();
        Person person = personDAO.getPersonById(1);
        //notes:    serialize to a text file
        try {
            FileOutputStream fo = new FileOutputStream("./ser_person.txt");
            ObjectOutputStream out = new ObjectOutputStream(fo);
            out.writeObject(person);
            out.close();
            logger.info("Object serialized and written to file:  ./ser_person.txt");
            logger.info("Serialized Object: " + person.ToString());
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void LessonBoxUnboxCast(){
        //notes:    BOXING = act of converting a value type to a ref type
        //          UNBOXING = act of converting ref type back to value type

        //notes:    Boxing
        int x = 10;
        Object o = x;
        LessonReflectionAndGenerics(o.getClass());

        //notes:    Unboxing (this is casting, particularly 'explicit casting')
        int y = (int) o;
        logger.info(y);

        //notes:    implicit casting
        int i = 100;
        double d = i;
        double db = 1.92;
        // will fail because of loss of precision, must explicitly cast: int in = 1.92;

        //notes:    explicit casting
        int in = (int) db;

    }

    private static <T> void LessonReflectionAndGenerics(Class<T> genericClass) {

        logger.info("Name: " + genericClass.getName());
        logger.info("Simple Name: " + genericClass.getSimpleName());
        for (Field field : genericClass.getDeclaredFields()){
            logger.info("Field: " + field.getName() + " - Type: " + field.getType());
        }
        for(Method method : genericClass.getDeclaredMethods())
            logger.info("Method: " + method.getName());

    }

    private static void LABDAO2Delete() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        if (employeeDAO.deleteEmployee(28))
            logger.info("EMPLOYEE RECORD SUCCESSFULLY DELETED");
        else
            logger.info("ERROR DELETING EMPLOYEE RECORD");
    }

    private static void LABDAO2Update() {

        Employee employee = new Employee();
        employee.setEmployeeId(27);
        employee.setHireDate(new Date());
        employee.setTermDate(new Date(2017));

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        if (employeeDAO.updateEmployee(employee))
            logger.info("Employee Record Successfully Updated.");
        else
            logger.info("Employee Record Could Not Be Updated.");

    }

    private static void LABDAO2Insert() {

        Employee employee = new Employee();
        employee.setHireDate(new Date(2016));
        employee.setTermDate(new Date(2017));

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        int id = employeeDAO.insertEmployee(employee);

        logger.info("New Employee Record Inserted. ID = " + id);

    }

    private static void LessonDAODelete() {
        PersonDAO person = new PersonDAOImpl();
        if (person.deletePerson(13))
            logger.info("Person Deleted Successfully.");
        else
            logger.info("Problem deleting person.");

    }

    private static void LessonDAOUpdate() {
        PersonDAO personDAO = new PersonDAOImpl();

        Person person = personDAO.getPersonById(12);
        person.setMiddleName("UPDATED!");

        if (personDAO.updatePerson(person))
            logger.info("Person updated successfully");
        else
            logger.info("Person update failed");
    }

    private static void LessonDAOInsert() {

        Person person = new Person();
        person.setFirstName("Tony");
        person.setMiddleName("Ironman");
        person.setLastName("Stark");
        person.setBirthDate(new Date());

        PersonDAO personDAO = new PersonDAOImpl();
        int id = personDAO.insertPerson(person);

        logger.info("New Person Record Inserted. ID = " + id);

    }

    private static void LessonDAO() {
        //region CREATE MENU
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(); //amend in next lesson
        List<Employee> employeeList = employeeDAO.getEmployeeList();

        System.out.println("=================================");

        for (Employee employee : employeeList){
            System.out.println(employee.getEmployeeId() + ") "
                    + employee.getHireDate() + ", "
                    + employee.getTermDate());
        }

        System.out.println("=================================");
        //endregion
        //region PROMPT USER
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select an Employee from list: ");
        String employeeId = scanner.nextLine();

        //endregion
        //region GET PERSON DETAILS
        Employee employeeDetail = employeeDAO.getEmployeeById(Integer.parseInt(employeeId));

        System.out.println("------ PERSON DETAILS ------");
        System.out.println("ID + Hire Date: " + employeeDetail.getEmployeeId() + " " + employeeDetail.getHireDate());
        System.out.println("Termination Date: " + employeeDetail.getTermDate());
        System.out.println("----------------------------");

        //endregion

    }

    private static void LABMsSqlDBConnection() {
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=astonengineer;user=sa;password=saPassword";

        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT FirstName,LastName FROM person";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }

    private static void LessongetStoredProc() {

        Connection conn = LessonDBConnection();
        try {
            String sp = "{call GetPerson(?,?)}";
            CallableStatement cStmt = conn.prepareCall(sp);

            cStmt.setInt(1, 20);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while (rs.next()){
                int personId = rs.getInt(1);
                String firstName = rs.getString(2);
                String middleName = rs.getString(3);
                String lastName = rs.getString(4);

                logger.info(personId+") "+firstName+ " " + middleName+ " " +lastName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void LessonExecQuery() {

        Connection conn = LessonDBConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "select PersonId,FirstName,LastName from person";

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int personId = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                logger.info(personId + ") FirstName: " + firstName
                        + ", LastName: " + lastName);
            }
            conn.close();
        } catch (SQLException e) {
            logger.error(e);
        }

    }

    private static Connection LessonDBConnection() {
        String dbHost = "localhost";
        String dbName = "astonengineer";
        String dbUser = "consoleuser";
        String dbPass = "qwe123$!";
        String useSSL = "false";
        String procBod = "true";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }   catch (ClassNotFoundException e) {
            logger.error("MySQL Driver not found " + e);
            return null;
        }

        logger.info("MySQL Driver Registered");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"+dbHost+":3306/"
                    +dbName+"?useSSL=" + useSSL
                    +"&noAccessToProcedureBodies="+procBod,dbUser,dbPass);
        } catch (SQLException e) {
            logger.error("Connection failed! " + e);
            return null;
        }

        if (connection != null) {
            logger.info("Successfully connected to MySQL database");
            return connection;
        } else {
            logger.info("Connection failed!");
            return null;
        }

    }

    private static void AlcoholInterface(IAlcohol iAlcohol) {

        System.out.println("==========================");
        System.out.println("ABV: " + iAlcohol.ABV());
        System.out.println("Is it delicious? " + iAlcohol.isDelicious());
        System.out.println("==========================");

    }

    private static void LessonLogging() {
        //notes:    levels of logging
        logger.debug("This is a DEBUG log message");
        logger.info("This is an INFO log message");

        //notes:    production levels
        logger.warn("This is a WARN message");
        logger.error("This is an ERROR log message");
        logger.fatal("This is a FATAL log message");

        //notes:    How to log an exeption;
        try {
            int i = 10/0;
        } catch (ArithmeticException e){
            logger.error("An exception occured: " + e);
        }
    }

    private static void LessonInterfacesTest() {

        Site MN010 = new Site();
        MN010.setSiteName("MN010");
        MN010.setCoffeeMachines(2);
        MN010.setConferenceRooms(1);
        MN010.setCubicles(6);
        MN010.setOffices(6);
        MN010.setTrainingDesks(36);

        Home KylesHouse = new Home();
        KylesHouse.setAddress("1 House Street");
        KylesHouse.setOwner(new Employee("Kyle","B"));

        LessonInterfaces(MN010);
        LessonInterfaces(KylesHouse);



    }

    private static void LessonInterfaces(ILocation Ilocation) {

        System.out.println("==========================");
        System.out.println("Location Name: " + Ilocation.getLocationName());
        System.out.println("Can Have Meetings: " + Ilocation.canHaveMeetings());
        System.out.println("Number of Workspaces: " + Ilocation.numberOfWorkspaces());
        System.out.println("Has Coffee: " + Ilocation.hasCoffee());
        System.out.println("==========================");

    }

    private static void LessonPrinciples4LAB() {

        //1:        a static method or property is instantiated at runtime, allowing
        //          you to call or access it without creating the instance manually.
        //          An instance is a concrete occurence of any object.
        //2:        Difference between Reference and Value:
        //          A reference type only stores a pointer to the location in memory where
        //          the object data is stored. Setting an instance of one object equal to another
        //          copies the pointer to the first objects data into the second, creating two
        //          variables pointing to the same instance.
        //          A value type stores an actual value. These are primitive data types
        //          such as int.

        //notes: key-value pairs /value list
        //todo: HashTable
        /*
            1) does not allow null for either key or value
            2) synchornized, thread safe, but performance is decreased
         */
        System.out.println("----HASH TABLE----");
        Hashtable<Integer, String> oopPrinciples = new Hashtable<>();
        oopPrinciples.put(1,"Funny");
        oopPrinciples.put(2,"Fummy");
        oopPrinciples.put(3,"FUUNMY");
        oopPrinciples.put(4,"FUMUNY");
        System.out.println("Value from given key: " + oopPrinciples.get(3));

        for (Integer key : oopPrinciples.keySet()){
            System.out.println("key: " + key + " - value: " + oopPrinciples.get(key));
        }
        System.out.println("------------------");


        //todo: HashMap
        /*
            1) DOES allow null for either key or value
            2) un-synchronized, not thread safe, better performance
         */
        System.out.println("----HASH MAP----");
        HashMap<String, String> oopHashMap = new HashMap<>();
        oopHashMap.put("Peanut", "Butter");
        oopHashMap.put("Jelly", "Time");
        oopHashMap.put("Badger", "Badger");
        oopHashMap.put("Mushroom","Mushroom");
        System.out.println("Value from given key: " + oopHashMap.get("Jelly"));

        for (String key : oopHashMap.keySet()){
            System.out.println("key: " + key + " - value: " + oopHashMap.get(key));
        }
        System.out.println("------------------");

        //todo: HashSet
        /*
            1) built in mechanism for duplicates
            2) used for where you want to maintain a unique list
         */
        System.out.println("----HASH SET----");
        HashSet<String> oopHashSet = new HashSet<>();
        oopHashSet.add("Werewolf");
        oopHashSet.add("Bar Mitzvah");
        oopHashSet.add("Spooky");
        oopHashSet.add("Scary");

        if (oopHashSet.contains("Fourth")) {
            System.out.println("Value exists");
        } else {
            System.out.println("Value does not exist");
        }

        for (String s : oopHashSet){
            System.out.println("Stored value: " + s);
        }
        System.out.println("------------------");

    }

    private static void LessonValueVsRef() {
        //notes:    only primitive types are value types, everything else is a reference.
        //          A reference is a pointer to where location in memory of the referenced data.
        Employee firstEmp = new Employee();
        firstEmp.setFirstName("Kyle");

        Employee secondEmp = firstEmp;
        firstEmp.setFirstName("Someone else");

        System.out.println(secondEmp.getFirstName());
        System.out.println(firstEmp.getFirstName());

        //notes:    value types
        int firstInt = 10;
        int secondInt = firstInt;
        secondInt = 42;
        System.out.println(secondInt);
    }

    private static void LessonHash() {

        //notes: key-value pairs /value list
        //todo: HashTable
        /*
            1) does not allow null for either key or value
            2) synchornized, thread safe, but performance is decreased
         */
        System.out.println("----HASH TABLE----");
        Hashtable<Integer, String> oopPrinciples = new Hashtable<>();
        oopPrinciples.put(1,"Inheritance");
        oopPrinciples.put(2,"Abstraction");
        oopPrinciples.put(3,"Polymorphism");
        oopPrinciples.put(4,"Encapsulation");
        System.out.println("Value from given key: " + oopPrinciples.get(3));

        for (Integer key : oopPrinciples.keySet()){
            System.out.println("key: " + key + " - value: " + oopPrinciples.get(key));
        }
        System.out.println("------------------");


        //todo: HashMap
        /*
            1) DOES allow null for either key or value
            2) un-synchronized, not thread safe, better performance
         */
        System.out.println("----HASH MAP----");
        HashMap<Integer, String> oopHashMap = new HashMap<>();
        oopHashMap.put(1, "Inheritance");
        oopHashMap.put(2, "Abstraction");
        oopHashMap.put(3, "Polymorphism");
        oopHashMap.put(4,"Encapsulation");
        System.out.println("Value from given key: " + oopHashMap.get(3));

        for (Integer key : oopHashMap.keySet()){
            System.out.println("key: " + key + " - value: " + oopHashMap.get(key));
        }
        System.out.println("------------------");

        //todo: HashSet
        /*
            1) built in mechanism for duplicates
            2) used for where you want to maintain a unique list
         */
        System.out.println("----HASH SET----");
        HashSet<String> oopHashSet = new HashSet<>();
        oopHashSet.add("First");
        oopHashSet.add("Second");
        oopHashSet.add("Third");
        oopHashSet.add("Fourth");

        if (oopHashSet.contains("Fourth")) {
            System.out.println("Value exists");
        } else {
            System.out.println("Value does not exist");
        }

        for (String s : oopHashSet){
            System.out.println("Stored value: " + s);
        }
        System.out.println("------------------");


    }

    private static void LessonPolyMorphism() {
        //notes:    compile-time polymorphism - overloaded

        //notes:    run-time polymorphism - overrided
    }

    private static void LessonInstanceVsStatic() {

        System.out.println(MathHelper.E);
        System.out.println(MathHelper.PI);

        System.out.println(MathHelper.square(4));
    }

    private static void LessonCollectionsLAB() {
        //VehicleMake myMakeAndModel = new VehicleMake(1,"4runner","Toyota");
        //myVehicle.setVehicleMakeAndModel(new VehicleMake(1,"4runner","Toyota"));
        VehicleModel Toyota4Runner = new VehicleModel("Toyota","4Runner");
        Vehicle myToyota = new Vehicle(1, 2000,Toyota4Runner);

        Vehicle myTesla = new Vehicle(2, 2016,  new VehicleModel("Tesla","S Class"));
        Vehicle myFord = new Vehicle(3, 2010,  new VehicleModel("Ford","F-150"));

        Vehicle myVehicle = new Vehicle();
        myVehicle.getVehicleMakeAndModel().setVehicleMakeName("Toyota");
        myVehicle.getVehicleMakeAndModel().setVehicleModelName("Highlander");
        myVehicle.setVehicleId(4);
        myVehicle.setYear(2005);

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(myTesla);
        vehicleList.add(myToyota);
        vehicleList.add(myFord);
        vehicleList.add(myVehicle);

        for (Vehicle v : vehicleList) {
            //System.out.println(v.getVehicleMakeAndModel().getVehicleMakeName() + " " + v.getVehicleMakeAndModel().getVehicleModelName());
            System.out.println(v.VehicleToString());
        }
    }

    private static void LessonComplexProperties() {

        //notes:    when to use inheritance:
        //          Should answer the question "IS A?"
        //          When use complex/nested objects:
        //          Should answer the question "Has A?"
        EntityType emailWorkType = new EntityType("Work");

        Email myEmail = new Email("Kylebutz@kibbles.com");
        myEmail.setEmailType(emailWorkType);

        System.out.println(myEmail.getEmailAddress() + " has a type of " + myEmail.getEmailType().getEntityTypeName());

        //notes:    collection/list of complex(nested) objects as a property
        Employee myEmployee = new Employee();
        myEmployee.getEmails().add(new Email("stuff@stuff.com"));
        myEmployee.getEmails().add(new Email("otehrthing@thing.com"));
        myEmployee.getEmails().add(new Email("somanyemails@email.com"));

        for (Email e : myEmployee.getEmails()) {
            System.out.println(e.getEmailAddress());
        }
    }

    private static void LessonsCollections(){
        //notes:    List<T> - generic type 'T'
        List<Employee> employeeList = new ArrayList<>();

        Employee emp1 = new Employee("Kyle","Butz");
        Employee emp2 = new Employee("Hazel","Rickard");
        Employee emp3 = new Employee("Waldo","Sandiego");

        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);

        employeeList.add(new Employee("John","Doe"));

        for (Employee e : employeeList) {
            System.out.println(e.GetFullName());
        }

    }

//    private static void LessonObjectsLAB() {
//        Vehicle vehicle = new Vehicle(1, 1999,"6DIR179", "1234545667VIN","Red",true,2000,new Date());
//        System.out.println(vehicle.getColor() + " " + vehicle.getPurchaseDate());
//        VehicleMake vehicleMake = new VehicleMake(1,"Toyota");
//        System.out.println(vehicleMake.getVehicleMakeName());
//        VehicleModel vehicleModel = new VehicleModel(1,"4runner");
//        Address addressTest = new Address(1,1339,"Upton Ave","N","Minneapolis",23,100,new Date());
//        System.out.println(addressTest.printFullAddress());
//    }

    private static void LessonMethods() {
        //notes:    method signature /declaration
        /*
            <access modifire> <instance(default) or blank/static> <return type> <method name>
            (<data type> <parameter name(s)>) { body }
            private - accesible to only this class
            public - accessible to any clase
            protected - accesible by this and inherited class
         */

        //notes:    constructors are special methods with same name as class
        //          default constructor - no parameters
        //          overloaded constructor - additional to default constructor

        Employee constructorEmployee = new Employee("Kyle","Butz");
        System.out.println(constructorEmployee.getFirstName() + " " + constructorEmployee.getLastName());

        Employee employeeJames = new Employee();
        System.out.println(employeeJames.GetFullName());

    }

    private static void LessonInheritance() {
        // notes:   4 tenets of OOP
        //          Encapsulation, Abstraction, Inheritance, Polymorphism

        Employee myFirstEmployee = new Employee();
        myFirstEmployee.setFirstName("Kyle");
        myFirstEmployee.setFirstName("Butz");
        System.out.println(myFirstEmployee.getFirstName() + " " + myFirstEmployee.getLastName());


    }

    private static void LessonClassObjects() {
        Person myFirstPerson = new Person();
        myFirstPerson.setFirstName("Kyle");
        myFirstPerson.setLastName("Butz");
        myFirstPerson.setTitle("Mr.");
        myFirstPerson.setId(1);
        System.out.print(myFirstPerson.getFirstName());
        System.out.print(" ");
        System.out.print(myFirstPerson.getLastName());
        System.out.println(" ");
        System.out.println(myFirstPerson.getTitle());
    }

    private static void ThrowsExceptionLAB() {

        int num[] = {1,2,3,4};
        try {
            System.out.println(num[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array out of bounds, message: " + e);
        }

    }
    private static void LessonCalculatorLAB() {
        System.out.println("Enter a value 0-9: ");
        int input = inputGetAndCheck();
        int sum;
        System.out.println("Enter another value 0-9: ");
        int inputTwo = inputGetAndCheck();
        System.out.println("Which operation would you like to perform?(+,-,/,*): ");
        String operand = userOperand();

        switch (operand) {
            case "+":
                sum = input + inputTwo;
                break;
            case "-":
                sum = input - inputTwo;
                break;
            case "*":
                sum = input * inputTwo;
                break;
            case "/":
                sum = input/inputTwo;
                break;
            default:
                sum = 42;
                break;
        }
        System.out.println("The sum of " + input + " " + operand + " " + inputTwo + " is " + sum);

    }
    private static String userOperand() {
        Scanner reader = new Scanner(System.in);
        String operand;
        boolean operandTest = true;
        do {
            operand = reader.nextLine();
            if (!operand.equals("+")
                    && !operand.equals("-")
                    && !operand.equals("*")
                    && !operand.equals("/")) {
                System.out.println("INVALID");
                operandTest = false;
            } else {
                operandTest = true;
            }
        } while (!operandTest);
        System.out.println("You chose: '" + operand + "' as your operator.");
        return operand;
    }
    private static int inputGetAndCheck(){
        Scanner reader = new Scanner(System.in);
        int input;
        do {
            input = reader.nextInt();
            if (input < 0 || input > 9) {
                System.out.println("INVALID");
            }
        } while (input < 0 || input > 9);
        System.out.println("You entered: " + input);
        return input;
    }
    private static void LessonFizzBuzzLAB() {

        for (int i = 0; i < 100; ++i) {
            if (i == 0) {
                System.out.println(i);
            } else if (i % 15 == 0){
                System.out.println("FizzBuzz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else {
                System.out.println(i);
            }
        }

    }
    private static void LessonFlowControl() {

        //notes: If-Else
        String name = "Kyle";
        if(name.equals("Kyle"))
            System.out.println("Correct");

    }
    private static void LessonFundamentalsLAB() throws ParseException {
        // 2.   Write code that prompts the user to enter a value from 1-10,
        //      adds 1.75 to the nubmer, then outputs to the screen.
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a value from 1-10: ");
        double input = reader.nextDouble();
        double fromInputString = input;
        if (fromInputString < 1 || fromInputString > 10) {
            System.out.println("Invalid input. Please try again");
            input = reader.nextDouble();
            fromInputString = input;
        }
        double sum = fromInputString + 1.75;
        System.out.println(sum);

        // 3.   Create variables for the eight Java data types and output
        //      the default, max and min values(where applicable).
        System.out.println("byte default value when an uninitialized field: 0");
        System.out.println("Min byte value   = " + Byte.MIN_VALUE);
        System.out.println("Max byte value   = " + Byte.MAX_VALUE);
        System.out.println("short default value when an uninitialized field: 0");
        System.out.println("Min short value  = " + Short.MIN_VALUE);
        System.out.println("Max short value  = " + Short.MAX_VALUE);
        System.out.println("int default value when an uninitialized field: 0");
        System.out.println("Min int value    = " + Integer.MIN_VALUE);
        System.out.println("Max int value    = " + Integer.MAX_VALUE);
        System.out.println("long default value when an uninitialized field: 0L");
        System.out.println("Min long value    = " + Long.MIN_VALUE);
        System.out.println("Max long value    = " + Long.MAX_VALUE);
        System.out.println("float default value when an uninitialized field: 0.0f");
        System.out.println("Min float value  = " + Float.MIN_VALUE);
        System.out.println("Max float value  = " + Float.MAX_VALUE);
        System.out.println("double default value when an uninitialized field: 0.0d");
        System.out.println("Min double value = " + Double.MIN_VALUE);
        System.out.println("Max double value = " + Double.MAX_VALUE);
        System.out.println("char default value when an uninitialized field: '\\u0000'");
        // Max value of char is \uFFFF, or a question mark, min is "". An operand converts to int
        System.out.println("Character Max Value: "+(Character.MAX_VALUE+0));
        System.out.println("Character Max Value: "+(Character.MIN_VALUE+0));
        System.out.println("boolean default value when an uninitialized field: false");
        System.out.println("Boolean data type represents one bit of information, but size is not precicesly defined");

        // 4.   Strings are not part of the eight primitive data types, why not?
        //      which of the data types is a String actually composed of?
        //      Prove this in your code.
        String largeString = "A String is an array of chars, who's use is made easy by the Java String Class.";
        System.out.println(largeString);
        System.out.println("...enumerating String to char array");
        char[] charArray = largeString.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            System.out.printf("Char at index %d is: %c, %n", i, charArray[i]);
        }

        // 5.   Create a List, populate it with values and then iterate thru those values
        //      outputting them to the console.  Why can you not create a List of 'char' or 'int'?

        // A.   Java Collections require Objects, and primitives are not derived from Objects
        List<String> myStringList = new ArrayList<>();
        myStringList.add("String #1");
        myStringList.add("String #2");
        myStringList.add("String #3");
        myStringList.add("String #4");
        myStringList.add("String #5");

        for (String singleString : myStringList) {
            System.out.println(singleString);
        }

        // 6.   Create variables of appropriate data types, to store information regarding
        //      a lottery ticket (i.e. GameName, WinningNumbers, Jackpot, DrawingDate, etc..).
        //      Populate these variables and output them to the console.

        String gameName = "PowerBall!";
        String winningNumbers = "01,23,04,15,20,Sizzler 5";
        int jackpot = 2147483647;


        System.out.println("GameName: " + gameName);
        System.out.println("Winning Numbers: " + winningNumbers);
        System.out.println("Jackpot = " + jackpot);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String drawingDate = "11-01-2016";
        Date date = sdf.parse(drawingDate);
        System.out.println("Drawing date: " + sdf.format(date));

    }
    private static void LessonLists(){
        //notes:    collections / Lists are groups of variables
        List<String> myList = new ArrayList<String>();
        myList.add("1st String");
        myList.add("2nd String");
        myList.add("3rd String");
        myList.add("4th String");
        myList.add("5th String");

        for (String singleString : myList) {

            System.out.println(singleString);

        }

        List<Integer> myIntCollection = new ArrayList<Integer>();

        myIntCollection.add(10);
        myIntCollection.add(20);
        myIntCollection.add(30);
        myIntCollection.add(40);
        myIntCollection.add(50);

        System.out.println(myIntCollection);

        for (int singleInt : myIntCollection) {
            System.out.println(singleInt);
        }

        //notes:    arrays
        String[] myStringArray = new String[5];
        myStringArray[0] = "1st";
        myStringArray[1] = "2nd";
        myStringArray[2] = "3rd";
        myStringArray[3] = "4th";
        myStringArray[4] = "5th";

        System.out.println(myStringArray);

        for (String singleString : myStringArray) {
            System.out.println(singleString);
        }




    }
    private static void LessonStrings() {

        //notes:    strings have a value or not.
        String firstString = "";
        firstString = null;

        if(firstString == null || firstString.isEmpty()){
            System.out.println("String is empty");
        } else {
            System.out.println("String is: " + firstString);
        }

        //notes:    immutable def. unable to be changed
        firstString = "another value";

        for(int x = 0; x <= 100; x++) {
            firstString = "New value: " + Integer.toString(x);
            System.out.println(firstString); //we are creating a new String for every iteration
            //VERY INEFFICIENT
        }
        //notes:    StringBuilder()
        StringBuilder myStringBuilder = new StringBuilder();

        for (int x =0; x <= 100; x++) {
            myStringBuilder.append("new value with string builder: ")
                            .append(Integer.toString(x))
                            .append("\n");
        }
        System.out.println(myStringBuilder);

        //notes:    searching strings (indexOf, lastIndexOf...
        String myName = "Kyle Butz";
        /*

            String can be visualized as an array of characters:
            [0] [1] [2] [3] [4]
            ['K','y','l','e'...]

         */

        int indexOf = myName.indexOf("p");
        System.out.println(indexOf);

        int lastIndexOf = myName.lastIndexOf("t");
        System.out.println(lastIndexOf);

        //notes:    enumerating a string
        String largeString = "This is a longer string than before.";
        for(char c: largeString.toCharArray()) {
            System.out.println(c);
        }

        //notes:    substring(beginning index) or substring(beginning index, end index)
        String partOfLargerString = largeString.substring(10,15);
        System.out.println(partOfLargerString);

    }
    private static void LessonDataTypes() {

        //      primitive data types:
        //      int (number)
        //      float (floating point number or decimal)
        //      double (loarger number)
        //      char (character)
        //      boolean (true/false)

        //      common data types
        boolean myBool = false;
        int myInt = 4;
        String myString = "some words";
        Date myDate = new Date();

        System.out.println(myBool);
        System.out.println(myInt);
        System.out.println(myString);
        System.out.println(myDate);

        //notes:    parsing /converting data types
        String numberString = "341";
        int intFromString = Integer.parseInt(numberString);

        System.out.println(intFromString);

        //notes:    int -> String
        int i = 312;
        String stringFromIn = Integer.toString(i);
        System.out.println(i);

        //notes:    date -> String
        String stringFromDate = myDate.toString();
        System.out.println(stringFromDate);
    }
    private static void LessonVariables() {
        //notes:  declare variable
        String lastName = "Butz", firstName = "Kyle";

        System.out.println(lastName);

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String input = reader.nextLine();
        System.out.println("Hello " + input);

    }

}












