package dto;

import java.util.List;

public class TuberculosisDto extends CoMorbidityDto{

	private List<CoMorbidityTreatmentDto> prevent;

    public List<CoMorbidityTreatmentDto> getPrevent() {
        return prevent;
    }

    public void setPrevent(List<CoMorbidityTreatmentDto> prevent) {
        this.prevent = prevent;
    }

}
