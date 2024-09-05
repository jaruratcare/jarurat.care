<script>
	import { cn } from '$lib/utils';
	import Button from './ui/button.svelte';
	import Header from './ui/header.svelte';
	import Advocasy from '$lib/svg/advocasy.svelte';
	import Treatment from '$lib/svg/treatment.svelte';
	import Education from '$lib/svg/education.svelte';
	import Food from '$lib/svg/food.svelte';
	import Team from '$lib/svg/team.svelte';
	import ImgBgHowWeSupport from '$lib/svg/bg/how-we-support.svelte';
	import mentorImage from "../assets/mentor.png"

	const cards = [
		{
			title: 'Patient Advocacy',
			description:
				'We stand by your side, helping navigate treatment options and healthcare systems.',
			services: ['Insurance Assistance', 'Legal Support', 'Healthcare Navigation'],
			class: 'ring-[#B0DFF9]',
			icon: Advocasy
		},
		{
			title: 'Access To Treatment',
			description:
				'We help connect you with trusted healthcare providers and guide you through treatment',
			services: ['Individual Counseling', 'Support Groups', '24/7 Helpline'],
			class: 'ring-[#E4CFFF]',
			icon: Treatment
		},
		{
			title: 'Holistic Care Options',
			description:
				'We provide access to complementary therapies like nutrition counseling, stress management, and wellness programs',
			services: ['Nutrition Counseling', 'Stress Management', 'Wellness Programs'],
			class: 'ring-[#96f3d7]',
			icon: Food
		},
		{
			title: 'Educational Resources',
			description: 'We offer information and workshops to help you understand cancer',
			services: ['Informational Workshops', 'Patient Guides', 'Online Learning'],
			class: 'ring-[#FFC1F6]',
			icon: Education
		},
		{
			title: 'Community Connection',
			description:
				'We create a network of support by connecting you with others who are going through similar experiences, so you never feel alone.',
			services: ['Peer Mentorship', 'Online Forums'],
			class: 'ring-[#E4CFFF]',
			icon: Team
		}
	];
</script>

<div class="md:py-16 sm:py-28 flex flex-col relative overflow-hidden bg-cover bg-center bg-[#D3F2FC]  ">
	<div
		class="absolute inset-x-0 top-0 z-0 bg-cover bg-center"
		style="background-image: url('');"
	>
		<div
			class="w-full aspect-square -translate-y-[90%] scale-[4] sm:scale-[1.4] rounded-full bg-[#DCF2FF]"
			style="opacity: 0.75;"
		></div>
	</div>


	<div class="text-center z-10 mt-16 ">
		<h2 class="text-[2rem] text-[#0D2561] font-semibold " style="font-family: Rubik, sans-sarif;" >How we <span class="text-[#0155BD;] ">Support You</span></h2>
		<p class="text-[0.85rem] font-md  " style="font-family: Manrope;" >Comprehensive care and support throughout your cancer journey</p>
	</div>


	<div
		class="max-w-[60rem] w-full mx-auto grid  grid-cols-1 sm:grid-cols-3 gap-4 sm:gap-16 p-4 my-8  "
	>
		{#each cards as card}
			<div class=" relative flex flex-col max-w-[80%] sm:max-w-fit mx-auto">
				<div class="w-full h-[2rem] sm:h-[2.5rem] relative z-10 flex items-center justify-center">
					<div
						class="aspect-square md:w-[7.5rem] sm:w-[5rem] translate-y-4 rounded-full p-[0.1rem] bg-white border border-blue-300"
					>
						<div class="size-full bg-white flex items-center justify-center rounded-full">
							<svelte:component this={card.icon} />
						</div>
					</div>
				</div>

				<div
					class={cn(
						`px-8 sm:px-8 md:py-3 sm:py-12 rounded-xl sm:rounded-xl  bg-white `,
						card.class,
						`flex flex-col items-center gap-2 sm:gap-4 text-center ring relative grow`
					)}
				>
					<div class="flex flex-col gap-2 sm:gap-4 grow size-full mt-16 ">
						<h3
							class=" font-bold md:text-[1.2em] sm:text-[1.3em] leading-[1] text-[#0154BC] "
							style="font-family: Manrope;"
						>
							{card.title}
						</h3>

						<p class="font-manrope px-5 font-medium text-[0.75em] text-[#5F697A]">{card.description}</p>

						<div class="max-w-[80%] mx-auto ">
							{#each card.services as service}
								<div class="w-full flex gap-2 items-center justify-start text-sm">
									<div class="aspect-square w-2 bg-[#0154BC] rounded-full"></div>
									<div class="text-[#0154BC] text-left">{service}</div>
								</div>
							{/each}
						</div>
					</div>
				</div>
			</div>
		{/each}
	</div>

	<div class="text-center">
		<a
		href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer"
		class="inline-block mx-auto underline mr-1 "><Button class="border-2 border-[#0154BC] text-[#0154BC] " >Contact Us</Button></a
	>
	<a
		href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer"
		class="inline-block mx-auto underline ml-1 "><Button class="border-2 border-[#0154BC] bg-[#0154BC] " >Seek Support</Button></a
	>
	</div>

	<div class="w-full h-96 flex items-center  rounded-3xl mt-20 z-10 bg-center bg-cover " style="background-image: url({mentorImage})" >

		<div class="md:w-1/4 w-1/2 flex flex-col  text-white md:ml-48  " style="font-family: Rubik;"  >
			<h1 class="text-2xl  " >Find a Mentor for Your <span class="text-yellow-500 ">Cancer</span> Journey</h1>
			<p class="font-thin my-3 md:block hidden ">You Don’t Have to Face Cancer Alone</p>
			<p class="text-[0.8rem] font-thin mb-5 md:block hidden ">Connect with a compassionate mentor who understands your challenges and can offer personal support and guidance.</p>

			<a href="/">
				<Button class="bg-yellow-500" >Get a Mentor</Button>
			</a>
		</div>
	</div>
	 
</div>

<div class="rounded-xl"></div>
