import {useState, useEffect} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import { getNicerString } from '../utils';


function InterviewByAppList({ appId }) {
    const [interviews, setInterviews] = useState([]);
    const url = "http://localhost:8080/api/interview"
    const navigate = useNavigate();

    // Bringing in our data 
    useEffect(()=>{
        fetch(`${url}/appId/${appId}`)
        .then(response => {
            if(response.status === 200){
                return response.json();
            }else{
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => setInterviews(data)) // here we are setting our data to our state variable
        .catch(console.log);
    }, [appId]);

    const handleDeleteInterview = (interviewId) => {
        // Find the interview
        const interview = interviews.find(interview => interview.interviewId === interviewId);

        // confirm deletion
        if(window.confirm(`Delete Interview ID: ${interview.interviewId}?`)){
            const init = {
                method: 'DELETE'
            };
            fetch(`${url}/${interviewId}`, init)
            .then(response => {
                if(response.status === 204){
                    const newInterviews = interviews.filter(interview => interview.interviewId !== interviewId);
                    setInterviews(newInterviews);
                }else{
                    return Promise.reject(`Unexpected Status Code: ${response.status}`);
                }
            })
            .catch(console.log);
        }
    }

    const displayWhen = (whenString) => {
        const dateTime = whenString.split('T');
        return `${dateTime[0]} @ ${dateTime[1]}`;
    }

    return (<>
        <main className='container'>
            <section>
                <h2 className='mb-4'>Interviews</h2>
                <button className='btn btn-primary btn-lg mb-3' onClick={() => navigate('/interview/add', { state: { applicationId: appId }})}>Add Interview</button>
                <table className='table table-striped table-hover table-md'>
                    <thead className='thead-dark'>
                        <tr>
                            <th>Interview ID</th>
                            <th>Application ID</th>{/* Will remove after testing */}
                            <th>Interview Type</th>
                            <th>Interview Result</th>
                            <th>When</th>
                            <th>Personal Notes</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        {interviews.map(interview => (
                            <tr key={interview.interviewId}>
                                <td>{interview.interviewId}</td>
                                <td>{interview.applicationId}</td>
                                <td>{getNicerString(interview.type)}</td>
                                <td>{getNicerString(interview.result)}</td>
                                <td>{displayWhen(interview.when)}</td>
                                <td>{interview.notes}</td>
                                <td>
                                    <div className='float-right mr-2'>
                                        <Link className='btn btn-primary mr-3' to={`/interview/edit/${interview.interviewId}`}>Edit</Link>

                                        <button className='btn btn-danger' onClick={() => handleDeleteInterview(interview.interviewId)}>Delete</button>
                                    </div>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </section>
        </main>
    </>)
}

export default InterviewByAppList;