package ru.lanit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MonitoringPage extends BasePage {

    @FindBy(xpath = "//button[text()='Применить']")
    WebElement Применить;

    @FindBy(xpath = "//select/option[text()='МФЦ/ТОСП']/parent::*")
    WebElement УровеньДетализации;

    @FindBy(xpath = "//tbody//tr//td[3]")
    WebElement Филиалы;
}
