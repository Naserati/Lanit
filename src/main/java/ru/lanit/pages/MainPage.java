package ru.lanit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Мониторинг показателей работы МФЦ')]")
    WebElement МониторингПоказателейРаботыМФЦ;

}
