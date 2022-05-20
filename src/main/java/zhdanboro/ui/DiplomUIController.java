package zhdanboro.ui;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import zhdanboro.controller.MainController;
import zhdanboro.ui.utils.Utils;

import java.io.File;
import java.util.HashMap;

public class DiplomUIController {
    private Stage primaryStage;

    //FXML analyzer elements block
    @FXML
    private TextField sequenceFileTextField;
    @FXML
    private Button sequenceFileChooseButton;
    @FXML
    private Label errorSequenceFileLabel;

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
    private Label errorSequenceTypeLabel;

    @FXML
    private RadioButton useBaseInner;
    @FXML
    private RadioButton useBaseOther;
    @FXML
    private Label errorBaseTypeLabel;

    @FXML
    private RadioButton fiftyPercentCriteria;
    @FXML
    private RadioButton seventyFivePercentCriteria;
    @FXML
    private RadioButton hundredPercentCriteria;
    @FXML
    private Label errorCriteriaLabel;

    @FXML
    private CheckBox showGrahpCheckBox;
    @FXML
    private CheckBox showEqualCheckBox;
    @FXML
    private CheckBox analyzeSequenceCheckBox;
    @FXML
    private CheckBox convertCheckBox;

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
    private CheckBox singleGraphCheckBox;
    @FXML
    private CheckBox saveToBaseCheckBox;
    @FXML
    private CheckBox analyzeBest;
    @FXML
    private TextField shiftPolynomialTextField;


    @FXML
    private void initialize() {
        //Sequence analyzer tab initializer block
        ToggleGroup typeGroup = new ToggleGroup();
        typeBinary.setToggleGroup(typeGroup);
        typeNormal.setToggleGroup(typeGroup);
        typeOther.setToggleGroup(typeGroup);

        ToggleGroup etalonBaseGroup = new ToggleGroup();
        useBaseInner.setToggleGroup(etalonBaseGroup);
        useBaseOther.setToggleGroup(etalonBaseGroup);

        ToggleGroup criteriaGroup = new ToggleGroup();
        fiftyPercentCriteria.setToggleGroup(criteriaGroup);
        seventyFivePercentCriteria.setToggleGroup(criteriaGroup);
        hundredPercentCriteria.setToggleGroup(criteriaGroup);
    }

    @FXML
    private void chooseSequenceFile() {
        //analyzer block
        File sequenceFile = new FileChooser().showOpenDialog(primaryStage);
        if (sequenceFile != null)
            sequenceFileTextField.setText(sequenceFile.getAbsolutePath());
    }

    @FXML
    private void chooseBaseFile() {
        File baseFile = new FileChooser().showOpenDialog(primaryStage);
        if (baseFile != null)
            baseFileTextField.setText(baseFile.getAbsolutePath());
    }

    @FXML
    private void startGenerate() {
        //collecting arguments
        HashMap<String, String> generateParameters = new HashMap<>();
        generateParameters.put("Polynomial", polynomialInputTextField.getText());
        generateParameters.put("Shifts", shiftPolynomialTextField.getText());
        generateParameters.put("Deviation", deviationTextField.getText());
        generateParameters.put("Length", generationLengthTextField.getText());
        generateParameters.put("Count", generationCountTextField.getText());
        generateParameters.put("Start", startStageTextField.getText());

        if (singleGraphCheckBox.isSelected())
            generateParameters.put("Single", "true");
        else
            generateParameters.put("Single", "false");
        if (saveToBaseCheckBox.isSelected())
            generateParameters.put("Save", "true");
        else
            generateParameters.put("Save", "false");
        if (analyzeBest.isSelected())
            generateParameters.put("Analyze", "true");
        else
            generateParameters.put("Analyze", "false");

        //converting args
        String[] args = Utils.packGenerateInfo(generateParameters);

        MainController controller = new MainController(true, args);
        controller.generate(args);
    }

    @FXML
    private void startAnalyzing() {
        boolean isCorrect;
        boolean correctSequenceFile;
        boolean correctSequenceType;
        boolean correctBaseType;
        boolean correctCriteria;
        //collecting args
        HashMap<String, String> analyzeParameters = new HashMap<>();
        analyzeParameters.put("SequenceFile", sequenceFileTextField.getText());
        if (typeBinary.isSelected()) {
            analyzeParameters.put("SequenceType", "binary");
            correctSequenceType = true;
            errorSequenceTypeLabel.setText("");
        } else if (typeNormal.isSelected()) {
            analyzeParameters.put("SequenceType", "normal");
            correctSequenceType = true;
            errorSequenceTypeLabel.setText("");
        } else if (typeOther.isSelected()) {
            analyzeParameters.put("SequenceType", "other");
            correctSequenceType = true;
            errorSequenceTypeLabel.setText("");
        }
        else {
            errorSequenceTypeLabel.setText("Выберите тип");
            correctSequenceType = false;
        }

        if (useBaseInner.isSelected()) {
            analyzeParameters.put("BaseType", "inner");
            errorBaseTypeLabel.setText("");
            correctBaseType = true;
        } else if (useBaseOther.isSelected()) {
            analyzeParameters.put("BaseType", baseFileTextField.getText());
            errorBaseTypeLabel.setText("");
            correctBaseType = true;
        } else {
            errorBaseTypeLabel.setText("Выберите тип базы");
            correctBaseType = false;
        }

        if (fiftyPercentCriteria.isSelected()) {
            analyzeParameters.put("Criteria", "fifty");
            errorCriteriaLabel.setText("");
            correctCriteria = true;
        } else if (seventyFivePercentCriteria.isSelected()) {
            analyzeParameters.put("Criteria", "seventyFive");
            errorCriteriaLabel.setText("");
            correctCriteria = true;
        } else if (hundredPercentCriteria.isSelected()) {
            analyzeParameters.put("Criteria", "hundred");
            errorCriteriaLabel.setText("");
            correctCriteria = true;
        } else {
            errorCriteriaLabel.setText("Выберите критерий");
            correctCriteria = false;
        }

        analyzeParameters.put("ShowGraph", Boolean.valueOf(showGrahpCheckBox.isSelected()).toString());
        analyzeParameters.put("ShowEqual", Boolean.valueOf(showEqualCheckBox.isSelected()).toString());
        analyzeParameters.put("AnalyzeSequence", Boolean.valueOf(analyzeSequenceCheckBox.isSelected()).toString());
        analyzeParameters.put("Convert", Boolean.valueOf(convertCheckBox.isSelected()).toString());

        String[] args = Utils.packAnalyzeInfo(analyzeParameters);
        if (args.length == 1) {
            errorSequenceFileLabel.setText(args[0]);
            correctSequenceFile = false;
        } else {
            errorSequenceFileLabel.setText("");
            correctSequenceFile = true;
        }
        isCorrect = correctBaseType & correctCriteria & correctSequenceType & correctSequenceFile;

        if (isCorrect) {
            MainController controller = new MainController(false, args);
            controller.analyze();
        }
    }
}