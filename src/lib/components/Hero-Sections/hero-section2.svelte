<script>
    import bg from '../../assets/landing-page/bg3.png';
    import Button from '../ui/button.svelte';
</script>

<div
    class="relative h-screen w-full overflow-hidden flex items-center justify-start p-4 pl-10 mt-20"
    style="background-image: linear-gradient(to right, rgba(121, 202, 239, 0.5), rgba(69, 116, 137, 0.1)), url({bg}); background-size: cover; background-position: center;"
>



    <!-- Hero Section -->
    <div class="w-[600px] h-[383px] flex-col justify-start items-center gap-[50px] inline-flex">
        <div class="self-stretch h-[269px] flex-col justify-start items-center gap-[21px] flex">
            <div class="px-5 justify-center items-start gap-2.5 inline-flex">
                <div class="text-center text-[#0d2460] text-2xl font-extrabold font-['Manrope'] uppercase leading-[28.80px]">Jaisi JARURAT Vaisi CARE</div>
            </div>
            <div class="self-stretch h-[219px] flex-col justify-start items-center flex">
                <div class="self-stretch h-[73px] flex-col justify-center items-center gap-2.5 flex">
                    <div class="w-[634px] text-center text-white text-[70px] font-semibold font-['Rubik'] leading-[73.50px]">Make A</div>
                </div>
                <div class="justify-start items-end gap-[15px] inline-flex">
                    <div class="text-center"><span class="text-[#0d2460] text-[70px] font-semibold font-['Rubik'] leading-[73.50px]">“Difference”<br/></span><span class="text-white text-[70px] font-semibold font-['Rubik'] leading-[73.50px]">Today !</span></div>
                </div>
            </div>
        </div>
        <a href="#donate">
            <Button class="bg-[#0155BD] transition transform hover:scale-110 duration-300 ease-in-out">Donate Today</Button>
        </a>
    </div>
</div>
