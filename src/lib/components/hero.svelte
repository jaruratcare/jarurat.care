<script lang="ts">

    import HeroSection1 from './Hero-Sections/hero-section1.svelte';
    import HeroSection2 from './Hero-Sections/hero-section2.svelte';
    import HeroSection3 from './Hero-Sections/hero-section3.svelte';
	import { onMount } from 'svelte';

	let slides = [
	{ id: 1, component: HeroSection1 },
	{ id: 2, component: HeroSection2 },
	{ id: 3, component: HeroSection3 }
	];

	let currentIndex: number = 0;

	const goToSlide = (index: number): void => {
	currentIndex = index;
	};

	onMount(() => {
	const interval = setInterval(() => {
		currentIndex = (currentIndex + 1) % slides.length;
	}, 5000); // Change slide every 5 seconds

	return () => clearInterval(interval);
	});

	const handleKeyPress = (event: KeyboardEvent, index: number) => {
	if (event.key === 'Enter' || event.key === ' ') {
		goToSlide(index);
	}
	};

	
</script>


<div class="relative h-screen w-full overflow-hidden flex items-center justify-center">
    <div class="carousel relative w-full h-full overflow-hidden">
        <div
            class="slides flex transition-transform duration-1000"
            style="transform: translateX(-{currentIndex * 100}%)"
        >
            {#each slides as slide}
                <div
                    class="slide min-w-full h-full flex items-center justify-center"
                    role="img"
                    aria-label={`Slide ${slide.id}`}
                >
                    <svelte:component this={slide.component} />
                </div>
            {/each}
        </div>

        <div class="absolute w-full bottom-4 flex justify-center gap-4">
            {#each slides as _, index}
                <div
                    class="indicator w-3 h-3 rounded-full bg-[#2E2F33] cursor-pointer"
                    class:active={index === currentIndex}
                    role="button"
                    aria-label={`Go to slide ${index + 1}`}
                    tabindex="0"
                    on:click={() => goToSlide(index)}
                    on:keydown={(event) => handleKeyPress(event, index)}
                ></div>
            {/each}
        </div>

				<!-- Previous Slide Button with Hover Effect -->
		<div 
		class="absolute top-1/2 left-4 transform -translate-y-1/2 flex justify-center items-center"
		>
		<button
			class="relative w-[70px] h-[70px] text-white text-3xl font-thin rounded-full overflow-hidden"
			aria-label="Previous slide"
			on:click={() => goToSlide((currentIndex - 1 + slides.length) % slides.length)}
		>
			&lt;
		</button>
		</div>

		<!-- Next Slide Button with Hover Effect -->
		<div 
		class="absolute top-1/2 right-4 transform -translate-y-1/2 flex justify-center items-center"
		>
		<button
			class="relative w-[70px] h-[70px] text-white text-3xl font-thin rounded-full overflow-hidden"
			aria-label="Next slide"
			on:click={() => goToSlide((currentIndex + 1) % slides.length)}
		>
			&gt;
		</button>
		</div>


</div>
</div>


<style>
	.carousel {
	  position: relative;
	  width: 100%;
	  overflow: hidden;
	}

	.slides {
	  display: flex;
	  transition: transform 1s ease-in-out;
	  width: 100%;
	  height: 100%;
	}

	.slide {
	  min-width: 100%;
	  height: 100%;
	  background-size: cover;
	  background-position: center;
	}


	.indicator {
	  width: 0.75rem;
	  height: 0.75rem;
	  border-radius: 50%;
	  background-color: gray;
	  cursor: pointer;
	}

	.indicator.active {
	  background-color: #FFBA41;
	}

    button {
    position: relative;
    background-color: #0155bd; 
    border-radius: 50%; 
    display: flex;
    justify-content: center;
    align-items: center;
    width: 60px;
    height: 60px;
    overflow: hidden;
    z-index: 1;
    transition: transform 0.3s ease-in-out; 
}
button:hover {
    transform: scale(1.2); 
}

button::after {
    content: '';
    display: block;
    background-color: #ffba41; 
    width: 100%;
    height: 100%;
    position: absolute;
    z-index: -1;
    top: 0;
    left: 0;
    border-radius: 50%;
    transition: transform 0.3s cubic-bezier(0.5, 0.36, 0.22, 1);
    transform: scale(0); 
    transform-origin: center;
}

button:hover::after {
    transform: scale(2); 
}



	
	@keyframes flyIn {
	  to {
		opacity: 1;
		transform: translateY(0);
	  }
	}

	@keyframes shrink {
	  to {
		width: 40%;
		top: auto;
		bottom: 0;
	  }
	}
</style>
