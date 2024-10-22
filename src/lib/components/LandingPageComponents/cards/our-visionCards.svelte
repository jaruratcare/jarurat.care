<script>
    export let title = "";
    export let subtitle = "";
    import Pin from "$lib/assets/landing-page/ourvision/pin.png";
  </script>
  
  <div class="w-[320px] h-[240px] relative flex flex-col justify-start items-center group">
    
    <!-- Rotated shadow or background card -->
    <div class="w-[310px] h-[230px] rotate-[-2.42deg] bg-[#d3f2fc] rounded-2xl absolute transition-transform duration-300 ease-in-out group-hover:rotate-0 group-hover:hidden"></div>
  
    <!-- Background card with scaling effect on hover -->
    <div class="w-[310px] h-[230px] bg-[#f8fcfe] rounded-2xl border-2 absolute transition-transform duration-300 ease-in-out group-hover:scale-90"></div>
  
    <!-- Pin image -->
    <img class="w-[30px] h-8 relative" src={Pin} alt="loading" />   
  
    <!-- Text content -->
    <div class="w-[280px] text-center relative mt-6 items-center justify-center h-full">
      <span class="text-[#0c2461] text-[24px] font-medium font-['Rubik'] leading-7">{title}</span>
      <span class="text-[#232729] text-base font-medium font-['Manrope'] leading-6 mt-2 block">
          {subtitle}
      </span>
    </div>
  </div>
  
  <style>
    @keyframes rotateBackAndForth {
      0% {
        transform: rotate(0deg);
      }
      50% {
        transform: rotate(-1.42deg);
      }
      100% {
        transform: rotate(0deg);
      }
    }
  
    /* Applying the animation to the entire container */
    .group {
      animation: rotateBackAndForth 1.5s infinite ease-in-out;
      animation-play-state: running; /* Ensure it's running by default */
    }
  
    /* Stop rotation on hover */
    .group:hover {
      animation-play-state: paused; /* Pause the animation on hover */
    }
  </style>
  