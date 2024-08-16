<script>
  import { slide } from 'svelte/transition';
  import PlusIcon from '$lib/svg/plus-icon.svelte';
  import bg from '../../assets/images/Frame 1171275545.png';
  import { t} from "$lib/translations/translations.js"

  $: faqs = [
    {
      id: 1,
      question: $t("home.faqs.question1"),
      answer:$t("home.faqs.answer1")
    },
    {
      id: 2,
      question: $t("home.faqs.question2"),
      answer:$t("home.faqs.answer2"),
    },
    {
      id: 3,
      question:$t("home.faqs.question3"),
      answer:$t("home.faqs.answer3")
    },
    {
      id: 4,
      question: $t("home.faqs.question4"),
      answer:$t("home.faqs.answer4")
    },
    {
      id: 5,
      question: $t("home.faqs.question5"),
      answer:$t("home.faqs.answer5")
    },
    {
      id: 6,
      question: $t("home.faqs.question6"),
      answer:$t("home.faqs.answer6")
    },
    {
      id: 7,
      question: $t("home.faqs.question7"),
      answer:$t("home.faqs.answer7")
    },
    {
      id: 8,
      question: $t("home.faqs.question8") ,
      answer:$t("home.faqs.answer8")
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
     {$t("home.faqs.title")}
    </h2>
    <p class="text-[#596274] text-xl font-medium" style="font-family: 'Manrope', sans-serif">
      {$t("home.faqs.description")}
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
     {$t("home.faqs.stillHaveQuestions")}
    </h2>
    <p class="text-[#596274] text-xl font-medium" style="font-family: 'Manrope', sans-serif">
      {$t("home.faqs.contactUs")}
    </p>
    <button
      class="relative z-1 mt-12 px-9 py-2.5 bg-[#0155BD] text-xl text-white border-2 border-[#0155BD] rounded-full hover:text-[#0155BD] group flex items-center justify-center overflow-hidden duration-200 ease"
      style="font-family: 'Roboto', sans-serif;"
    >
      <span
        class="absolute bottom-0 left-1/2 right-1/2 w-0 h-0 bg-[#DBEAFE] rounded-full transition-all duration-400 ease group-hover:w-full group-hover:h-full group-hover:left-0 group-hover:bottom-0 group-hover:right-0"
        style="transform: translateX(-50%), translateX(50%);"
      ></span>
      <span class="relative">{$t("home.faqs.buttonText")}</span>
    </button>
  </div>
</section>
