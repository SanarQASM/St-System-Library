package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class deleteStageController {
    private static bookController bC;
    private static Stage tempStage;
    public deleteStageController(bookController bC, Stage tempStage){
        deleteStageController.bC=bC;
        deleteStageController.tempStage=tempStage;
    }
    public deleteStageController(){}

    @FXML
    private Label message;

    @FXML
    void cancleButton(ActionEvent event) {
        tempStage.close();
    }

    @FXML
    void deleteButton(ActionEvent event) {
        bC.findParentId();
    }
}
