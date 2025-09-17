import React, {useEffect, useState} from 'react'
import {NailTech} from "./interfaces/NailTech";
import StaffComponent from "./components/StaffComponent";
import {useLocation} from "react-router-dom";
import {type} from "node:os";

const StaffPage = () => {
    const location = useLocation();
    const serviceIds = location.state;
    const[techs, setTechs] = useState<NailTech[]>([]);

    useEffect(() => {
        // Method fetches nail techs.
        const fetchTechs = async () => {
            try {
                const url = 'http://localhost:8080/api/services/nailtechs';
                // Stringify the serviceId as a payload
                const response = await fetch(url, {
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify({serviceIds})
                });
                const techList = await response.json();
                setTechs(techList);
            } catch(e) {
                console.log("Failed to fetch techs!" + e);
            }
        }
        fetchTechs();
    }, [])
  return (
      <div className="d-flex justify-content-center mt-5">

          <div className="d-flex flex-column w-50">
              <div className="d-flex flex-column gap-2 mt-5">
                  <h5 className="fw-semibold">Please select your nail tech!</h5>
                  {techs.map((tech, index) => (
                      <div key={tech.id}>
                          <StaffComponent tech={tech}></StaffComponent>
                          {index < techs.length - 1 && <hr></hr>}
                      </div>
                  ))}
              </div>
          </div>
      </div>
  )
}

export default StaffPage;