# ── Import libraries ─────────────────────────────────────────
from flask import Flask, request, jsonify
from flask_cors import CORS
import joblib
import numpy as np

# ── Create Flask app ─────────────────────────────────────────
app = Flask(__name__)
CORS(app)

# ── Load trained models on startup ───────────────────────────
heart_model    = joblib.load("heart_model.pkl")
diabetes_model = joblib.load("diabetes_model.pkl")

print("Both models loaded successfully")

# ── Prediction endpoint ───────────────────────────────────────
@app.route("/ml/predict", methods=["POST"])
def predict():

    # Step 1: Read JSON from Spring Boot
    data = request.get_json()

    # Step 2: Extract values
    age            = data.get("age", 40)
    gender         = data.get("gender", "Male")
    heart_rate     = data.get("heartRate", 75)
    blood_pressure = data.get("bloodPressure", 120)
    blood_glucose  = data.get("bloodGlucose", 90)
    cholesterol    = data.get("cholesterol", 200)
    chest_pain     = data.get("chestPain", False)
    fatigue        = data.get("fatigue", False)
    shortness      = data.get("shortnessBreath", False)
    frequent_urin  = data.get("frequentUrination", False)
    blurred_vision = data.get("blurredVision", False)

    # Step 3: Convert to numbers the model understands
    sex = 1 if gender == "Male" else 0
    cp  = 1 if chest_pain else 0
    fbs = 1 if blood_glucose > 120 else 0

    # ── Heart model input ─────────────────────────────────────
    # Column order MUST match training data exactly:
    # age, sex, cp, trestbps, chol, fbs, restecg,
    # thalach, exang, oldpeak, slope, ca, thal
    heart_input = np.array([[
        age,                        # age
        sex,                        # sex — 1=male, 0=female
        cp,                         # cp — chest pain (1=yes, 0=no)
        blood_pressure,             # trestbps — resting blood pressure
        cholesterol,                # chol — cholesterol (default 200)
        fbs,                        # fbs — blood glucose > 120? 1/0
        0,                          # restecg — 0=normal (default)
        heart_rate,                 # thalach — max heart rate
        1 if shortness else 0,      # exang — breathlessness mapped here
        0.0,                        # oldpeak — ST depression, 0=normal
        2,                          # slope — 2=flat (most common)(upslopping=normal)
        0,                          # ca — 0 major vessels (default)
        3                           # thal — 3=normal in uci ds
    ]])

    # ── Diabetes model input ──────────────────────────────────
    # Column order MUST match training data exactly:
    # Pregnancies, Glucose, BloodPressure, SkinThickness,
    # Insulin, BMI, DiabetesPedigreeFunction, Age
    diabetes_input = np.array([[
        0,              # Pregnancies — not collected, use 0
        blood_glucose,  # Glucose — blood glucose level
        blood_pressure, # BloodPressure
        20,             # SkinThickness — not collected, 20=average default
        80,             # Insulin — not collected, 80=normal default
        25.0,           # BMI — not collected, 25=normal default
        0.5,            # DiabetesPedigreeFunction — not collected, 0.5=average
        age             # Age
    ]])

    # Step 4: Get predictions
    # predict_proba returns [[prob_no_disease, prob_has_disease]]
    # [0][1] gives us the probability of HAVING the condition
    heart_risk    = heart_model.predict_proba(heart_input)[0][1]
    diabetes_risk = diabetes_model.predict_proba(diabetes_input)[0][1]

    # Step 5: Return result as JSON
    return jsonify({
        "heartRisk":    round(float(heart_risk), 4),
        "diabetesRisk": round(float(diabetes_risk), 4)
    })


# ── Health check endpoint ─────────────────────────────────────
@app.route("/ml/health", methods=["GET"])
def health():
    return jsonify({"status": "ML service is running"})


# ── Start Flask server ────────────────────────────────────────
if __name__ == "__main__":
    app.run(port=5000, debug=True)