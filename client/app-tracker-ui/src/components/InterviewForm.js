import {useState, useEffect} from 'react';
import {useNavigate, useLocation, useParams} from 'react-router-dom';

const interviewDefault = {
    applicationId: 0,
    type: "BEHAVIORAL",
    result: "UNDETERMINED",
    when: "",
    notes: ""
}

function InterviewForm() {
    const [interview, setInterview] = useState(interviewDefault);
    const [errors, setErrors] = useState([]);

    const url = "http://localhost:8080/api/interview";

    const navigate = useNavigate();
    const location = useLocation();

    const { id } = useParams();
    let appId = 0;
    if (location.state) {
        appId = location.state.applicationId;
    }

    useEffect(() => {
        // check if we have an id variable (if so we use it to get the interview with that id)
        if(id) {
            fetch(`${url}/${id}`)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                setInterview(data);
            })
            .catch(console.log);
        } else {
            updateInterviewAppId();
        }
    }, [id]);

    const updateInterviewAppId = () => {
        // use the application ID from the passed in state to become the default
        const newInterview = {...interview};
        newInterview['applicationId'] = appId;
        setInterview(newInterview);
    }

    const handleChange = (event) => {
        const newInterview = {...interview};

        newInterview[event.target.name] = event.target.value;
        
        setInterview(newInterview);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if (id) {
            updateInterview();
        } else {
            addInterview();
        }
    }

    const updateInterview = () => {
        // set the id of the state copy
        interview.id = id;

        const init = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(interview)
        };

        fetch(`${url}/${id}`, init)
        .then(response => {
            if(response.status === 204) {
                return null;
            } else if (response.status === 400) {
                return response.json();
            } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => {
            if (!data) {
                // update was successful -> go to the previous page (application deatails) where we updated the interview
                navigate(`/applications/${interview.applicationId}`);
            } else {
                setErrors(data);
            }
        })
        .catch(console.log)
    }

    const addInterview = () => {
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(interview)
        };

        fetch(url, init)
        .then(response => {
            if (response.status === 201 || response.status === 400) {
                return response.json();
            } else {
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => {
            // add was successful as we have a positive ID -> go to the previous page (application deatails) where we added the interview
            if (data.interviewId) {
                navigate(`/applications/${interview.applicationId}`);
            } else {
                setErrors(data);
            }
        })
        .catch(console.log)
    }

    return (<>
        <main className='container'>
            <h2 className='mb-4 mt-2'>{id > 0 ? 'Update Interview' : 'Add Interview'}</h2>
                {errors.length > 0 && (
                    // Display any errors in the entered form elements
                    <div className='alert alert-danger'>
                        <p>The following errors were found:</p>
                        <ul>
                            {errors.map(error => (
                                <li style={{listStyle:"inside"}} key={error}>{error}</li>
                            ))}
                        </ul>
                    </div>
                )}
                <form onSubmit={handleSubmit}>
                    {/* <fieldset className='form-group'>
                        <label htmlFor='applicationId'>Application ID</label>
                        <input
                        id='applicationId'
                        name='applicationId'
                        type='number'
                        className='form form-control'
                        value={interview.applicationId}
                        onChange={handleChange}
                        />
                    </fieldset> */}
                    <fieldset className='form-group'>
                        <label htmlFor='type'>Interview Type</label>
                        <select id='type' name='type' className='form-control' value={interview.type} onChange={handleChange}>
                            <option value={'BEHAVIORAL'}>Behavioral</option>
                            <option value={'TECHNICAL'}>Technical</option>
                        </select>
                    </fieldset>
                    <fieldset className='form-group'>
                        <label htmlFor='result'>Interview Result</label>
                        <select id='result' name='result' className='form-control' value={interview.result} onChange={handleChange}>
                            <option value={'UNDETERMINED'}>Undetermiend</option>
                            <option value={'PASS'}>Passed</option>
                            <option value={'FAIL'}>Failed</option>
                        </select>
                    </fieldset>
                    <fieldset className='form-group'>
                        <label htmlFor='when'>Interview Date and Time</label>
                        <input
                        id='when'
                        name='when'
                        type='datetime-local'
                        className='form form-control'
                        value={interview.when}
                        onChange={handleChange}
                        />
                    </fieldset>
                    <fieldset className='form-group'>
                        <label htmlFor='notes'>Additional Notes</label>
                        <textarea
                        id='notes'
                        name='notes'
                        className='form form-control'
                        value={interview.notes}
                        onChange={handleChange}
                        />
                    </fieldset>
                    <div className='mt-4'>
                        <button className='btn btn-success btn-lg mr-2' type='submit'>{id > 0 ? 'Update Interview' : 'Add Interview'}</button>
                        <button className='btn btn-warning btn-lg' type='button' onClick={() => navigate(`/applications/${interview.applicationId}`)}>Cancel</button>
                    </div>
                </form>
        </main>
    </>);
}

export default InterviewForm;