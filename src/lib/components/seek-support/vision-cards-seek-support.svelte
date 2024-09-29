<script>
    export let content;
    </script>
    
    <div class="relative w-full">
        <div class="back bg-[#0D2561] -rotate-1 rounded-2xl w-[60%] h-[18rem]"></div>
        <div class="front bg-[#FFFFFF] absolute bottom-2 left-1 rounded-2xl w-[60%] h-[18rem] flex justify-center items-center text-center">
            {content}
        </div>
    </div>