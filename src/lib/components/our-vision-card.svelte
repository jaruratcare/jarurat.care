<script>
export let content;
</script>

<div class="relative w-full">
    <div class="back bg-[#0D2561] -rotate-1 rounded-2xl md:w-[350px] w-full h-[250px]"></div>
    <div class="front bg-[#FFFFFF] absolute bottom-2 left-1 rounded-2xl md:w-[350px] w-full h-[250px] flex justify-center items-center text-center md:px-3">
        {content}
    </div>
</div>

