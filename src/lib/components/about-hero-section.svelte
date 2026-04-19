<script>
    import background from "$lib/assets/about-hero-section.jpg";
</script>

<div class="relative">

    <div
        class="w-full h-72 md:h-96 bg-cover bg-center relative"
        style={`background-image: url(${background})`}
    >

        <!-- Gradient overlay -->
        <div class="absolute inset-0 bg-gradient-to-r from-white/50 via-white/25 to-transparent md:from-black/50 md:via-black/30 md:to-transparent"></div>

        <div class="font-extrabold text-3xl text-white md:hidden flex justify-start items-end h-full relative px-6 pb-6">
            About Us
        </div>

    </div>

    <div class="text-primaryBlue md:text-white mt-7 md:mt-0 text-center md:text-start max-w-[520px] px-6 md:px-0 md:absolute md:bottom-12 md:left-12 relative">

        <!-- Stronger title -->
        <div class="font-extrabold text-4xl md:text-5xl leading-tight">
            What We Do
        </div>

        <!-- Improved readability -->
        <div class="text-base md:text-lg mt-4 text-gray-700 md:text-gray-100">
            Supporting the physical and emotional needs of a cancer patient empowers them to
            contribute positively to society, turning them into a source of strength rather
            than burden.
        </div>

    </div>

</div>