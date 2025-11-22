# Fullstack Playground Kubernetes

Проект fullstack-приложения с PostgreSQL, двумя backend-сервисами и фронтендом в Kubernetes с MetalLB и Ingress.

---

## Kubernetes-ресурсы

### MetalLB
- `IPAddressPool` — `cheap` (`192.168.10.240-192.168.10.250`)

### Storage
- `PersistentVolumeClaim` — `postgres-pvc` (1Gi, ReadWriteOnce)

### Secrets
- `Secret` — `microb-postgres-secret`
  - SPRING_DATASOURCE_URL
  - SPRING_DATASOURCE_USERNAME
  - SPRING_DATASOURCE_PASSWORD

### Deployments
- `Deployment` — `postgres` (PostgreSQL 15)
- `Deployment` — `backend-deployment-1` (`server1`, порт 8081)
- `Deployment` — `backend-deployment-2` (`server2`, порт 8082)
- `Deployment` — `frontend-deployment` (`frontend`, порт 80, использует ConfigMap для HTML)

### Services
- `Service` — `postgres` (ClusterIP, порт 5432)
- `Service` — `backend-svc-1` (LoadBalancer, IP 192.168.10.244, порт 8081) — приложение отправляет RestTemplate по кластеру к сервису 2 и возвращает json, также тут весь функционал добавления и удаления продуктов
- `Service` — `backend-svc-2` (LoadBalancer, IP 192.168.10.245, порт 8082)
- `Service` — `front-svc` (LoadBalancer, IP 192.168.10.246, порт 80)

### ConfigMaps
- `ConfigMap` — `frontend-html` (index.html, кнопки Seed, Load, Clear, Hi)

### Ingress
- `Ingress` — `backend-ingress`  
  - host: `api.playground.zaikin.ru`  
  - пути: `/products` → `backend-svc-1:8081`, `/welcome` → `backend-svc-2:8082`
- `Ingress` — `frontend-ingress`  
  - host: `playground.zaikin.ru`  
  - путь `/` → `front-svc:80`  

---

## API

### Backend 1 (`server1`)
- `GET /products` — все продукты
- `GET /products/seed` — добавить тестовые продукты
- `DELETE /products/clear` — очистить продукты
- `GET /products/hi` — обращение к Backend 2

### Backend 2 (`server2`)
- `GET /welcome` — возвращает `"HELLO FROM WELCOME"`

---

## Доступ
- Frontend: `http://playground.zaikin.ru`  
- Backend API: `http://api.playground.zaikin.ru/products` и `/welcome`

---

## Запуск

```bash
kubectl apply -f k8s/
kubectl get svc -n fullstack

## Coming

Helm
Gitlab CI