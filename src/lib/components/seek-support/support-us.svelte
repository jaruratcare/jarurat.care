<script>
    import background from '$lib/assets/seek-support/gradient.png';
	import Button from '../ui/button.svelte';
</script>

<div style={`background-image: url(${background})`} class="w-[100%] h-[300px] flex items-center justify-center">
    <div class=" w-[55%] flex flex-row justify-between">
        <div class="w-[45%]">
            <h1 class="text-2xl font-bold pb-7 text-[#0155BD]">Support Us</h1>
            <p class="text-[#0D2561]">All donations to JaruratCare Foundation are eligible for 50% tax exemption under section 80G of the Income Tax Act.</p>
        </div>
        
            <Button class="relative z-10 mr-10 rounded-[30px] w-[175px] h-[50px] pb-3 mt-[50px] shadow-sm">Donate Now</Button>
    </div>
</div>