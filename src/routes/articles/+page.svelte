<script lang="ts">
	import { onMount } from 'svelte';
	import Nav from '$lib/components/nav.svelte';
	import NewsFooter from '$lib/components/news-footer.svelte';
	import { Search, LayoutGrid, List, ChevronRight, Tag, X, ArrowLeft } from 'lucide-svelte';
	import { supabase } from '$lib/supabase';

	type ViewMode = 'grid' | 'list';

	interface ArticleRow {
		id: number;
		created_at: string;
		title: string | null;
		content: string | null;
		image: string | null;
		status: string | null;
		category?: string | null;
		tags?: string[] | null;
		author?: string | null;
		slug?: string | null;
		read_time?: number | null;
		hidden?: boolean | null;
		is_featured?: boolean | null;
	}

	interface ArticleDoc {
		id: number;
		slug: string;
		title: string;
		content: string;
		excerpt: string;
		thumbnail: string;
		category: string;
		tags: string[];
		author: string;
		date: string;
		readTime: number;
		isFeatured: boolean;
		hidden: boolean;
	}

	let articles: ArticleDoc[] = [];
	let selectedArticle: ArticleDoc | null = null;

	let loading = true;
	let errorMsg = '';

	let searchQuery = '';
	let activeTag: string | null = null;
	let viewMode: ViewMode = 'grid';
	let sortBy: 'date' | 'title' = 'date';

	const PAGE_SIZE = 9;
	let currentPage = 1;

	function safeText(value: string | null | undefined, fallback = '') {
		return (value ?? fallback).trim();
	}

	function makeExcerpt(content: string | null | undefined, fallback = '') {
		const text = safeText(content, fallback).replace(/\s+/g, ' ');
		return text.length > 180 ? `${text.slice(0, 180)}…` : text;
	}

	function formatDate(dateValue: string | null | undefined) {
		if (!dateValue) return '';
		const date = new Date(dateValue);
		if (Number.isNaN(date.getTime())) return '';
		return new Intl.DateTimeFormat('en-IN', {
			day: 'numeric',
			month: 'short',
			year: 'numeric'
		}).format(date);
	}

	function formatBody(content: string) {
		return content
			.split(/\n\s*\n/)
			.map((p) => p.trim())
			.filter(Boolean);
	}

	async function loadArticles() {
		loading = true;
		errorMsg = '';

		try {
			const { data, error } = await supabase
				.from('articles')
				.select('*')
				.eq('status', 'approved')
				.order('created_at', { ascending: false });

			if (error) throw error;

			const rows = (data ?? []) as ArticleRow[];

			articles = rows
				.filter((row) => !row.hidden)
				.map((row) => ({
					id: row.id,
					slug: row.slug?.trim() || String(row.id),
					title: safeText(row.title, 'Untitled Article'),
					content: safeText(row.content, ''),
					excerpt: makeExcerpt(row.content),
					thumbnail:
						row.image?.trim() ||
						'https://images.unsplash.com/photo-1505751172876-fa1923c5c528?auto=format&fit=crop&w=1200&q=80',
					category: safeText(row.category, 'General'),
					tags: Array.isArray(row.tags) ? row.tags.filter(Boolean) : [],
					author: safeText(row.author, 'Editorial'),
					date: formatDate(row.created_at),
					readTime: Number(row.read_time) > 0 ? Number(row.read_time) : 3,
					isFeatured: Boolean(row.is_featured),
					hidden: Boolean(row.hidden)
				}));
		} catch (err) {
			console.error(err);
			errorMsg = 'Error loading articles';
		} finally {
			loading = false;
		}
	}

	function openArticle(article: ArticleDoc) {
		selectedArticle = article;
		window.scrollTo({ top: 0, behavior: 'smooth' });
	}

	function closeArticle() {
		selectedArticle = null;
		window.scrollTo({ top: 0, behavior: 'smooth' });
	}

	onMount(loadArticles);

	$: filtered = articles.filter((a) => {
		const q = searchQuery.toLowerCase();
		return !q || a.title.toLowerCase().includes(q) || a.content.toLowerCase().includes(q);
	});

	$: sorted = [...filtered].sort((a, b) => {
		if (sortBy === 'title') return a.title.localeCompare(b.title);
		return new Date(b.date).getTime() - new Date(a.date).getTime();
	});

	$: totalPages = Math.max(1, Math.ceil(sorted.length / PAGE_SIZE));
	$: pagedArticles = sorted.slice((currentPage - 1) * PAGE_SIZE, currentPage * PAGE_SIZE);

	$: if (currentPage > totalPages) currentPage = totalPages;

	$: allTags = [...new Set(articles.flatMap((a) => a.tags ?? []))].sort((a, b) =>
		a.localeCompare(b)
	);

	$: categories = [...new Set(articles.map((a) => a.category).filter(Boolean))].sort((a, b) =>
		a.localeCompare(b)
	);

	$: featuredArticle = articles.find((a) => a.isFeatured) ?? articles[0] ?? null;

	function clearFilters() {
		searchQuery = '';
		activeTag = null;
		currentPage = 1;
	}

	function toggleTag(tag: string) {
		activeTag = activeTag === tag ? null : tag;
		currentPage = 1;
	}
</script>

<svelte:head>
	<title>The Journal — Jarurat Care</title>
	<meta
		name="description"
		content="Approved articles from the Jarurat Care journal, loaded directly from Supabase."
	/>
</svelte:head>

<Nav />

<div class="min-h-screen bg-[#F4F9FF] pt-24 pb-16">
	<div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
		{#if selectedArticle}
			<section class="mx-auto max-w-5xl">
				<div class="mb-6">
					<button
						on:click={closeArticle}
						class="inline-flex items-center gap-2 rounded-full border border-[#D8E8FA] bg-white px-4 py-2 text-xs font-black text-[#0D2460] shadow-sm transition-colors hover:border-[#0155BD] hover:text-[#0155BD]"
					>
						<ArrowLeft size={14} />
						Back to articles
					</button>
				</div>

				<article class="overflow-hidden rounded-[2rem] border border-[#D8E8FA] bg-white shadow-[0_16px_40px_rgba(1,85,189,0.10)]">
					<div class="p-6 sm:p-8 lg:p-10 border-b border-[#E8F0FB] bg-[#FBFDFF]">
						<div class="flex flex-wrap items-center gap-3 mb-5">
							<span class="text-[10px] font-black uppercase tracking-[0.2em] text-[#0155BD]">
								{selectedArticle.category}
							</span>
							<span class="rounded-full bg-[#FFF0C2] px-2.5 py-1 text-[10px] font-black uppercase tracking-widest text-[#7A5C00]">
								{selectedArticle.readTime} min read
							</span>
						</div>

						<h1
							class="text-3xl font-black leading-tight text-[#0D2460] sm:text-4xl lg:text-5xl"
							style="font-family:'DM Serif Display',serif;"
						>
							{selectedArticle.title}
						</h1>

						<div class="mt-5 flex flex-wrap items-center gap-x-5 gap-y-2 text-sm text-[#6B7280]">
							<p class="font-bold text-[#0D2460]">{selectedArticle.author}</p>
							<span>•</span>
							<p>{selectedArticle.date}</p>
						</div>

						{#if selectedArticle.tags.length > 0}
							<div class="mt-5 flex flex-wrap gap-2">
								{#each selectedArticle.tags as tag}
									<span class="rounded-full border border-[#D8E8FA] bg-white px-3 py-1 text-xs font-bold text-[#4A6D6D]">
										#{tag}
									</span>
								{/each}
							</div>
						{/if}
					</div>

					<div class="p-4 sm:p-6 lg:p-8">
						<img
							src={selectedArticle.thumbnail}
							alt={selectedArticle.title}
							class="mb-8 h-[240px] w-full rounded-[1.5rem] object-cover shadow-sm sm:h-[320px] lg:h-[420px]"
						/>

						<div class="mx-auto max-w-3xl">
							{#each formatBody(selectedArticle.content) as paragraph}
								<p class="mb-5 text-[1.05rem] leading-8 text-[#374151] sm:text-[1.12rem]">
									{paragraph}
								</p>
							{/each}
						</div>
					</div>
				</article>
			</section>
		{:else}
			<header class="mb-10">
				<div class="flex flex-col gap-4 border-b-2 border-[#0155BD] pb-6 lg:flex-row lg:items-end lg:justify-between">
					<div>
						<p class="mb-1 text-[10px] font-black uppercase tracking-[0.3em] text-[#78C520]">
							Jarurat Care
						</p>
						<h1
							class="text-4xl font-black leading-none tracking-tight text-[#0D2460] sm:text-5xl lg:text-6xl"
							style="font-family:'DM Serif Display',serif;"
						>
							The Journal
						</h1>
						<p class="mt-3 max-w-2xl text-sm text-[#5B6780]">
							Approved articles are fetched live from Supabase and displayed directly from the table.
						</p>
					</div>

					<div class="flex flex-wrap items-center gap-3">
						<select
							bind:value={sortBy}
							class="cursor-pointer rounded-full border border-[#D8E8FA] bg-white px-4 py-2 text-xs font-bold text-[#0D2460] outline-none transition-colors hover:border-[#0155BD]"
						>
							<option value="date">Latest First</option>
							<option value="title">A – Z</option>
						</select>

						<div class="flex items-center gap-1 rounded-full border border-[#D8E8FA] bg-white p-1 shadow-sm">
							<button
								on:click={() => (viewMode = 'grid')}
								class="flex h-9 w-9 items-center justify-center rounded-full transition-all {viewMode === 'grid' ? 'bg-[#0155BD] text-white' : 'text-[#7A9999] hover:bg-[#EEF6FF]'}"
								aria-label="Grid view"
							>
								<LayoutGrid size={13} />
							</button>
							<button
								on:click={() => (viewMode = 'list')}
								class="flex h-9 w-9 items-center justify-center rounded-full transition-all {viewMode === 'list' ? 'bg-[#0155BD] text-white' : 'text-[#7A9999] hover:bg-[#EEF6FF]'}"
								aria-label="List view"
							>
								<List size={13} />
							</button>
						</div>

						<button
							on:click={loadArticles}
							class="rounded-full border border-[#D8E8FA] bg-white px-4 py-2 text-xs font-black text-[#0D2460] transition-colors hover:border-[#0155BD] hover:text-[#0155BD]"
						>
							Refresh
						</button>
					</div>
				</div>
			</header>

			<div class="grid grid-cols-1 gap-10 xl:grid-cols-[minmax(0,2fr)_minmax(320px,1fr)] xl:gap-12">
				<main class="min-w-0">
					{#if featuredArticle}
						<div
							role="button"
							tabindex="0"
							on:click={() => openArticle(featuredArticle)}
							on:keydown={(e) => e.key === 'Enter' && openArticle(featuredArticle)}
							class="group mb-10 cursor-pointer overflow-hidden rounded-[1.75rem] border border-[#D8E8FA] bg-white shadow-[0_10px_30px_rgba(1,85,189,0.08)] transition-all hover:border-[#0155BD] hover:shadow-[0_16px_40px_rgba(1,85,189,0.14)]"
						>
							<div class="flex flex-col md:flex-row">
								<div class="relative overflow-hidden md:w-[52%]">
									<img
										src={featuredArticle.thumbnail}
										alt={featuredArticle.title}
										class="h-72 w-full object-cover transition-transform duration-700 group-hover:scale-[1.03] md:h-[420px]"
									/>
								</div>

								<div class="flex flex-col justify-between border-t border-[#D8E8FA] bg-[#FBFDFF] p-8 md:w-[48%] md:border-l md:border-t-0 md:p-10">
									<div>
										<div class="mb-5 flex flex-wrap items-center gap-3">
											<span class="text-[10px] font-black uppercase tracking-[0.2em] text-[#0155BD]">
												{featuredArticle.category}
											</span>
											<span class="rounded-full bg-[#FFF0C2] px-2 py-1 text-[10px] font-black uppercase tracking-widest text-[#7A5C00]">
												Featured
											</span>
										</div>

										<h2
											class="mb-5 text-3xl font-black leading-tight text-[#0D2460] transition-colors group-hover:text-[#0155BD] md:text-4xl"
											style="font-family:'DM Serif Display',serif;"
										>
											{featuredArticle.title}
										</h2>

										<p class="line-clamp-4 text-sm leading-relaxed text-[#3B3E43]">
											{featuredArticle.excerpt}
										</p>
									</div>

									<div class="mt-8 flex items-center justify-between gap-4 border-t border-[#E8F0FB] pt-6">
										<div>
											<p class="text-sm font-bold text-[#0D2460]">{featuredArticle.author}</p>
											<p class="mt-0.5 text-xs text-[#7A9999]">
												{featuredArticle.date} · {featuredArticle.readTime} min read
											</p>
										</div>
										<span class="border-b border-[#B8D7FB] pb-0.5 text-xs font-bold text-[#0155BD] transition-colors group-hover:border-[#0155BD]">
											Read →
										</span>
									</div>
								</div>
							</div>
						</div>
					{:else if loading}
						<div class="rounded-2xl border border-[#D8E8FA] bg-white p-10 text-center text-sm font-bold text-[#7A9999] shadow-sm animate-pulse">
							Loading articles…
						</div>
					{:else if errorMsg}
						<div class="rounded-2xl border border-dashed border-[#D8E8FA] bg-white py-20 text-center shadow-sm">
							<p class="mb-2 text-2xl font-black text-[#0D2460]" style="font-family:'DM Serif Display',serif;">
								Something went wrong
							</p>
							<p class="text-sm text-[#5B6780]">{errorMsg}</p>
						</div>
					{:else}
						<div class="rounded-[1.75rem] border border-dashed border-[#D8E8FA] bg-white py-24 text-center shadow-sm">
							<p class="mb-3 text-3xl font-black text-[#C7DDF7]" style="font-family:'DM Serif Display',serif;">
								No articles yet.
							</p>
						</div>
					{/if}

					<div class="mt-8 flex flex-wrap items-center gap-3">
						<div class="relative min-w-[260px] flex-1">
							<input
								type="text"
								bind:value={searchQuery}
								placeholder="Search title, content…"
								class="w-full rounded-full border border-[#D8E8FA] bg-white py-3 pl-5 pr-10 text-sm text-[#0D2460] outline-none transition-colors placeholder:text-[#B6C7DE] focus:border-[#0155BD]"
							/>
							<Search size={15} class="absolute right-4 top-1/2 -translate-y-1/2 text-[#B6C7DE]" />
						</div>

						{#if activeTag}
							<button
								on:click={() => (activeTag = null)}
								class="flex items-center gap-2 rounded-full border border-[#D8E8FA] bg-[#EEF6FF] px-4 py-2 text-xs font-bold text-[#0155BD]"
							>
								<X size={10} />
								Clear #{activeTag}
							</button>
						{/if}
					</div>

					{#if sorted.length === 0 && !loading}
						<div class="mt-8 rounded-[1.75rem] border border-dashed border-[#D8E8FA] bg-white py-20 text-center shadow-sm">
							<p class="mb-4 text-3xl font-black text-[#C7DDF7]" style="font-family:'DM Serif Display',serif;">
								No articles found.
							</p>
							<button
								on:click={clearFilters}
								class="rounded-full bg-[#0155BD] px-6 py-2.5 text-xs font-bold text-white transition-colors hover:bg-[#0D2460]"
							>
								Clear Filters
							</button>
						</div>
					{:else}
						{#if viewMode === 'grid'}
							<div class="mt-8 grid grid-cols-1 gap-5 md:grid-cols-2 xl:grid-cols-3">
								{#each pagedArticles as item}
									<article
										role="button"
										tabindex="0"
										on:click={() => openArticle(item)}
										on:keydown={(e) => e.key === 'Enter' && openArticle(item)}
										class="group cursor-pointer overflow-hidden rounded-[1.5rem] border border-[#D8E8FA] bg-white shadow-sm transition-all hover:shadow-[0_14px_34px_rgba(1,85,189,0.10)]"
									>
										<div class="relative aspect-[16/9] overflow-hidden">
											<img
												src={item.thumbnail}
												alt={item.title}
												class="h-full w-full object-cover transition-transform duration-500 group-hover:scale-105"
											/>
											<div class="absolute left-3 top-3">
												<span class="rounded-full bg-[#0155BD] px-2 py-1 text-[8px] font-black uppercase tracking-widest text-white shadow-sm">
													{item.category}
												</span>
											</div>
										</div>

										<div class="flex flex-1 flex-col p-5">
											<p class="mb-2 text-[9px] font-black uppercase tracking-[0.2em] text-[#7A9999]">
												{item.date}
											</p>

											<h3
												class="mb-2 line-clamp-3 text-base font-black leading-snug text-[#0D2460] transition-colors group-hover:text-[#0155BD]"
												style="font-family:'DM Serif Display',serif;"
											>
												{item.title}
											</h3>

											<p class="mb-4 line-clamp-2 text-xs leading-relaxed text-[#3B3E43]">
												{item.excerpt}
											</p>

											<div class="mt-auto flex items-center justify-between gap-3 border-t border-[#F0F6FD] pt-3">
												<p class="truncate text-[10px] font-bold text-[#7A9999]">
													{item.author}
												</p>
												<span class="shrink-0 text-[10px] font-bold text-[#0155BD] group-hover:underline">
													Read →
												</span>
											</div>
										</div>
									</article>
								{/each}
							</div>
						{:else}
							<div class="mt-8 overflow-hidden rounded-[1.5rem] border border-[#D8E8FA] bg-white shadow-sm">
								{#each pagedArticles as item}
									<div
										role="button"
										tabindex="0"
										on:click={() => openArticle(item)}
										on:keydown={(e) => e.key === 'Enter' && openArticle(item)}
										class="group flex cursor-pointer items-center gap-5 border-b border-[#E8F0FB] px-5 py-4 transition-colors last:border-b-0 hover:bg-[#F8FBFF]"
									>
										<div class="h-14 w-20 flex-shrink-0 overflow-hidden rounded-xl border border-[#D8E8FA]">
											<img
												src={item.thumbnail}
												alt={item.title}
												class="h-full w-full object-cover transition-transform duration-500 group-hover:scale-105"
											/>
										</div>

										<div class="min-w-0 flex-1">
											<span class="mb-1 inline-block text-[9px] font-black uppercase tracking-widest text-[#0155BD]">
												{item.category}
											</span>
											<h3
												class="line-clamp-2 text-sm font-black text-[#0D2460] transition-colors group-hover:text-[#0155BD]"
												style="font-family:'DM Serif Display',serif;"
											>
												{item.title}
											</h3>
											<p class="mt-1 text-[10px] text-[#7A9999]">{item.author} · {item.date}</p>
										</div>

										<ChevronRight size={14} class="shrink-0 text-[#C7DDF7] transition-colors group-hover:text-[#0155BD]" />
									</div>
								{/each}
							</div>
						{/if}

						{#if totalPages > 1}
							<div class="mt-8 flex flex-wrap items-center justify-center gap-2">
								<button
									on:click={() => (currentPage = Math.max(1, currentPage - 1))}
									disabled={currentPage === 1}
									class="rounded-full border border-[#D8E8FA] bg-white px-4 py-2 text-xs font-bold text-[#0D2460] transition-colors hover:bg-[#EEF6FF] disabled:opacity-30"
								>
									← Prev
								</button>
								<span class="px-2 text-xs font-bold text-[#7A9999]">
									{currentPage} / {totalPages}
								</span>
								<button
									on:click={() => (currentPage = Math.min(totalPages, currentPage + 1))}
									disabled={currentPage === totalPages}
									class="rounded-full border border-[#D8E8FA] bg-white px-4 py-2 text-xs font-bold text-[#0D2460] transition-colors hover:bg-[#EEF6FF] disabled:opacity-30"
								>
									Next →
								</button>
							</div>
						{/if}
					{/if}
				</main>

				<aside class="flex min-w-0 flex-col gap-8 xl:pt-2">
					<div class="rounded-[1.5rem] border border-[#D8E8FA] bg-white p-5 shadow-sm">
						<p class="mb-3 text-[10px] font-black uppercase tracking-[0.25em] text-[#78C520]">
							Categories
						</p>
						<div class="flex flex-col gap-2">
							{#if categories.length === 0}
								<p class="text-sm text-[#7A9999]">No categories available.</p>
							{:else}
								{#each categories as category}
									<button
										on:click={() => (searchQuery = category)}
										class="flex items-center justify-between rounded-2xl border border-[#E8F0FB] px-4 py-3 text-left transition-colors hover:bg-[#FBFDFF]"
									>
										<span class="text-sm font-bold text-[#0D2460]">{category}</span>
										<span class="rounded-full bg-[#EEF6FF] px-2 py-0.5 text-xs font-black text-[#0155BD]">
											{articles.filter((a) => a.category === category).length}
										</span>
									</button>
								{/each}
							{/if}
						</div>
					</div>

					<div class="rounded-[1.5rem] border border-[#D8E8FA] bg-white p-5 shadow-sm">
						<div class="mb-4 flex items-center justify-between gap-3">
							<p class="text-[10px] font-black uppercase tracking-[0.25em] text-[#78C520]">
								Trending Topics
							</p>
							{#if allTags.length > 0}
								<span class="text-[10px] font-black uppercase text-[#7A9999]">
									{allTags.length} tags
								</span>
							{/if}
						</div>

						{#if allTags.length === 0}
							<p class="text-sm text-[#7A9999]">No tags available.</p>
						{:else}
							<div class="flex flex-wrap gap-2">
								{#each allTags as tag}
									<button
										on:click={() => toggleTag(tag)}
										class="rounded-full border px-3 py-1.5 text-xs font-bold transition-all {activeTag === tag
											? 'border-[#0155BD] bg-[#0155BD] text-white'
											: 'border-[#D8E8FA] bg-white text-[#4A6D6D] hover:border-[#0155BD] hover:text-[#0155BD]'}"
									>
										<Tag size={9} class="mr-1 inline -mt-0.5" />#{tag}
									</button>
								{/each}
							</div>
						{/if}
					</div>

					<div class="rounded-[1.5rem] border border-[#D8E8FA] bg-white p-5 shadow-sm">
						<p class="mb-3 text-[10px] font-black uppercase tracking-[0.25em] text-[#78C520]">
							Live from Supabase
						</p>
						<p class="text-sm leading-relaxed text-[#3B3E43]">
							This page shows only approved articles stored in the <strong>articles</strong> table. Images are loaded from the image URL saved in the same row.
						</p>
					</div>
				</aside>
			</div>
		{/if}
	</div>

	<div class="mx-auto mt-16 max-w-7xl px-4 sm:px-6 lg:px-8">
		<div class="overflow-hidden rounded-[1.75rem] border border-[#D8E8FA] bg-white shadow-[0_10px_30px_rgba(1,85,189,0.08)]">
			<NewsFooter />
		</div>
	</div>
</div>

<style>
	:global(body) {
		font-family: 'DM Sans', sans-serif;
		background: #f4f9ff;
	}

	:global(*) {
		box-sizing: border-box;
	}

	:global(.article-body p) {
		font-size: 1.08rem;
		line-height: 1.9;
		color: #374151;
		margin-bottom: 1.25rem;
	}

	:global(.article-body h2) {
		font-family: 'DM Serif Display', serif;
		font-size: 1.8rem;
		font-weight: 900;
		color: #0d2460;
		margin: 2.25rem 0 1rem;
	}

	:global(.article-body h3) {
		font-family: 'DM Serif Display', serif;
		font-size: 1.35rem;
		font-weight: 900;
		color: #0155bd;
		margin: 1.75rem 0 0.75rem;
	}

	:global(.article-body ul, .article-body ol) {
		margin: 0 0 1.25rem 1.5rem;
		color: #374151;
	}

	:global(.article-body li) {
		margin-bottom: 0.5rem;
		font-size: 1.02rem;
	}

	:global(.article-body blockquote) {
		border-left: 3px solid #0155bd;
		background: #fbfdff;
		padding: 1rem 1.25rem;
		margin: 1.5rem 0;
		font-style: italic;
		border-radius: 0.75rem;
	}

	:global(.article-body img) {
		border-radius: 1rem;
		margin: 1.5rem 0;
	}
</style>