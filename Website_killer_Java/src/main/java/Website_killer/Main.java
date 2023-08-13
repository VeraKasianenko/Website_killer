package Website_killer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.swing.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Main {
    public static void main(String[] args) {
        try {
            File lockFile = new File("app.lock");

            // Удаление файла блокировки, если он существует
            if (lockFile.exists()) {
                lockFile.delete();
            }            
            RandomAccessFile randomAccessFile = new RandomAccessFile(lockFile, "rw");
            FileChannel channel = randomAccessFile.getChannel();

            FileLock lock = channel.tryLock();
            if (lock == null) {
                // Если блокировку не удалось получить, приложение уже запущено
                JOptionPane.showMessageDialog(null, "Доступ на сегодня закончился", "Ошибка", JOptionPane.ERROR_MESSAGE);
                channel.close();
                randomAccessFile.close();
                System.exit(1);
            }

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
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            JOptionPane optionPane = new JOptionPane("Время работы игры истекло", JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog(frame, "Сообщение");
            dialog.setIconImage(null);
            dialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "На сегодня доступ запрещен", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
