<script>
	import { cn } from '$lib/utils';

	let className = '';

	export let title = '';
	export let subtitle = '';
	export { className as class };
</script>

<header class={cn('px- flex flex-col gap-3 font-medium text-center', className)}>
	<h2 class="text-[#0D2561] font-rubik md:text-[1.7em] sm:text-[2.3em] leading-[1]">{title}</h2>
	<h4 class="text-[#132442] font-manrope md:text-[0.9em] sm:text-[1em] leading-[1]">{subtitle}</h4>
</header>
