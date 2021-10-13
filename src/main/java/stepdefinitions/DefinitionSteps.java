package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FormPage;
import pages.HomePage;
import pages.NewsPage;
import pages.SearchResultPage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps {
    private static final long DEFAULT_TIMEOUT = 60;
    WebDriver driver;
    HomePage homePage;
    NewsPage newsPage;
    SearchResultPage searchResultPage;
    FormPage formPage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User clicks 'News' button")
    public void userClicksNewsButton() {
        homePage.clickNewsButton();
    }

    @Then("User checks that name of the headline article is {string}")
    public void userChecksThatNameOfTheHeadlineArticleIsArmyPutOnStandbyToEaseFuelCrisis(final String mainArticleName) {
        newsPage = pageFactoryManager.getNewsPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(newsPage.getTextFromTopNewsHeader(), mainArticleName);
    }

    @Then("User checks that News page contains articles with the following {string}")
    public void userChecksThatNewsPageContainsArticlesWithTheFollowingTitle(final String secondaryArticleTitle) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        newsPage = pageFactoryManager.getNewsPage();
        StringBuffer bufferNewsTitles = new StringBuffer();
        for (WebElement webElement : newsPage.getListOfSecondaryArticleTitles()) {
            String title = webElement.getText();
            bufferNewsTitles = bufferNewsTitles.append(title);
        }
        Assert.assertTrue(bufferNewsTitles.toString().contains(secondaryArticleTitle));
    }

    @And("User enters in the Search bar the stored text of the Category link of the headline article")
    public void userEntersInTheSearchBarTheStoredTextOfTheCategoryLinkOfTheHeadlineArticle() {
        newsPage = pageFactoryManager.getNewsPage();
        newsPage.enterToSearchFieldSavedTextOfCategoryLinkHeadlineArticle();
    }

    @Then("User checks that name of the first article is {string}")
    public void userChecksThatNameOfTheFirstArticleIs(final String firstArticelTitle) {
        searchResultPage = pageFactoryManager.getSearchResultPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals(searchResultPage.getTextFromTitleOfSearchResultFirstArticle(), firstArticelTitle);
    }

    @And("User clicks 'Coronavirus' button")
    public void userClicksCoronavirusButton() {
        newsPage = pageFactoryManager.getNewsPage();
        newsPage.clickCoronavirusButton();
    }

    @And("User clicks 'Your Coronavirus Stories' button")
    public void userClicksYourCoronavirusStoriesButton() {
        newsPage.clickCoronavirusStoriesTab();
    }

    @And("User clicks 'What questions do you have' button")
    public void userClicksWhatQuestionsDoYouHaveButton() {
        newsPage.clickQeustionFormButton();
    }

    @And("User fills the form entering {string} {string} {string} and selecting {string}")
    public void userFillsTheForm(final String questionText, final String name, final String email, final String checkbox) {
        formPage = pageFactoryManager.getFormPage();
        formPage.enterQuestionText(questionText);
        formPage.enterName(name);
        formPage.enterEmail(email);
        formPage.clickAcceptanceButton(checkbox);
    }

    @And("User clicks 'Submit' button")
    public void userClicksSubmitButton() {
        formPage = pageFactoryManager.getFormPage();
        formPage.clickSubmitQuestionButton();
    }

    @Then("User checks that 'Error message' appears")
    public void userChecksThatErrorMessageAppears() {
        Assert.assertTrue(formPage.isErrorMessageDisplayed());
    }


    @After
    public void tearDown() {
        driver.close();
    }
}
