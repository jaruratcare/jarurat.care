<script>
	import Button from "../ui/button.svelte";
import CardsOfSeeksupport from "./cards-of-seeksupport.svelte";

    let items =[
        {
            title:"Patient Advocacy",
            about:"We stand by your side, helping navigate treatment options and healthcare systems",
            border:"#B0DFF9",
            list1:"Insurance Assistance",
            list2:"Legal Support",
            list3:"Healthcare Navigation",
        },
        {
            title:"Patient Advocacy",
            about:"We stand by your side, helping navigate treatment options and healthcare systems",
            border:"#B0DFF9",
            list1:"Insurance Assistance",
            list2:"Legal Support",
            list3:"Healthcare Navigation",
        },
        {
            title:"Patient Advocacy",
            about:"We stand by your side, helping navigate treatment options and healthcare systems",
            border:"#B0DFF9",
            list1:"Insurance Assistance",
            list2:"Legal Support",
            list3:"Healthcare Navigation",
        },
        {
            title:"Patient Advocacy",
            about:"We stand by your side, helping navigate treatment options and healthcare systems",
            border:"#B0DFF9",
            list1:"Insurance Assistance",
            list2:"Legal Support",
            list3:"Healthcare Navigation",
        },
    ]

</script>

<div class="py-24">
    <div class="flex flex-col justify-center items-center gap-3">

        <div class="text-3xl font-extrabold"><span>How We </span> <span class="text-[#0D2460]">Support You</span> </div>
        <div class="text-sm">Comprehensive care and support throughout your cancer journey</div>
    </div>
    <div class="flex gap-5 mt-20 justify-center mb-14">
        {#each items as item}
        <CardsOfSeeksupport title={item.title} about={item.about} border={item.border} list1={item.list1} list2={item.list2} list3={item.list3}/>
        {/each}
    </div>

    <div class="flex justify-center ">
        <Button class="relative z-10">Seek Support</Button>
    </div>
   
    
</div>