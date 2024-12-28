<script lang="ts">
	import { writable } from 'svelte/store';
	import BillingOptions from './billing-options.svelte';
	import PersonalDetails from './personal-details.svelte';
	import SuccessScreen from './success-screen.svelte';
	import ImgHeroBg from '$lib/assets/donate/hero-bg-img.webp';
	import { billingSchema, personalDetailsSchema } from './schema';
	import { TriangleAlert } from 'lucide-svelte';
	import SingleWaveDown from '$lib/svg/single-wavelightblue.svelte';

  let paymentData = writable({ amount: 200, 'payment-type': 'subscription', email: '' });
  
	// Function to update payment data
	function updatePaymentData(data: Record<string, string | number>) {
		paymentData.set({ ...$paymentData, ...data });
		return $paymentData;
	}

	let currentScreen = 'billing';
	let isLoading = writable(false);
	let transactionId = writable<string>('');
  
  function handleBack() {
		currentScreen = 'billing';
	}

	// Function to get payment token URL
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

	// Retry logic for payment status verification with exponential backoff
	let reqCount = 1;
	const delay = (ms: number) => new Promise((res) => setTimeout(res, ms));
	const delayTime = 1000;
	const maxReqCount = 5;
	const genRangeRandom = (min: number, max: number) =>
			Math.floor(Math.random() * (max - min + 1)) + min;

	// Payment callback function to verify transaction
	async function paymentCallback() {
		isLoading.set(true);
		window.document.body.style.overflow = 'auto';

		const maxRetries = 5;
		let retryCount = 0;
		let backoffTime = 1000;

		try {
			const fetchTransactionStatus = async () => {
				const response = await fetch(`/api/payment/verify-transaction?txn-id=${$transactionId}`, {
					headers: { 'Content-Type': 'application/json' }
				});

				const json = await response.json();
				const data = json.data || {};
				const state = data.state ? data.state.toLowerCase() : 'error';

				return { state, data };
			};

			while (retryCount < maxRetries) {
				const { state, data } = await fetchTransactionStatus();

				if (state === 'completed') {
					// Transaction successful
					currentScreen = 'success';
					isLoading.set(false);

					// Send email with payment details (name, amount, email)
					await fetch('https://jarurat-care-email-service.onrender.com/jarurat-care/sendMail/', {
						method: 'POST',
						headers: {
							'Content-Type': 'application/json'
						},
						body: JSON.stringify({
							name: $paymentData['full-name'],
							amount: $paymentData.amount,
							email: $paymentData.email
						})
					});

					return { status: 'success', data };
				}

				if (state === 'error') {
					// Transaction failed
					alert('Failed to make the transaction');
					isLoading.set(false);
					return { status: 'error', data };
				}

				// If state is still processing/pending, retry with exponential backoff
				retryCount++;
				backoffTime *= 2;
				console.log(`Retrying... Attempt ${retryCount} with ${backoffTime}ms delay`);

				await new Promise(resolve => setTimeout(resolve, backoffTime));
			}

			// If max retries are exhausted, show an alert
			alert('Max retries reached. Please try again later.');
			isLoading.set(false);
			return { status: 'error', data: null };

		} catch (err) {
			console.error('Error in payment callback:', err);
			isLoading.set(false);
			return { status: 'error', data: null };
		}
	}
</script>

<div id="donate" class="md:py-1 bg-[#D3F2FC]">
	<div
		class="sm:py-16 py-10 px-4 md:px-48 sm:mt-14 max-w-full mx-auto sm:bg-center relative overflow-hidden bg-cover"
		style="background-image: url('{ImgHeroBg}')"
	>
		<div class="grid md:grid-cols-4 gap-4 relative z-10">
			<div class="col-span-2 flex flex-col justify-center sm:max-w-[72%] items-center">
				<div class="flex flex-col sm:gap-8 gap-4 px-6">
					<div class="font-rubik text-[#0D2561] sm:text-start text-center sm:text-[2.3em] text-[1.9em] font-semibold leading-tight">
						<h3>Your <span class="text-[#0155BD]">DONATION</span></h3>
						<h3>Can Make A Life</h3>
						<h3>Saving Difference</h3>
					</div>

					<div
						class="flex items-{currentScreen === 'details'
							? 'start'
							: 'center'} bg-[#FFBA41] sm:w-80 md:max-w-{currentScreen === 'details' ? '48' : 'full'}"
					>
						<TriangleAlert class="w-{currentScreen === 'details' ? '12' : '14'} h-full p-1" />
						<p class="md:text-[0.61em] text-[0.6em] p-2 text-black-900">
							All donations to JaruratCare Foundation are eligible for 50% tax exemption under
							section 80G of the Income Tax Act.
						</p>
					</div>
				</div>
			</div>
      
			<div
				class=" col-span-2 z-10 font-manrope flex flex-col items-center gap-4 rounded-2xl bg-white shadow overflow-hidden"
			>
				<div class="flex-col item-center px-6 py-2">
					{#if currentScreen != 'success'}
						<h2 class="flex gap-1 font-rubik text-[#0D2561] font-medium text-[1.5em] justify-center py-4">
							Donate<span class="text-[#0155BD] md:text-[#0D2561]"> Today</span>
						</h2>
					{:else}
						<p class="text-[#0D2561] px-4 md:px-6 text-[0.7em] md:text-[0.8em] text-center font-semibold leading-tight py-4">
							Your donation is making a real difference in the fight against cancer.
						</p>
					{/if}
					<div class="w-full z-20">
						{#if currentScreen === 'billing'}
							<BillingOptions
								on:data={(ev) => updatePaymentData(ev.detail)}
								on:submit={() => {
									const resp = billingSchema.safeParse($paymentData);
									if (resp.success) {
										currentScreen = 'details';
									} else {
										alert('Please fill out all required fields.');
									}
								}}
							/>
						{:else if currentScreen === 'details'}
							<PersonalDetails
								selectedAmount={$paymentData.amount}
								selectedPaymentType={$paymentData['payment-type']}
								isLoading={$isLoading}
								on:data={(ev) => updatePaymentData(ev.detail)}
								on:back={handleBack}
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
				<div class="text-gray-500 text-xs font-extralight text-center">
					<h6 class:hidden={currentScreen != 'details'}>3-Step Process</h6>
					<h6 class:hidden={currentScreen != 'success'}>Completed!</h6>
				</div>
				<!-- Progress Status -->
				<div class="w-full h-2 flex items-center justify-center gap-2">
					<div class="w-1/3 h-2 bg-[#78C520]"></div>
					<div
						class="w-1/3 h-2"
						class:bg-[#DBEDED]={currentScreen === 'billing'}
						class:bg-[#78C520]={currentScreen != 'billing'}
					></div>
					<div
						class="w-1/3 h-2"
						class:bg-[#DBEDED]={currentScreen != 'success'}
						class:bg-[#78C520]={currentScreen === 'success'}
					></div>
				</div>
			</div>
		</div>
	</div>
</div>
<SingleWaveDown fill="#D3F2FC" class="absolute w-full sm:-mt-36 hidden sm:flex"/>

<style lang="postcss">
	@media (max-width: 768px) {
		#donate .bg-cover {
			background-image: none !important;
		}
	}
</style>
