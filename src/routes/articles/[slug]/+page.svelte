<script lang="ts">
  import { page } from '$app/stores';
  import articles from '$lib/data/articles.json'; // JSON is the only source now
  import { ChevronLeft, Calendar, Share2, Youtube, ArrowUp, Link as LinkIcon } from 'lucide-svelte';
  import Nav from '$lib/components/nav.svelte';
  import { onMount } from 'svelte';

  const slug = $page.params.slug;
  const article: any = articles.find((a: any) => a.slug === slug);
  
  let readingProgress = 0;
  let showScrollTop = false;
  let articleEl: HTMLElement;

  onMount(() => {
    const onScroll = () => {
      if (!articleEl) return;
      const rect = articleEl.getBoundingClientRect();
      const total = articleEl.offsetHeight;
      const scrolled = Math.max(0, -rect.top);
      readingProgress = Math.min(100, Math.round((scrolled / total) * 100));
      showScrollTop = window.scrollY > 600;
    };
    window.addEventListener('scroll', onScroll, { passive: true });
    return () => window.removeEventListener('scroll', onScroll);
  });

  function getEmbedUrl(url: string) {
    if (!url) return null;
    const regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
    const match = url.match(regExp);
    return (match && match[2].length === 11) ? `https://www.youtube.com/embed/${match[2]}` : url;
  }

  function scrollTop() { window.scrollTo({ top: 0, behavior: 'smooth' }); }
</script>

<Nav />

<div class="fixed top-0 left-0 right-0 z-[100] h-1 bg-gray-100">
  <div class="h-full bg-[#004085] transition-all duration-100" style="width:{readingProgress}%"/>
</div>

{#if article}
  <div class="min-h-screen pt-24 pb-32 bg-[#FAFAF8]">
    <article bind:this={articleEl} class="max-w-4xl mx-auto px-6 font-inter">
      <h1 class="text-4xl md:text-6xl font-bold text-gray-900 mb-8">{article.title}</h1>
      
      <div class="article-body prose prose-lg max-w-none text-gray-800">
        {@html article.content}
      </div>

      {#if article.citations}
        <div class="mt-16 p-8 bg-gray-50 rounded-2xl border border-gray-100">
            <h3 class="flex items-center gap-2 text-lg font-bold text-[#004085] mb-4">
                <LinkIcon size={18}/> References
            </h3>
            <ul class="space-y-2">
                {#each article.citations as cite}
                    <li class="text-sm text-gray-600 truncate">
                        <a href={cite} target="_blank" class="hover:underline">{cite}</a>
                    </li>
                {/each}
            </ul>
        </div>
      {/if}
    </article>
  </div>
{:else}
  <div class="h-screen flex items-center justify-center">Article Not Found</div>
{/if}