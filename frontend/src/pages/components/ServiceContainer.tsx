import React, {useEffect, useState} from 'react'
import {Service} from "../interfaces/Service";
import ServiceItem from "./ServiceItem";

const ServiceContainer = ({services, addService} : {services: Service[], addService: any}) => {
  const[serviceList, setList] = useState<Service[]>(services ?? []);
  useEffect(() => {
    setList(services);
  }, [services])

  return (
    <div>
      {serviceList.map((service, index) => (
          <div key={service.id}>
            <ServiceItem addService={addService} service={service}></ServiceItem>
            {index < serviceList.length - 1 && <hr></hr>}
          </div>
      ))}
    </div>
  )
}

export default ServiceContainer;