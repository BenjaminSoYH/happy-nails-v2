import AppointmentSummary from "./components/AppointmentSummary";
import { Service } from "./interfaces/Service";
import React from "react";

const AppointmentMainPage: React.FC = () => {
    // Test Services Prop
    const services = [
        {name: "Straight-Razor Shave", description: "", duration: 45, price: 40.00},
        {name: "Haircut", description: "", duration: 45, price: 50.00},

    ]
    // Test Empty Services Prop
    const emptyServices: Service[] = [];

    return (
        <div className="d-flex justify-content-center align-items-center px-2 py-5 mt-5 gap-lg-5">
            <div className="d-flex flex-row gap-3">
                <div className="d-flex flex-column gap-3">
                    <div>
                        {/*Space for the calendar*/}
                        calendar
                    </div>

                    <div className="border border-secondary-subtle"></div>

                    <div>
                        {/*Space for the timeslots*/}
                        timeslots
                    </div>
                </div>

                <div>
                    <AppointmentSummary services={services}></AppointmentSummary>
                </div>

            </div>
        </div>
    );
};

export default AppointmentMainPage;