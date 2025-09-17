import React from 'react'
import {Service} from "../interfaces/Service";

const ServiceItem = ({service, addService, selected}: {service: Service, addService: any, selected:boolean}) => {
    const convertMins = (minutes : number) => {
        const hours = Math.floor(minutes / 60);
        const min = minutes % 60;
        return hours > 0 ? `${hours} hr ${min} min` : `${minutes} mins`;
    }

    const handleClick = () => {
        addService(service);
    }

  return (
    <div id="serviceItem" className="py-2 px-1 text-black d-flex flex-column gap-1 cursor-pointer"
         style={{width:"30rem"}} onClick={() => {handleClick()}}>
        <h6 className="fw-semibold">{service.name}</h6>
        <h6 className="text-secondary fw-light">{service.description}</h6>
        <h6 className="fw-light">{`$${service.price.toFixed(2)} · ${convertMins(service.duration)}`}</h6>
        {selected && <h6 className="text-primary">✓ Added!</h6>}
    </div>
  )
}

export default ServiceItem;