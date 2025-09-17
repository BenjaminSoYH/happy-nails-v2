
import { useCallback, useEffect, useState } from 'react';
import PhoneInput from 'react-phone-number-input'
import 'react-phone-number-input/style.css'



const ContactInfo = () => {

    const [phone, setPhone] = useState<string | undefined>();
    const [firstName, setFirstName] = useState<string | undefined>();
    const [lastName, setLastName] = useState<string | undefined>();
    const [email, setEmail] = useState<string | undefined>();
    const [emailError, setEmailError] = useState<string | undefined>();

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

    useEffect(() => {
        if (email === undefined) {
            setEmailError(undefined);
            return;
        } 

        setEmailError(undefined);
        
        const timeout = setTimeout(() => {
            if (!validateEmail(email)) {
                setEmailError("Invalid Email");
            } else {
                setEmailError(undefined);
            }
        }, 500)

        return () => clearTimeout(timeout);
    }, [email])

    return (
        <>
        
            <h4 className="text-body fw-bold">Contact Info</h4>
            <div className="d-flex flex-column gap-3 w-100" style={{ maxWidth: "40rem" }}>

                <div className="input-group input-group-lg w-100">
                    <PhoneInput
                        placeholder="Enter phone number"
                        value={phone}
                        onChange={setPhone}
                        defaultCountry="US"
                        international
                        countryCallingCodeEditable={false}
                        inputComponent={InputComponent}
                        className="form-control"
                    />
                </div>

                
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
        </>
    )
}

export default ContactInfo;