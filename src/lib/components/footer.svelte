<script>
	import LogoSquare from '$lib/svg/logo-square.svelte';
	import { ArrowUp, Instagram, Twitter, Facebook, Linkedin } from 'lucide-svelte';
	import Button from './ui/button.svelte';
	import Icon from '@iconify/svelte';
	import instagramIcon from '@iconify-icons/mdi/instagram';
	import twitterIcon from '@iconify-icons/mdi/twitter';
	import facebookIcon from '@iconify-icons/mdi/facebook';
</script>

<div class="bg-[#0D2561] text-white p-4 sm:p-12 flex flex-col gap-4">
	<div class="md:flex md:flex-row flex-col items-center md:px-8">
		<div class="flex flex-row items-center py-4">
			<LogoSquare class="max-w-[2.5em] sm:max-w-[3.5em] w-full" />
			<div class="flex flex-col gap-1 leading-[1] text-[1.2em] md:text-[2em] ml-4">
				<span class="font-semibold">Jarurat Care Foundation</span>
				<small class="text-[0.6em] text-center md:text-start text-[#BFE9E8]">Jaisi Jarurat, Vaisi Care</small>
			</div>
		</div>

		<hr class="mx-26 border-gray-300" />

		<div class="relative z-10 text-[0.8em] md:text-[0.9em] gap-2 md:flex flex flex-row items-center justify-center">
			<a href="/donate"><Button class="custom-button md:px-6 md:py-3 px-4 py-2">Donate Now</Button></a>
			<a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
				<Button class="md:px-6 md:py-3 px-4 py-2">Seek Support</Button>
			</a>
		</div>

	<div class="w-full h-[0.07em] opacity-30 md:hidden flex" style="background-image: linear-gradient(90deg, #00C8F4 0%, #24D0F5 33%, #D6B7FF 69%, #FFFFFF 94%);" ></div>

	<div class="md:flex md:flex-row flex-col items-center md:px-8">
		 <div class="w-full md:w-1/4 mb-6 md:mb-0">
            <div class="grid text-xs grid-cols-2">
                <ul class=" space-y-2">
                    <li><a href="/" class="hover:text-[#ffba41] hover:cursor-pointer">Home</a></li>
                    <li><a href="/" class="hover:text-[#ffba41] hover:cursor-pointer">Mission</a></li>
                    <li><a href="/get-involved" class="hover:text-[#ffba41] hover:cursor-pointer">Volunteer</a></li>
                    <li><a href="/" class="hover:text-[#ffba41] hover:cursor-pointer">Locate Hospitals</a></li>
                    <li><a href="/" class="hover:text-[#ffba41] hover:cursor-pointer">Terms & Conditions</a></li>
                </ul>
                <ul class="space-y-2">
                    <li><a href="/about-us" class="hover:text-[#ffba41] hover:cursor-pointer">About Us</a></li>
                    <li><a href="/" class="hover:text-[#ffba41] hover:cursor-pointer">Blogs</a></li>
                    <li><a href="/" class="hover:text-[#ffba41] hover:cursor-pointer">News</a></li>
                    <li><a href="mailto:jaruratcare@gmail.com" class="hover:text-[#ffba41] hover:cursor-pointer">Contact Us</a></li>
                </ul>
            </div>
        </div>

		<div class="grow"></div>
		<div class="w-full h-[0.07em] opacity-30 md:hidden flex" style="background-image: linear-gradient(90deg, #00C8F4 0%, #24D0F5 33%, #D6B7FF 69%, #FFFFFF 94%);" ></div>


		<div class="relative z-10 text-[0.8em] md:text-[0.9em] gap-2 md:flex flex flex-row items-center justify-end md:py-0 pt-4">
			<div class="w-full md:w-28 text-center md:text-right">
            <h2 class="text-sm mb-4 text-center md:normal-case md:text-white text-[#ffba41] font-semibold uppercase">Connect with us</h2>
            <div class="flex justify-center md:justify-end gap-2">
				<a class="bg-[#FFFFFF80] rounded-lg p-2 hover:cursor-pointer hover:text-[#ffba41]" href="https://www.instagram.com/jarurat.care/?hl=en"><Instagram/></a>
				<a class="bg-[#FFFFFF80] rounded-lg p-2 hover:cursor-pointer hover:text-[#ffba41]" href="https://x.com/JaruratF73422"><Twitter/></a>
                <a class="bg-[#FFFFFF80] rounded-lg p-2 hover:cursor-pointer hover:text-[#ffba41]"  href="https://twitter.com/Jarurat_care"><Facebook/></a>
                <a class="bg-[#FFFFFF80] rounded-lg p-2 hover:cursor-pointer hover:text-[#ffba41]"  href="https://www.linkedin.com/company/jaruratcare/posts/?feedView=all"><Linkedin/></a>
            </div>
        </div>
		</div>
	</div>

	<div class="w-full h-0.5 opacity-30 md:block hidden" style="background-image: linear-gradient(90deg, #00C8F4 0%, #24D0F5 33%, #D6B7FF 69%, #FFFFFF 94%);" ></div>

	<div class="">

		<div class="text-[#2C72B9] text-[0.8em] font-rubik flex flex-col sm:flex-row gap-2 sm:items-center">
			<div class="flex gap-2 items-center justify-center">
				<a href="/" target="_blank">Privacy Policy</a>
				<a href="/" target="_blank">Terms of service</a>
			</div>
			<div class="grow"></div>
			<span class="text-xs text-center">©2024 JARURAT CARE LCC. All rights reserved</span>
			<div class="grow"></div>
			<button	class="md:flex hidden gap-1 text-[#FFBA41] justify-end"
				on:click={() => window.scrollTo({ top: 0, left: 0, behavior: 'smooth' })}
				>
				Back to top <ArrowUp />
			</button>
		</div>
	</div>
</footer>
