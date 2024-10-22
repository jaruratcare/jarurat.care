<script>
    export let title = "Card Title";
    export let buttonText = "Explore Now";
    export let imageSrc = ""; // Add an imageSrc prop for the image
    export let altText = ""; // Add an altText prop for accessible image description
    import Button from "../ui/button.svelte";
</script>
<div class="w-[420px] h-[350px] cards relative flex flex-col items-center">
<div class=" w-[420px] h-[346px] rounded-[5px] shadow border border-[#d3f2fc] flex flex-col items-center justify-between gap-4 overflow-hidden">
    
    <!-- Card Header Section with 25%/75% background split -->
    <div class="w-full h-full flex flex-col justify-between">
        <!-- Top 25% Section -->
        <div class="w-full h-[25%] bg-[#e7ebf1] flex justify-center items-center">
            <div class="text-center text-[#0d2460] text-[28px] font-medium font-['Rubik'] leading-[33.60px]">{title}</div>
        </div>

        <!-- Bottom 75% Section -->
        <div class="w-full h-[75%] bg-[#f5f6f9] flex flex-col items-center relative">
            <!-- Image Section -->
            {#if imageSrc}
                <img src={imageSrc} alt={altText} class="w-fit h-[180px] absolute bottom-0" />
            {/if}
            

        </div>
    </div>
</div>
            <!-- Button Section -->
            <div class="absolute bottom-0 flex justify-center items-center">
                <Button class="bg-[#0155BD] transition transform hover:scale-110 duration-300 ease-in-out">
                    {buttonText}
                </Button>
            </div>
            <br>
</div>

