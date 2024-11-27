<script>

export let icon ;
export let title;
export let about;
</script>



<div class="  bg-[#F8FCFF]">
    <div class="icon"><svelte:component this={icon} /></div>
    <div class="heading text-primaryBlue font-extrabold md:text-2xl text-lg mt-5 mb-2 ">{title}</div>
    <div class="about text-sm">{about}</div>
</div>



