import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebScrape {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.flashflashrevolution.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public Document[] test(String username, String password,
			boolean includeTokens, String compare) throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.name("vb_login_username")).clear();
		driver.findElement(By.name("vb_login_username")).sendKeys(username);
		driver.findElement(By.name("vb_login_password")).clear();
		driver.findElement(By.name("vb_login_password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("header_logo")));

		Document[] source = new Document[2];

		if (compare != null) {
			driver.get(baseUrl + "levelrank.php?sub=" + compare);
			source[0] = Jsoup.parse(driver.getPageSource());

			if (includeTokens) {
				driver.get(baseUrl + "levelrank_special.php?sub=" + username);
				source[1] = Jsoup.parse(driver.getPageSource());
			}
		} else {
			driver.get(baseUrl + "levelrank.php?sub=" + username);
			source[0] = Jsoup.parse(driver.getPageSource());

			if (includeTokens) {
				driver.get(baseUrl + "levelrank_special.php?sub=" + username);
				source[1] = Jsoup.parse(driver.getPageSource());
			}
		}

		return source;
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
