export async function load({ fetch, params }) {
	let item = { blogs: [] }; // Initialize item with an empty blogs array
	let otherData = {}; // Initialize otherData to store response from another endpoint
	let error = null; // Initialize error state
	let loading = true; // Initialize loading state
  
	let pageName = params.id; // Correctly get the page ID from the route parameters

	try {
		// Fetching blogs
		const blogsRes = await fetch(`http://localhost:5000/jc/blogs/getAllBlogs`);
		if (!blogsRes.ok) {
			throw new Error('Failed to fetch blogs');
		}
		item = await blogsRes.json();

		// Fetching data from another endpoint using the pageName (ID) from the URL
		const otherRes = await fetch(`http://localhost:5000/jc/blogs/getBlogsById/${pageName}`);
		if (!otherRes.ok) {
			throw new Error('Failed to fetch other data');
		}
		otherData = await otherRes.json();
	} catch (err) {
		console.error(err);
		error = err.message; // Set error message
	} finally {
		loading = false; // Stop loading once fetching is complete
	}

	return { item, otherData, error, loading }; // Return both item and otherData
}
