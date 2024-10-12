<script>
	import Button from '../ui/button.svelte';
    import SupportUs from '$lib/svg/donate/support-us.svelte';
    import UpdateImgOne from '$lib/assets/donate/update-frame-one.webp';
    import UpdateImgTwo from '$lib/assets/donate/update-frame-two.webp';
    import UpdateImgThree from '$lib/assets/donate/update-frame-three.webp';
    import DonateTodayTopBg from '$lib/assets/donate/frame-donate-today.webp';
</script>

<div>
    <div class="py-4 sm:py-12 rounded-lg p-2">
        <div class="md:flex block items-center justify-center relative md:bg-transparent bg-[#D3F2FC] p-4 rounded-lg">
            <SupportUs/>
            <div class="z-20 flex flex-col gap-2">
                <h2 class="text-[1.2em] sm:text-[1.8em] md:text-start text-center font-bold text-[#0155BD] leading-tight">
                    Support Us
                </h2>
                <p class="text-[#0D2561] md:text-start text-center font-medium leading-snug text-[0.7em] sm:text-[0.8em] md:w-[48%] w-[100%]">
                    All donations to JaruratCare Foundation are eligible for 50% tax exemption under section 80G of the Income Tax Act.
                </p>
            </div>
            <div class="flex items-center justify-center pt-8">
                <Button class="z-20 custom-button md:py-3 py-2 px-4 md:px-6">Donate Now</Button>
            </div>
        </div>
    </div>

    <div class="bg-[#0D2561] mt-4 md:block hidden overflow-hidden">
        <div class="scrolling-text py-6 flex items-center justify-start whitespace-nowrap">
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
            <span class="text-white">Monthly Updates</span>
            <span class="text-[#ffba41] mx-2">•</span>
        </div>

        <div class="py-12 px-16">
            <div class="md:flex items-center justify-center md:gap-6 gap-4 relative">
                <div class="w-1/3 h-auto">
                    <img class="bg-cover bg-center h-full w-full overflow-hidden" src="{UpdateImgOne}" alt="">
                </div>
                
                <div class="w-1/3 h-auto">
                    <img class="bg-cover bg-center h-full w-full  overflow-hidden" src="{UpdateImgTwo}" alt="">
                </div>
                
                <div class="w-1/3 h-auto">
                    <img class="bg-cover bg-center h-full w-full overflow-hidden" src="{UpdateImgThree}" alt="">
                </div>
            </div>


        </div>

    </div>

    <div class="bg-[#0D2561] py-8 md:flex hidden">
        <div class="bg-[#0155BD] min-w-[22rem] md:w-full mx-auto rounded-xl md:rounded-xl drop-shadow-xl mb-10 md:mb-0 lg:w-1/2 md:px-24 px-2 py-4">
            <div class=" md:px-6"> 
                <div class="mb-7">
                    <div class="text-white text-center font-bold text-2xl mb-1">Subscribe to our <span class="text-[#ffba41]">Newsletter</span></div>
                    <div class="text-[0.6rem] text-white text-center">Get the latest updates, inspiring stories, and important news delivered right to your inbox.</div>
                </div>
                <div class="gap-6 flex flex-col md:flex-row">
                    <input type="text" class="rounded-lg md:w-96 px-2 py-2 placeholder:text-[0.6rem] md:placeholder:text-[0.8rem]" placeholder="Enter you email address">
                    <button class="text-[#ffba41] border border-[#ffba41] font-bold px-6 py-1 rounded-3xl"> Submit</button>
                </div>
            </div>
        </div>
    </div>

    <div class="md:hidden font-manrope flex flex-col items-center px-3 py-16">
        <div style="background-image: url('{DonateTodayTopBg}'); background-position: top; background-repeat: no-repeat; background-size: cover;" class="w-full md:h-[0.9em] h-[0.5em] z-10 rounded-t-2xl overflow-hidden"></div>
        <div class="flex flex-col item-center shadow-md rounded-lg p-4 gap-3">
            <h2 class="font-rubik text-[#0D2561] text-center font-medium text-[1.5em] leading-tight py-2">
                Subscribe to <br>our <span class="text-[#0155BD]">Newsletter</span>
            </h2>
            <p class="text-[#0D2561] text-center text-[0.8em] font-semibold leading-tight">
                Get the latest updates, inspiring stories, and important news delivered right to your inbox.
            </p>
            <div class="gap-6 flex flex-col items-center justify-center">
                <input type="text" class="rounded-lg w-full text-black px-2 py-2 bg-[#fff0cd] placeholder:text-[0.7rem]" placeholder="Enter you email address">
                <Button class=" text-center px-8 text-sm">Submit</Button>
            </div>
        </div>
    </div>
</div>

<style>
@keyframes scroll {
    0% {
        transform: translateX(0%);
    }
    100% {
        transform: translateX(-10%);
    }
}

.scrolling-text {
    display: inline-flex;
    animation: scroll 1s linear infinite;
}

.scrolling-text span {
    white-space: nowrap;
}

@media (min-width: 640px) {
    .scrolling-text {
        animation-duration: 2s;
    }
}

@media (min-width: 1024px) {
    .scrolling-text {
        animation-duration: 1s;
    }
}


</style>