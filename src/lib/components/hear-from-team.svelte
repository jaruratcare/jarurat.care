<script>
  import { Splide, SplideSlide } from '@splidejs/svelte-splide';
  import TeamMessageCard from './team-message-card.svelte';
  import { t } from "$lib/translations/translations.js";


  // Using a reactive statement to update memberData whenever the language changes
  $: memberData = [
    {
      Name: $t("home.Team.Name"),
      Designation:  $t("home.Team.Designation"),
      Message: $t("home.Team.Message")
    }
  ];
</script>

<h1 class="text-blue-900 text-3xl font-bold mx-48 my-16">{$t("home.Team.Heading")}</h1>
{#each memberData as data}
  <Splide options={{ rewind: true }}>
    <SplideSlide>
      <TeamMessageCard {data} />
    </SplideSlide>
    <SplideSlide>
      <TeamMessageCard {data} />
    </SplideSlide>
    <SplideSlide>
      <TeamMessageCard {data} />
    </SplideSlide>
    <SplideSlide>
      <TeamMessageCard {data} />
    </SplideSlide>
  </Splide>
{/each}
<div class="flex justify-center align-middle my-8">
  <button class="mx-4 border-2 border-blue-900 px-4 py-2 text-blue-900 rounded-3xl">{$t("home.nav.about")}</button>
  <button class="mx-4 border-2 border-blue-900 px-4 py-2 text-white rounded-3xl bg-blue-900">{$t("home.nav.contactUs")}</button>
</div>