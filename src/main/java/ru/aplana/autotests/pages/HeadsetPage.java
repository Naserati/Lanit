package ru.aplana.autotests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeadsetPage extends BasePage {

    @FindBy(xpath = "//input[@name='Цена от']")
    WebElement ЦенаОт;

    @FindBy(xpath = "//input[@name='Цена до']")
    WebElement ЦенаДо;

    @FindBy(xpath = "//span[text()='Beats']")
    WebElement Beats_Checkbox;

    @FindBy(xpath = "//button[@class='button button_theme_normal button_arrow_down button_size_s select__button i-bem button_js_inited']")
    WebElement ТоваровНаСтранице;

    @FindBy(xpath = "//div[contains(@class,'n-snippet-cell2 i-bem b-zone b-spy-visible n-snippet-cell2_type_product b-spy-visible_js_inited')][1]//div[@class='n-snippet-cell2__title']")
    WebElement ПервыйТовар;

    @FindBy(xpath = "//input[@id='header-search']")
    WebElement СтрокаПоиска;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement Найти;


}
