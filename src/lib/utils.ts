import clsx, { type ClassValue } from 'clsx';
import { twMerge } from 'tailwind-merge';

export function cn(...inputs: ClassValue[]) {
	return twMerge(clsx(inputs));
}

export async function getHospitals() {
	const baseURL = `https://chat-backend-e7nr.onrender.com`;
	const response = await fetch(`${baseURL}/hospitals`);
	const hospitals = await response.json();
	return hospitals;
}
