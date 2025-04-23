<script>
	import LogoSquare from '$lib/svg/logo-square.svelte';
	import Button from './ui/button.svelte';
	import Icon from '@iconify/svelte';
	import instagramIcon from '@iconify-icons/mdi/instagram';
	import twitterIcon from '@iconify-icons/mdi/twitter';
	import facebookIcon from '@iconify-icons/mdi/facebook';

	function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
</script>

<footer class="bg-[#132442] font-sans py-5">
	<div class="bg-[#0D2561] text-white p-4 pt-24 sm:p-12 sm:pt-32 flex flex-col gap-8">
		<!-- Logo and Buttons Section -->
		<div class="flex items-center">
			<LogoSquare class="max-w-[2.5em] sm:max-w-[3.5em] w-full" />
			<div class="flex flex-col gap-1 leading-[1] text-[1.2em] md:text-[2em] ml-4">
				<span class="font-semibold">Jaruratcare Foundation</span>
				<small class="text-[0.7em] text-[#BFE9E8]">Jaisi Jarurat, Vaisi Care</small>
			</div>

			<div class="grow"></div>

			<div class="relative z-10 text-[0.9em] gap-2 hidden sm:flex">
				<a href="/donate"><Button>Donate Now</Button></a>
				<a href="mailto:Priyanka.joshi@jarurat.care?subject=Seek%20Support%20For%20Cancer">
					<Button>Seek Support</Button>
				</a>
			</div>
		</div>

		<hr class="mx-26 border-gray-300" />

		<!-- Links Section -->
		<div class="flex text-white text-sm justify-end py-5 mx-24 gap-5">
			<div class="flex flex-col space-y-2">
				<a href="/">Home</a>
				<a href="/">Mission</a>
				<a href="/get-involved">Volunteer</a>
				<a href="/">Locate Hospitals</a>
				<a href="/">Terms & Conditions</a>
			</div>
			<div class="flex flex-col space-y-2">
				<a href="/about-us">About Us</a>
				<a href="/">Blogs</a>
				<a href="/">News</a>
				<a href="/contact-us">Contact Us</a>
			</div>
			<div class="flex flex-col space-y-2">
				<p>Connect with us</p>
				<div class="flex space-x-5">
					<a href="https://instagram.com/jarurat.care" target="_blank" aria-label="Instagram">
						<Icon icon={instagramIcon} class="icon h-7 cursor-pointer" />
					</a>
					<a href="https://twitter.com/jarurat_care" target="_blank" aria-label="Twitter">
						<Icon icon={twitterIcon} class="icon h-7 cursor-pointer" />
					</a>
					<a href="https://facebook.com/yourprofile" target="_blank" aria-label="Facebook">
						<Icon icon={facebookIcon} class="icon h-7 cursor-pointer" />
					</a>
				</div>
			</div>
		</div>

		<hr class="mx-26 border-gray-300" />

		<!-- Bottom Section -->
		<div class="flex text-[#204D80] justify-between mx-20 py-5">
			<div class="flex space-x-1 cursor-pointer">
				<a href="/privacy-policy">Privacy policy</a>
				<a href="/terms-of-service">Terms of service</a>
			</div>
			<div>
				<p>&copy;2024 JARURAT CARE LCC. All rights reserved</p>
			</div>
		
<div class="text-[#D8E74E] cursor-pointer" on:click={scrollToTop}>
  <p>Back to Top &uarr;</p>
</div>
		</div>
	</div>
</footer>
