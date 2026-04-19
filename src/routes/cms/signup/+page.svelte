<script>
import { auth } from '$lib/firebase';
import { createUserWithEmailAndPassword } from "firebase/auth";
import { supabase } from '$lib/supabase';
import Nav from "$lib/components/nav.svelte";

let email = "";
let password = "";
let message = "";

// Password validation function
function validatePassword(password) {

const pattern =
/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/;

return pattern.test(password);

}

async function signup() {

message = "";

if (!validatePassword(password)) {

message =
"Password must be at least 8 characters and include uppercase, lowercase, number and special character.";

return;

}

try {

const userCredential = await createUserWithEmailAndPassword(auth, email, password);


// 🔹 Insert email into Supabase
const { data, error } = await supabase
  .from('user')
  .insert([
    {
      email: email,
      role: null
    }
  ])

if (error) {
  throw error
}

message = "Account created successfully!";

email = "";
password = "";

} catch (err) {

message = err.message;

}

}
</script>
<Nav />
<div class="auth-container">

<div class="auth-card">

<h2>Create Account</h2>
<p class="subtitle">Register for the CMS</p>

<input
type="email"
placeholder="Email address"
bind:value={email}
/>

<input
type="password"
placeholder="Password"
bind:value={password}
/>

<button on:click={signup}>
Sign Up
</button>

<p class="message">{message}</p>

<p class="switch-link">
Already have an account?
<a href="/cms/login">Login</a>
</p>

</div>

</div>

<style>

.auth-container{
height:100vh;
display:flex;
align-items:center;
justify-content:center;
background:#f5f7fb;
}

.auth-card{
width:380px;
padding:40px;
background:white;
border-radius:10px;
box-shadow:0 8px 20px rgba(0,0,0,0.08);
text-align:center;
}

h2{
margin-bottom:5px;
color:#2c3e50;
}

.subtitle{
font-size:14px;
color:#777;
margin-bottom:25px;
}

input{
width:100%;
padding:12px;
margin-bottom:15px;
border:1px solid #ddd;
border-radius:6px;
font-size:14px;
}

button{
width:100%;
padding:12px;
background:#2f80ed;
border:none;
color:white;
border-radius:6px;
cursor:pointer;
font-size:15px;
}

button:hover{
background:#1c6dd0;
}

.message{
margin-top:12px;
font-size:13px;
color:#333;
}

.switch-link{
margin-top:18px;
font-size:14px;
}

a{
color:#2f80ed;
text-decoration:none;
}

</style>