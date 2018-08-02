/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Al-Hussein
 */
public class FXMLDocumentController implements Initializable {

    public LoginModel loginModel = new LoginModel();
    public ViewDataBase viewDataBase = new ViewDataBase();

    @FXML
    private Label isConnected;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField password;

    @FXML
    void login(ActionEvent event) {
        try {
            if (loginModel.isLogin(user.getText(), password.getText())) {
                isConnected.setText("Done Login");
            } else {
                isConnected.setText("Useranme Or Password not Correctly");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void viewDatabase(ActionEvent event) {
        try {
            List<User> list = viewDataBase.getDataBase();
            System.out.println(list);
            if (list == null) {
                return;
            }

            ViewDataController viewDataController = new ViewDataController(list);
            Stage stage = new Stage();
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("ViewData.fxml"));
            fXMLLoader.setController(viewDataController);

            AnchorPane root = (AnchorPane) fXMLLoader.load();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.showAndWait();

        } catch (SQLException | IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Not Connected");

        }
    }

}
