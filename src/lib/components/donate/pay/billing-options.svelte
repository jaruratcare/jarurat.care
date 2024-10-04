<script lang="ts">
	import { ArrowRight } from 'lucide-svelte';
	import Button from '$lib/components/ui/button.svelte';
	import Checkbox from '$lib/components/ui/checkbox.svelte';
	import CustomCheckbox from '$lib/components/ui/custom-checkbox.svelte';
	import Input from '$lib/components/ui/input.svelte';
	import { createEventDispatcher } from 'svelte';
	import { writable } from 'svelte/store';

	const dispatch = createEventDispatcher();

	function onChange(data: Record<string, string | number>) {
		dispatch('data', data);
	}

	function onSubmit() {
		dispatch('submit', {});
	}

	const amounts = [200, 500, 1000, 2000, 5000, 10000];

	let selectedAmount = '200';
	let selectedPaymentType = 'one-time';
	let selectedFinalAmount = writable(200);

	selectedFinalAmount.subscribe((value) => onChange({ amount: value }));
</script>

<div class="font-manrope flex flex-col items-center gap-4 md:gap-8 px-5 py-4 md:px-6 md:py-6 rounded-2xl bg-white shadow border">

	<div class="w-full">
		<h3 class="text-[1.1em] leading-tight font-rubik font-medium text-[#0D2460]">
			Billing Options
		</h3>
		<div class="flex items-center justify-evenly mt-3">
			<CustomCheckbox 
				label="Pay Once"
				type="radio"
				name="payment-type"
				value="one-time"
				checked={selectedPaymentType === 'one-time'}
				on:change={(ev) => {
					selectedPaymentType = 'one-time';
					onChange({ ['payment-type']: 'one-time' });
				}}
			/>
			<CustomCheckbox
				label="Pay Monthly"
				type="radio"
				name="payment-type"
				value="subscription"
				checked={selectedPaymentType === 'subscription'}
				on:change={(ev) => {
					selectedPaymentType = 'subscription';
					onChange({ ['payment-type']: 'subscription' });
				}}
			/>
		</div>
	</div>

	<div class="w-full">
		<h3 class="text-[1.1em] leading-tight font-rubik font-medium text-[#0D2460]">
			Donation Amount
		</h3>
		<p class="leading-tight text-[#576171] md:text-[0.9em] text-[0.7em]">
			Choose the amount you would like to donate
		</p>
		<div class="flex md:gap-3 gap-2 flex-wrap mt-2">
			{#each amounts as amount}
				<Checkbox
					label={`₹${amount}`}
					type="radio"
					name="amount"
					value={amount}
					checked={amount.toString() === selectedAmount}
					on:change={() => {
						selectedAmount = amount.toString();
						selectedFinalAmount.set(amount);
					}}
				/>
			{/each}

			<Checkbox
				label={`Custom Amount`}
				type="radio"
				name="amount"
				value="custom-amount"
				checked={'custom-amount' === selectedAmount}
				on:change={() => (selectedAmount = 'custom-amount')}
			/>

			{#if selectedAmount === 'custom-amount'}
				<Input
					type="number"
					max="50000"
					min="0"
					class="md:min-w-[18rem] min-w-[12rem]"
					placeholder="Please enter amount"
					required
					prefix="₹"
					label="Amount"
					on:change={(ev) => selectedFinalAmount.set(parseFloat(ev.detail.value))}
				/>
			{/if}
		</div>
	</div>

	<Button class="bg-[#0155bd] flex items-center gap-1 px-4 py-2" on:click={onSubmit}>
		Continue <ArrowRight />
	</Button>
</div>
