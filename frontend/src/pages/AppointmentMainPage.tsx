import AppointmentSummary from "./components/AppointmentSummary";
import { Service } from "./interfaces/Service";
import React, {useEffect, useState} from "react";
import ServiceContainer from "./components/ServiceContainer";
import {useLocation, useNavigate} from "react-router-dom";

const AppointmentMainPage: React.FC = () => {
    const[services, setServices] = useState<Service[]>([]);
    const[chosenServices, setChosen] = useState<Service[]>([]);
    const[chosenIds, setChosenIds] = useState<number[]>([]);
    const navigate = useNavigate();

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
    }, []);

    const addService = (service:Service) => {
        if(chosenServices.filter(s => s.id === service.id).length > 0){
            alert("Service has already been added!");
        } else {
            setChosen([...chosenServices, service]); // Update the chosen services
            setChosenIds([...chosenIds, service.id]);
        }
    }

    // Define a method that removes a service
    const removeService = (id: number)=> {
        const services = chosenServices.filter(s => s.id !== id);
        setChosen(services);
        const ids = chosenIds.filter(val => val !== id);
        setChosenIds(ids);
    }

    // Define onClick method for next
    const handleNext = () => {
        if(chosenServices.length == 0){
            alert("Please select at least one service to proceed.");
        } else {
            navigate(`/nailtechs`, {state: chosenIds});
        }
    }

    return (
        <div className="d-flex justify-content-center align-items-center mt-5">
            <div className="d-flex flex-row gap-5">
                <div>
                    <ServiceContainer services={services} addService={addService} chosenServices={chosenServices}></ServiceContainer>
                </div>

                <div className="d-flex flex-column gap-2">
                    <AppointmentSummary services={chosenServices} removeService={removeService}></AppointmentSummary>
                    <button type="button" className="btn btn-primary fw-bold" onClick={() => handleNext()}>Next</button>
                </div>

            </div>
        </div>
    );
};

export default AppointmentMainPage;