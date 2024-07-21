<script>
  import { Splide, SplideSlide } from "@splidejs/svelte-splide";
  import TeamMessageCard from "./team-message-card.svelte";

   const memberData =[{
      Name:"Priyanka",
      Designation:"Co Founder",
      Message:"At Jaruart Care, our mission is to bring hope and healing to every person battling cancer. We believe in the power of community support, compassion and innovation to make a real diffrence in the lives of those we serve"
   }]
</script>
<h1 class="text-blue-900 text-3xl font-bold mx-48 my-16">Hear From Our Team</h1>
{#each memberData as data }
<Splide options={{rewind: true,}} >
   <SplideSlide>
           <TeamMessageCard data={data} />
      </SplideSlide>
      <SplideSlide>
           <TeamMessageCard data={data} />
      </SplideSlide>
      <SplideSlide>
           <TeamMessageCard data={data} />
      </SplideSlide>
      <SplideSlide>
           <TeamMessageCard data={data} />
      </SplideSlide>
</Splide>
 
{/each}
<div class="flex justify-center align-middle my-8">
   <button class="mx-4 border-2 border-blue-900 px-4 py-2 text-blue-900 rounded-3xl">About Us</button>
   <button class="mx-4 border-2 border-blue-900 px-4 py-2 text-white rounded-3xl bg-blue-900">Contact Us</button>
</div>