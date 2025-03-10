<script>
	let searchQuery = '';
	let subGroups = ['Sub Group 1', 'Sub Group 2', 'Sub Group 3', 'Sub Group 4'];
	import Navbar from '$lib/components/online-community/navbar.svelte';
	import { onMount } from 'svelte';
	import Swiper from 'swiper';
	import { Pagination } from 'swiper/modules';
	import 'swiper/css';
	import 'swiper/css/pagination';
	

	let events = [
		{ title: 'Event 1', date: 'March 10', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 2', date: 'April 5', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 3', date: 'May 15', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 4', date: 'June 20', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 5', date: 'July 8', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 6', date: 'July 9', image: 'https://via.placeholder.com/150'},
		{ title: 'Event 1', date: 'March 10', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 2', date: 'April 5', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 3', date: 'May 15', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 4', date: 'June 20', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 5', date: 'July 8', image: 'https://via.placeholder.com/150' },
		{ title: 'Event 6', date: 'July 9', image: 'https://via.placeholder.com/150'}
	];

	let swiper;

	onMount(() => {
		swiper = new Swiper('.swiper', {
			modules: [Pagination],
			slidesPerView: 4,
			spaceBetween: 110,
			pagination: {
				el: '.swiper-pagination',
				clickable: true,
				dynamicBullets: true
			},
			
			breakpoints: {
				768: { slidesPerView: 2 }, // Tablets
				1024: { slidesPerView: 3 }, // Large screens
				1280: { slidesPerView:5}, // Extra large screens
				640: { slidesPerView: 2 }, // Small screens
				320: { slidesPerView: 1 } // Mobile
			},
			
		});
	});
</script>

<div class="w-full mx-auto p-6">
	<Navbar events={true} />
	<div class="absolute z-0 top-0 left-0">
		<svg
			width="277"
			height="255"
			viewBox="0 0 277 255"
			fill="none"
			xmlns="http://www.w3.org/2000/svg"
		>
			<ellipse cx="46" cy="23" rx="231" ry="232" fill="#A4D9FB" fill-opacity="0.71" />
		</svg>
	</div>

	<div class="md:justify-evenly mt-[120px] md:mt-[20px] md:ml-[252px]">
		<h1 class="text-2xl font-extrabold text-[#0D2460] mb-4 items-center">EVENTS</h1>

		<div
			class="block md:flex md:items-center bg-white border-2 border-[#5D5F6426] md:rounded-full md:px-2 md:py-1 xl:w-[69%] md:mb-6"
		>
			<div class="flex search border-2 bg-[#5D5F6433] md:w-[70%] md:rounded-full px-4 py-2">
				<svg
					width="25"
					height="25"
					viewBox="0 0 25 25"
					fill="none"
					xmlns="http://www.w3.org/2000/svg"
				>
					<path
						d="M24.6582 21.6162L19.79 16.748C19.5703 16.5283 19.2725 16.4062 18.96 16.4062H18.1641C19.5117 14.6826 20.3125 12.5146 20.3125 10.1562C20.3125 4.5459 15.7666 0 10.1562 0C4.5459 0 0 4.5459 0 10.1562C0 15.7666 4.5459 20.3125 10.1562 20.3125C12.5146 20.3125 14.6826 19.5117 16.4062 18.1641V18.96C16.4062 19.2725 16.5283 19.5703 16.748 19.79L21.6162 24.6582C22.0752 25.1172 22.8174 25.1172 23.2715 24.6582L24.6533 23.2764C25.1123 22.8174 25.1123 22.0752 24.6582 21.6162ZM10.1562 16.4062C6.7041 16.4062 3.90625 13.6133 3.90625 10.1562C3.90625 6.7041 6.69922 3.90625 10.1562 3.90625C13.6084 3.90625 16.4062 6.69922 16.4062 10.1562C16.4062 13.6084 13.6133 16.4062 10.1562 16.4062Z"
						fill="black"
					/>
				</svg>
				<input
					type="text"
					class="w-full outline-none px-2 bg-transparent placeholder:text-extrabold placeholder:text-[rgb(55,57,61)] placeholder:text-md placeholder:pl-4"
					bind:value={searchQuery}
					placeholder="Search"
				/>
			</div>

			<div class="flex filter items-center bg-[#5D5F6433] px-4 py-2 md:rounded-full md:ml-5 md:w-[30%]">
				<svg
					width="25"
					height="22"
					viewBox="0 0 25 22"
					fill="none"
					xmlns="http://www.w3.org/2000/svg"
				>
					<path
						d="M23.2969 10.9994H8.60444M3.41839 10.9994H1.29688M3.41839 10.9994C3.41839 10.3415 3.69152 9.71048 4.17769 9.24524C4.66387 8.78 5.32327 8.51863 6.01082 8.51863C6.69838 8.51863 7.35777 8.78 7.84395 9.24524C8.33012 9.71048 8.60325 10.3415 8.60325 10.9994C8.60325 11.6574 8.33012 12.2884 7.84395 12.7536C7.35777 13.2189 6.69838 13.4802 6.01082 13.4802C5.32327 13.4802 4.66387 13.2189 4.17769 12.7536C3.69152 12.2884 3.41839 11.6574 3.41839 10.9994ZM23.2969 18.5181H16.4614M16.4614 18.5181C16.4614 19.1762 16.1876 19.8079 15.7013 20.2732C15.2151 20.7386 14.5555 21 13.8678 21C13.1802 21 12.5208 20.7375 12.0347 20.2723C11.5485 19.807 11.2754 19.176 11.2754 18.5181M16.4614 18.5181C16.4614 17.86 16.1876 17.2294 15.7013 16.764C15.2151 16.2987 14.5555 16.0373 13.8678 16.0373C13.1802 16.0373 12.5208 16.2986 12.0347 16.7639C11.5485 17.2291 11.2754 17.8601 11.2754 18.5181M11.2754 18.5181H1.29688M23.2969 3.4808H19.6044M14.4184 3.4808H1.29688M14.4184 3.4808C14.4184 2.82285 14.6915 2.19185 15.1777 1.72661C15.6639 1.26137 16.3233 1 17.0108 1C17.3513 1 17.6884 1.06417 18.0029 1.18884C18.3174 1.31351 18.6032 1.49625 18.8439 1.72661C19.0847 1.95697 19.2756 2.23045 19.4059 2.53144C19.5362 2.83242 19.6033 3.15501 19.6033 3.4808C19.6033 3.80658 19.5362 4.12917 19.4059 4.43016C19.2756 4.73114 19.0847 5.00462 18.8439 5.23498C18.6032 5.46535 18.3174 5.64808 18.0029 5.77275C17.6884 5.89743 17.3513 5.96159 17.0108 5.96159C16.3233 5.96159 15.6639 5.70022 15.1777 5.23498C14.6915 4.76974 14.4184 4.13874 14.4184 3.4808Z"
						stroke="#12294E"
						stroke-width="1.5"
						stroke-miterlimit="10"
						stroke-linecap="round"
					/>
				</svg>
				<span class="ml-3">Filter</span>
				<svg
					width="10"
					height="10"
					viewBox="0 0 10 10"
					fill="none"
					xmlns="http://www.w3.org/2000/svg"
					class="ml-auto"
				>
					<path
						d="M9.35156 5.328L0.891562 9.096V7.428L7.99556 4.392V4.896L0.891562 1.86V0.252L9.35156 4.02V5.328Z"
						fill="#12294E"
					/>
				</svg>
			</div>
		</div>
	</div>
</div>
<div
	class="w-full middle bg-blue-100 py-10 flex justify-center items-center min-h-screen rounded-lg"
>
	<div class="bg-white w-[70%] md:w-[80%] min-h-screen rounded-md p-6 shadow-lg">
		<div class="block lg:flex lg:flex-row gap-5">
			<div>
				<h2 class="text-2xl font-bold text-blue-900 w-[250px]">Why you should join event</h2>
				<p class="text-sm font-bold text-black mt-2 xl:w-[440px]">
					Why you should join event Why you should join event Why you should join event Why you
					should join event
				</p>
			</div>
			<div class="parent_container grid  grid-cols-1 md:grid-cols-2 gap-x-20 gap-y-4 mt-20 lg:mt-auto">
				<div
					class="relative text-black font-semibold flex flex-col items-center justify-center text-center border-2 border-gray-400 rounded-lg shadow-[4px_4px_10px_rgba(0,0,0,0.7)] xl:w-64 h-32 p-4"
				>
					Connect with Experts
					<div class="absolute bottom-0 right-0">
						<svg
							width="60"
							height="50"
							viewBox="0 0 60 50"
							fill="none"
							xmlns="http://www.w3.org/2000/svg"
						>
							<circle cx="46.5" cy="46.5" r="46.5" fill="#0D2460" />
							<text text-anchor="middle" font-size="16" fill="white" dy=".3em" x="50%" y="50%"
								>1</text
							>
						</svg>
					</div>
				</div>

				<div
					class="relative text-black font-semibold flex flex-col items-center justify-center text-center border-2 border-gray-400 rounded-lg shadow-[4px_4px_10px_rgba(0,0,0,0.7)] xl:w-64 h-32 p-4"
				>
					Personalized Guidance
					<div class="absolute bottom-0 right-0">
						<svg
							width="60"
							height="50"
							viewBox="0 0 60 50"
							fill="none"
							xmlns="http://www.w3.org/2000/svg"
						>
							<circle cx="46.5" cy="46.5" r="46.5" fill="#0D2460" />
							<text text-anchor="middle" font-size="16" fill="white" dy=".3em" x="50%" y="50%"
								>2</text
							>
						</svg>
					</div>
				</div>

				<div
					class="relative text-black font-semibold flex flex-col items-center justify-center text-center border-2 border-gray-400 rounded-lg shadow-[4px_4px_10px_rgba(0,0,0,0.7)] xl:w-64 h-32 p-4"
				>
					Real Stories
					<div class="absolute bottom-0 right-0">
						<svg
							width="60"
							height="50"
							viewBox="0 0 60 50"
							fill="none"
							xmlns="http://www.w3.org/2000/svg"
						>
							<circle cx="46.5" cy="46.5" r="46.5" fill="#0D2460" />
							<text text-anchor="middle" font-size="16" fill="white" dy=".3em" x="50%" y="50%"
								>3</text
							>
						</svg>
					</div>
				</div>

				<div
					class="relative text-black font-semibold flex flex-col items-center justify-center text-center border-2 border-gray-400 rounded-lg shadow-[4px_4px_10px_rgba(0,0,0,0.7)] xl:w-64 h-32 p-4"
				>
					Exclusive Resources
					<div class="absolute bottom-0 right-0">
						<svg
							width="60"
							height="50"
							viewBox="0 0 60 50"
							fill="none"
							xmlns="http://www.w3.org/2000/svg"
						>
							<circle cx="46.5" cy="46.5" r="46.5" fill="#0D2460" />
							<text text-anchor="middle" font-size="16" fill="white" dy=".3em" x="50%" y="50%"
								>4</text
							>
						</svg>
					</div>
				</div>
			</div>
		</div>
		<div class="block">
			<p class="text-[#0D2460] text-3xl font-bold underline mt-20 lg:mt-auto">Upcoming events</p>
			
				<div class="swiper">
					<div class="swiper-wrapper">
						{#each events as event}
							<div class="swiper-slide">
								<div class="w-48 h-[260px] mt-5 bg-white  rounded-lg overflow-hidden ">
									<div class="bg-[#D9D9D9] p-4 text-start font-semibold">
										{event.title}<br /><span class="text-sm font-semibold">{event.date}</span>
									</div>
									<div class="bg-[#3B353566] h-20 flex items-center justify-center  text-white">
										<img src={event.image} alt="Image" class="h-full w-full object-cover" />
									</div>
									<div class="bg-[#3B3535] text-white flex">
										<button class="flex-1 py-1 text-center">Register</button>
										<button class="flex-1 py-1 text-center border-l border-gray-600"
											>More info</button
										>
									</div>
								</div>
							</div>
						{/each}
					</div>
					<div class="swiper-pagination"></div>
				</div>
			
		</div>
	</div>
</div>
