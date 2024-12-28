<script>
	// Import images directly
	import triptiImage from '$lib/assets/get-involved/tripti.png';
	import shreyaImage from '$lib/assets/get-involved/shreya.png';
	import adityaImage from '$lib/assets/get-involved/aditya.png';
	import communityBackground from '$lib/assets/get-involved/community.png';
	import quoteImage from '$lib/assets/get-involved/quote.png';

	// Members array
	let members = [
		{
			name: 'Tripti',
			role: 'Social Media POD LEAD',
			feedback: 'It was we really have good co-workers and culture and being very active and can learn a lot from this internship',
			image: triptiImage
		},
		{
			name: 'Shreya',
			role: 'Content & Research',
			feedback: 'Open and supportive environment where ideas are valued, feedback is encouraged, and growth is prioritized.',
			image: shreyaImage
		},
		{
			name: 'Aditya Nalawade',
			role: 'Video Editor',
			feedback: 'We have really good co-workers and a culture of being very active, with a lot to learn from this internship.',
			image: adityaImage
		},
		{
			name: 'Rohan Sharma',
			role: 'Graphic Designer',
			feedback: 'I loved the creative freedom and collaboration!',
			image: adityaImage
		},
		{
			name: 'Sara Singh',
			role: 'Event Organizer',
			feedback: 'Great opportunity for professional and personal growth.',
			image: triptiImage
		}
	];

	let currentIndex = 0;
	let isWideScreen = false;

	// Check if the screen is wide when the component mounts
	$: if (typeof window !== 'undefined') {
		isWideScreen = window.innerWidth > 768;
	}

	// Next and Previous slide functions
	function nextSlide() {
		currentIndex = (currentIndex + 1) % members.length;
	}

	function prevSlide() {
		currentIndex = (currentIndex - 1 + members.length) % members.length;
	}
</script>

<!-- Carousel Container -->
<div
		class="w-full md:h-[855px] h-[600px] bg-cover bg-no-repeat bg-blue-50 relative flex items-center justify-center"
		style="background-image: url({communityBackground});"
>
	<div class="w-full max-w-4xl mx-auto relative">
		<h2 class="md:text-center text-left text-2xl md:text-3xl font-extrabold text-[#0D2460] mb-10 ml-3">
			Hear From <span class="block md:inline text-[#0155BD]">Our Community</span>
		</h2>

		<!-- Carousel Navigation Buttons -->
		<div class="relative flex items-center">
			<!-- Previous Button -->
			<button
					on:click={prevSlide}
					class="absolute left-0 z-10 ml-4 p-3 bg-white rounded-full shadow-lg hover:bg-[#FFBA41] flex items-center justify-center"
					style="top: 50%; transform: translateY(-50%); width: 40px; height: 40px;"
			>
				<span class="text-xl" style="line-height: 0;">&lt;</span>
			</button>

			<!-- Carousel Slides -->
			<div class="flex overflow-hidden w-full">
				{#each members as member, index (index)}
					{#if index >= currentIndex && index < currentIndex + (isWideScreen ? 3 : 1)}
						<div
								class="w-full sm:w-1/3 md:h-[26rem] h-[12rem] px-2 flex-shrink-0 transition-transform duration-500 ease-in-out transform translate-x-[${index - currentIndex}00%]"
						>
							<div class="bg-white p-4 md:p-0 rounded-xl shadow-lg flex flex-col items-center h-full">
								<div class="flex flex-row md:flex-col gap-3">
									<img
											src={member.image}
											alt={member.name}
											class="md:w-[19rem] md:h-[16rem] w-[5rem] h-[5rem] rounded-md mb-2 object-cover"
									/>
									<div class="md:px-4">
										<h3 class="text-xl font-bold text-[#0155BD]">{member.name}</h3>
										<p class="text-sm text-[#0155BD]">{member.role}</p>
									</div>
									<img
											src={quoteImage}
											alt="quote"
											class="w-[2.5rem] h-[2.5rem] pr-1 mt-1 sm:hidden"
									/>
								</div>
								<p class="text-left text-sm text-gray-600 mt-2 md:px-4 md:pb-4">
									{member.feedback}
								</p>
							</div>
						</div>
					{/if}
				{/each}
			</div>

			<!-- Next Button -->
			<button
					on:click={nextSlide}
					class="absolute right-0 z-10 mr-4 p-3 bg-white rounded-full shadow-lg hover:bg-[#FFBA41] flex items-center justify-center"
					style="top: 50%; transform: translateY(-50%); width: 40px; height: 40px;"
			>
				<span class="text-xl" style="line-height: 0;">&gt;</span>
			</button>
		</div>
	</div>
</div>

<!-- Styles -->
<style>
	button {
		width: 40px;
		height: 40px;
	}
</style>
