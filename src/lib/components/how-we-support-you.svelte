<script>
	import { onMount } from 'svelte';
	import Button from './ui/button.svelte';
	import Header from './ui/header.svelte';
	import Advocasy from '$lib/svg/advocasy.svelte';
	import Treatment from '$lib/svg/treatment.svelte';
	import Education from '$lib/svg/education.svelte';
	import Food from '$lib/svg/food.svelte';
	import Team from '$lib/svg/team.svelte';
	import Button2 from './ui/tbutton.svelte';
	import CButton from './ui/cardbutton.svelte';
	import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
	import { ChevronLeft, ChevronRight } from 'lucide-svelte';
	import doctorimg from '$lib/assets/landing-page/howwesupportyou/Doctor.jpeg';
	import caregiverimg from '$lib/assets/landing-page/howwesupportyou/Caregiver.jpeg';
    import pngimage from '$lib/assets/landing-page/howwesupportyou/womanslide.png';
	import manpngimage from '$lib/assets/landing-page/howwesupportyou/manslide.png';

	let perPage = 3.5; // Default value for large screens
  
	const cards = [
	  {
		title: 'Patient Advocacy',
		description: 'We stand by your side, helping navigate treatment options and healthcare systems.',
		icon: Advocasy
	  },
	  {
		title: 'Access To Treatment',
		description: 'We stand by your side, helping navigate treatment options and healthcare systems.',
		icon: Treatment
	  },
	  {
		title: 'Holistic Care Options',
		description: 'We stand by your side, helping navigate treatment options and healthcare systems.',
		icon: Food
	  },
	  {
		title: 'Educational Resources',
		description: 'We offer information and workshops to help you understand cancer',
		icon: Education
	  },
	  {
		title: 'Community Connection',
		description: 'We create a network of support by connecting you with others who are going through similar experiences, so you never feel alone.',
		icon: Team
	  }
	];
  
	// Run only on the client
	onMount(() => {
	  // Update `perPage` based on the window size
	  perPage = window.innerWidth < 600 ? 1 : 3.5;
	});
  </script>
  
  <div class="py-12 sm:py-28 flex flex-col w-full items-center justify-center h-screen relative overflow-hidden bg-cover bg-center">
	<Header
	  class="z-10"
	  title="How we"
	  spantitle="Support You"
	  spantitleColor="#0155BD" 
	/>

	<div class="relative flex items-center justify-center w-fit h-screen"> <!-- Changed to relative for absolute positioning -->
		<Splide
		  hasTrack={false}
		  aria-label="Carousel"
		  class="max-w-[80rem] mx-auto mt-4"
		  options={{
			perPage, // Now perPage is dynamic and changes after `onMount`
			type: 'loop',
			gap: '4rem',
			pagination: false
		  }}
		>
		  <!-- Custom navigation arrows -->
		  <div class="splide__arrows flex items-center justify-end gap-2 mb-2">
			<Button class="splide__arrow splide__arrow--prev size-12 p-0 items-center">
			  <ChevronLeft class="w-full" />
			</Button>
			<Button class="splide__arrow splide__arrow--next size-12 p-0 flex items-center">
			  <ChevronRight class="w-full" />
			</Button>
		  </div>
	  
		  <!-- Custom wrapper for track and slides -->
		  <div class="custom-wrapper ml-16">
			<SplideTrack>
			  {#each cards as card}
				<SplideSlide class="p-1">
				  <div class="w-fit h-full bg-white rounded-[10px] flex-col justify-start items-center gap-2.5 inline-flex">
					<div class="w-full h-[2rem] sm:h-[2.5rem] relative z-10 flex items-center justify-center">
					  <div class="aspect-square w-[6rem] sm:w-[6rem] translate-y-1/4 rounded-full p-[0.1rem] bg-white border">
						<div class="size-full bg-white flex items-center justify-center rounded-full">
						  <svelte:component this={card.icon} />
						</div>
					  </div>
					</div>
	  
					<div class="px-8 sm:px-8 py-6 sm:py-12 rounded-xl sm:rounded-2xl ring bg-white flex flex-col items-center gap-2 sm:gap-4 text-center relative grow">
					  <div class="flex flex-col gap-2 sm:gap-4 grow mt-6 size-full">
						<h3 class="font-manrope font-bold text-[1.1em] sm:text-[1.3em] leading-[1]">
						  {card.title}
						</h3>
						<p class="font-manrope font-medium text-[0.9em]">
						  {card.description}
						</p>
					  </div>
					</div>
				  </div>
				</SplideSlide>
			  {/each}
			</SplideTrack>
		  </div>
		</Splide>
	  
		<!-- Gradient div in the right corner -->
		<div class="absolute top-0 right-0 w-[10rem] h-[900px] bg-gradient-to-l from-[rgba(255,255,255,0.3)] to-[rgba(255,255,255,0.7)] flex-col justify-center items-center inline-flex">
		  <!-- Add any additional content or styles for this div here -->
		</div>
	  </div>
  
	<div class="flex justify-center mt-6">
	  <div class="btn">
		<a 
		  href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer"
		  class="inline-block p-2 mx-auto underline">
		  <Button2 color='#0153BC'>Contact Us</Button2>
		</a>
		<a
		  href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer"
		  class="inline-block p-2 mx-auto underline">
		  <Button>Seek Support</Button>
		</a>
	  </div>
	</div>  
  </div>

  <div class="services flex flex-col lg:flex-row">
	<!-- First part -->
	<div class="w-full lg:w-1/2 h-auto px-4 py-10 lg:py-36 bg-[#2cbfe2]/60 flex-col justify-start items-center gap-2.5 relative">
		<div class="max-w-full lg:w-[800px] h-auto px-[30px] lg:px-[60px] pt-8 pb-6 rounded-[10px] shadow flex-col justify-start items-start gap-2.5 relative z-10 overflow-hidden bg-transition"
			 style="background-image: linear-gradient(to left, rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.7)), url({doctorimg}); background-size: cover; background-position: center;">
		  <div class="flex flex-col gap-7 relative z-20">
			<div>
			  <div class="text-[#e7ebf1] text-[30px] lg:text-[40px] font-semibold font-['Rubik']">
				Find a Mentor for Your <span class="text-[#ffba41]">Cancer</span> Journey
			  </div>
			  <div class="text-[#e7ebf1] text-xl lg:text-2xl font-semibold font-['Manrope']">
				You Don’t Have to Face Cancer Alone
			  </div>
			</div>
			<div class="w-full lg:w-[420px] text-[#e7ebf1] text-lg lg:text-xl font-light font-['Manrope'] leading-7">
			  Connect with a compassionate mentor who understands your challenges and can offer personal support and guidance.
			</div>
			<div class="inline-flex gap-2.5">
			  <CButton primaryColor='#ffba41' secondaryColor = '#2cbfe2'  class="px-10 py-4 rounded-full shadow border border-[#fffaf3] text-[#f8fff2] bg-blue-500 text-xl lg:text-2xl font-semibold transition transform duration-300 ease-in-out">
				Get a Mentor
			  </CButton>
			</div>
		  </div>
		  <!-- The sliding PNG -->
		  <div class="sliding-png absolute top-32 right-[-300px] w-[40%] h-full  bg-no-repeat  transition-all duration-500 ease-in-out"
         style="background-image: url({pngimage});"></div>
		</div>
		
	  </div>
  
	<!-- Second part -->
	<div class="w-full lg:w-1/2 h-auto px-4 py-10 lg:py-36 bg-[#2cbfe2] bg-custom flex-col justify-center items-center gap-2.5">
		<div class="max-w-full lg:w-[800px] h-auto px-[30px] lg:px-[60px] pt-8 pb-6 rounded-[10px] shadow flex-col justify-start items-start gap-2.5 relative z-10 overflow-hidden bg-transition"
			 style="background-image: linear-gradient(to left, rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.7)), url({caregiverimg}); background-size: cover; background-position: center;">
		  
		  <div class="flex flex-col gap-7">
			<div>
			  <div class="text-[#e7ebf1] text-[30px] lg:text-[40px] font-semibold font-['Rubik']">
				Connect with a <span class="caregiver text-[#2cbfe2]">Caregiver</span> for Your Cancer Journey
			  </div>
			  <div class="text-[#e7ebf1] text-xl lg:text-2xl font-semibold font-['Manrope']">
				You’re Not Alone in This Fight
			  </div>
			</div>
			<div class="w-full lg:w-[420px] text-[#e7ebf1] text-lg lg:text-xl font-light font-['Manrope'] leading-7">
			  Connect with a compassionate mentor who understands your challenges and can offer personal support and guidance.
			</div>
			<div class="inline-flex gap-2.5">
			  <CButton primaryColor='#ffba41' secondaryColor='#2cbfe2' class="px-10 py-4 rounded-full shadow border border-[#fffaf3] text-white text-xl lg:text-2xl font-semibold transition transform duration-300 ease-in-out">
				Get a Caregiver
			  </CButton>
			</div>
		  </div>
	  
		  <!-- The sliding PNG -->
		  <div class="sliding-png absolute top-32 right-[-300px] w-[40%] h-full bg-no-repeat transition-all duration-500 ease-in-out"
			   style="background-image: url({manpngimage});"></div>
		</div>
	  </div>
	  
	  
</div>

  
  <style lang="css">
	.splide__arrows {
	  position: absolute;
	  top: 50%;
	  transform: translateY(-50%);
	  width: 100%;
	  display: flex;
	  justify-content: space-between;
	  z-index: 10;
	}
  
	.splide__arrow {
	  background: transparent;
	  border: none;
	  cursor: pointer;
	  padding: 0.5rem;
	  color: #0154BC;
	}
  
	.splide__track {
	  overflow: visible;
	}

	.bg-transition {
	transition: background-image 0.5s ease-in-out;
}

/* On hover, change the background to a solid color and slide in the PNG */
.bg-transition:hover   {
	background:#0154BC !important;
}

/* Sliding PNG from right to left */
.bg-transition:hover .sliding-png {
	right: 0;
}

.sliding-png {
	transition: right 0.5s ease-in-out;
}

.bg-custom:hover .caregiver {
    color: #ffba41; /* Color on hover */
    transition: color 0.0s ease; /* Smooth transition */
}
  </style>
  