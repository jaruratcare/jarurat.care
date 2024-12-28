<script lang="ts">
	import { createEventDispatcher } from 'svelte';

	export let type: 'radio' | 'checkbox' = 'checkbox';
	export let value: string | number;
	export let label = '';
	export let name = '';
	export let checked: boolean = false;
	export let disabled: boolean = false;

	const dispatch = createEventDispatcher();

	function onChange(value: string) {
		dispatch('change', { value });
	}
</script>

<label class="relative font-rubik">
	<input
		{type}
		{value}
		{name}
		class="absolute inset-0 size-full opacity-0 cursor-pointer"
		{checked}
		{disabled}
		on:change={(ev) => onChange(ev.currentTarget.value)}
	/>
	<small class="sm:py-3 py-2 px-8 sm:px-12 text-[0.8em] sm:text-[0.85em] font-thin flex items-center gap-1">
		{label || value}
	</small>
</label>

<style>
	input ~ small {
		color: #868B93;
		border-radius: 0.4rem;
		border: 0.1rem solid #e8ebf1;
		background-color: #e8ebf1;
	}

	input:checked ~ small {
		color: #0155bd;
		border: 0.1rem solid #0155bd;
		background-color: #d3f2fc;
	}
</style>
