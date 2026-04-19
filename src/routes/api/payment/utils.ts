import { nanoid } from 'nanoid'
import { Buffer } from 'buffer'
import { createHash } from 'crypto'

const PHONEPE_MARCHENT_ID = process.env.PHONEPE_MARCHENT_ID;
const SALT_KEY = process.env.PHONEPE_SALT_KEY;
const SALT_INDEX = process.env.PHONEPE_SALT_INDEX;

export async function getPayPageUrl(marchentUserId: string, amount: number) {
    try {
        const txnId = nanoid();
        const muId = marchentUserId || nanoid();
        
        const data = {
            merchantId: PHONEPE_MARCHENT_ID,
            merchantTransactionId: txnId,
            merchantUserId: muId,
            amount: amount * 100, // Paisa conversion
            paymentInstrument: { type: "PAY_PAGE" },
            redirectUrl: "https://www.jarurat.care",
            redirectMode: "REDIRECT",
            callbackUrl: "https://www.jarurat.care",
        };

        const payload = JSON.stringify(data);
        const payloadMain = Buffer.from(payload).toString("base64");
        
        // SHA256 logic
        const stringToHash = payloadMain + "/pg/v1/pay" + SALT_KEY;
        const sha256 = createHash("sha256").update(stringToHash).digest("hex");
        const checksum = sha256 + "###" + SALT_INDEX;

        const prodURL = "https://api.phonepe.com/apis/hermes/pg/v1/pay";

        const resp = await fetch(prodURL, {
            method: "POST",
            headers: {
                "accept": "application/json",
                "Content-Type": "application/json",
                "X-VERIFY": checksum,
            },
            body: JSON.stringify({ request: payloadMain }),
        });

        const json = await resp.json();

        if (!json.success) {
            throw new Error(json.message || "Payment initiation failed");
        }

        return {
            status: "success",
            data: {
                url: json.data.instrumentResponse.redirectInfo.url,
                txnId: json.data.merchantTransactionId,
            },
        }
    } catch (err) {
        console.error("PhonePe Error:", err);
        throw err;
    }
}

export async function verifyTxn(txnId?: string, name?: string, email?: string, amount?: number) {
    try {
        if (!txnId) throw new Error('No TxnId found');
        
        const endpoint = `/pg/v1/status/${PHONEPE_MARCHENT_ID}/${txnId}`;
        const stringToHash = endpoint + SALT_KEY;
        const sha256 = createHash('sha256').update(stringToHash).digest('hex');
        const checksum = sha256 + '###' + SALT_INDEX;
        
        const prodURL = `https://api.phonepe.com/apis/hermes${endpoint}`;

        const resp = await fetch(prodURL, {
            method: 'GET',
            headers: {
                'accept': 'application/json',
                'Content-Type': 'application/json',
                'X-VERIFY': checksum,
                'X-MERCHANT-ID': PHONEPE_MARCHENT_ID
            }
        });

        const json = await resp.json();
        
        if (json.success && json.code === 'PAYMENT_SUCCESS') {
            // Email service call
            try {
                await fetch('https://jarurat-care-email-service.onrender.com/jarurat-care/sendMail/', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ name, amount, email })
                });
            } catch (e) {
                console.error("Email sending failed:", e);
            }
        }
        
        return {
            status: 'success',
            data: json.data
        };
    } catch (err) {
        console.error("Verify Error:", err);
        throw err;
    }
}
