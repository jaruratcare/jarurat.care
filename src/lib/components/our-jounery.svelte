<script>
	import LogoBlueface from '$lib/svg/about/logo-blueface.svelte';
	import SingleWave from '$lib/svg/single-wavedown.svelte';
	import RekhaJoshi from '$lib/assets/rekha joshi.png';
	import cofounders from '$lib/assets/cofounders.png';
	import podcast from '$lib/assets/prodcast.png';
	import GrpOfPeople from '$lib/svg/about/grp-of-people.svelte';
	import Reaserch from '$lib/svg/about/reaserch.svelte';
	import LaunchRocket from '$lib/svg/about/launch-rocket.svelte';
	import BlubIdea from '$lib/svg/about/blub-idea.svelte';
</script>

<div>
	<SingleWave fill="#0155BD" />
	<div class="bg-primaryBlue pb-12  ">
		<div
			class="flex md:items-center relative justify-center flex-row-reverse md:flex-col gap-6"
		>
			<div class="font-extrabold text-3xl text-white text-center">Our Journey</div>
			<div class="logo md:w-20 w-10 bg-white rounded-full p-1 md:p-4 absolute md:relative left-0 "><LogoBlueface /></div>
		</div>

		<div class="pl-4 md:pl-0 ">
			<div class="flex flex-col md:flex-row">
				<div
					class=" md:w-1/2 relative flex justify-center border-l-4 md:border-l-0 md:border-r-4 border-dashed border-white"
				>
					<div
						class="cirlce hidden md:block bg-[#FFBA41] w-9 h-9 rounded-full absolute -right-5 top-[45%]"
					></div>
					<div class="relative">
						<div
							class="back bg-[#0D2460] h-[13rem] w-[18rem]  lg:w-[25rem] lg:h-[18rem]  rounded-2xl -rotate-2"
						></div>
						<div class="front absolute top-0 right-0"><img src={RekhaJoshi} alt="" /></div>
					</div>
				</div>
				<div class="flex justify-center items-center  border-l-4 md:border-0 border-dashed md:w-1/2 pl-3 md:pl-4 ">
					<div class=" md:w-[25rem] pt-5 md:pt-0">
						<div class="flex pb-5 font-extrabold">
							<span class="text-white">2023, </span> <span class="text-[#FFBA41]"> DECEMBER</span>
						</div>
						<div class="md:text-sm text-xs text-white">
							Our story commenced in December 2023, marking a pivotal moment in our lives. It was
							then that we faced the profound loss of our cherished mother, who bravely battled
							Cholangiocarcinoma, a form of bile duct cancer, for seven months.
						</div>
					</div>
				</div>
			</div>

			<div class="flex">
				<div class="flex justify-center items-center md:w-1/2 relative">
					<div
						class="cirlce1 bg-[#FFBA41]  md:w-9 md:h-9 w-6 h-6 rounded-full absolute -left-3 md:-right-5 md:left-auto  md:top-[45%] top-[8%]"
					></div>
				
					<div class=" border-dashed border-l-4 md:border-0 md:w-[25rem] pl-3 md:pl-0 md:pr-4 pr-0 ">
						<div class="flex pb-5 justify-start md:justify-end pt-5 pl-4 md:pl-0 md:pr-4 pr-0 md:pt-0">
							<span class="text-white font-extrabold">2024, </span>
							
						</div>
						<div class="md:text-sm text-xs text-white flex flex-col gap-5">
							<div class="flex flex-row-reverse md:flex-row justify-end gap-2">
								<span class="md:text-sm text-xs md:text-end text-start gap-1">
									Led by two co-founders who have firsthand experience, infusing our mission with
									passion and deep insight.</span
								>
								<span class="w-11 h-11"><GrpOfPeople /></span>
							</div>

							<div class="flex flex-row-reverse md:flex-row justify-end gap-2">
								<span class="md:text-sm text-xs md:text-end text-start gap-1">
									Conducted extensive research to identify specific needs and gaps in cancer patient
									support services</span
								>
								<span class="w-11 h-11"><Reaserch /></span>
							</div>
							<div></div>
						</div>
					</div>
				</div>

				<div class="w-1/2 md:flex justify-center border-l-4 border-dashed border-white hidden ">
					<div class="relative">
						<div class="back bg-[#0D2460] lg:h-[18rem] lg:w-[25rem] h-[15rem] w-[20rem] rounded-2xl -rotate-2"></div>
						<div class="front absolute top-0 right-0"><img src={cofounders} alt="" /></div>
					</div>
				</div>
			</div>

			<div class="flex flex-col-reverse md:flex-row ">
				<div class=" md:w-1/2 flex justify-center  md:border-r-4 border-dashed border-white relative">
					<div class="cirlce bg-[#FFBA41] w-3 h-3 rounded-full absolute md:-right-2 -left-1 md:left-auto bottom-auto top-0 md:top-auto md:-bottom-1"></div>
					<div class="relative">
						<div class="back bg-[#0D2460] lg:h-[18rem] h-[13rem] lg:w-[25rem] w-[18rem] rounded-2xl -rotate-2"></div>
						<div class="front absolute top-0 right-0"><img src={podcast} alt="" /></div>
					</div>
				</div>
				<div class="flex justify-center items-center md:w-1/2 ">
					<div class=" md:w-[25rem] border-dashed border-l-4 md:border-l-0 md:border-0">
						<div class="flex pb-5 justify-start pl-3 md:pl-0 relative">
							<span class="text-white font-extrabold pl-3 md:pl-4">2024, </span>
							<div class="cirlce bg-[#FFBA41]  md:hidden w-6 h-6 rounded-full absolute  -left-3  "></div>
						</div>
						<div class="md:text-sm text-xs text-white flex flex-col gap-5 pl-3 md:pl-4 ">
							<div class="flex justify-start gap-2">
								<span class="w-11 h-11"><BlubIdea /></span>
								<span class="md:text-sm text-xs text-start gap-1">
									Developed a clear vision and strategic plan to guide our mission, ensuring focused
									and effective initiatives in cancer support.</span
								>
							</div>

							<div class="flex justify-start gap-2">
								<span class="w-11 h-11"><LaunchRocket /></span>
								<span class="md:text-sm text-xs text-start gap-1">
									Launched impactful community engagement programs that have cultivated an influence
									within the cancer support community.</span
								>
							</div>
							<div></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
