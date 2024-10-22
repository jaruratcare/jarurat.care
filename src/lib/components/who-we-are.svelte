<script>
	import img from '../assets/landing-page/page2/handglow.png'
    import  Contact from '../components/for-personalized-disease.svelte'
    import Button from './ui/button.svelte';
    import Button1 from './ui/tbutton.svelte';
	import IndiamapSvg from '$lib/svg/indiamap-svg.svelte';
    let isHovered = false;
</script>
<Contact/>

<div class="w-full h-auto bg-[#0c2461] flex justify-center items-center">
    <div class="w-[80%] h-[80%] flex justify-between items-center gap-[272px] mt-10">
        <div
        role="button" 
        tabindex="0"
        class="relative w-[378.07px] h-[419.57px]"
        on:mouseenter={() => isHovered = true}
        on:mouseleave={() => isHovered = false}
        on:keydown={(e) => { if (e.key === 'Enter') isHovered = !isHovered }} 
    >
        <div class="absolute w-[378.07px] h-[419.57px]">
            <div class={isHovered ? 'glow w-[202px] h-[202px] left-12 top-12 absolute bg-white rounded-full opacity-60 blur-[100px]' : 'hidden'}></div>
            <img class="absolute w-[351.89px] h-[396.52px] origin-top-left rotate-[3.91deg]" src={img} alt="loading" />
        </div>
    </div>
        <div class="flex flex-col justify-center items-start gap-14">
            <div class="w-[677px]">
                <span class="text-[#e7ebf1] text-2xl font-normal font-['Manrope'] leading-[38.85px]">At </span>
                <span class="text-[#ffba41] text-[40.47px] font-semibold font-['Manrope'] leading-[64.74px]">Jarurat Care</span>
                <span class="text-[#e7ebf1] text-2xl font-normal font-['Manrope'] leading-[38.85px]">, we believe no one should face cancer alone. We provide vital medical support, emotional care, and financial assistance to help patients and their families navigate this difficult journey. Together, we bring hope, healing, and a compassionate community to those who need it most.</span>
            </div>
            <div class="flex justify-start items-center gap-5">
                <a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
                    <Button1 class="transition transform hover:scale-110 duration-300 ease-in-out">Volunteer</Button1>
                </a>
                <a href="mailto:jaruratcare@gmail.com?subject=Seek%20Support%20For%20Cancer">
                    <Button class="bg-[#0155BD] transition transform hover:scale-110 duration-300 ease-in-out">Seek Support</Button>
                </a>
            </div>
        </div>
    </div>
    
</div>

<div class="w-full h-screen bg-gradient-to-b from-[#0d2561] to-[#0c235b] relative flex flex-col items-center">
    <div class="map absolute left-1/2 top-1/3"><IndiamapSvg/></div>
    <div class="w-[80%] absolute top-1/4">
        <span class="text-white text-[40px] font-medium font-['Rubik'] leading-[64px]">Our </span>
        <span class="text-[#ffbe41] text-[40px] font-medium font-['Rubik'] leading-[64px]">Impact</span>
    </div>
    <div class="flex justify-between absolute top-1/3">
        <div class="min-w-[292px] h-[151px] p-5 rounded-[15px] flex-col justify-center items-start gap-10 inline-flex">
            <div class="h-[63px] flex-col justify-start items-start gap-2.5 flex">
                <div class="self-stretch h-[63px] bg-[#d9d9d9]"></div>
                <div class="w-auto text-[#2cbfe2] text-[64px] font-medium font-['Manrope'] leading-[64px]">0</div>
            </div>
            <div class="self-stretch text-white text-xl font-normal font-['Rubik'] leading-[19px]">Mentor</div>
        </div>
        <div class="min-w-[292px] h-[151px] p-5 rounded-[15px] flex-col justify-center items-start gap-10 inline-flex">
            <div class="h-[63px] flex-col justify-start items-start gap-2.5 flex">
                <div class="self-stretch h-[63px] bg-[#d9d9d9]"></div>
                <div class="w-auto text-[#2cbfe2] text-[64px] font-medium font-['Manrope'] leading-[64px]">0</div>
            </div>
            <div class="self-stretch text-white text-xl font-normal font-['Rubik'] leading-[19px]">Doctors</div>
        </div>
        <div class="min-w-[292px] h-[151px] p-5 rounded-[15px] flex-col justify-center items-start gap-10 inline-flex">
            <div class="h-[63px] flex-col justify-start items-start gap-2.5 flex">
                <div class="self-stretch h-[63px] bg-[#d9d9d9]"></div>
                <div class="w-auto text-[#2cbfe2] text-[64px] font-medium font-['Manrope'] leading-[64px]">0</div>
            </div>
            <div class="self-stretch text-white text-xl font-normal font-['Rubik'] leading-[19px]">People</div>
        </div>
        <div class="min-w-[292px] h-[151px] p-5 rounded-[15px] flex-col justify-center items-start gap-10 inline-flex">
            <div class="flex justify-start items-center gap-2">
                <div class="w-auto h-[63px] relative flex items-center">
                    <div class="w-[91px] h-[63px] left-0 top-0 absolute"></div>
                    <div class="left-0 top-0 absolute flex gap-4">
                        <span class="text-[#2cbfe2] text-[64px] font-semibold font-['Manrope'] leading-[64px]">0<br/></span>
                        <span class="text-[#2cbfe2] text-[64px] font-medium font-['Manrope'] leading-[64px]">%</span>

                    </div>
                </div>
            </div>
            <div class="text-white text-xl font-normal font-['Rubik'] leading-[19px]">Early Treatments Initiated</div>
        </div>
    </div>
    
    
    
    
</div>





<style>
 .glow {
        transition: opacity 0.3s ease; 
    }

</style>