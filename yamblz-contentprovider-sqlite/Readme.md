* Придумать структуру БД и оформить в видескриптов CREATE TABLE. С ИНДЕКСАМИ!
* Делаем ContentProvider с БД
* Делаем понятную структуру
* Пишем тесты (1 кейс на каждую функцию
DbBackend минимум)
* Использовать многопоточность (WAL)

---

## Структура БД

__Groups__: (*Id, Name)

__Learners__: (*Id, Name, Phone)

__GroupLearners__: (*GroupId, *LearnerId)