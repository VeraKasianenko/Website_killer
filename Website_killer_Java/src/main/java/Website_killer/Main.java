package Website_killer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import javax.swing.*;


public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException {

        // Время для таймера (40 минут)
        int time = 40 * 60 * 1000;

        // Целевой домен, который нужно закрыть
        String targetDomain = "https://yandex.ru/games";

        // Устанавливаем путь к драйверу Firefox
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");

        // Создаем экземпляр веб-драйвера Firefox
        WebDriver driver = new FirefoxDriver();

        // Переходим на целевой домен
        driver.navigate().to(targetDomain);

        // Таймер
        Thread.sleep(time);

        // Проверка всех вкладок, содержат ли они целевой домен, если да, то они будут закрыты
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains(targetDomain)) {
                driver.close();
            }
        }

        // Вывод сообщения об окончании игровой сессии
        UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        JOptionPane.showMessageDialog(null, "Время работы игры истекло", "Сообщение", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}