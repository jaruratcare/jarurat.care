<script>
	import { MailIcon } from 'lucide-svelte';
	import PaymentSuccessIcon from '$lib/svg/payment-success-icon.svelte';

	export let txnId;
	export let amount;
</script>

<div class="font-manrope flex flex-col items-center gap-8 rounded-2xl bg-[#EFF2F5] shadow border overflow-hidden">
	<div class="rounded-t-3xl bg-white w-full flex flex-col justofy-center items-center pt-8 pb-2">
		<PaymentSuccessIcon class="size-[4rem]" />

		<h3 class="font-rubik text-[1.4em] text-[#78C520] mt-2">Donation Successful</h3>

		<div
			class="my-4 border w-[90%] mx-auto leading-tight text-center p-4 flex flex-col gap-2 font-[0.8em] text-[#0d2561] rounded-xl"
		>
			<p>Your donation of ₹{amount} has been successfully processed.</p>
			<p>Transaction ID : <strong>{txnId}</strong></p>
			<!-- <p>Transaction ID : <strong>5667</strong></p> -->

			<p
				class="bg-[#D3F2FC] flex items-center gap-2 text-[0.7em] self-center px-2 py-1 rounded-r-sm mt-2 text-[#0155BD] border-l-2 border-[#0155BD]"
			>
				<MailIcon />
				A receipt has been sent to your email
			</p>
		</div>

		<div class="flex gap-4 items-center text-[0.6em] pb-6 pt-3 text-gray-400 font-rubik">
			<a href="/">About Us</a>
			<a href="/">Advisory Board</a>
			<a href="/">Need Help?</a>
		</div>
	</div>
</div>
