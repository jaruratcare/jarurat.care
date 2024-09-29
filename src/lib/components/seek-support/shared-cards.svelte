<script>
    export let title;
    export let about;
    export let name;
    export let cancer;
    export let backgound;

</script>

<div class="relative shadow-lg min-w-80 p-10 border-4 border-[#DBEDFD] rounded-lg bg-white]">
    <div 
      class="absolute inset-0 opacity-75 bg-cover bg-center  rounded-lg" 
      style={`background-image: url(${backgound})`} 
    />

    {#if backgound}
    <div class="absolute inset-0 bg-black opacity-50 rounded-lg -z-5"/>
    {/if}
    <div class="relative z-10">
        <div class={`title font-bold ${backgound?"text-white":"text-black"}`}>{title}</div>
        <div class={`"about  my-6 ${backgound?"text-white":"text-[#0D2561]"}`}>{about}</div>
        <div class={`"name  text-sm ${backgound?"text-white":"text-[#576171] "} `}>{name}</div>
        <div class={`"cancer text-sm ${backgound?"text-white":"text-[#576171] "} `}>{cancer}</div>
      </div>
</div>