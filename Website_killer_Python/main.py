import os

from selenium import webdriver
from selenium.webdriver.firefox.service import Service
import time
import tkinter as tk
from tkinter import messagebox

# Время для таймера (40 минут)
timer_delay = 60 * 40

# Целевой домен для проверки URL
target_domain = "https://yandex.ru/games"

# Инициализация веб-драйвера
service = Service(log_path=os.devnull)
driver = webdriver.Firefox(service=service)
driver.get(target_domain)

# Таймер
time.sleep(timer_delay)

# Перебор всех открытых окон браузера и поиск целевого домена, если он найдет, то должен быть закрыт
for handle in driver.window_handles[:]:
    driver.switch_to.window(handle)
    if target_domain in driver.current_url:
        driver.close()

# Создание окна для вывода сообщения
root = tk.Tk()
root.withdraw()
messagebox.showinfo('Сообщение', 'Время работы игры истекло')
exit(0)


