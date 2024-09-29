<script>
	import SharedCards from "./shared-cards.svelte";
    import priyanka from "$lib/assets/team-members/priyanka.webp"

    const sharedPeople= [
        {
            title: `"A Lifeline in Times of Need"`,
            about: `"Thanks to your generous donations, we received the support we needed when my husband lost his job. We are forever grateful."`,
            name:`Asha, Beneficiary`,
            cancer: `Breast cancer fighter`,
            backgound: priyanka
        },
        {
            title: `"A Lifeline in Times of Need"`,
            about: `"Thanks to your generous donations, we received the support we needed when my husband lost his job. We are forever grateful."`,
            name:`Asha, Beneficiary`,
            cancer: `Breast cancer fighter`,
            backgound:""
        },
        {
            title: `"A Lifeline in Times of Need"`,
            about: `"Thanks to your generous donations, we received the support we needed when my husband lost his job. We are forever grateful."`,
            name:`Asha, Beneficiary`,
            cancer: `Breast cancer fighter`,
            backgound: priyanka
        },
        {
            title: `"A Lifeline in Times of Need"`,
            about: `"Thanks to your generous donations, we received the support we needed when my husband lost his job. We are forever grateful."`,
            name:`Asha, Beneficiary`,
            cancer: `Breast cancer fighter`,
            backgound:""
        },
        {
            title: `"A Lifeline in Times of Need"`,
            about: `"Thanks to your generous donations, we received the support we needed when my husband lost his job. We are forever grateful."`,
            name:`Asha, Beneficiary`,
            cancer: `Breast cancer fighter`,
            backgound: priyanka
        },
    ]


</script>

<div class="pt-9">
    <div class="text-center">
        <div class="font-extrabold text-2xl mb-1"> <span>Shared</span> <span class="text-[#0155BD]">Journeys</span> </div>
        <div class="text-sm">The Impact of Your Kindness: Letters from Beneficiaries</div>
    </div>
    
    <div class="cards flex justify-center gap-8 my-14 overflow-y-auto scrollbar ">
        {#each sharedPeople as people}
        <SharedCards title={people.title} about={people.about} name={people.name} cancer={people.cancer} backgound={people.backgound}/>
        {/each}
        
    </div>
</div>