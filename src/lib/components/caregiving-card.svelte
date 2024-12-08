<script>
	export let icon;
	export let title;
	export let about;
</script>

<div class="text-white md:w-[400px] w-[250px] md:h-[300px]  bg-gradient-to-br from-[#065AC040] to-[#85DAFF80] rounded-3xl p-6">
	<div class="flex justify-around items-center" >
        <div class="title font-extrabold" >{title}</div>
        <div class="icon " ><svelte:component this={icon} /></div>
    </div>
    <div class="pb-6 text-sm">{about}</div>
</div>
