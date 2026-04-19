<script lang="ts">
	import { onMount } from 'svelte';
	import { supabase } from '$lib/supabase';

	type Article = {
		id: number;
		title: string;
		content: string;
		image: string | null;
		status: string | null;
		created_at: string;
	};

	let user: any = null;
	let articles: Article[] = [];
	let loading = true;

	let selectedArticle: Article | null = null;

	let title = '';
	let content = '';
	let imageFile: File | null = null;
	let previewUrl = '';
	let submitting = false;
	let message = '';

	async function loadUser() {
		const { data } = await supabase.auth.getUser();
		user = data.user;
	}

	async function loadArticles() {
		loading = true;

		const { data, error } = await supabase
			.from('articles')
			.select('*')
			.order('created_at', { ascending: false });

		if (error) {
			console.error(error);
			articles = [];
		} else {
			articles = data ?? [];
			if (!selectedArticle && articles.length > 0) {
				selectedArticle = articles[0];
			}
		}

		loading = false;
	}

	function handleImage(e: Event) {
		const file = (e.target as HTMLInputElement).files?.[0];
		imageFile = file || null;

		if (previewUrl) URL.revokeObjectURL(previewUrl);
		if (file) previewUrl = URL.createObjectURL(file);
	}

	async function uploadImage(file: File) {
		const fileName = `${Date.now()}-${file.name}`;

		const { error } = await supabase.storage
			.from('articles')
			.upload(fileName, file);

		if (error) throw error;

		const { data } = supabase.storage.from('articles').getPublicUrl(fileName);
		return data.publicUrl;
	}

	async function addArticle() {
		if (!title.trim() || !content.trim()) {
			message = 'Title and content are required';
			return;
		}

		submitting = true;
		message = '';

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

			message = 'Submitted for approval';
			title = '';
			content = '';
			imageFile = null;

			if (previewUrl) {
				URL.revokeObjectURL(previewUrl);
				previewUrl = '';
			}

			await loadArticles();
		} catch (err: any) {
			message = err.message || 'Something went wrong';
		}

		submitting = false;
	}

	function openArticle(article: Article) {
		selectedArticle = article;
		window.scrollTo({ top: 0, behavior: 'smooth' });
	}

	function deleteArticle(id: number) {
		if (!confirm('Delete this article?')) return;

		supabase
			.from('articles')
			.delete()
			.eq('id', id)
			.then(({ error }) => {
				if (error) {
					alert(error.message);
				} else {
					loadArticles();
					if (selectedArticle?.id === id) selectedArticle = articles[0] ?? null;
				}
			});
	}

	function formatDate(date: string) {
		return new Date(date).toLocaleDateString('en-IN', {
			day: 'numeric',
			month: 'short',
			year: 'numeric'
		});
	}

	onMount(async () => {
		await loadUser();
		await loadArticles();
	});
</script>

<div class="dashboard">
	<div class="topbar">
		<div>
			<h1>Author Dashboard</h1>
			<p class="welcome">Welcome, {user?.email?.split('@')[0] || '...'}</p>
		</div>
		<div class="pill">Articles: {articles.length}</div>
	</div>

	<div class="layout">
		<!-- LEFT: CREATE FORM -->
		<section class="card form-card">
			<h3>Create Article</h3>

			<input placeholder="Article title" bind:value={title} />
			<textarea rows="7" placeholder="Write your content..." bind:value={content}></textarea>
			<input type="file" accept="image/*" on:change={handleImage} />

			{#if previewUrl}
				<img src={previewUrl} class="preview" alt="Preview" />
			{/if}

			<button on:click={addArticle} disabled={submitting}>
				{submitting ? 'Publishing...' : 'Publish'}
			</button>

			{#if message}
				<p class="msg">{message}</p>
			{/if}
		</section>

		<!-- MIDDLE: ARTICLES LIST -->
		<section class="articles">
			<div class="section-head">
				<h3>Your Articles</h3>
				<p>Click any card to open it in the preview panel.</p>
			</div>

			{#if loading}
				<div class="card small">Loading...</div>
			{:else if articles.length === 0}
				<div class="card small">No articles yet.</div>
			{:else}
				<div class="grid">
					{#each articles as article}
						<article
							class="card article-card {selectedArticle?.id === article.id ? 'active' : ''}"
							on:click={() => openArticle(article)}
							role="button"
							tabindex="0"
							on:keydown={(e) => e.key === 'Enter' && openArticle(article)}
						>
							<img
								src={article.image || 'https://images.unsplash.com/photo-1505751172876-fa1923c5c528?auto=format&fit=crop&w=1200&q=80'}
								class="thumb"
								alt={article.title}
							/>

							<div class="body">
								<div class="row">
									<h4>{article.title}</h4>
									<span class="status {article.status}">{article.status || 'pending'}</span>
								</div>

								<p class="date">{formatDate(article.created_at)}</p>
								<p class="preview-text">{article.content.slice(0, 110)}...</p>

								<div class="actions">
									<button
										class="delete"
										on:click|stopPropagation={() => deleteArticle(article.id)}
									>
										Delete
									</button>
								</div>
							</div>
						</article>
					{/each}
				</div>
			{/if}
		</section>

		<!-- RIGHT: BIG ARTICLE PREVIEW -->
		<section class="card reader">
			{#if selectedArticle}
				<div class="reader-head">
					<div>
						<p class="reader-label">{selectedArticle.status || 'pending'}</p>
						<h2>{selectedArticle.title}</h2>
						<p class="reader-meta">
							{formatDate(selectedArticle.created_at)} · {user?.email?.split('@')[0] || 'author'}
						</p>
					</div>
				</div>

				{#if selectedArticle.image}
					<img
						src={selectedArticle.image}
						class="hero"
						alt={selectedArticle.title}
					/>
				{/if}

				<div class="reader-content">
					{selectedArticle.content}
				</div>
			{:else}
				<div class="empty">
					<h3>No article selected</h3>
					<p>Click an article card to read it here.</p>
				</div>
			{/if}
		</section>
	</div>
</div>

<style>
	.dashboard {
		padding: 40px;
		background: #f4f9ff;
		min-height: 100vh;
		font-family: 'DM Sans', sans-serif;
	}

	.topbar {
		display: flex;
		justify-content: space-between;
		align-items: flex-end;
		gap: 16px;
		margin-bottom: 24px;
	}

	h1 {
		font-size: 30px;
		font-weight: 900;
		color: #0d2460;
		margin: 0;
	}

	.welcome {
		color: #5b6780;
		margin-top: 6px;
	}

	.pill {
		background: white;
		border: 1px solid #d8e8fa;
		color: #0155bd;
		font-weight: 800;
		padding: 10px 14px;
		border-radius: 999px;
	}

	.layout {
		display: grid;
		grid-template-columns: 340px minmax(0, 1.1fr) minmax(340px, 1fr);
		gap: 24px;
		align-items: start;
	}

	.card {
		background: white;
		border-radius: 18px;
		padding: 20px;
		box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);
		border: 1px solid #e8eef7;
	}

	.form-card input,
	.form-card textarea {
		width: 100%;
		margin: 10px 0;
		padding: 12px 14px;
		border-radius: 10px;
		border: 1px solid #d9e3f0;
		outline: none;
		font: inherit;
		background: #fff;
	}

	.form-card input:focus,
	.form-card textarea:focus {
		border-color: #0155bd;
	}

	button {
		background: #0155bd;
		color: white;
		padding: 10px 14px;
		border-radius: 10px;
		border: none;
		cursor: pointer;
		font-weight: 700;
	}

	button:disabled {
		opacity: 0.65;
		cursor: not-allowed;
	}

	.preview {
		width: 100%;
		border-radius: 12px;
		margin-top: 10px;
		max-height: 220px;
		object-fit: cover;
		border: 1px solid #e8eef7;
	}

	.msg {
		color: #167a33;
		font-weight: 700;
		margin-top: 12px;
	}

	.articles h3,
	.form-card h3 {
		margin: 0 0 10px;
		color: #0d2460;
		font-size: 20px;
	}

	.section-head p {
		color: #6b7280;
		margin-bottom: 16px;
	}

	.grid {
		display: grid;
		grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
		gap: 16px;
	}

	.article-card {
		padding: 0;
		overflow: hidden;
		cursor: pointer;
		transition: transform 0.2s ease, box-shadow 0.2s ease;
	}

	.article-card:hover,
	.article-card.active {
		transform: translateY(-2px);
		box-shadow: 0 14px 30px rgba(1, 85, 189, 0.12);
		border-color: #0155bd;
	}

	.thumb {
		width: 100%;
		height: 160px;
		object-fit: cover;
		display: block;
	}

	.body {
		padding: 14px;
	}

	.row {
		display: flex;
		justify-content: space-between;
		align-items: flex-start;
		gap: 10px;
	}

	h4 {
		margin: 0;
		font-size: 16px;
		line-height: 1.35;
		color: #0d2460;
	}

	.date {
		font-size: 12px;
		color: #7a7a7a;
		margin: 6px 0 8px;
	}

	.status {
		font-size: 10px;
		padding: 4px 8px;
		border-radius: 999px;
		font-weight: 900;
		text-transform: uppercase;
		white-space: nowrap;
	}

	.status.pending {
		background: #fff4cc;
		color: #8a6500;
	}

	.status.approved {
		background: #e6f8e9;
		color: #167a33;
	}

	.preview-text {
		font-size: 13px;
		line-height: 1.65;
		color: #374151;
		margin-bottom: 12px;
	}

	.actions {
		display: flex;
		gap: 8px;
		flex-wrap: wrap;
	}

	.delete {
		background: #dc2626;
	}

	.reader {
		min-height: 540px;
		position: sticky;
		top: 24px;
	}

	.reader-head {
		margin-bottom: 16px;
		padding-bottom: 16px;
		border-bottom: 1px solid #eef2f7;
	}

	.reader-label {
		display: inline-block;
		margin: 0 0 10px;
		font-size: 10px;
		font-weight: 900;
		text-transform: uppercase;
		letter-spacing: 0.2em;
		color: #0155bd;
		background: #eef6ff;
		padding: 6px 10px;
		border-radius: 999px;
	}

	.reader h2 {
		margin: 0;
		font-size: 28px;
		line-height: 1.2;
		color: #0d2460;
	}

	.reader-meta {
		margin-top: 10px;
		color: #6b7280;
		font-size: 13px;
	}

	.hero {
		width: 100%;
		height: 260px;
		object-fit: cover;
		border-radius: 14px;
		margin-bottom: 18px;
		border: 1px solid #e8eef7;
	}

	.reader-content {
		white-space: pre-line;
		line-height: 1.9;
		font-size: 1.02rem;
		color: #374151;
		padding-right: 4px;
	}

	.empty {
		height: 100%;
		min-height: 420px;
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;
		text-align: center;
		color: #6b7280;
		gap: 8px;
	}

	.small {
		text-align: center;
	}

	@media (max-width: 1200px) {
		.layout {
			grid-template-columns: 1fr;
		}

		.reader {
			position: static;
		}
	}

	@media (max-width: 700px) {
		.dashboard {
			padding: 20px;
		}

		.topbar {
			flex-direction: column;
			align-items: flex-start;
		}
	}
</style>