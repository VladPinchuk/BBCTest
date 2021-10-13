package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NewsPage extends BasePage {

    @FindBy(xpath = "(//h3[@class='gs-c-promo-heading__title gel-paragon-bold nw-o-link-split__text'])[1]")
    private WebElement topNewsHeader;

    @FindBy(xpath = "//div[@id='news-top-stories-container']/descendant::div[contains(@class,'gs-u-mt@m')]/descendant::h3[contains(@class,'nw-o-link-split__text')]")
    private List<WebElement> listOfSecondaryArticleTitles;

    @FindBy(xpath = "//div[contains(@class,'gs-c-promo-body gs-u-display-none gs-u-display-inline-block@')]/descendant::a[contains(@class,'gs-c-section-link')]")
    private WebElement mainNewsCategory;

    @FindBy(xpath = "//input[@id='orb-search-q']")
    private WebElement searchFieldAtNewsPage;

    @FindBy(xpath = "//nav[@class='nw-c-nav__wide']/descendant::a[@href='/news/coronavirus']")
    private WebElement coronavirusTab;

    @FindBy(xpath = "//nav[@class='nw-c-nav__wide-secondary']/descendant::span[text()='Your Coronavirus Stories']")
    private WebElement yourCoronavirusStoriesTab;

    @FindBy(xpath = "//a[contains(@class,'gs-c-promo-heading') and @href='/news/52143212']")
    private WebElement questionFormButton;


    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public String getTextFromTopNewsHeader() {
        return topNewsHeader.getText();
    }

    public List<WebElement> getListOfSecondaryArticleTitles() {
        waitForPageLoadComplete(10);
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfSecondaryArticleTitles));
        return listOfSecondaryArticleTitles;
    }

    public void enterToSearchFieldSavedTextOfCategoryLinkHeadlineArticle() {
        wait.until(ExpectedConditions.visibilityOf(mainNewsCategory));
        String textOfCategoryLink = mainNewsCategory.getText();
        wait.until(ExpectedConditions.visibilityOf(searchFieldAtNewsPage));
        searchFieldAtNewsPage.sendKeys(textOfCategoryLink);
        searchFieldAtNewsPage.sendKeys(Keys.ENTER);
    }
    public void clickCoronavirusButton(){
        waitVisibility(coronavirusTab);
        coronavirusTab.click();
    }
    public void clickCoronavirusStoriesTab(){
        waitVisibility(yourCoronavirusStoriesTab);
        yourCoronavirusStoriesTab.click();
    }
    public void clickQeustionFormButton(){
        waitVisibility(questionFormButton);
        questionFormButton.click();
    }
}


