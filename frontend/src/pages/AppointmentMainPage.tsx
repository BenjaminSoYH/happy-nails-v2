import AppointmentSummary from "./components/AppointmentSummary";
import { Service } from "./interfaces/Service";
import React, {useEffect, useState} from "react";
import ServiceContainer from "./components/ServiceContainer";
import {useLocation, useNavigate} from "react-router-dom";

const AppointmentMainPage: React.FC = () => {
    const[services, setServices] = useState<Service[]>([]);
    const[chosenServices, setChosen] = useState<Service[]>([]);
    const location = useLocation();
    const selected = location.state != null; // If there is a state stored in location
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

    useEffect(() => {
        if(selected){
            addService(location.state.service);
        }
    }, [selected])


    const addService = (service:Service) => {
        if(chosenServices.filter(s => s.id === service.id).length > 0){
            alert("Service has already been added!");
        } else {
            setChosen([...chosenServices, service]);
        }
    }

    // Define a method that removes a service
    const removeService = (id: number)=> {
        const services = chosenServices.filter(s => s.id !== id);
        setChosen(services);
    }

    // Define onClick method for next
    const handleNext = () => {
        if(chosenServices.length == 0){
            alert("Please select at least one service to proceed.");
        } else {
            navigate(`/nailtechs`);
        }
    }

    return (
        <div className="d-flex justify-content-center align-items-center mt-5">
            <div className="d-flex flex-row gap-5">
                <div>
                    <ServiceContainer services={services} addService={addService}></ServiceContainer>
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