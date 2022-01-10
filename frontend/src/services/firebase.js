import { initializeApp } from "firebase/app";
import { GoogleAuthProvider } from 'firebase/auth'

const firebaseConfig = {
    apiKey: "AIzaSyABgtRg_odESeT1cVRHNSaWqV0NBgq9IyU",
    authDomain: "questions-98a5f.firebaseapp.com",
    projectId: "questions-98a5f",
    storageBucket: "questions-98a5f.appspot.com",
    messagingSenderId: "1005975627522",
    appId: "1:1005975627522:web:a8765d4c10cf1f0db1041b"
};

const app = initializeApp(firebaseConfig);
const googleAuth = new GoogleAuthProvider();

export {app, googleAuth}