import logo from './logo.svg';
import './App.css';
import appStore from './store/appStore';
import { Toaster } from 'react-hot-toast';
import { Provider } from 'react-redux';
import AppRouter from './AppRouter';

function App() {

  return (
    <Provider store={appStore}>
      <Toaster
        position="top-center"
        reverseOrder={false}
      />
      <AppRouter />
    </Provider>
  );
}

export default App;

