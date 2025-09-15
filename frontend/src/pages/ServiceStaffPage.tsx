import React, {useEffect, useState} from 'react'
import {useLocation} from "react-router-dom";
import {Service} from "./interfaces/Service";
import StaffComponent from "./components/StaffComponent";

const ServiceStaffPage = () => {
    const location = useLocation();
    const service: Service = location.state;
    const[nailTechs, setNailTechs] = useState([]);

    useEffect(() => {
        const fetchNailTechs = async () => {
            try {
                const url = `http://localhost:8080/api/services/${service.id}/nailtechs`
                const response = await fetch(url);
                const data = await response.json();
                console.log(data);
                setNailTechs(data);
                console.log("Nail techs fetched successfully")
            } catch (e) {
                console.log("Failed to fetch nail techs" + e);
            }
        }
        fetchNailTechs();
    }, []);

    const convertMins = (minutes : number) => {
        const hours = Math.floor(minutes / 60);
        const min = minutes % 60;
        return hours > 0 ? `${hours} hr ${min} min` : `${minutes} mins`;
    }


  return (
      <div className="d-flex justify-content-center mt-5">

          <div className="d-flex flex-column w-50">
              <div className="d-flex flex-column justify-content-start">
                  <h3 className="fw-semibold">{service.name}</h3>
                  <h5 className="text-secondary">{service.description}</h5>
                  <h5 className="fw-light">{`$${service.price.toFixed(2)} · ${convertMins(service.duration)}`}</h5>
              </div>

              <div className="d-flex flex-column gap-2 mt-5">
                  <h5 className="fw-semibold">Staff</h5>
                  {nailTechs.map((tech, index) => (<StaffComponent tech={tech}></StaffComponent>))}
              </div>
          </div>


      </div>
  )
}

export default ServiceStaffPage;