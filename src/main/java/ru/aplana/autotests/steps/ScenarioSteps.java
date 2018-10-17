package ru.aplana.autotests.steps;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioSteps extends BaseSteps {

    ScenarioSubSteps subSteps = new ScenarioSubSteps();

    @When("^выполнено нажатие на \"(.+)\"$")
    public void whenFieldIsClicked(String fieldName) {
        subSteps.stepFieldIsClicked(fieldName);
    }


    @When("^наводим курсор на поле \"(.+)\"$")
    public void whenMoveCursorToField(String fieldName) {
        subSteps.stepMoveCursorToField(fieldName);
    }

    @When("^поле \"(.+)\" заполняется значением \"(.+)\"$")
    public void whenFieldIsFilledWithValue(String fieldName, String value) {
        subSteps.stepFieldIsFilledWithValue(fieldName, value);
    }

    @When("^поле \"(.+)\" содержит значение \"(.+)\"$")
    public void whenFieldContainsValue(String fieldName, String expectedValue) {
        subSteps.stepFieldContainsValue(fieldName, expectedValue);
    }

    @When("^значение поля \"(.+)\" сохранено в переменную \"(.+)\"$")
    public void whenFieldValueIsSavedInVariable(String fieldName, String variable){
        subSteps.stepFieldValueIsSavedInVariable(fieldName, variable);
    }

    @Then("^ожидаем \"(.+)\" секунд$")
    public void whenWaitForSomeTime(String seconds){
        subSteps.stepWaitForSomeTime(seconds);
    }

    @Then("^загружена страница \"(.+)\"$")
    public void whenPageIsLoaded(String page) {
        subSteps.stepPageIsLoaded(page);
    }

}

