<script>
	import LogoSquare from '$lib/svg/logo-square.svelte';
	import Button from './ui/button.svelte';
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
  
		

  
		<div class=" relative z-10 text-[0.9em] gap-2 hidden sm:flex ml-64" >
		  <a href="/donate"  ><Button class = "px-8">Donate Now</Button></a>
		  <a href="mailto:Priyanka.joshi@jarurat.care?subject=Seek%20Support%20For%20Cancer">
			<Button class = "px-8">Seek Support</Button>
		  </a>
		</div>
	  </div>
  
	  
  
	  <!-- Links Section -->
	  <div class="flex text-white text-sm py-5 mx-24 gap-5">
		<div class="flex flex-col space-y-2">
		  <a href="/">Home</a>
		  <a href="/">Mission</a>
		  <a href="/get-involved">Volunteer</a>
		  <a href="/">Locate Hospitals</a>
		  <a href="/">Terms & Conditions</a>
		</div>
		<div class="flex flex-col space-y-2 ml-10">
		  <a href="/about-us">About Us</a>
		  <a href="/">Blogs</a>
		  <a href="/">News</a>
		  <a href="/contact-us">Contact Us</a>
		</div>
  
		<!-- Social Media Section -->
		<div class="flex flex-col space-y-2 ml-auto">
		  <p style="font-family: 'Segoe UI', sans-serif; font-size: 1.2em; font-weight: 600; color: white;">Connect with us</p>
  
		  <div class="flex space-x-5">
			<a href="https://instagram.com/jarurat.care" target="_blank" aria-label="Instagram" class="icon-container">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
				  <path fill="#fff" d="M7.8 2h8.4C19.4 2 22 4.6 22 7.8v8.4a5.8 5.8 0 0 1-5.8 5.8H7.8C4.6 22 2 19.4 2 16.2V7.8A5.8 5.8 0 0 1 7.8 2m-.2 2A3.6 3.6 0 0 0 4 7.6v8.8C4 18.39 5.61 20 7.6 20h8.8a3.6 3.6 0 0 0 3.6-3.6V7.6C20 5.61 18.39 4 16.4 4zm9.65 1.5a1.25 1.25 0 0 1 1.25 1.25A1.25 1.25 0 0 1 17.25 8A1.25 1.25 0 0 1 16 6.75a1.25 1.25 0 0 1 1.25-1.25M12 7a5 5 0 0 1 5 5a5 5 0 0 1-5 5a5 5 0 0 1-5-5a5 5 0 0 1 5-5m0 2a3 3 0 0 0-3 3a3 3 0 0 0 3 3a3 3 0 0 0 3-3a3 3 0 0 0-3-3"/>
				</svg>
			  </a>
			  
			<a href="https://twitter.com/jarurat_care" target="_blank" aria-label="Twitter" class="icon-container">
			  <svg xmlns="http://www.w3.org/2000/svg" width="44" height="44" viewBox="0 0 512 512">
				<path fill="#fff" d="M389.2 48h70.6L305.6 224.2L487 464H345L233.7 318.6L106.5 464H35.8l164.9-188.5L26.8 48h145.6l100.5 132.9zm-24.8 373.8h39.1L151.1 88h-42z"/>
			  </svg>
			</a>
			<a href="https://www.facebook.com/profile.php?id=61564363136062&mibextid=ZbWKwL" target="_blank" aria-label="Facebook" class="icon-container">
			  <svg xmlns="http://www.w3.org/2000/svg" width="44" height="44" viewBox="0 0 486.037 1000">
				<path fill="#fff" d="M124.074 1000V530.771H0V361.826h124.074V217.525C124.074 104.132 197.365 0 366.243 0C434.619 0 485.18 6.555 485.18 6.555l-3.984 157.766s-51.564-.502-107.833-.502c-60.9 0-70.657 28.065-70.657 74.646v123.361h183.331l-7.977 168.945H302.706V1000z"/>
			  </svg>
			</a>
			<a href="https://www.linkedin.com/company/jaruratcare" target="_blank" aria-label="LinkedIn" class="icon-container">
			  <svg xmlns="http://www.w3.org/2000/svg" width="44" height="44" viewBox="0 0 448 512">
				<path fill="#fff" d="M100.28 448H7.4V148.9h92.88zM53.79 108.1C24.09 108.1 0 83.5 0 53.8a53.79 53.79 0 0 1 107.58 0c0 29.7-24.1 54.3-53.79 54.3M447.9 448h-92.68V302.4c0-34.7-.7-79.2-48.29-79.2c-48.29 0-55.69 37.7-55.69 76.7V448h-92.78V148.9h89.08v40.8h1.3c12.6-23.9 43.7-49.2 90.4-49.2c96.3 0 113.7 63.4 113.7 145.4V448h-.02z"/>
			  </svg>
			</a>

			<a href="https://www.threads.net/@jarurat.care" target="_blank" aria-label="Threads" class="icon-container">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
				  <path fill="#fff" d="M12.184 1.41h-.002C9.09 1.432 6.7 2.473 5.094 4.516c-1.428 1.815-2.16 4.348-2.184 7.49v.002c.025 3.143.756 5.662 2.184 7.477c1.606 2.042 4.009 3.084 7.1 3.105h.002c2.748-.019 4.697-.74 6.303-2.344c2.104-2.103 2.042-4.741 1.347-6.363c-.53-1.234-1.575-2.221-2.976-2.835c-.18-2.985-1.86-4.726-4.62-4.744c-1.63-.01-3.102.72-4.003 2.087l1.655 1.136c.533-.809 1.377-1.199 2.335-1.19c1.387.009 2.3.774 2.555 2.117a11.7 11.7 0 0 0-2.484-.105c-2.64.152-4.368 1.712-4.253 3.875c.12 2.262 2.312 3.495 4.393 3.381c2.492-.137 3.973-1.976 4.324-4.321c.577.373 1.003.85 1.244 1.413c.44 1.025.468 2.716-.915 4.098c-1.217 1.216-2.68 1.746-4.912 1.762c-2.475-.018-4.332-.811-5.537-2.343C5.52 16.774 4.928 14.688 4.906 12c.022-2.688.614-4.775 1.746-6.213c1.205-1.533 3.062-2.325 5.537-2.344c2.493.019 4.384.815 5.636 2.356c.691.85 1.124 1.866 1.413 2.915l1.94-.517c-.363-1.338-.937-2.613-1.815-3.694c-1.653-2.034-4.081-3.071-7.18-3.093m.236 10.968a9.4 9.4 0 0 1 2.432.156c-.14 1.578-.793 2.947-2.512 3.041c-1.112.063-2.237-.434-2.292-1.461c-.04-.764.525-1.63 2.372-1.736"/>
				</svg>
			  </a>			  

		  </div>
		</div>
	  </div>

	  <hr class="mx-26 border-gray-400 opacity-20" />


	  <!-- Bottom Section -->
	  <div class="flex text-[#204D80] justify-between mx-20 py-5">
		<div class="flex space-x-1 cursor-pointer">
			<a href="/privacy-policy">Privacy policy</a>
			<a href="/terms-of-service">Terms of service</a>
		</div>
		<div>
			<p>&copy;2024 JARURAT CARE LCC. All rights reserved</p>
		</div>
		<div class="text-[#D8E74E] cursor-pointer">
			<p>Back to Top &uarr;</p>
		</div>
	</div>
	</div>
  
  </footer>
  
  <style>
	.icon-container {
		display: flex;
		align-items: center;
		justify-content: center;
		background-color: #6CBFD9; 
		border-radius: 12px; 
		padding: 0.5rem; 
		transition: transform 0.2s ease, box-shadow 0.2s ease;
	}

	/* Glow effect and scale on hover */
	.icon-container:hover {
		transform: scale(1.1); 
		box-shadow: 0 0 15px #4A91AE; 
	}

	

	
	.icon-container svg {
		width: 44px;
		height: 44px;
	}
</style>