// src/store/index.js
import { createStore, combineReducers } from 'redux';
import { createStoreHook } from 'react-redux';
import userReducer from './reducers/userReducer';

// Combine multiple reducers (if necessary)
const rootReducer = combineReducers({
  userDetails: userReducer,
});

// Create the Redux store
const store = createStore(rootReducer);

export default store;
