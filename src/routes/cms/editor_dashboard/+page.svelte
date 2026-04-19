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

	let articles: Article[] = [];
	let loading = true;

	let selectedArticle: Article | null = null;

	let editingId: number | null = null;
	let editTitle = '';
	let editContent = '';

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
		}

		loading = false;
	}

	function openArticle(article: Article) {
		selectedArticle = article;
		window.scrollTo({ top: 0, behavior: 'smooth' });
	}

	function closeArticle() {
		selectedArticle = null;
	}

	function startEdit(article: Article) {
		editingId = article.id;
		editTitle = article.title;
		editContent = article.content;
	}

	async function saveEdit(id: number) {
		const { error } = await supabase
			.from('articles')
			.update({
				title: editTitle,
				content: editContent,
				status: 'pending'
			})
			.eq('id', id);

		if (error) {
			alert(error.message);
		} else {
			editingId = null;
			loadArticles();
		}
	}

	async function approve(id: number) {
		await supabase.from('articles').update({ status: 'approved' }).eq('id', id);
		loadArticles();
	}

	async function reject(id: number) {
		await supabase.from('articles').update({ status: 'pending' }).eq('id', id);
		loadArticles();
	}

	async function deleteArticle(id: number) {
		const ok = confirm("Delete this article?");
		if (!ok) return;

		await supabase.from('articles').delete().eq('id', id);
		loadArticles();
	}

	function formatDate(date: string) {
		return new Date(date).toLocaleDateString('en-IN', {
			day: 'numeric',
			month: 'short',
			year: 'numeric'
		});
	}

	onMount(loadArticles);
</script>

<div class="dashboard">

	<h1>Editor Dashboard</h1>

	{#if selectedArticle}
		<!-- FULL ARTICLE VIEW -->
		<div class="full-article">

			<button class="back" on:click={closeArticle}>← Back</button>

			<h2>{selectedArticle.title}</h2>

			<p class="meta">
				{formatDate(selectedArticle.created_at)} · {selectedArticle.status}
			</p>

			<img
				src={selectedArticle.image || 'https://images.unsplash.com/photo-1505751172876-fa1923c5c528'}
				class="hero"
			/>

			<p class="content">{selectedArticle.content}</p>

		</div>

	{:else}

		<!-- GRID VIEW -->
		{#if loading}
			<p>Loading...</p>

		{:else if articles.length === 0}
			<p>No articles found.</p>

		{:else}
			<div class="grid">

				{#each articles as article}
					<div class="card">

						<img
							src={article.image || 'https://images.unsplash.com/photo-1505751172876-fa1923c5c528'}
							class="thumb"
							on:click={() => openArticle(article)}
						/>

						<div class="card-body">

							<h3 on:click={() => openArticle(article)}>
								{article.title}
							</h3>

							<p class="date">{formatDate(article.created_at)}</p>

							<span class="status {article.status}">
								{article.status ?? 'pending'}
							</span>

							<p class="preview">
								{article.content.slice(0, 100)}...
							</p>

							{#if editingId === article.id}
								<input bind:value={editTitle} class="input" />
								<textarea bind:value={editContent} class="textarea"></textarea>

								<button on:click={() => saveEdit(article.id)}>Save</button>
								<button class="cancel" on:click={() => (editingId = null)}>Cancel</button>

							{:else}
								<div class="actions">
									<button class="edit" on:click={() => startEdit(article)}>Edit</button>
									<button class="approve" on:click={() => approve(article.id)}>Approve</button>
									<button class="reject" on:click={() => reject(article.id)}>Reject</button>
									<button class="delete" on:click={() => deleteArticle(article.id)}>Delete</button>
								</div>
							{/if}

						</div>

					</div>
				{/each}

			</div>
		{/if}

	{/if}

</div>

<style>
.dashboard {
	padding: 40px;
	background: #f4f9ff;
	min-height: 100vh;
	font-family: 'DM Sans', sans-serif;
}

h1 {
	font-size: 28px;
	font-weight: bold;
	margin-bottom: 20px;
	color: #0d2460;
}

/* GRID */
.grid {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
	gap: 20px;
}

/* CARD */
.card {
	background: white;
	border-radius: 16px;
	overflow: hidden;
	box-shadow: 0 8px 20px rgba(0,0,0,0.05);
	transition: 0.3s;
}

.card:hover {
	transform: translateY(-4px);
	box-shadow: 0 12px 30px rgba(0,0,0,0.1);
}

.thumb {
	width: 100%;
	height: 180px;
	object-fit: cover;
	cursor: pointer;
}

.card-body {
	padding: 16px;
}

h3 {
	cursor: pointer;
	font-size: 18px;
	margin-bottom: 6px;
	color: #0d2460;
}

.date {
	font-size: 12px;
	color: #888;
	margin-bottom: 6px;
}

.status {
	font-size: 11px;
	padding: 4px 10px;
	border-radius: 20px;
	font-weight: bold;
	display: inline-block;
	margin-bottom: 8px;
}

.status.pending {
	background: #fff4cc;
	color: #8a6500;
}

.status.approved {
	background: #e6f8e9;
	color: #167a33;
}

.preview {
	font-size: 13px;
	color: #444;
	margin-bottom: 10px;
}

.actions {
	display: flex;
	gap: 6px;
	flex-wrap: wrap;
}

button {
	border: none;
	padding: 6px 10px;
	border-radius: 6px;
	cursor: pointer;
	font-size: 12px;
	color: white;
}

.edit { background: #0155bd; }
.approve { background: #22a06b; }
.reject { background: #f59e0b; }
.delete { background: #dc2626; }
.cancel { background: gray; }

button:hover {
	opacity: 0.85;
}

/* FULL ARTICLE */
.full-article {
	max-width: 900px;
	margin: auto;
	background: white;
	padding: 30px;
	border-radius: 20px;
	box-shadow: 0 10px 30px rgba(0,0,0,0.08);
}

.full-article h2 {
	font-size: 32px;
	margin-bottom: 10px;
}

.meta {
	color: #777;
	margin-bottom: 20px;
}

.hero {
	width: 100%;
	border-radius: 12px;
	margin-bottom: 20px;
}

.content {
	line-height: 1.8;
	color: #333;
	white-space: pre-line;
}

.back {
	margin-bottom: 20px;
	background: #0155bd;
	padding: 8px 14px;
	border-radius: 8px;
	color: white;
}
</style>