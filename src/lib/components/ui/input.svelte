<script lang="ts">
	import { cn } from '$lib/utils';
	import { createEventDispatcher } from 'svelte';

	export let label: string = '';
	export let prefix: string = '';

	const dispatch = createEventDispatcher();

	function onChange(value: string) {
		dispatch('change', { value });
	}
</script>

<div>
	<small class="font-semibold text-[#676E73]">
		{label}
		{#if $$restProps.required}
			<small class="text-red-500">*</small>
		{/if}
	</small>
	<label
		class={cn(
			'flex items-stretch grow rounded-[0.4rem] overflow-hidden min-w-[40%]',
			$$restProps.class
		)}
	>
		{#if prefix}
			<span class="flex items-center justify-center bg-[#D3F2FC] text-[#0D2561] px-2 py-[0.2rem]">
				{prefix}
			</span>
		{/if}

		<input
			{...$$restProps}
			class={cn(`px-2 bg-[#e8ebf1] text-[1rem] rounded-[0.4rem] w-full text-[#0D2561] placeholder:text-[#889299] py-[0.2rem] placeholder:text-[0.7rem]`)}
			on:change={(ev) => onChange(ev.currentTarget.value)}
		/>
	</label>
</div>
