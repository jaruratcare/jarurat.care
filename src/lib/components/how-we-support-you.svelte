<script>
  import { cn } from '$lib/utils';
  import Button from './ui/button.svelte';
  import Header from './ui/header.svelte';
  import PaperPlane from '$lib/svg/paper-plane.svelte';

  const cards = [
    {
      title: 'Patient Advocacy',
      description:
        'We stand by your side, helping navigate treatment options and healthcare systems.',
      services: ['Insurance Assistance', 'Legal Support', 'Healthcare Navigation'],
      class: 'ring-[#B0DFF9]'
    },
    {
      title: 'Access To Treatment',
      description:
        'We stand by your side, helping navigate treatment options and healthcare systems.',
      services: ['Individual Counseling', 'Support Groups', '24/7 Helpline'],
      class: 'ring-[#E4CFFF]'
    },
    {
      title: 'Holistic Care Options',
      description:
        'We stand by your side, helping navigate treatment options and healthcare systems.',
      services: ['Nutrition Counseling', 'Stress Management', 'Wellness Programs'],
      class: 'ring-[#96f3d7]'
    },
    {
      title: 'Patient Advocacy',
      description:
        'We stand by your side, helping navigate treatment options and healthcare systems.',
      services: ['Insurance Assistance', 'Legal Support', 'Healthcare Navigation'],
      class: 'ring-[#FFC1F6]'
    },
    {
      title: 'Access To Treatment',
      description:
        'We stand by your side, helping navigate treatment options and healthcare systems.',
      services: ['Individual Counseling', 'Support Groups', '24/7 Helpline'],
      class: 'ring-[#E4CFFF]'
    },
    {
      title: 'Holistic Care Options',
      description:
        'We stand by your side, helping navigate treatment options and healthcare systems.',
      services: ['Nutrition Counseling', 'Stress Management', 'Wellness Programs'],
      class: 'ring-[#FFC1F6]'
    }
  ];
</script>

<div class="py-12 sm:py-28 flex flex-col relative overflow-hidden">
  <div class="absolute inset-x-0 top-0 z-0">
    <div
      class="w-full aspect-square -translate-y-[90%] scale-[4] sm:scale-[1.4] rounded-full bg-[#DCF2FF]"
      style="opacity: 0.75;"
    ></div>
  </div>

  <Header
    class="z-10"
    title="How we Support You"
    subtitle="Comprehensive care and support throughout your cancer journey"
  />

  <div
    class="max-w-[60rem] w-full mx-auto grid grid-cols-1 sm:grid-cols-3 gap-4 sm:gap-16 p-4 my-8"
  >
    {#each cards as card}
      <div class="flex flex-col max-w-[80%] sm:max-w-fit mx-auto">
        <div class="w-full h-[2rem] sm:h-[2.5rem] relative z-10 flex items-center justify-center">
          <div
            class="aspect-square w-[4rem] sm:w-[5rem] translate-y-1/4 rounded-full bg-gradient-to-br from-[#A4D9FB] to-[#F6FCFF] p-[0.1rem]"
          >
            <div class="size-full bg-white flex items-center justify-center rounded-full">
              <PaperPlane class="size-12 text-[#2C72B9]" />
            </div>
          </div>
        </div>

        <div
          class={cn(
            `px-8 sm:px-8 py-6 sm:py-12 rounded-xl sm:rounded-2xl ring bg-white`,
            card.class,
            `flex flex-col items-center gap-2 sm:gap-4 text-center relative grow`
          )}
        >
          <div class="flex flex-col gap-2 sm:gap-4 grow mt-6 size-full">
            <h3
              class="font-manrope font-bold text-[1.1em] sm:text-[1.3em] leading-[1] text-[#0154BC]"
            >
              {card.title}
            </h3>

            <p class="font-manrope font-medium text-[0.9em] text-[#0D2561]">{card.description}</p>

            <div class="max-w-[80%] mx-auto">
              {#each card.services as service}
                <div class="w-full flex gap-2 items-center justify-start text-sm">
                  <div class="aspect-square w-2 bg-[#0154BC] rounded-full"></div>
                  <div class="text-[#0154BC] text-left">{service}</div>
                </div>
              {/each}
            </div>
          </div>
        </div>
      </div>
    {/each}
  </div>

  <a href="/" class="inline-block mx-auto underline"><Button>Seek Support</Button></a>
</div>

<div class="rounded-xl"></div>
