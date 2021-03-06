package Tests;

import Core.BaseSeleniumTests;
import Pages.*;
import Pages.Basic.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasicTest extends BaseSeleniumTests {
    private MainMenu mainMenu;
    private IframePage iframePage;
    private TablePage tablePage;
    private FormPage formPage;
    private AlertsPage alertsPage;
    private WindowsPage windowsPage;

    @Test
    @DisplayName("iframes")
    public void iframe() {
        mainMenu = new MainMenu();
        iframePage = new MainMenu().getBasicMenu().getIframePage();

        iframePage.switchToFrame(1);
        iframePage.setFirstNameInput("Michał");
        iframePage.setSurnameInput("Nowicki");

        iframePage.switchToFrame(0);
        iframePage.switchToFrame(2);
        iframePage.setLoginInput("Mike");
        iframePage.setPasswordInput("secretPassword");
        iframePage.continentsSelectorClick();
        iframePage.setContinent(2);
        iframePage.setYearOfExp();

        iframePage.switchToFrame(0);
    }

    @Test
    public void tables() {
        tablePage = new MainMenu().getBasicMenu().getTablePage();
        tablePage.someMethod();
    }

    @Test
    public void form() throws InterruptedException {
        formPage = new MainMenu().getBasicMenu().getFormPage();
        formPage.setFirstName("Serega");
        formPage.setLastName("Kekman");
        formPage.setEmail("GrayFaceNoSpace@gmail.com");
        formPage.setRandomSex();
        formPage.setAge("30");
        formPage.setRandomYearOfExperience();
        formPage.setRandomProfessions();
        formPage.setRandomContinentsSelect();
        formPage.setSeleniumCommandsSelector();
        formPage.uploadFile("src/Kappa.txt");
        formPage.setAdditionalInformations("You touch my tralala");
        formPage.clickSignInButton();
        Assertions.assertEquals("Form send with success",formPage.getValidatorMessage());
        Thread.sleep(2000);
    }

    @Test
    public void simpleAlertPopUp() {
        alertsPage = new MainMenu().getBasicMenu().getAlertsPage();
        alertsPage.clickSimpleAlertButton();
        alertsPage.waitForAlertToBeReady().accept();
        Assertions.assertEquals("OK button pressed", alertsPage.getSimpleAlertLabel());
    }

    @Test
    public void promptAlertBoxAccept() {
        alertsPage = new MainMenu().getBasicMenu().getAlertsPage();
        alertsPage.clickPromptAlertButton();
        alertsPage.waitForAlertToBeReady().sendKeys("Nothing");
        alertsPage.waitForAlertToBeReady().accept();
        Assertions.assertEquals("Hello Nothing! How are you today?",alertsPage.getPromptLabel());
    }

    @Test
    public void promptAlertBoxDismiss() {
        alertsPage = new MainMenu().getBasicMenu().getAlertsPage();
        alertsPage.clickPromptAlertButton();
        alertsPage.waitForAlertToBeReady().dismiss();
        Assertions.assertEquals("User cancelled the prompt.",alertsPage.getPromptLabel());
    }

    @Test
    public void confirmPopUpAccept() {
        alertsPage = new MainMenu().getBasicMenu().getAlertsPage();
        alertsPage.clickConfirmPopUpButton();
        alertsPage.waitForAlertToBeReady().accept();
        Assertions.assertEquals("You pressed OK!",alertsPage.getConfirmLabel());
    }

    @Test
    public void confirmPopUpDismiss() {
        alertsPage = new MainMenu().getBasicMenu().getAlertsPage();
        alertsPage.clickConfirmPopUpButton();
        alertsPage.waitForAlertToBeReady().dismiss();
        Assertions.assertEquals("You pressed Cancel!",alertsPage.getConfirmLabel());
    }

    @Test
    public void delayedAlert() {
        alertsPage = new MainMenu().getBasicMenu().getAlertsPage();
        alertsPage.clickDelayedAlert();
        alertsPage.waitForAlertToBeReady().accept();
        Assertions.assertEquals("OK button pressed", alertsPage.getDelayedAlertLabel());
    }

    @Test
    public void newWindow() {
        windowsPage = new MainMenu().getBasicMenu().getWindowsPage();
        windowsPage.clickWindowButton();
        windowsPage.switchToNewWindowOrTab();
        Assertions.assertEquals("http://automation-practice.emilos.pl/",windowsPage.getCurrentUrl());
    }

    @Test
    public void newMessageWindow() {
        windowsPage = new MainMenu().getBasicMenu().getWindowsPage();
        windowsPage.clickMessageButton();
        windowsPage.switchToNewWindowOrTab();
        Assertions.assertEquals("Knowledge increases by sharing but not by saving. Please share this website " +
                "with your friends and in your organization.",windowsPage.getBody());
        System.out.println(windowsPage.getBody());
    }

    @Test
    public void newTab() {
        windowsPage = new MainMenu().getBasicMenu().getWindowsPage();
        windowsPage.clickTabButton();
        windowsPage.switchToNewWindowOrTab();
    }
}
