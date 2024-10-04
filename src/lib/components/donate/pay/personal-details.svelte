<script lang="ts">
	import { ArrowRight, LoaderCircle, Check } from 'lucide-svelte';
	import Button from '$lib/components/ui/button.svelte';
	import Input from '$lib/components/ui/input.svelte';
	import { createEventDispatcher } from 'svelte';

	export let isLoading = false;
	export let selectedAmount: string | number;
	export let selectedPaymentType: string;
	export let isAnonymous = false;

	const dispatch = createEventDispatcher();

	function onChange(data: Record<string, string | number>) {
		dispatch('data', data);
	}

	function onSubmit() {
		dispatch('submit', {});
	}

	const fields = [
		{
			type: 'text',
			label: 'Full Name',
			placeholder: 'Enter your full name',
			name: 'full-name',
			required: true
		},
		{
			type: 'text',
			label: 'Amount',
			prefix: `₹`,
			name: 'amount',
			value: `${selectedAmount}`,
			required: true,
			readonly: true
		},
		{
			type: 'email',
			label: 'Email',
			placeholder: 'Enter your email',
			name: 'email',
			required: true
		},
		{
			type: 'tel',
			label: 'Phone',
			placeholder: 'Enter your phone',
			prefix: '+91',
			name: 'phone',
			required: true
		},
		{
			type: 'textarea',
			label: 'Message',
			name: 'message',
			placeholder: 'Enter your message',
			height: '50px'
		},
		{ 
			type: 'text', 
			label: 'PAN', 
			placeholder: 'Enter your PAN number', 
			name: 'pan' 
		}
	];
</script>

<div class="font-manrope flex flex-col items-center gap-4 px-4 py-6 rounded-2xl bg-white shadow w-full">
	<div class="flex gap-4 item-center justify-center">
		<h3 class="text-[0.7em] font-extralight px-10 py-1.5 rounded-md leading-tight font-rubik bg-[#FDE3A7] text-[#A15819]">
			{selectedPaymentType === 'one-time' ? 'Pay-Once' : 'Monthly'}
		</h3>
		<h3 class="text-[0.7em] font-extralight py-1.5 px-10 rounded-md leading-tight font-rubik bg-[#FDE3A7] text-[#A15819]">
			₹{selectedAmount}
		</h3>
	</div>
	<div class="w-full">
		<h3 class="text-[1.1em] leading-tight font-rubik font-medium text-[#0D2460]">
			Personal Details
		</h3>
		<p class="leading-tight text-[#576171] text-[0.7em] pb-4">Please fill in your details correctly</p>
		<div class="grid md:grid-cols-2 gap-1 md:gap-6">
			{#each fields as field}
				<Input
					name={field.name}
					type={field.type}
					label={field.label}
					placeholder={field.placeholder}
					prefix={field.prefix}
					required={field.required}
					readonly={field.readonly}
					value={field.value} 
					on:change={(ev) => onChange({ [field.name]: ev.detail.value })}
				/>
			{/each}
		</div>

		<div class="flex items-center gap-2 mt-4">
			<label for="anonymous" class="flex items-center cursor-pointer">
				<div class="relative items-center justify-center">
					<input type="checkbox" id="anonymous" bind:checked={isAnonymous} class="sr-only peer" />
					<div class="w-4 h-4 bg-gray-200 rounded border border-gray-300 peer-checked:bg-transparent peer-checked:border-black-900 peer-focus:ring-1 peer-focus:ring-[#010101]"></div>
					<Check class='absolute w-3 h-3 text-black-900 left-[50%] top-[50%] -translate-y-1/2 -translate-x-1/2 hidden peer-checked:block' />
				</div>
				<span class="ml-2 text-[#000000] text-[0.6rem]">Keep Donation Anonymous</span>
			</label>
		</div>
	</div>

	<div class="flex gap-2">
		<Button
			class="flex items-center gap-1 px-4 py-1 bg-white text-[#0155bd] border border-[#0155bd] "
			on:click={() => dispatch('back', {})}
		>
			Back
		</Button>
		<Button class="bg-[#0155bd] flex items-center gap-1 px-4 py-1" on:click={onSubmit}>
			{#if isLoading}
				<LoaderCircle class="animate-spin" />
			{:else}
				Continue <ArrowRight />
			{/if}
		</Button>
	</div>
</div>
