import React, { useEffect, useState } from 'react';
import ExampleService from '../services/ExampleService';

const ExamplePage: React.FC = () => {
    const [examples, setExamples] = useState<Record<string, string>>({});
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchExamples = async () => {
            try {
                const data = await ExampleService.getAllExamples();
                setExamples(data);
            } catch (err) {
                setError('Failed to fetch examples');
                console.error(err);
            }
        };

        fetchExamples();
    }, []);

    return (
        <div>
            <h1>Example Page</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {Object.entries(examples).map(([key, value]) => (
                    <li key={key}>
                        <strong>{key}:</strong> {value}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ExamplePage;