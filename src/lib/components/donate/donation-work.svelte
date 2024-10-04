<script>
	import { TriangleAlert } from 'lucide-svelte';
    import { Splide, SplideSlide } from '@splidejs/svelte-splide';
	import DonateTodayTopBg from '$lib/assets/donate/frame-donate-today.webp';
	import QRcodeMobile from '$lib/assets/donate/QR_code_for_mobile.webp';
	import SingleWaveUp from '$lib/svg/single-waveup.svelte';
	import Button from '../ui/button.svelte';
	import JoinTeam from '$lib/svg/donate/join-team.svelte';
	import BecomeCaregiver from '$lib/svg/donate/become-caregiver.svelte';
	import BecomeMentor from '$lib/svg/donate/become-mentor.svelte';
	import SingleWavedown from '$lib/svg/single-wavedown.svelte';
	import WaveGradient from '$lib/svg/donate/wave-gradient.svelte';
	import Elipseleft from '$lib/svg/donate/elipseleft.svelte';
	import Elipseright from '$lib/svg/donate/elipseright.svelte';

	let donationOptions = [
		{ amount: "₹3,000 +", title: "Community Outreach & Education" },
		{ amount: "₹2,000 +", title: "Holistic Wellness Programs" },
		{ amount: "₹5,000 +", title: "Patient Comfort & Care Packages" },
		{ amount: "₹5,000 +", title: "Patient Treatment & Care" },
		{ amount: "₹2,000 +", title: "Counseling & Emotional Support" },
		{ amount: "₹3,000 +", title: "Community Outreach & Education" }
	];

</script>
<div class="overflow-hidden">
	<div class="md:hidden font-manrope flex flex-col items-center px-3 py-16">
		<div style="background-image: url('{DonateTodayTopBg}'); background-position: top; background-repeat: no-repeat; background-size: cover;" class="w-full md:h-[0.9em] h-[0.5em] z-10 rounded-t-2xl overflow-hidden"></div>
		<div class="flex flex-col item-center shadow-md rounded-lg p-4 gap-3">
			<h2 class="font-rubik text-[#0D2561] font-medium text-[1.5em] leading-tight py-2">
				Donate<span class="text-[#0155BD]"><br>Through UPI</span>
			</h2>
			<p class="text-[#0D2561] text-[0.8em] font-semibold leading-tight">
				Make a difference today! Your UPI donation to Zarurat Care can help provide essential resources and services to those in need. Every rupee counts.
			</p>
			<div class="flex items-center justify-start bg-[#FDE3A7] border-l-2 border-[#A15819] p-1 max-w-fit">
				<TriangleAlert class="h-full w-8 px-1 text-[#A15819]"/>
				<h1 class="text-sm text-[#A15819] font-medium px-1 pr-6">
					UPI Donation limit ₹20k
				</h1>
			</div>
			<div class="flex flex-col items-center justify-center gap-3 py-4">
				<img src="{QRcodeMobile}" alt="QR Code">
				<h1 class="font-rubik text-[#0D2561] text-[0.8em]">Scan and pay with any UPI app</h1>
				<div class="flex items-center justify-between gap-2">
					<div class="h-7 w-7 rounded-full bg-[#D3F2FC]"></div>
					<div class="h-7 w-7 rounded-full bg-[#D3F2FC]"></div>
					<div class="h-7 w-7 rounded-full bg-[#D3F2FC]"></div>
				</div>
			</div>
		</div>
	</div>

	<div class="sm:mt-14 mt-8">
		<SingleWaveUp fill="#0155BD"/>
		<div class="block md:flex md:justify-between bg-[#0155BD] -mt-1 px-8 md:px-20 relative overflow-hidden">
			<WaveGradient/>
			<div class="flex flex-col items-center justify-center md:items-start z-20">
				<div>
					<h2 class="text-3xl text-center md:max-w-[50em] max-w-[8em] font-bold text-white">Your <span class="text-[#ffba41]">Donation</span> at Work</h2>
					<p class="text-xs mt-2 max-w-[22em] text-center md:text-start text-white font-extralight">
						Your donation isn't just money—it's hope. It means a cancer patient gets the treatment they need, the support they deserve, and a chance at life. You're helping real people, real families, facing their toughest battles. Together, we can make a difference.
					</p>
				</div>
				<div class="md:mt-8 hidden md:flex">
					<Button class="custom-button z-20 ">
						Donate Now
					</Button>
				</div>
			</div>

			<div class="flex md:w-[59%] items-center justify-center mt-6 md:mt-0">
				<div class="grid grid-cols-2 grid-rows-2 md:grid-cols-3 md:grid-rows-2 gap-1 md:gap-[1.2rem]">
					{#each donationOptions as option}
					<div class="bg-gradient-to-br from-[#00C8F4]/20 to-white/70 p-[0.2rem] rounded-xl backdrop-blur-lg w-32 h-20 md:w-[100%] md:h-[100%]">
						<div class="grow md:p-4 p-1 flex flex-col gap-1 md:gap-2 ">
							<h3 class="md:text-[2vw] font-bold md:bg-[#D3F2FC] text-white md:text-[#0D2561] md:py-2 md:px-4 text-center md:text-start rounded-full">{option.amount}</h3>
							<p class="font-rubik font-[200] text-[0.7em] md:text-xs text-white leading-[1.2] text-center md:text-start md:mt-2">{option.title}</p>
						</div>
					</div>
					{/each}
				</div>
			</div>
			
			<div class="mt-8 md:hidden flex items-center justify-center">
				<Button class="custom-button z-20 px-6 py-2">
					Seek Support
				</Button>
			</div>

		</div>
	</div>

	<div class="sm:pt-24 pt-8 -mt-1 bg-[#0155BD] px-4  py-16 relative overflow-hidden md:rounded-b-lg">
		<Elipseleft/>
		<div class="z-20 w-[100%]">
			<h2 class="hidden md:block font-rubik text-white font-medium text-center text-[1.5em] leading-tight py-2">
				Get <span class="text-[#ffba41]">Involved</span>
			</h2>
			<div class="md:grid md:grid-flow-col md:grid-col-1 gap-5 px-12">

				<div class="box bg-white shadow-lg rounded-lg mt-6 z-20">
					<div class="rounded-lg">
						<div class="">
							<div class="bg-[#D3F2FC] md:bg-white rounded-t-lg py-4">
								<div class="rounded-full w-24 h-24 bg-white mx-auto flex justify-center items-center p-2">
									<JoinTeam />
								</div>
							</div>
							<SingleWavedown fill="#D3F2FC" class="flex md:hidden"/>
						</div>
						<div class="bg-white text-[#0D2460] rounded-b-lg pb-4 flex flex-col items-center justify-center gap-2">
							<h1 class="text-2xl md:px-24 text-center font-semibold font-playfair-display">Join Our Team</h1>
							<p class="text-center px-12 text-[0.8em]">Our NGO is here to help you through your cancer journey. Reach out to us today.</p>
							<Button class="custom-button z-10 md:py-2 md:px-6 px-4 py-1 text-[0.9em]">
								Apply Now
							</Button>
						</div>
					</div>
				</div>
				<div class="box bg-white shadow-lg rounded-lg mt-6 z-20">
					<div class="rounded-lg">
						<div class="">
							<div class="bg-[#D3F2FC] md:bg-white rounded-t-lg py-4">
								<div class="rounded-full w-24 h-24 bg-white mx-auto flex justify-center items-center p-2">
									<BecomeCaregiver />
								</div>
							</div>
							<SingleWavedown fill="#D3F2FC" class="flex md:hidden"/>
						</div>
						<div class="bg-white text-[#0D2460] rounded-b-lg pb-4 flex flex-col items-center justify-center gap-2">
							<h1 class="text-2xl md:px-24 text-center font-semibold font-playfair-display">Become a Caregiver</h1>
							<p class="text-center px-12 text-[0.8em]">Our NGO is here to help you through your cancer journey. Reach out to us today.</p>
							<Button class="custom-button z-10 md:py-2 md:px-6 px-4 py-1 text-[0.9em]">
								Apply Now
							</Button>
						</div>
					</div>
				</div>
				<div class="box bg-white shadow-lg rounded-lg mt-6 z-20">
					<div class="rounded-lg">
						<div class="">
							<div class="bg-[#D3F2FC] md:bg-white rounded-t-lg py-4">
								<div class="rounded-full w-24 h-24 bg-white mx-auto flex justify-center items-center p-2">
									<BecomeMentor />
								</div>
							</div>
							<SingleWavedown fill="#D3F2FC" class="flex md:hidden"/>
						</div>
						<div class="bg-white text-[#0D2460] rounded-b-lg pb-4 flex flex-col items-center justify-center gap-2">
							<h1 class="text-2xl md:px-24 text-center font-semibold font-playfair-display">Become a Mentor</h1>
							<p class="text-center px-12 text-[0.8em]">Our NGO is here to help you through your cancer journey. Reach out to us today.</p>
							<Button class="custom-button z-10 md:py-2 md:px-6 px-4 py-1 text-[0.9em]">
								Apply Now
							</Button>
						</div>
					</div>
				</div>

			</div>
		</div>
		<Elipseright/>
	</div>
	<SingleWavedown class="md:hidden flex -m-1" fill="#0155BD"/>
</div>