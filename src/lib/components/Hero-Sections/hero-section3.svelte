<script>
    import bg from '../../assets/landing-page/bg2.png';
    import Button from '../ui/button.svelte';
</script>

<div
    class="relative h-screen w-full overflow-hidden flex items-center justify-center p-4 md:justify-start md:pl-10 mt-20"
    style="background-image: linear-gradient(to right, rgba(121, 202, 239, 0.5), rgba(69, 116, 137, 0.1)), url({bg}); background-size: cover; background-position: center;"
>

    <!-- Hero Section -->
    <div class="w-full max-w-3xl md:max-w-lg lg:max-w-xl xl:max-w-2xl h-auto flex-col justify-start items-center gap-8 md:gap-12 inline-flex text-center md:text-left">
        <div class="flex-col justify-start items-center gap-5 md:gap-8 flex">
            <div class="px-5 flex justify-center items-center gap-2.5">
                <div class="text-[#0d2460] text-xl md:text-2xl font-extrabold font-['Manrope'] uppercase leading-tight">Jaisi JARURAT Vaisi CARE</div>
            </div>
            <div class="flex-col justify-start items-center flex">
                <div class="flex-col justify-center items-center gap-2.5 flex">
                    <div class="w-full max-w-xs md:max-w-full text-white text-3xl sm:text-4xl md:text-5xl lg:text-6xl font-semibold font-['Rubik'] leading-tight">You Could<br/>Make Them</div>
                </div>
                <div class="justify-center md:justify-start items-center gap-3 flex flex-wrap">
                    <div class="text-center">
                        <span class="text-[#0d2460] text-3xl sm:text-4xl md:text-5xl lg:text-6xl font-semibold font-['Rubik'] leading-tight">“Happy.”</span>
                    </div>
                </div>
            </div>
        </div>
        <a href="#get" class="mt-5 md:mt-10">
            <Button class="bg-[#0155BD] transition transform hover:scale-110 duration-300 ease-in-out">Get Involved</Button>
        </a>
    </div>
</div>
