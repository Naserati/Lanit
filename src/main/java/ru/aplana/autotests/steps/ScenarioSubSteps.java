package ru.aplana.autotests.steps;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.reflections.Reflections;
import ru.aplana.autotests.hooks.Hooks;
import ru.aplana.autotests.pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Set;

import static java.lang.Thread.sleep;

public class ScenarioSubSteps extends BaseSteps {

    @Step
    public void stepFieldIsClicked(String fieldName) {
        WebElement element = BasePage.currentPage.getFieldSafe(fieldName);
        BasePage.currentPage.clickField(element);
    }

    @Step
    public void stepFieldIsFilledWithValue(String fieldName, String value) {
        WebElement element = BasePage.currentPage.getFieldSafe(fieldName);
        value = checkVariable(value);
        BasePage.currentPage.sendKeys(element, value);
    }

    @Step
    public void stepFieldContainsValue(String fieldName, String expectedValue) {
        WebElement element = BasePage.currentPage.getFieldSafe(fieldName);
        String actualValue = element.getText().trim();
        expectedValue = checkVariable(expectedValue);
        Assert.assertTrue(String.format("\nТекущее значение поля '%s': \n'%s' \nне содержит ожидаемого значения \n'%s'", fieldName, actualValue, expectedValue), actualValue.contains(expectedValue));
    }

    @Step
    public void stepFieldValueIsSavedInVariable(String fieldName, String variable) {
        WebElement element = BasePage.currentPage.getFieldSafe(fieldName);
        String value = element.getText().trim();
        BaseSteps.variables.put(variable, value);
        System.out.println(String.format("В переменную '%s' сохранено значение '%s'", variable, value));
    }

    @Step
    public void stepMoveCursorToField(String fieldName) {

        Actions action = new Actions(Hooks.getDriver());
        WebElement webElement = BasePage.currentPage.getFieldSafe(fieldName);
        action.moveToElement(webElement).build().perform();
        try {
            sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void stepWaitForSomeTime(String seconds) {
        int sec = Integer.parseInt(seconds);
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void stepPageIsLoaded(String pageName) {
        Reflections reflections = new Reflections("ru.aplana.autotests.pages");
        Set<Class<? extends BasePage>> allPages = reflections.getSubTypesOf(BasePage.class);


        for (Class<? extends BasePage> currentPage : allPages) {
            if (currentPage.getName().endsWith("." + pageName))
                try {
                    BasePage page = currentPage.getConstructor().newInstance();
                    page.initialize(Hooks.getDriver());
                    BasePage.currentPage = page;
                    return;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
