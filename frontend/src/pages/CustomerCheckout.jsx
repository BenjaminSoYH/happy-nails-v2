import ContactInfo from "./components/ContactInfo";
import AppointmentSummary from './components/AppointmentSummary';

const CustomerCheckout = () => {

    const services = [
        {id: 12, name: "Straight-Razor Shave", description: "", duration: 45, price: 40.00},
        {id: 13, name: "Haircut", description: "", duration: 45, price: 50.00},
    ]

    return (
        <>
                    <div className="container-fluid px-3"> 
                        <div className="row justify-content-center">
                            
                            <div className="col-xxl-5 col-xl-5 col-lg-6 col-md-8 mt-3">
                                <ContactInfo/>
                                
                            </div>
                            <div className="col-xxl-2 col-xl-4 col-lg-6 col-md-8 mt-3">
                                <AppointmentSummary removeService={() => {}} services={services}/>
                                
                            </div>
                        </div>
                    </div>
                </>
    )
}

export default CustomerCheckout;