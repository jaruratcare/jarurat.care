<script lang="ts">
	import { onMount } from 'svelte';
	import { supabase } from '$lib/supabase';
	import { RefreshCw, CheckCircle2, Trash2, Eye, Plus, Upload, X } from 'lucide-svelte';

	type Article = {
		id: number;
		created_at: string;
		title: string | null;
		content: string | null;
		image: string | null;
		status: string | null;
	};

	let user = {
		name: '',
		email: '',
		role: 'Admin'
	};
  let selectedArticle: Article | null = null;
	let articles: Article[] = [];
	let loading = true;
	let savingId: number | null = null;
	let errorMsg = '';
	let activeFilter: 'all' | 'pending' | 'approved' = 'pending';

	// form fields
	let title = '';
	let content = '';
	let imageFile: File | null = null;
	let previewUrl = '';
	let submitting = false;
	let formMessage = '';

	async function loadUser() {
		const { data, error } = await supabase.auth.getUser();

		if (error || !data?.user) return;

		const authUser = data.user;
		user = {
			name: authUser.user_metadata?.full_name || authUser.email?.split('@')[0] || 'Admin',
			email: authUser.email || '',
			role: authUser.user_metadata?.role || 'Admin'
		};
	}
 async function openArticle(article:     Article) {
	selectedArticle = article;
}
	async function loadArticles() {
		loading = true;
		errorMsg = '';

		const query = supabase
			.from('articles')
			.select('*')
			.order('created_at', { ascending: false });

		const { data, error } =
			activeFilter === 'all'
				? await query
				: await query.eq('status', activeFilter);

		if (error) {
			console.error(error);
			errorMsg = error.message;
			articles = [];
		} else {
			articles = data ?? [];
		}

		loading = false;
	}

	function handleImageChange(event: Event) {
		const input = event.target as HTMLInputElement;
		const file = input.files?.[0] ?? null;

		imageFile = file;

		if (previewUrl) {
			URL.revokeObjectURL(previewUrl);
			previewUrl = '';
		}

		if (file) {
			previewUrl = URL.createObjectURL(file);
		}
	}

	async function uploadImage(file: File) {
		const fileName = `${Date.now()}-${file.name}`;

		const { error: uploadError } = await supabase.storage
			.from('articles')
			.upload(fileName, file);

		if (uploadError) {
			throw new Error(uploadError.message);
		}

		const { data } = supabase.storage.from('articles').getPublicUrl(fileName);
		return data.publicUrl;
	}

	async function createArticle() {
		formMessage = '';

		if (!title.trim() || !content.trim()) {
			formMessage = 'Title and content are required.';
			return;
		}

		submitting = true;

		try {
			let imageUrl = null;

			if (imageFile) {
				imageUrl = await uploadImage(imageFile);
			}

			const { error } = await supabase.from('articles').insert([
				{
					title: title.trim(),
					content: content.trim(),
					image: imageUrl,
					status: 'pending'
				}
			]);

			if (error) throw error;

			formMessage = 'Article added successfully.';
			title = '';
			content = '';
			imageFile = null;

			if (previewUrl) {
				URL.revokeObjectURL(previewUrl);
				previewUrl = '';
			}

			await loadArticles();
		} catch (err: any) {
			console.error(err);
			formMessage = err.message || 'Failed to add article.';
		} finally {
			submitting = false;
		}
	}

	async function approveArticle(id: number) {
		savingId = id;
		const { error } = await supabase.from('articles').update({ status: 'approved' }).eq('id', id);

		if (error) {
			alert(error.message);
		} else {
			await loadArticles();
		}
		savingId = null;
	}

	async function deleteArticle(id: number) {
		const ok = confirm('Delete this article permanently?');
		if (!ok) return;

		savingId = id;
		const { error } = await supabase.from('articles').delete().eq('id', id);

		if (error) {
			alert(error.message);
		} else {
			await loadArticles();
		}
		savingId = null;
	}

	function formatDate(value: string | null | undefined) {
		if (!value) return '';
		return new Intl.DateTimeFormat('en-IN', {
			day: 'numeric',
			month: 'short',
			year: 'numeric'
		}).format(new Date(value));
	}

	onMount(async () => {
		await loadUser();
		await loadArticles();
	});
</script>

<div class="dashboard">
	<div class="topbar">
		<div>
			<h1>Admin Dashboard</h1>
			<p>Approve, add, review, and delete articles from Supabase.</p>
		</div>

		<button class="refresh" on:click={loadArticles}>
			<RefreshCw size={16} />
			Refresh
		</button>
	</div>

	<div class="card user-card">
		<h3>Logged in as</h3>
		<p><strong>Name:</strong> {user.name || 'Loading...'}</p>
		<p><strong>Email:</strong> {user.email || '-'}</p>
		<p><strong>Role:</strong> {user.role}</p>
	</div>

	<div class="card">
		<h3>Add Article</h3>

		<div class="form-grid">
			<div class="field">
				<label>Title</label>
				<input bind:value={title} placeholder="Article title" />
			</div>

			<div class="field full">
				<label>Description</label>
				<textarea bind:value={content} rows="6" placeholder="Write the article content..."></textarea>
			</div>

			<div class="field full">
				<label>Image</label>
				<input type="file" accept="image/*" on:change={handleImageChange} />
			</div>

			{#if previewUrl}
				<div class="preview-wrap full">
					<img src={previewUrl} alt="Preview" class="preview" />
					<button class="remove-preview" on:click={() => { imageFile = null; URL.revokeObjectURL(previewUrl); previewUrl = ''; }}>
						<X size={14} />
						Remove
					</button>
				</div>
			{/if}
		</div>

		<button class="submit" on:click={createArticle} disabled={submitting}>
			<Plus size={16} />
			{submitting ? 'Adding...' : 'Add Article'}
		</button>

		{#if formMessage}
			<p class="message">{formMessage}</p>
		{/if}
	</div>

	<div class="filters">
		<button class:active={activeFilter === 'pending'} on:click={() => { activeFilter = 'pending'; loadArticles(); }}>
			Pending
		</button>
		<button class:active={activeFilter === 'approved'} on:click={() => { activeFilter = 'approved'; loadArticles(); }}>
			Approved
		</button>
		<button class:active={activeFilter === 'all'} on:click={() => { activeFilter = 'all'; loadArticles(); }}>
			All
		</button>
	</div>

	{#if loading}
		<div class="card">Loading articles…</div>
	{:else if errorMsg}
		<div class="card error">{errorMsg}</div>
	{:else if articles.length === 0}
		<div class="card">No articles found.</div>
	{:else}
		<div class="grid">
			{#each articles as article}
				<div class="article-card">
					{#if article.image}
						<img src={article.image} alt={article.title ?? 'Article image'} class="thumb" />
					{/if}

					<div class="content">
						<div class="meta">
							<span class="status {article.status ?? 'pending'}">{article.status ?? 'pending'}</span>
							<span>{formatDate(article.created_at)}</span>
						</div>

						<h3>{article.title ?? 'Untitled article'}</h3>
						<p>{article.content ? article.content.slice(0, 180) : 'No content available.'}</p>

						<div class="actions">
							{#if article.status !== 'approved'}
								<button
									class="approve"
									disabled={savingId === article.id}
									on:click={() => approveArticle(article.id)}
								>
									<CheckCircle2 size={16} />
									Approve
								</button>
							{/if}

							<button class="view" on:click={() => openArticle(article)}>
	<Eye size={16} />
	View
</button>

							<button
								class="delete"
								disabled={savingId === article.id}
								on:click={() => deleteArticle(article.id)}
							>
								<Trash2 size={16} />
								Delete
							</button>
						</div>
					</div>
				</div>
			{/each}
		</div>
	{/if}
</div>
{#if selectedArticle}
	<div class="card" style="margin-top:20px;">
		<div style="display:flex; justify-content:space-between; align-items:center;">
			<h3>{selectedArticle.title}</h3>
			<button
				class="remove-preview"
				on:click={() => (selectedArticle = null)}
			>
				<X size={14} />
				Close
			</button>
		</div>

		<p style="color:#6b7280; font-size:12px;">
			{formatDate(selectedArticle.created_at)}
		</p>

		{#if selectedArticle.image}
			<img
				src={selectedArticle.image}
				alt="preview"
				style="width:100%; max-height:350px; object-fit:cover; border-radius:12px; margin:10px 0;"
			/>
		{/if}

		<p style="white-space:pre-line; line-height:1.8; color:#444;">
			{selectedArticle.content}
		</p>
	</div>
{/if}
<style>
	.dashboard {
		padding: 40px;
		font-family: Arial, sans-serif;
		background: #f6f9ff;
		min-height: 100vh;
	}

	.topbar {
		display: flex;
		justify-content: space-between;
		align-items: center;
		gap: 16px;
		margin-bottom: 20px;
	}

	h1 {
		color: #2c3e50;
		margin: 0 0 8px;
	}

	p {
		margin: 0;
		color: #5b6472;
	}

	.card {
		background: white;
		padding: 20px;
		margin-bottom: 20px;
		border-radius: 12px;
		box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
	}

	.user-card {
		max-width: 420px;
	}

	.refresh,
	.filters button,
	.actions button,
	.submit,
	.remove-preview {
		border: none;
		cursor: pointer;
		border-radius: 10px;
		font-weight: 700;
		transition: 0.2s ease;
		display: inline-flex;
		align-items: center;
		gap: 8px;
	}

	.refresh {
		background: #0155bd;
		color: white;
		padding: 10px 16px;
	}

	.refresh:hover {
		background: #0d2460;
	}

	.filters {
		display: flex;
		gap: 10px;
		margin-bottom: 20px;
		flex-wrap: wrap;
	}

	.filters button {
		background: white;
		color: #0d2460;
		padding: 10px 16px;
		border: 1px solid #d8e8fa;
	}

	.filters button.active {
		background: #0155bd;
		color: white;
		border-color: #0155bd;
	}

	.form-grid {
		display: grid;
		grid-template-columns: repeat(2, minmax(0, 1fr));
		gap: 16px;
		margin-top: 14px;
	}

	.field {
		display: flex;
		flex-direction: column;
		gap: 6px;
	}

	.field.full,
	.preview-wrap.full {
		grid-column: 1 / -1;
	}

	label {
		font-size: 13px;
		font-weight: 700;
		color: #0d2460;
	}

	input,
	textarea {
		width: 100%;
		padding: 12px 14px;
		border: 1px solid #d8e8fa;
		border-radius: 10px;
		font: inherit;
		background: #fff;
	}

	textarea {
		resize: vertical;
		min-height: 140px;
	}

	.submit {
		margin-top: 16px;
		background: #0155bd;
		color: white;
		padding: 12px 18px;
	}

	.submit:hover {
		background: #0d2460;
	}

	.submit:disabled {
		opacity: 0.7;
		cursor: not-allowed;
	}

	.message {
		margin-top: 12px;
		font-weight: 700;
		color: #0d2460;
	}

	.preview-wrap {
		position: relative;
	}

	.preview {
		width: 100%;
		max-height: 260px;
		object-fit: cover;
		border-radius: 12px;
		border: 1px solid #d8e8fa;
	}

	.remove-preview {
		position: absolute;
		top: 12px;
		right: 12px;
		background: rgba(13, 36, 96, 0.9);
		color: white;
		padding: 8px 12px;
	}

	.grid {
		display: grid;
		grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
		gap: 20px;
	}

	.article-card {
		background: white;
		border-radius: 16px;
		overflow: hidden;
		box-shadow: 0 4px 14px rgba(0, 0, 0, 0.06);
		border: 1px solid #e8eef7;
	}

	.thumb {
		width: 100%;
		height: 200px;
		object-fit: cover;
		display: block;
	}

	.content {
		padding: 18px;
	}

	.meta {
		display: flex;
		justify-content: space-between;
		align-items: center;
		gap: 10px;
		font-size: 12px;
		color: #6b7280;
		margin-bottom: 10px;
	}

	.status {
		padding: 4px 10px;
		border-radius: 999px;
		font-size: 11px;
		font-weight: 800;
		text-transform: uppercase;
	}

	.status.pending {
		background: #fff4cc;
		color: #8a6500;
	}

	.status.approved {
		background: #e6f8e9;
		color: #167a33;
	}

	h3 {
		margin: 0 0 10px;
		color: #0d2460;
		font-size: 18px;
	}

	.content p {
		line-height: 1.6;
		color: #444;
	}

	.actions {
		display: flex;
		flex-wrap: wrap;
		gap: 10px;
		margin-top: 16px;
	}

	.actions button {
		padding: 10px 14px;
		color: white;
	}

	.approve {
		background: #22a06b;
	}

	.approve:hover {
		background: #1b855a;
	}

	.view {
		background: #0155bd;
	}

	.view:hover {
		background: #0d2460;
	}

	.delete {
		background: #dc2626;
	}

	.delete:hover {
		background: #b91c1c;
	}

	.error {
		color: #b91c1c;
		background: #fff1f1;
		border: 1px solid #fecaca;
	}

	@media (max-width: 700px) {
		.dashboard {
			padding: 20px;
		}

		.topbar {
			flex-direction: column;
			align-items: flex-start;
		}

		.form-grid {
			grid-template-columns: 1fr;
		}
	}
</style>