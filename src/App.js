import {
  Routes,
  Route,
  BrowserRouter as Router
} from 'react-router-dom';
import routes from './routes';
// import NavBar from "./components/NavBar";

function App() {
  return (
    <div className="App">
      {/* <NavBar /> */}
      <Router>
        <Routes>
          {routes.map(r => <Route
            exact path={r.path}
            key={r.path}
            element={<r.element />} />)}
        </Routes>
      </Router>

    </div>
  );
}

export default App;
