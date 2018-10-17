package ru.aplana.autotests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarketPage extends BasePage {

    @FindBy(xpath = "//li[@data-department='Электроника']")
    WebElement Электроника;

    @FindBy(xpath = "//div[@class='topmenu__sublist']/a[text()='Телевизоры']")
    WebElement Телевизоры;

    @FindBy(xpath = "//div[@class='topmenu__sublist']/a[text()='Наушники']")
    WebElement Наушники;

}
