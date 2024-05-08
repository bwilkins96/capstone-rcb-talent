import JobApplicationList from "./JobApplicationList";
import { AboutText } from "./About";
import Visualization from "./Visualization";

function Home() {
    return (<>
        <header>
            <div>
                <h1>Application Tracker</h1>
            </div>
        </header>
        <section className="about">
            <div align="center">
                <h2>About Us</h2>
                <AboutText />
            </div>
        </section>
        <section>
            <Visualization />
        </section>
        <section>
            <JobApplicationList />
        </section>
        <footer>
            Copyright 2024
        </footer>
    </>);
}

export default Home;