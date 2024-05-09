function About() {
    return (<>
        <header>
            <div>
                <h1>About Us</h1>
            </div>
        </header>
        <section align="center">
            <AboutText />
        </section>
        <footer>
            Copyright 2024
        </footer>
    </>);
}

export function AboutText() {
    return (
    <section className="about container">
        <div>
            <p>
                We created this app with the idea of helping job seekers 
                keep track of their applications.
            </p>
            
            <h3 className="mb-4 mt-4">About the Team</h3>
            <p>
                <b>Radhika Sinha - </b>
                I am a Computer Science graduate from the University of California, Santa Cruz. 
                Besides coding, my hobbies include weightlifting and freelance photography. 
                I’ve gained many valuable skills through my interests— 
                such as the ability to talk to people, an innate desire to be creative, 
                and determination even when faced with setbacks— 
                and I believe these are the key to thriving as an engineer. 
                I’m excited to embark on this lifelong journey of learning 
                and improving my skills in the tech space.
            </p>
            <p>
                <b>Cristian Astorga - </b>
                I am a graduate of the University of Texas at Austin. 
                I've always enjoyed putting things together, such as Legos or model kits, 
                so I was instantly intrigued when I was first introduced to Computer Science. 
                From facing a difficult problem to putting the solution together, 
                seeing my work at the end of the day has always brought me the utmost satisfaction.
            </p>
            <p>
                <b>Benjamin Wilkins - </b> 
                I started my coding journey by completing App Academny Open's coding bootcamp. 
                I then completed a Master's degree in Software Development 
                at Maryville University of St. Louis. 
                I enjoy swimming, movies, and spending time with my wife. 
            </p>
        </div>
    </section>
    );
}

export default About;