import axios from 'axios';

const BASE_URL = 'http://localhost:8080/examples'; // Replace with your actual API base URL

class ExampleService {
    async getExample(key: string): Promise<string> {
        try {
            const response = await axios.get(`${BASE_URL}/${key}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching example:', error);
            throw error;
        }
    }

    async getAllExamples(): Promise<Record<string, string>> {
        try {
            const response = await axios.get(BASE_URL);
            return response.data;
        } catch (error) {
            console.error('Error fetching all examples:', error);
            throw error;
        }
    }

    // Create or update an example
    async addOrUpdateExample(key: string, value: string): Promise<string> {
        try {
            const response = await axios.post(`${BASE_URL}/${key}`, null, {
                params: { value },
            });
            return response.data;
        } catch (error) {
            console.error('Error adding or updating example:', error);
            throw error;
        }
    }
}

export default new ExampleService();