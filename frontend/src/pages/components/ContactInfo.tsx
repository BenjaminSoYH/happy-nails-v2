
import { useCallback, useEffect, useState } from 'react';
import PhoneInput, { isValidPhoneNumber } from 'react-phone-number-input'
import 'react-phone-number-input/style.css'



const ContactInfo = () => {

    const [phone, setPhone] = useState<string>("");
    const [firstName, setFirstName] = useState<string>("");
    const [lastName, setLastName] = useState<string>("");
    const [nameError, setNameError] = useState<string>("")
    const [email, setEmail] = useState<string>("");
    const [emailError, setEmailError] = useState<string | undefined>();
    const [notes, setNotes] = useState<string>("");
    const [phoneError, setPhoneError] = useState<string | undefined>();

    const InputComponent = useCallback(
        (props: any) => (
            <input
                {...props}
                className="form-control form-control-lg rounded"
            />
        ),
        []
    );

    const validateEmail= (email: string): boolean => {
        const regex = /^[^\s@]+@[^\s@]+\.[a-zA-Z]{2,}$/;
        return regex.test(email);
    }

    const validatePhone = (p: string): boolean => {
        try {
            return isValidPhoneNumber(p);
        } catch {
            return false;
        }
    };

    useEffect(() => {
        if (!phone) {
            setPhoneError(undefined);
            return;
        }

        setPhoneError(undefined);

        const timeout = setTimeout(() => {
            if (!validatePhone(phone)) {
                setPhoneError("Invalid phone number");
            } else {
                setPhoneError(undefined);
            }
        }, 500)
        return () => clearTimeout(timeout);
    }, [phone])

    useEffect(() => {
        if (!email) {
            setEmailError(undefined);
            return;
        } 

        setEmailError(undefined);
        
        const timeout = setTimeout(() => {
            if (!validateEmail(email)) {
                setEmailError("Invalid email");
            } else {
                setEmailError(undefined);
            }
        }, 500)

        return () => clearTimeout(timeout);
    }, [email])

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        let ok = true;

        // require at least one contact method (email or phone)
        if (!email) {
            setEmailError("Please provide an email");
            ok = false;
        } else if (!validateEmail(email)) {
            setEmailError("Invalid email");
            ok = false;
        } else {
            setEmailError(undefined);
        }

        if (!phone) {
            setPhoneError("Please provide a phone number");
            ok = false;
        } else if (!validatePhone(phone)) {
            setPhoneError("Invalid phone number");
            ok = false;
        } else {
            setPhoneError(undefined);
        }

        if (!firstName || !lastName) {
            setNameError("Please enter a first and last name");
            ok = false;
        } else {
            setNameError("");
            ok = true;
        }

        if (!ok) return;

        // need to add api call
        // create a new customer first. we don't have all the inputs for api call yet to create a new appt 
        // (need to pass nailtech, service through all the way to this page).
        // for now we have status, notes, customer, timeslot. work on passing the timeslot 
        
        const createCustomer = async () => {
            const url = 'http://localhost:8080/api/customers/create';
            const name = firstName +" " + lastName;
            try {
                const response = await fetch(url, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        name,
                        email,
                        phone,
                        notes,
                    }),
                }); 

                if (!response.ok) {
                    throw new Error("Failed to create customer");
                }

                const data = await response.json();
                console.log("Customer created:", data);
                return data;
            } catch (error) {
                console.log("Failed to book appointment");
            };
        }
        createCustomer();

        console.log("Submitting appointment", {
            firstName, lastName, email, phone, notes
        });
    };

    return (
        <form onSubmit={handleSubmit}>
        
            <h4 className="text-body fw-bold">Contact Info</h4>
            <div className="d-flex flex-column gap-3 w-100" style={{ maxWidth: "40rem" }}>

                <div className="input-group input-group-lg w-100">
                    <PhoneInput
                        placeholder="Enter phone number"
                        value={phone || ""}   
                        onChange={(value) => setPhone(value || "")}
                        defaultCountry="US"
                        international
                        countryCallingCodeEditable={false}
                        inputComponent={InputComponent}
                    />
                    
                </div>
                {phoneError && <div className="text-danger mb-3">{phoneError}</div>}

                
                <small>
                    By providing your phone number you acknowledge you will 
                    receive occasional informational messages, including automated messages, 
                    on your mobile device from this merchant. Text STOP to opt out at any time, 
                    and text HELP to get HELP. Message and data rates may apply.
                </small>

                <div className="d-flex gap-3">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="First Name"
                        value={firstName}
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) => 
                            setFirstName(event.target.value)}
                    />
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Last Name"
                        value={lastName}
                        onChange={(event: React.ChangeEvent<HTMLInputElement>) =>
                            setLastName(event.target.value)}
                    />
                </div>
                {nameError && <div className="text-danger mb-3">{nameError}</div>}
                
                
                <input
                    type="email"
                    className="form-control"
                    placeholder="Email"
                    value={email}
                    onChange={(event: React.ChangeEvent<HTMLInputElement>) => 
                        setEmail(event.target.value)
                    }
                />
                {emailError && <div className="text-danger mb-3">{emailError}</div>}
            </div>
            <div className="align-items-center mt-4">
                <h5 className="text-body fw-bold ">Appointment Notes</h5>
                <textarea
                    className="form-control"
                    placeholder="Notes"
                    value={notes}
                    onChange={(event: React.ChangeEvent<HTMLTextAreaElement>) =>
                        setNotes(event.target.value)
                    }
                />
            </div>
            
            <button className="btn btn-primary mt-4 w-100 py-2 fs-5" type="submit">Book Appointment</button>
        </form>
    )
}

export default ContactInfo;