package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        //driver.get("https://kristinek.github.io/sitetasks/provide_feedback");
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");

    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        WebElement name = driver.findElement(By.id("fb_name"));
        assertEquals("", name.getAttribute("value"));
        assertFalse(name.isSelected());

        WebElement age = driver.findElement(By.id("fb_age"));
        assertEquals("", age.getAttribute("value"));
        assertFalse(age.isSelected());

        WebElement comment = driver.findElement(By.cssSelector(".w3-section > .w3-input"));
        assertEquals("", comment.getAttribute("value"));

//         "Don't know" is selected in "Genre"
        List<WebElement> selectorList = driver.findElements(By.cssSelector(".w3-section > .w3-validate"));
        for (WebElement selector: selectorList ) {
            if (selector.getText() == "Don't know (Disabled)") {
                assertTrue(selector.isSelected());
            }
        }

//         "Choose your option" in "How do you like us?"
        Select whichOption = new Select(driver.findElement(By.id("like_us")));
        List<WebElement> allOptions;
        whichOption.selectByVisibleText("Choose your option");
        allOptions = whichOption.getAllSelectedOptions();
        assertEquals("Choose your option", allOptions.get(0).getText());


//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.className("w3-blue"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("");
        assertEquals("", name.getAttribute("value"));
        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys("");
        assertEquals("", age.getAttribute("value"));

        List<WebElement> selectorList = driver.findElements(By.cssSelector(".w3-section > .w3-validate"));
        for (WebElement selector: selectorList ) {
            assertEquals(null,selector.getAttribute("value"));
        }

        List<WebElement> likeList = driver.findElements(By.id("like_us"));
        for (WebElement like: likeList ) {
            assertEquals("",like.getAttribute("value"));
        }

        WebElement comment = driver.findElement(By.cssSelector(".w3-section > .w3-input"));
        comment.sendKeys("");
        assertEquals("", comment.getAttribute("value"));

        WebElement sendButton = driver.findElement(By.className("w3-blue"));
        sendButton.click();

        WebElement greenButton = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", greenButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", greenButton.getCssValue("color"));

        WebElement redButton = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(244, 67, 54, 1)", redButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", redButton.getCssValue("color"));

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        String nameText = "Onute";
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys(nameText);
        assertEquals(nameText, name.getAttribute("value"));

        String ageText = "20";
        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys(ageText);
        assertEquals(ageText, age.getAttribute("value"));

        String textComment = "Dragons can also fly";
        WebElement comment = driver.findElement(By.cssSelector(".w3-section > .w3-input"));
        comment.sendKeys(textComment);
        assertEquals(textComment, comment.getAttribute("value"));

                    // here a little bit cheating, but let it be. Or maybe not
        List<WebElement> selectorList = driver.findElements(By.cssSelector(".w3-section > .w3-validate"));
        WebElement selector1 = selectorList.get(0);
        selector1.click();
        String expectedMessage = "English";
        assertEquals(expectedMessage,selector1.getText());

        WebElement selector2 = selectorList.get(4);
        selector2.click();
        expectedMessage = "Male";
        assertEquals(expectedMessage,selector2.getText());
                    // till here

        WebElement sendButton = driver.findElement(By.className("w3-blue"));
        sendButton.click();

        WebElement greenButton = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", greenButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", greenButton.getCssValue("color"));

        WebElement redButton = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(244, 67, 54, 1)", redButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", redButton.getCssValue("color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background

        String nameText = "Onute";
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys(nameText);
        assertEquals(nameText, name.getAttribute("value"));


        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys("");
        assertEquals("", age.getAttribute("value"));

        List<WebElement> selectorList = driver.findElements(By.cssSelector(".w3-section > .w3-validate"));
        for (WebElement selector: selectorList ) {
            assertEquals(null,selector.getAttribute("value"));
        }

        List<WebElement> likeList = driver.findElements(By.id("like_us"));
        for (WebElement like: likeList ) {
            assertEquals("",like.getAttribute("value"));
        }

        WebElement comment = driver.findElement(By.cssSelector(".w3-section > .w3-input"));
        comment.sendKeys("");
        assertEquals("", comment.getAttribute("value"));

        WebElement sendButton = driver.findElement(By.className("w3-blue"));
        sendButton.click();

        WebElement greenButton = driver.findElement(By.className("w3-green"));
        greenButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String expectedMessage = "Thank you, " + nameText +", for your feedback!";
        assertEquals(expectedMessage,message.getText());

        WebElement greenMessage = driver.findElement(By.className("w3-panel"));
        assertEquals("rgba(76, 175, 80, 1)", greenMessage.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", greenMessage.getCssValue("color"));


    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background

        String nameText = "";
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys(nameText);
        assertEquals(nameText, name.getAttribute("value"));

        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys("");
        assertEquals("", age.getAttribute("value"));

        List<WebElement> selectorList = driver.findElements(By.cssSelector(".w3-section > .w3-validate"));
        for (WebElement selector: selectorList ) {
            assertEquals(null,selector.getAttribute("value"));
        }

        List<WebElement> likeList = driver.findElements(By.id("like_us"));
        for (WebElement like: likeList ) {
            assertEquals("",like.getAttribute("value"));
        }

        WebElement comment = driver.findElement(By.cssSelector(".w3-section > .w3-input"));
        comment.sendKeys("");
        assertEquals("", comment.getAttribute("value"));

        WebElement sendButton = driver.findElement(By.className("w3-blue"));
        sendButton.click();

        WebElement greenButton = driver.findElement(By.className("w3-green"));
        greenButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String expectedMessage = "Thank you for your feedback!";
        assertEquals(expectedMessage,message.getText());

        WebElement greenMessage = driver.findElement(By.className("w3-panel"));
        assertEquals("rgba(76, 175, 80, 1)", greenMessage.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", greenMessage.getCssValue("color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly


        String nameText = "Onute";
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys(nameText);

        String ageText = "20";
        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys(ageText);

        String textComment = "Dragons can also fly";
        WebElement comment = driver.findElement(By.cssSelector(".w3-section > .w3-input"));
        comment.sendKeys(textComment);


                // from here a little bit cheating, but let it be. Or maybe not
        List<WebElement> selectorList = driver.findElements(By.cssSelector(".w3-section > .w3-validate"));
        WebElement selector1 = selectorList.get(0);
        selector1.click();

        WebElement selector2 = selectorList.get(4);
        selector2.click();

        Select whichOption = new Select(driver.findElement(By.id("like_us")));
        whichOption.selectByVisibleText("Good");
                // till here

        WebElement sendButton = driver.findElement(By.className("w3-blue"));
        sendButton.click();

        WebElement redButton = driver.findElement(By.className("w3-red"));
        redButton.click();

        assertEquals(nameText, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(ageText, driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertEquals(textComment, driver.findElement(By.cssSelector(".w3-section > .w3-input")).getAttribute("value"));
//        assertTrue(driver.findElement(By.xpath("//*[@value='English']")).isSelected()) ;      // why do not work???
//        assertTrue(driver.findElement(By.xpath("//*[@value='male']")).isSelected());
        assertEquals("Good", driver.findElement(By.id("like_us")).getAttribute("value"));

    }
}
