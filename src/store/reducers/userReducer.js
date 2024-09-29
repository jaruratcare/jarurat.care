import { UPDATE_USER_DETAILS } from "../action/useraction";

//Make a common model class for the user object for better structure @Priyam
/**
 * @typedef {Object} UserState
 * @property {string} fullName
 * @property {string} gender
 * @property {string} email
 * @property {string} dob
 * @property {string} phoneNumber
 * @property {string} addressLine1
 * @property {string} addressLine2
 * @property {string} country
 * @property {string} city
 * @property {string} pincode
 */

//Make a common model class for the user object for better structure @Priyam (give default values for default state )
/** @type {UserState} */
const initialState = {
  fullName: '',
  gender: '',
  email: '',
  dob: '',
  phoneNumber: '',
  addressLine1: '',
  addressLine2: '',
  country: '',
  city: '',
  pincode: '',
};

/**
 * @typedef {Object} UpdateUserDetailsAction
 * @property {string} type
 * @property {Partial<UserState>} payload
 */

/**
 * User reducer to update user details
 * @param {UserState} state
 * @param {UpdateUserDetailsAction} action
 * @returns {UserState}
 */
const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case UPDATE_USER_DETAILS:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default userReducer;
