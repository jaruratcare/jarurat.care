import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";   // add this

const firebaseConfig = {
  apiKey: "AIzaSyAaG8uOZ507VdVKo-5Va0kqRJIJB5gw65Q",
  authDomain: "news-and-blogs-c0ec0.firebaseapp.com",
  projectId: "news-and-blogs-c0ec0",
  storageBucket: "news-and-blogs-c0ec0.firebasestorage.app",
  messagingSenderId: "395300860961",
  appId: "1:395300860961:web:0d94038e9a6fd400791f60",
  measurementId: "G-J4371PPCWE"
};

const app = initializeApp(firebaseConfig);

export const auth = getAuth(app);
export const db = getFirestore(app);   