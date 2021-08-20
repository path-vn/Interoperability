using System;
using DevExpress.Xpo;

namespace GlobitsHivInfoAdapter.Dto
{

    public class ValuesetDto
    {
        public String code { get; set; }
        public String display { get; set; }
        public String definition { get; set; }

        public ValuesetDto(String code, String display)
        {
            this.code = code; this.display = display;
        }
    }

}