import { Chart } from 'react-google-charts';
import { useState, useEffect } from 'react';

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

    const url = "http://localhost:8080/api/job/application"

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
    }, [jobApplications]); // double check on this

    useEffect(() => {
        handleDataUpdate();
    }, [jobApplications]);

    function handleDataUpdate() {
        const header = [["From", "To", "Weight", { 'type': 'string', 'role': 'tooltip', 'p': { 'html': true } }]];

        let newSankeyData = jobApplications.map(jobApplication =>
            [jobApplication.origin, "All Job Applications", 1, createCustomTooltipHtml(`${jobApplication.orgin} -> All Job Applications`)]
        );

        let results = jobApplications.map(jobApplication =>
            ["All Job Applications", jobApplication.status, 1, createCustomTooltipHtml(`All Job Applications -> ${jobApplication.orgin}`)]
        );

        newSankeyData = header.concat(newSankeyData, results);
        console.log(newSankeyData);
        setSankeyData(newSankeyData);
    }

    return (
        <div align="center">
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