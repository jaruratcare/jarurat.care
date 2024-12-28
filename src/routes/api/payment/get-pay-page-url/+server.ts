import { nanoid } from "nanoid"
import { getPayPageUrl } from "../utils"

/** @type {import('./$types').RequestHandler} */
export async function GET({ url }) {
    const query = new URLSearchParams(url.search)
    const amount = parseFloat(query.get('amount')?.toString() || '200')
    const payPageUrl = await getPayPageUrl(nanoid(), amount,)
    return new Response(JSON.stringify(payPageUrl, null, 2), { headers: { 'Content-Type': 'application/json' } })
}