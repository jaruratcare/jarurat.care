<script>
	import { onMount } from "svelte";
	import { Map }  from "@onsvisual/svelte-maps";
	import maplibre from "maplibre-gl";

	let map;
	// State
	let zoom;
	let center = {};
	

	onMount(() => {
		map.addControl(new maplibre.NavigationControl(), 'top-left');
	})
</script>

<main>
	<Map id="map" 
		style="https://api.maptiler.com/maps/streets/style.json?key=LBhXmHW7ll6FScEnokYK" 
		location={{lng: 78.7745, lat:30.1471 , zoom: 18}} 
		bind:map={map} bind:zoom={zoom} bind:center={center} />
</main>

<style>
	main {
		width: 100%;
		position: relative;
		height: 100%;
	}
</style>