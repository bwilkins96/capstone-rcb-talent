function About() {
    return (<>
        <header>
            <div>
                <h1>About Us</h1>
            </div>
        </header>
        <section>
            <AboutText />
        </section>
        <footer>
            Copyright 2024
        </footer>
    </>);
}

export function AboutText() {
    return (<section className="about">
        <div>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Morbi tristique senectus et netus et malesuada. Maecenas accumsan lacus vel facilisis volutpat est velit egestas. Arcu cursus vitae congue mauris. Sagittis id consectetur purus ut faucibus pulvinar elementum. Viverra aliquet eget sit amet tellus cras. Sit amet est placerat in. Integer malesuada nunc vel risus commodo viverra maecenas accumsan lacus. Amet consectetur adipiscing elit pellentesque. Erat nam at lectus urna duis convallis. Quis commodo odio aenean sed adipiscing. Venenatis cras sed felis eget velit. Massa placerat duis ultricies lacus. Diam phasellus vestibulum lorem sed risus ultricies. Sagittis orci a scelerisque purus semper eget duis.<br />
            </p>
            <p>
                Orci eu lobortis elementum nibh. Euismod elementum nisi quis eleifend quam adipiscing vitae. Libero nunc consequat interdum varius sit amet mattis. Nisl rhoncus mattis rhoncus urna neque viverra justo nec. Ut aliquam purus sit amet luctus venenatis lectus. Vestibulum sed arcu non odio euismod lacinia at quis risus. In nibh mauris cursus mattis molestie a iaculis. Nunc sed augue lacus viverra vitae congue eu consequat ac. Vitae semper quis lectus nulla at volutpat. Dolor morbi non arcu risus quis. Dictumst quisque sagittis purus sit amet volutpat. Sit amet est placerat in egestas. Bibendum enim facilisis gravida neque convallis a cras. Orci a scelerisque purus semper eget duis at.
            </p>
        </div>
    </section>);
}

export default About;