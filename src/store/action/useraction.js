// Define action types
export const UPDATE_USER_DETAILS = 'UPDATE_USER_DETAILS';

// Action creator for updating user details
export function updateUserDetails(details) {
    return {
        type: UPDATE_USER_DETAILS,
        payload: details,
      };
};

