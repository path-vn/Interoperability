using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class HivDiagnosisDto
    {
        public Nullable<DateTime> dateOfConfirmation { get; set; } //Date of Confirmation
        public String confirmingLab { get; set; } //Confirming Lab	
        public Nullable<DateTime> dateOfSpecimenCollection { get; set; } //Date of Specimen Collection	
        public String placeOfSpecimenCollection { get; set; } //Place of Specimen Collection	
    }
}
