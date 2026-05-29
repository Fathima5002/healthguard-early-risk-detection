# # HealthGuard — Early Risk Detection System

HealthGuard is an early risk detection system that estimates the likelihood
of heart disease and diabetes risk based on user-provided vitals —
powered by machine learning and built with a Java Spring Boot microservice architecture.
> ⚠️ This tool is for informational and educational purposes only.
> It does not diagnose any medical condition and is not a substitute
> for professional medical advice.

---

## Features

- User registration with health profile
- Daily vitals input with symptom tracking
- Real ML-based risk assessment (Random Forest)
- Color-coded risk dashboard (LOW / MODERATE / HIGH)
- Recommendation messages based on risk level
- Historical tracking with trend chart

---

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | HTML, CSS, JavaScript |
| Backend | Java 17, Spring Boot 4 |
| Database | MySQL 8 |
| ML Service | Python, Flask, Scikit-learn |
| ML Algorithm | Random Forest Classifier |
| Dataset | UCI Heart Disease + Pima Indians Diabetes |

---

---

## ML Model Accuracy

| Model | Dataset | Accuracy |
|---|---|---|
| Heart Disease | UCI Heart Disease | 98.54% |
| Diabetes | Pima Indians Diabetes | 72.08% |

---

## How to Run Locally

### Prerequisites
- Java 17+
- Python 3.10+
- MySQL 8

### 1. Database setup
```sql
CREATE DATABASE healthtracker;
-- Then run all CREATE TABLE statements from /sql/schema.sql
```

### 2. Spring Boot backend

cd health-tracker
./mvnw spring-boot:run

Runs on http://localhost:8080

### 3. ML service

cd ml-service
pip install -r requirements.txt
python train_model.py
python app.py

Runs on http://localhost:5000

### 4. Frontend
Open `frontend/index.html` in your browser.

---

## API Endpoints

| Method | URL | Description |
|---|---|---|
| POST | /api/register | Register a new user |
| POST | /api/health/submit | Submit vitals and get risk prediction |
| GET | /api/health/history/{userId} | Get past predictions for a user |
| POST | /ml/predict | ML risk prediction (Flask) |
