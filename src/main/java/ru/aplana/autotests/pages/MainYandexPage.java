package ru.aplana.autotests.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.aplana.autotests.hooks.Hooks;

public class MainYandexPage extends BasePage {

    @FindBy(xpath = "//a[@data-id='market']")
    WebElement Маркет;

}
