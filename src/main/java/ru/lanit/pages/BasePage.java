package ru.lanit.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.lanit.hooks.Hooks;

import java.util.Set;

public abstract class BasePage {

    public static BasePage currentPage;

    static WebDriver driver;
    protected FluentWait<WebDriver> wait;

    public void compareValue(String currentValue, String expectedValue) {
        if (!currentValue.equals(expectedValue)) {
            Assert.fail("Ошибка сравнения; текущее значение - " + currentValue + ", ожидаемое значение - " + expectedValue);
        }
    }

    public static void waitPageIsLoaded() {
        new WebDriverWait(driver, 60).until(
                (WebDriver d) -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    public void waitFieldIsDisplayed(By locator) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until((WebDriver d) -> d.findElement(locator).isDisplayed());
            return;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        Assert.fail("Поле не отображено: " + locator);
    }

    public void waitFieldIsDisplayed(WebElement element) {
        try {
            wait = new WebDriverWait(driver, 10).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            return;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        Assert.fail("Поле не отображено: " + element);
    }

    public boolean isElementPresent(By locator) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isElementPresent(WebElement element) {
        try {
            wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clickField(By locator) {
        if (!isElementPresent(locator)) {
            Assert.fail("Невозможно нажать на поле");
        }
        waitFieldIsDisplayed(locator);
        driver.findElement(locator).click();
    }

    public void clickField(WebElement element) {

        if (!isElementPresent(element)) {
            Assert.fail("Невозможно нажать на поле: " + element);
        }
        element.click();
    }

    public void selectField(String fieldName) {
        WebElement element = getFieldSafe(fieldName);
        clickField(element);
    }

    public void clickFieldAndChangeWindow(WebElement element) {
        Hooks.setOldWindowHandle(driver.getWindowHandle());
        Set<String> winSetOld = driver.getWindowHandles();
        clickField(element);
        changeWindow(winSetOld);
    }

    public void changeWindow(Set<String> winset) {
        //Переключение на новое окно(вкладку)
        String newWindow = wait.until(thereIsWindowOtherThan(winset));
        driver.switchTo().window(newWindow);
    }


    public void sendKeys(By locator, String value) {
        if (isElementPresent(locator)) {
            waitFieldIsDisplayed(locator);
            driver.findElement(locator).click();
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(value);
        } else Assert.fail("Невозможно ввести значение в поле");
    }

    public void sendKeys(WebElement element, String value) {
        if (isElementPresent(element)) {
            waitFieldIsDisplayed(element);
            element.click();
            element.clear();
            element.sendKeys(value);
        } else Assert.fail("Невозможно ввести значение в поле: " + element);
    }

    public String getText(WebElement element) {
        return element.getAttribute("value");
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> winSet) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> curWinSet = driver.getWindowHandles();
                curWinSet.removeAll(winSet);
                return curWinSet.size() > 0 ? curWinSet.iterator().next() : null;
            }
        };
    }

    public WebElement getFieldSafe(String fieldName) {

        Object object = null;
        try {
            object = getField(fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (object instanceof WebElement) {
            if (isElementPresent((WebElement) object)) {
                return (WebElement) object;
            } else {

                Assert.fail("Поле не отображено: " + fieldName);
                return null;// на самом деле досюда не дойдет
            }
        } else {

            throw new RuntimeException();
        }
    }

    public Object getField(String fieldName) throws Exception {

        return (getClass().getDeclaredField(fieldName).get(this));
    }

    public void initialize(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


}
