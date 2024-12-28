<script lang="ts">
	import Button from '../ui/button.svelte';
	import FaqCard from '../faqs/faq-card.svelte';
	import DonatePageFAQs from '$lib/data/donate/faqs-donate-page.json';

	const columnOne = DonatePageFAQs.slice(0, DonatePageFAQs.length / 2);
	const columnTwo = DonatePageFAQs.slice(DonatePageFAQs.length / 2);

</script>

<div class="bg-white sm:py-16 sm:px-8 px-2 py-4 md:px-40">
	<div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 font-rubik">
		<h2 class="text-3xl text-center sm:max-w-[50em] max-w-full text-[#0D2561]">
			Frequently <span class="sm:text-[#FFBA41] text-[#0D2561]">Asked</span> <span class="text-[#FFBA41]">Questions</span>
		</h2>
		<h2 class="sm:text-lg text-xs text-center text-gray-600 sm:py-0 py-2">
			Look through some of our frequently answered questions
		</h2>

		<div class="grid grid-cols-1 gap-x-12 gap-y-4">
			<div class="mx-auto flex flex-col sm:flex-row sm:gap-16 gap-2 sm:my-8 my-4">
				<div class="grow flex flex-col sm:gap-4 gap-2">
					{#each columnOne as faq}
						<FaqCard question={faq.question} answer={faq.answer} />
					{/each}
				</div>
				<div class="grow flex flex-col sm:gap-4 gap-2">
					{#each columnOne as faq}
						<FaqCard question={faq.question} answer={faq.answer} />
					{/each}
				</div>
			</div>
		</div>

		<div class="sm:py-8 py-2 flex flex-col justify-center items-center">
			<h2 class="text-lg text-center sm:max-w-[50em] max-w-[12em] text-[#0D2561]">
				Still Have Questions?
			</h2>
			<p class="text-xs text-center font-sans font-semibold text-gray-500">
				Feel free to contact us
			</p>
			<a
				href="mailto:jaruratcare@gmail.com"
				class="flex justify-center items-center mt-4"
			>
				<Button class="custom-button z-20 font-sans">Contact Us</Button>
			</a>
		</div>
	</div>
</div>
