<script>
	import Colon from "$lib/svg/colon.svelte";


export let image ;
export let name;
export let about;
export let position;

</script>

<div class="bg-white rounded-2xl md:w-[480px] w-[323px] flex flex-col justify-center gap-6 pl-8 pt-14  pr-14 pb-24 shadow-md">
    <div class="flex justify-between ">
        <div class="flex gap-7">
            <div><img src={image} alt="profile"></div>
            <div> 
                <div class="text-[#0155BD] font-extrabold">{name}</div>
                <div class="text-[#0155BD] font-semibold">{position}</div>
            </div>
        </div>
        <div class="w-8 h-8"><Colon/></div>
    </div>
    <div>{about}</div>
</div>