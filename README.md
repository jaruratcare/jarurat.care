# 🤖 JaruratCare HopeBot

JaruratCare HopeBot is a Spring Boot-based WhatsApp chatbot built to assist cancer patients and caregivers through personalized, compassionate support. Integrated with the Meta WhatsApp Cloud API and Firebase, it enables real-time conversations, provides access to critical care resources, and maintains privacy and trust.

---

## 🧩 Features

- 📱 WhatsApp Chat Integration via Meta Cloud API
- 🌐 Language Selection & Personalized Greeting
- 🔐 Secure Consent & Data Handling
- 🧑‍⚕️ Patient Details Collection (Cancer type, Stage, Hospital, etc.)
- 💬 Menu-driven Navigation with 7+ Support Modules:
  - Financial Guidance 💰
  - Nutritional Care 🍱
  - Emotional Support 💜
  - Nearby Hospitals 🏥
  - Free/Low-Cost Stay & Food 🏨🍲
  - Diagnostic Labs 🧪
  - Palliative & Hospice Care ☘️
  - Talk to a Volunteer 🧑‍🤝‍🧑

---

## ⚙️ Architecture


<img width="808" alt="Architecture" src="https://github.com/user-attachments/assets/34041aed-a8b7-444b-8a50-1a740fd0cdc5" />


✅ Ultra-Light Firebase Structure (Only Intent)
📁 Collection: users

Each user gets a single document (based on WhatsApp number).
🔸 Example Document (users/whatsapp:+919999000001)

{
  "userId": "whatsapp:+919999000001",
  "language": "English",
  "currentIntent": "Accommodation Support",
  "lastSeen": "2025-06-18T12:30:00Z"
}

