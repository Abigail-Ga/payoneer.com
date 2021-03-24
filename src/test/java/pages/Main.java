package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Main extends Base {

	public Main(WebDriver driver) {
		super(driver);
	}

	// cllick on registration button
	public boolean goRegister() {
		click(By.xpath("//a[@title='Register']"));

		return true;
	}

	// select account
	public boolean selectFaild() throws InterruptedException {
		click(By.xpath("//*[@id=\"other\"]/div[1]/div"));
		click(By.xpath("//*[@id=\"other\"]/div[1]/div/div/ul/li[1]"));

		Thread.sleep(2000);
		click(By.xpath("//*[@id=\"other\"]/div[2]/div"));
		click(By.xpath("//*[@id=\"other\"]/div[2]/div/div/ul/li[1]"));

		return true;
	}

	// cllick on second registration
	public boolean goRegister2() {
		click(By.xpath("//*[@id=\"plan-1\"]/div[1]/div[1]/div[1]/a"));
		return true;
	}

	// verify
	public boolean verifySingup() {
		WebElement singu = driver.findElement(By.xpath("//*[@id=\"page-title\"]/div/div/div/div[1]"));

		if (singu.equals("Payoneer Sign Up"))
			return true;
		else
			return false;
	}

	public boolean verfyUrl() {

		String url1 = driver.getCurrentUrl();
		click(By.xpath("//a[@title='Register']"));

		String url2 = driver.getCurrentUrl();

		if (url1.equals(url2))
			return true;
		else
			return false;
	}
}
