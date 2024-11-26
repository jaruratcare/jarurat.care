<script>
    import bg from '../../assets/landing-page/bg1.png';
    import Button from '../ui/button.svelte';
    import Button1 from '../ui/tbutton.svelte';
</script>

<div
    class="relative h-screen w-full overflow-hidden flex items-center justify-center p-4 md:justify-start md:pl-10 mt-20"
    style="
        background-image: 
            linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)),
            linear-gradient(rgba(211, 242, 252, 0.3), rgba(211, 242, 252, 0.3)),
            url({bg}); 
        background-size: cover;
        background-position: center;
        background-blend-mode: screen;
    "
>

    <!-- Hero Section -->
    <div class="w-full max-w-3xl md:max-w-lg lg:max-w-xl xl:max-w-2xl h-auto flex-col justify-start items-center gap-8 md:gap-12 inline-flex ml-6 text-center md:text-left">
        <div class="flex-col justify-start items-center gap-5 md:gap-8 flex">
            <div class="px-5 flex justify-center items-center gap-2.5">
                <div class="text-[#0d2460] text-xl md:text-2xl font-extrabold font-['Manrope'] uppercase leading-tight">Jaisi JARURAT Vaisi CARE</div>
            </div>
            <div class="flex-col justify-start items-center flex">
                <div class="flex-col justify-center items-center gap-2.5 flex">
                    <div class="w-full max-w-xs md:max-w-full text-white text-3xl sm:text-4xl md:text-5xl lg:text-6xl font-semibold font-['Rubik'] leading-tight">Empowering <br />Cancer Patients</div>
                </div>
                <div class="justify-center md:justify-start items-center gap-3 flex flex-wrap">
                    <div class="text-white text-3xl sm:text-4xl md:text-5xl lg:text-6xl font-semibold font-['Rubik'] leading-tight">with</div>
                    <div class="text-[#0d2460] text-3xl sm:text-4xl md:text-5xl lg:text-6xl font-semibold font-['Rubik'] leading-tight">“Care”</div>
                </div>
            </div>
        </div>
        <div class="justify-center md:justify-start items-center gap-3 md:gap-5 inline-flex">
            <a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
                <Button1 class="transition transform hover:scale-110 duration-300 ease-in-out">Volunteer</Button1>
            </a>
            <a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
                <Button class="bg-[#0155BD] transition transform hover:scale-110 duration-300 ease-in-out">Seek Support</Button>
            </a>
        </div>
    </div>
</div>
