import './App.css';
import { BrowserRouter as Router } from 'react-router-dom';
import { Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import Navbar from './components/Navbar';
import JobApplicationForm from './components/JobApplicationForm';
import JobApplicationList from './components/JobApplicationList';
import About from './components/About';
import NotFound from './components/NotFound';
import Visualization from './components/Visualization';


function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='application/add' element={<JobApplicationForm />} />
        <Route path='/application/edit/:id' element={<JobApplicationForm />} />
        <Route path='/applications' element={<JobApplicationList />} />
        <Route path='/about' element={<About />} />
        <Route path='/sankey/diagram' element={<Visualization />} />
        <Route path='*' element={<NotFound />} />
      </Routes>
    </Router >
  );
}

export default App;

