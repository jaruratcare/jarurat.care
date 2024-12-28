<script>
	export let icon;
	export let title;
	export let about;
</script>

<div class="text-white  bg-gradient-to-br p-1">
	<div class="flex justify-around items-center" >
        <div class="title font-extrabold" >{title}</div>
        <div class="icon" ><svelte:component this={icon} /></div>
    </div>
    <div class="pb-6 text-sm">{about}</div>
</div>
