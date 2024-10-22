<script lang="ts">
	import { cn } from '$lib/utils';
	import { createEventDispatcher } from 'svelte';

	let className = '';
	let primaryColor = '#0155bd'; // Default primary color
	let secondaryColor = '#ffba41'; // Default secondary color

	export { className as class, primaryColor, secondaryColor };

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
	style="--primary-color: {primaryColor}; --secondary-color: {secondaryColor};"
>
	<slot />
</button>

<style>
	button {
		background-color: var(--primary-color);
		color: #F9FFF2;
		position: relative;
		overflow: hidden;
		border: none;
		cursor: pointer;
	}

	button::before {
		display: block;
		background-color: var(--primary-color);
		width: 100%;
		height: 100%;
		content: '';
		position: absolute;
		z-index: -20;
		top: 0;
		left: 0;
		border-radius: 10rem; /* Optional, for rounded corners */
	}

	button::after {
		display: block;
		background-color: var(--secondary-color);
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
