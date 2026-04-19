<script>
  import { auth } from '$lib/firebase';
  import { supabase } from '$lib/supabase';
  import { signInWithEmailAndPassword } from 'firebase/auth';
  import Nav from '$lib/components/nav.svelte';
  import { goto } from '$app/navigation';

  let email = '';
  let password = '';
  let error = '';
  let showAdminChoice = false; // modal for admin
  let adminEmail = '';

  async function login() {
    error = '';
    try {
      const cred = await signInWithEmailAndPassword(auth, email, password);
      const loggedEmail = cred.user.email;

      const { data, error: roleError } = await supabase
        .from('user')
        .select('role')
        .eq('email', loggedEmail)
        .maybeSingle();

      if (roleError) throw roleError;

      const role = data?.role || 'pending';

      if (role === 'admin') {
        // instead of redirecting immediately, show the choice
        adminEmail = loggedEmail;
        showAdminChoice = true;
      } else if (role === 'author') {
        goto('/cms/author_dashboard');
      } else if (role === 'editor') {
        goto('/cms/editor_dashboard');
      } else {
        goto('/cms/pending');
      }

    } catch (err) {
      error = err.message;
    }
  }

  function goToPanel() {
    showAdminChoice = false;
    goto('/cms/admin/admin_panel');
  }

  function goToDashboard() {
    showAdminChoice = false;
    goto('/cms/admin/admin_dashboard'); // assuming you have a dashboard page
  }

</script>

<Nav />

<div class="login-container">
  <div class="login-card">
    <h2>Welcome Back</h2>
    <p class="subtitle">Login to your CMS account</p>

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

    <button on:click={login}>
      Login
    </button>

    <a href="/cms/forgot">Forgot Password?</a>

    <p class="error">{error}</p>

    <p class="signup-link">
      Don't have an account?
      <a href="/cms/signup">Sign up</a>
    </p>
  </div>
</div>

<!-- Admin choice modal -->
{#if showAdminChoice}
  <div class="modal-backdrop">
    <div class="modal-card">
      <h3>Admin Access</h3>
      <p>Where do you want to go?</p>
      <div class="modal-buttons">
        <button on:click={goToDashboard}>Dashboard</button>
        <button on:click={goToPanel}>Admin Panel</button>
      </div>
    </div>
  </div>
{/if}

<style>
.login-container {
  min-height: calc(100vh - 120px);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 60px 20px;
  background: #f5f7fb;
  box-sizing: border-box;
}

.login-card {
  width: 380px;
  padding: 40px;
  background: white;
  border-radius: 10px;
  box-shadow: 0 8px 20px rgba(0,0,0,0.08);
  text-align: center;
}

h2 { margin-bottom: 5px; color: #2c3e50; }
.subtitle { font-size: 14px; color: #777; margin-bottom: 25px; }
input { width: 100%; padding: 12px; margin-bottom: 15px; border:1px solid #ddd; border-radius:6px; font-size:14px; box-sizing:border-box; }
button { width: 100%; padding:12px; background:#2f80ed; border:none; color:white; border-radius:6px; cursor:pointer; font-size:15px; }
button:hover { background:#1c6dd0; }
a { display:block; margin-top:10px; font-size:13px; color:#2f80ed; text-decoration:none; }
.error { color:red; font-size:13px; margin-top:10px; }
.signup-link { margin-top:15px; font-size:14px; }

/* Modal styling */
.modal-backdrop {
  position: fixed;
  top: 0; left: 0; right: 0; bottom: 0;
  background: rgba(0,0,0,0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-card {
  background: white;
  padding: 30px;
  border-radius: 10px;
  text-align: center;
  width: 300px;
}

.modal-card h3 {
  margin-bottom: 15px;
}

.modal-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.modal-buttons button {
  width: 45%;
  padding: 10px;
  border-radius: 6px;
}
</style>