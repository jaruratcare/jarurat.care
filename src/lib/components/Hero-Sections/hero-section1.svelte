<script>
    import bg from '../../assets/landing-page/bg1.png';
    import Button from '../ui/button.svelte';
    import Button1 from '../ui/tbutton.svelte';
</script>

<div
    class="relative h-screen w-full overflow-hidden flex items-center justify-start p-4 pl-10 mt-20"
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
    <div class="w-[600px] h-[383px] flex-col justify-start items-center gap-[50px] inline-flex ml-6">
        <div class="self-stretch h-[269px] flex-col justify-start items-center gap-[21px] flex">
            <div class="px-5 justify-center items-start gap-2.5 inline-flex">
                <div class="text-center text-[#0d2460] text-2xl font-extrabold font-['Manrope'] uppercase leading-[28.80px]">Jaisi JARURAT Vaisi CARE</div>
            </div>
            <div class="self-stretch h-[219px] flex-col justify-start items-center flex">
                <div class="self-stretch h-[146px] flex-col justify-center items-center gap-2.5 flex">
                    <div class="w-[634px] text-center text-white text-[70px] font-semibold font-['Rubik'] leading-[73.50px]">Empowering <br/>Cancer Patients </div>
                </div>
                <div class="justify-start items-end gap-[15px] inline-flex">
                    <div class="text-center text-white text-[70px] font-semibold font-['Rubik'] leading-[73.50px]">with</div>
                    <div class="text-center text-[#0d2460] text-[70px] font-semibold font-['Rubik'] leading-[73.50px]">“Care”</div>
                </div>
            </div>
        </div>
        <div class="justify-start items-center gap-5 inline-flex">
            <a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
                <Button1 class="transition transform hover:scale-110 duration-300 ease-in-out">Volunteer</Button1>
            </a>
            <a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
                <Button class="bg-[#0155BD] transition transform hover:scale-110 duration-300 ease-in-out">Seek Support</Button>
            </a>
        </div>
    </div>
</div>

