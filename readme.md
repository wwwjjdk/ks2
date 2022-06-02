# Курсовая работа № 2
### ServerOk:
Сервер осуществляет расчеты объемов фигур, предназначен на обработку запросов от трех пользователей сразу, а также логирует все действия в файл log.log.
Ключевые методы:
* searchCylinderArea;
* searchCubeArea;
* searchConeArea;
* doSplit.
#### Все ключевые методы сервера покрыты тестами.
### Client:
Клиент подключается к выбранному порту, после чего отправляется запрос серверу на нахождения объема выбранной фигуры.
### DoingAll:
Абстрактный класс методы которого используются в классе Client и ServerOk.
Методы абстрактного класса:
* readReport -> считывает инструкцию с файла setting.txt;
* choice -> подключение к порту(сервера и клиента).
###Выполнены следующие обязательные требования к реализации проекта:
* Сервер должен уметь одновременно ожидать новых пользователей и обрабатывать поступающие сообщения от пользователей;
* Использован сборщик пакетов gradle/maven;
* Код размещен на github;
* Код покрыт unit-тестами.