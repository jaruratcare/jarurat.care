<script>
export let content;
</script>

<div class="relative pt-9 w-full flex justify-center items-center">
    <!-- Background Card -->
    <div class="back bg-[#0D2561] -rotate-1 rounded-2xl md:w-[350px] w-full h-[250px]"></div>

    <!-- Foreground Card -->
    <div class="front bg-[#FFFFFF] absolute bottom-2 left-1 rounded-2xl md:w-[350px] w-full h-[250px] flex justify-center items-center text-center md:px-3">
        <!-- Content Box that Shrinks or Wraps -->
        <div class="w-full h-full flex flex-col justify-center items-center">
            <div class="w-full max-h-full text-sm sm:text-base text-center break-words leading-tight overflow-hidden">
                {content}
            </div>
        </div>
    </div>
</div>



