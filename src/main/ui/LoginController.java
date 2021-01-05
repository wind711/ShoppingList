package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {


    @FXML
    public TextField username;

    @FXML
    public Button loginButton;

    @FXML
    public Button newUserButton;

    public static String usernameString;

    @FXML
    public void newUserButtonClicked()  {
        try {
            openMenu(newUserButton);
            usernameString = username.getText();
            System.out.println(username.getText() + " created");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void loginButtonClicked() {
        try {
            StartList.listManager.load(username.getText());
            openMenu(loginButton);
            usernameString = username.getText();
            System.out.println(username.getText() + " logged in");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Username Not Found");
        }
    }

    private void openMenu(Button button) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu.fxml"));
        Parent parent = loader.load();
        MenuController controller = loader.getController();
        Scene scene = new Scene(parent);
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
