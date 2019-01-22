package controller;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import utils.SimulationUtils;

import java.util.ArrayList;
import java.util.List;

public class StrategyController {
    private LineChartController lineChartController;

    private List<Strategy> strategyList = new ArrayList<>();

    private List<IRule> ruleList = new ArrayList<>();

    private List<Object> simulationResultList = new ArrayList<>();

    @FXML
    private ListView<Strategy> strategyListView = new ListView<>();

    @FXML
    private ListView<IRule> ruleListView = new ListView<>();

    @FXML
    private ListView<Object> simulationResultListView = new ListView<>();

    @FXML
    private Label addStrategyErrorLabel;

    @FXML
    private Label addRuleErrorLabel;

    @FXML
    private Label simulationErrorLabel;

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
    private Button simulateButton;

    @FXML
    private void initialize() {
        ruleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        strategyListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setTextFieldFormatters();
        setButtonActionHandlers();
    }

    private void setButtonActionHandlers() {
        ruleSaveButton.setOnAction(this::handleRuleSaveButtonClick);
        addStrategyButton.setOnAction(this::handleAddNewStrategyButtonClick);
        ruleDeleteButton.setOnAction(this::handleRuleDeleteButtonClick);
        strategyDeleteButton.setOnAction(this::handleStrategyDeleteButtonClick);
        ruleMergeOrButton.setOnAction(this::handleRuleMergeOrButton);
        ruleMergeAndButton.setOnAction(this::handleRuleMergeAndButton);
        simulateButton.setOnAction(this::handleSimulateButton);
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

            if (days == 0) {
                throw new NumberFormatException("Value in text fields cannot be 0");
            }

            ruleList.add(new RuleBasic(days, value, valueGreaterRadioButton.isSelected()));
            ruleListView.setItems(FXCollections.observableArrayList(ruleList));

            resetRuleViewControls();
        } catch (NumberFormatException e) {
            addRuleErrorLabel.setText("Invalid values in text fields");
        }
    }

    private void resetStrategyViewControls() {
        strategyTextFieldValue.clear();
        buyRadioButton.setSelected(true);
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

            if (ruleListView.getSelectionModel().getSelectedItems().size() == 1) {
                strategyList.add(new Strategy(percentage, ruleListView.getSelectionModel().getSelectedItem()));
                strategyListView.setItems(FXCollections.observableArrayList(strategyList));

                resetStrategyViewControls();
            } else {
                addStrategyErrorLabel.setText("Select one rule too add");
            }
        } catch (NumberFormatException e) {
            addStrategyErrorLabel.setText("Invalid buy/sell value");
        }
    }

    private void handleRuleDeleteButtonClick(Event event) {
        ruleList.removeAll(ruleListView.getSelectionModel().getSelectedItems());

        ruleListView.setItems(FXCollections.observableArrayList(ruleList));
    }

    private void handleStrategyDeleteButtonClick(Event event) {
        Strategy selectedStrategy = strategyListView.getSelectionModel().getSelectedItem();

        strategyList.remove(selectedStrategy);

        strategyListView.setItems(FXCollections.observableArrayList(strategyList));
    }

    private void handleRuleMergeOrButton(Event event) {
        if (ruleListView.getSelectionModel().getSelectedItems().size() > 1) {
            addRuleErrorLabel.setText("");

            List<IRule> tmpRuleList = new ArrayList<>();

            tmpRuleList.addAll(ruleListView.getSelectionModel().getSelectedItems());

            ruleList.add(new RuleOr(tmpRuleList));

            ruleListView.setItems(FXCollections.observableArrayList(ruleList));
        } else {
            addRuleErrorLabel.setText("Select at least 2 rules to merge!");
        }
    }

    private void handleRuleMergeAndButton(Event event) {
        if (ruleListView.getSelectionModel().getSelectedItems().size() > 1) {
            addRuleErrorLabel.setText("");

            List<IRule> tmpRuleList = new ArrayList<>();

            tmpRuleList.addAll(ruleListView.getSelectionModel().getSelectedItems());

            ruleList.add(new RuleAnd(tmpRuleList));

            ruleListView.setItems(FXCollections.observableArrayList(ruleList));
        } else {
            addRuleErrorLabel.setText("Select at least 2 rules to merge!");
        }
    }

    private void handleSimulateButton(Event event) {
        if (strategyListView.getSelectionModel().getSelectedItems().size() > 0) {
            simulationErrorLabel.setText("");

            List<Strategy> selectedStrategies = new ArrayList<>();
            selectedStrategies.addAll(strategyListView.getSelectionModel().getSelectedItems());

            Object res = SimulationUtils.simulate(selectedStrategies, lineChartController.getZoomedData());

            simulationResultList.add(res);
            simulationResultListView.setItems(FXCollections.observableArrayList(simulationResultList));

            simulationResultListView.refresh();
        } else {
            simulationErrorLabel.setText("Select at least 1 strategy to simulate!");
        }
    }

    public void setLineChartController(LineChartController lineChartController) {
        this.lineChartController = lineChartController;
    }
}
