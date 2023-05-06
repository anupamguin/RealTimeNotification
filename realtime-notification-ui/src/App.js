import "./App.css";
import HomePage from "./page/HomePage";
import { Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/home/:userId" element={<HomePage />} />
      </Routes>
    </div>
  );
}

export default App;
