package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormPage extends BasePage {

    @FindBy(xpath = "//div[@class='input-error-message']")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[@class='button' and contains(text(),'Submit')]")
    private WebElement submitQuestionButton;

    @FindBy(xpath = "//textarea[@placeholder='What questions would you like us to answer?']")
    private WebElement textQuestionField;

    @FindBy(xpath = "//input[@placeholder='Name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@placeholder='Email address']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement acceptanceCheckbox;

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public boolean isErrorMessageDisplayed() {
        waitVisibility(errorMessage);
        return errorMessage.isDisplayed();
    }

    public void clickSubmitQuestionButton() {
        waitVisibility(submitQuestionButton);
        submitQuestionButton.click();
    }

    public void enterQuestionText(final String enterQuestionText) {
        waitVisibility(textQuestionField);
        textQuestionField.click();
        textQuestionField.sendKeys(enterQuestionText);
    }

    public void enterName(final String enterName) {
        waitVisibility(nameField);
        nameField.click();
        nameField.sendKeys(enterName);
    }

    public void enterEmail(final String enterEmail) {
        waitVisibility(emailField);
        emailField.click();
        emailField.sendKeys(enterEmail);
    }

    public void clickAcceptanceButton(String selectCheckbox) {
        if (selectCheckbox.equals("select")) {
            acceptanceCheckbox.click();
        }
    }
}
