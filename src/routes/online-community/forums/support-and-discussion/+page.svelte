<script>
	import Navbar from '$lib/components/online-community/navbar.svelte';
	let supportDiscussionData = [
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum',
		'About forum'
	];
	let announcements = [
		{ day_time: 'Today 09:00', announcement: 'Announcement' },
		{ day_time: 'Today 02:30', announcement: 'Announcement' },
		{ day_time: 'Tuesday 10:15', announcement: 'New project kickoff' },
		{ day_time: 'Tuesday 04:00', announcement: 'Deadline for report submissions' },
		{ day_time: 'Wednesday 11:00', announcement: 'Guest lecture on AI & Data Science' },
		{ day_time: 'Wednesday 03:45', announcement: 'Office hours with the manager' },
		{ day_time: 'Thursday 09:30', announcement: 'HR policy update session' },
		{ day_time: 'Thursday 01:00', announcement: 'Team lunch at the cafeteria' },
		{ day_time: 'Friday 10:00', announcement: 'Weekly performance review' },
		{ day_time: 'Friday 05:00', announcement: 'Fun Friday games & activities' }
	];

	let index = 0;
	$: visibleAnnouncements = announcements.slice(index, index + 3);

	setInterval(() => {
		index = (index + 1) % (announcements.length - 2);
	}, 3000);
</script>

<div class="w-full mx-auto p-1 bg-[#D9D9D9]">
	<Navbar supportDiscussion={true} />
	<div class="absolute z-0 top-0 left-0">
		<svg
			width="277"
			height="180"
			viewBox="0 0 277 255"
			fill="none"
			xmlns="http://www.w3.org/2000/svg"
		>
			<ellipse cx="46" cy="23" rx="231" ry="232" fill="#A4D9FB" fill-opacity="0.71" />
		</svg>
	</div>

	<div class="flex justify-center mt-[20px] mb-8">
		<h1 class="text-3xl font-extrabold text-[#0D2460] mb-4 items-center">Welcome to the Forum!</h1>
	</div>
</div>
<div class="w-full middle bg-white py-10 flex justify-center items-center min-h-screen rounded-lg">
	<div class="md:flex bg-white xl:w-[80%]  rounded-lg md:space-x-6">
		<div class="xl:w-[70%] border-2 rounded-lg">
			<div class="flex title bg-[#C0ECF7] h-10 items-center rounded-t-lg">
				<p class="text-xs font-bold ml-4">
					<span class="text-lg font-bold">FORUMS</span> - FOR SUPPORT AND DISCUSSION
				</p>
			</div>
			<div class="overflow-y-auto max-h-[680px] custom-scrollbar">
				{#each supportDiscussionData as sdd}
					<div
						class="flex cards border-b-2 h-20 justify-between items-center font-bold rounded-b-lg"
					>
						<div class="ml-5">
							{sdd}
						</div>
						<button class="bg-[#065AC0] text-white mr-10 p-3 text-xs rounded-lg font-light"
							>Click here</button
						>
					</div>
				{/each}
			</div>
		</div>

		<div class="md:w-1/4 space-y-3 mt-10 md:mt-0">
			<div class="bg-[#C0ECF7] pb-4 pr-4 pl-4 rounded-xl w-72 h-[220px]">
				<div class="flex items-center justify-center mb-2">
					<h2 class="font-bold text-lg">Announcements</h2>
					<svg
						width="40"
						height="40"
						viewBox="0 0 40 40"
						fill="none"
						xmlns="http://www.w3.org/2000/svg"
						class="ml-2"
					>
						<g clip-path="url(#clip0_4850_31121)">
							<path
								d="M30 13.3359C31.3261 13.3359 32.5979 13.8627 33.5355 14.8004C34.4732 15.7381 35 17.0099 35 18.3359C35 19.662 34.4732 20.9338 33.5355 21.8715C32.5979 22.8092 31.3261 23.3359 30 23.3359"
								stroke="black"
								stroke-width="2"
								stroke-linecap="round"
								stroke-linejoin="round"
							/>
							<path
								d="M16.6641 13.3359V31.6693C16.6641 32.1113 16.4885 32.5352 16.1759 32.8478C15.8633 33.1603 15.4394 33.3359 14.9974 33.3359H13.3307C12.8887 33.3359 12.4648 33.1603 12.1522 32.8478C11.8397 32.5352 11.6641 32.1113 11.6641 31.6693V23.3359"
								stroke="black"
								stroke-width="2"
								stroke-linecap="round"
								stroke-linejoin="round"
							/>
							<path
								d="M20 13.3339L27.54 7.05056C27.7591 6.86808 28.0257 6.75182 28.3085 6.71541C28.5913 6.679 28.8786 6.72394 29.1367 6.84498C29.3949 6.96602 29.6132 7.15814 29.7661 7.39881C29.919 7.63949 30.0001 7.91876 30 8.2039V28.4639C30.0001 28.749 29.919 29.0283 29.7661 29.269C29.6132 29.5097 29.3949 29.7018 29.1367 29.8228C28.8786 29.9439 28.5913 29.9888 28.3085 29.9524C28.0257 29.916 27.7591 29.7997 27.54 29.6172L20 23.3339H6.66667C6.22464 23.3339 5.80072 23.1583 5.48816 22.8457C5.17559 22.5332 5 22.1093 5 21.6672V15.0006C5 14.5585 5.17559 14.1346 5.48816 13.8221C5.80072 13.5095 6.22464 13.3339 6.66667 13.3339H20Z"
								stroke="black"
								stroke-width="2"
								stroke-linecap="round"
								stroke-linejoin="round"
							/>
						</g>
						<defs>
							<clipPath id="clip0_4850_31121">
								<rect width="40" height="40" fill="white" />
							</clipPath>
						</defs>
					</svg>
				</div>
				<div class="h-32 overflow-hidden space-y-4">
					{#each visibleAnnouncements as announcement}
						<div class="flex items-center">
							<div class="text-xs font-semibold w-16 border-r-2 border-gray-500">
								{announcement.day_time}
							</div>
							<div class="text-xs ml-7">{announcement.announcement}</div>
						</div>
					{/each}
				</div>
			</div>

			<div class="block bg-[#C0ECF7] rounded-xl w-72 h-[180px] place-items-center space-y-8">
				<p class="font-bold text-xl">Q&A <span class="font-light text-3xl">?</span></p>
				<p class="text-xs mr-3 font-bold">About Q&A</p>
				<button class="bg-[#065AC0] text-white rounded-md p-1 px-4 mr-2 text-md">Click here</button>
			</div>
			<div class="block bg-[#C0ECF7] w-72 h-64 rounded-lg place-items-center">
				<div class="block">
					<div class="flex justify-center">
						<p class="font-bold text-xl pt-1">Inspiration corner</p>
						<svg
							width="40"
							height="40"
							viewBox="0 0 40 40"
							fill="none"
							xmlns="http://www.w3.org/2000/svg"
							class="pt-1"
						>
							<g clip-path="url(#clip0_4850_31145)">
								<path
									d="M35 20C35 17.0329 34.12 14.1324 32.4714 11.6654C30.8227 9.19846 28.4794 7.27588 25.7379 6.14086C22.9965 5.00584 19.98 4.70937 17.07 5.28896C14.16 5.86855 11.4873 7.29815 9.38987 9.39694C7.29248 11.4957 5.86466 14.1694 5.28701 17.0798C4.70936 19.9902 5.00784 23.0065 6.14469 25.7472C7.28153 28.4879 9.20568 30.8299 11.6737 32.4769C14.1418 34.1239 17.0429 35.002 20.01 35"
									stroke="black"
									stroke-width="2"
									stroke-linecap="round"
									stroke-linejoin="round"
								/>
								<path
									d="M15 16.6641H15.0167"
									stroke="black"
									stroke-width="2"
									stroke-linecap="round"
									stroke-linejoin="round"
								/>
								<path
									d="M25 16.6641H25.0167"
									stroke="black"
									stroke-width="2"
									stroke-linecap="round"
									stroke-linejoin="round"
								/>
								<path
									d="M15.8359 25C16.3791 25.5543 17.0274 25.9947 17.7428 26.2954C18.4583 26.596 19.2265 26.7509 20.0026 26.7509C20.7787 26.7509 21.5469 26.596 22.2624 26.2954C22.9779 25.9947 23.6261 25.5543 24.1693 25"
									stroke="black"
									stroke-width="2"
									stroke-linecap="round"
									stroke-linejoin="round"
								/>
								<path
									d="M31.6693 37.5026C32.0115 36.088 32.737 34.7952 33.7661 33.7661C34.7952 32.737 36.088 32.0115 37.5026 31.6693C36.088 31.3271 34.7952 30.6016 33.7661 29.5725C32.737 28.5434 32.0115 27.2505 31.6693 25.8359C31.3271 27.2505 30.6016 28.5434 29.5725 29.5725C28.5434 30.6016 27.2505 31.3271 25.8359 31.6693C27.2505 32.0115 28.5434 32.737 29.5725 33.7661C30.6016 34.7952 31.3271 36.088 31.6693 37.5026Z"
									stroke="black"
									stroke-width="2"
									stroke-linecap="round"
									stroke-linejoin="round"
								/>
							</g>
							<defs>
								<clipPath id="clip0_4850_31145">
									<rect width="40" height="40" fill="white" />
								</clipPath>
							</defs>
						</svg>
					</div>

					<p class="text-xs mr-3 font-bold ml-8 mt-5">Stay Inspired, Stay Strong!</p>
				</div>

				<div class="block place-items-center border-2 border-gray-300 rounded-lg mt-4 h-[200px]">
					<p class="font-bold italic text-md text-center mt-10">
						Read uplifting stories, personal journeys, and expert advice
					</p>
					<button class="bg-[#065AC0] text-white rounded-md p-1 px-4 mr-2 text-md mt-10"
						>Click here</button
					>
				</div>
			</div>
		</div>
	</div>
</div>
