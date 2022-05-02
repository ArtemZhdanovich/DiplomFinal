package zhdanboro.ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import zhdanboro.maincontroller.MainController;
import zhdanboro.ui.utils.GenerationUtils;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DiplomUIController {

    //analyzer block
    private Desktop desktop;
    private File sequenceFile = null;
    private File baseFile = null;
    private Stage primaryStage;
    private HashMap<String, String> generateParameters;

    //generation block


    //FXML analyzer elements block
    @FXML
    public TextField sequenceFileTextField;
    @FXML
    public Button sequenceFileChooseButton;

    @FXML
    private TextField baseFileTextField;
    @FXML
    private Button baseFileChooseButton;

    @FXML
    private RadioButton typeBinary;
    @FXML
    private RadioButton typeNormal;
    @FXML
    private RadioButton typeOther;

    @FXML
    private RadioButton useBaseInner;
    @FXML
    private RadioButton useBaseOther;

    @FXML
    private Button buttonStartGenerate;

    //FXML generation block
    @FXML
    private TextField polynomialInputTextField;
    @FXML
    private TextField generationLengthTextField;
    @FXML
    private TextField generationCountTextField;
    @FXML
    private TextField deviationTextField;
    @FXML
    private TextField startStageTextField;
    @FXML
    private CheckBox saveToBaseCheckBox;
    @FXML
    private CheckBox analyzeBest;
    @FXML
    public ComboBox<String> chooseGeneratorComboBox;


    @FXML
    private void initialize() {
        desktop = Desktop.getDesktop();

        //Sequence analyzer tab initializer block
        ToggleGroup typeGroup = new ToggleGroup();
        typeBinary.setToggleGroup(typeGroup);
        typeNormal.setToggleGroup(typeGroup);
        typeOther.setToggleGroup(typeGroup);

        ToggleGroup etalonBaseGroup = new ToggleGroup();
        useBaseInner.setToggleGroup(etalonBaseGroup);
        useBaseOther.setToggleGroup(etalonBaseGroup);

        //Sequence generation initializer block
        ObservableList<String> types = FXCollections.observableArrayList("LFSR", "AES256", "SHA1", "Other");
        chooseGeneratorComboBox.setItems(types);
        chooseGeneratorComboBox.setValue("LFSR");
    }

    @FXML
    private void chooseSequenceFile() {
        FileChooser fileChooser = new FileChooser();
        sequenceFileTextField.clear();

        sequenceFile = fileChooser.showOpenDialog(primaryStage);
        if(sequenceFile != null) {
            try {
                this.desktop.open(sequenceFile);
                sequenceFileTextField.setText(sequenceFile.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void chooseBaseFile() {
        FileChooser fileChooser = new FileChooser();
        baseFileTextField.clear();

        baseFile = fileChooser.showOpenDialog(primaryStage);
        if(baseFile != null) {
            try {
                this.desktop.open(baseFile);
                baseFileTextField.setText(baseFile.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void startGenerate() {
        generateParameters = new HashMap<>();
        generateParameters.put("Polynomial", polynomialInputTextField.getText());
        generateParameters.put("Deviation", deviationTextField.getText());
        generateParameters.put("Length", generationLengthTextField.getText());
        generateParameters.put("Count", generationCountTextField.getText());
        generateParameters.put("Start", startStageTextField.getText());

        if (saveToBaseCheckBox.isSelected())
            generateParameters.put("Save", "true");
        else
            generateParameters.put("Save", "false");
        if (analyzeBest.isSelected())
            generateParameters.put("Analyze", "true");
        else
            generateParameters.put("Analyze", "false");

        String[] args = GenerationUtils.packInfo(generateParameters);

        MainController.generate(args);
    }
}