<script>
	import { goto } from '$app/navigation';
	export let chatsAndgroupPage = false;
	export let forumsPage = false;
	export let events = false;
	export let supportDiscussion = false;
	export let blogs = false;
	let isMenuOpen = false;
	function toggleMenu() {
		isMenuOpen = !isMenuOpen;
	}
</script>

<nav
	class="w-full block place-items-center md:flex md:justify-between md:items-center px-4 py-6 {supportDiscussion
		? 'bg-[#D9D9D9]'
		: 'bg-white'}"
>
	<div class="relative md:flex md:items-center lg:ml-2 z-10">
		{#if !events}
			<img src="/image.png" alt="Logo" class="h-20 h-15 md:h-11 lg:h-11" />
		{/if}
	</div>
	<div class="hidden md:flex items-center lg:space-x-[4.7rem] lg:mr-5 z-10">
		{#if chatsAndgroupPage}
			<button
				on:click={() => goto('/online-community/chats-and-group/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
				>Chats and Group</button
			>
		{:else}
			<button
				on:click={() => goto('/online-community/chats-and-group/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
				>Chats and Group</button
			>
		{/if}

		{#if forumsPage}
			<button
				on:click={() => goto('/online-community/forums/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
				>Forums</button
			>
		{:else if supportDiscussion}
			<button
				on:click={() => goto('/online-community/forums/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
				>Forums</button
			>
		{:else}
			<button
				on:click={() => goto('/online-community/forums/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
				>Forums</button
			>
		{/if}
		{#if events}
			<button
				on:click={() => goto('/online-community/community_events/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
				>Events</button
			>
		{:else}
			<button
				on:click={() => goto('/online-community/community_events/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
				>Events</button
			>
		{/if}
		<button
			href="#"
			class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
			>Tags</button
		>
		{#if blogs}
			<button
				on:click={() => goto('/online-community/blogs/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
				>Blog</button
			>
		{:else}
			<button
				on:click={() => goto('/online-community/blogs/')}
				class="text-[#0D2561] md:px-2 lg:px-6 text-lg font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
				>Blog</button
			>
		{/if}

		<svg width="26" height="25" viewBox="0 0 26 25" fill="none" xmlns="http://www.w3.org/2000/svg">
			<path
				d="M25.0714 21.4286H0.928571C0.682299 21.4286 0.446113 21.5226 0.271972 21.6901C0.0978314 21.8575 0 22.0846 0 22.3214L0 24.1071C0 24.3439 0.0978314 24.571 0.271972 24.7385C0.446113 24.9059 0.682299 25 0.928571 25H25.0714C25.3177 25 25.5539 24.9059 25.728 24.7385C25.9022 24.571 26 24.3439 26 24.1071V22.3214C26 22.0846 25.9022 21.8575 25.728 21.6901C25.5539 21.5226 25.3177 21.4286 25.0714 21.4286ZM25.0714 14.2857H0.928571C0.682299 14.2857 0.446113 14.3798 0.271972 14.5472C0.0978314 14.7147 0 14.9418 0 15.1786L0 16.9643C0 17.2011 0.0978314 17.4282 0.271972 17.5956C0.446113 17.7631 0.682299 17.8571 0.928571 17.8571H25.0714C25.3177 17.8571 25.5539 17.7631 25.728 17.5956C25.9022 17.4282 26 17.2011 26 16.9643V15.1786C26 14.9418 25.9022 14.7147 25.728 14.5472C25.5539 14.3798 25.3177 14.2857 25.0714 14.2857ZM25.0714 7.14286H0.928571C0.682299 7.14286 0.446113 7.23693 0.271972 7.40437C0.0978314 7.57181 0 7.79891 0 8.03571L0 9.82143C0 10.0582 0.0978314 10.2853 0.271972 10.4528C0.446113 10.6202 0.682299 10.7143 0.928571 10.7143H25.0714C25.3177 10.7143 25.5539 10.6202 25.728 10.4528C25.9022 10.2853 26 10.0582 26 9.82143V8.03571C26 7.79891 25.9022 7.57181 25.728 7.40437C25.5539 7.23693 25.3177 7.14286 25.0714 7.14286ZM25.0714 0H0.928571C0.682299 0 0.446113 0.0940686 0.271972 0.261512C0.0978314 0.428955 0 0.656057 0 0.892857L0 2.67857C0 2.91537 0.0978314 3.14247 0.271972 3.30992C0.446113 3.47736 0.682299 3.57143 0.928571 3.57143H25.0714C25.3177 3.57143 25.5539 3.47736 25.728 3.30992C25.9022 3.14247 26 2.91537 26 2.67857V0.892857C26 0.656057 25.9022 0.428955 25.728 0.261512C25.5539 0.0940686 25.3177 0 25.0714 0Z"
				fill="#0D2561"
			/>
		</svg>
	</div>

	<button on:click={toggleMenu} class="md:hidden relative z-20 mt-5 cursor-pointer">
		<svg width="26" height="25" viewBox="0 0 26 25" fill="none" xmlns="http://www.w3.org/2000/svg">
			<path
				d="M25.0714 21.4286H0.928571C0.682299 21.4286 0.446113 21.5226 0.271972 21.6901C0.0978314 21.8575 0 22.0846 0 22.3214L0 24.1071C0 24.3439 0.0978314 24.571 0.271972 24.7385C0.446113 24.9059 0.682299 25 0.928571 25H25.0714C25.3177 25 25.5539 24.9059 25.728 24.7385C25.9022 24.571 26 24.3439 26 24.1071V22.3214C26 22.0846 25.9022 21.8575 25.728 21.6901C25.5539 21.5226 25.3177 21.4286 25.0714 21.4286ZM25.0714 14.2857H0.928571C0.682299 14.2857 0.446113 14.3798 0.271972 14.5472C0.0978314 14.7147 0 14.9418 0 15.1786L0 16.9643C0 17.2011 0.0978314 17.4282 0.271972 17.5956C0.446113 17.7631 0.682299 17.8571 0.928571 17.8571H25.0714C25.3177 17.8571 25.5539 17.7631 25.728 17.5956C25.9022 17.4282 26 17.2011 26 16.9643V15.1786C26 14.9418 25.9022 14.7147 25.728 14.5472C25.5539 14.3798 25.3177 14.2857 25.0714 14.2857ZM25.0714 7.14286H0.928571C0.682299 7.14286 0.446113 7.23693 0.271972 7.40437C0.0978314 7.57181 0 7.79891 0 8.03571L0 9.82143C0 10.0582 0.0978314 10.2853 0.271972 10.4528C0.446113 10.6202 0.682299 10.7143 0.928571 10.7143H25.0714C25.3177 10.7143 25.5539 10.6202 25.728 10.4528C25.9022 10.2853 26 10.0582 26 9.82143V8.03571C26 7.79891 25.9022 7.57181 25.728 7.40437C25.5539 7.23693 25.3177 7.14286 25.0714 7.14286ZM25.0714 0H0.928571C0.682299 0 0.446113 0.0940686 0.271972 0.261512C0.0978314 0.428955 0 0.656057 0 0.892857L0 2.67857C0 2.91537 0.0978314 3.14247 0.271972 3.30992C0.446113 3.47736 0.682299 3.57143 0.928571 3.57143H25.0714C25.3177 3.57143 25.5539 3.47736 25.728 3.30992C25.9022 3.14247 26 2.91537 26 2.67857V0.892857C26 0.656057 25.9022 0.428955 25.728 0.261512C25.5539 0.0940686 25.3177 0 25.0714 0Z"
				fill="#0D2561"
			/>
		</svg>
	</button>

	{#if isMenuOpen}
		<div class="block place-items-center md:hidden">
			{#if chatsAndgroupPage}
				<button
					on:click={() => goto('/online-community/chats-and-group/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
					>Chats and Group</button
				>
			{:else}
				<button
					on:click={() => goto('/online-community/chats-and-group/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
					>Chats and Group</button
				>
			{/if}

			{#if forumsPage}
				<button
					on:click={() => goto('/online-community/forums/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
					>Forums</button
				>
			{:else if supportDiscussion}
				<button
					on:click={() => goto('/online-community/forums/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
					>Forums</button
				>
			{:else}
				<button
					on:click={() => goto('/online-community/forums/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
					>Forums</button
				>
			{/if}
			{#if events}
				<button
					on:click={() => goto('/online-community/community_events/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
					>Events</button
				>
			{:else}
				<button
					on:click={() => goto('/online-community/community_events/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
					>Events</button
				>
			{/if}
			<button
				href="#"
				class="text-[#0D2561] block py-2 text-2xl font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
				>Tags</button
			>
			{#if blogs}
				<button
					on:click={() => goto('/online-community/blogs/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic bg-[#2CBFE24D] rounded-2xl shadow-md"
					>Blog</button
				>
			{:else}
				<button
					on:click={() => goto('/online-community/blogs/')}
					class="text-[#0D2561] block py-2 text-2xl font-bold italic hover:bg-[#2CBFE24D] hover:rounded-2xl hover:shadow-md"
					>Blog</button
				>
			{/if}
		</div>
	{/if}
</nav>
