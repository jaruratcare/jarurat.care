<script>
    export let content;
    </script>
    
    <div class="relative w-full mt-9">
        <div class="back bg-[#0D2561] -rotate-1 rounded-2xl w-[350px] h-[250px]"></div>
        <div class="front bg-[#FFFFFF] absolute bottom-2 left-1 rounded-2xl w-[350px] h-[250px] flex justify-center items-center text-center p-2">
            {content}
        </div>
    </div>