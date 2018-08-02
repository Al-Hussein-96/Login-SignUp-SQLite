/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Al-Hussein
 */
public class ViewDataController implements Initializable {

    @FXML
    private TableView<TableUser> tabelview;

    @FXML
    private TableColumn<TableUser, String> c1;

    @FXML
    private TableColumn<TableUser, String> c2;

    @FXML
    private TableColumn<TableUser, String> c3;

    List<User> users;

    public ViewDataController(List<User> users) {
        this.users = users;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<TableUser> list;
        TableUser[] st = new TableUser[users.size()];
        int idx = 0;
        for (User temp : users) {
            st[idx++] = new TableUser(temp.getId(),temp.getName(),temp.getPassword());
        }
        list = FXCollections.observableArrayList(st);
        c1.setCellValueFactory(new PropertyValueFactory<TableUser, String>("Id"));
        c2.setCellValueFactory(new PropertyValueFactory<TableUser, String>("UserName"));
        c3.setCellValueFactory(new PropertyValueFactory<TableUser, String>("PassWord"));
        tabelview.setItems(list);
    }

    public class TableUser {

        public StringProperty Id;
        public StringProperty UserName;
        public StringProperty PassWord;

        public TableUser(int Id, String UserName, String PassWord) {
            this.Id = new SimpleStringProperty(String.valueOf(Id));
            this.UserName = new SimpleStringProperty(UserName);
            this.PassWord = new SimpleStringProperty(PassWord);
        }

        public String getId() {
            return Id.get();
        }

        public String getUserName() {
            return UserName.get();
        }

        public String getPassWord() {
            return PassWord.get();
        }

    }

}
