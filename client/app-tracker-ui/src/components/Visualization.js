import { Chart } from 'react-google-charts';
import { useState, useEffect } from 'react';

// if time, figure out how to do custom tool tip
// leave as name for now
const createCustomTooltipHtml = (params) => {
    return `${params}`;
}

// const options = {
//     sankey: {
//         tooltip: { isHtml: true },
//         link: { colorMode: 'gradient' },
//         node: {
//             colors: ['#a6cee3', '#b2df8a', '#fb9a99', '#fdbf6f',
//                 '#cab2d6', '#ffff99', '#1f78b4', '#33a02c'],
//             label: {
//                 fontName: 'Open Sans',
//                 fontSize: 14,
//                 color: '#000000'//,
//             },
//         },
//     },
// };

function Visualization() {
    // const [solarPanels, setSolarPanels] = useState([]);
    // const [data, setData] = useState([]);

    // const url = "http://localhost:8080/api/solarpanel";

    // useEffect(() => {
    //     fetch(url)
    //         .then(response => {
    //             if (response.status === 200) {
    //                 return response.json();
    //             } else {
    //                 return Promise.reject(`Unexpected status code: ${response.status}`);
    //             }
    //         })
    //         .then(data => setSolarPanels(data)) // here we are setting our data to our state variable
    //         .catch(console.log);
    // }, []);


    // useEffect(() => {
    //     handleDataUpdate();
    // }, [solarPanels]);

    // function handleDataUpdate() {
    //     const header = ["From", "To", "Weight", { 'type': 'string', 'role': 'tooltip', 'p': { 'html': true } }];
    //     let newData = solarPanels.map((solarPanel) =>
    //         [solarPanel.section, "All Solar Panels", 1, createCustomTooltipHtml(`${solarPanel.section}`)]
    //     );
    //     const allToMaterials = solarPanels.map((solarPanel) =>
    //         ["All Solar Panels", solarPanel.material, 1, createCustomTooltipHtml(`All Solar Panels`)]
    //     );
    //     console.log(solarPanels);
    //     newData.unshift(header);
    //     newData = newData.concat(allToMaterials);
    //     const materialToYear = solarPanels.filter(sp => sp.section != "The Ridge").map((solarPanel) => {
    //         if (solarPanel.yearInstalled > 2010) {
    //             return [solarPanel.material, "Installed after 2000", 1, createCustomTooltipHtml(`${solarPanel.material}`)];
    //         } else {
    //             return [solarPanel.material, "Installed before 2000", 1, createCustomTooltipHtml(`${solarPanel.material}`)];
    //         }
    //     }
    //     );
    //     let isTracking = solarPanels.filter(sp => sp.tracking && sp.yearInstalled > 2000)
    //         .map(sp => ["Installed after 2000", "Is tracking", 1, createCustomTooltipHtml(`Installed after 2000`)]);
    //     let earlyRidgeTracking = solarPanels.filter(sp => sp.section === "The Ridge" && sp.yearInstalled <= 2000 && sp.tracking)
    //         .map(sp => ["All Solar Panels", "Is tracking", 1, createCustomTooltipHtml(`The Ridge, installed before 2000, tracking`)]);
    //     newData = newData.concat(materialToYear, isTracking, earlyRidgeTracking);

    //     console.log(newData);
    //     setData(newData);
    // }

    return (
        <div align="center">
            {/* <Chart
                chartType="Sankey"
                width="82%"
                height="300px"
                data={data}
                options={options}
            /> */}
        </div>
    );
}

export default Visualization;