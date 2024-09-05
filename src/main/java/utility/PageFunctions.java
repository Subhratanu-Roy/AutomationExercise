package utility;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import base.Base;

public class PageFunctions extends Base {

	public static void clickOnElement(WebDriver driver, WebElement element) {
		try {
			for (int counter = 1; counter <= 5; counter++) {
				element.click();
				break;
			}
		} catch (Exception e) {
			System.out.println("Enter into js click");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
	}

	public static void sendKeys(WebElement element, String key) {
		element.sendKeys(key);
	}

	public static void explicitWait(WebDriver driver, WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void implicitWait(WebDriver driver, int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public static String getText(WebElement element) {
		return element.getText();
	}

	public static void selectByVisibleText(WebElement ele, String text) {
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(text);
			System.out.println(text + " is selected");
		} catch (Exception e) {
			System.out.println("Unable to select " + text);
		}
	}

	public static boolean checkIfElementDisplayed(WebElement ele) {
		return ele.isDisplayed();
	}

	public static void takeSnapWIthText(WebDriver driver, String testcasename, String msg) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss");
			String val = sdf.format(new Date()) + ".png";
			TakesScreenshot ts = (TakesScreenshot) driver;
			File file = ts.getScreenshotAs(OutputType.FILE);
			String directory = System.getProperty("user.dir") + "\\screenshots\\" + testcasename;
			new File(directory).mkdir();
			String destfile = testcasename + "_" + val;
			String destPath = directory + "\\" + destfile;
			System.out.println("Dest path: " + destPath);
			try {
				FileUtils.copyFile(file, new File(destPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// write text into image file
			final BufferedImage image = ImageIO.read(new File(destPath));
			Graphics g = image.getGraphics();
			g.setFont(g.getFont().deriveFont(25f));
			g.setColor(Color.black);
			g.drawString(msg, 150, 520);
			g.dispose();
			ImageIO.write(image, "png", new File(destPath));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void takeSnap(WebDriver driver, String testcasename) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss");
		String val = sdf.format(new Date()) + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		String directory = System.getProperty("user.dir") + "\\screenshots\\" + testcasename;
		new File(directory).mkdir();
		String destfile = testcasename + "_" + val;
		String destPath = directory + "\\" + destfile;
		System.out.println("Dest path: " + destPath);
		try {
			FileUtils.copyFile(file, new File(destPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void createWordDoc(String testcasename) throws InvalidFormatException, IOException {

		String directory = System.getProperty("user.dir") + "\\screenshots\\" + testcasename;
		try {
			// create blank word document
			XWPFDocument doc = new XWPFDocument();// XWPF = xml word processing format

			// add paragraph to the document, paragraph, alighment, borders
			XWPFParagraph paragraph = doc.createParagraph();

			// add paragraph texts, set fonts, colors, next line
			XWPFRun run = paragraph.createRun();

			CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr(); // get the body of the document
			XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(doc, sectPr);// header and footer object
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(
						directory + "\\" + testcasename + "_" + getCuurTime("ddMMyyyy_hhmmss") + ".docx");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File file = new File(directory + "\\");
			System.out.println(directory);
			File[] files = file.listFiles();
			List<String> flist = new ArrayList<>();
			if (files != null) {
				System.out.println("Files exists");
				for (File f : files) {
					String ext = FileNameUtils.getExtension(f.getName());
					if (ext.equalsIgnoreCase("png")) {
						System.out.println("extention type is png");
						flist.add(f.getName());
						InputStream pic = new FileInputStream(f);
						run.addBreak();
						run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, f.getName(), Units.toEMU(545),
								Units.toEMU(275));
						System.out.println("picture added");
						pic.close();
					}
				}
				doc.write(fos);
				fos.flush();
				fos.close();
				doc.close();
				for (String f : flist) {
					File f1 = new File(directory+"\\"+f);
					f1.delete();
				}

			}
		} catch (Exception e) {
			Log.info(e.toString());
		}

	}

	static String getCuurTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

}
