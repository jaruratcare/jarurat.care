<script>
	import img from '../assets/landing-page/page2/handglow.png';
	import Contact from '../components/for-personalized-disease.svelte';
	import Button from './ui/button.svelte';
	import Button1 from './ui/tbutton.svelte';
	import IndiamapSvg from '$lib/svg/indiamap-svg.svelte';
	import Header from '$lib/components/ui/header.svelte'
	let isHovered = false;
</script>

<Contact/>

<div class="w-full h-auto bg-[#0c2461] flex justify-center items-center px-4 sm:px-8 md:px-16 py-10">
	<div class="w-full max-w-[1200px] h-auto flex flex-col md:flex-row justify-between items-center gap-8 md:gap-[272px] mt-10">
		<div 
			role="button"
			tabindex="0"
			class="relative w-[250px] sm:w-[300px] md:w-[378px] h-[280px] sm:h-[350px] md:h-[419px]"
			on:mouseenter={() => isHovered = true}
			on:mouseleave={() => isHovered = false}
			on:keydown={(e) => { if (e.key === 'Enter') isHovered = !isHovered }}
		>
			<div class="absolute w-full h-full">
				<div class={isHovered ? 'glow w-auto h-auto sm:w-[202px] sm:h-[202px] left-8 top-8 absolute bg-white rounded-full opacity-60 blur-[80px]' : 'hidden'}></div>
				<img class="absolute w-full h-fit origin-top-left rotate-[3.91deg]" src={img} alt="loading" />
			</div>
		</div>
		<div class="flex flex-col justify-center items-start gap-8 md:gap-14 px-4 md:px-0">
			<div class="w-full max-w-[677px]">
				<span class="text-[#e7ebf1] text-lg md:text-2xl font-normal font-['Manrope'] leading-relaxed">At </span>
				<span class="text-[#ffba41] text-[24px] sm:text-[32px] md:text-[40.47px] font-semibold font-['Manrope'] leading-tight">Jarurat Care</span>
				<span class="text-[#e7ebf1] text-lg md:text-2xl font-normal font-['Manrope'] leading-relaxed">
					, we believe no one should face cancer alone. We provide vital medical support, emotional care, and financial assistance to help patients and their families navigate this difficult journey. Together, we bring hope, healing, and a compassionate community to those who need it most.
				</span>
			</div>
			<div class="flex  sm:flex-row justify-center items-center gap-4 sm:gap-5">
				<a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
					<Button1 class="transition transform hover:scale-110 duration-300 ease-in-out">Volunteer</Button1>
				</a>
				<a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
					<Button class="bg-[#0155BD] transition transform hover:scale-110 duration-300 ease-in-out">Seek Support</Button>
				</a>
			</div>
		</div>
	</div>
</div>

<div class="w-full h-screen bg-gradient-to-b from-[#0d2561] to-[#0c235b] relative flex flex-col items-center px-4">
    <!-- Map Positioned in the Right Corner -->
	<div class="map absolute bottom-0 right-4 transform translate-y-1/3 hidden sm:block">
		<IndiamapSvg />
	</div>
    <!-- Header Text -->
    <div class="w-full mt-20 relative text-center">
        <Header color='white' title='Our' spantitle='Impact' />
    </div>

    <!-- Stats Cards -->
    <div class="flex flex-row flex-wrap sm:w-[90%] sm:flex-row sm:flex-wrap justify-between gap-1 sm:gap-4 absolute top-1/2 transform -translate-y-1/2  p-4">
		<!-- Mentor Card -->
		<div class="min-w-[150px] sm:min-w-[200px] h-[120px] p-4 sm:p-5 rounded-[15px] flex flex-col justify-center items-center sm:items-start gap-4 ">
			<div class="flex items-center">
				<div class="w-auto text-[#2cbfe2] text-[28px] sm:text-[36px] md:text-[48px] font-medium font-['Manrope']">0</div>
			</div>
			<div class="text-white text-sm sm:text-lg md:text-xl font-normal font-['Rubik']">Mentor</div>
		</div>
	
		<!-- Doctors Card -->
		<div class="min-w-[150px] sm:min-w-[200px] h-[120px] p-4 sm:p-5 rounded-[15px] flex flex-col justify-center items-center sm:items-start gap-4 ">
			<div class="flex items-center">
				<div class="w-auto text-[#2cbfe2] text-[28px] sm:text-[36px] md:text-[48px] font-medium font-['Manrope']">0</div>
			</div>
			<div class="text-white text-sm sm:text-lg md:text-xl font-normal font-['Rubik']">Doctors</div>
		</div>
	
		<!-- People Card -->
		<div class="min-w-[150px] sm:min-w-[200px] h-[120px] p-4 sm:p-5 rounded-[15px] flex flex-col justify-center items-center sm:items-start gap-4 ">
			<div class="flex items-center">
				<div class="w-auto text-[#2cbfe2] text-[28px] sm:text-[36px] md:text-[48px] font-medium font-['Manrope']">0</div>
			</div>
			<div class="text-white text-sm sm:text-lg md:text-xl font-normal font-['Rubik']">People</div>
		</div>
	
		<!-- Early Treatments Initiated Card -->
		<div class="sm:min-w-[150px]  h-[120px] p-4 sm:p-5 rounded-[15px] flex flex-col justify-center items-center sm:items-start gap-4">
			<div class="flex items-center">
				<div class="text-[#2cbfe2] text-[28px] sm:text-[36px] md:text-[48px] font-semibold font-['Manrope']">
					0%
				</div>
			</div>
			<div class="text-white text-xs sm:text-sm md:text-lg font-normal font-['Rubik'] text-center sm:text-left">
				Early Treatments Initiated
			</div>
		</div>
		
	</div>
	
</div>


<style>
	.glow {
		transition: opacity 0.3s ease;
	}
</style>
