package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Rule;
import model.Strategy;
import model.StrategyList;

public class StrategyController {
    private StrategyList strategyList = new StrategyList();

    @FXML
    private Button strategyDeleteButton;

    @FXML
    private Button ruleDeleteButton;

    @FXML
    private RadioButton valueRiseRadioButton;

    @FXML
    private RadioButton valueFallRadioButton;

    @FXML
    private RadioButton buyRadioButton;

    @FXML
    private RadioButton sellRadioButton;

    @FXML
    private RadioButton andRadioButton;

    @FXML
    private RadioButton orRadioButton;

    @FXML
    private Label addStrategyErrorLabel;

    @FXML
    private Label addRuleErrorLabel;

    @FXML
    private ListView<String> strategyListView = new ListView<>();

    @FXML
    private ListView<String> ruleListView = new ListView<>();

    @FXML
    private TextField ruleTextFieldDays = new TextField();

    @FXML
    private TextField ruleTextFieldValue = new TextField();

    @FXML
    private TextField strategyTextFieldValue = new TextField();

    @FXML
    private Button ruleSaveButton = new Button();

    @FXML
    private Button addStrategyButton = new Button();

    @FXML
    private void initialize() {

        ruleTextFieldValue.setTextFormatter( new TextFormatter<>(c ->
        {
            if (c.getControlNewText().matches("(^[1-9]?[0-9](\\.[0-9]*)?$|^$)")){
                return c;
            }else{
                return null;
            }
        }));

        ruleTextFieldDays.setTextFormatter(new TextFormatter<>(c ->{
            if (c.getControlNewText().matches("^[0-9]*")){
                return c;
            } else {
                return null;
            }
        }));

        strategyTextFieldValue.setTextFormatter(new TextFormatter<>(c ->{
            if (c.getControlNewText().matches("(^[1-9]?[0-9](\\.[0-9]*)?$|^$)")){
                return c;
            } else {
                return null;
            }
        }));

        ruleSaveButton.setOnAction(this::handleRuleSaveButtonClick);
        addStrategyButton.setOnAction(this::handleAddNewStrategyButtonClick);
        ruleDeleteButton.setOnAction(this::handleRuleDeleteButtonClick);
        strategyDeleteButton.setOnAction(this::handleStrategyDeleteButtonClick);

        strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));

        strategyListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (strategyListView.getSelectionModel().getSelectedIndex() != -1)
                    ruleListView.setItems(FXCollections.observableArrayList(
                            strategyList.getStrategy(strategyListView.getSelectionModel().getSelectedIndex()).getRules()
                    ));
            }
        });
    }

    private void handleRuleSaveButtonClick(Event event) {
        try {

            int days = Integer.valueOf(ruleTextFieldDays.getText());
            float value = Float.valueOf(ruleTextFieldValue.getText());
            if (valueFallRadioButton.isSelected()) value *= -1;

            strategyList.getStrategy(strategyListView.getSelectionModel().getSelectedIndex())
                    .addRule(new Rule(days, value));

            ruleListView.setItems(FXCollections.observableArrayList(
                    strategyList.getStrategy(strategyListView.getSelectionModel().getSelectedIndex()).getRules()
            ));

            ruleTextFieldDays.clear();
            ruleTextFieldValue.clear();
            valueRiseRadioButton.setSelected(true);
            addRuleErrorLabel.setText("");
        } catch (ArrayIndexOutOfBoundsException e){
            addRuleErrorLabel.setText("Select strategy first to add rule to it");
        } catch (NumberFormatException e){
            addRuleErrorLabel.setText("Both text fields cannot be null");
        }
    }

    private void handleAddNewStrategyButtonClick(Event event) {
        try {
            float percentage = Float.valueOf(strategyTextFieldValue.getText());
            if (sellRadioButton.isSelected()) percentage *= -1;

            strategyList.addStrategy(new Strategy(percentage, andRadioButton.isSelected()));
            strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));

            strategyTextFieldValue.clear();
            buyRadioButton.setSelected(true);
            andRadioButton.setSelected(true);
            addStrategyErrorLabel.setText("");
        } catch (NumberFormatException e){
            addStrategyErrorLabel.setText("Invalid buy/sell value");
        }
    }

    private void handleRuleDeleteButtonClick(Event event){

    }

    private void handleStrategyDeleteButtonClick(Event event){

    }

}
