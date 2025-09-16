import React from 'react'
import {NailTech} from "../interfaces/NailTech";
import {useNavigate} from "react-router-dom";
import {Service} from "../interfaces/Service";


const StaffComponent = ({tech, service}:{tech:NailTech, service: Service}) => {
    const navigate = useNavigate();

    // Define a method that handles the click
    const handleClick = () => {
        navigate(`/`, {state: {tech: tech, service: service}});
    }

  return (
      <div id="staffItem" className="py-3 px-1 text-black d-flex flex-row gap-3
      align-items-center cursor-pointer" onClick={() => (handleClick())}>
          <div id="image" className="border border-secondary-subtle rounded-circle"
               style={{width:"5rem", height:"5rem", backgroundImage: `url('${tech.path}')`,
                   backgroundSize:"cover", backgroundPosition:"center"}}>
          </div>
          <div className="d-flex flex-column gap-0">
              <h5 className="">{tech.name}</h5>
              <h5 className="text-secondary fw-light">{tech.description}</h5>
          </div>
      </div>
  )
}

export default StaffComponent;