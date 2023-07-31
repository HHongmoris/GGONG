import './App.css';
import IconButton from './components/Button/IconButton';
import ActionButton from './components/Button/ActionButton';
import Button from './components/Button/Button';

import icons from './global/icons';

function App() {
  return (
    <div className="App">
      <Button icon={icons.PLUS} leftIcon={true} />
      <IconButton icon={icons.PLUS} />
      <ActionButton icon={icons.PLUS} />
    </div>
  );
}

export default App;
