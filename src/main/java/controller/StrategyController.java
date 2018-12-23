package controller;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class StrategyController {

    private StrategyList strategyList = new StrategyList();

    @FXML
    private ListView<Strategy> strategyListView = new ListView<>();

    @FXML
    private ListView<IRule> ruleListView = new ListView<>();

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
    private RadioButton valueGreaterRadioButton;

    @FXML
    private RadioButton valueLessRadioButton;

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
    private Button ruleMergeOrButton;

    @FXML
    private Button ruleMergeAndButton;

    @FXML
    private void initialize() {
        ruleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setTextFieldFormatters();
        setButtonActionHandlers();
        configureStrategyListView();
    }

    private void configureStrategyListView() {
        strategyListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (strategyListView.getSelectionModel().getSelectedIndex() != -1) {
                ruleListView.setItems(FXCollections.observableArrayList(
                        strategyListView.getSelectionModel().getSelectedItem().getRules()
                ));
            }
        });

        strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));
    }

    private void setButtonActionHandlers() {
        ruleSaveButton.setOnAction(this::handleRuleSaveButtonClick);
        addStrategyButton.setOnAction(this::handleAddNewStrategyButtonClick);
        ruleDeleteButton.setOnAction(this::handleRuleDeleteButtonClick);
        strategyDeleteButton.setOnAction(this::handleStrategyDeleteButtonClick);
        ruleMergeOrButton.setOnAction(this::handleRuleMergeOrButton);
        ruleMergeAndButton.setOnAction(this::handleRuleMergeAndButton);
    }

    private void setTextFieldFormatters() {
        String decimalFormatRegex = "(^?[1-9]?[0-9](\\.[0-9]*)?$|^$)";
        String decimalWithNegativeFormatRegex = "(^-?[1-9]?[0-9](\\.[0-9]*)?$|^$)";
        String integerFormatRegex = "^[0-9]*";

        ruleTextFieldValue.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().matches(decimalWithNegativeFormatRegex)) {
                return c;
            } else {
                return null;
            }
        }));

        ruleTextFieldDays.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().matches(integerFormatRegex)) {
                return c;
            } else {
                return null;
            }
        }));

        strategyTextFieldValue.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().matches(decimalFormatRegex)) {
                return c;
            } else {
                return null;
            }
        }));
    }

    private void resetRuleViewControls() {
        ruleTextFieldDays.clear();
        ruleTextFieldValue.clear();
        valueGreaterRadioButton.setSelected(true);
        addRuleErrorLabel.setText("");
    }

    private void handleRuleSaveButtonClick(Event event) {
        try {
            int days = Integer.valueOf(ruleTextFieldDays.getText());
            float value = Float.valueOf(ruleTextFieldValue.getText());

            if (valueLessRadioButton.isSelected()) {
                value *= -1;
            }

            if (days == 0 || value == 0) {
                throw new NumberFormatException("Value in text fields cannot be 0");
            }

            Strategy selectedStrategy = strategyListView.getSelectionModel().getSelectedItem();
            selectedStrategy.addRule(new RuleBasic(days, value, valueGreaterRadioButton.isSelected()));

            ruleListView.setItems(FXCollections.observableArrayList(selectedStrategy.getRules()));

            resetRuleViewControls();

        } catch (NullPointerException e) {
            addRuleErrorLabel.setText("Select strategy first to add rule to it");
        } catch (NumberFormatException e) {
            addRuleErrorLabel.setText("Invalid values in text fields");
        }
    }

    private void resetStrategyViewControls() {
        strategyTextFieldValue.clear();
        buyRadioButton.setSelected(true);
        andRadioButton.setSelected(true);
        addStrategyErrorLabel.setText("");
    }

    private void handleAddNewStrategyButtonClick(Event event) {
        try {
            float percentage = Float.valueOf(strategyTextFieldValue.getText());

            if (sellRadioButton.isSelected()) {
                percentage *= -1;
            }

            if (percentage == 0) {
                throw new NumberFormatException("Value in text fields cannot be 0");
            }

            StrategyType strategyType = andRadioButton.isSelected() ? StrategyType.STRATEGY_AND : StrategyType.STRATEGY_OR;
            Strategy strategy = new Strategy(percentage, strategyType);

            strategyList.addStrategy(strategy);
            strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));

            resetStrategyViewControls();

        } catch (NumberFormatException e) {
            addStrategyErrorLabel.setText("Invalid buy/sell value");
        }
    }

    private void handleRuleDeleteButtonClick(Event event) {
        Strategy selectedStrategy = strategyListView.getSelectionModel().getSelectedItem();

        for (IRule rule : ruleListView.getSelectionModel().getSelectedItems()) {
            selectedStrategy.getRules().remove(rule);
        }

        ruleListView.setItems(FXCollections.observableArrayList(selectedStrategy.getRules()));
    }

    private void handleStrategyDeleteButtonClick(Event event) {
        Strategy selectedStrategy = strategyListView.getSelectionModel().getSelectedItem();

        strategyList.removeStrategy(selectedStrategy);

        strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));
        ruleListView.setItems(FXCollections.observableArrayList());
    }

    private void handleRuleMergeOrButton(Event event) {
        if (ruleListView.getSelectionModel().getSelectedItems().size() > 1) {
            addRuleErrorLabel.setText("");
            Strategy selectedStrategy = strategyListView.getSelectionModel().getSelectedItem();

            List<IRule> ruleList = new ArrayList<>();

            for (IRule rule : ruleListView.getSelectionModel().getSelectedItems()) {
                ruleList.add(rule);
            }

            selectedStrategy.addRule(new RuleOr(ruleList));

            ruleListView.setItems(FXCollections.observableArrayList(selectedStrategy.getRules()));
        } else {
            addRuleErrorLabel.setText("Select at least 2 rules to merge!");
        }
    }

    private void handleRuleMergeAndButton(Event event) {
        if (ruleListView.getSelectionModel().getSelectedItems().size() > 1) {
            addRuleErrorLabel.setText("");
            Strategy selectedStrategy = strategyListView.getSelectionModel().getSelectedItem();

            List<IRule> ruleList = new ArrayList<>();

            for (IRule rule : ruleListView.getSelectionModel().getSelectedItems()) {
                ruleList.add(rule);
            }

            selectedStrategy.addRule(new RuleAnd(ruleList));

            ruleListView.setItems(FXCollections.observableArrayList(selectedStrategy.getRules()));
        } else {
            addRuleErrorLabel.setText("Select at least 2 rules to merge!");
        }
    }
}
