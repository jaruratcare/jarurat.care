import { twMerge } from 'tailwind-merge'
import { clsx, type ClassValue } from 'clsx'


export function cn(...inputs: ClassValue[]) {
    return twMerge(clsx(inputs))
}

export function isMobile(): boolean {
    if ('maxTouchPoints' in navigator) return navigator.maxTouchPoints > 0;

    const mQ = matchMedia?.('(pointer:coarse)');
    if (mQ?.media === '(pointer:coarse)') return !!mQ.matches;

    if ('orientation' in window) return true;

    // @ts-ignore
    return /\b(BlackBerry|webOS|iPhone|IEMobile)\b/i.test(navigator.userAgent) ||
        // @ts-ignore
        /\b(Android|Windows Phone|iPad|iPod)\b/i.test(navigator.userAgent);
}