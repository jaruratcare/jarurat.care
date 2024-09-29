// src/store/reducers/userReducer.js
import { UPDATE_USER_DETAILS} from "../action/useraction";

// Initial state for user details
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

// User reducer to update user details
const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case UPDATE_USER_DETAILS:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default userReducer;
