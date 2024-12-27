<script lang="ts">
	import { writable } from 'svelte/store';
	import BillingOptions from './billing-options.svelte';
	import PersonalDetails from './personal-details.svelte';
	import SuccessScreen from './success-screen.svelte';
	import ImgHeroBg from '$lib/assets/donate/hero-img.webp';
	import { billingSchema, personalDetailsSchema } from './schema';

	function updatePaymentData(data: Record<string, string | number>) {
		paymentData.set({ ...$paymentData, ...data });
		return $paymentData;
	}

	let paymentData = writable({ amount: 200, 'payment-type': 'subscription' });
	let currentScreen = 'billing';
	let isLoading = writable(false);
	let transactionId = writable<string>('');

	async function getTokenUrl(amount: number) {
		transactionId.set('');
		isLoading.set(true);
		const resp = await fetch(`/api/payment/get-pay-page-url?amount=${amount}`, {
			headers: { 'Content-Type': 'application/json' }
		}).then((resp) => resp.json());

		const txnId = resp?.data?.txnId || '';
		transactionId.set(txnId);

		return resp?.data?.url || '';
	}

	let reqCount = 1;
	const delay = (ms: number) => new Promise((res) => setTimeout(res, ms));
	const delayTime = 1000;
	const maxReqCount = 5;
	const genRangeRandom = (min: number, max: number) =>
		Math.floor(Math.random() * (max - min + 1)) + min;

	async function paymentCallback() {
		isLoading.set(true);
		window.document.body.style.overflow = 'auto';

		try {
			const response = await fetch(`/api/payment/verify-transaction?txn-id=${$transactionId}`, {
				headers: { 'Content-Type': 'application/json' }
			});
			const json = await response.json();
			const data = json.data || {};
			const state = data.state ? data.state.toLowerCase() : 'error';

			if ((state != 'completed' || state != 'error') && reqCount < maxReqCount) {
				isLoading.set(true);

				const delta = genRangeRandom(1000, 4_000);
				const totalDelay = delayTime * reqCount + delta;
				await delay(totalDelay);
				reqCount++;

				return paymentCallback();
			}

			if (state === 'completed') {
				isLoading.set(false);
				currentScreen = 'success';
			} else alert('Failed to make the transaction');

			return json;
		} catch (err) {
			2;
			console.log(err);
			return {};
		} finally {
			// finally
			isLoading.set(false);
		}
	}
</script>

<div id="donate" class="md:p-2 bg-[#D3F2FC]">
	<div
		style="background-image: url('{ImgHeroBg}');"
		class="py-8 px-4 md:my-16 max-w-[66rem] mx-auto md:rounded-xl bg-cover bg-center relative overflow-hidden"
	>
		<!-- overlay -->
		<div class="bg-[#D3F2FC] sm:bg-[#797e8a]/80 absolute inset-0"></div>

		<div class="grid md:grid-cols-3 relative z-10">
			<div class="col-span-2 hidden md:flex flex-col justify-end px-4 max-w-[70%]">
				<div class="flex flex-col justify-end items-start gap-2">
					<h3 class="font-rubik text-white text-[1.4em] leading-tight">Help Us Fight Cancer</h3>
					<p class="text-white text-[0.9em] leading-tight">
						Jarurat Care is dedicated to providing comprehensive support to cancer patients and
						their caregivers in our local community. Through emotional counseling, mental wellness
						programs, caregiver mentorship, and connections to top medical experts, we aim to
						empower those impacted by cancer. Your donation will directly fund these vital services,
						ensuring no one faces their cancer journey alone.
					</p>
					<p
						class="bg-green-400 text-[0.8em] leading-tight p-2 rounded-r-md border-l-2 border-black"
					>
						All donations to JaruratCare Foundation are eligible for 50% tax exemption under section
						80G of the Income Tax Act.
					</p>
				</div>
			</div>

			<div class="z-10">
				{#if currentScreen === 'billing'}
					<BillingOptions
						on:data={(ev) => updatePaymentData(ev.detail)}
						on:submit={() => {
							const resp = billingSchema.safeParse($paymentData);

							if (resp.success) {
								currentScreen = 'details';
							}
						}}
					/>
				{:else if currentScreen === 'details'}
					<PersonalDetails
						isLoading={$isLoading}
						on:data={(ev) => updatePaymentData(ev.detail)}
						on:submit={async () => {
							const billingDetails = billingSchema.parse($paymentData);
							const personalDetails = personalDetailsSchema.safeParse($paymentData);

							window.document.body.style.overflow = 'hidden';

							if (personalDetails.success) {
								const script = document.createElement('script');
								script.src = 'https://mercury.phonepe.com/web/bundle/checkout.js';
								document.head.appendChild(script);

								const tokenUrl = await getTokenUrl(billingDetails.amount);
								// @ts-ignore
								window.PhonePeCheckout.transact({
									tokenUrl,
									callback: paymentCallback,
									type: 'IFRAME'
								});
							} else alert(personalDetails.error.message);
						}}
					/>
				{:else}
					<SuccessScreen txnId={$transactionId} amount={$paymentData.amount} />
				{/if}
			</div>
		</div>
	</div>
</div>
