package controller;

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
    private ListView<Strategy> strategyListView = new ListView<>();

    @FXML
    private ListView<Rule> ruleListView = new ListView<>();

    @FXML
    private Label addStrategyErrorLabel;

    @FXML
    private Label addRuleErrorLabel;

    @FXML
    private TextField ruleTextFieldDays = new TextField();

    @FXML
    private TextField ruleTextFieldValue = new TextField();

    @FXML
    private TextField strategyTextFieldValue = new TextField();

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
    private Button ruleSaveButton = new Button();

    @FXML
    private Button addStrategyButton = new Button();

    @FXML
    private Button strategyDeleteButton;

    @FXML
    private Button ruleDeleteButton;

    @FXML
    private void initialize() {

        setTextFieldFormatters();
        setButtonActionHandlers();
        configureStrategyListView();

    }

    private void configureStrategyListView() {

        strategyListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (strategyListView.getSelectionModel().getSelectedIndex() != -1)
                ruleListView.setItems(FXCollections.observableArrayList(
                        strategyList.getStrategy(strategyListView.getSelectionModel().getSelectedIndex()).getRules()
                ));

        });

        strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));

    }

    private void setButtonActionHandlers(){

        ruleSaveButton.setOnAction(this::handleRuleSaveButtonClick);
        addStrategyButton.setOnAction(this::handleAddNewStrategyButtonClick);
        ruleDeleteButton.setOnAction(this::handleRuleDeleteButtonClick);
        strategyDeleteButton.setOnAction(this::handleStrategyDeleteButtonClick);

    }

    private void setTextFieldFormatters(){

        String decimalFormatRegex = "(^[1-9]?[0-9](\\.[0-9]*)?$|^$)";
        String integerFormatRegex = "^[0-9]*";

        ruleTextFieldValue.setTextFormatter( new TextFormatter<>(c ->
        {
            if (c.getControlNewText().matches(decimalFormatRegex)){
                return c;
            } else {
                return null;
            }
        }));

        ruleTextFieldDays.setTextFormatter(new TextFormatter<>(c ->{
            if (c.getControlNewText().matches(integerFormatRegex)){
                return c;
            } else {
                return null;
            }
        }));

        strategyTextFieldValue.setTextFormatter(new TextFormatter<>(c ->{
            if (c.getControlNewText().matches(decimalFormatRegex)){
                return c;
            } else {
                return null;
            }
        }));

    }

    private void resetRuleView(){

        ruleTextFieldDays.clear();
        ruleTextFieldValue.clear();
        valueRiseRadioButton.setSelected(true);
        addRuleErrorLabel.setText("");

    }

    private void handleRuleSaveButtonClick(Event event) {
        try {

            int days = Integer.valueOf(ruleTextFieldDays.getText());

            float value = Float.valueOf(ruleTextFieldValue.getText());
            if (valueFallRadioButton.isSelected()) value *= -1;

            Strategy selectedStrategy = strategyList.getStrategy(strategyListView.getSelectionModel().getSelectedIndex());
            selectedStrategy.addRule(new Rule(days, value));

            ruleListView.setItems(FXCollections.observableArrayList(selectedStrategy.getRules()));

            resetRuleView();

        } catch (ArrayIndexOutOfBoundsException e){

            addRuleErrorLabel.setText("Select strategy first to add rule to it");

        } catch (NumberFormatException e){

            addRuleErrorLabel.setText("Both text fields cannot be null");

        }
    }

    private void resetStrategyView(){

        strategyTextFieldValue.clear();
        buyRadioButton.setSelected(true);
        andRadioButton.setSelected(true);
        addStrategyErrorLabel.setText("");

    }

    private void handleAddNewStrategyButtonClick(Event event) {
        try {

            float percentage = Float.valueOf(strategyTextFieldValue.getText());
            if (sellRadioButton.isSelected()) percentage *= -1;

            strategyList.addStrategy(new Strategy(percentage, andRadioButton.isSelected()));
            strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));

            resetStrategyView();

        } catch (NumberFormatException e){

            addStrategyErrorLabel.setText("Invalid buy/sell value");

        }
    }

    private void handleRuleDeleteButtonClick(Event event){

        Strategy selectedStrategy = strategyListView.getSelectionModel().getSelectedItem();
        Rule selectedRule = ruleListView.getSelectionModel().getSelectedItem();

        selectedStrategy.getRules().remove(selectedRule);

        ruleListView.setItems(FXCollections.observableArrayList(selectedStrategy.getRules()));

    }

    private void handleStrategyDeleteButtonClick(Event event){

        Strategy selectedStrategy = strategyListView.getSelectionModel().getSelectedItem();

        strategyList.removeStrategy(selectedStrategy);

        strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));
        ruleListView.setItems(FXCollections.observableArrayList());

    }

}
