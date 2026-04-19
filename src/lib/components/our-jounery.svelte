<script>
	import SingleWave from '$lib/svg/single-wave.svelte';
	import LogoBlueface from '$lib/svg/about/logo-blueface.svelte';

	import RekhaJoshi from '$lib/assets/rekha joshi.png';
	import cofounders from '$lib/assets/cofounders.png';
	import podcast from '$lib/assets/prodcast.png';

	import GrpOfPeople from '$lib/svg/about/grp-of-people.svelte';
	import Reaserch from '$lib/svg/about/reaserch.svelte';
	import LaunchRocket from '$lib/svg/about/launch-rocket.svelte';
	import BlubIdea from '$lib/svg/about/blub-idea.svelte';

	const timeline = [
		{
			date: "2023, December",
			image: RekhaJoshi,
			text: "Our story commenced in December 2023 when we lost our mother to Cholangiocarcinoma after a seven month battle."
		},
		{
			date: "2024",
			image: cofounders,
			items: [
				{ icon: GrpOfPeople, text: "Led by two co-founders with firsthand experience." },
				{ icon: Reaserch, text: "Conducted extensive research on cancer patient support needs." }
			]
		},
		{
			date: "2024",
			image: podcast,
			items: [
				{ icon: BlubIdea, text: "Developed a strategic vision for cancer support initiatives." },
				{ icon: LaunchRocket, text: "Launched community engagement programs." }
			]
		}
	];
</script>

<div>
	<SingleWave fill="#0155BD" />

	<section class="bg-primaryBlue py-16 px-6">
		<div class="max-w-6xl mx-auto">

			<!-- Header -->
			<div class="flex flex-col items-center gap-4 mb-16">
				<div class="w-14 md:w-20 bg-white rounded-full p-2 md:p-4">
					<LogoBlueface />
				</div>

				<h2 class="text-white text-3xl font-extrabold text-center">
					Our Journey
				</h2>
			</div>

			<!-- Timeline -->
			<div class="relative">

				<!-- vertical line -->
				<div class="absolute left-4 md:left-1/2 top-0 bottom-0 w-[3px] bg-white/40"></div>

				{#each timeline as event, i}

					<div class="relative mb-10 md:mb-20 md:grid md:grid-cols-2 md:gap-12 items-center">

						<!-- timeline dot -->
						<div class="absolute left-4 md:left-1/2 -translate-x-1/2 w-4 h-4 bg-[#FFBA41] rounded-full"></div>

						<!-- Image block -->
						<div class="pl-10 md:pl-0 {i % 2 ? 'md:order-2' : ''}">
							<div class="relative w-full max-w-md">

								<!-- rotated background card -->
								<div class="absolute inset-0 bg-[#0D2460] rounded-2xl -rotate-2 translate-x-2 translate-y-2"></div>

								<!-- image -->
								<img
									src={event.image}
									alt=""
									class="relative rounded-2xl w-full object-cover shadow-lg"
								/>

							</div>
						</div>

						<!-- Text block -->
						<div class="pl-10 md:pl-0 pt-4 md:pt-0 text-white {i % 2 ? 'md:order-1 md:text-right' : ''}">
							<h3 class="font-extrabold text-lg mb-4">
								{event.date}
							</h3>

							{#if event.text}
								<p class="text-sm leading-relaxed">
									{event.text}
								</p>
							{/if}

							{#if event.items}
								<div class="flex flex-col gap-4 mt-4">
									{#each event.items as item}
										<div class="flex gap-3 items-start {i % 2 ? 'md:flex-row-reverse' : ''}">
											<span class="w-8 h-8">
												<svelte:component this={item.icon} />
											</span>

											<p class="text-sm">
												{item.text}
											</p>
										</div>
									{/each}
								</div>
							{/if}
						</div>

					</div>

				{/each}

			</div>

		</div>
	</section>
</div>