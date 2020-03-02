package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;

public class Controller implements Initializable {
    @FXML
    JFXButton createdbbutton;
    @FXML
    JFXButton loaddatabutton;
    @FXML
    JFXButton majorF;
    @FXML
    JFXButton ageF;
    @FXML
    JFXButton gpaF;
    @FXML
    JFXListView materialListView;


    final String DB_URL = "jdbc:derby:Student;create=true";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createdbbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    Connection conn = DriverManager.getConnection(DB_URL);
                    Statement stmt = conn.createStatement();

                    try {
                        stmt.execute("CREATE TABLE Student (" +
                                "Name CHAR(25), " +
                                "Id VARCHAR(36), " +
                                "Age INT, " +
                                "Major CHAR(25), " +
                                "GPA DOUBLE)");

                        System.out.println("TABLE CREATED");
                    } catch (Exception ex) {
                        System.out.println("TABLE ALREADY EXISTS, NOT CREATED");
                    }

                    UUID id = UUID.randomUUID();
                    String idString = id.toString();
                    String sql = "INSERT INTO Student VALUES" + "('Tom Cruise', '" + idString + "', 5, 'CHEM', 1.0)";
                    stmt.executeUpdate(sql);

                    UUID id2 = UUID.randomUUID();
                    String idString2 = id2.toString();
                    String sql2 = "INSERT INTO Student VALUES" + "('Walter White', '" + idString2 + "', 8, 'CHEM', 4.0)";
                    stmt.executeUpdate(sql2);

                    UUID id3 = UUID.randomUUID();
                    String idString3 = id3.toString();
                    String sql3 = "INSERT INTO Student VALUES" + "('Jesse Pinkman', '" + idString3 + "', 15, 'ART', 2.0)";
                    stmt.executeUpdate(sql3);


                    System.out.println("TABLE FILLED");

                    stmt.close();
                    conn.close();
                } catch (Exception ex) {
                    var msg = ex.getMessage();
                    System.out.println(msg);
                }


            }
        });
        loaddatabutton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Connection conn = DriverManager.getConnection(DB_URL);

                    Statement stmt = conn.createStatement();

                    String sqlStatement = "SELECT * FROM Student";
                    ResultSet result = stmt.executeQuery(sqlStatement);
                    ObservableList<Student> dbStudentList = FXCollections.observableArrayList();
                    while (result.next())
                    {
                        Student student = new Student();
                        student.id = UUID.fromString(result.getString("Id"));
                        student.name = result.getString("Name");
                        student.age = result.getInt("Age");
                        student.major = result.getString("Major");
                        student.GPA = result.getDouble("GPA");

                        dbStudentList.add(student);
                    }
                    materialListView.setItems(dbStudentList);

                    System.out.println("DATA LOADED");

                    stmt.close();
                    conn.close();
                }
                catch (Exception ex)
                {
                    var msg = ex.getMessage();
                    System.out.println("DATA NOT LOADED");
                    System.out.println(msg);
                }


            }
        });

        majorF.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Connection conn = DriverManager.getConnection(DB_URL);

                    Statement stmt = conn.createStatement();

                    String sqlStatement = "SELECT * FROM Student WHERE Major= 'Chemical Engineering'";
                    ResultSet result = stmt.executeQuery(sqlStatement);
                    ObservableList<Student> dbStudentList = FXCollections.observableArrayList();
                    while (result.next())
                    {
                        Student student = new Student();
                        student.id = UUID.fromString(result.getString("Id"));
                        student.name = result.getString("Name");
                        student.age = result.getInt("Age");
                        student.major = result.getString("Major");
                        student.GPA = result.getDouble("GPA");

                        dbStudentList.add(student);
                    }
                    materialListView.setItems(dbStudentList);

                    System.out.println("DATA LOADED");

                    stmt.close();
                    conn.close();
                }
                catch (Exception ex)
                {
                    var msg = ex.getMessage();
                    System.out.println("DATA NOT LOADED");
                    System.out.println(msg);
                }


            }
        });
        ageF.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {



            }
        });
        gpaF.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {


            }
        });





     /*   UUID id4 = UUID.randomUUID();
        var student4 = new Student("Harry Potter", id4, "17", "Chemistry", "4.0");

        UUID id5 = UUID.randomUUID();
        var student5 = new Student("Ragnar Lothbrok", id5, "19", "Finance", "4.0");

        UUID id6 = UUID.randomUUID();
        var student6 = new Student("Joker", id6, "19", "Art", "4.0");

        UUID id7 = UUID.randomUUID();
        var student7 = new Student("Bruce Wayne", id7, "9", "Chemical Engineering", "1.0");

        UUID id8 = UUID.randomUUID();
        var student8 = new Student("Gandalf", id8, "9", "Art", "4.0");

        UUID id9 = UUID.randomUUID();
        var student9 = new Student("Darth Vader", id9, "26", "Chemical Engineering", "1.0");

        UUID id10 = UUID.randomUUID();
        var student10 = new Student("Pablo Escobar", id10, "24", "Finance", "3.0"); */
    }

}