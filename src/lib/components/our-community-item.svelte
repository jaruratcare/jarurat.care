<script>
import CardBakgound from "$lib/svg/card-bakgound.svelte";
export let icon ;
export let title;
export let about;
</script>


<div>
    <div class="background relative md:max-w-[21rem] ">
        <div class="w-44 h-64 lg:w-60 lg:h-96 "><CardBakgound/></div>
        
        <div class="icon w-20 lg:w-32 bg-white rounded-full  left-[15%] md:left-[20%] top-[-50px] lg:top-[-47px] absolute   z-10 "><svelte:component this={icon} /></div>
        <div class="absolute  lg:top-28 top-14 lg:w-56 w-48 lg:left-[2%] left-0  px-4">
            <div class="title lg:text-2xl text-lg font-bold  text-center lg:mb-5 mb-2">{title}</div>
            <div class="title lg:text-sm text-xs text-center">{about}</div>
        </div>
    </div>
</div>

