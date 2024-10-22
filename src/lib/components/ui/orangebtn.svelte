<script lang="ts">
	import { cn } from '$lib/utils';
	import { createEventDispatcher } from 'svelte';

	let className = '';

	export { className as class };

	const dispatch = createEventDispatcher();

	function onClick(ev: MouseEvent & { currentTarget: EventTarget & HTMLButtonElement }) {
		dispatch('click', ev);
	}
</script>

<button
	on:click={onClick}
	class={cn(
		'py-3 px-6 text-[#F9FFF2] rounded-full font-semibold inline-block',
		'relative overflow-hidden',
		className
	)}
>
	<slot />
</button>

<style>
	button::before {
		display: block;
		background-color: #0155bd;
		width: 100%;
		height: 100%;
		content: '';
		position: absolute;
		z-index: -20;
		top: 0;
		left: 0;
	}

	button::after {
		display: block;
		background-color: #ffba41;
		width: 100%;
		height: 100%;
		content: '';
		position: absolute;
		z-index: -10;
		top: 0;
		left: 0;
		border-radius: 10rem;
		transition: 0.3s cubic-bezier(0.5, 0.36, 0.22, 1) transform;
		transform: scale(0);
		transform-origin: center;
	}

	button:hover::after {
		transform: scale(2);
	}
</style>
