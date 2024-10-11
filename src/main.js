import App from './app'
import { createStore } from 'redux'; // Use the Redux createStore
import { Store } from 'svelte-redux'; // Svelte's Redux connector
import rootReducer from './store/reducers/userReducer'

// Create a Redux store
const store = createStore(rootReducer);

// Create the Svelte app and pass in the Redux store using `props`
const app = new App({
  target: document.body,
  props: {
    store: new Store(store), // Create an instance of Svelte's Store wrapper
  },
});

export default app;