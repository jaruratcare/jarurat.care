<script>
    export let title = "Unique Card Title"; // Customize the title here
    export let buttonText = "Explore Now";
    export let imageSrc = ""; // Add an imageSrc prop for the image
    export let altText = ""; // Add an altText prop for accessible image description
    import Button from "../ui/button.svelte";
</script>

<div class="max-w-[420px] w-full mx-auto relative">
    <div class="h-[350px] rounded-md shadow border border-[#d3f2fc] flex flex-col overflow-hidden">
        <!-- Card Header Section with 25%/75% background split -->
        <div class="h-[25%] bg-[#e7ebf1] flex justify-center items-center">
            <div class="text-center text-[#0d2460] text-[28px] font-medium font-['Rubik'] sm:leading-tight md:text-[24px]">
                {title}
            </div>
        </div>

        <!-- Bottom 75% Section -->
        <div class="h-[75%] bg-[#f5f6f9] flex flex-col items-center relative">
            <!-- Image Section -->
            {#if imageSrc}
                <img src={imageSrc} alt={altText} class="max-w-full h-auto absolute bottom-0" />
            {/if}
        </div>
    </div>

    <!-- Button Section positioned half inside the card and half outside -->
    <div class="absolute -bottom-4 left-1/2 transform -translate-x-1/2">
        <Button class="bg-[#0155BD] transition-transform transform hover:scale-110 duration-300 ease-in-out">
            {buttonText}
        </Button>
    </div>
</div>

<style>
    /* Custom styles can be added here if needed */
</style>