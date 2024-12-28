import { verifyTxn } from "../utils"

/** @type {import('./$types').RequestHandler} */
export async function GET({ url }) {
    const query = new URLSearchParams(url.search)
    const txnId = (query.get('txn-id')?.toString())
    const name = (query.get('name')?.toString())
    const email = (query.get('email')?.toString())
    const amount = parseFloat(query.get('amount')?.toString() || '200');

    const payPageUrl = await verifyTxn(txnId,name,email,amount)

    return new Response(JSON.stringify(payPageUrl, null, 2), { headers: { 'Content-Type': 'application/json' } })
}