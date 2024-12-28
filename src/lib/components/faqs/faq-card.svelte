<script lang="ts">
	import { marked } from 'marked';
	import { Plus } from 'lucide-svelte';
	import { onMount } from 'svelte';
	import { writable } from 'svelte/store';

	export let question = '';
	export let answer = '';

	let isOpen = writable(false);

	let container: HTMLDivElement | null = null;
	let contentHeight = 0;

	onMount(() => {
		if (container) contentHeight = container.scrollHeight;
	});
</script>

<div class="sm:px-4 px-2 sm:py-4 py-2 rounded-lg border-b {$isOpen ? "bg-[#FFBA41]":"bg-[#FFF1D2]"}">
	<button
	    style="font-weight: {$isOpen ? "700" : "400"}"
		class="flex items-center justify-between cursor-pointer w-full text-[#0D2561] font-sans font-normal sm:text-[0.8em] text-[0.6em] text-left sm:gap-4 gap-0"
		on:click={() => isOpen.set(!$isOpen)}
	>
		{question}
		<Plus class={`text-[#0D2561] ${$isOpen ? 'rotate-180' : ''} transition-transform`} />
	</button>

	<div
		bind:this={container}
		style="height: {$isOpen ? contentHeight : 0}px"
		class="overflow-hidden transition-all text-[#0D2561] sm:text-[0.8em] text-[0.6em]"
	>
		<p class="sm:pt-2 pt-1">{@html marked(answer)}</p>
	</div>
</div>
