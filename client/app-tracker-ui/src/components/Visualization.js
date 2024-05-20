import { Chart } from 'react-google-charts';
import { useState, useEffect } from 'react';
import { getNicerString } from '../utils';

// if time, figure out how to do custom tool tip
// leave as name for now
const createCustomTooltipHtml = (params) => {
    return `${params}`;
}

const options = {
    sankey: {
        tooltip: { isHtml: true },
        link: { colorMode: 'gradient' },
        node: {
            colors: ['#a6cee3', '#b2df8a', '#fb9a99', '#fdbf6f',
                '#cab2d6', '#ffff99', '#1f78b4', '#33a02c'],
            label: {
                fontName: 'Open Sans',
                fontSize: 14,
                color: '#000000'//,
            },
        },
    },
};

function Visualization() {
    const [jobApplications, setJobApplications] = useState([]);
    const [sankeyData, setSankeyData] = useState([]);

    const url = "http://localhost:8080/api/job/application";

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
    }, []);

    useEffect(() => {
        handleDataUpdate();
    }, [jobApplications]);

    function handleDataUpdate() {

        let pendingCount = 0;
        let offerCount = 0;
        let rejectionCount = 0;
        let noResponseCount = 0;
        let withdrawnCount = 0;
        let coldApplyCount = 0;
        let referralCount = 0;
        let careerFairCount = 0;

        for (const jobApplication of jobApplications) {
            if (jobApplication.status === 'PENDING') {
                pendingCount++;
            } else if (jobApplication.status === 'OFFER') {
                offerCount++;
            } else if (jobApplication.status === 'REJECTION') {
                rejectionCount++;
            } else if (jobApplication.status === 'NO_RESPONSE') {
                noResponseCount++;
            } else if (jobApplication.status === 'WITHDRAWN') {
                withdrawnCount++;
            }

            if (jobApplication.origin === 'COLD_APPLY') {
                coldApplyCount++;
            } else if (jobApplication.origin === 'REFERRAL') {
                referralCount++;
            } else if (jobApplication.origin === 'CAREER_FAIR') {
                careerFairCount++;
            }
        }

        const header = [["From", "To", "Weight", { 'type': 'string', 'role': 'tooltip', 'p': { 'html': true } }]];

        let newSankeyData = jobApplications.map((jobApplication) => {
            if (jobApplication.origin === 'COLD_APPLY') {
                return [`${getNicerString(jobApplication.origin)} (${coldApplyCount})`, "All Job Applications", 1, createCustomTooltipHtml(`${getNicerString(jobApplication.origin)} -> All Job Applications`)];
            } else if (jobApplication.origin === 'REFERRAL') {
                return [`${getNicerString(jobApplication.origin)} (${referralCount})`, "All Job Applications", 1, createCustomTooltipHtml(`${getNicerString(jobApplication.origin)} -> All Job Applications`)];
            } else if (jobApplication.origin === 'CAREER_FAIR') {
                return [`${getNicerString(jobApplication.origin)} (${careerFairCount})`, "All Job Applications", 1, createCustomTooltipHtml(`${getNicerString(jobApplication.origin)} -> All Job Applications`)];
            }
        });

        let results = jobApplications.map((jobApplication) => {
            if (jobApplication.status === 'PENDING') {
                return ["All Job Applications", `${getNicerString(jobApplication.status)} (${pendingCount})`, 1, createCustomTooltipHtml(`All Job Applications -> ${getNicerString(jobApplication.status)}`)];
            } else if (jobApplication.status === 'OFFER') {
                return ["All Job Applications", `${getNicerString(jobApplication.status)} (${offerCount})`, 1, createCustomTooltipHtml(`All Job Applications -> ${getNicerString(jobApplication.status)}`)];
            } else if (jobApplication.status === 'REJECTION') {
                return ["All Job Applications", `${getNicerString(jobApplication.status)} (${rejectionCount})`, 1, createCustomTooltipHtml(`All Job Applications -> ${getNicerString(jobApplication.status)}`)];
            } else if (jobApplication.status === 'NO_RESPONSE') {
                return ["All Job Applications", `${getNicerString(jobApplication.status)} (${noResponseCount})`, 1, createCustomTooltipHtml(`All Job Applications -> ${getNicerString(jobApplication.status)}`)];
            } else if (jobApplication.status === 'WITHDRAWN') {
                return ["All Job Applications", `${getNicerString(jobApplication.status)} (${withdrawnCount})`, 1, createCustomTooltipHtml(`All Job Applications -> ${getNicerString(jobApplication.status)}`)];
            }
        });

        newSankeyData = header.concat(newSankeyData, results);
        console.log(newSankeyData);
        setSankeyData(newSankeyData);
    }

    if (jobApplications.length === 0) {
        return (
            <div className='mt-4 mb-4' align="center">
                <p>There are currently no applications</p>
            </div>
        )
    }

    return (
        <div className='mt-4 mb-4' align="center">
            <Chart
                chartType="Sankey"
                width="82%"
                height="300px"
                data={sankeyData}
                options={options}
            />
        </div>
    );
}

export default Visualization;