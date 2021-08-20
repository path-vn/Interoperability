const listGender = [{ code: 'transgender-to-female', display: "Transgender female" },
  { code: 'transgender-to-male', display: "Transgender male" },
  { code: 'non-binary', display: "non-binary" },
  { code: 'male', display: "male" },
  { code: 'female', display: "female" },
  { code: 'other', display: "Other" },
  { code: 'not-disclosed', display: "Does not wish to disclose" },
  ]

  const listTransmissionRoute =[{ code: '1', display: "	Blood born" },
  { code: '2', display: "Sexual Relationship" },
  { code: '3', display: "Mother to child" },
  { code: '4', display: "Unidentified" },
  ]

  module.exports = Object.freeze({
    
    listGender:listGender,
    listTransmissionRoute:listTransmissionRoute,
});