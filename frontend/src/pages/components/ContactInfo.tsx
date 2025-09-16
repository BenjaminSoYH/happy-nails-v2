
import { useState } from 'react';
import PhoneInput from 'react-phone-number-input'
import 'react-phone-number-input/style.css'



const ContactInfo = () => {

    const [value, setValue] = useState<string | undefined>();

    return (
        <>
            <h4 className="text-body fw-bold">Contact Info</h4>
            <PhoneInput
                placeholder="Enter phone number"
                value={value}
                onChange={setValue}
                defaultCountry="US"
                international
                countryCallingCodeEditable={false}
            />
        </>
    )
}

export default ContactInfo;