import { writable } from 'svelte/store';

 const userData = writable({
  fullName: '',
  gender: '',
  email: '',
  dob: '',
  countryCode: '+91',
  phoneNumber: '',
  addressLine1: '',
  addressLine2: '',
  country: '',
  city: '',
  pincode: '',
  agreeToTerms: false,
  subscribeToNewsletter: false,
  selectedSupport: '',
  description: ''
});

export default userData