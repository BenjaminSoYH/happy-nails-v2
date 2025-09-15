import React from 'react'
import {Service} from "../interfaces/Service";
import {useNavigate} from "react-router-dom";

const ServiceItem = ({service, addService}: {service: Service, addService: any}) => {
    const navigate = useNavigate();
    const convertMins = (minutes : number) => {
        const hours = Math.floor(minutes / 60);
        const min = minutes % 60;
        return hours > 0 ? `${hours} hr ${min} min` : `${minutes} mins`;
    }

    const handleClick = () => {
        // addService(service);
        navigate(`/service/${service.id}`, {state: service}); // Navigate to a different page and pass in state.
    }

  return (
    <div id="serviceItem" className="py-2 px-1 text-black d-flex flex-column gap-1 cursor-pointer"
         style={{width:"30rem"}} onClick={() => {handleClick()}}>
        <h6 className="fw-semibold">{service.name}</h6>
        <h6 className="text-secondary fw-light">{service.description}</h6>
        <h6 className="fw-light">{`$${service.price.toFixed(2)} · ${convertMins(service.duration)}`}</h6>
    </div>
  )
}

export default ServiceItem;