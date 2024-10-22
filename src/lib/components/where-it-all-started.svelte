<script>
  import WhereItAllStartedwave from "$lib/svg/where-it-all-startedwave.svelte";
  import Header from "./ui/header.svelte";
  import { Splide, SplideSlide, SplideTrack } from '@splidejs/svelte-splide';
  import { ChevronLeft, ChevronRight, QuoteIcon } from 'lucide-svelte';
  import Button from './ui/button.svelte';
  import ImgMom from '$lib/assets/mom.webp';
  import OurVisionCard from "./our-vision-card.svelte";
	import OurVisionCards from "./LandingPageComponents/cards/our-visionCards.svelte";
  
  const slider = [
		{
			title: 'Why Cancer',
			content: `Cancer remains one of the leading causes of death worldwide, affecting millions of lives each year. Despite advancements in research and treatment, many people still lack access to the care and support they need. By focusing on cancer, we aim to raise awareness, provide vital resources, and drive efforts toward early detection, effective treatment.`
		},
		{
			title: 'Why Donate',
			content: `Donations are the lifeblood of Jarurat Care, empowering us to provide critical support to cancer patients who need it most. Your contributions directly fund life-saving treatments, emotional care, and essential services that make a real difference in people's lives. Every donation brings us one step closer to a world where no one has to face cancer alone. Your support is not just important—it's transformative.`
		},
		{
			title: 'Why Collaborate',
			content: `Join us because together, we can do so much more. By collaborating, we pool our strengths and resources to bring greater support to those fighting cancer. Your partnership helps us reach more people and make a bigger impact. Let’s work hand in hand to change lives.`
		}
	];
	const card = [
		{
			title: 'Empower',
			subtitle:'cancer patients and survivors to take control of their health and well-being through expert care services'
		},
		{
			
			title: 'Connect', 
			subtitle:'patients to cancer care & community, regardless of their socioeconomic status or location.'		
		},
		{
			title: 'Advocate',
			subtitle:'public awareness and education about cancer prevention, detection, and treatment.'
			}
	];
</script>


<div class="whereitallstarted overflow-hidden">
<WhereItAllStartedwave/>

<div class="w-full h-[fit] px-[210px] pt-[90px] pb-[91px] bg-[#0c2461] justify-center items-center flex-col">
   <Header color="#ffffff" title="Where it all" spantitle="started"></Header>

   <!-- Where it all Started -->
<div id="about" class="py-8 sm:py-32 max-w-[60rem] mx-auto">
	<Splide hasTrack={false} options={{ perPage: 1, type: 'loop', gap: '2rem', pagination: true }}>
		<div
			class="splide__arrows items-center justify-between gap-2 absolute -inset-x-16 h-full hidden xl:flex"
		>
			<Button class="splide__arrow splide__arrow--prev size-12 p-0 items-center border border-white">
				<ChevronLeft class="w-full" />
			</Button>

			<Button class="splide__arrow splide__arrow--next size-12 p-0 flex items-center bg-[#2CBFE2] ">
				<ChevronRight class="w-full" />
			</Button>
		</div>

		<SplideTrack>
			{#each slider as slide}
				<SplideSlide class="p-1 aspect-[16/9] ">
					<div class="size-full relative rounded-xl sm:rounded-3xl overflow-hidden">
						<img src={ImgMom} alt="Rekha Joshi - Jarurat Care" class="size-full object-cover" />
						<div class="absolute inset-0 bg-[#002944]/40 flex items-center justify-center">
                        <div class="absolute inset-0 bg-[#0c2461]/50 flex items-center justify-center w-[40%]">

							<div
								class="absolute top-4 left-0 max-w-[28rem] p-4 sm:p-8 text-[#DBE1E6] text-[0.8em] sm:text-[1.2em] leading-[1.3]"
							>
                            <div><span class="text-[#ffba41] text-[22px] font-normal font-['Rubik'] leading-normal">“She was a dedicated and kind spirited woman” <br/></span><span style="text-white text-lg font-normal font-['Rubik'] leading-normal"><br/>No one in the family was ready to hear her diagnosis. Her daughter Priyanka and family diligently helped Rekha ji to fight her battle.<br/><br/>It was Rekha ji’s last trip before she started her battle with Cholangiocarcinoma and succumbed on 25th December2023.<br/></span></div>
							</div>
						</div>
                        </div>
					</div>
				</SplideSlide>

				<SplideSlide class="p-1 aspect-[16/9] ">
					<div class="size-full relative rounded-xl sm:rounded-3xl overflow-hidden">
						<img src={ImgMom} alt="Rekha Joshi - Jarurat Care" class="size-full object-cover" />
						<div class="absolute inset-0 bg-[#002944]/65 flex items-center justify-center">
							<div
								class="absolute bottom-0 left-0 max-w-[28rem] p-4 sm:p-8 text-[#DBE1E6] text-[0.8em] sm:text-[1.2em] leading-[1.3]"
							>
								<h3 class="block sm:hidden text-[1.5em] font-black text-[#F9FDFE] mb-2">
									WHERE IT ALL <span class="text-[#FFBA41]">STARTED</span>
								</h3>

								Rekha's last trip before she started her battle with Cholangiocarcinoma and
								succumbed on 25th December, 2023
							</div>
						</div>
					</div>
				</SplideSlide>
			{/each}
		</SplideTrack>
	</Splide>
</div>

<div class="w-fit max-w-[1200px] mx-auto p-10 h-[450px] bg-gradient-to-b from-[#0c245f] to-[#1055bd] rounded-[40px] flex flex-col justify-center items-center">
    <!-- Title -->
    <div class="text-center mb-6">
        <span class="text-white text-[40px] font-medium font-['Rubik'] capitalize">Our </span>
        <span class="text-[#ffba41] text-[40px] font-medium font-['Rubik'] capitalize">Vision</span>
    </div>

    <!-- Vision Cards -->
    <div class="w-full max-w-[1000px] flex justify-between items-center gap-6 px-4">
        <OurVisionCards title={card[0].title} subtitle={card[0].subtitle}></OurVisionCards>
        <OurVisionCards title={card[1].title} subtitle={card[1].subtitle}></OurVisionCards>
        <OurVisionCards title={card[2].title} subtitle={card[2].subtitle}></OurVisionCards>
    </div>
</div>
</div>
</div>
 
