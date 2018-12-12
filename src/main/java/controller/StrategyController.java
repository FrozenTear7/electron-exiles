package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Rule;
import model.Strategy;
import model.StrategyList;

public class StrategyController {
    private StrategyList strategyList = new StrategyList();

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
    private CheckBox andCheckBox = new CheckBox();

    @FXML
    private void initialize() {
        ruleSaveButton.setOnAction(this::handleRuleSaveButtonClick);
        addStrategyButton.setOnAction(this::addNewStrategyButton);

        strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));

        strategyListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ruleListView.setItems(FXCollections.observableArrayList(
                        strategyList.getStrategy(strategyListView.getSelectionModel().getSelectedIndex()).getRules()
                ));
            }
        });
    }

    private void handleRuleSaveButtonClick(Event event) {
        strategyList.getStrategy(strategyListView.getSelectionModel().getSelectedIndex())
                .addRule(new Rule(Integer.valueOf(ruleTextFieldDays.getText()), Float.valueOf(ruleTextFieldValue.getText())));

        ruleListView.setItems(FXCollections.observableArrayList(
                strategyList.getStrategy(strategyListView.getSelectionModel().getSelectedIndex()).getRules()
        ));

        ruleTextFieldDays.clear();
        ruleTextFieldValue.clear();
    }

    private void addNewStrategyButton(Event event) {
        strategyList.addStrategy(new Strategy(Float.valueOf(strategyTextFieldValue.getText()), andCheckBox.isSelected()));
        strategyListView.setItems(FXCollections.observableArrayList(strategyList.getStrategyList()));

        strategyTextFieldValue.clear();
        andCheckBox.setSelected(false);
    }
}
