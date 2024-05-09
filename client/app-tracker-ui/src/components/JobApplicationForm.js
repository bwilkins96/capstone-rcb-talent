import { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';

import { getOption, getTodayString } from '../utils';

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

function JobApplicationForm() {
    const [application, setApplication] = useState(JobApplicationDefault);
    const [errors, setErrors] = useState([]);
    const [postings, setPostings] = useState([]);
    
    const url = 'http://localhost:8080/api/job/application';
    const postingUrl = 'http://localhost:8080/api/job/posting';
    const navigate = useNavigate();
    const { id } = useParams();

    // fetch application data
    useEffect(() => {
        if (!id) {
            return;
        }

        fetch(`${url}/${id}`)
        .then(response => {
            if (response.status === 200) {
                return response.json();
            }

            return Promise.reject(`Unexpected status code: ${response.status}`);
        })
        .then(data => {
            setApplication(data);
        })
        .catch(console.log);
    }, [id]);

    // fetch posting data
    useEffect(() => {
        fetch(postingUrl)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setPostings(data)) // here we are setting our data to our state variable
            .catch(console.log);
    }, []);

    // helper functions

    const getInit = method => {
        return {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(application)
        };
    }

    const getErrorLi = error => {
        return <li key={error}>{error}</li>;
    }

    const getPostingOption = posting => {
        return (
            <option key={posting.postingId} value={posting.postingId}>
                {posting.role} - {posting.level}
            </option>
        );
    }

    const addOrEdit = () => {
        return id ? 'Edit Application' : 'Add Application';
    }

    // event handlers

    const handleAdd = () => {
        fetch(url, getInit('POST'))
        .then(response => {
            if (response.status === 201 || response.status === 400) {
                return response.json();
            }

            return Promise.reject(`Unexpected status code: ${response.status}`);
        })
        .then(data => {
            if (data.applicationId) {
                navigate('/applications');
            } else {
                setErrors(data);
            }
        })
        .catch(console.log);
    }

    const handleEdit = () => {
        fetch(`${url}/${id}`, getInit('PUT'))
        .then(response => {
            if (response.status === 204) {
                return null;
            } else if (response.status === 400) {
                return response.json();
            }
            
            return Promise.reject(`Unexpected status code: ${response.status}`);
        })
        .then(data => {
            if (!data) {
                navigate('/applications');
            } else {
                setErrors(data);
            }
        })
        .catch(console.log);
    }

    const handleSubmit = event => {
        event.preventDefault();

        if (id) {
            handleEdit();
        } else {
            handleAdd();
        }
    }

    const handleChange = event => {
        const newApplication = { ...application };

        if (event.target.type === 'select-one') {
            const selected = getOption(event.target);

            if (event.target.id === 'posting') {
                newApplication.posting = { postingId: selected };
            } else {
                newApplication[event.target.name] = selected;
            }

        } else {
            newApplication[event.target.name] = event.target.value;
        }
        
        setApplication(newApplication);
    }

    return (
    <main className='container'>

        <h2 className='mb-4 mt-2'>{addOrEdit()}</h2>

        {(errors.length > 0) && (
                <div className='alert alert-danger mb-2 max600'>
                    <p>The following errors were found:</p>
                    <ul>
                        {errors.map(error => getErrorLi(error))}
                    </ul>
                </div>
        )}

        <form onSubmit={handleSubmit}>
                <fieldset className='mb-4'>
                    <label htmlFor='status' className='form-label'>Status</label>

                    <select 
                    id='status' 
                    name='status' 
                    value={application.status}
                    onChange={handleChange}>
                        <option value='PENDING'>Pending</option>
                        <option value='OFFER'>Offer</option>
                        <option value='REJECTION'>Rejection</option>
                        <option value='NO_RESPONSE'>No Response</option>
                        <option value='WITHDRAWN'>Withdrawn</option>
                    </select>
                </fieldset>
                <fieldset className='mb-4'>
                    <label htmlFor='origin' className='form-label'>Origin</label>

                    <select 
                    id='origin' 
                    name='origin' 
                    value={application.origin}
                    onChange={handleChange}>
                        <option value='COLD_APPLY'>Cold Apply</option>
                        <option value='REFERRAL'>Referral</option>
                        <option value='CAREER_FAIR'>Career Fair</option>
                    </select>
                </fieldset>
                <fieldset className='mb-4'>
                    <label htmlFor='posting' className='form-label'>Posting</label>

                    <select 
                    id='posting' 
                    name='posting' 
                    value={application.posting.postingId}
                    onChange={handleChange}>
                        { postings.map(p => getPostingOption(p)) }
                    </select>
                </fieldset>
                <fieldset className='mb-4'>
                    <label htmlFor='dateApplied' className='form-label'>Date Applied</label>
                    
                    <input 
                    type='date'
                    className='form-control'
                    id='dateApplied'
                    name='dateApplied'
                    value={application.dateApplied}
                    onChange={handleChange}
                    required
                    ></input>
                </fieldset>
                <fieldset className='mb-4'>
                    <label htmlFor='notes' className='form-label'>Notes</label>
                    
                    <textarea 
                    className='form-control'
                    id='notes'
                    name='notes'
                    value={application.notes}
                    onChange={handleChange}
                    cols='8'
                    ></textarea>
                </fieldset>

                <div>
                    <button className='btn btn-success mr-2' type='submit'>{addOrEdit()}</button>
                    <Link className='btn btn-warning' to={'/applications'}>Cancel</Link>
                </div>
        </form>

    </main>
    );
}

export default JobApplicationForm;