package ru.lanit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MonitoringPage extends BasePage {

    @FindBy(xpath = "//button[text()='Применить']")
    WebElement Применить;

    @FindBy(xpath = "//select/option[text()='МФЦ/ТОСП']/parent::*")
    WebElement УровеньДетализации;

    @FindBy(xpath = "//tbody//tr[1]//td[3]")
    WebElement ФилиалыМФЦ;

}
