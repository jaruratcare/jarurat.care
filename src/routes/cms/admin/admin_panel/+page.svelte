<script>
import { onMount } from "svelte";
import { supabase } from "$lib/supabase";
import Nav from "$lib/components/nav.svelte";

let users = [];
let roles = {};
let selectedUser = null;
let loading = true;

// Load users
async function loadUsers() {
  loading = true;

  const { data, error } = await supabase
    .from("user")
    .select("*");

  if (error) {
    console.log(error);
  } else {
    users = data;
    users.forEach(u => {
      roles[u.email] = u.role;
    });
  }

  loading = false;
}

// Select user
function openUser(user) {
  selectedUser = user;
}

// Save role
async function saveRole(email) {
  const role = roles[email];

  const { error } = await supabase
    .from("user")
    .update({ role })
    .eq("email", email);

  if (error) {
    alert("Error updating role");
  } else {
    alert("Updated successfully");
    loadUsers();
  }
}

// Delete user
async function deleteUser(email) {
  const confirmDelete = confirm("Are you sure you want to delete this user?");
  if (!confirmDelete) return;

  const { error } = await supabase
    .from("user")
    .delete()
    .eq("email", email);

  if (error) {
    alert("Error deleting user");
  } else {
    alert("User deleted successfully");
    selectedUser = null;
    loadUsers();
  }
}

function closeUser() {
  selectedUser = null;
}

onMount(loadUsers);
</script>

<Nav />

<div class="admin-wrapper">

  <!-- LEFT SIDE -->
  <div class="user-list">
    <h2>Users</h2>

    {#if loading}
      <p>Loading...</p>

    {:else}
      <table>
        <thead>
          <tr>
            <th>Email</th>
            <th>Role</th>
          </tr>
        </thead>

        <tbody>
          {#each users as user}
            <tr on:click={() => openUser(user)}>
              <td>{user.email}</td>
              <td>{user.role}</td>
            </tr>
          {/each}
        </tbody>
      </table>
    {/if}
  </div>

  <!-- RIGHT SIDE -->
  <div class="user-detail">

    {#if selectedUser}
      <div class="detail-card">

        <button class="back" on:click={closeUser}>
          ← Back
        </button>

        <h2>User Details</h2>

        <p><strong>Email:</strong> {selectedUser.email}</p>

        <div class="role-section">
          <label>Role</label>

          <select bind:value={roles[selectedUser.email]}>
            <option value="pending">Pending</option>
            <option value="admin">Admin</option>
            <option value="author">Author</option>
            <option value="editor">Editor</option>
          </select>

          <button on:click={() => saveRole(selectedUser.email)}>
            Save Role
          </button>

          <button class="delete" on:click={() => deleteUser(selectedUser.email)}>
            Delete User
          </button>
        </div>

      </div>

    {:else}
      <div class="empty">
        <p>Select a user to view details</p>
      </div>
    {/if}

  </div>

</div>

<style>
.admin-wrapper {
  display: grid;
  grid-template-columns: 1fr 1.2fr;
  gap: 20px;
  padding: 100px 40px 40px;
  background: #f4f9ff;
  min-height: 100vh;
}

/* LEFT SIDE */
.user-list {
  background: white;
  padding: 20px;
  border-radius: 14px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.05);
}

.user-list h2 {
  margin-bottom: 15px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background: #0155bd;
  color: white;
  padding: 10px;
  text-align: left;
}

td {
  padding: 12px;
  border-bottom: 1px solid #eee;
}

tr {
  cursor: pointer;
  transition: 0.2s;
}

tr:hover {
  background: #eef6ff;
}

/* RIGHT SIDE */
.user-detail {
  display: flex;
}

.detail-card {
  width: 100%;
  background: white;
  padding: 30px;
  border-radius: 14px;
  box-shadow: 0 8px 30px rgba(0,0,0,0.06);
}

.back {
  background: none;
  border: none;
  color: #0155bd;
  font-weight: bold;
  margin-bottom: 20px;
  cursor: pointer;
}

.role-section {
  margin-top: 20px;
  display: flex;
  gap: 10px;
  align-items: center;
}

select {
  padding: 8px;
  border-radius: 6px;
  border: 1px solid #ccc;
}

button {
  background: #0155bd;
  color: white;
  border: none;
  padding: 8px 14px;
  border-radius: 6px;
  cursor: pointer;
}

button:hover {
  background: #013e8a;
}

/* DELETE BUTTON */
.delete {
  background: #d93025;
}

.delete:hover {
  background: #b3261e;
}

/* EMPTY STATE */
.empty {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #888;
  font-size: 18px;
}
</style>