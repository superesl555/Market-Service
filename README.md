Проект реализует микросервисную архитектуру для управления заказами и платежами.
Система включает два ключевых сервиса:

Orders Service — создание и просмотр заказов.

Payments Service — создание счета, пополнение и автоматическая обработка оплаты заказов.

Микросервисы взаимодействуют через Kafka, используя надёжные шаблоны передачи сообщений (Transactional Outbox / Inbox), обеспечивая семантику Exactly Once при списании средств.

## Реализация

* Создание заказов и асинхронная оплата — orders-service/.../orders/service/OrderService.java

* Создание счета, пополнение, просмотр баланса — payments-service/.../payments/service/AccountService.java

## Архитектурное проектирование

a) Разделение на сервисы
orders-service — отвечает за операции с заказами.

payments-service — за финансы пользователя.

b) Логичное использование очередей сообщений
Kafka используется для отправки и получения событий:

order-payments — запрос на оплату заказа.

payment-success / payment-failure — обратная связь.

c) Применение паттернов:
Transactional Outbox (Order Service)
→ OrderService сохраняет заказ и сообщение в одной транзакции.

Transactional Inbox и Outbox (Payments Service)
→ Платёжный сервис принимает событие через Kafka, обрабатывает его и отвечает результатом в Kafka.

Exactly Once при списании денег
→ Реализовано через атомарное обновление баланса в AccountService.withdrawIfSufficient(...).

## Docker / Docker Compose
a) Dockerfile
Каждый сервис имеет собственный Dockerfile:

orders-service/Dockerfile

payments-service/Dockerfile

b) docker-compose.yml
Разворачивает всю инфраструктуру: Kafka, Zookeeper, PostgreSQL и оба микросервиса.

c) Проверка
docker compose up --build запускает всю систему.

## Запуск
```bash
cd payments-service
mvn clean package

cd ../orders-service
mvn clean package
```
### Запуск контейнеров
```bash
docker compose up --build
```
