import { Link } from 'react-router-dom';

function Navbar() {
    return (<>
        <nav className='bg-dark'>
            <Link to={'/'}>Home</Link>
            <Link to={`/about`}>About</Link>
            <Link to={`/applications`}>Applications</Link>
            <Link to={`/sankey/diagram`}>Sankey Diagram</Link>
        </nav>
    </>);

}

export default Navbar;

