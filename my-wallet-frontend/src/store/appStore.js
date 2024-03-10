import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./userSlice";
import thunk from 'redux-thunk'; 

const appStore = configureStore({
    reducer: {
        user: userReducer,
    }
});

export default appStore;