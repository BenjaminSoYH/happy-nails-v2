import AppointmentSummary from "./components/AppointmentSummary";
import { Service } from "./interfaces/Service";
import React, {useEffect, useState} from "react";
import ServiceContainer from "./components/ServiceContainer";

const AppointmentMainPage: React.FC = () => {
    const[services, setServices] = useState<Service[]>([]);
    const[chosenServices, setChosen] = useState<Service[]>([]);

    useEffect(() => {
        const fetchServices = async () => {
            try {
                const url = `http://localhost:8080/api/services`;
                const response = await fetch(url); // Fetch data
                const json = await response.json(); // Convert to JSON
                const data = await json.content; // Get the content from the JSON
                setServices(data); // Set the state
                console.log("Services fetched successfully.")

            } catch (e) {
                console.log("Failed to fetch services.");
            }
        }
        fetchServices();
    }, [])


    const addService = (service:Service) => {
        setChosen([...chosenServices, service])
    }

    return (
        <div className="d-flex justify-content-center align-items-center mt-5">
            <div className="d-flex flex-row gap-5">
                <div>
                    <ServiceContainer services={services} addService={addService}></ServiceContainer>
                </div>

                <div className="d-flex flex-column gap-2">
                    <AppointmentSummary services={chosenServices}></AppointmentSummary>
                    <button type="button" className="btn btn-primary fw-bold">Next</button>
                </div>

            </div>
        </div>
    );
};

export default AppointmentMainPage;