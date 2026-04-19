<script>
  export let role = 'pending';
</script>

<div class="role-box">
  {#if role === 'admin'}
    <a href="/cms/admin/admin_panel">Admin Panel</a>
    <a href="/cms/admin_dashboard">Admin Dashboard</a>
  {:else if role === 'author'}
    <a href="/cms/author_dashboard">Author Dashboard</a>
  {:else if role === 'editor'}
    <a href="/cms/editor_dashboard">Editor Dashboard</a>
  {:else}
    <p>Your account is pending approval.</p>
  {/if}
</div>

<style>
  .role-box {
    display: grid;
    gap: 10px;
    padding: 16px;
    border-radius: 12px;
    background: #f7f9fc;
  }

  a {
    text-decoration: none;
    color: #2f80ed;
    font-weight: 600;
  }

  p {
    margin: 0;
    color: #666;
  }
</style>