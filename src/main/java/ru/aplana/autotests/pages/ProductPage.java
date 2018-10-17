package ru.aplana.autotests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1[@class='title title_size_28 title_bold_yes']")
    WebElement НазваниеТовара;

    @FindBy(xpath = "//input[@id='header-search']")
    WebElement СтрокаПоиска;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement Найти;
}
