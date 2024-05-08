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
    
    const url = 'http://localhost:8080/api/job/application';
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

    // helper functions

    const handleSubmit = event => {
        event.preventDefault();
    }

    const handleChange = event => {
        const newApplication = { ...application };

        if (event.target.type === 'select-one') {
            const selected = getOption(event.target);
            newApplication[event.target.name] = selected;
        } else {
            newApplication[event.target.name] = event.target.value;
        }
        
        setApplication(newApplication);
    }

    const addOrEdit = () => {
        return id ? 'Edit Application' : 'Add Application';
    }

    return (
    <main className='container'>

        <h2 className='mb-4'>{addOrEdit()}</h2>

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
                    <label htmlFor='dateApplied' className='form-label'>Date Applied</label>
                    
                    <input 
                    type='date'
                    className='form-control'
                    id='dateApplied'
                    name='dateApplied'
                    value={application.dateApplied}
                    onChange={handleChange}
                    ></input>
                </fieldset>

                <div>
                    <button className='btn btn-success me-2' type='submit'>{addOrEdit()}</button>
                    <Link className='btn btn-warning' to={'/'}>Cancel</Link>
                </div>
        </form>

    </main>
    );
}

export default JobApplicationForm;