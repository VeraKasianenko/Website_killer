package Website_killer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException, IOException {
        // Путь к файлу date.txt
        String filePath = "date_web.txt";

        // Проверяем, существует ли файл
        File file = new File(filePath);
        boolean fileExists = file.exists();

        // Если файл не существует, создаем его и записываем текущую дату
        if (fileExists) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String storedDate = reader.readLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = dateFormat.format(new Date());
                if (currentDate.equals(storedDate)) {
                    JOptionPane.showMessageDialog(null, "На сегодня доступ запрещен", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);

                }
            }
        }

        try (PrintWriter writer = new PrintWriter(filePath)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date());
            writer.println(currentDate);
        }

        // Время для таймера (20 минут)
        int time = 20 * 60 * 1000;

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
