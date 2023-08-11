# Website killer 
Данное приложение предназначено для того, чтобы не играть в игры более 40 минут ~~(мой брат точно не прибьет меня в будущем 2.0)~~

Приложение самостоятельно открывает сайт (здесь Яндекс Игры) в Firefox, а после 40 минут закрывает его и все связанные сайты

Существует в двух версиях:
- на <a href="https://github.com/VeraKasianenko/Website_killer/tree/main/Website_killer_Java">Java</a>
    - поддерживает Windows 7 и выше
    - есть .exe формат и .jar формат
    - может потребоваться установка <a href="https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html">JRE</a> и переменных среды (`JAVA_HOME` - `<путь до jre>`, `PATH` - `<путь до jre>\bin`)
    - необходимо скачать и переместить <a href="https://github.com/mozilla/geckodriver/releases">geckodriver.exe</a> в папку `C:\Program Files\Mozilla Firefox`
- на <a href="https://github.com/VeraKasianenko/Website_killer/tree/main/Website_killer_Python">Python</a>
    - только Windows 10 и выше
