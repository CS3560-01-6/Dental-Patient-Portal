import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateProfilePage {

    @FXML
    private TextField CVVInput;

    @FXML
    private TextField addressLine1Input;

    @FXML
    private TextField addressLine2Input;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cardHolderInput;

    @FXML
    private TextField cardNumberInput;

    @FXML
    private TextField cityInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField expiryDateInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField phoneNumberInput;

    @FXML
    private Button saveButton;

    @FXML
    private TextField stateInput;

    @FXML
    private TextField zipInput;

    /* Closes Update Profile Page window and does not save any changes if made. */
    @FXML
    void cancelProfileChanges(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure you want to cancel before saving changes?");
        alert.setContentText("All unsaved changes will be lost.");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Node node = (Node) event.getSource();
            Stage updateProfileStage = (Stage) node.getScene().getWindow();
            updateProfileStage.close();
        }
    }

    /* Saves any changes made to profile and updates attributes of Patient to new changes. */
    @FXML
    void saveProfile(ActionEvent event) {

    }

}