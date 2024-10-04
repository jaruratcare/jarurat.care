<script lang="ts">
	import { writable } from 'svelte/store';
	import BillingOptions from './billing-options.svelte';
	import PersonalDetails from './personal-details.svelte';
	import SuccessScreen from './success-screen.svelte';
	import ImgHeroBg from '$lib/assets/donate/hero-img.webp';
	import ImgHeroColorBg from '$lib/assets/donate/hero-color-img.webp';
	import DonateTodayTopBg from '$lib/assets/donate/frame-donate-today.webp';
	import { billingSchema, personalDetailsSchema } from './schema';
	import { TriangleAlert,Check } from 'lucide-svelte';
	import SingleWaveDown from '$lib/svg/single-wavedown.svelte';

	function updatePaymentData(data: Record<string, string | number>) {
		paymentData.set({ ...$paymentData, ...data });
		return $paymentData;
	}

	let paymentData = writable({ amount: 200, 'payment-type': 'one-time' });
	let currentScreen = 'billing';
	// let currentScreen = 'success';
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

<style lang="postcss">
	@media (max-width: 768px) {
		#donate .bg-cover {
			background-image: none !important;
		}
	}
</style>

<div id="donate" class="md:p-1 bg-[#D3F2FC]">
	<div class="py-6 md:py-8 px-1 md:px-14 md:my-16 max-w-[70rem] mx-auto md:rounded-xl md:bg-center relative overflow-hidden sm:bg-none bg-cover"
		style="background-image: url('{currentScreen === 'success' ? ImgHeroColorBg : ImgHeroBg}')">
		<!-- overlay -->
		<div class="bg-[#D3F2FC] sm:bg-[#797e8a]/80 absolute inset-0 {currentScreen === 'success' ? 'hidden':'block'}"></div>

		<div class="grid md:grid-cols-4 gap-4 relative z-10 items-{currentScreen === 'details' ? 'start':'center'}">
			<div class="col-span-2 flex flex-col justify-end md:px-8 max-w-{currentScreen === 'details' ? '[50%]':'[72%]'}">
				<div class="flex flex-col justify-end items-start gap-2">
					<div>
						<h3 class="hidden md:flex font-rubik md:text-white text-[1.3em] md:max-w-{currentScreen === 'details' ? '24':'72'} leading-tight">Help Us Fight Cancer</h3>
						<h3 class="md:hidden flex font-rubik text-[#0D2561] text-[1.3em] md:max-w-{currentScreen === 'details' ? '24':'72'} leading-tight">Help Us</h3>
						<h3 class="md:hidden flex font-rubik text-[#0155BD] text-[1.3em] md:max-w-{currentScreen === 'details' ? '24':'72'} leading-tight">Fight Cancer</h3>
					</div>
					{#if currentScreen != 'details' }
					<p class="text-white text-[0.7em] hidden md:flex font-thin leading-tight max-w-64">
						Jarurat Care is dedicated to providing comprehensive support to cancer patients and
						their caregivers in our local community. Through emotional counseling, mental wellness
						programs, caregiver mentorship, and connections to top medical experts, we aim to
						empower those impacted by cancer. Your donation will directly fund these vital services,
						ensuring no one faces their cancer journey alone.
					</p>
					{/if}
					<div class="flex items-{currentScreen === 'details' ? 'start':'center'} bg-[#ffaf26] w-72 md:max-w-{currentScreen === 'details' ? '48':'72'} border-l-2 border-black">
						<TriangleAlert class="w-{currentScreen === 'details' ? '12':'14'} h-full p-1" />
						<p class="md:text-[0.5em] text-[0.6em] leading-tight p-1 text-black-900">
						  All donations to JaruratCare Foundation are eligible for 50% tax exemption under section
						  80G of the Income Tax Act.
						</p>
					</div>					  
				</div>
			</div>

			<div class="w-[{currentScreen === 'details' ? '550px' : ''}] col-span-2 z-10 font-manrope flex flex-col items-center gap-4 rounded-2xl bg-[#EFF2F5] shadow md:ml-{currentScreen === 'details' ? '10' : '16'}">

				<div style="background-image: url('{DonateTodayTopBg}'); background-position: top; background-repeat: no-repeat; background-size: cover;" class="w-full md:h-[0.9em] h-[0.5em] z-10 rounded-t-2xl overflow-hidden"></div>
				
				<div class="flex-col item-center">
					{#if currentScreen != 'success' }
					<h2 class="flex gap-1 font-rubik text-[#0D2561] font-medium text-[1.5em] justify-center">
						Donate<span class="text-[#0155BD] md:text-[#0D2561]"> Today</span>
					</h2>
					<p class="text-black-900 text-center font-medium text-[0.7em] leading-tight">
						Your donation can make a life-saving difference.
					</p>
					{:else}
					<p class="text-[#0D2561] px-4 md:px-4 text-[0.7em] md:text-[0.9em] text-center font-semibold leading-tight">
						Your donation is making a real difference in the fight against cancer.
					</p>
					{/if}
					<!-- Progress Status -->
					<div class="flex items-center justify-center pt-4">
						<div class="flex items-center">
							<div class="flex font-semibold items-center justify-center w-6 h-6 rounded-full border-2" 
							class:bg-[#ffaf26]={currentScreen === 'details'}
							class:border-[#ffaf26]={currentScreen === 'details'}
							class:bg-[#78C520]={currentScreen === 'success'}
							class:border-[#78C520]={currentScreen === 'success'}
							class:text-white={currentScreen !== 'billing'}
							class:bg-blue-100={currentScreen === 'billing'}
							class:text-blue-700={currentScreen === 'billing'}
							class:border-blue-700={currentScreen === 'billing'}
							>
							{#if currentScreen !== 'billing'} <Check /> {:else} 1 {/if}
						    </div>
							<span class="w-10 h-[0.1em]" class:bg-[#ffaf26]={currentScreen === 'details'} class:bg-gray-300={currentScreen === 'billing'} class:bg-[#78C520]={currentScreen === 'success'}></span>

							<div
							class="flex font-semibold items-center justify-center w-6 h-6 rounded-full border-2" 
							class:bg-gray-300={currentScreen === 'billing'}
							class:text-white={currentScreen === 'success'} 
							class:bg-blue-100={currentScreen === 'details'}
							class:text-blue-700={currentScreen === 'details'}
							class:border-blue-700={currentScreen === 'details'}
							class:bg-[#78C520]={currentScreen === 'success'}
							class:border-[#78C520]={currentScreen === 'success'}
							>
							{#if currentScreen === 'success'} <Check /> {:else} <span class="text-{currentScreen === 'details' ? "blue-700":"gray-600"}">2</span> {/if}
 						    </div>
							
							<span class="w-10 h-[0.1em]" class:bg-[#78C520]={currentScreen === 'success'} class:bg-gray-300={currentScreen !== 'success'}></span>
							<div
							class="flex font-semibold items-center justify-center w-6 h-6 rounded-full border-2"
							class:bg-gray-300={currentScreen != 'success'}
							class:text-white={currentScreen === 'success'} 
							class:bg-[#78C520]={currentScreen === 'success'}
							class:border-[#78C520]={currentScreen === 'success'}
							>
							{#if currentScreen === 'success'} <Check /> {:else} <span class="text-gray-600">3</span> {/if}
						    </div>
					    </div>
				    </div>
			    </div>

			<div class="w-full z-20">
				{#if currentScreen === 'billing' }
					<BillingOptions
					on:data={(ev) => updatePaymentData(ev.detail)}
					on:submit={() => {
						const resp = billingSchema.safeParse($paymentData);
						console.log(resp)
						if (resp.success) {
							currentScreen = 'details';
						}
					}}
					/>
					{:else if currentScreen === 'details'}
					<PersonalDetails
					selectedAmount={$paymentData.amount}
					selectedPaymentType={$paymentData['payment-type']}
					isLoading={$isLoading}
					on:data={(ev) => updatePaymentData(ev.detail)}
					on:submit={async () => {
						console.log("submitted");
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
					<!-- <SuccessScreen  amount={$paymentData.amount} /> -->
				{/if}
			</div>
			</div>
		</div>
	</div>
</div>
<SingleWaveDown fill="#D3F2FC"/>