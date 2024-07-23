<script>
  import { slide } from 'svelte/transition';
  import PlusIcon from '$lib/svg/plus-icon.svelte';
  import bg from '../../assets/images/Frame 1171275545.png';

  const faqs = [
    {
      id: 1,
      question: 'What is Mentor?',
      answer:
        'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.'
    },
    {
      id: 2,
      question: 'How do I apply for assistance?',
      answer:
        'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.'
    },
    {
      id: 3,
      question: 'What areas or regions does Jarurat Care operate in?',
      answer:
        'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.'
    },
    {
      id: 4,
      question: 'How can I apply for assistance from Jarurat Care?',
      answer:
        'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.'
    },
    {
      id: 5,
      question: 'Who is eligible for support?',
      answer:
        'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.'
    },
    {
      id: 6,
      question: 'Are the services free?',
      answer:
        'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.'
    },
    {
      id: 7,
      question: 'How can I volunteer or donate?',
      answer:
        'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.'
    },
    {
      id: 8,
      question: 'How can I volunteer or donate?',
      answer:
        'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nulla magnam nostrum quidem, recusandae modi labore magni provident incidunt doloremque accusantium, voluptas eligendi reiciendis fugit eaque rerum nam ratione fuga amet sed blanditiis eos? Fugit blanditiis facere perspiciatis esse officia aut.'
    }
  ];

  /**
   * @type {number | null}
   */
  let openId = null;

  const handleClick = (/** @type {number | null} */ id) => {
    openId = openId === id ? null : id;
  };
</script>

<section
  id="FAQs"
  class="w-full flex-col flex justify-center items-center py-20 bg-cover bg-no-repeat bg-center"
  style="background-image: url({bg});"
>
  <div class="text-center w-full">
    <h2
      class="text-[2rem] font-medium text-[#0D2561]"
      style="font-family: 'Rubik Variable', sans-serif;"
    >
      Frequently Asked Questions
    </h2>
    <p class="text-[#596274] text-xl font-medium" style="font-family: 'Manrope', sans-serif">
      Look through some of our frequently answered questions
    </p>
  </div>
  <div class="my-24 w-full flex justify-center items-center z-10">
    <div class="w-2/3 flex flex-wrap justify-between overflow-hidden">
      {#each faqs as faq (faq.id)}
        <div class="w-[48.5%] my-2 rounded-md bg-white">
          <button
            on:click={() => handleClick(faq.id)}
            class="w-full flex justify-between py-6 px-5 text-[#0D2561]"
          >
            <span>{faq?.question}</span>
            <span><PlusIcon /></span>
          </button>
          {#if openId === faq.id}
            <div class="px-5 pb-3" transition:slide>{faq?.answer}</div>
          {/if}
        </div>
      {/each}
    </div>
  </div>
  <div class="flex justify-center items-center flex-col text-center">
    <h2
      class="text-[2rem] font-medium text-[#0D2561]"
      style="font-family: 'Rubik Variable', sans-serif;"
    >
      Still Have Questions?
    </h2>
    <p class="text-[#596274] text-xl font-medium" style="font-family: 'Manrope', sans-serif">
      Feel free to contact us
    </p>
    <button
      class="relative z-1 mt-12 px-9 py-2.5 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-[#0155BD] group flex items-center justify-center overflow-hidden duration-200 ease"
      style="font-family: 'Roboto', sans-serif;"
    >
      <span
        class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
        style="transform: translateX(-50%), translateX(50%);"
      ></span>
      <span class="relative">Contact Us</span>
    </button>
  </div>
</section>
