<script>
export let image ;
export let name;
export let about;
</script>
<div class="relative mx-auto w-[250px] transition-transform duration-300 hover:scale-105">
    <div class="bg-primaryBlue w-[250px] h-[340px] -rotate-2 rounded-xl mx-auto"></div>

    <div class="absolute top-0 left-0 w-[250px] h-[340px] rounded-xl overflow-hidden">
        <div class="img h-[280px]">
            <img class="w-full h-full object-cover rounded-t-xl" src={image} alt="profileImg">
        </div>
        <div class="absolute bottom-0 left-0 right-0 bg-white rounded-b-xl shadow-sm">
            <div class="about text-center py-4 px-3">
                <div class="name text-[#0D2460] font-bold text-sm">{name}</div>
                <div class="text-primaryBlue text-xs mt-1">{about}</div>
            </div>
        </div>
    </div>
</div>