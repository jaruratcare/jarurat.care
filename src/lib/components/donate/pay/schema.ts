import { z } from 'zod';

export const billingSchema = z.object({
	amount: z.number().default(200),
	['payment-type']: z.enum(['subscription', 'one-time']).default('one-time')
});

export type BillingSchemaType = z.infer<typeof billingSchema>;

export const personalDetailsSchema = z.object({
	['full-name']: z.string(),
	email: z.string().email(),
	phone: z.string(),
	pan: z.string().optional().default('')
});

export type PersonalDetailsSchemaType = z.infer<typeof personalDetailsSchema>;
