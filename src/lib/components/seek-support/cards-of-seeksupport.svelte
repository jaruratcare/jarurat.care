<script>


    export let title;
    export let about;
    export let border;
    export let list1;
    export let list2;
    export let list3;

</script>

<div class={` w-[18rem] bg-white shadow-lg border-[4px] border-[${border}] py-14 px-6 rounded-lg relative `}>
    <div class={`icon absolute w-28 h-28 rounded-full -top-14 right-24 bg-white border-t-4 border-[${border}] drop-shadow-md`}>

    </div>
    <div class="font-bold text-2xl pb-4">{title}</div>

    <div class="text-sm text-[#676E73] pb-3">{about}</div>
    <ul class="text-sm hidden md:block text-[#0155BD] list-disc ml-4" >
        <li>{list1}</li>
        <li>{list2}</li>
        <li>{list3}</li>
    </ul>
</div>