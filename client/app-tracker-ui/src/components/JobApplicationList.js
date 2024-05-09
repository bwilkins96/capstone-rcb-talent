import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { getNicerString } from '../utils';

function JobApplicationList() {
    const [jobApplications, setJobApplications] = useState([]);
    const url = "http://localhost:8080/api/job/application"
    const navigate = useNavigate();

    useEffect(() => {
        fetch(url)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setJobApplications(data)) // here we are setting our data to our state variable
            .catch(console.log);
    }, [jobApplications]);

    const handleDeleteApplication = (applicationId) => {
        // first find the application
        const jobApplication = jobApplications.find(jobApplication => jobApplication.applicationId === applicationId);
        // confirm
        if (window.confirm(`Delete Job Application: ${jobApplication.applicationId}?`)) {
            const init = {
                method: "DELETE"
            };
            fetch(`${url}/${applicationId}`, init)
                .then(response => {
                    if (response.stats === 204) {
                        const newJobApplications = jobApplications.filter(jobApplication => jobApplication.id !== applicationId);
                        setJobApplications(newJobApplications);
                    } else {
                        return Promise.reject(`Unexpected Status Code: ${response.status}`);
                    }
                })
                .catch(console.log);
        }
    }

    /*
    private JobPosting posting; - maybe break up posting into a couple fields only (company / role)
    private Status status;              Position @Company   SWE @Google
    private Origin origin;              Google Software Engineer
    private LocalDate dateApplied;      Position               Company
    private String notes;               Software Engineer      Google
    */

    return (<>
        <main className='container'>
            <section>
                <h2 className="mb-4 mt-2">Job Applications</h2>
                <button className='btn btn-primary mb-2' onClick={() => navigate('/applications/add')}>Add Job Application</button>
                <table className="table table-striped table-hover table-sm">
                    <thead className='thead-dark'>
                        <tr>
                            <th>Job Posting</th>
                            <th>Status</th>
                            <th>Origin</th>
                            <th>Date Applied</th>
                            <th>Notes</th>
                            <th>More Details</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        {jobApplications.map(jobApplication => (
                            <tr key={jobApplication.applicationId}>
                                <td>{jobApplication.posting.postingId}</td>
                                <td>{getNicerString(jobApplication.status)}</td>
                                <td>{getNicerString(jobApplication.origin)}</td>
                                <td>{jobApplication.dateApplied}</td>
                                <td>{jobApplication.notes}</td>
                                <td>
                                    <div>
                                        <Link to={`/applications/${jobApplication.applicationId}`}>Details</Link>
                                    </div>
                                </td>
                                <td>
                                    <div className="float-right mr-2">
                                        <Link className='btn btn-primary mr-2' to={`/applications/edit/${jobApplication.applicationId}`} > Edit</Link>
                                        <button className='btn btn-danger mr-2' onClick={() =>
                                            handleDeleteApplication(jobApplication.applicationId)}>Delete</button>
                                    </div>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </section>
        </main >
    </>);
}

export default JobApplicationList;