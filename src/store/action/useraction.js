// Define action types
export const UPDATE_USER_DETAILS = 'UPDATE_USER_DETAILS';

//Make a common model class for the user object for better structure @Priyam
/**
 * @typedef {Object} UserDetails
 * @property {string} [fullName]
 * @property {string} [gender]
 * @property {string} [email]
 * @property {string} [dob]
 * @property {string} [phoneNumber]
 * @property {string} [addressLine1]
 * @property {string} [addressLine2]
 * @property {string} [country]
 * @property {string} [city]
 * @property {string} [pincode]
 */

/**
 * Action creator for updating user details
 * @param {UserDetails} details
 * @returns {{type: string, payload: UserDetails}}
 */
export function updateUserDetails(details) {
    return {
        type: UPDATE_USER_DETAILS,
        payload: details,
    };
};
