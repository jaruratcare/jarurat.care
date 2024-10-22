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

<div class="bg-[#f6e9cd] sm:px-4 py-3 sm:rounded-lg">
	<button
		class="flex items-center justify-between cursor-pointer w-full text-[#0d2460] font-medium text-[0.9em] text-left gap-4"
		on:click={() => isOpen.update(val => !val)}
	>
		{question}
		<Plus class={`text-[#0d2460] ${$isOpen ? 'rotate-180' : ''} transition-transform`} style="width: 20px; height: 20px;" />
	</button>

	<div
		bind:this={container}
		style="height: {$isOpen ? contentHeight : 0}px"
		class="overflow-hidden transition-all text-gray-700 text-[0.8em]"
	>
		<p class="mt-2 px-2 pt-2 border-t prose">{@html marked(answer)}</p>
	</div>
</div>

<style>
	.rotate-180 {
		transform: rotate(180deg);
	}
	.transition-transform {
		transition: transform 0.3s ease;
	}
</style>
