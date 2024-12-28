'use server' // only import this file in server-side functions
import { nanoid } from 'nanoid'
import { Buffer } from 'buffer'
import { createHash } from 'node:crypto'

const PHONEPE_API_KEY = "aa69b0a6-d1d2-475a-a6f8-9f172d86bd2e";
const PHONEPE_MARCHENT_ID = "M227ARX0TN68N";
const SALT_KEY = "aa69b0a6-d1d2-475a-a6f8-9f172d86bd2e";
const SALY_INDEX = "1";

export async function getPayPageUrl(marchentUserId: string, amount: number) {
    try {

        const txnId = nanoid();
        const muId = marchentUserId || nanoid();
        const merchantTransactionId = txnId;
        const data = {
            merchantId: PHONEPE_MARCHENT_ID,
            merchantTransactionId: txnId,
            merchantUserId: muId,
            amount: amount * 100,
            paymentInstrument: { type: "PAY_PAGE" },
            redirectUrl: "https://www.jarurat.care",
            redirectMode: "REDIRECT",
            callbackUrl: "https://www.jarurat.care",
        };

        const payload = JSON.stringify(data);
        const payloadMain = Buffer.from(payload).toString("base64");
        const keyIndex = 1;
        const string = payloadMain + "/pg/v1/pay" + SALT_KEY;
        const sha256 = createHash("sha256").update(string).digest("hex");
        const checksum = sha256 + "###" + SALY_INDEX;

        const prodURL = "https://api.phonepe.com/apis/hermes/pg/v1/pay";
        const options = {
            method: "POST",
            url: prodURL,
            headers: {
                accept: "application/json",
                "Content-Type": "application/json",
                "X-VERIFY": checksum,
            },
            data: {
                request: payloadMain,
            },
        };

        const resp = await fetch(prodURL, {
            method: "POST",
            headers: {
                accept: "application/json",
                "Content-Type": "application/json",
                "X-VERIFY": checksum,
            },
            body: JSON.stringify({ request: payloadMain }),
        });

        const json = await resp.json();

        return {
            status: "success",
            data: {
                url: json.data.instrumentResponse.redirectInfo.url,
                txnId: json.data.merchantTransactionId,
            },
        }
    } catch (err) {
        console.log(err);
        throw err;
    }
}

export async function verifyTxn(txnId?: string, name?: string, email?: string,amount:number) {
	try {
		if (!txnId) throw new Error('No TxnId found');
		const payload = `/pg/v1/status/${PHONEPE_MARCHENT_ID}/${txnId}`;
		const keyIndex = 1;
		const sha256 = createHash('sha256').update(`${payload}${SALT_KEY}`).digest('hex');
		const checksum = sha256 + '###' + SALY_INDEX;
		const prodURL = `https://api.phonepe.com/apis/hermes${payload}`;

		const resp = await fetch(prodURL, {
			method: 'GET',
			headers: {
				accept: 'application/json',
				'Content-Type': 'application/json',
				'X-VERIFY': checksum,
				'X-MERCHANT-ID': PHONEPE_MARCHENT_ID
			}
		});

		const json = await resp.json();
		const data = (json || {}).data;
		if (data?.status?.toLowerCase() === 'success') {
            console.log("sending...");
			await fetch('https://jarurat-care-email-service.onrender.com/jarurat-care/sendMail/', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ name, amount: amount, email }) // Use the dynamic values here
			});
		}
		return {
			status: 'success',
			data
		};
	} catch (err) {
		console.log(err);
		throw err;
	}
}
