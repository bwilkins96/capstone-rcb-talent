import { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import InterviewByAppList from "./InterviewByAppList";
import { getNicerString, getTodayString } from '../utils';

const JobApplicationDefault = {
    applicationId: 0,
    posting: {
        postingId: 1,
        company: {},
        skills: []
    },
    status: "PENDING",
    origin: "COLD_APPLY",
    dateApplied: getTodayString(),
    notes: ""
};

function JobApplicationInfo() {
    const [application, setApplication] = useState(JobApplicationDefault);
    const url = 'http://localhost:8080/api/job/application';

    const { id } = useParams();

    // Bring in application data
    useEffect(() => {
        fetch(`${url}/${id}`)
        .then(response => {
            if(response.status === 200) {
                return response.json();
            } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => {
            setApplication(data);
        })
        .catch(console.log);
    }, [id]);

    const displayPostingInfo = () => {
        const posting = application.posting;
        const company = posting.company;

        return <>
            <p>Company Name: {company.name}</p>
            <p>Job Title: {posting.role}</p>
            <p>Job Level: {posting.level}</p>
            <p>Desired Degree: {posting.degree}</p>
            <p>Visa Sponsorship: {(posting.visaSponsorship ? 'Offered': 'Not Offered')}</p>
            <ul>Desired Skills:
                {posting.skills.map( (skill) => {
                    return <li key={skill} style={{textIndent: 25, listStyle:"inside"}}>{skill}</li>
                })}
            </ul>
        </>;
    }

    return(<>
        <main className="container">
            <h2 className="mb-4"><u>Application Details</u></h2>
            <Link className='btn btn-info btn-lg mb-4' type='button' to={'/applications'} >Back to List</Link>
            <div>
                {displayPostingInfo()}
                <p>_____</p>
                <p>Application Status: {getNicerString(application.status)}</p>
                <p>Application Origin: {getNicerString(application.origin)}</p>
                <p>Date Applied: {application.dateApplied}</p>
                <p>Notes:</p>
                <p style={{ textIndent: 25 }}>{application.notes}</p>
                <p>_____</p>
            </div>
            <div>
                <InterviewByAppList appId={id}/>
            </div>
        </main>
    </>);
}

export default JobApplicationInfo;