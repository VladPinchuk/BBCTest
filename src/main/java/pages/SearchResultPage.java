package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "(//p[@class='ssrcss-6arcww-PromoHeadline e1f5wbog4'])[1]")
    private WebElement nameFirstArticleAfterSearch;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }
    public String getTextFromTitleOfSearchResultFirstArticle() {
        return nameFirstArticleAfterSearch.getText();
    }
}
