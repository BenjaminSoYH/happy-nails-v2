import React, { useEffect, useState } from 'react';
import ExampleService from '../services/ExampleService';
import AppointmentSummary from "./TimeSlots";

const ExamplePage: React.FC = () => {
    // const [examples, setExamples] = useState<Record<string, string>>({});
    // const [error, setError] = useState<string | null>(null);
    //
    // useEffect(() => {
    //     const fetchExamples = async () => {
    //         try {
    //             const data = await ExampleService.getAllExamples();
    //             setExamples(data);
    //         } catch (err) {
    //             setError('Failed to fetch examples');
    //             console.error(err);
    //         }
    //     };
    //
    //     fetchExamples();
    // }, []);

    return (
        <div>
            <AppointmentSummary></AppointmentSummary>
        </div>
    );
};

export default ExamplePage;