import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    }

    /* Saves any changes made to profile and updates attributes of Patient to new changes. */
    @FXML
    void saveProfile(ActionEvent event) {

    }

}
