<script>
	let email = "";
	let message = "";
	let loading = false;

	const MAILCHIMP_URL =
		"https://care.us16.list-manage.com/subscribe/post-json?u=1c1adf80af9253ef26764a28d&id=3ed044ddf2";

	function subscribe(e) {
		e.preventDefault();
		message = "";
		loading = true;

		const callbackName = "jsonp_cb_" + Date.now();

		window[callbackName] = (res) => {
			loading = false;

			if (res.result === "success") {
				message = "✅ Subscribed successfully!";
				email = "";
			} else {
				message = res.msg.replace(/<[^>]+>/g, "");
			}

			delete window[callbackName];
			document.body.removeChild(script);
		};

		const script = document.createElement("script");

		script.src = `${MAILCHIMP_URL}&EMAIL=${encodeURIComponent(
			email
		)}&c=${callbackName}`;

		document.body.appendChild(script);
	}
</script>

<form on:submit={subscribe} class="flex gap-2 mt-10">
	<input
		type="email"
		bind:value={email}
		placeholder="Enter your email"
		required
		class="border px-4 py-2 rounded w-full"
	/>

	<button
		type="submit"
		disabled={loading}
		class="bg-black text-white px-4 py-2 rounded"
	>
		{loading ? "Sending..." : "Submit"}
	</button>
</form>

{#if message}
	<p class="mt-3">{message}</p>
{/if}