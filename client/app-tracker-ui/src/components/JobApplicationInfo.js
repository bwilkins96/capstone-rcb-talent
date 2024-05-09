import { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import InterviewByAppList from "./InterviewByAppList";
import { getTodayString } from '../utils';

const JobApplicationDefault = {
    applicationId: 0,
    posting: {
        postingId: 1
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

    return(<>
        <main className="container">
            <h2 className="mb-3"><u>Application Details</u></h2>
            <Link className='btn btn-info btn-lg mb-3' type='button' to={'/applications'} >Back to List</Link>
            <div>
                <InterviewByAppList appId={id}/>
            </div>
        </main>
    </>);
}

export default JobApplicationInfo;