
# ── Import libraries ────────────────────────────────────────
import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import joblib

# ════════════════════════════════════════════════════════════
# PART 1 — HEART DISEASE MODEL
# ════════════════════════════════════════════════════════════

print("Training heart disease model...")

# Step 1: Load the dataset into a DataFrame
# A DataFrame is like an Excel sheet in Python — rows and columns
heart_df = pd.read_csv("heart.csv")

# Step 2: Separate inputs (X) from output (y)
# X = all columns EXCEPT target — these are the features the model learns from
# y = only the target column — this is what the model learns to predict
X_heart = heart_df.drop("target", axis=1)
y_heart = heart_df["target"]

# Step 3: Split data into training set and testing set
# 80% of data is used to train the model
# 20% is kept aside to test how accurate the model is
# random_state=42 means the split is always the same — reproducible results
X_train, X_test, y_train, y_test = train_test_split(
    X_heart, y_heart, test_size=0.2, random_state=42
)

# Step 4: Create and train the Random Forest model
# n_estimators=100 means the forest has 100 decision trees
# Each tree votes on the prediction — majority vote wins
heart_model = RandomForestClassifier(n_estimators=100, random_state=42)
heart_model.fit(X_train, y_train)
# .fit() is where the actual learning happens — the model studies the training data

# Step 5: Test the model accuracy
y_pred = heart_model.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)
print(f"Heart model accuracy: {accuracy * 100:.2f}%")
# This prints something like: Heart model accuracy: 85.37%

# Step 6: Save the trained model to a file
# joblib saves the entire trained model so we can load it later in Flask
joblib.dump(heart_model, "heart_model.pkl")
print("Heart model saved as heart_model.pkl")


# ════════════════════════════════════════════════════════════
# PART 2 — DIABETES MODEL
# ════════════════════════════════════════════════════════════

print("\nTraining diabetes model...")

# Same process as above — just different dataset and column name
diabetes_df = pd.read_csv("diabetes.csv")

X_diabetes = diabetes_df.drop("Outcome", axis=1)
y_diabetes = diabetes_df["Outcome"]

X_train, X_test, y_train, y_test = train_test_split(
    X_diabetes, y_diabetes, test_size=0.2, random_state=42
)

diabetes_model = RandomForestClassifier(n_estimators=100, random_state=42)
diabetes_model.fit(X_train, y_train)

y_pred = diabetes_model.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)
print(f"Diabetes model accuracy: {accuracy * 100:.2f}%")

joblib.dump(diabetes_model, "diabetes_model.pkl")
print("Diabetes model saved as diabetes_model.pkl")

print("\nBoth models trained and saved successfully!")