* Придумать структуру БД и оформить в видескриптов CREATE TABLE. С ИНДЕКСАМИ!
* Делаем ContentProvider с БД
* Делаем понятную структуру
* Пишем тесты (1 кейс на каждую функцию DbBackend минимум)
* Использовать многопоточность (WAL)

---

## Структура БД

__Groups__: (*Id, Name)

__Learners__: (*Id, Name, Phone)

```sql
CREATE TABLE groups (
    id INTEGER PRIMARY KEY,
	name TEXT UNIQUE NOT NULL
);

PRAGMA foreign_keys = ON;

CREATE TABLE learners (
    id INTEGER PRIMARY KEY,
	name TEXT NOT NULL, // возможно, здесь стоит сделать индекс по имени
	phone TEXT,
	group_id INTEGER NOT NULL REFERENCES groups(id)
);

CREATE INDEX idx_learners_name
ON learners(name);
```