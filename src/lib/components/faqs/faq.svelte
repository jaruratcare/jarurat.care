<script>
	import Header from '../ui/header.svelte';
	import AnimatedGradientMesh from '$lib/svg/animated-gradient-mesh.svelte';
	import FaqCard from './faq-card.svelte';
	import Button from '../ui/button.svelte';
	import { t } from "$lib/translations/translations.js";

	const faqs = [
		{
			question: $t("home.faqs.question1"),
			answer: $t("home.faqs.answer1")
		},
		{
			question: $t("home.faqs.question2"),
			answer: $t("home.faqs.answer2")
		},
		{
			question: $t("home.faqs.question3"),
			answer: $t("home.faqs.answer3")
		},
		{
			question: $t("home.faqs.question4"),
			answer: $t("home.faqs.answer4")
		},
		{
			question: $t("home.faqs.question5"),
			answer: $t("home.faqs.answer5")
		},
		{
			question: $t("home.faqs.question6"),
			answer: $t("home.faqs.answer6")
		},
		{
			question: $t("home.faqs.question3"),
			answer: $t("home.faqs.answer3")
		},
		{
			question: $t("home.faqs.question4"),
			answer: $t("home.faqs.answer4")
		}
	];

	const columnOne = faqs.slice(0, faqs.length / 2);
	const columnTwo = faqs.slice(faqs.length / 2);
</script>

<div class="py-16 sm:py-32 px-4 relative">
	<div class="absolute inset-0 overflow-hidden -z-10">
		<AnimatedGradientMesh />
	</div>

	<Header
		title={$t("home.faqs.title")}
		subtitle={$t("home.faqs.description")}
	/>

	<div class="max-w-[50rem] mx-auto flex flex-col sm:flex-row sm:gap-4 my-8">
		<div class="grow flex flex-col sm:gap-4">
			{#each columnOne as faq}
				<FaqCard question={faq.question} answer={faq.answer} />
			{/each}
		</div>
		<div class="grow flex flex-col sm:gap-4">
			{#each columnTwo as faq}
				<FaqCard question={faq.question} answer={faq.answer} />
			{/each}
		</div>
	</div>

	<Header title={$t("home.faqs.stillHaveQuestions")} subtitle={$t("home.faqs.contactUs")} class="mt-16" />
	<Button class="mt-8 block mx-auto">{$t("home.faqs.buttonText")}</Button>
</div>
