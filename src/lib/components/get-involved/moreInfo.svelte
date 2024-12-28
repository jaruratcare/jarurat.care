<script>
	import volunteerImg from '$lib/assets/get-involved/volunteer.png';
	import joinTeamImg from '$lib/assets/get-involved/join.png';
	import partnershipImg from '$lib/assets/get-involved/partnership.png';
</script>

<img src={volunteerImg} alt="volunteerImg" class="bg-cover absolute bottom-0 w-[5rem] md:w-[12rem] md:h-auto md:mr-5">

<div class="flex flex-col justify-center items-center p-5 md:p-40 bg-blue-50 w-screen md:w-full">
	<!-- Volunteer/Internship Card -->
	<div
		class="card relative flex flex-col md:flex-row my-10 p-5 bg-white shadow-md rounded-xl md:p-9 md:w-auto w-full flex-grow"
	>
		<!-- Text Section -->
		<div class="container_1 flex flex-col justify-center items-start m-1 md:w-2/3 flex-grow">
			<h3
				class="font-extrabold text-[#FFBA41] text-left text-sm sm:text-3xl md:text-2xl lg:text-3xl"
			>
				VOLUNTEER/
				<span class="text-[#0155BD]">INTERNSHIP</span>
			</h3>
			<p class="text-left text-xs sm:text-sm md:text-base lg:font-normal my-3">
				Your time and energy can change lives. Whether you’re providing support to caregivers,
				assisting with events, or spreading awareness, volunteering with Jarurat Care Foundation is
				a powerful way to give back. Every bit of help makes a difference, and we’re here to support
				you as you make an impact.
			</p>
			<button
				class="bg-[#9CCB44] text-white py-2 px-4 my-3 border-[2px] border-double border-[#c2ec8b] rounded-full"
			>
				<p class="text-sm md:text-base">Register</p>
			</button>
		</div>

		<!-- Image Section -->
		<div class="img_container flex items-end justify-end md:w-1/3 w-full">
			<img
				src={volunteerImg}
				alt="volunteerImg"
				class="bg-cover absolute bottom-0 w-[5rem] md:w-[12rem] md:h-auto md:mr-5"
			/>
		</div>
	</div>

	<!-- Join Our Team Card -->
	<div
		class="card relative flex flex-col md:flex-row my-10 p-5 bg-white shadow-md rounded-xl md:p-9 md:w-auto w-full overflow-hidden flex-grow"
	>
		<!-- Text Section -->
		<div class="container_1 flex flex-col justify-center items-start m-1 md:w-2/3 flex-grow">
			<h3
				class="font-extrabold text-[#FFBA41] text-left text-sm sm:text-3xl md:text-2xl lg:text-3xl"
			>
				JOIN OUR TEAM
			</h3>
			<p class="text-left text-xs sm:text-sm md:text-base lg:font-normal my-3">
				Be part of a passionate team making a real difference in the lives of cancer patients and
				their families. At Jarurat Care Foundation, we’re always looking for dedicated professionals
				who are committed to our mission. If you’re driven, compassionate, and want to contribute
				your skills to a meaningful cause, we’d love to hear from you.
			</p>
			<button
				class="bg-[#9CCB44] text-white py-2 px-4 my-3 border-[2px] border-double border-[#c2ec8b] rounded-full"
			>
				<p class="text-sm md:text-base">Register</p>
			</button>
		</div>

		<!-- Image Section -->
		<div class="img_container relative flex items-center justify-end md:w-1/3 w-full">
			<img
				src={joinTeamImg}
				alt="joinTeamImg"
				class="bg-cover absolute -right-12 w-[7rem] md:w-[12rem] md:h-auto md:mr-20"
			/>
		</div>
	</div>

	<!-- Corporate Partnerships Card -->
	<div
		class="card flex flex-col md:flex-row my-10 p-5 bg-white shadow-md rounded-xl md:p-9 md:w-auto w-full flex-grow"
	>
		<!-- Text Section -->
		<div class="container_1 flex flex-col justify-center items-start m-1 md:w-2/3 flex-grow">
			<h3
				class="font-extrabold text-[#FFBA41] text-left text-sm sm:text-3xl md:text-2xl lg:text-3xl"
			>
				CORPORATE
				<span class="text-[#0155BD]">PARTNERSHIPS</span>
			</h3>
			<p class="text-left text-xs sm:text-sm md:text-base lg:font-normal my-3">
				Partnering with Jarurat Care Foundation aligns your brand with a cause that truly matters.
				Together, we can create impactful CSR initiatives that benefit cancer patients and their
				families. From sponsorships to employee engagement programs, there are many ways for your
				company to get involved and make a lasting difference.
			</p>
			<button
				class="bg-[#9CCB44] text-white py-2 px-4 my-3 border-[2px] border-double border-[#c2ec8b] rounded-full"
			>
				<p class="text-sm md:text-base">Let's Collaborate</p>
			</button>
		</div>

		<!-- Image Section -->
		<div class="img_container relative flex items-center justify-end md:w-1/3 w-full">
			<img
				src={partnershipImg}
				alt="partnershipImg"
				class="bg-cover w-[5rem] md:w-[12rem] md:h-auto md:mr-5"
			/>
		</div>
	</div>
</div>
