import { verifyTxn } from "../utils"

/** @type {import('./$types').RequestHandler} */
export async function GET({ url }) {
    const query = new URLSearchParams(url.search)
    const txnId = (query.get('txn-id')?.toString())

    const payPageUrl = await verifyTxn(txnId)

    return new Response(JSON.stringify(payPageUrl, null, 2), { headers: { 'Content-Type': 'application/json' } })
}