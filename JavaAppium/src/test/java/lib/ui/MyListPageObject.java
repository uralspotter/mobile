package lib.ui;
import lib.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    /* TEMPLATE METHODS */
    private static String getFolderXPathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getRemoveButtonByTitle(String name_of_folder) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleByTitle(String article_title) {
        return ARTICLE_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATE METHODS */

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSaveArticleByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSaveArticleByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        String article_xpath = getSaveArticleByTitle(article_title);
        this.waitForArticleToAppearByTitle(article_title);

        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if(Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }
}
